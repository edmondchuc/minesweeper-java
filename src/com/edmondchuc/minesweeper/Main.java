/**
 * <h1>Minesweeper</h1>
 * This is a minesweeper game. It emphasises on the principles of software engineering and utilises many of the
 * design principles to ensure robustness and modularity in the software architecture.
 *
 * @author Edmond Chuc
 * @version 2018-07-20
 * @link Class-based Finite State Machines source: http://gameprogrammingpatterns.com/state.html
 */

package com.edmondchuc.minesweeper;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class Main extends Application {

    /**
     * Sets the view
     * @param cell Cell context object
     * @param view ImageView object
     * @param cellDefaultImage Image object of cell default
     * @param cellDownImage Image object of cell down
     */
    public void setView(CellContext cell, ImageView view, Image cellDefaultImage, Image cellDownImage) {
        if(cell.getState().getClass() == CellDefault.class) {
            view.setImage(cellDefaultImage);
        }
        else if(cell.getState().getClass() == CellHover.class) {
            view.setImage((cellDownImage));
        }
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        // screen size
        int screenWidth = 512+128; // 512 for the default game
        int screenHeight = 640+128;// 640


        // Creating a group object
        // add objects to the list so that they get rendered
        Group root = new Group();
        ObservableList list = root.getChildren();

        GameMode gameMode = new GameMode(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void stop() {
        System.out.println("User closed the application.");
        System.exit(0);
    }
}
