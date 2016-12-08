package com.javafx.tetris.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.javafx.tetris.block.Block;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class View_Controller implements Initializable {
	
	private static final int BRICK_SIZE = 20;		
	
	@FXML
	private GridPane gamePanel;
	
	@FXML
	private GridPane blockPanel;
	
	Rectangle[][] rectangles;
	
	private Game_Controller game_ctr;	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		gamePanel.setFocusTraversable(true);
		gamePanel.requestFocus();
		gamePanel.setOnKeyPressed(event->{
			if(event.getCode()==KeyCode.UP){
				System.out.println("up");
				
			}
			if(event.getCode()==KeyCode.DOWN){
				System.out.println("down");
				
				printBlock(game_ctr.downEvent());
				//gamePanel.getChildren();
				//int index = gamePanel.getChildren().indexOf(curr_rect);
				//moveBlock(1, 0);
				//printBlock();
			}
			if(event.getCode()==KeyCode.LEFT){
				System.out.println("left");
				//moveBlock(-1, 0);
			}
			if(event.getCode()==KeyCode.RIGHT){
				System.out.println("right");
				//moveBlock(1, 0);				
			}
			
		});

	}
	
	public void setGameController(Game_Controller game_ctr) {
		this.game_ctr = game_ctr;
	}
	
	public void moveBlock(int off_y, int off_x) {
		// TODO Auto-generated method stub		
	
		
		
	}
	
	public void initGameView(int height, int width, Block currBlock) {
		Rectangle rect;
		rectangles = new Rectangle[height][width];
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				rect = new Rectangle(20,20);
				rect.setFill(Color.RED);
				rectangles[i][j] = rect;
				gamePanel.add(rect, j, i);
			}
		}
		currBlock.offset.setYX(3, 3);
		printBlock(currBlock);
	}
	
	private void printBlock(Block currBlock) {
		// TODO Auto-generated method stub
		for(int i=0; i<currBlock.shape.length; i++){
			int x = currBlock.getCurrPoint()[i].getX();
			int y = currBlock.getCurrPoint()[i].getY();
			rectangles[y][x].setFill(currBlock.color);
		}		
	}
	
//	public void createbrick() {
////		curr_rect = new Rectangle(20,20);
////		curr_rect.setFill(Color.BLUE);
////		gamePanel.add(curr_rect, 4, 0);
//		curr_block = new Block();
//        for(int i=0; i<curr_block.locations.length; i++){
////        	curr_rect = new Rectangle(20,20);
////        	curr_rect.setFill(curr_block.color);
//   
//        	int x = curr_block.locations[i].getX()+4;
//        	int y = curr_block.locations[i].getY();
//        	//rectangles[y][x].setFill(Color.BLUE);
//        	curr_block.locations[i].setXY(x, y);
//    		
//        	//gamePanel.add(rect, curr_block.locations[i].getY(), curr_block.locations[i].getX());
//        }
//		
//		timeLine = new Timeline(new KeyFrame(
//                Duration.millis(500),
//                ae -> moveBlock(0, 1)
//        ));
//        timeLine.setCycleCount(Timeline.INDEFINITE);
//        timeLine.play();
//        
//        
//	}

}