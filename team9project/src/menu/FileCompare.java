package menu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;

public class FileCompare {
	/*private ArrayList<String> leftContent, rightContent;*/
	private int lcsLength = 0;
	
	public FileCompare() {
		
	}
	
	public int getLcsLength() {
		return lcsLength;
	}
	public int[][] makeLCSTable(String[] left, String[] right) {
		int[][] table = null;
		int max;
		
		// make table
		table = new int[right.length][];
		for(int i = 0; i < right.length; i++)
			table[i] = new int[left.length];
		
		// table initialize(1)
		for(int i = 0; i < left.length; i++)
			table[0][i] = 0;
		
		// Calculate Table Index and LCS length
		// 여기서부턴 인덱스의 개념과 실제 순서가 같다고 보면된다. ( 1번째 인덱스 = 1(0이 아님) )
		for(int i = 1; i < right.length; i++) {
			max = 0;
			table[i][0] = 0;	// table initialize(2)
			for(int j = 1; j < left.length; j++) {
				if(left[j].equals(right[i]))	// 문장이 서로 같은 경우
				{
					max = table[i - 1][j - 1] + 1;
					table[i][j] = max;
				}
				else	// 문장이 서로 다를경우						 ... 2 3 4 5 → j
				{											//  . ..........
					if(table[i][j - 1] > table[i - 1][j])	//	. ..........
	                    table[i][j] = table[i][j-1];		//	2 .... y ...
	                else									//	3 .. z k ...	
	                    table[i][j] = table[i-1][j];		//	↓ 			※ k : y z 중 큰 녀석
				}											//  i
			}
			if(lcsLength < max)
				lcsLength = max;
		}
		return table;
	}
	
	public ArrayList<Integer> getDifferentLineNumberIndex(ArrayList<String> left, ArrayList<String> right) {
		ArrayList<Integer> differ_index = new ArrayList<Integer>();
		
		for(int i = 0; i < left.size(); i++) {
			if(left.get(i).equals(right.get(i)))	
				continue;
			else									// 다른 경우, 그부분의 인덱스를 저장
				differ_index.add(i);
		}
		return differ_index;
	}
	
	public ArrayList<String> makeLCSString(int leftLength, int rightLength, int lcsLength, int[][] table, String[] str1, String[] str2) // 0 이 포함된 문자열길이
	{
		ArrayList<String> lcs1 = new ArrayList<String>();
		ArrayList<String> lcs2 = new ArrayList<String>();
		int count1 = 0, count2 = 0;			// 같은 위치개수를 찾는다
		
		// right 기준
		int temp0, temp1, for_j;
		temp1 = lcsLength;
		temp0 = temp1 - 1;
		for_j = leftLength - 1;
		
		for(int i = rightLength - 1 ; i > 0 ; i--) {
			for(int j = for_j; j > 0; j--) {
				if (table[i][j] == temp1 && table[i][j - 1] == temp0 && table[i - 1][j - 1] == temp0 && table[i - 1][j] == temp0) {
	                temp0--;
	                temp1--;
	                lcs1.add(0, str2[i]); // lcs1 = str2[i] + lcs1;
	                
	                if(i == j)
	                	count2++;
	                
	                for_j = j;
	                break;
				}
			}
		}
		
		// left 기준
		temp1 = lcsLength;
		temp0 = temp1 - 1;
		for_j = rightLength - 1;
		
		for(int i = leftLength - 1 ; i > 0 ; i--) {
			for(int j = for_j; j > 0; j--) {
				if (table[j][i] == temp1 && table[j][i - 1] == temp0 && table[j - 1][i - 1] == temp0 && table[j - 1][i] == temp0) {
	                temp0--;
	                temp1--;
	                lcs2.add(0, str1[i]); // lcs2 = str1[i] + lcs2;
	                if(i == j)
	                	count1++;
	                for_j = j;
	                break;
				}
			}
		}
		
		if(count2 > count1)
			return lcs1;
		else
			return lcs2;
	}
	
	public void synchronizingTextContent(ArrayList<String> left, ArrayList<String> right, ArrayList<String> lcs) {
		// 위에서 아래로, 아래에서 위로 각각 LCS를 실행해보고 최소한의 줄로 동기화를 완료하는 쪽을 택한다.
		ArrayList<String> down_left;		// 위    -> 아래
		ArrayList<String> down_right;
		ArrayList<String> up_left, up_right;			// 아래  -> 위
		
		// initialize
		down_left = (ArrayList<String>) left.clone();
		down_right = (ArrayList<String>) right.clone();
		up_left = (ArrayList<String>) left.clone();
		up_right = (ArrayList<String>) right.clone();
		
		// down 실행
		for(int i = 0, j = 0, k = 0 ; ; ) {
			if(k == lcs.size()) {
				if(i == down_left.size() && j == down_right.size())
					break;
				if(i == down_left.size()) // 왼쪽이 끝났을 경우
				{
					down_left.add(i, "");
					i++;
					j++;
				}
				else if(j == down_right.size()) // 오른쪽이 끝났을 경우
				{
					down_right.add(i, "");
					i++;
					j++;
				}
				else // 둘다 안끝난 경우
				{
					i++;
					j++;
				}
			}
			else if(down_left.get(i).equals(lcs.get(k)) && down_right.get(j).equals(lcs.get(k))) {
				i++; j++; k++; 
			}
			else if(down_left.get(i).equals(lcs.get(k))) {
				down_left.add(i, "");
				i++;
				j++;
			}
			else if(down_right.get(j).equals(lcs.get(k))) {
				down_right.add(j, "");
				i++;
				j++;
			}
			else {
				i++;
				j++;
			}
		}
		
		// up 실행
		for(int i = up_left.size() - 1, j = up_right.size() - 1, k = lcs.size() - 1; ; ) {
			if(k == -1) {
				if(i == -1 && j == -1)
					break;
				if(i == -1) // 왼쪽이 끝났을 경우
				{
					up_left.add(0, "");
					j--;
				}
				else if(j == -1) // 오른쪽이 끝났을 경우
				{
					up_right.add(0, "");
					i--;
				}
				else // 둘다 안끝난 경우
				{
					i--;
					j--;
				}
			}
			else if(up_left.get(i).equals(lcs.get(k)) && up_right.get(j).equals(lcs.get(k))) {
				i--; j--; k--; 
			}
			else if(up_left.get(i).equals(lcs.get(k))) {
				up_left.add(i+1, "");			// 인덱스 에러나지 않을까...ㅜㅜ
				j--;
			}
			else if(up_right.get(j).equals(lcs.get(k))) {
				up_right.add(j+1, "");
				i--;
			}
			else {
				i--;
				j--;
			}
		}
		
		
		// 비교 후 최소한으로 동기화 한 쪽을 택한다
		if(down_left.size() > up_left.size()) {
			left.clear();
			right.clear();
			for(int i = 0; i < up_left.size(); i++) {
				left.add(up_left.get(i));
				right.add(up_right.get(i));
			}
			/*left = (ArrayList<String>) up_left.clone();
			right = (ArrayList<String>) up_right.clone();*/
		}
		else {
			left.clear();
			right.clear();
			for(int i = 0; i < down_left.size(); i++) {
				left.add(down_left.get(i));
				right.add(down_right.get(i));
			}
			/*left = (ArrayList<String>) down_left.clone();
			right = (ArrayList<String>) down_right.clone();*/
		}
	}
}