package com.javafx.tetris.block;

import javafx.scene.paint.Color;

public class OBlock extends Block {

	public OBlock() {
		//super();
		B_Point[] angle_0 = {	new B_Point(0,0), new B_Point(0,1),
								new B_Point(1,0), new B_Point(1,1)};		

		shape_list.add(angle_0);
		color = Color.YELLOW;
		//setAngle((int)(Math.random()*shape_list.size()));
		setAngle(0);
	}
}
