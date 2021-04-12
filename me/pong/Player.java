/*
 * Player class is really just the two bars that move
 */

import java.awt.*;

public class Player
{
	// Fields
	private final int HEIGHT = 50, WIDTH = 10;
	private int xPos, yPos;
	private boolean movingUp, movingDown;
	private int velocity;
	private int score;
	
	public Player(int x, int y, int v)
	{
		xPos = x;
		yPos = y;
		velocity = v;
		score = 0;
	}
	
	// Set methods
	public void setMovingUp(boolean bool)
	{
		movingUp = bool;
	}
	
	public void setMovingDown(boolean bool)
	{
		movingDown = bool;
	}
	
	public void incrementScore()
	{
		score ++;
	}
	
	public void resetScore()
	{
		score = 0;
	}
	
	public void setVelocity(int v)
	{
		velocity = v;
	}
	
	// Accessor methods
	public boolean getMovingUp()
	{
		return movingUp;
	}
	
	public boolean getMovingDown()
	{
		return movingDown;
	}
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public int getScore()
	{
		return score;
	}
	
	// Draws the player
	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(xPos, yPos, WIDTH, HEIGHT);
	}
	
	// Move methods
	public void moveUp()
	{
		yPos -= velocity;
	}
	
	public void moveDown()
	{
		yPos += velocity;
	}
}
