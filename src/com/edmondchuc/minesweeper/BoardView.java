package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardView {

    Image cellDefault;
    Image cellHover;
    Image cellDownEmpty;
    Image cellDownBomb;

    CellView[][] cells;

    int boardSize;

    public BoardView(ObservableList list, int boardSize) throws FileNotFoundException {

        cellDefault = new Image(new FileInputStream("assets" + File.separator + "Cell.png"));
        cellHover = new Image(new FileInputStream("assets" + File.separator + "CellOver.png"));
        cellDownEmpty = new Image(new FileInputStream("assets" + File.separator + "CellDown.png"));
        cellDownBomb = new Image(new FileInputStream("assets" + File.separator + "ExplodedMineCell.png"));

        cells = new CellView[boardSize][boardSize];

        this.boardSize = boardSize;

        // size of cell image length in pixels
        int sizeOfCell = 512;
        double length = sizeOfCell/boardSize;

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                // cell view
                cells[i][j] = new CellView(i, j, cellDefault);
                cells[i][j].imageView.setX(length * j);
                cells[i][j].imageView.setY(length * i + 128);
                cells[i][j].imageView.setFitHeight(length);
                cells[i][j].imageView.setFitWidth(length);
                list.add(cells[i][j].imageView);
            }
        }
    }

    public void setEvents(GameController gameController) {
        for(int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j].setEvents(gameController);
            }
        }
    }

    public void setView(int i, int j, BoardModel model) {
        if(model.cells[i][j].getState().getClass() == CellDefault.class) {
            cells[i][j].imageView.setImage(cellDefault);
        }
        else if(model.cells[i][j].getState().getClass() == CellHover.class) {
            cells[i][j].imageView.setImage((cellHover));
        }
        else if(model.cells[i][j].getState().getClass() == CellRevealedBomb.class) {
            cells[i][j].imageView.setImage(cellDownBomb);
        }
        else if(model.cells[i][j].getState().getClass() == CellRevealedEmpty.class) {
            cells[i][j].imageView.setImage(cellDownEmpty);
        }
    }
}
