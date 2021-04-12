/*
 * Used to determine user input
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener
{
	public UserInput(){}
	
	@Override
	public void keyTyped(KeyEvent e){}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		// Leave game if it is running using escape key
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			Pong.stopGame();
		}
		
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_W -> Pong.getController().getPlayer1().setMovingUp(true);
			case KeyEvent.VK_S -> Pong.getController().getPlayer1().setMovingDown(true);
		}
		
		if (Pong.getController().getIsMultiplayer())
		{
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP -> Pong.getController().getPlayer2().setMovingUp(true);
				case KeyEvent.VK_DOWN -> Pong.getController().getPlayer2().setMovingDown(true);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_W -> Pong.getController().getPlayer1().setMovingUp(false);
			case KeyEvent.VK_S -> Pong.getController().getPlayer1().setMovingDown(false);
		}
		
		if (Pong.getController().getIsMultiplayer())
		{
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_UP -> Pong.getController().getPlayer2().setMovingUp(false);
				case KeyEvent.VK_DOWN -> Pong.getController().getPlayer2().setMovingDown(false);
			}
		}
	}
}
