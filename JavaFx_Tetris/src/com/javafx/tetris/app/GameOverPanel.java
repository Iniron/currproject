package com.javafx.tetris.app;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameOverPanel extends BorderPane {						//BorderPane을 상속

	public Label gameOverLabel;
	
    public GameOverPanel() {
        gameOverLabel = new Label("TETRIS");			//GAME OVER라는 Label생성
        gameOverLabel.getStyleClass().add("gameOverLabel");			//CSS적용
        gameOverLabel.prefWidthProperty().bind(this.widthProperty());
        setCenter(gameOverLabel);									//Label을 borderPane의 가운데 위치시킨다.
    }
}
