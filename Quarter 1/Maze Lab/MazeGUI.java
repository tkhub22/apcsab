import javax.swing.*;

public class MazeGUI
{
   public static void main (String [] args)
   {
      JFrame frame = new JFrame ("Maze Lab");
      frame.setSize(600,550);
      frame.setLocation(200,200);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new MazePanel());
      frame.setVisible(true);
   }
}