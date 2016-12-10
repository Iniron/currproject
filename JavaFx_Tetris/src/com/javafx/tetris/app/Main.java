package com.javafx.tetris.app;

import com.javafx.tetris.controller.Game_Controller;
import com.javafx.tetris.controller.View_Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	public Game_Controller g;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Layout.fxml"));	//fxmlloader 생성
	    Parent root = fxmlLoader.load();												//fxml을 로드해 root를 설정한다.
	    View_Controller v_controller = fxmlLoader.getController();						//controller객체를 가져온다.	
	 	    
		Scene scene = new Scene(root, 340, 540);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tetris Game");
		primaryStage.show();
		
		g = new Game_Controller(v_controller);	//게임 컨트롤러 생성
		v_controller.setGameController(g);		//뷰컨트롤러에 게임컨트롤러 객체 전달
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
