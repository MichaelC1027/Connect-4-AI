import java.util.Scanner;

/**
 * Connect Four Game!
 * Here we will have a Connect four Game with an AI,
 * The AI is a rough draft AI that will be playing as the second player
 * 
 * @author Michael Castillo
 * @version 5/10/2019
 *
 */
public class CFGame 
{
	/*
	 * The Variables
	 * We are going to use the same variables
	 * that we used in Tic-tac-toe because both
	 * games are closely similar in gameboard and set up
	 */
	static int input;
	static int turns = 0;
	static int p1 = 0;
	static int p2 = 1;
	static String Symbol = "";
	static String winner = "";
	static Scanner scan = new Scanner(System.in);
	static boolean win = false;
	
	//here we have the connectfour gameboard
	static String[][] gameBoard = 
		{
				{" ", " ", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", " ", " "},
				{" ", " ", " ", " ", " ", " ", " "}
		};
	
	//The intro for the user, this will help them understand how to place pieces
	public static void Intro()
	{
		System.out.println("Welcome to the connect 4 game!");
		System.out.println("Please type in the number you would");
		System.out.println("like to drop your game piece into");
	}
	
	/*
	 * The PrintBoard Method
	 * this method will produce 6 rows and 7 columns,
	 * just like the real connect 4 board!
	 * it creates the board by running a nested for loop
	 */
	public static void PrintBoard()
	{
		System.out.println();
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				System.out.print("|_" + gameBoard[i][j] + "_");
			}
			System.out.println("|");
		}
		gameboard[5][3] = "X";
		
		
	}
	
	/*
	 * The PlayGame Method!
	 * This Method will play the game for us,
	 * the while loop will run as long as the board is not full
	 * once it is full it will end and call it a tie
	 */
	public static void PlayGame()
	{
		while(turns < 42)
		{
			if(p1 < p2)
			{
				System.out.println("Player 1's Turn");
				input = scan.nextInt();
				if(input < 7 && input >= 0)
				{
					Symbol = "R";
					PositionCheck(input);
					p1 += 2;
					turns += 1;
					CheckWin();
				}
				else
				{
					TryAgain();
				}
			}
			else
			{
				BotTurn();
			}
		}
		End();
	}
	
	//The TryAgain method
	public static void TryAgain()
	{
		System.out.println("Sorry, You cant play in that column");
		PrintBoard();
		PlayGame();
	}
	
	/*
	 * The PositionCheck Method
	 * @param an int that is given by the user
	 * it will return a Symbol in that position
	 * Afterwards it will play the game again
	 */
	public static void PositionCheck(int pos)
	{
		for(int i = 5; i >=0; i--)
		{
			if(gameBoard[i][pos] == " ")
			{
				gameBoard[i][pos] = Symbol;
				break;
			}
			else if(gameBoard[0][pos] != " ")
			{
				TryAgain();
			}
		}
		PrintBoard();
	}
	
	/*
	 * The CheckWin Method
	 * This Method will check for the Vertical win,
	 * The Horizontal Win, and the Diagonal win
	 * 
	 * I had some help with the diagonal win with Michael Pascale
	 * 
	 */
	public static void CheckWin()
	{
		//here is how we check for vertical wins for P1
		for(int col = 0; col <= 6; col++)
		{
			int counter = 0;
			for(int row = 0; row <= 5; row++)
			{
				if(gameBoard[row][col] == "R")
					counter++;
				else
					counter = 0;
				//when the counter is 4 they should win
				if(counter == 4)
				{
					winner = "Player 1";
					win = true;
					break;
				}
			}
		}
		
		//here is how we will check for vertical wins for P2
		for(int col = 0; col <= 6; col++)
		{
			int counter = 0;
			for(int row = 0; row <= 5; row++)
			{
				if(gameBoard[row][col] == "B")
					counter++;
				else
					counter = 0;
				//when the counter is 4 they should win
				if(counter == 4)
				{
					winner = "Player 2";
					win = true;
					break;
				}
			}
		}
		
		//here is how we will check the horizontal wins for P1
		for(int col = 0; col <= 5; col++)
		{
			int counter = 0;
			for(int row = 0; row <= 6; row++)
			{
				if(gameBoard[col][row] == "R")
					counter++;
				else
					counter = 0;
				//when the counter is 4 they should win
				if(counter == 4)
				{
					winner = "Player 1";
					win = true;
					break;
				}
			}
		}
		
		//here is how we will check the horizontal wins for P2
		for(int col = 0; col <= 5; col++)
		{
			int counter = 0;
			for(int row = 0; row <= 6; row++)
			{
				if(gameBoard[col][row] == "B")
					counter++;
				else
					counter = 0;
				//when the counter is 4 they should win
				if(counter == 4)
				{
					winner = "Player 2";
					win = true;
					break;
				}
			}
		}
		
		//this statement will only be true if the counter is right 
		if(win == true)
			End();
		else
			PlayGame();
	}
	
	/*
	 * this End Method will actually call out
	 * the winner of the game or a tie,
	 * afterwards it will close out the program
	 */
	public static void End()
	{
		if(win == true)
		{
			System.out.println(winner + " Wins!");
			System.exit(0);
		}
		System.out.println("It is a Tie!");
		System.exit(0);
		
	}
	
	/**
	 * The AI Bot Code
	 * Here we will have all the code for the Bot.
	 * The Bot will be able to Block, Win, and Random Place
	 * The Bot will be taking the turn just like a normal person
	 * The only factor is the random factor
	 */
	
	/*
	 * The BotTurn Method
	 * This Method will actually play the player 2 turn but randomly
	 * this would be a random place
	 */
	public static void BotTurn()
	{
		//the variables for the random number
		int max = 6;
		int min = 0;
		int range = max - min + 1;
		
		
		System.out.println("Bot's Turn");
		input = (int)(Math.random() * range + min)
		if(input < 7 && input >= 0)
		{
			Symbol = "B";
			PositionCheck(input);
			p2 += 2;
			turns += 1;
			CheckWin();
		}
		else
		{
			TryAgain();
		}			
	}
	
	/*
	 * The Block Method,
	 * The Block Method will be exactly like the CheckWin Method's for Loops
	 * The only difference instead of matching up 4 and winning, the bot will
	 * count to 3 and then it will block
	 */
	public static void Block()
	{
		//here is how we check for vertical wins for P1
				for(int col = 0; col <= 6; col++)
				{
					int counter = 0;
					for(int row = 0; row <= 5; row++)
					{
						if(gameBoard[row][col] == "R")
							counter++;
						else
							counter = 0;
						//when the counter is 4 they should win
						if(counter == 3 && board[])
						{
							winner = "Player 1";
							win = true;
							break;
						}
					}
				}
				
				
				
				//here is how we will check the horizontal wins for P1
				for(int col = 0; col <= 5; col++)
				{
					int counter = 0;
					for(int row = 0; row <= 6; row++)
					{
						if(gameBoard[col][row] == "R")
							counter++;
						else
							counter = 0;
						//when the counter is 4 they should win
						if(counter == 3)
						{
							winner = "Player 1";
							win = true;
							break;
						}
					}
				}
			
	}
	
	/*
	 * The Win Method,
	 * The Win Method will be exactly like the CheckWin Method's for Loops
	 * The only difference instead of matching up 4 and winning, the bot will
	 * count to 3 and then it will try to win
	 */
	public static void Win()
	{
		//here is how we will check for vertical wins for P2
		for(int col = 0; col <= 6; col++)
		{
			int counter = 0;
			for(int row = 0; row <= 5; row++)
			{
				if(gameBoard[row][col] == "B")
					counter++;
				else
					counter = 0;
				//when the counter is 4 they should win
				if(counter == 3)
				{
					winner = "Player 2";
					win = true;
					break;
				}
			}
		}
		
		//here is how we will check the horizontal wins for P2
		for(int col = 0; col <= 5; col++)
		{
			int counter = 0;
			for(int row = 0; row <= 6; row++)
			{
				if(gameBoard[col][row] == "B")
					counter++;
				else
					counter = 0;
				//when the counter is 4 they should win
				if(counter == 3)
				{
					winner = "Player 2";
					win = true;
					break;
				}
			}
		}
	}

}
