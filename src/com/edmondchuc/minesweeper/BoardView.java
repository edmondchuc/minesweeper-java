package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardView {

    Paint cellDefault = Color.LIGHTBLUE;
    Paint cellHover = Color.GREENYELLOW;
    Paint cellDownEmpty = Color.ALICEBLUE;
    Paint cellDownBomb = Color.RED;

    CellView[] cells;

    int boardSize;
    int n; // size of list of cells

    public BoardView(ObservableList list, int boardSize, Stage primaryStage) {

        this.boardSize = boardSize;
        this.n = boardSize*boardSize;
        cells = new CellView[n];

        // size of cell image length in pixels
        double length = 40;//sizeOfCell/boardSize;

        int col = 0;
        int row = 0;
        for(int i = 0; i < n; i++) {

            // cell view
            cells[i] = new CellView(i, col, row, length);
            cells[i].setX(length * col);
            cells[i].setY(length * row + 128);
            cells[i].setHeight(length);
            cells[i].setWidth(length);
            cells[i].setStroke(Color.GREENYELLOW);
            cells[i].setFill(cellDefault);
//                cells[i][j].imageView.setFitHeight(length);
//                cells[i][j].imageView.setFitWidth(length);
            list.add(cells[i]);

            list.add(cells[i].text);

            col++;
            if(col >= boardSize) {
                row++;
                col = 0;
            }
        }


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
        }
    }
}
