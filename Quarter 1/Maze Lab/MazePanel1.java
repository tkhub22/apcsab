import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
purpose: click on a square then it solves maze for you
change event handlers to running the exithelper method
*/
public class MazePanel1 extends JPanel
{
   private JButton[][] board;
   private JLabel label;
   private JButton reset;
   private int [] [] grid2 = 
   {
      {1,1,1,1},
      {1,0,0,1},
      {1,1,0,1},
      {0,0,0,1}
   };
   
   public MazePanel1()
   {
      setLayout(new BorderLayout());
      
      JPanel north = new JPanel(); //top
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      label = new JLabel("You have clicked... ");
      north.add(label);
      
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(10,10));
      add(center, BorderLayout.CENTER);
      board = new JButton[10][10];
      
      reset = new JButton("Reset");
      reset.addActionListener( new Handler2() );
      reset.setEnabled(true);
      add(reset, BorderLayout.SOUTH);
      
      
      
}
