/*
 * Main program for Pong
 */

import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("*** PONG ***");
		System.out.println("Controls:");
		System.out.println("'W' moves the player up.");
		System.out.println("'S' moves the player down.");
		System.out.println("Escape stops the game. This is not pause.");
		System.out.println("Up and Down arrows move player 2 for multiplayer.");
		System.out.println();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Type 1 for single player, 2 for multiplayer: ");
		PongGame.setMultiPlayer(in.nextInt() == 2);
		
		in.close();
		
		// Start game
		Pong thread = new Pong();
		thread.start();
	}
}
