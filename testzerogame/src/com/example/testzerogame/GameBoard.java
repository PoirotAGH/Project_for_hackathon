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
			//if (i == 0 || i == 1 || i == 2)
				//gameBoard[i] = new GamePiece("Red"); 
			
			int min = 1;
			int max = 9;	
			Random r = new Random();
			gameBoard[i] = new GamePiece(r.nextInt(max - min + 1) + min);
		}
		
	}
	
	public void SetMove(int val, int startLocation, int endLocation) {
		
		gameBoard[startLocation].setColor(" ");
		gameBoard[endLocation].setVal(val);
	}

}