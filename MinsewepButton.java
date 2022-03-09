
/**
 * A button to use for playing Minseweper
 *
 * @author APCS-A 2022 Awesome People @ HB
 * @version 2022-03
 */

import javax.swing.*;

public class MinsewepButton extends JButton
{
    public static final boolean OPEN = true;
    public static final boolean CLOSED = false;
    
    private int value;
    private boolean status;
    private int row, col;
    
    /** Initializes the MinsewepButton with its row and column in the grid.
     *  Sets the status to closed (not yet clicked) and the value to 0.
     *  The value will be updated when the board is initialized.
     */
    public MinsewepButton(int r, int c)
    {
        super();
        value = 0;
        row = r;
        col = c;
        status = CLOSED;
    }
    
    // Accessor Methods
    public int getValue()      { return value;   }
    public int getRow()        { return row;     }
    public int getCol()        { return col;     }
    public boolean getStatus() { return status;  }
    
    // Mutator Methods
    public void setValue(int v)      { value = v;   }
    public void setRow(int r)        { row = r;     }
    public void setCol(int c)        { col = c;     }
    public void setStatus(boolean s) { status = s;  }
    
    
}
