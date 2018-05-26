package menu;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class Menu {
	//Menu class콜했을시 기본 틀과 패널과 버튼들
	JFrame f = new JFrame("Comparing Text");
	JButton Compare = new JButton("Compare");
	JButton LeftMerge = new JButton("LeftMerge");
	JButton RightMerge = new JButton("RightMerge");
	JButton EXIT = new JButton("EXIT");
	
	JButton LeftLoad = new JButton("Load");
	JButton LeftEdit = new JButton("Edit");
	JButton LeftSave = new JButton("Save");
	JPanel LeftPanel = new JPanel();
	
	JButton RightLoad = new JButton("Load");
	JButton RightEdit = new JButton("Edit");
	JButton RightSave = new JButton("Save");
	JPanel RightPanel = new JPanel();
	
	//TextArea를 50행, 50열로 설정합니다.      
	JTextArea Righttextfield = new JTextArea("", 50, 50); 		
	JTextArea Lefttextfield = new JTextArea("", 50, 50); 
	
	//EDIT수정 가능 불가능 용 boolean
	private boolean LeftEditonoff = false;
	private boolean RightEditonoff = false;

	//Dialog창으로 저장하기
	FileDialog  SaveText = new FileDialog(f,"저장",FileDialog.SAVE);
	
	public Menu(){
		f.setSize(900,600);//화면의 크기를 구함
		f.setLayout(new BorderLayout());
		
		//오른쪽 Compare & Merge 부분
		JPanel menueastPanel = new JPanel();
		menueastPanel.setLayout(new GridLayout(4,1,4,20));
		f.add("East", menueastPanel);
		/*버튼색깔설정할려면 여기서 색깔조정
		Compare.setBackground(Color.pink);
		Merge.setBackground(Color.pink);
		EXIT.setBackground(Color.pink);
		*/
		menueastPanel.add("East",Compare);
		menueastPanel.add("East",LeftMerge);
		menueastPanel.add("East",RightMerge);
		menueastPanel.add("East",EXIT);

		//중앙 왼쪽 TextView 부분
		LeftPanel.setLayout(new BorderLayout());
		JPanel LeftNorthPanel = new JPanel();
		LeftNorthPanel.setLayout(new GridLayout(1,3,4,4));
		
		LeftPanel.add("North", LeftNorthPanel);
		/*버튼색깔설정할려면 여기서 색깔조정
		LeftLoad.setBackground(Color.pink);
		LeftEdit.setBackground(Color.pink);
		LeftSave.setBackground(Color.pink);
		*/
		LeftNorthPanel.add("North",LeftLoad);
		LeftNorthPanel.add("North",LeftEdit);
		LeftNorthPanel.add("North",LeftSave);
		
		//TextArea를 왼쪽에 추가, textfield는 기본 false로 잠겨있는상태    
		Lefttextfield.setEditable(LeftEditonoff);
		LeftPanel.add("Center",new JScrollPane(Lefttextfield));

		
		//중앙 오른쪽 TextView 부분
		RightPanel.setLayout(new BorderLayout());
		JPanel RightNorthPanel = new JPanel();
		RightNorthPanel.setLayout(new GridLayout(1,3,4,4));
		RightPanel.add("North", RightNorthPanel);
		/*버튼색깔설정할려면 여기서 색깔조정
		RightLoad.setBackground(Color.pink);
		RightEdit.setBackground(Color.pink);
		RightSave.setBackground(Color.pink);
		*/
		RightNorthPanel.add("North",RightLoad);
		RightNorthPanel.add("North",RightEdit);
		RightNorthPanel.add("North",RightSave);
		
		//TextArea를 오른쪽에 추가, textfield는 기본 false로 잠겨있는상태      
		Righttextfield.setEditable(RightEditonoff);
		RightPanel.add("Center",new JScrollPane(Righttextfield));

		
		//왼쪽 오른쪽 Textview 나누어진거 중앙에 패널만들어서 추가 
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(new GridLayout(1,2,4,4));
		CenterPanel.add("West",LeftPanel);
		CenterPanel.add("East",RightPanel);
		f.add("Center", CenterPanel);

		
		Toolkit tk = Toolkit.getDefaultToolkit(); //구현된 Toolkit객체를 얻는다.
		Dimension screenSize = tk.getScreenSize();
		
		//버튼을 눌렀을시 아래에 class에 정의한내용대로 실행하도록 내용추가
		ButtonListener listener = new ButtonListener();
		Compare.addActionListener(listener);
		LeftMerge.addActionListener(listener);
		RightMerge.addActionListener(listener);
		EXIT.addActionListener(listener);
		
		LeftLoad.addActionListener(listener);
		LeftEdit.addActionListener(listener);
		LeftSave.addActionListener(listener);

		RightLoad.addActionListener(listener);
		RightEdit.addActionListener(listener);
		RightSave.addActionListener(listener);
		
		//처음 시작시 프로그램 위치 및 사이즈 설정
		f.setLocation(screenSize.width/2 - 450, screenSize.height/2 - 300);

		//오른쪽위 닫기버튼 실행시 프로그램종료되도록설정
		f.addWindowListener(new EventHandler());
		
		//생성한 Frame을 화면에 보이도록 한다.
		f.setVisible(true);
	}
	public class ButtonListener implements ActionListener{
		
		ArrayList<String> leftTXT;  // 왼쪽 파일
		ArrayList<String> rightTXT; // 오른쪽 파일
		File leftfile = null;//왼쪽 파일 처음에 비어있도록설정 이쪽 패널에 로드시 파일에관한 내용이들어가도록해서 Save에 영향을줌
		File rightfile = null;	//오른쪽 파일 처음에 비어있도록설정 이쪽 패널에 로드시 파일에관한 내용이들어가도록해서 Save에 영향을줌
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == LeftMerge){
				//Merge관련 action시 실행될것들 내용추가
			}
			else if(e.getSource() == RightMerge){
				//Merge관련 action시 실행될것들 내용추가
			}
			else if(e.getSource() == Compare){
				//Compare관련 action시 실행될것들 내용추가
			}
			else if(e.getSource() == LeftLoad){
				//Load관련 action시 실행될것들 내용추가
				FileLoader load = new FileLoader(); // 탐색기
				leftTXT = load.fileRead(); // 파일 가져오기
				leftfile = load.fileLoad;
				Lefttextfield.setText(""); // 텍스트필드 초기화 후 출력
				for(int i = 0; i < leftTXT.size(); i++) { // 텍스트필드에 출력
					Lefttextfield.append(leftTXT.get(i)+"\n");
				}
			}
			else if(e.getSource() == LeftEdit){
				//Edit관련 action시 실행될것들 내용추가
				if(LeftEditonoff == false){
					LeftEdit.setText("EDIT ON");
					LeftEdit.setFont(new Font("고딕",Font.ITALIC,12));
					LeftEditonoff = true;
				}
				else if(LeftEditonoff = true){
					LeftEdit.setText("Edit");
					LeftEdit.setFont(new Font("Dialog",Font.BOLD,12));
					LeftEditonoff = false;
				}
				Lefttextfield.setEditable(LeftEditonoff);
			}
			else if(e.getSource() == LeftSave){
				new FileSave(leftfile,Lefttextfield);
				//Save관련 action시 실행될것들 내용추가
			}
			else if(e.getSource() == RightLoad){
				//Load관련 action시 실행될것들 내용추가
				FileLoader load = new FileLoader(); // 탐색기
				rightTXT = load.fileRead(); // 파일 가져오기
				rightfile = load.fileLoad;
				Righttextfield.setText("");
				for(int i = 0; i < rightTXT.size(); i++) { // 텍스트필드에 저장
					Righttextfield.append(rightTXT.get(i)+"\n");
				}
			}
			else if(e.getSource() == RightEdit){
				//Edit관련 action시 실행될것들 내용추가
				if(RightEditonoff == false){
					RightEdit.setText("EDIT ON");
					RightEdit.setFont(new Font("고딕",Font.ITALIC,12));
					RightEditonoff = true;
				}
				else if(RightEditonoff = true){
					RightEdit.setText("Edit");
					RightEdit.setFont(new Font("Dialog",Font.BOLD,12));
					RightEditonoff = false;
				}
				Righttextfield.setEditable(RightEditonoff);
			}
			else if(e.getSource() == RightSave){
				new FileSave(rightfile,Righttextfield);
				//Save관련 action시 실행될것들 내용추가
			}
			else if(e.getSource() == EXIT){
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		}
	}
	
}



class EventHandler implements WindowListener
{
	public void windowOpened(WindowEvent e){}
	public void windowClosing(WindowEvent e){
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
}
