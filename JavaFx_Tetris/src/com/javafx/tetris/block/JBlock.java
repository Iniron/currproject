package com.javafx.tetris.block;

import javafx.scene.paint.Color;

public class JBlock extends Block {

	public JBlock() {
		//super();
		B_Point[] angle_0 = {	new B_Point(0,0), new B_Point(0,1),
								new B_Point(0,2), new B_Point(1,2)};		
		
		B_Point[] angle_90 = {	new B_Point(1,1), new B_Point(0,1),
								new B_Point(-1,1), new B_Point(-1,2)};	
		
		B_Point[] angle_180 = {	new B_Point(-1,0), new B_Point(0,0),
								new B_Point(0,1), new B_Point(0,2)};	
		
		B_Point[] angle_270 = {	new B_Point(1,0), new B_Point(1,1),
								new B_Point(0,1), new B_Point(-1,1)};

		shape_list.add(angle_0);
		shape_list.add(angle_90);
		shape_list.add(angle_180);
		shape_list.add(angle_270);
		color = Color.BLUE;
		//setAngle((int)(Math.random()*shape_list.size()));
		setAngle(0);
	}
	
}
