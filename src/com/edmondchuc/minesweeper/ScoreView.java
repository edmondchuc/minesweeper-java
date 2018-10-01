package com.edmondchuc.minesweeper;


import javafx.collections.ObservableList;
import javafx.scene.text.Text;



public class ScoreView {
    private Text textScore;

    public ScoreView(ObservableList list) {
        textScore = new Text("");
        list.add(textScore);
    }

//    public int getScore() {
//        return score;
//    }

    public void update(int score) {
        textScore.setText("Score: " + score);
        textScore.toFront();
        textScore.setX(120);
        textScore.setY(100);
    }

    public void delete() {
        this.textScore.setText("");
    }
}
