package com.example.testzerogame;

public class GamePiece {

private String color;
private int val;

public String getColor() {
	return color;
}

public int getVal() {
	return val;
}

public void setColor(String color) {
	this.color = color;
}

public void setVal(int val) {
	this.val = val;
}

public GamePiece(String color)
{
	this.color=color;
	}

public GamePiece(int val)
{
	this.val=val;
	}
	
}
