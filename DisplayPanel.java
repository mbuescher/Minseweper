// Represents a display panel for the Minseweper game

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DisplayPanel extends JPanel
{
  private JTextField bombCountText;
  private int bombCount, timerCount;

  // Constructor
  public DisplayPanel(int numBombs)
  {
    super(new GridLayout(1, 4, 10, 0));
    setBorder(new EmptyBorder(0, 0, 5, 0));

    add(new JLabel("    Remaining:"));

    Font displayFont = new Font("Monospaced", Font.BOLD, 16);
    String bombCountStr = " " + numBombs;

    bombCountText = new JTextField(bombCountStr, 5);
    bombCountText.setFont(displayFont);
    bombCountText.setEditable(false);
    bombCountText.setBackground(Color.WHITE);
    add(bombCountText);
    
    add(new JLabel(" "));  // Placeholder
    add(new JLabel(" "));  // Placeholder
  }

  
  /** Updates the number of bombs remaining to be found
   *  @param   bombs  The number of bombs remaining to be found
   */
  public void updateBombs(int bombs)
  {
    bombCount = bombs;
    bombCountText.setText(" " + bombCount);
  }
}
