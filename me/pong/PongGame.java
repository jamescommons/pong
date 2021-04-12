/*
 * Game engine for the Pong game
 */

public class PongGame
{
	private Player player1, player2;
	private Ball ball;
	private static boolean isMultiplayer;
	private int wonLast; // -1 is player 1, 1 is player 2
	private boolean threeSecondRest; // Ball rest
	private double timer; // Ball rest timer
	
	public void tick() throws InterruptedException
	{
		// Process user input
		if (player1.getMovingUp())
			if (player1.getYPos() >= 0)
				player1.moveUp();
			
		if (player1.getMovingDown())
			if (player1.getYPos() <= Pong.getPanel().getHeight() - 50)
				player1.moveDown();
			
		if (isMultiplayer)
		{
			if (player2.getMovingUp())
				if (player2.getYPos() >= 0)
					player2.moveUp();
			
			if (player2.getMovingDown())
				if (player2.getYPos() <= Pong.getPanel().getHeight() - 50)
					player2.moveDown();
		}
		else // for single player (AI)
		{
			// Slow player 2 down
			player2.setVelocity(7);
			
			// Figure out where player 2 should be
			int whereToBe;
			if (ball.getDirection() == 1)
				whereToBe = ball.getYPos() - 10;
			else
				whereToBe = Pong.getPanel().getHeight() / 2;
			
			if (whereToBe >= Pong.getPanel().getHeight())
				whereToBe = Pong.getPanel().getHeight() / 2;
			
			if (player2.getYPos() >= whereToBe + 20)
			{
				player2.setMovingUp(true);
				player2.setMovingDown(false);
			}
			else if (player2.getYPos() <= whereToBe - 20)
			{
				player2.setMovingDown(true);
				player2.setMovingUp(false);
			}
			else
			{
				player2.setMovingUp(false);
				player2.setMovingDown(false);
			}
			
			if (player2.getMovingUp())
				if (player2.getYPos() >= 0)
					player2.moveUp();
			
			if (player2.getMovingDown())
				if (player2.getYPos() <= Pong.getPanel().getHeight() - 50)
					player2.moveDown();
		}
		
		// Move ball
		// Only ticks when not resting i.e not in between rounds
		if (!threeSecondRest)
		{
			if (ball.getDirection() == 1)
				ball.moveRight();
			else
				ball.moveLeft();
		}
		else if ((timer >= Pong.getAmountOfTicks() * 3))
		{
			timer = 0;
			threeSecondRest = false;
		}
		else
			timer ++;
		
		// Collision detection for player 1
		// If ball is within x cords range of player1
		if (ball.getXPos() <= 30 && ball.getXPos() >= 20)
		{
			// If ball is within y cords range of player 1
			if (ball.getYPos() >= player1.getYPos() - 10 &&
				ball.getYPos() <= player1.getYPos() + 40)
			{
				ball.changeDirection();
				
				// increase slope if in special range
				if ((ball.getYPos() >= player1.getYPos() - 10 &&
					ball.getYPos() <= player1.getYPos() + 15) ||
					(ball.getYPos() >= player1.getYPos() - 35 &&
					ball.getYPos() <= player1.getYPos() + 50))
				{
					if (ball.getSlope() < 1.5)
						ball.setSlope(ball.getSlope() * 2);
				}
				else
					ball.setSlope(ball.getSlope() * 0.75);
			}
		}
		
		// Collision detection for player 2
		// If ball is within x cords range of player 2
		if (ball.getXPos() >= player2.getXPos() - 20 && ball.getXPos() <= player2.getXPos() + 30)
		{
			// If ball is within y cords range of player 2
			if (ball.getYPos() >= player2.getYPos() - 10 &&
					ball.getYPos() <= player2.getYPos() + 40)
			{
				ball.changeDirection();
			}
			
			// increase slope if in special range
			if ((ball.getYPos() >= player2.getYPos() - 10 &&
				ball.getYPos() <= player2.getYPos() + 15) ||
				(ball.getYPos() >= player2.getYPos() - 35 &&
				ball.getYPos() <= player2.getYPos() + 50))
			{
				if (ball.getSlope() < 1.5)
					ball.setSlope(ball.getSlope() * 2);
			}
			else
				ball.setSlope(ball.getSlope() * 0.75);
		}
		
		// a catch all in case the slope gets too crazy
		if (Math.abs(ball.getSlope()) > 2)
			ball.setSlope(0.75);
			
		// Collision detection with floor and ceiling
		if (ball.getYPos() < 3 || ball.getYPos() > Pong.getPanel().getHeight() - 23)
			ball.setSlope(-ball.getSlope());
		
		// Check to see if anyone lost
		if (ball.getXPos() < - 20)
		{
			wonLast = 1;
			player2.incrementScore();
			restThree();
			
			// reset ball
			ball.setXPos((Pong.getPanel().getWidth() / 2) - 10);
			ball.setYPos(Pong.getPanel().getHeight() / 2);
			ball.setSlope(Math.random() / 2);
			if (2 * Math.random() == 0)
				ball.setSlope(-ball.getSlope());
			
			// See if game is over, winner stored in wonLast
			if (player2.getScore() >= 10)
				Pong.stopGame();
		}
		
		if (ball.getXPos() > Pong.getPanel().getWidth())
		{
			wonLast = -1;
			player1.incrementScore();
			restThree();
			ball.setXPos((Pong.getPanel().getWidth() / 2) - 10);
			ball.setYPos(Pong.getPanel().getHeight() / 2);
			
			// reset ball
			ball.setXPos((Pong.getPanel().getWidth() / 2) - 10);
			ball.setYPos(Pong.getPanel().getHeight() / 2);
			ball.setSlope(Math.random() / 2);
			if (2 * Math.random() == 0)
				ball.setSlope(-ball.getSlope());
			
			// See if game is over, winner stored in wonLast
			if (player1.getScore() >= 10)
				Pong.stopGame();
		}
	} // end of tick
	
	// sets ball rest to true
	public void restThree()
	{
		threeSecondRest = true;
	}
	
	// Set methods
	public void setPlayer1(Player p)
	{
		player1 = p;
	}
	
	public void setPlayer2(Player p)
	{
		player2 = p;
	}
	
	public void setBall(Ball b)
	{
		ball = b;
	}
	
	public static void setMultiPlayer(boolean bool)
	{
		isMultiplayer = bool;
	}
	
	// Accessor methods
	
	public Player getPlayer1()
	{
		return player1;
	}
	
	public Player getPlayer2()
	{
		return player2;
	}
	
	public Ball getBall()
	{
		return ball;
	}
	
	public boolean getIsMultiplayer()
	{
		return isMultiplayer;
	}
	
	public int getWonLast()
	{
		return wonLast;
	}
}
