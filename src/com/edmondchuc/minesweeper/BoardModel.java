package com.edmondchuc.minesweeper;

public class BoardModel {
    CellContext[][] cells;

    public BoardModel(int boardSize) {
        cells = new CellContext[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(i == 0 && j == 0 ||
                        i == 2 && j == 1 ||
                        i == 2 && j == 2 ||
                        i == 2 && j == 3 ||
                        i == 3 && j == 0 ||
                        i == 4 && j == 7 ||
                        i == 5 && j == 0 ||
                        i == 5 && j == 5 ||
                        i == 7 && j == 3 ||
                        i == 7 && j == 6) {
                    cells[i][j] = new CellContext(true);
                }
                else {
                    cells[i][j] = new CellContext(false);
                }
            }
        }
    }

    public void onMouseEntered(int i, int j) {
        cells[i][j].setStateHoverEnter();
    }

    public void onMouseExited(int i, int j) {
        cells[i][j].setStateHoverExit();
    }

    public void onMouseLeftClicked(int i, int j) {
        cells[i][j].setStateLeftClick();
    }
}
