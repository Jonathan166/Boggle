// Assignment A08
// Program Boggle / Group Project
// Author Cliff Bateman, Bryan Fritz, Brandon Poirier, David Storm
// Date Nov 10, 2015
package boggle;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

@SuppressWarnings({ "unused", "serial" })  
public class BoggleButton extends JButton implements MouseListener 
{

    private static BoggleButton buttons[][];
    private final Border clickBorder = new LineBorder(Color.RED, 5);
    private final Border unclickborder = new LineBorder(Color.BLACK, 5);
    private int state = 0;
    private boolean selectedButton = false;
    public static void init(BoggleButton[][] buttons) 
    {
        BoggleButton.buttons = buttons;
    }

    private int row;
    private int col;

    public BoggleButton(int row, int col) 
    {
        this.row = row;
        this.col = col;
        addMouseListener(this);
        this.setFont(new Font("American Typewriter", Font.BOLD, 30));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    	// TODO Auto-generated method stub	
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
    	enableButtons();
    	this.state = 3;
	
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	// TODO Auto-generated method stub
	}

    public int getRow()
    {
    	return this.row;
    }
    public int getCol()
    {
    	return this.col;
    }
    public boolean getState()
    {
    	if (this.state == 1)
    	{
    		return true;
    	}else
    	{
    		return false;
    	}
    }
    public void setState(int state)
    {
    	if (state == 1)
    	{
    		this.state = 1;
    	}else if (state == 0)
    	{
    		this.state = 0;
    	}	
    }
    public void enableButtons() 
	{
		for (int r = 0; r < buttons.length; ++r) 
    	{
    		for (int c = 0; c < buttons[0].length; ++c) 
    		{

    			if (Math.abs(buttons[r][c].row - row) <= 1 && Math.abs(buttons[r][c].col - col) <= 1
                    && !(Math.abs(buttons[r][c].row - row) == 0 && Math.abs(buttons[r][c].col - col) == 0)) 
    			{
    				if (!buttons[r][c].isSelected() && this.state != 3) 
    				{         
    					//buttons[r][c].setEnabled(true);
    					buttons[r][c].setState(1);
    				}
    				else 
    				{
    					buttons[r][c].setEnabled(false);
    					buttons[r][c].setState(0);
    				}
    			}
    		}
    	}
	}
    public void setSelectedButtonTrue()
    {
    	this.selectedButton = true;
    }
    public void setSelectedButtonFalse()
    {
    	this.selectedButton = false;
    }
    public boolean getSlectedButtonStatus()
    {
    	if (this.selectedButton == true)
    	{
    		return true;
    	}
    	else return false;
    }
    public void shuffle()
    {
    	for (int r = 0; r < 4; r++) 
    	{
    		for (int c = 0; c < 4; c++) 
    		{
                    	buttons[r][c].setText(new BoggleDice().rollCube().toUpperCase());
                        buttons[r][c].setBorder(unclickborder);
                        buttons[r][c].setFont(new Font("American Typewriter",
                                Font.BOLD, 30));
                        buttons[r][c].setEnabled(true);                    
                        buttons[r][c].setBorder(new LineBorder(Color.BLACK, 5));  
                        buttons[r][c].setState(0);
                        buttons[r][c].setSelectedButtonFalse();
            }
        }  	
    }
    public boolean isValidButton(int r, int c)
    {  	
    	if (Math.abs(this.row - buttons[r][c].row) <= 1 && Math.abs(this.col - buttons[r][c].col) <= 1
                && !(this.row - Math.abs(buttons[r][c].row) == 0 && Math.abs(this.col - buttons[r][c].col) == 0)) 
			{
				if (!this.getSlectedButtonStatus() && this.state != 3) 
				{         
					return true;
				}
				else 
				{
					return false;
				}
			}
    	else
    	{
    		return false;
    	}
    	
    }
}
