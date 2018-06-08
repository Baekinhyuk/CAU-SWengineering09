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
		
		JFileChooser fileSearch = new JFileChooser(); // 탐색기 객체 생성
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null,"txt"); // txt 필터
		fileSearch.setFileFilter(filter); // 필터 장착
		
		isOpen = fileSearch.showOpenDialog(null); // 탐색기 열기
		
		if(isOpen == JFileChooser.APPROVE_OPTION) { // 열기를 클릭
			openFile = fileSearch.getSelectedFile();
		}
		else if(isOpen == JFileChooser.CANCEL_OPTION) { // 취소를 클릭
			
		}
	}
	
	public File getFile() {
		return openFile;
	}
	
	public void setFile(File other) {
		openFile = other;
	}
}
