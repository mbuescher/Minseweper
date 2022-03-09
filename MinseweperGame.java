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
    public static final int NUMROWS = 10;
    public static final int NUMCOLS = 10;
    public static final int NUMBOMBS = 25;
        
    // The grid!
    private MinsewepButton grid[][] = new MinsewepButton[NUMROWS][NUMCOLS]; 
    
    // How many cells have been opened
    private int numOpen;
    
    // Bombs Remaining and where to find that info
    private int bombsLeft;
    DisplayPanel display;

    public MinseweperGame(DisplayPanel d)
    {
        super();
        bombsLeft = NUMBOMBS;
        display = d;
        setLayout(new GridLayout(NUMROWS, NUMCOLS));
        initializeGame();
    }
   
    
    /** Creates a 2-D array of MinsewepButtons and 
     *  gives them all mouseListenters
     */
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
        
        numOpen = 0;
    }    
    
    /** Places NUMBOMBS on the board, subject to these conditions:
     *  Only one bomb on a cell.
     *  No bombs adjacent to the first cell that the user clicks, identified by
     *  fRow and fCol
     *  POSTCONDITION: NUMBOMBS have been placed
     *  
     *  @param  fRow  The row number of the user's initial click
     *  @param  fCol  The column number of the user's initial click
     */
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
    
    /** Returns the number of open cells.
     *  Used to communicate with the calling Minseweper class for display
     *  @return  How many cells have been opened.
     */
    public int getNumOpen()   
    { return numOpen;  }

    /** Returns the number of unfound bombs, based on the number of flags placed.
     *  Used to communicate with the calling Minseweper class for display
     *  @return  How many bombs have not yet been flagged.
     */
    public int getNumUnfound()   
    { return NUMBOMBS;  }
    
    // --------------------------------------------------------------------
    /* Actions to take when the mouse is clicked on a button.  */
    // --------------------------------------------------------------------
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
                // If this is the first cell opened, place the bombs (in other places)
                if(numOpen == 0)
                {
                    setBombs(row, col);
                }
                // Open the cell and change the button properties
                Color customColor = new Color(86, 166, 83);
                buttonClicked.setBackground(customColor);
                grid[row][col].setStatus(MinsewepButton.OPEN);
                buttonClicked.setText("");
                Font newFont = new Font("Arial", Font.BOLD, 16);
                buttonClicked.setFont(newFont);
                
                // Increment the count of open cells
                numOpen++;
                if(buttonClicked.getValue() == 0)
                {
                    openZero(row, col);
                }
            }
            else if (e.getButton() == MouseEvent.BUTTON3)  // Right Button
            {
                // Toggle a flag image on the button
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
    
    /** Counts the number of bombs adjacent to a given cell.
     *  @param   r  The row number of the cell to check
     *  @param   c  The column number of the cell to check
     *  @return  -1 if the cell contains a bomb; otherwise, the number of adjacent bombs
     */
    public int getNumber(int r, int c)
    {
        int count = 0; 
        if(grid[r][c].getValue() == -1)
        {
            return -1;
        }
        for(int i = r - 1; i <= r + 1; i++)
        {
            for (int j = c - 1; j <= c + 1; j++)
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
    
    /** Opens all cells adjacent to a cell with zero adjacent bombs
     *  (you might have to read that twice!
     *  
     *  PRECONDITION:  The cell at [row][col] has value 0 (no adjacent bombs)
     *  POSTCONDITION: All cells connected to the zero cell are also opened.
     */
    public void openZero(int row, int col)
    {
        for(int r = row-1; r <= row+1; r++)
        {
            for(int c = col-1; c <= col+1; c++)
            {
                if(r >= 0 && c >= 0 && r < NUMROWS && c < NUMCOLS && 
                     grid[r][c].getStatus() == MinsewepButton.CLOSED)
                {
                    numOpen++;
                    grid[r][c].setStatus(MinsewepButton.OPEN);
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
