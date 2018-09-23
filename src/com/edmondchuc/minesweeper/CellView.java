package com.edmondchuc.minesweeper;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

public class CellView extends Polygon {
    protected int i;
    protected Text text;

    public CellView(int i, int col, int row, double length) {
        this.i = i;
//        imageView = new ImageView(cellDefault);

        // text for the neighbouring bombs
        text = new Text(length * col + 27, length * row + 164, "3");
        text.setFont(new Font(20));
        text.setText("");
    }

    public Text getText() {
        return text;
    }

    public void setBombText(int bombs) {
        switch(bombs) {
            case 0:
                // show nothing
                break;
            case 1:
                text.setText(Integer.toString(bombs));
                text.setFill(Color.BLUE);
                break;
            case 2:
                text.setText(Integer.toString(bombs));
                text.setFill(Color.GREEN);
                break;
            case 3:
                text.setText(Integer.toString(bombs));
                text.setFill(Color.RED);
                break;
            case 4:
                text.setText(Integer.toString(bombs));
                text.setFill(Color.DARKBLUE);
                break;
            case 5:
                text.setText(Integer.toString(bombs));
                text.setFill(Color.DARKRED);
                break;
            case 6:
                text.setText(Integer.toString(bombs));
                text.setFill(Color.DARKCYAN);
                break;
            case 7:
                text.setText(Integer.toString(bombs));
                text.setFill(Color.BLACK);
                break;
            case 8:
                text.setText(Integer.toString(bombs));
                text.setFill(Color.GRAY);
                break;
            default:
                throw new java.lang.Error("Invalid value for bombs: " + bombs);
        }
    }

    public void setEvents(GameController gameController) {
        this.setOnMouseEntered(event -> {
            if(!BoardView.gameOver) {
                gameController.onMouseEntered(this.i);
            }
        });

        this.setOnMouseExited(event -> {
            if(!BoardView.gameOver) {
                gameController.onMouseExited(this.i);
            }
        });

        this.setOnMousePressed(event -> {
            if(!BoardView.gameOver) {
                gameController.onMouseLeftClicked(this.i);
            }
        });
    }

}
