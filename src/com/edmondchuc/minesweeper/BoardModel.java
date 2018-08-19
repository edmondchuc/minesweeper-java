package com.edmondchuc.minesweeper;

public class BoardModel {
    CellContext[][] cells;

    public BoardModel(int boardSize) {
        cells = new CellContext[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                cells[i][j] = new CellContext();
            }
        }
    }

    public void onMouseEntered(int i, int j) {
        cells[i][j].setStateHoverEnter();
    }

    public void onMouseExited(int i, int j) {
        cells[i][j].setStateHoverExit();
    }
}
