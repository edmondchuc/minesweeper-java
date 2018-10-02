package com.edmondchuc.minesweeper;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;

/**
 * Manages the cell view
 */
public class CellView extends Polygon {
    protected int i;
    protected Text text;

    /**
     * Constructor
     * @param i The cell identifier
     * @param col column
     * @param row row
     * @param length length
     * @param gameMode the game mode
     */
    public CellView(int i, int col, int row, double length, int gameMode) {
        this.i = i;
//        imageView = new ImageView(cellDefault);

        // text for the neighbouring bombs //TODO: split this into ClassicCellView and HexCellView to handle GameMode
        if(gameMode == GameMode.CLASSIC) {
            text = new Text(length * col + 15, length * row + 155, "3");
            text.setFont(new Font(20));
            text.setText("");
        }
        else if(gameMode == GameMode.HEX) {
            if(row % 2 == 0) {
                text = new Text(length * col + 65, length * row + 157, "3");
                text.setFont(new Font(20));
                text.setText("");
            }
            else {
                text = new Text(length * col + 45, length * row + 157, "3");
                text.setFont(new Font(20));
                text.setText("");
            }

        }

    }

    /**
     * Get the text object
     * @return object
     */
    public Text getText() {
        return text;
    }

    /**
     * Set the bomb text
     * @param bombs number of bombs
     */
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

    /**
     * Set the callbacks for this object's events
     * @param gameController The game controller object
     */
    public void setEvents(GameController gameController) {
        this.setOnMouseEntered(event -> {
            if(!BoardView.gameOver && !BoardView.win) {
                gameController.onMouseEntered(this.i);
            }
        });

        this.setOnMouseExited(event -> {
            if(!BoardView.gameOver && !BoardView.win) {
                gameController.onMouseExited(this.i);
            }
        });

        this.setOnMousePressed(event -> {
            if(!BoardView.gameOver && !BoardView.win) {

                if(event.isPrimaryButtonDown()) {
                    gameController.onMouseLeftClicked(this.i);
                } else {
                    gameController.onMouseRightClicked(this.i);
                }
            }
        });
    }

}
