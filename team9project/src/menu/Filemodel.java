package menu;

import java.io.File;
import java.util.ArrayList;

public class Filemodel {

    private ArrayList<String> left;
    private ArrayList<String> right;
    private File leftfile = null;
    private File rightfile = null;
    private ArrayList<Integer> differ_index = null;
    
    Filemodel(){
        left = new ArrayList<String>();
        right = new ArrayList<String>();        
    };
    
    public void addLeft(String s)
    {
        left.add(s);
    }
    
    public void addright(String s)
    {
        right.add(s);
    }
    
    public ArrayList<String> getLeft()
    {
        return left;
    }
    
    public ArrayList<String> getRight()
    {
        return right;
    }
    
    public void setLeft(ArrayList<String> s)
    {
        left = s;
    }
    
    public void setRight(ArrayList<String> s)
    {
        right = s;
    }
    
    public void setLeftFile(File f)
    {
        leftfile = f;
    }
    
    public void setRightFile(File f)
    {
        rightfile = f;
    }
    
    public File getLeftFile()
    {
        return leftfile;
    }
    
    public File getRightFile()
    {
        return rightfile;
    }
    
    public ArrayList<Integer> getdiff()
    {
        return differ_index;
    }
    
    public void setdiff(ArrayList<Integer> d)
    {
        differ_index = d;
    }
}
