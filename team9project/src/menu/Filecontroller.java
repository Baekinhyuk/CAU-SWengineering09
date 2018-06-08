package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;



public class Filecontroller implements ActionListener {

    private Fileview view;
    private Filemodel model; 
    
    // �ؿ� �� ���� ���ְ� model�� �ִ� arraylist �����ߴµ� �������� �״�� ����
    private ArrayList<String> leftTXT;  // ���� ����
    private ArrayList<String> rightTXT; // ������ ����
    
    // compare ��ư ��Ȱ��ȭ �� ��������� �ٽ� Ȱ��ȭ ��Ű�� ���� �������� �ĸ� ���ϱ� ���� ����
    private String Before_left = "";
    private String Before_right = "";
    
    private FileLoader load;
    private FileCompare compare;
    private FileSave filesave;
    
    public Filecontroller(Fileview view, Filemodel model){
        this.view = view;
        this.model = model;
        addListener();
    }
    
    private void addListener()
    {        
        view.Compare.addActionListener(this);
        view.LeftMerge.addActionListener(this);
        view.RightMerge.addActionListener(this);
        view.EXIT.addActionListener(this);    
        view.LeftLoad.addActionListener(this);
        view.LeftEdit.addActionListener(this);
        view.LeftSave.addActionListener(this);
        view.RightLoad.addActionListener(this);
        view.RightEdit.addActionListener(this);
        view.RightSave.addActionListener(this);    
    }
    
