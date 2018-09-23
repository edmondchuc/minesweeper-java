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

        Text title = new Text("Minesweeper");
        title.setFont(new Font(50));
        title.setUnderline(true);
        list.add(title);

        // select game mode
        MenuBar gameModeMenuBar = new MenuBar();
        gameModeMenuBar.setPrefSize(170, 30);
        gameModeMenuBar.setLayoutY(20);
        Menu gameModeMenu = new Menu("Select game mode...");
        gameModeMenuBar.getMenus().add(gameModeMenu);
        RadioMenuItem radioMenuClassic = new RadioMenuItem("Classic");
        RadioMenuItem radiomenuHex = new RadioMenuItem("Hex");
        RadioMenuItem radioMenuColor = new RadioMenuItem("Color");
        ToggleGroup toggleGroupGameMode = new ToggleGroup();
        toggleGroupGameMode.getToggles().add(radioMenuClassic);
        toggleGroupGameMode.getToggles().add(radiomenuHex);
        toggleGroupGameMode.getToggles().add(radioMenuColor);
        toggleGroupGameMode.selectToggle(radioMenuClassic);
        gameModeMenu.getItems().add(radioMenuClassic);
        gameModeMenu.getItems().add(radiomenuHex);
        gameModeMenu.getItems().add(radioMenuColor);

        Text gameModeText = new Text(100, 75 ,"Classic");
        gameModeText.prefWidth(100);
        gameModeText.setLayoutX(-gameModeText.getLayoutBounds().getWidth());
        gameModeText.setFont(new Font(20));

        gameModeMenu.setOnAction(value -> {
            RadioMenuItem selected = (RadioMenuItem) toggleGroupGameMode.getSelectedToggle();
            gameModeText.setText(selected.getText());
        });

        list.add(gameModeMenuBar);
        list.add(gameModeText);

        // select difficulty
        MenuBar difficultyMenuBar = new MenuBar();
        difficultyMenuBar.setPrefSize(170, 30);
        difficultyMenuBar.setLayoutY(20);
        Menu difficultyMenu = new Menu("Select difficulty...");
        difficultyMenuBar.getMenus().add(difficultyMenu);
        RadioMenuItem radioMenuEasy = new RadioMenuItem("Easy");
        RadioMenuItem radioMenuMedium = new RadioMenuItem("Medium");
        RadioMenuItem radioMenuHard = new RadioMenuItem("Hard");
        ToggleGroup toggleDifficultyMode = new ToggleGroup();
        toggleDifficultyMode.getToggles().add(radioMenuEasy);
        toggleDifficultyMode.getToggles().add(radioMenuMedium);
        toggleDifficultyMode.getToggles().add(radioMenuHard);
        toggleDifficultyMode.selectToggle(radioMenuEasy);
        difficultyMenu.getItems().add(radioMenuEasy);
        difficultyMenu.getItems().add(radioMenuMedium);
        difficultyMenu.getItems().add(radioMenuHard);

        Text difficultyText = new Text(100, 75 ,"Easy");
        difficultyText.prefWidth(100);
        difficultyText.setLayoutX(-difficultyText.getLayoutBounds().getWidth());
        difficultyText.setFont(new Font(20));
//        text.setText("");
        difficultyMenu.setOnAction(value -> {
            RadioMenuItem selected = (RadioMenuItem) toggleDifficultyMode.getSelectedToggle();
            difficultyText.setText(selected.getText());
        });

        list.add(difficultyMenuBar);
        list.add(difficultyText);

        // start button
        Button startGame = new Button("Start game");
        startGame.setPrefSize(100, 30);
        startGame.setLayoutY(100);
        startGame.setOnAction(value -> {

            RadioMenuItem selectedGameMode = (RadioMenuItem) toggleGroupGameMode.getSelectedToggle();
            System.out.println("Starting game with difficulty: " + selectedGameMode.getText());

            RadioMenuItem selectedDifficulty = (RadioMenuItem) toggleDifficultyMode.getSelectedToggle();
            System.out.println("Starting game with difficulty: " + selectedDifficulty.getText());

            Group modes = new Group();
            ObservableList newList = modes.getChildren();

            //TODO: add hex and color game mode
            if(selectedGameMode.getText() == "Classic") {
                if(selectedDifficulty.getText() == "Easy") {
                    BoardModel model = new BoardModel(8);
                    ClassicBoardView view = new ClassicBoardView(newList, 8, primaryStage);
                    GameController gameController = new GameController(model, view);
                }
                else if(selectedDifficulty.getText() == "Medium") {
                    BoardModel model = new BoardModel(12);
                    ClassicBoardView view = new ClassicBoardView(newList, 12, primaryStage);
                    GameController gameController = new GameController(model, view);
                }
                else if(selectedDifficulty.getText() == "Hard") {
                    BoardModel model = new BoardModel(16);
                    ClassicBoardView view = new ClassicBoardView(newList, 16, primaryStage);
                    GameController gameController = new GameController(model, view);
                }
            }
            else if(selectedGameMode.getText() == "Hex") {
                if(selectedDifficulty.getText() == "Easy") {
                    BoardModel model = new BoardModel(8);
                    HexBoardView view = new HexBoardView(newList, 8, primaryStage);
                    GameController gameController = new GameController(model, view);
                }
                else if(selectedDifficulty.getText() == "Medium") {
                    BoardModel model = new BoardModel(12);
                    HexBoardView view = new HexBoardView(newList, 12, primaryStage);
                    GameController gameController = new GameController(model, view);
                }
                else if(selectedDifficulty.getText() == "Hard") {
                    BoardModel model = new BoardModel(16);
                    HexBoardView view = new HexBoardView(newList, 16, primaryStage);
                    GameController gameController = new GameController(model, view);
                }
            }


            Scene game = new Scene(modes, 700, 700);
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
