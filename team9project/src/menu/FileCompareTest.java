package menu;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileCompareTest {

    private FileCompare fc; 
    private String[] left;
    private String[] right;
    
    @Before
    public void setUp() throws Exception {
        fc = new FileCompare();
                
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void comparetest() {
        left = new String[]{"0",
                       "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                       "X                                               X",
                       "X XXXXX X  X XXXX    XXXX X   X XXXX    XX      X",
                       "X   X   X  X X       X    XX  X X   X   XX      X",
                       "X   X   XXXX XXXX    XXXX X X X X   X   XX      X",
                       "X   X   X  X X       X    X  XX X   X           X",
                       "X   X   X  X XXXX    XXXX X   X XXXX    XX      X",
                       "X                                               X",
                       "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"};
        right = new String[]{"0",
                        "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                        "X                                               X",
                        "X XXXXX X  X XXXX    XXXX X   X XXXX            X",
                        "X   X   X  X X       X    XX  X X   X           X",
                        "X   X   XXXX XXXX    XXXX X X X X   X           X",
                        "X   X   X  X X       X    X  XX X   X           X",
                        "X   X   X  X XXXX    XXXX X   X XXXX            X",
                        "X                                               X",
                        "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"};
                
        assertArrayEquals(fc.makeLCSTable(left, right), new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                                                     {0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 
                                                     {0, 1, 2, 2, 2, 2, 2, 2, 2, 2}, 
                                                     {0, 1, 2, 2, 2, 2, 2, 2, 2, 2}, 
                                                     {0, 1, 2, 2, 2, 2, 2, 2, 2, 2}, 
                                                     {0, 1, 2, 2, 2, 2, 2, 2, 2, 2}, 
                                                     {0, 1, 2, 2, 2, 2, 3, 3, 3, 3}, 
                                                     {0, 1, 2, 2, 2, 2, 3, 3, 3, 3}, 
                                                     {0, 1, 2, 2, 2, 2, 3, 3, 4, 4}, 
                                                     {0, 1, 2, 2, 2, 2, 3, 3, 4, 5}});
        
    }
    @Test
    public void diffLinenumtest() {
       ArrayList<String> left = new ArrayList<String>();
       ArrayList<String> right = new ArrayList<String>();
       ArrayList<Integer> same = new ArrayList<Integer>();
       
        left.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        left.add("X                                               X");
        left.add("X XXXXX X  X XXXX    XXXX X   X XXXX    XX      X");
        left.add("X   X   X  X X       X    XX  X X   X   XX      X");
        left.add("X   X   XXXX XXXX    XXXX X X X X   X   XX      X");
        left.add("X   X   X  X X       X    X  XX X   X           X");
        left.add("X   X   X  X XXXX    XXXX X   X XXXX    XX      X");
        left.add("X                                               X");
        left.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        
        right.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        right.add("X                                               X");
        right.add("X XXXXX X  X XXXX    XXXX X   X XXXX            X");
      right.add("X   X   X  X X       X    XX  X X   X           X");
      right.add("X   X   XXXX XXXX    XXXX X X X X   X           X");
      right.add("X   X   X  X X       X    X  XX X   X           X");
      right.add("X   X   X  X XXXX    XXXX X   X XXXX            X");
      right.add("X                                               X");
      right.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
              
      same.add(2);
      same.add(3);
      same.add(4);
      same.add(6);
        assertEquals(fc.getDifferentLineNumberIndex(left, right), same);
        
    }
    
}
    
  

