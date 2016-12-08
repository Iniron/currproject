package com.javafx.tetris.controller;

import com.javafx.tetris.block.Block;

public class Game_Controller {
	
	private final View_Controller view_ctr;
	private final Block_Controller block_ctr = new Block_Controller();
	//public Game_Controller g = this;
	
	final int height = 20;			//게임패널의 높이
	final int witdh = 10;			//게임패널의 넓이
	
	public Game_Controller(View_Controller c) {
		this.view_ctr = c;
		block_ctr.blockCreate();
		view_ctr.initGameView(height, witdh, block_ctr.currBlock);
		
		//controller.createbrick();
	}	
	
	public Block downEvent() {
		// TODO Auto-generated method stub
		return block_ctr.BlockDown();
	}
}
