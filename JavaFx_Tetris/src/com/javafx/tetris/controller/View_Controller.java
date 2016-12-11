package com.javafx.tetris.controller;

import java.net.URL;
import java.util.Queue;
import java.util.ResourceBundle;

import com.javafx.tetris.app.GameOverPanel;
import com.javafx.tetris.block.Block;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class View_Controller implements Initializable {
	
	private static final int BRICK_SIZE = 20;		
	
	@FXML
	private GridPane gamePanel;
	
	@FXML
	private GridPane blockPanel;
	
	@FXML
	private Button newGameButton;
	
	@FXML
    private GameOverPanel gameOverPanel;
	
	private Game_Controller game_ctr;
	boolean isKey = false;
	
	Rectangle[][] gameRects;
	Rectangle[][] blockRects;
	Timeline timeline;
	
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
//					while(true){
//						currBlock = game_ctr.downEvent();
//						if(currBlock==null) break;
//						else setBlockPanel(currBlock);
//					}
					do{
						currBlock = game_ctr.downEvent();
						setBlockPanel(currBlock);
					}while(currBlock!=null);
					event.consume();
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
		
		gameOverPanel.setPrefWidth(gamePanel.getLayoutX()+(gamePanel.getHgap()*width+1)+(BRICK_SIZE*width));
		gameOverPanel.setPrefHeight((gamePanel.getLayoutY()+(gamePanel.getVgap()*height+1)+(BRICK_SIZE*height)));
		//gameOverPanel.setStyle("-fx-border-color:white; -fx-border-width:1px;");
		
		//blockPanel.setLayoutX(gamePanel.getLayoutX()+((gamePanel.getHgap())*(4-1))+(BRICK_SIZE*(4-1)));
		//blockPanel.setLayoutY(gamePanel.getLayoutY()+((gamePanel.getVgap())*(-1-1))+(BRICK_SIZE*(-1-1)));
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
			//System.out.println("unable to rotate");
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
	public boolean refreshGamePanel(Block currBlock) {
		for(int i=0; i<currBlock.getCurrPoint().length; i++){
			int x = currBlock.getCurrPoint()[i].getX();
			int y = currBlock.getCurrPoint()[i].getY();
			//ȭ�鿡 �������� ���� ������ ���� ��ĥ����
			if(y>=0 && gameRects[y][x].getFill().equals(Color.TRANSPARENT)){
				gameRects[y][x].setFill(currBlock.color);
			}
		}
		for(int i=0; i<currBlock.getCurrPoint().length; i++){
			int y = currBlock.getCurrPoint()[i].getY();
			if(y<0){
				gameOver();
				return false;
			}
		}
		return true;
	}
	
	
	public void setBlockPanel(Block currBlock) {
		if(currBlock==null){
			//System.out.println("unable to move");
			return;
		}else{
			blockPanel.setLayoutX(gamePanel.getLayoutX()+(gamePanel.getHgap()*currBlock.offset.getX())+(BRICK_SIZE*(currBlock.offset.getX()-1)));
			blockPanel.setLayoutY(gamePanel.getLayoutY()+(gamePanel.getVgap()*currBlock.offset.getY())+(BRICK_SIZE*(currBlock.offset.getY()-1)));
		}
	}
	
	public void startGame(int height, int width){
		//�ʿ��� ��� timeline���� �� �׷��� ó���� �ٽý��� �ؾ��Ѵ�.
		gameOverPanel.setVisible(false);
		gamePanel.requestFocus();		//��Ŀ���� �����гο� ����
		isKey = true;					//Ű�Է� ���
		//��׶��� ��ü ����
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				gameRects[i][j].setFill(Color.TRANSPARENT);
			}
		}
		
		if((timeline==null) || (timeline.getStatus()==Status.STOPPED)){
			timeline = new Timeline(new KeyFrame(
	              Duration.millis(500),
	              event -> setBlockPanel(game_ctr.downEvent())
		    ));
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
		}
	}
	
	public void gameOver(){
		timeline.stop();
		//���� �г� ���
		gameOverPanel.gameOverLabel.setText("GAME OVER");
		gameOverPanel.setVisible(true);
		
		isKey = false;
		System.out.println("���� ����");
	}
	
	public void deleteLine(Queue<Paint[]> keepRows, Queue<Integer> delRows, int width, int height){
		timeline.stop();

		//�ٻ��� ȿ�� ���� ����
//		for(int i=height-1; i>=0; i--){
//			if(!delRows.isEmpty() && (delRows.poll()==i)){
//				for(int j=0; j<width; j++)
//					gameRects[i][j].setFill(Color.TRANSPARENT);
//			}
//		}

		for(int i=height-1; i>=0; i--){
			Paint[] newRows = keepRows.poll();		
			for(int j=0; j<width; j++)
				if(newRows!=null) gameRects[i][j].setFill(newRows[j]);
				else gameRects[i][j].setFill(Color.TRANSPARENT);
		}
		
		timeline.play();
	}
}
