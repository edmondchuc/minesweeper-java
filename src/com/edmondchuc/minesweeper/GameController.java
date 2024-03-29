package com.edmondchuc.minesweeper;

import javafx.scene.text.Text;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The game controller
 */
public class GameController {
    BoardModel model;
    BoardView view;

    /**
     * Constructor
     * @param model The board model object
     * @param view The view model object
     */
    public GameController(BoardModel model, BoardView view) {
        this.model = model;
        this.view = view;
        view.setEvents(this);
    }

    public void onMouseEntered(int i) {
        model.onMouseEntered(i);
        view.setView(model);
    }

    public void onMouseExited(int i) {
        model.onMouseExited(i);
        view.setView(model);
    }

    public void onMouseLeftClicked(int i) {
        model.onMouseLeftClicked(i);
        view.setView(model);
        view.checkWin(model);
    }

    public void onMouseRightClicked(int i) {
        model.onMouseRightClicked(i);
    }
}
