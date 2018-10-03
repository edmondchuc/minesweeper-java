package com.edmondchuc.minesweeper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Manages the board data of the game.
 */
public class BoardModel {
    CellContext[] cells;
    boolean gameOver = false;
    boolean win = false;
    int revealedCount;
    int bombCount;
    final int winCount;
    int flagCount;

    /**
     * Constructor
     * @param boardSize The board size.
     * @param gameMode The game mode.
     * @param difficulty The difficulty.
     */
    public BoardModel(int boardSize, int gameMode, int difficulty) {
        // set the global difficulty and game mode
        GameMode.currentGameMode = gameMode;
        GameMode.currentDifficulty = difficulty;

        int n = boardSize*boardSize;
        cells = new CellContext[n];

        // set the number of bombs to have
        if(difficulty == GameMode.EASY) { bombCount = 10; }
        else if(difficulty == GameMode.MEDIUM) { bombCount = 12; }
        else if(difficulty == GameMode.HARD) { bombCount = 15; }
        else { throw new java.lang.Error("Invalid game difficulty"); }

        // set the revealedCount to check if the win condition has been met
        revealedCount = 0;
        winCount = n - bombCount;

        flagCount = bombCount;

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

    /**
     * Get the number of cells that have been revealed.
     * @return The number of cells revealed.
     */
    public int getRevealedCount() {
        return revealedCount;
    }

    /**
     * Set the game state to be "game over".
     */
    private void setGameOver() {
        gameOver = true;

        // reveal mine field
        for(int i = 0; i < Array.getLength(cells); i++) {
            if(cells[i].getState().getClass() == CellDefault.class) {
                cells[i].setStateHoverEnter();
                cells[i].setStateLeftClick();
            } else if(cells[i].getState().getClass() == CellFlagged.class) {
                cells[i].setStateRightClick();
                cells[i].setStateHoverEnter();
                cells[i].setStateLeftClick();
            }
        }
    }

    /**
     * Get the win state.
     * @return The win state.
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Get the game over state.
     * @return The game over state.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Get the number of flags available.
     * @return The flag count.
     */
    public int getFlagCount() {
        return flagCount;
    }

    /**
     * Mouse hover action.
     * @param i The cell identifier.
     */
    public void onMouseEntered(int i) {
        cells[i].setStateHoverEnter();
    }

    /**
     * Mouse exited action..
     * @param i The cell identifier.
     */
    public void onMouseExited(int i) {
        cells[i].setStateHoverExit();
    }

    /**
     * Mouse left clicked action.
     * @param i The cell identifier.
     */
    public void onMouseLeftClicked(int i) {
        cells[i].setStateLeftClick();
        if(cells[i].isBomb()) {
            setGameOver();
        } else {
            // loop through the arrays and get the number of revealed empty cells
            revealedCount = 0;
            for(int j = 0; j < cells.length; j++) {
                if(cells[j].getState() instanceof CellRevealedEmpty) {
                    revealedCount++;
                }
            }
            if(revealedCount == winCount) {
                win = true;
            }
        }
    }

    /**
     * Mouse right clicked action.
     * @param i The cell identifier.
     */
    public void onMouseRightClicked(int i) {
        if(flagCount == 0 && cells[i].getState().getClass() == CellFlagged.class || flagCount > 0) {
            cells[i].setStateRightClick();

            // if it has changed to flagged, then minus flagCount
            // else, add flagCount
            if(cells[i].getState() instanceof CellFlagged) {
                flagCount--;
            } else  if(cells[i].getState() instanceof CellDefault) {
                flagCount++;
            } else {
                throw new java.lang.Error("Invalid state for mouse right click. " + cells[i].getState().toString());
            }
        }



    }
}
