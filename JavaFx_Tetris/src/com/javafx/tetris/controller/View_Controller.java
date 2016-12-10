package com.javafx.tetris.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.javafx.tetris.block.B_Point;
import com.javafx.tetris.block.Block;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import oracle.net.aso.g;

public class View_Controller implements Initializable {
	
	private static final int BRICK_SIZE = 20;		
	
	@FXML
	private GridPane gamePanel;
	
	@FXML
	private GridPane blockPanel;
	
	@FXML
	private Button newGameButton;
	
	Rectangle[][] gameRects;
	Rectangle[][] blockRects;
	Timeline timeline;
	boolean isKey = false;
	
	private Game_Controller game_ctr;	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		newGameButton.setFocusTraversable(false);
		gamePanel.setFocusTraversable(true);
		gamePanel.requestFocus();
		gamePanel.setOnKeyPressed(event->{
			if(isKey){
				if(event.getCode()==KeyCode.UP){
					Block currBlock = game_ctr.rotateEvent();
					refreshBlockPanel(currBlock);
					setBlockPanel(currBlock);
					event.consume();
				}
				if(event.getCode()==KeyCode.DOWN){
					setBlockPanel(game_ctr.downEvent());
					event.consume();
				}
				if(event.getCode()==KeyCode.LEFT){
					setBlockPanel(game_ctr.leftEvent());
					event.consume();
				}
				if(event.getCode()==KeyCode.RIGHT){
					setBlockPanel(game_ctr.rightEvent());
					event.consume();
				}
				if(event.getCode()==KeyCode.SPACE){
					Block currBlock;
					while(true){
						currBlock = game_ctr.downEvent();
						if(currBlock==null) break;
						else setBlockPanel(currBlock);
					}
					event.consume();	//������ �����̽� ����...�̾�...
				}
			}
				if(event.getCode()==KeyCode.ENTER){
					game_ctr.createNewGame();
					event.consume();			
				}
		});
		newGameButton.setOnAction(event->{
			game_ctr.createNewGame();
			event.consume();
		});
	}
	
	public void setGameController(Game_Controller game_ctr) {
		this.game_ctr = game_ctr;
	}
	
	public void initGameView(int height, int width) {
		Rectangle rect;
		gameRects = new Rectangle[height][width];			//setGamePanel
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				rect = new Rectangle(BRICK_SIZE, BRICK_SIZE);
				rect.setFill(Color.TRANSPARENT);
				gameRects[i][j] = rect;
				gamePanel.add(rect, j, i);
			}
		}
		
		blockRects = new Rectangle[Block.PointCnt][Block.PointCnt];
		for(int i=0; i<Block.PointCnt; i++){
			for(int j=0; j<Block.PointCnt; j++){
				rect = new Rectangle(BRICK_SIZE, BRICK_SIZE);
				rect.setFill(Color.TRANSPARENT);
				blockRects[i][j] = rect;
				blockPanel.add(rect, j, i);
			}
		}
		
		blockPanel.setLayoutX(gamePanel.getLayoutX()+((gamePanel.getHgap())*(4-1))+(BRICK_SIZE*(4-1)));
		blockPanel.setLayoutY(gamePanel.getLayoutY()+((gamePanel.getVgap())*(-1-1))+(BRICK_SIZE*(-1-1)));
	}
	
	public void initBlockPanel(Block currBlock){
		currBlock.offset.setYX(-1, 4); 		//y=-1: �ʱⰪ, y:=-3: �Ⱥ��϶���
		currBlock.setAngle(0);				//���� ������ �ʱⰪ���� ����
		setBlockPanel(currBlock);			//�� �г��� ��ġ ����
		refreshBlockPanel(currBlock);		//�� �гο� ���� ���ΰ�ħ 
	}
	
	//�� �г��� ���ΰ�ħ
	public void refreshBlockPanel(Block currBlock) {
		if(currBlock==null){
			System.out.println("unable to rotate");
			return;
		} 
		for(int i=0; i<currBlock.shape.length; i++){
			for(int j=0; j<currBlock.shape.length; j++){
				blockRects[i][j].setFill(Color.TRANSPARENT);
			}
		}
		for(int i=0; i<currBlock.shape.length; i++){
			int x = currBlock.shape[i].getX()+1;
			int y = currBlock.shape[i].getY()+1;
			blockRects[y][x].setFill(currBlock.color);
		}
	}
	
	//���� �г��� ���ΰ�ħ
	public void refreshGamePanel(Block currBlock) {
		for(int i=0; i<currBlock.getCurrPoint().length; i++){
			int x = currBlock.getCurrPoint()[i].getX();
			int y = currBlock.getCurrPoint()[i].getY();
			//ȭ�鿡 �������� �� && ������ �� ��ĥ����
			if(y>=0 && gameRects[y][x].getFill().equals(Color.TRANSPARENT)){
				gameRects[y][x].setFill(currBlock.color);
			}
		}
					
		for(int i=0; i<currBlock.getCurrPoint().length; i++){
			int y = currBlock.getCurrPoint()[i].getY();
			if(y<0){	
				//�����Լ� ����
				//timeline.stop();
				isKey = false;
				//����� �г��� ���� ĥ�ϸ� �ȵǰ� �����ؾ���
				System.out.println("��������");
				return;
			}
		}
	}
	
	
	public void setBlockPanel(Block currBlock) {
		if(currBlock==null){
			System.out.println("unable to move");
			return;
		}else{
			blockPanel.setLayoutX(gamePanel.getLayoutX()+((gamePanel.getHgap())*(currBlock.offset.getX()-1))+(BRICK_SIZE*(currBlock.offset.getX()-1)));
			blockPanel.setLayoutY(gamePanel.getLayoutY()+((gamePanel.getVgap())*(currBlock.offset.getY()-1))+(BRICK_SIZE*(currBlock.offset.getY()-1)));
		}
	}
	
	public void startGame(){
		//�ʿ��� ��� timeline���� �� �׷��� ó���� �ٽý��� �ؾ��Ѵ�. 
		gamePanel.requestFocus();		//��Ŀ���� �����гο� ����
		isKey = true;					//Ű�Է� ���
		//��׶��� ��ü ����
		
//		if((timeline==null) || (timeline.getStatus()==Status.STOPPED)){
//			timeline = new Timeline(new KeyFrame(
//	              Duration.millis(500),
//	              event -> setBlockPanel(game_ctr.downEvent())
//		    ));
//			timeline.setCycleCount(Timeline.INDEFINITE);
//			timeline.play();
//		}
	}
	
	public void deleteLine(int line/*���ڷ� ���� �ޱ�*/){
		//timeline.stop();
				
		//System.out.println("top: " + min);
		//System.out.println("bottom" + max);
		
		for(int i=line; i>=0; i--){
			for(int j=0; j<10; j++){
				if(i>0)
				gameRects[i][j].setFill(gameRects[i-1][j].getFill());
				if(i==0)
					gameRects[i][j].setFill(Color.TRANSPARENT);
			}
		}
		
		
		
		
		//timeline.play();
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
