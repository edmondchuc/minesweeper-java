/**
 * <h1>Minesweeper</h1>
 * This is a minesweeper game. It emphasises on the principles of software engineering and utilises many of the aaaaaaaa
 * design principles to ensure robustness and modularity in the software architecture.
 *
 * @author Edmond Chuc
 * @version 2018-07-20
 * @link Class-based Finite State Machines source: http://gameprogrammingpatterns.com/state.html
 */

package com.edmondchuc.minesweeper;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;


public class Main extends Application {



    /**
     * Add
     * @param x xx
     * @param y yy
     * @author Edmond Chuc
     */
    public void add(int x, int y) {
        System.out.println(x + y);
    }

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
        // Creating an image
        // C:\Users\edmon\IdeaProjects\minesweeper\src\com\edmondchuc\minesweeper\assets
        Image cellDefaultImage = new Image(new FileInputStream("assets" + File.separator + "Cell.png"));
//        add(1, 2);

        Image cellDownImage = new Image(new FileInputStream("assets" + File.separator + "CellDown.png"));

        // screen size
        int screenWidth = 512+128; // 512 for the default game
        int screenHeight = 640+128;// 640


        // Creating a group object
        // add objects to the list so that they get rendered
        Group root = new Group();
        ObservableList list = root.getChildren();

        // select game mode
//        createGameModeMenu(list, screenWidth, screenHeight);
        GameMode gameMode = new GameMode(primaryStage);

        // select difficulty



//        BoardModel model = new BoardModel(8);
//        BoardView view = new BoardView(list, 8);
//        GameController gameController = new GameController(model, view);

        // draw rectangle for test
//        Rectangle rect = new Rectangle(100, 250, 100, 100);
//        list.add(rect);
//        rect.setOnMouseClicked(value -> {
//            System.out.println("clicked black box");
//        });

        // construct the menu bar
//        MenuBar menuBar = new MenuBar();
//
//        // root menu
//        Menu menu = new Menu("Game Mode");
//        menuBar.getMenus().add(menu);
//
//        // easy
//        MenuItem menuEasyMode = new MenuItem("Easy");
//        menu.getItems().add(menuEasyMode);
//
//        // medium
//        MenuItem menuMediumMode = new MenuItem("Medium");
//        menu.getItems().add(menuMediumMode);
//
//        // hard
//        MenuItem menuHardMode = new MenuItem("Hard");
//        menu.getItems().add(menuHardMode);
//
//        // add it to view
//        list.add(menuBar);





    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void stop() {
        System.out.println("User closed the application.");
    }
}
