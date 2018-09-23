package com.edmondchuc.minesweeper;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameMode {

    static int EASY = 0;
    static int MEDIUM = 1;
    static int HARD = 2;

    static int CLASSIC = 3;
    static int HEX = 4;
    static int COLOR = 5;


    public GameMode(Stage primaryStage) {
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(20);
        ObservableList list = root.getChildren();

        // select game mode
        MenuBar gameModeMenuBar = new MenuBar();
        gameModeMenuBar.setPrefSize(170, 30);
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

        Text text = new Text(100, 75 ,"Easy");
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
        startGame.setLayoutY(100);
        startGame.setOnAction(value -> {

            RadioMenuItem selected = (RadioMenuItem) toggleGroupGameMode.getSelectedToggle();
            System.out.println("Starting game with difficulty: " + selected.getText());

            Group modes = new Group();
            ObservableList newList = modes.getChildren();

            if(selected.getText() == "Easy") {
                BoardModel model = new BoardModel(8);
                BoardView view = new BoardView(newList, 8, primaryStage);
                GameController gameController = new GameController(model, view);
            }
            else if(selected.getText() == "Medium") {
                BoardModel model = new BoardModel(12);
                BoardView view = new BoardView(newList, 12, primaryStage);
                GameController gameController = new GameController(model, view);
            }
            else if(selected.getText() == "Hard") {
                BoardModel model = new BoardModel(16);
                BoardView view = new BoardView(newList, 16, primaryStage);
                GameController gameController = new GameController(model, view);

            }

            Scene game = new Scene(modes);
            primaryStage.setScene(game);


        });
        list.add(startGame);

        // Creating a scene object
        Scene scene = new Scene(root);

        // Setting title to the stage
        primaryStage.setTitle("Minesweeper by Edmond Chuc");

        // Adding scene to the stage
        primaryStage.setScene(scene);

        // Displaying the contents of the stage
        primaryStage.show();

        // Disable window resize
        primaryStage.setResizable(false);
    }

}
