/**
 * The container for a Minseweper game
 *
 * @author the brilliant students of Hathaway Brown APCS-A
 * @version 2022-03-09
 */

import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class Minseweper extends JFrame
{
    public Minseweper()
    {
        super("Minseweper");
   
        DisplayPanel display = new DisplayPanel(25);
        MinseweperGame gameBoard = new MinseweperGame(display);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel.add(display, BorderLayout.NORTH);
        panel.add(gameBoard, BorderLayout.CENTER);
    
        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);
    } 
  
    public static void main(String[] args) 
    {
        Minseweper window = new Minseweper();
        //JFrame window = new JFrame("Minseweper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.getContentPane().add(new MinseweperGame());
        window.setBounds(300,200,600,600);
        window.setVisible(true);
    }
}
