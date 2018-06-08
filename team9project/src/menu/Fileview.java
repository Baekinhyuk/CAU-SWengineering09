package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;



public class Fileview {    

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
        
        //TextPane으로 설정바꾸었음 그래야지 줄별로 색깔변경가능해서      
        JTextPane Righttextfield = new JTextPane();         
        JTextPane Lefttextfield = new JTextPane();  
        StyledDocument rightdoc, leftdoc;   
        
        //SimpleAttributeSet 생성 
        SimpleAttributeSet attribute = new SimpleAttributeSet();
        SimpleAttributeSet firstattribute = new SimpleAttributeSet();
            
        //EDIT수정 가능 불가능 용 boolean
        boolean LeftEditonoff = false;
        boolean RightEditonoff = false;
        
        //위쪽에 파일명 추가
        JLabel LeftName = new JLabel("파일명 : ");
        JLabel RightName = new JLabel("파일명 : ");
        
        private EventHandler eventhandler = new EventHandler();
        
        public Fileview(){
            f.setSize(900,600);//화면의 크기를 구함
            f.setLayout(new BorderLayout());

            //SimpleAttributeSet에서 생성한거고 성질은 넣어주는거 (글자다르게하는부분추가하려고 이 부분 추가 -빨간색진하게밑줄)
            StyleConstants.setBackground(attribute, Color.orange);
            StyleConstants.setBold(attribute, true);
            StyleConstants.setUnderline(attribute, true);

            //제일처음에 나오는 기본 텍스트필드 속성
            StyleConstants.setBackground(firstattribute, Color.white);
            StyleConstants.setBold(firstattribute, false);
            StyleConstants.setUnderline(firstattribute, false);
            
            //오른쪽 Compare & Merge 부분
            JPanel menueastPanel = new JPanel();
            menueastPanel.setLayout(new GridLayout(4,1,4,20));
            f.add("East", menueastPanel);
            menueastPanel.add("East",Compare);
            menueastPanel.add("East",LeftMerge);
            menueastPanel.add("East",RightMerge);
            menueastPanel.add("East",EXIT);

            //중앙 왼쪽 TextView 부분
            LeftPanel.setLayout(new BorderLayout());
            JPanel LeftNorthPanel = new JPanel();
            JPanel LeftNorthPanel2 = new JPanel();
            LeftNorthPanel.setLayout(new BorderLayout(4,1));
            LeftNorthPanel2.setLayout(new GridLayout(1,3,4,4));
            
            LeftNorthPanel.add("South", LeftName);
            LeftNorthPanel2.add(LeftLoad);
            LeftNorthPanel2.add(LeftEdit);
            LeftNorthPanel2.add(LeftSave);
            LeftNorthPanel.add("North", LeftNorthPanel2);
            LeftPanel.add("North", LeftNorthPanel);
            
            //TextPane를 왼쪽에 추가, textfield는 기본 false로 잠겨있는상태 
            Lefttextfield.setEditable(LeftEditonoff);
            LeftPanel.add("Center",new JScrollPane(Lefttextfield));

            
            //중앙 오른쪽 TextView 부분
            RightPanel.setLayout(new BorderLayout());
            JPanel RightNorthPanel = new JPanel();
            JPanel RightNorthPanel2 = new JPanel();
            RightNorthPanel.setLayout(new BorderLayout(4,1));
            RightNorthPanel2.setLayout(new GridLayout(1,3,4,4));
                    
            RightNorthPanel.add("South", RightName);
            RightNorthPanel2.add("North",RightLoad);
            RightNorthPanel2.add("North",RightEdit);
            RightNorthPanel2.add("North",RightSave);
            RightNorthPanel.add("North", RightNorthPanel2);
            RightPanel.add("North", RightNorthPanel);
                    
            //TextPane를 오른쪽에 추가, textfield는 기본 false로 잠겨있는상태       
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
           
           
            
            //처음 시작시 프로그램 위치 및 사이즈 설정
            f.setLocation(screenSize.width/2 - 450, screenSize.height/2 - 300);

            //오른쪽위 닫기버튼 실행시 프로그램종료되도록설정
            f.addWindowListener(eventhandler);
            
            //생성한 Frame을 화면에 보이도록 한다.
            f.setVisible(true);
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