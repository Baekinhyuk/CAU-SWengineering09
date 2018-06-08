package menu;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler {

	private int isOpen;
	private File openFile;
	
	public FileHandler(){
	
	}
	
	public void fileOpen() {
		
		JFileChooser fileSearch = new JFileChooser(); // Ž���� ��ü ����
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"txt"); // txt ����
		fileSearch.setFileFilter(filter); // ���� ����
		
		isOpen = fileSearch.showOpenDialog(null); // Ž���� ����
		
		if(isOpen == JFileChooser.APPROVE_OPTION) { // ���⸦ Ŭ��
			openFile = fileSearch.getSelectedFile();
		}
		else if(isOpen == JFileChooser.CANCEL_OPTION) { // ��Ҹ� Ŭ��
			
		}
	}
	
	public File getFile() {
		return openFile;
	}
	
	public void setFile(File other) {
		openFile = other;
	}
}
