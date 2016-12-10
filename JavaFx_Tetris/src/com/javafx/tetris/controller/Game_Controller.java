package com.javafx.tetris.controller;

import com.javafx.tetris.block.B_Point;
import com.javafx.tetris.block.Block;

import javafx.scene.paint.Color;

public class Game_Controller {
	
	private final View_Controller view_ctr;
	private final Block_Controller block_ctr = new Block_Controller();
	//public Game_Controller g = this;
	
	private final int height = 20;			//게임패널의 높이
	private final int width = 10;			//게임패널의 넓이
	
	public Game_Controller(View_Controller c) {
		this.view_ctr = c;
		block_ctr.blockCreate();
		view_ctr.initGameView(height, width);
		//점수바인드
	}
	
	
	public void createNewGame(){
		newAction();
		view_ctr.startGame();							//새로운 게임시작
	}
	
	public void newAction(){
		block_ctr.getBlock();							//새로운 블럭을 설정
		view_ctr.initBlockPanel(block_ctr.currBlock);	//초기 블럭패널 위치 설정
	}
	
	public Block downEvent() {		
		B_Point[] temp = block_ctr.currBlock.getCurrPoint();
		for(int i=0; i<temp.length; i++){
			int x = temp[i].getX();
			int y = temp[i].getY() + 1;
			
			if(y==height){
				view_ctr.refreshGamePanel(block_ctr.currBlock);
				checkLine();
				newAction();
				
				//줄제거
				return null;
			}
			else if(y>=0){
				if(!view_ctr.gameRects[y][x].getFill().equals(Color.TRANSPARENT)){
					view_ctr.refreshGamePanel(block_ctr.currBlock);
					checkLine();
					newAction();
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
			else if(y>=0){
				if(!view_ctr.gameRects[y][x].getFill().equals(Color.TRANSPARENT)){
					System.out.println("null");
					return null;
				}
			}
		}
		block_ctr.rotate();
		return block_ctr.currBlock;
	}
	
	public void checkLine(){
		System.out.println("줄삭제 로직 실행됨");
		
		int min = 19;
		int max = 0;
		
		for(int i=0; i<block_ctr.currBlock.getCurrPoint().length; i++){
			int y = block_ctr.currBlock.getCurrPoint()[i].getY();
			if(max < y) max = y; 
			if(min > y) min = y;
		}
		
		for(int i=max; i>=min-1; i--){
			boolean isLine = true;
			for(int j=0; j<10; j++){
				if(view_ctr.gameRects[i][j].getFill().equals(Color.TRANSPARENT)){
					//하나라도 빈곳이 있으면 튕
					isLine = false;	
					break;
				}
			}
			if(isLine){
				view_ctr.deleteLine(i);
			}
		}
		
		
		
	}
	
}
