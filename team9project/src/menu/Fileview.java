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
        
        //TextPane���� �����ٲپ��� �׷����� �ٺ��� ���򺯰氡���ؼ�      
        JTextPane Righttextfield = new JTextPane();         
        JTextPane Lefttextfield = new JTextPane();  
        StyledDocument rightdoc, leftdoc;   
        
        //SimpleAttributeSet ���� 
        SimpleAttributeSet attribute = new SimpleAttributeSet();
        SimpleAttributeSet firstattribute = new SimpleAttributeSet();
            
        //EDIT���� ���� �Ұ��� �� boolean
        boolean LeftEditonoff = false;
        boolean RightEditonoff = false;
        
        //���ʿ� ���ϸ� �߰�
        JLabel LeftName = new JLabel("���ϸ� : ");
        JLabel RightName = new JLabel("���ϸ� : ");
        
        private EventHandler eventhandler = new EventHandler();
        
        public Fileview(){
            f.setSize(900,600);//ȭ���� ũ�⸦ ����
            f.setLayout(new BorderLayout());

            //SimpleAttributeSet���� �����ѰŰ� ������ �־��ִ°� (���ڴٸ����ϴºκ��߰��Ϸ��� �� �κ� �߰� -���������ϰԹ���)
            StyleConstants.setBackground(attribute, Color.orange);
            StyleConstants.setBold(attribute, true);
            StyleConstants.setUnderline(attribute, true);

            //����ó���� ������ �⺻ �ؽ�Ʈ�ʵ� �Ӽ�
            StyleConstants.setBackground(firstattribute, Color.white);
            StyleConstants.setBold(firstattribute, false);
            StyleConstants.setUnderline(firstattribute, false);
            
            //������ Compare & Merge �κ�
            JPanel menueastPanel = new JPanel();
            menueastPanel.setLayout(new GridLayout(4,1,4,20));
            f.add("East", menueastPanel);
            menueastPanel.add("East",Compare);
            menueastPanel.add("East",LeftMerge);
            menueastPanel.add("East",RightMerge);
            menueastPanel.add("East",EXIT);

            //�߾� ���� TextView �κ�
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
            
            //TextPane�� ���ʿ� �߰�, textfield�� �⺻ false�� ����ִ»��� 
            Lefttextfield.setEditable(LeftEditonoff);
            LeftPanel.add("Center",new JScrollPane(Lefttextfield));

            
            //�߾� ������ TextView �κ�
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
                    
            //TextPane�� �����ʿ� �߰�, textfield�� �⺻ false�� ����ִ»���       
            Righttextfield.setEditable(RightEditonoff);
            RightPanel.add("Center",new JScrollPane(Righttextfield));

            
            //���� ������ Textview ���������� �߾ӿ� �гθ��� �߰� 
            JPanel CenterPanel = new JPanel();
            CenterPanel.setLayout(new GridLayout(1,2,4,4));
            CenterPanel.add("West",LeftPanel);
            CenterPanel.add("East",RightPanel);
            f.add("Center", CenterPanel);

            
            Toolkit tk = Toolkit.getDefaultToolkit(); //������ Toolkit��ü�� ��´�.
            Dimension screenSize = tk.getScreenSize();   
           
           
            
            //ó�� ���۽� ���α׷� ��ġ �� ������ ����
            f.setLocation(screenSize.width/2 - 450, screenSize.height/2 - 300);

            //�������� �ݱ��ư ����� ���α׷�����ǵ��ϼ���
            f.addWindowListener(eventhandler);
            
            //������ Frame�� ȭ�鿡 ���̵��� �Ѵ�.
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