/**
 * The container for a Minseweper game
 *
 * @author the brilliant students of Hathaway Brown APCS-A
 * @version 2022-03
 */

import javax.swing.*;

public class Minseweper
{
     
    public static void main(String[] args) 
    {
        JFrame window = new JFrame("Minseweper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new MinseweperGame());
        window.setBounds(300,200,600,600);
        window.setVisible(true);
    }
}
