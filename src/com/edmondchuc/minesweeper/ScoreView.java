package com.edmondchuc.minesweeper;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ScoreView {

    // game timer for user's score
    private int score = 0;
    private Text textScore;

    public ScoreView(ObservableList list) {
        textScore = new Text("");
        list.add(textScore);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    // Make sure this timer is stopped
                    if(!BoardView.gameOver && !BoardView.win) {
                        score++;
                        System.out.println(score);
                        textScore.setText("Score: " + score);
                        textScore.toFront();
                        textScore.setX(120);
                        textScore.setY(100);
                    } else {
                        timer.cancel();
                    }
                });
            }
        }, new Date(), 1000);
    }

    public Text getTextScore() {
        return textScore;
    }

    public int getScore() {
        return score;
    }
}
