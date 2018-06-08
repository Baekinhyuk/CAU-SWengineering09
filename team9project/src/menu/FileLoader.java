package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader {

	private FileHandler fileSearch = new FileHandler();
	private File fileLoad;
	
	FileLoader(){
		
	}
	
	public ArrayList<String> fileRead() {
		ArrayList<String> readText = new ArrayList<String>();
		String readline = new String();
		
		fileSearch.fileOpen();
		fileLoad = fileSearch.getFile();
		try {
			FileReader filereader = new FileReader(fileLoad); // 입력 스트림
			BufferedReader bufReader = new BufferedReader(filereader);
			
			while((readline=bufReader.readLine())!= null) {
				readText.add(readline);
			}
			bufReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return readText;
	}
	
	public String getDirectory() {
		return fileLoad.getAbsolutePath();
	}
	
	public File getFile() {
		return fileLoad;
	}
	
	public void setFile(File file) {
		this.fileLoad = file;
	}
}
