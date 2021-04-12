/*
 * Main class for the pong game, contains the game loop, creates the game window
 * also has the main menu code.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Pong extends Thread
{
	// Fields
	private static PongGraphics panel;
	private static PongGame controller;
	private static UserInput input;
	private static GameStatus status;
	private static final double AMOUNT_OF_TICKS = 30.0; // also frame rate (sort of) (tick rate)
	private static int gamesPlayed = 0;
	
	// extends Thread constructor so Main constructor not needed
	
	public void tick() throws InterruptedException
	{
		controller.tick();
	}
	
	public void render()
	{
		panel.repaint();
	}
	
	// Main game thread, called when main.start() is called.
	// Contains game loop
	public void run()
	{
		JFrame window = new JFrame("Pong"); // Creates window
		window.setBounds(350, 100, 720, 480);
		window.setResizable(false);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		panel = new PongGraphics();
		panel.setBackground(Color.BLACK);
		
		// Add panel to window
		window.getContentPane().add(panel);
		
		// Make window visible
		window.setVisible(true);
		
		// Add user input to window
		input = new UserInput();
		window.addKeyListener(input);
		
		controller = new PongGame();
		
		startGame(); // called from Main method
		long ms = (long)(1000 / AMOUNT_OF_TICKS); // gives wanted number of ms per tick
		controller.restThree();
		
		while(isGameRunning())
		{
			long startTime = System.currentTimeMillis();
			
				// I started having issues with the thread and this fixed it
				try
				{
					tick();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				render();
			
			long elapsed = System.currentTimeMillis() - startTime;
			
			try
			{
				sleep((ms - elapsed));
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		} // end of while loop
		
		gamesPlayed ++;
	} // end of run()
	
	public boolean isGameRunning()
	{
		return status == GameStatus.RUNNING;
	}
	
	public static void startGame()
	{
		status = GameStatus.RUNNING;
		
		// Create actors
		controller.setPlayer1(new Player(20, (panel.getHeight() / 2) - 10, 10));
		controller.setPlayer2(new Player(panel.getWidth() - 30, (panel.getHeight() / 2) - 10, 10));
		controller.setBall(new Ball((panel.getWidth() / 2) - 10, panel.getHeight() / 2));
		
		// Initialize slope for ball
		controller.getBall().setSlope(Math.random() / 2);
		if (2 * Math.random() == 0)
			controller.getBall().setSlope(-controller.getBall().getSlope());
	}
	
	public static void stopGame()
	{
		status = GameStatus.STOPPED;
	}
	
	// Accessor methods
	public static int getGamesPlayed()
	{
		return gamesPlayed;
	}
	
	public static GameStatus getStatus()
	{
		return status;
	}
	
	public static PongGraphics getPanel()
	{
		return panel;
	}
	
	public static PongGame getController()
	{
		return controller;
	}
	
	public static UserInput getInput()
	{
		return input;
	}
	
	public static double getAmountOfTicks()
	{
		return AMOUNT_OF_TICKS;
	}
}
