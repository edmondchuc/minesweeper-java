package com.edmondchuc.minesweeper;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The controller of the score object. It uses a timer.
 */
public class ScoreController {

    // game timer for user's score
    private int score = 0;
    private boolean isRunning = true;

    public ScoreController(ScoreView scoreView, BoardModel boardModel) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    // Make sure this timer is stopped
                    if(isRunning) {
                        score++;
                        System.out.println(score);

                        scoreView.update(score);

                    } else {
                        scoreView.delete();
                        timer.cancel();
                        timer.purge(); // use this to end the threads once the application is closed.
                    }
                });
            }
        }, new Date(), 1000);
    }

    public int getScore() {
        return score;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

}
