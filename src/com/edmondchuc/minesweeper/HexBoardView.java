package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Manages the board view for the hex game mode
 */
public class HexBoardView extends BoardView {

    /**
     * Constructor
     * @param list Observable list
     * @param boardSize the board size
     * @param primaryStage The JavaFX Stage object
     * @param scoreController The score controller object
     */
    public HexBoardView(ObservableList list, int boardSize, Stage primaryStage, ScoreController scoreController) {
        super(list, boardSize, primaryStage, scoreController);

        double length = 50;//sizeOfCell/boardSize;
        int col = 0;
        int row = 0;
        int offSet = 20;
        double a = (float)length/4;
        double b = Math.sqrt(3) * a;

        for(int i = 0; i < n; i++) {

            // cell view
            cells[i] = new CellView(i, col, row, length, GameMode.HEX);

            // DRAW HEXAGONS
            cells[i].getPoints().addAll(new Double[]{
                    .0, -2.0 * a,
                    b, -a,
                    b, a,
                    0.0, 2.0 * a,
                    -b, a,
                    -b, -a

            });
            //TODO: Fix the positioning and offset of this.
            ///TODO: Fix the window size when starting a hex game
            //TODO: Refactor BoardView for classic and hex
            //TODO: Add neighbour calculations for hex

//            if(row == 0) {
//                cells[i].setTranslateX(length * col + 50 + offSet);
//                cells[i].setTranslateY(length * row + 128);
//            }

            if(row % 2 == 0) {
                cells[i].setTranslateX(length * col + 50 + offSet);
                cells[i].setTranslateY(length * row + 150);
            }
            else {
                cells[i].setTranslateX(length * col + 50);
                cells[i].setTranslateY(length * row + 150);
            }






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
