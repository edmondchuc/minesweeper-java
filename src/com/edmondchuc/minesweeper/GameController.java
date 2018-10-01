package com.edmondchuc.minesweeper;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    BoardModel model;
    BoardView view;

    // game timer for user's score
    int score = 0;

    public GameController(BoardModel model, BoardView view) {
        this.model = model;
        this.view = view;
        view.setEvents(this);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!BoardView.gameOver && !BoardView.win) {
                    score++;
                    System.out.println(score);
                } else {
                    timer.cancel();
                }
            }
        }, new Date(), 1000);
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

    public void onMouseRightClicked(int i) {
        model.onMouseRightClicked(i);
    }
}
