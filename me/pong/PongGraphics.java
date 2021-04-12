/*
 * Graphics engine for the Pong game
 */

import javax.swing.*;
import java.awt.*;

public class PongGraphics extends JPanel
{
	// Constructor not needed
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// Draw dotted line in middle
		g.setColor(Color.WHITE);
		for (int i = 0; i <= getHeight(); i += 25)
		{
			g.fillRect((getWidth() / 2) - 5, i, 10, 10);
		}
		
		// Draw score
		Font font = new Font("Serif", Font.BOLD, 30);
		g.setFont(font);
		g.drawString(Integer.toString(Pong.getController().getPlayer1().getScore()),
				getWidth() / 4, 40); // player 1
		g.drawString(Integer.toString(Pong.getController().getPlayer2().getScore()),
				(getWidth() / 4) * 3, 40); // player 2
		
		Pong.getController().getPlayer1().draw(g);
		Pong.getController().getPlayer2().draw(g);
		Pong.getController().getBall().draw(g);
	}
}
