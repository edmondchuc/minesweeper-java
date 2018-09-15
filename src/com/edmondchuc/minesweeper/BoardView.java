package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardView {

    Image cellDefault;
    Image cellHover;
    Image cellDownEmpty;
    Image cellDownBomb;

    Image cellOne;
    Image cellTwo;
    Image cellThree;
    Image cellFour;
    Image cellFive;
    Image cellSix;
    Image cellSeven;
    Image cellEight;

    CellView[][] cells;

    int boardSize;

    public BoardView(ObservableList list, int boardSize) throws FileNotFoundException {

//        String dirPath = "assets" + File.separator + "original" + File.separator;
        String dirPath = "assets" + File.separator;
//        cellDefault = new Image(new FileInputStream( dirPath + "cell-default.png"));
//        cellHover = new Image(new FileInputStream(dirPath + "cell-hover.png"));
//        cellDownEmpty = new Image(new FileInputStream(dirPath + "cell-down.png"));
//        cellDownBomb = new Image(new FileInputStream(dirPath + "cell-bomb-hit.png"));
        cellDefault = new Image(new FileInputStream( dirPath + "Cell.png"));
        cellHover = new Image(new FileInputStream(dirPath + "CellOver.png"));
        cellDownEmpty = new Image(new FileInputStream(dirPath + "CellDown.png"));
        cellDownBomb = new Image(new FileInputStream(dirPath + "ExplodedMineCell.png"));



        cells = new CellView[boardSize][boardSize];

        this.boardSize = boardSize;

        // size of cell image length in pixels
        int sizeOfCell = 512;
        double length = sizeOfCell/boardSize;

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                // cell view
                cells[i][j] = new CellView(i, j, cellDefault, length);
                cells[i][j].imageView.setX(length * j);
                cells[i][j].imageView.setY(length * i + 128);
                cells[i][j].imageView.setFitHeight(length);
                cells[i][j].imageView.setFitWidth(length);
                list.add(cells[i][j].imageView);

                list.add(cells[i][j].text);
            }
        }

//        Image test = new Image(new FileInputStream("assets" + File.separator + "original" + File.separator + "cell-bomb-hit.png"));
//        ImageView viewTest = new ImageView(test);
//        viewTest.setX(200);
//        viewTest.setY(200);
//        viewTest.setFitHeight(length);
//        viewTest.setFitWidth(length);
//        list.add(viewTest);


    }

    public void setEvents(GameController gameController) {
        for(int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j].setEvents(gameController);
            }
        }
    }

    public void setView(BoardModel model) {
        for(int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
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
                    cells[i][j].setBombText(model.cells[i][j].getNumOfNeighboursIsBomb());
                }
            }
        }
    }
}
