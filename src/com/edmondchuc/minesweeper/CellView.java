package com.edmondchuc.minesweeper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellView {
    private int i;
    private int j;
    public ImageView imageView;

    public CellView(int i, int j, Image cellDefault) {
        this.i = i;
        this.j = j;
        imageView = new ImageView(cellDefault);
    }

    public void setOnMouseEntered(GameController gameController) {
        imageView.setOnMouseEntered(event -> {
            gameController.onMouseEntered(this.i, this.j);
        });
    }

    public int getJ() {
        return i;
    }

    public int getI() {
        return j;
    }
}