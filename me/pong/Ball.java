/*
 * Object to be bounced back and forth
 */

import java.awt.*;

public class Ball
{
	// Fields
	private final int HEIGHT = 20, WIDTH = 20;
	private int xPos, yPos;
	private double slope;
	private final int VELOCITY = 10;
	private int direction; // -1 is left, 1 is right
	
	public Ball(int x, int y)
	{
		xPos = x;
		yPos = y;
		direction = -1;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillOval(xPos, yPos, WIDTH, HEIGHT);
	}
	
	// Accessor methods
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public double getSlope()
	{
		return slope;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	// Set methods
	public void setSlope(double m)
	{
		slope = m;
	}
	
	public void changeDirection()
	{
		direction *= -1;
	}
	
	public void setXPos(int x)
	{
		xPos = x;
	}
	
	public void setYPos(int y)
	{
		yPos = y;
	}
	
	// move methods follow straight lines with slope
	public void moveRight()
	{
		int dy = 0;
		int dx = 0;
		for (int i = 1; Math.sqrt((i * i) + (dy * dy)) < VELOCITY; i ++)
		{
			dx = i;
			dy = (int) (slope * dx);
		}
		
		xPos += dx;
		yPos += dy;
	}
	
	public void moveLeft()
	{
		int dy = 0;
		int dx = 0;
		for (int i = 1; Math.sqrt((i * i) + (dy * dy)) < VELOCITY; i ++)
		{
			dx = i;
			dy = (int) (slope * dx);
		}
		
		xPos -= dx;
		yPos += dy;
	}
}
