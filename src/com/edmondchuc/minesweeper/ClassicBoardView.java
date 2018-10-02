package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Manages the Classic mode's board view
 */
public class ClassicBoardView extends BoardView {

    /**
     * Constructor
     * @param list observable list
     * @param boardSize size of board
     * @param primaryStage JavaFX Stage
     * @param scoreController The score controller object
     */
    public ClassicBoardView(ObservableList list, int boardSize, Stage primaryStage, ScoreController scoreController) {
        super(list, boardSize, primaryStage, scoreController);

        double length = 40;//sizeOfCell/boardSize;
        int col = 0;
        int row = 0;
        for(int i = 0; i < n; i++) {

            // cell view
            cells[i] = new CellView(i, col, row, length, GameMode.CLASSIC);
            cells[i].getPoints().addAll(new Double[]{


                    length * col, length * row + 128,
                    length * col + length, length * row + 128,
                    length * col + length, length * row + 128 + length,
                    length * col, length * row + 128 + length
            });
//            cells[i].setX(length * col);
//            cells[i].setY(length * row + 128);
//            cells[i].setHeight(length);
//            cells[i].setWidth(length);
            cells[i].setStroke(Color.DARKSLATEBLUE);
            cells[i].setFill(cellDefault);
            list.add(cells[i]);
            list.add(cells[i].getText());

            col++;
            if(col >= boardSize) {
                row++;
                col = 0;
            }
        }
    }
}
