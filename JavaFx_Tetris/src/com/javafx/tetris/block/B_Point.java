package com.javafx.tetris.block;

public class B_Point {
	private int y;
	private int x;

	public B_Point() {
	}
	
	public B_Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	public void setYX(int y, int x){
		this.y = y;
		this.x = x;
	}
	
	public void moveYX(int offset_y, int offset_x){
		y+=offset_y;
		x+=offset_x;		
	}

	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}

}
