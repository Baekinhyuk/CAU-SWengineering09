package menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSave {
	private int isSave;
	
	FileSave(){}
	FileSave(File f,JTextArea TA){
		fileSave(f,TA);
	}
	
	public void fileSave(File f,JTextArea TA) {
		
		JFileChooser fileSearch;
		if(f == null){
			//������ ����ִ°�� ó����ο� ���������ϵ��ϼ���
			fileSearch = new JFileChooser();
		}
		else{
			//���� ������ �ִ°�� �� ���Ͽ� ��ġ���� �����ؼ����������ϵ��ϼ���
			fileSearch = new JFileChooser(f.getAbsolutePath());
		}
		// Ž���� ��ü ����
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"txt"); // txt ����
		fileSearch.setFileFilter(filter); // ���� ����
		fileSearch.setDialogTitle("���������ϱ�");
		
		if(f != null){
			//�ҷ��������� ������쿡�� �⺻���� �� ������ �����ؼ� ����� �⺻������ �� ���Ͽ� �̸����� �����ϵ��ϼ���
			fileSearch.setSelectedFile(f);
		}
		
		isSave = fileSearch.showSaveDialog(null); // Ž���� ����
		
		
		if(isSave == JFileChooser.APPROVE_OPTION) { 
			// ������ Ŭ��
			f = fileSearch.getSelectedFile();
			if(f.exists()){//�����Ϸ��� ������ �����Ұ��(�����̸�) ������ ������� ��¿�� ������� �����ϵ��ϼ���
				int existF = JOptionPane.showConfirmDialog(null, "�̹� ������ �����մϴ�. ������?");
				if(existF == JOptionPane.YES_OPTION){
					//YES��ư�� ������ �ÿ��� �����ϵ��� ����
					textSave(f,TA);	
				}
			}
			else
			{//������� �̸��� �������� ���� ��쿡�� �ٷ������ϵ��� ����
				textSave(f,TA);
			}
		}
		else if(isSave == JFileChooser.CANCEL_OPTION) { // ��Ҹ� Ŭ��
			
		}
	}
	
	private void textSave(File f,JTextArea TA){
		try{
			//�⺻������ �ؽ�Ʈ����� �ִ� ������ ���о ���Ͽ� ����
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
		}catch(Exception e1){}
		
	}
}
