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

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GameMode {

    static int EASY = 0;
    static int MEDIUM = 1;
    static int HARD = 2;

    static int CLASSIC = 3;
    static int HEX = 4;
    static int COLOR = 5;

    static int currentGameMode;
    static int currentDifficulty;


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
                    int boardSize = 8;
                    ScoreView scoreView = new ScoreView(newList);
                    BoardModel model = new BoardModel(boardSize, GameMode.CLASSIC, GameMode.EASY);
                    ScoreController scoreController = new ScoreController(scoreView, model);
                    ClassicBoardView view = new ClassicBoardView(newList, boardSize, primaryStage, scoreController);
                    GameController gameController = new GameController(model, view);
                }
                else if(selectedDifficulty.getText() == "Medium") {
                    int boardSize = 12;
                    ScoreView scoreView = new ScoreView(newList);
                    BoardModel model = new BoardModel(boardSize, GameMode.CLASSIC, GameMode.MEDIUM);
                    ScoreController scoreController = new ScoreController(scoreView, model);
                    ClassicBoardView view = new ClassicBoardView(newList, boardSize, primaryStage, scoreController);
                    GameController gameController = new GameController(model, view);
                }
                else if(selectedDifficulty.getText() == "Hard") {
                    int boardSize = 16;
                    ScoreView scoreView = new ScoreView(newList);
                    BoardModel model = new BoardModel(boardSize, GameMode.CLASSIC, GameMode.HARD);
                    ScoreController scoreController = new ScoreController(scoreView, model);
                    ClassicBoardView view = new ClassicBoardView(newList, boardSize, primaryStage, scoreController);
                    GameController gameController = new GameController(model, view);
                }

                Scene game = new Scene(modes);
                primaryStage.setScene(game);
            }
            else if(selectedGameMode.getText() == "Hex") {
                if(selectedDifficulty.getText() == "Easy") {
                    int boardSize = 8;
                    ScoreView scoreView = new ScoreView(newList);
                    BoardModel model = new BoardModel(boardSize, GameMode.HEX, GameMode.EASY);
                    ScoreController scoreController = new ScoreController(scoreView, model);
                    HexBoardView view = new HexBoardView(newList, boardSize, primaryStage, scoreController);
                    GameController gameController = new GameController(model, view);
                    Scene game = new Scene(modes, 512, 550);
                    primaryStage.setScene(game);
                }
                else if(selectedDifficulty.getText() == "Medium") {
                    int boardSize = 12;
                    ScoreView scoreView = new ScoreView(newList);
                    BoardModel model = new BoardModel(boardSize, GameMode.HEX, GameMode.MEDIUM);
                    ScoreController scoreController = new ScoreController(scoreView, model);
                    HexBoardView view = new HexBoardView(newList, boardSize, primaryStage, scoreController);
                    GameController gameController = new GameController(model, view);
                    Scene game = new Scene(modes, 700, 764);
                    primaryStage.setScene(game);
                }
                else if(selectedDifficulty.getText() == "Hard") {
                    int boardSize = 16;
                    ScoreView scoreView = new ScoreView(newList);
                    BoardModel model = new BoardModel(boardSize, GameMode.HEX, GameMode.HARD);
                    ScoreController scoreController = new ScoreController(scoreView, model);
                    HexBoardView view = new HexBoardView(newList, boardSize, primaryStage, scoreController);
                    GameController gameController = new GameController(model, view);
                    Scene game = new Scene(modes, 900, 950);
                    primaryStage.setScene(game);
                }
            }

        });
        list.add(startGame);

        // hiscore view
        Button hiscoreButton = new Button("View hiscore");
        list.add(hiscoreButton);
        hiscoreButton.setOnAction(event -> {
            System.out.println("Viewing hiscore");
            Group hiscoreGroup = new Group();

            // add stuff to group to be viewed in new scene
            ObservableList hiscoreList = hiscoreGroup.getChildren();

            VBox box = new VBox();
            hiscoreList.add(box);
            box.setPadding(new Insets(10, 10, 10, 10));
            box.setSpacing(20);

            Text hiscoreTitle = new Text("Hiscores");
            hiscoreTitle.setStyle("-fx-font: 24 arial;");
            hiscoreTitle.toFront();
            box.getChildren().add(hiscoreTitle);

            // load scores from file and add to box
            String filename = "hiscore.txt";
            File file = new File(filename);
            ArrayList<String> scoreArray = new ArrayList<String>();
            try {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
//                    System.out.println(scanner.nextLine());
                    scoreArray.add(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ArrayList<ArrayList<String>> viewScoreArray = new ArrayList<ArrayList<String>>();
            // sort array by score
            for(String line : scoreArray) {
                int space = line.indexOf(" ");
                String name = line.substring(0, space);
                String score = line.substring(space+1, line.length());

                // array of name and score
                ArrayList<String> lineArray = new ArrayList<String>();
                lineArray.add(name);
                lineArray.add(score);

                // add this array to the score array
                viewScoreArray.add(lineArray);
            }
            // sort it so the lower scores are first (because they're better)
            Collections.sort(viewScoreArray, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    return o1.get(1).compareTo(o2.get(1));
                }
            });
            String view = "";
            for(ArrayList<String> line : viewScoreArray) {
                view = view.concat(line.get(0) + ": " + line.get(1) + "\n");
//                String o1 = line.get(0) + ": ";
//                String o2 = line.get(1) + "\n";
//                int width = 25;
//                System.out.println("width:" + Integer.toString(width - o1.length() + 1));
//                view = view.concat(String.format("%-1s %" + Integer.toString(width - o1.length() + 1) + "s", o1, o2));
            }
            System.out.println(view);

            Text viewText = new Text(view);
            box.getChildren().add(viewText);

            Button back = new Button("Back");
            back.setOnAction(event1 -> {
                GameMode gameMode = new GameMode(primaryStage);
            });
            box.getChildren().add(back);

            Scene hiscoreScene = new Scene(hiscoreGroup);
            primaryStage.setScene(hiscoreScene);
        });

        Button quit = new Button("Quit");
        list.add(quit);
        quit.setOnMouseClicked(event -> {
            System.exit(0);
        });

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
