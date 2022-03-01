/**
 * Plays the game Minseweper (which is closely related to Minesweeper)
 * 
 * @author  The brilliant students of APCS-A at Hathaway Brown
 * @version 2022-03
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class MinseweperGame extends JPanel
{
    // public constants for the game
    public final int NUMROWS = 10;
    public final int NUMCOLS = 10;
    public final int NUMBOMBS = 10;
    
    // The grid!
    MinsewepButton grid[][] = new MinsewepButton[NUMROWS][NUMCOLS]; 

    public MinseweperGame()
    {
        super();
        setLayout(new GridLayout(NUMROWS, NUMCOLS));
        initializeGame();
    }
   
    
        public void initializeGame()
    {
        
        for(int row = 0; row < NUMROWS; row++)
        {
          for (int col = 0; col < NUMCOLS; col++)
          { 
            grid[row][col] = new MinsewepButton(row, col);  
            grid[row][col].addMouseListener(new ButtonListenerLeftRightClick());
            add(grid[row][col]);  
          }
        }
        
    }    



    
    private class ButtonListenerLeftRightClick implements MouseListener
    {
        // When the mouse is clicked on a cell:
        //   Determine whether it is a left-click or a right-click
        //   and respond appropriately
        public void mouseClicked (MouseEvent e)
        {
            // Determine which button / cell was clicked
            MinsewepButton buttonClicked = (MinsewepButton)e.getSource(); //get the particular button that was clicked
            int row = buttonClicked.getRow();
            int col = buttonClicked.getCol();
            
            if (e.getButton() == MouseEvent.BUTTON1 && buttonClicked.getIcon() == null)       // Left Button
            {
                //Color customColor = new Color(86, 166, 83);
                //buttonClicked.setBackground(customColor);
                //buttonClicked.setText("");
                //Font newFont = new Font("Arial", Font.BOLD, 25);
                //buttonClicked.setFont(newFont);
            }
            else if (e.getButton() == MouseEvent.BUTTON3)  // Right Button
            {
                if(buttonClicked.getIcon() == null)
                {
                    Icon icon = new ImageIcon("flagjpeg.jpg");
                    buttonClicked.setIcon(icon); 
                }   
                else
                {
                    buttonClicked.setIcon(null);
                }
            }
        }
        
        // Unused methods from the MouseListener interface
        public void mousePressed (MouseEvent e)  { }
        public void mouseReleased (MouseEvent e) { }
        public void mouseEntered (MouseEvent e)  { }
        public void mouseExited (MouseEvent e)   { }
    }

}
