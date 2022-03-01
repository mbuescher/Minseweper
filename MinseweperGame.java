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
        int bombsNotPlaced = NUMBOMBS;
        while(bombsNotPlaced > 0)
        {
            int row = (int) (Math.random() * NUMROWS);
            int col = (int) (Math.random() * NUMCOLS);
            if(grid[row][col].getValue() == 0)
            {
                grid[row][col].setValue(-1);
                grid[row][col].setText("" + grid[row][col].getValue());
                bombsNotPlaced--;
            }
        }
        for(int i = 0; i < NUMROWS; i++)
        {
            for(int j = 0; j < NUMCOLS; j++)
            {
                grid[row][col].setValue(getNumber(row, col));
                grid[row][col].setText("" + grid[row][col].getValue());
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
                Color customColor = new Color(86, 166, 83);
                buttonClicked.setBackground(customColor);
                buttonClicked.setText("");
                Font newFont = new Font("Arial", Font.BOLD, 25);
                buttonClicked.setFont(newFont);
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
    
    
    public int getNumber(int r, int c)
    {
        int count = 0; 

        if (r>0 && c>0 && r<NUMROWS-1 && c<NUMCOLS-1)//if it is in the middle of the grid
        {
            for(int i = r-1; i<=r+1; i++)
            {
                for (int j = c-1; j<=c+1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
        }
        
        else if(r==0 && c==0)//check the top left corner
        {
            for (int i = 0; i<=r+1; i++)
            {
                for (int j = 0; j<=c+1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
        }
        else if(r==NUMROWS-1 && c==0)//check the bottom left corner
        {
            for (int i = NUMROWS-2; i<=NUMROWS-1; i++)
            {
                for (int j = 0; j<=c+1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
        }
        else if(r==0 && c==NUMCOLS-1)//check the top right corner
        {
            for (int i = 0; i<=r+1; i++)
            {
                for (int j = NUMCOLS-2; j<=NUMCOLS-1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
        }
        else if(r==NUMROWS-1 && c==NUMCOLS-1)//check the top left corner
        {
            for (int i = NUMROWS-2; i<=NUMROWS-1; i++)
            {
                for (int j = NUMCOLS-2; j<=NUMCOLS-1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
        }
        else if(r==0)//top edge
        {
            for (int i = 0; i<=1; i++)
            {
                for (int j = c-1; j<= c+1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
        }
        else if(r==NUMROWS-1)//bottom edge
        {
            for (int i = NUMROWS-2; i<=NUMROWS-1; i++)
            {
                for (int j = c-1; j<= c+1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
        }
        else if (c==0)//left side edge
        {
            for (int i = r-1; i<=r+1; i++)
            {
                for (int j =0; j<=1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
        }
        else if (c== NUMCOLS-1)
        {
            for (int i = r-1; i<=r+1; i++)
            {
                for (int j = NUMCOLS-2; j<=NUMCOLS-1; j++)
                {
                    if (grid[i][j].getValue()==-1)
                    {
                        count++;
                    }
                }
            }
            
        }
        
        return count;
    }

}
