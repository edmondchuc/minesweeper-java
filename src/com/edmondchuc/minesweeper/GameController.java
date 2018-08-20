package com.edmondchuc.minesweeper;

public class GameController {
    BoardModel model;
    BoardView view;

    public GameController(BoardModel model, BoardView view) {
        this.model = model;
        this.view = view;
        view.setEvents(this);
    }

    public void onMouseEntered(int i, int j) {
        model.onMouseEntered(i, j);
        view.setView(model);
    }

    public void onMouseExited(int i, int j) {
        model.onMouseExited(i, j);
        view.setView(model);
    }

    public void onMouseLeftClicked(int i, int j) {
        model.onMouseLeftClicked(i, j);
        view.setView(model);
    }
}
