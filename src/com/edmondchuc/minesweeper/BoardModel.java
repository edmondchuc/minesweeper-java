package com.edmondchuc.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class BoardModel {
    CellContext[] cells;

    public BoardModel(int boardSize) {
        int n = boardSize*boardSize;
        cells = new CellContext[n];
        for(int i = 0; i < n; i++) {
            if(i == 0 || i == 17 || i == 18 || i == 19 || i == 24 || i == 39 || i == 40 || i == 45 || i == 59 || i == 62) {
                cells[i] = new CellContext(true);
            }
            else {
                cells[i] = new CellContext(false);
            }
        }

        // set the neighbours of each cell if they don't contain a bomb
        for(int i = 0; i < n; i++) {
            if(!cells[i].isBomb()) {
                List neighbours = new ArrayList();
                int cell = i;

                // TOP
                int topLeft = cell - boardSize - 1;
                if(topLeft >= 0 && cell % boardSize != 0) {
                    neighbours.add(cells[topLeft]);
                }

                int top = cell - boardSize;
                if(top >= 0) {
                    neighbours.add(cells[top]);
                }

                int topRight = cell - boardSize + 1;
                if(topRight > 0 && cell % boardSize != boardSize-1) {
                    neighbours.add(cells[topRight]);
                }

                // SIDES
                int left = cell - 1;
                if(cell % boardSize != 0) {
                    neighbours.add(cells[left]);
                }

                int right = cell + 1;
                if(cell % boardSize != boardSize-1) {
                    neighbours.add(cells[right]);
                }

                // BOTTOM
                int botLeft = cell + boardSize - 1;
                if(botLeft < n-1 && cell % boardSize != 0) {
                    neighbours.add(cells[botLeft]);
                }

                int bot = cell + boardSize;
                if(bot <  n-1) {
                    neighbours.add(cells[bot]);
                }

                int botRight = cell + boardSize + 1;
                if(botRight < n-1 && cell % boardSize != boardSize-1) {
                    neighbours.add(cells[botRight]);
                }


                cells[i].setNeighbours(neighbours);
            }

        }
    }

    public void onMouseEntered(int i) {
        cells[i].setStateHoverEnter();
    }

    public void onMouseExited(int i) {
        cells[i].setStateHoverExit();
    }

    public void onMouseLeftClicked(int i) {
        cells[i].setStateLeftClick();
    }
}
