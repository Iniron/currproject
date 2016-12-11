package com.javafx.tetris.app;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameOverPanel extends BorderPane {						//BorderPane�� ���

	public Label gameOverLabel;
	
    public GameOverPanel() {
        gameOverLabel = new Label("TETRIS");			//GAME OVER��� Label����
        gameOverLabel.getStyleClass().add("gameOverLabel");			//CSS����
        gameOverLabel.prefWidthProperty().bind(this.widthProperty());
        setCenter(gameOverLabel);									//Label�� borderPane�� ��� ��ġ��Ų��.
    }
}
