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
		
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Layout.fxml"));	//fxmlloader ����
	    Parent root = fxmlLoader.load();												//fxml�� �ε��� root�� �����Ѵ�.
	    View_Controller v_controller = fxmlLoader.getController();						//controller��ü�� �����´�.	
	 	    
		Scene scene = new Scene(root, 340, 540);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tetris Game");
		primaryStage.show();
		
		g = new Game_Controller(v_controller);	//���� ��Ʈ�ѷ� ����
		v_controller.setGameController(g);		//����Ʈ�ѷ��� ������Ʈ�ѷ� ��ü ����
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
