package com.example.testzerogame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private GameBoard game;
	private int startLocation;
	private Button gameButtons[];
	private TextView infoDisplay;
	private boolean gameOver= false;
	private boolean redFirst=true;
	private boolean redTurn=true;
	private boolean placeSelected=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		
		//setup the gameButtons array
		
		gameButtons = new Button[game.getBOARD_SIZE()];
		gameButtons[0] = (Button) findViewById(R.id.button1);
		gameButtons[1] = (Button) findViewById(R.id.button2);
		gameButtons[2] = (Button) findViewById(R.id.button3);
		gameButtons[3] = (Button) findViewById(R.id.button4);
		gameButtons[4] = (Button) findViewById(R.id.button5);
		gameButtons[5] = (Button) findViewById(R.id.button6);
		gameButtons[6] = (Button) findViewById(R.id.button7);
		gameButtons[7] = (Button) findViewById(R.id.button8);
		gameButtons[8] = (Button) findViewById(R.id.button9);
		
		infoDisplay = (TextView) findViewById(R.id.infoDisplay);
		
		game = new GameBoard();
		
		startNewGame();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	switch(item.getItemId())
	{
	case R.id.newGame:
		startNewGame();
		break;
	case R.id.exitGame:
		MainActivity.this.finish();
		break;
	
	}
	
	return true;
	}
	
	private void startNewGame() {
		game.ClearBoard();
		for (int i=0; i<gameButtons.length; i++)
		{
			if (i==0 || i==1 || i==2)
			{
				gameButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.green_launch));
				gameButtons[i].setTag("Red");
			}
			
			else if (i==6 || i==7 || i==8)
			{
				gameButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_launch));
				gameButtons[i].setTag("Blue");
			}
			
			else
			{
				gameButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.white_launch));
				gameButtons[i].setTag("Blank");
			}
			
			gameButtons[i].setText("");
			gameButtons[i].setOnClickListener(new ButtonClickListener(i));
		}
		if (redFirst)
		{
			redTurn= true;
			redFirst= false;
			infoDisplay.setText("Red's Turn");
		}
		
		else {
			redTurn= false;
			redFirst= true;
			infoDisplay.setText("Blue's Turn");
		}
		
		startLocation = -1;
		gameOver=false;
	}
	
	private void setMove(String color, int startLoc, int endLoc)
	{
		Drawable cDrawable = null;
		
		if (color =="Red")
			cDrawable = getResources().getDrawable(R.drawable.green_launch);
		if (color =="Blue")
			cDrawable = getResources().getDrawable(R.drawable.blue_launch);
		
		game.SetMove(color, startLoc, endLoc);
		gameButtons[startLoc].setBackgroundDrawable(getResources().getDrawable(R.drawable.white_launch));
		gameButtons[startLoc].setTag("Blank");
		gameButtons[endLoc].setBackgroundDrawable(cDrawable);
		gameButtons[endLoc].setTag("color");
		
		placeSelected = false;
	}
	
	private void MoveResult()
	{
		int winner = game.CheckForWin();
		
		if (winner == 0)
		{
			if (redTurn)
			{
				infoDisplay.setText("Blue's turn");
				redTurn = false;
			}
			
			else
			{
				infoDisplay.setText("Red's turn");
				redTurn = true;
			}
		}
		else if (winner == 1)
		{
			infoDisplay.setText("Red Wins!");
			gameOver=true;
		}
		else if (winner == 2)
		{
			infoDisplay.setText("Blue Wins!");
			gameOver=true;
		}
	}
	
	private class ButtonClickListener implements View.OnClickListener {
		int location;
		
		public ButtonClickListener(int location)
		{
			this.location = location;
		}
		
		public void onClick(View view)
		{
			if(!gameOver)
			{
				if(!placeSelected)
				{
					if(redTurn && gameButtons[location].getTag() == "Red")
					{
						gameButtons[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.green_launch));
					}
					
					if(!redTurn && gameButtons[location].getTag() == "Blue")
					{
						gameButtons[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_launch));
					}
				}
				else if (placeSelected)
				{
					if(gameButtons[location].getTag() == "Blank")
					{
						switch(startLocation)
						{
						case 0:
						if (location == 1 || location == 3 || location == 4)
						{
							if(redTurn && gameButtons[startLocation].getTag()== "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
					if(redTurn && gameButtons[startLocation].getTag() == "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						
						case 1:
						if (location == 0 || location == 2 || location == 3|| location == 4 || location == 5)
						{
							if(redTurn && gameButtons[startLocation].getTag() == "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
						if(redTurn && gameButtons[startLocation].getTag() == "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						
						case 2:
						if (location == 1 || location == 4 || location == 5)
						{
							if(redTurn && gameButtons[startLocation].getTag() == "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
						if(redTurn && gameButtons[startLocation].getTag() == "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						
						case 3:
						if (location == 0 || location == 1 || location == 4|| location == 6 || location == 7)
						{
							if(redTurn && gameButtons[startLocation].getTag() == "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
						if(redTurn && gameButtons[startLocation].getTag() == "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						
						case 4:
						if (location == 0 || location == 1 || location == 2 || location == 3 || location == 5|| location == 6 || location == 7|| location == 8)
						{
							if(redTurn && gameButtons[startLocation].getTag() == "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
						if(redTurn && gameButtons[startLocation].getTag() == "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						
						case 5:
						if (location == 1 || location == 2 || location == 4 || location == 7 || location == 8)
						{
							if(redTurn && gameButtons[startLocation].getTag() == "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
						if(redTurn && gameButtons[startLocation].getTag()== "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						
						case 6:
						if (location == 3 || location == 4 || location == 7)
						{
							if(redTurn && gameButtons[startLocation].getTag()== "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
						if(redTurn && gameButtons[startLocation].getTag() == "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						
						case 7:
						if (location == 3 || location == 4 || location == 5 || location == 6 || location == 8)
						{
							if(redTurn && gameButtons[startLocation].getTag()== "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
						if(redTurn && gameButtons[startLocation].getTag() == "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						
						case 8:
						if (location == 4 || location == 5 || location == 7)
						{
							if(redTurn && gameButtons[startLocation].getTag() == "Red")
							{
								setMove("Red", startLocation, location);
								
								MoveResult();
							}
						if(redTurn && gameButtons[startLocation].getTag() == "Blue")
							{
								setMove("Blue", startLocation, location);
								
								MoveResult();
							}
						}
						break;
						}
					}
				}
			}
		}
	}
	
}