    public void actionPerformed(ActionEvent e){
      
        
        if(e.getSource() == view.LeftMerge){ // right to left
            //Merge���� action�� ����ɰ͵� �����߰�
            String[] sychright = view.Righttextfield.getText().split("\r\n",100000);
            String[] sychleft = view.Lefttextfield.getText().split("\r\n",100000);
            ArrayList<String> left = new ArrayList<String>();
            ArrayList<String> right = new ArrayList<String>();
                
            for(int i = 0; i < sychright.length ; i++) {
                right.add(sychright[i]);
            }
            for(int i = 0; i < sychleft.length ; i++) {
                left.add(sychleft[i]);
            }
            for(int i = 0; i < model.getdiff().size(); i++) {

                left.set(model.getdiff().get(i), right.get(model.getdiff().get(i)));
            }
            view.Lefttextfield.setText("");
            String lText = new String();
            for(int i = 0; i < left.size(); i++) { // �ؽ�Ʈ�ʵ忡 ����
            	if(left.get(i).equals("")) {
            		continue;
            	}
                lText = lText + left.get(i) + "\r\n";
            }
            view.Lefttextfield.setText(lText);
            
            view.Righttextfield.setText("");
            String rText = new String();
            for(int i = 0; i < right.size(); i++) { // �ؽ�Ʈ�ʵ忡 ����
            	if(right.get(i).equals("")) {
            		continue;
            	}
                rText = rText + right.get(i) + "\r\n";
            }
            view.Righttextfield.setText(rText);
            
        }
        else if(e.getSource() == view.RightMerge){ // left to right
            //Merge���� action�� ����ɰ͵� �����߰�
           
            String[] sychright = view.Righttextfield.getText().split("\r\n",100000);
            String[] sychleft = view.Lefttextfield.getText().split("\r\n",100000);
            ArrayList<String> left = new ArrayList<String>();
            ArrayList<String> right = new ArrayList<String>();
                
            for(int i = 0; i < sychright.length ; i++) {
                right.add(sychright[i]);
            }
            for(int i = 0; i < sychleft.length ; i++) {
                left.add(sychleft[i]);
            }       
            
            
            for(int i = 0; i < model.getdiff().size(); i++) {
                    right.set(model.getdiff().get(i), left.get(model.getdiff().get(i)));  
            }
            view.Righttextfield.setText("");
            String rText = new String();
            for(int i = 0; i < right.size(); i++) { // �ؽ�Ʈ�ʵ忡 ����
            	if(right.get(i).equals("")) {
            		continue;
            	}
                rText = rText + right.get(i) + "\r\n";
            }
            view.Righttextfield.setText(rText);
            
            view.Lefttextfield.setText("");
            String lText = new String();
            for(int i = 0; i < left.size(); i++) { // �ؽ�Ʈ�ʵ忡 ����
            	if(left.get(i).equals("")) {
            		continue;
            	}
                lText = lText + left.get(i) + "\r\n";
            }
            view.Lefttextfield.setText(lText);
        }
        else if(e.getSource() == view.Compare){
        	// ����� ��ư�� ��Ȱ��ȭ��ų�� �����Ѵ�.
        	if(Before_left.equals(view.Lefttextfield.getText()) && Before_right.equals(view.Righttextfield.getText()))
        		;
        	else {
        		//���� �ٽ� ���Ҽ��� ������ �� ó�� �⺻�������� �ʱ�ȭ
            	view.Righttextfield.getStyledDocument().setParagraphAttributes(0, (view.Righttextfield.getX()*view.Righttextfield.getY()), view.firstattribute, false);
            	view.Lefttextfield.getStyledDocument().setParagraphAttributes(0, (view.Lefttextfield.getX()*view.Lefttextfield.getY()), view.firstattribute, false);
                //Compare���� action�� ����ɰ͵� �����߰�
                
                compare = new FileCompare();
                int max = 10000000;
                
                // table ����� ���� textfield���� ���ڿ� ��������
                String str_tmp = view.Lefttextfield.getText();
                
                str_tmp = "0" + "\r\n" + str_tmp;   // ���̺� Ư¡�� ���ڿ� �տ� 0 ó��
                String[] left = str_tmp.split("\r\n", max);
                
                
                str_tmp = view.Righttextfield.getText();
                str_tmp = "0" + "\r\n" + str_tmp;   // ���̺� Ư¡�� ���ڿ� �տ� 0 ó��
                String[] right = str_tmp.split("\r\n", max);
                
                // table �����
                int[][] table = compare.makeLCSTable(left, right);
                int lcsLength = compare.getLcsLength(); // LCS_Length ���� ����
                
                // convert to array -> List
                // initialize
                leftTXT = new ArrayList<String>();  
                for(int i = 1 ; i < left.length ; i++) // copy array to List
                    leftTXT.add(left[i]);
                
                // initialize
                rightTXT = new ArrayList<String>();
                for(int i = 1 ; i < right.length ; i++) // copy array to List
                    rightTXT.add(right[i]);
                
                ArrayList<String> lcs = compare.makeLCSString(left.length, right.length, lcsLength, table, left, right);  // lcs ���ڿ��� ���ϴ� �Լ��� �� �ʿ��ϴ�.
                compare.synchronizingTextContent(leftTXT, rightTXT, lcs);
                
                String lText = new String();
                view.Lefttextfield.setText(""); // �ؽ�Ʈ�ʵ� �ʱ�ȭ �� ���
                for(int i = 0; i < leftTXT.size(); i++) { // �ؽ�Ʈ�ʵ忡 ���
                    if(i == leftTXT.size() - 1)
                        lText = lText + leftTXT.get(i);
                    else
                        lText = lText + leftTXT.get(i) + "\r\n";
                }
                view.Lefttextfield.setText(lText);
                
                String rText = new String();
                view.Righttextfield.setText(""); // �ؽ�Ʈ�ʵ� �ʱ�ȭ �� ���
                for(int i = 0; i < rightTXT.size(); i++) { // �ؽ�Ʈ�ʵ忡 ���
                    if(i == rightTXT.size() - 1)
                        rText = rText + rightTXT.get(i);
                    else
                        rText = rText + rightTXT.get(i) + "\r\n";
                }
                view.Righttextfield.setText(rText);
                
                // �̺κ��� ���� �ٸ� �κ��� �ε����� ����ش�
                model.setdiff(compare.getDifferentLineNumberIndex(leftTXT, rightTXT));
                
                //�ٸ��κ� ��������
                int cont = 0;
                int diff = 0;
                for(int j = 0; j < leftTXT.size(); j++) {
                	if(diff < model.getdiff().size()){
                		if(model.getdiff().get(diff) == j){
                			view.Lefttextfield.getStyledDocument().setCharacterAttributes(cont, leftTXT.get(j).length(), view.attribute, false);
                			diff++;
                		}	
                	}
                    cont += leftTXT.get(j).length() + 1;
                }
                cont = 0; diff = 0;
                for(int j = 0; j < rightTXT.size(); j++) {
                	if(diff < model.getdiff().size()){
                		if(model.getdiff().get(diff) == j){
                			view.Righttextfield.getStyledDocument().setCharacterAttributes(cont, rightTXT.get(j).length(), view.attribute, false);
                			diff++;
                		}
                	}
                 	cont += rightTXT.get(j).length() + 1;
                }
            // ������ ���ڿ��� �ֽ�ȭ�� ����� ��ư�� ��Ȱ��ȭ���� �˻��ϰ� �Ѵ�
            Before_left = view.Lefttextfield.getText();
            Before_right = view.Righttextfield.getText();
        	}
            
        }
        else if(e.getSource() == view.LeftLoad){
            //Load���� action�� ����ɰ͵� �����߰�
            load = new FileLoader(); // Ž����
            leftTXT = load.fileRead(); // ���� ��������
            model.setLeftFile(load.getFile());
            String lText = new String();
            view.Lefttextfield.setText(""); // �ؽ�Ʈ�ʵ� �ʱ�ȭ �� ���
            for(int i = 0; i < leftTXT.size(); i++) { // �ؽ�Ʈ�ʵ忡 ���
                lText = lText + leftTXT.get(i);
                model.addLeft(lText);     
                lText = lText + "\r\n";
            }
            view.Lefttextfield.setText(lText);            
            view.LeftName.setText("���ϸ� : "+model.getLeftFile().getName());
        }
        else if(e.getSource() == view.LeftEdit){
            //Edit���� action�� ����ɰ͵� �����߰�
            if(view.LeftEditonoff == false){
                view.LeftEdit.setText("EDIT ON");
                view.LeftEdit.setFont(new Font("���",Font.ITALIC,12));
                view.LeftEditonoff = true;
                
            }
            else if(view.LeftEditonoff = true){
                view.LeftEdit.setText("Edit");
                view.LeftEdit.setFont(new Font("Dialog",Font.BOLD,12));
                view.LeftEditonoff = false;
                String[] sychleft = view.Lefttextfield.getText().split("\r\n",100000);
                ArrayList<String> left = new ArrayList<String>();
                for(int i = 0; i < sychleft.length ; i++) {
                    left.add(sychleft[i]);
                }
                model.setLeft(left);
            }
            view.Lefttextfield.setEditable(view.LeftEditonoff);
        }
        else if(e.getSource() == view.LeftSave){
            filesave = new FileSave(model.getLeftFile(),view.Lefttextfield);
            model.setLeftFile(filesave.getFile());
            //Save���� action�� ����ɰ͵� �����߰�
        }
        else if(e.getSource() == view.RightLoad){
            //Load���� action�� ����ɰ͵� �����߰�
            load = new FileLoader(); // Ž����
            rightTXT = load.fileRead(); // ���� ��������
            model.setRightFile(load.getFile());
            String rText = new String();
            view.Righttextfield.setText("");
            for(int i = 0; i < rightTXT.size(); i++) { // �ؽ�Ʈ�ʵ忡 ����
                rText = rText + rightTXT.get(i);
                model.addright(rText);
                rText = rText + "\r\n";
            }
            view.Righttextfield.setText(rText);
            view.RightName.setText("���ϸ� : "+model.getRightFile().getName());
        }
        else if(e.getSource() == view.RightEdit){
            //Edit���� action�� ����ɰ͵� �����߰�
            if(view.RightEditonoff == false){
                view.RightEdit.setText("EDIT ON");
                view.RightEdit.setFont(new Font("���",Font.ITALIC,12));
                view.RightEditonoff = true;
            }
            else if(view.RightEditonoff = true){
                view.RightEdit.setText("Edit");
                view.RightEdit.setFont(new Font("Dialog",Font.BOLD,12));
                view.RightEditonoff = false;
                String[] sychright = view.Righttextfield.getText().split("\r\n",100000);
                ArrayList<String> right = new ArrayList<String>();
                for(int i = 0; i < sychright.length ; i++) {
                    right.add(sychright[i]);
                }                
                model.setRight(right);
            }
            view.Righttextfield.setEditable(view.RightEditonoff);
        }
        else if(e.getSource() == view.RightSave){
            filesave = new FileSave(model.getRightFile(),view.Righttextfield);
            model.setRightFile(filesave.getFile());
            //Save���� action�� ����ɰ͵� �����߰�
        }
        else if(e.getSource() == view.EXIT){
            view.f.setVisible(false);
            view.f.dispose();
            System.exit(0);
        }
    }
}


