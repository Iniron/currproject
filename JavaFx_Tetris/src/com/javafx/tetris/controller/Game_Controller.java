package com.javafx.tetris.controller;

import com.javafx.tetris.block.B_Point;
import com.javafx.tetris.block.Block;

import javafx.scene.paint.Color;

public class Game_Controller {
	
	private final View_Controller view_ctr;
	private final Block_Controller block_ctr = new Block_Controller();
	//public Game_Controller g = this;
	
	final int height = 20;			//게임패널의 높이
	final int width = 10;			//게임패널의 넓이
	
	public Game_Controller(View_Controller c) {
		this.view_ctr = c;
		block_ctr.blockCreate();
		//block_ctr.getBlock();
		view_ctr.initGameView(height, width);
		//점수바인드
		
		//controller.createbrick();
	}
	
	public void createNewGame(){
		block_ctr.getBlock();
		view_ctr.initBlockPanel(block_ctr.currBlock);
		//view_ctr.isKey = true;
	}
	
	public Block downEvent() {		
		B_Point[] temp = block_ctr.currBlock.getCurrPoint();
		for(int i=0; i<temp.length; i++){
			int x = temp[i].getX();
			int y = temp[i].getY() + 1;
			
			if(y==height){
				view_ctr.refreshGamePanel(block_ctr.currBlock);	//배경판넬에 그린다. 
				block_ctr.getBlock();							//다음블럭을 저장
				view_ctr.initBlockPanel(block_ctr.currBlock);	//블럭패널을 올리고 그 패널에 다음블럭을 출력
				return null;
			}
			else if(y>=0){
				if(!view_ctr.gameRects[y][x].getFill().equals(Color.TRANSPARENT)){
					view_ctr.refreshGamePanel(block_ctr.currBlock);
					block_ctr.getBlock();
					view_ctr.initBlockPanel(block_ctr.currBlock);
					return null;
				}
			}
			
		}
		block_ctr.moveDown();
//		if(block_ctr.currBlock.offset.getY()>0){
//			view_ctr.isKey = true;
//		}
		return block_ctr.currBlock;
	}
	
	public Block leftEvent() {		
		B_Point[] temp = block_ctr.currBlock.getCurrPoint();
		for(int i=0; i<temp.length; i++){
			int x = temp[i].getX() - 1;
			int y = temp[i].getY();
			
			if(x<0) return null;
			else if(y>=0){
				if(!view_ctr.gameRects[y][x].getFill().equals(Color.TRANSPARENT)){
					return null;
				}
			}
		}
		block_ctr.moveLeft();
		return block_ctr.currBlock;
	}
	
	public Block rightEvent() {		
		B_Point[] temp = block_ctr.currBlock.getCurrPoint();
		for(int i=0; i<temp.length; i++){
			int x = temp[i].getX() + 1;
			int y = temp[i].getY();
			
			if(x==width) return null;
			if(y>=0){
				if(!view_ctr.gameRects[y][x].getFill().equals(Color.TRANSPARENT)){
					return null;
				}
			}
		}
		block_ctr.moveRight();
		return block_ctr.currBlock;
	}
	
	public Block rotateEvent() {		
		B_Point[] temp = block_ctr.currBlock.getNextShape();
		for(int i=0; i<temp.length; i++){
			int x = temp[i].getX();
			int y = temp[i].getY();
			
			if(y==height || x<0 || x==width) return null;
			else if(y>0){
				if(!view_ctr.gameRects[y][x].getFill().equals(Color.TRANSPARENT)){
					System.out.println("null");
					return null;
				}
			}
		}
		block_ctr.rotate();
		return block_ctr.currBlock;
	}
}
