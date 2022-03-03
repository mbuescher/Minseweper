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
    public final int NUMBOMBS = 25;
    
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
    public void setBombs(int fRow, int fCol)
    {
        int bombsNotPlaced = NUMBOMBS;
        while(bombsNotPlaced > 0)
        {
            int row = (int) (Math.random() * NUMROWS);
            int col = (int) (Math.random() * NUMCOLS);
            if(Math.abs(row - fRow) > 1 && Math.abs(col - fCol) > 1 && grid[row][col].getValue() == 0)
            {
                Font newFont = new Font("Arial", Font.BOLD, 35);
                grid[row][col].setFont(newFont);
                grid[row][col].setForeground(Color.RED);
                grid[row][col].setValue(-1);
                grid[row][col].setText("" + grid[row][col].getValue());
                bombsNotPlaced--;
            }
        }
        for(int i = 0; i < NUMROWS; i++)
        {
            for(int j = 0; j < NUMCOLS; j++)
            {
                grid[i][j].setValue(getNumber(i, j));
                grid[i][j].setText("" + grid[i][j].getValue());
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
                if(numOpen == 0)
                {
                    setBombs(row, col);
                }
                Color customColor = new Color(86, 166, 83);
                buttonClicked.setBackground(customColor);
                buttonClicked.setText("");
                Font newFont = new Font("Arial", Font.BOLD, 25);
                buttonClicked.setFont(newFont);
                if(buttonClicked.getValue() == 0)
                {
                    openZero(row, col);
                }
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
        if(grid[r][c].getValue() == -1)
        {
            return -1;
        }
        for(int i = r-1; i<=r+1; i++)
        {
            for (int j = c-1; j<=c+1; j++)
            {
                if(i >= 0 && i < NUMROWS && j >= 0 && j < NUMCOLS)
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
    
    public void openZero(int row, int col)
    {
        for(int r = row-1; r <= row+1; r++)
        {
            for(int c = col-1; c <= col+1; c++)
            {
                if(r >= 0 && c >= 0 && r < NUMROWS && c < NUMCOLS && grid[r][c].getStatus() == false)
                {
                    grid[r][c].setStatus(true);
                    grid[r][c].setForeground(new Color(14, 110, 40));
                    if(grid[r][c].getValue() == 0)
                    {
                        openZero(r, c);
                        grid[r][c].setText("");
                    }
                }
            }
        }
    }

}
