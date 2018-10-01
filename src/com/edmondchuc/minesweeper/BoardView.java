package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardView {

    Paint cellDefault = Color.LIGHTBLUE;
    Paint cellHover = Color.GREENYELLOW;
    Paint cellDownEmpty = Color.ALICEBLUE;
    Paint cellDownBomb = Color.RED;
    Paint cellFlag = Color.YELLOW;

    CellView[] cells;

    int boardSize;
    int n; // size of list of cells
    Stage primaryStage;

    static boolean gameOver;
    static boolean win;

    Text textFlagCount;

    public BoardView(ObservableList list, int boardSize, Stage primaryStage) {
        this.gameOver = false;
        this.win = false;

        this.primaryStage = primaryStage;

        this.boardSize = boardSize;
        this.n = boardSize*boardSize;
        cells = new CellView[n];

        // size of cell image length in pixels

        textFlagCount = new Text("");
        textFlagCount.toFront();
        textFlagCount.setX(30);
        textFlagCount.setY(100);
        list.add(textFlagCount);


        // main menu button
        Button startGame = new Button("Main Menu");
        list.add(startGame);
        startGame.setOnMouseClicked(e -> {
            GameMode gameMode = new GameMode(primaryStage);
        });

    }

    public void setEvents(GameController gameController) {
        for(int i = 0; i < n; i++) {
            cells[i].setEvents(gameController);
        }
    }

    public void setView(BoardModel model) {

        // set the flag count
        textFlagCount.setText("Bombs: " + Integer.toString(model.getFlagCount()));

        for(int i = 0; i < n; i++) {
            if(model.cells[i].getState().getClass() == CellDefault.class) {
                cells[i].setFill(cellDefault); //.imageView.setImage(cellDefault);
            }
            else if(model.cells[i].getState().getClass() == CellHover.class) {
                cells[i].setFill(cellHover); //imageView.setImage((cellHover));
            }
            else if(model.cells[i].getState().getClass() == CellRevealedBomb.class) {
                cells[i].setFill(cellDownBomb); //imageView.setImage(cellDownBomb);
            }
            else if(model.cells[i].getState().getClass() == CellRevealedEmpty.class) {
                cells[i].setFill(cellDownEmpty); //imageView.setImage(cellDownEmpty);
                cells[i].setBombText(model.cells[i].getNumOfNeighboursIsBomb());
            }
            else if(model.cells[i].getState().getClass() == CellFlagged.class) {
                cells[i].setFill(cellFlag);
            }
        }

        // if game lose, show popup to notify user
        if(model.isGameOver()) {
            this.gameOver = true;
            if(this.gameOver) {
                Stage dialog = new Stage();
                dialog.initOwner(primaryStage);
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setAlwaysOnTop(true);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("You have clicked on a mine! Game over!"));
                Scene dialogScene = new Scene(dialogVbox);
                dialog.setScene(dialogScene);
                dialog.showAndWait();
            }
        }

        if(model.isWin()) {
            this.win = true;
            if(this.win) {
                Stage dialog = new Stage();
                dialog.initOwner(primaryStage);
                dialog.initModality(Modality.WINDOW_MODAL);
                dialog.setAlwaysOnTop(true);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("You win!"));
                Scene dialogScene = new Scene(dialogVbox);
                dialog.setScene(dialogScene);
                dialog.showAndWait();
            }
        }
    }
}
