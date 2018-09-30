package com.edmondchuc.minesweeper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardModel {
    CellContext[] cells;
    boolean gameOver = false;
    int revealedCount;
    int bombCount;

    public BoardModel(int boardSize, int gameMode, int difficulty) {

        int n = boardSize*boardSize;
        cells = new CellContext[n];

        // set the number of bombs to have
        if(difficulty == GameMode.EASY) { bombCount = 10; }
        if(difficulty == GameMode.MEDIUM) { bombCount = 15; }
        if(difficulty == GameMode.HARD) { bombCount = 20; }

        // set the revealedCount to check if the win condition has been met
        revealedCount = n - bombCount;

        // loop through the board and assign bombs - used for TESTING - static bombs
//        for(int i = 0; i < n; i++) {
//            if(i == 0 || i == 17 || i == 18 || i == 19 || i == 24 || i == 39 || i == 40 || i == 45 || i == 59 || i == 62) {
//                cells[i] = new CellContext(true);
//            }
//            else {
//                cells[i] = new CellContext(false);
//            }
//        }

        for(int i = 0; i < n; i++) {
            cells[i] = new CellContext(false);
        }

        // assign bombs
        Random rand = new Random();
        for(int i = 0; i < bombCount; i++) {
            int id;
            do {
                id = rand.nextInt(n);
            } while(cells[id].isBomb());
            cells[id].setBomb();
        }

        // set the neighbours of each cell if they don't contain a bomb
        if(gameMode == GameMode.CLASSIC) {
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
                    if(botLeft < n && cell % boardSize != 0) {
                        neighbours.add(cells[botLeft]);
                    }

                    int bot = cell + boardSize;
                    if(bot <  n) {
                        neighbours.add(cells[bot]);
                    }

                    int botRight = cell + boardSize + 1;
                    if(botRight < n && cell % boardSize != boardSize-1) {
                        neighbours.add(cells[botRight]);
                    }
                    cells[i].setNeighbours(neighbours);
                }
            }
        }
        else if(gameMode == GameMode.HEX) {
            for(int i = 0; i < n; i++) {
                if (!cells[i].isBomb()) {
                    List neighbours = new ArrayList();
                    int cell = i;
                    int row = cell/boardSize;

                    // TOP FOR EVEN/ODD ROW
                    if(row % 2 == 0) {
                        int topRight = cell - boardSize + 1;
                        if(topRight >= 0 && cell % boardSize != boardSize-1) {
                            neighbours.add(cells[topRight]);
                        }
                    }
                    else {
                        int topLeft = cell - boardSize - 1;
                        if(topLeft >= 0 && cell % boardSize != 0) {
                            neighbours.add(cells[topLeft]);
                        }
                    }

                    // TOP
                    int top = cell - boardSize;
                    if(top >= 0) {
                        neighbours.add(cells[top]);
                    }

                    // LEFT
                    int left = cell - 1;
                    if(cell % boardSize != 0) {
                        neighbours.add(cells[left]);
                    }

                    // RIGHT
                    int right = cell + 1;
                    if(cell % boardSize != boardSize-1) {
                        neighbours.add(cells[right]);
                    }

                    // BOT
                    int bot = cell + boardSize;
                    if(bot < n) {
                        neighbours.add(cells[bot]);
                    }

                    // BOT FOR EVEN/ODD ROW
                    if(row % 2 == 0) {
                        int botRight = cell + boardSize + 1;
                        if(botRight < n && cell % boardSize != boardSize-1) {
                            neighbours.add(cells[botRight]);
                        }
                    }
                    else {
                        int botLeft = cell + boardSize - 1;
                        if(botLeft < n && cell % boardSize != 0) {
                            neighbours.add(cells[botLeft]);
                        }
                    }
                    cells[i].setNeighbours(neighbours);
                }
            }
        }

        // set the bomb neighbours in each cell
        for(int i = 0; i < n; i++) {
            cells[i].setBombNeighbours();
        }


    }

    public int getRevealedCount() {
        return revealedCount;
    }

    private void setGameOver() {
        gameOver = true;

        // reveal mine field
        for(int i = 0; i < Array.getLength(cells); i++) {
            cells[i].setStateHoverEnter();
            cells[i].setStateLeftClick();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void onMouseEntered(int i) {
        cells[i].setStateHoverEnter();
    }

    public void onMouseExited(int i) {
        cells[i].setStateHoverExit();
    }

    public void onMouseLeftClicked(int i) {
        cells[i].setStateLeftClick();
        if(cells[i].isBomb()) {
            setGameOver();
        } else {
            revealedCount--;
        }
    }
}
