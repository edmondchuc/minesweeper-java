package com.edmondchuc.minesweeper;


import javafx.collections.ObservableList;
import javafx.scene.text.Text;


/**
 * Manages the view of the score.
 */
public class ScoreView {
    private Text textScore;

    /**
     * Constructor
     * @param list The JavaFX ObservableList to render to the window.
     */
    public ScoreView(ObservableList list) {
        textScore = new Text("");
        list.add(textScore);
    }

    /**
     * Update the text to reflect the new score.
     * @param score The score to be shown.
     */
    public void update(int score) {
        textScore.setText("Score: " + score);
        textScore.toFront();
        textScore.setX(120);
        textScore.setY(100);
    }

    /**
     * Set the score to no longer be visible in the GUI.
     */
    public void delete() {
        this.textScore.setText("");
    }
}
