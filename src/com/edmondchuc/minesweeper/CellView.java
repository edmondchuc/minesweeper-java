package com.edmondchuc.minesweeper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class CellView extends Rectangle {
    private int i;
//    public ImageView imageView;
    public Text text;

    public CellView(int i, int col, int row, double length) {
        this.i = i;
//        imageView = new ImageView(cellDefault);

        // text for the neighbouring bombs
        text = new Text(length * col + 20, length * row + 170, "3");
        text.setFont(new Font(20));
        text.setText("");
    }

    public void setBombText(int bombs) {
        if(bombs != 0) {
            text.setText(Integer.toString(bombs));
        }
    }

    public void setEvents(GameController gameController) {
        this.setOnMouseEntered(event -> {
            gameController.onMouseEntered(this.i);
        });

        this.setOnMouseExited(event -> {
            gameController.onMouseExited(this.i);
        });

        this.setOnMousePressed(event -> {
            gameController.onMouseLeftClicked(this.i);
        });
    }

}
