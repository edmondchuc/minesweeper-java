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

    public void createGameModeMenu(ObservableList list, int screenWidth, int screenHeight) {
        // select game mode
        MenuBar gameModeMenuBar = new MenuBar();
        gameModeMenuBar.setPrefSize(170, 30);
        gameModeMenuBar.setLayoutX(screenWidth/2 - 170/2);
        gameModeMenuBar.setLayoutY(20);
        Menu gameModeMenu = new Menu("Select game mode...");
        gameModeMenuBar.getMenus().add(gameModeMenu);
        RadioMenuItem radioMenuEasy = new RadioMenuItem("Easy");
        RadioMenuItem radioMenuMedium = new RadioMenuItem("Medium");
        RadioMenuItem radioMenuHard = new RadioMenuItem("Hard");
        ToggleGroup toggleGroupGameMode = new ToggleGroup();
        toggleGroupGameMode.getToggles().add(radioMenuEasy);
        toggleGroupGameMode.getToggles().add(radioMenuMedium);
        toggleGroupGameMode.getToggles().add(radioMenuHard);
        toggleGroupGameMode.selectToggle(radioMenuEasy);
        gameModeMenu.getItems().add(radioMenuEasy);
        gameModeMenu.getItems().add(radioMenuMedium);
        gameModeMenu.getItems().add(radioMenuHard);

        Text text = new Text(screenWidth/ 2, 75 ,"Easy");
        text.prefWidth(100);
        text.setLayoutX(-text.getLayoutBounds().getWidth());
        text.setFont(new Font(20));
//        text.setText("");
        gameModeMenu.setOnAction(value -> {
            RadioMenuItem selected = (RadioMenuItem) toggleGroupGameMode.getSelectedToggle();
            text.setText(selected.getText());
        });

        list.add(gameModeMenuBar);
        list.add(text);

        // start button
        Button startGame = new Button("Start game");
        startGame.setPrefSize(100, 30);
        startGame.setLayoutX(screenWidth/2 - 100/2);
        startGame.setLayoutY(screenHeight - 80);
        startGame.setOnAction(value -> {
            //TODO: hook it up to start the game
            RadioMenuItem selected = (RadioMenuItem) toggleGroupGameMode.getSelectedToggle();
            System.out.println("Starting game with difficulty: " + selected.getText());

        });
        list.add(startGame);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // Creating an image
        // C:\Users\edmon\IdeaProjects\minesweeper\src\com\edmondchuc\minesweeper\assets
        Image cellDefaultImage = new Image(new FileInputStream("assets" + File.separator + "Cell.png"));
//        add(1, 2);

        Image cellDownImage = new Image(new FileInputStream("assets" + File.separator + "CellDown.png"));

        // screen size
        int screenWidth = 512; // 512 for the default game
        int screenHeight = 640;// 640


        // Creating a group object
        // add objects to the list so that they get rendered
        Group root = new Group();
        ObservableList list = root.getChildren();

        // select game mode
//        createGameModeMenu(list, screenWidth, screenHeight);

        // select difficulty



        BoardModel model = new BoardModel(8);
        BoardView view = new BoardView(list, 8);
        GameController gameController = new GameController(model, view);

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

        // Creating a scene object
        Scene scene = new Scene(root, screenWidth, screenHeight);

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
