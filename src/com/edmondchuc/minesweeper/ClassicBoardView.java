package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClassicBoardView extends BoardView {

    public ClassicBoardView(ObservableList list, int boardSize, Stage primaryStage) {
        super(list, boardSize, primaryStage);

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
