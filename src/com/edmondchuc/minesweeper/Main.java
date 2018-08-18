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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


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

        // Setting the image view
//        ImageView imageView = new ImageView(cellDefaultImage);

//        double length = 512/8;
        // Setting the position of the image
//        imageView.setX(50);
//        imageView.setY(25);
//        imageView.setFitHeight(length);
//        imageView.setFitWidth(length);

        // draw the grid of cells
//        int boardSize = 8;
//        ImageView[][] cellViews = new ImageView[boardSize][boardSize];

        // Creating a group object
        // add objects to the list so that they get rendered
        Group root = new Group();
        ObservableList list = root.getChildren();

        BoardModel model = new BoardModel(8);
        BoardView view = new BoardView(list, 8);
        GameController gameController = new GameController(model, view);

//        for(int i = 0; i < boardSize; i++) {
//            for(int j = 0; j < boardSize; j++) {
//                cellViews[i][j] = new ImageView(cellDefaultImage);
//                cellViews[i][j].setX(length * j);
//                cellViews[i][j].setY(length * i + 128);
//                cellViews[i][j].setFitHeight(length);
//                cellViews[i][j].setFitWidth(length);
//                list.add(cellViews[i][j]);
//            }
//        }

        // Setting the fit height and width of the image view
//        imageView.setFitHeight(455);
//        imageView.setFitWidth(500);

//        // Setting the preserve ratio of the image view
//        imageView.setPreserveRatio(true);
//
//        // MY CODE
//        CellContext cell = new CellContext();
//
//        imageView.setOnMouseEntered(event -> {
//            cell.setStateHoverEnter();
//            setView(cell, imageView, cellDefaultImage, cellDownImage);
//        });
//
//        imageView.setOnMouseExited(event -> {
//            cell.setStateHoverExit();
//            setView(cell, imageView, cellDefaultImage, cellDownImage);
//        });
//
//        imageView.setOnMouseReleased(event -> {
////            System.out.println("Mouse released on image");
//        });



//        imageView.setOnMousePressed(event -> {
////            System.out.println("Mouse pressed on image");
////            if(imageView.getImage() == cellDefaultImage) {
////                imageView.setImage(cellDownImage);
////            }
////            else {
////                imageView.setImage(cellDefaultImage);
////            }
//        });



        // Creating a scene object
        Scene scene = new Scene(root, 512, 640);

        // Setting title to the stage
        primaryStage.setTitle("Minesweeper by Edmond Chuc");

        // Adding scene to the stage
        primaryStage.setScene(scene);

        // Displaying the contents of the stage
        primaryStage.show();

        // Disable window resize
        primaryStage.setResizable(false);


    }

    public static void main(String[] args) {
        launch(args);
    }


}
