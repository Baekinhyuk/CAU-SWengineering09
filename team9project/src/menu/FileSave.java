package menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSave {
	private int isSave;
	private File savefile;
	
	FileSave(){}
	FileSave(File f,JTextPane TA){
		fileSave(f,TA);
	}
	
	public void fileSave(File f,JTextPane TA) {
		
		JFileChooser fileSearch;
		if(f == null){
			//파일이 비어있는경우 처음경로에 파일저장하도록설정
			fileSearch = new JFileChooser();
		}
		else{
			//읽은 파일이 있는경우 그 파일에 위치에서 시작해서파일저장하도록설정
			fileSearch = new JFileChooser(f.getAbsolutePath());
		}
		// 탐색기 객체 생성
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"txt"); // txt 필터
		fileSearch.setFileFilter(filter); // 필터 장착
		fileSearch.setDialogTitle("파일저장하기");
		
		if(f != null){
			//불러온파일이 있을경우에는 기본으로 그 파일을 설정해서 저장시 기본적으로 그 파일에 이름으로 저장하도록설정
			fileSearch.setSelectedFile(f);
		}
		else{
			//처음에 파일이 비어있을경우 이렇게 설정해서 저장시 .txt로 표현되도록 설정하려고 빈파일경로를 .txt로해서 이름으로설정
			savefile = new File(".txt");
			fileSearch.setSelectedFile(savefile);
		}
		
		isSave = fileSearch.showSaveDialog(null); // 탐색기 열기
		
		
		if(isSave == JFileChooser.APPROVE_OPTION) { 
			// 저장을 클릭
			f = fileSearch.getSelectedFile();
			if(f.exists()){//저장하려는 파일이 존재할경우(같은이름) 파일을 덮어쓸껀지 어쩔지 물어봐서 실행하도록설정
				int existF = JOptionPane.showConfirmDialog(null, "이미 파일이 존재합니다. 덮어쓸까요?");
				if(existF == JOptionPane.YES_OPTION){
					//YES버튼을 눌렀을 시에만 저장하도록 설정
					textSave(f,TA);	
				}
			}
			else
			{//덮어쓰려는 이름이 같은것이 없을 경우에는 바로저장하도록 설정
				textSave(f,TA);
			}
		}
		else if(isSave == JFileChooser.CANCEL_OPTION) { // 취소를 클릭
			
		}
	}
	
	private void textSave(File f,JTextPane TA){
		try{
			//기본적으로 텍스트에리어에 있는 파일을 쭉읽어서 파일에 저장
			FileWriter FW = new FileWriter(f);
			BufferedWriter BW = new BufferedWriter(FW);
			String str = TA.getText();
			for(int i =0; i <str.length();i++)
			{
				if(str.charAt(i) == '\n')
				{
					BW.newLine();
				}
				else
					BW.write(str.charAt(i));
			}
			BW.close();
			FW.close();
			savefile = f;
		}catch(Exception e1){}
		
	}
	
	public File getFile() {
		return savefile;
	}
	
	public void setFile(File file) {
		savefile = file;
	}
}
