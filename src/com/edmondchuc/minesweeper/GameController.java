package com.edmondchuc.minesweeper;

public class GameController {
    BoardModel model;
    BoardView view;

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
    }
}
