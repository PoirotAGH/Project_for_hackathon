package com.example.testzerogame;

import java.util.Random;

import android.R.integer;

public class GameBoard {

	private GamePiece gameBoard[];
	private final static int BOARD_SIZE = 9;
	
	public static int getBOARD_SIZE() {
		return BOARD_SIZE;
	}
	
	public GameBoard() {
	
	//init array of game pieces
	gameBoard = new GamePiece[BOARD_SIZE];
	
}
	
	public void ClearBoard() {
		
		for (int i=0; i < BOARD_SIZE; i++)
		{
			if (i == 0 || i == 1 || i == 2)
				gameBoard[i] = new GamePiece("Red"); 
			
			else if (i == 6 || i == 7 || i == 8)
				gameBoard[i] = new GamePiece("Blue"); 
			
			else gameBoard[i] = new GamePiece("Blank");
			
			//int min = 1;
			//int max = 9;	
			//Random r = new Random();
			//gameBoard[i] = new GamePiece(r.nextInt(max - min + 1) + min);
		}
		
	}
	
	public void SetMove(String color, int startLocation, int endLocation) {
		
		gameBoard[startLocation].setColor("Blank");
		gameBoard[endLocation].setColor(color);
	}
	
	public int CheckForWin() {
		//return 0 for no winner
		//return 1 for red wins
		
		//check for horizontal win
		
		//if the top row is filled with pieces
		if(gameBoard[0].getColor() == "Blue" && 
				gameBoard[1].getColor() == "Blue" && 
				gameBoard[2].getColor() == "Blue")
			return 2;
		
		
		if(gameBoard[3].getColor() == "Red" && 
				gameBoard[4].getColor() == "Red" && 
				gameBoard[5].getColor() == "Red")
			return 1;
		
		if(gameBoard[3].getColor() == "Blue" && 
				gameBoard[4].getColor() == "Blue" && 
				gameBoard[5].getColor() == "Blue")
			return 2;
		
		if(gameBoard[6].getColor() == "Red" && 
				gameBoard[7].getColor() == "Red" && 
				gameBoard[8].getColor() == "Red")
			return 1;
		
		
		//check for vertical win
		
		for (int i = 0; i <= 2; i++)
		{
			if(gameBoard[i].getColor() == "Red" &&
				gameBoard[i+3].getColor() == "Red" &&
				gameBoard[i+6].getColor() == "Red")
				return 1;
			
			if(gameBoard[i].getColor() == "Blue" &&
					gameBoard[i+3].getColor() == "Blue" &&
					gameBoard[i+6].getColor() == "Blue")
					return 2;
			
		}
		
		//check for diag win
		
		if ((gameBoard[0].getColor() == "Red" &&
				gameBoard[4].getColor() == "Red" &&
				gameBoard[8].getColor() == "Red" ) ||
				gameBoard[2].getColor() == "Red" &&
				gameBoard[4].getColor() == "Red" &&
				gameBoard[6].getColor() == "Red" )
				return 1;
		
		if ((gameBoard[0].getColor() == "Blue" &&
				gameBoard[4].getColor() == "Blue" &&
				gameBoard[8].getColor() == "Blue" ) ||
				gameBoard[2].getColor() == "Blue" &&
				gameBoard[4].getColor() == "Blue" &&
				gameBoard[6].getColor() == "Blue" )
				return 2;
		
		return 0;
	}

}