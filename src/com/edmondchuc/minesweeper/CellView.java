package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class CellView extends StackPane{
    private int i;
    private int j;
    public ImageView imageView;
    public Text text;

    public CellView(int i, int j, Image cellDefault, double length) {
        this.i = i;
        this.j = j;
        imageView = new ImageView(cellDefault);

        // text for the neighbouring bombs
        text = new Text(length * j + 20, length * i + 170, "3");
        text.setFont(new Font(20));
        text.setText("10");
    }

    public void setEvents(GameController gameController) {
        imageView.setOnMouseEntered(event -> {
            gameController.onMouseEntered(this.i, this.j);
        });

        imageView.setOnMouseExited(event -> {
            gameController.onMouseExited(this.i, this.j);
        });

        imageView.setOnMousePressed(event -> {
            gameController.onMouseLeftClicked(this.i, this.j);
        });
    }

    public int getJ() {
        return i;
    }

    public int getI() {
        return j;
    }
}
