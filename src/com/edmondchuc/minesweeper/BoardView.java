package com.edmondchuc.minesweeper;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.*;

/**
 * Manages the view of the board. Super class.
 */
public class BoardView {

    Paint cellDefault = Color.LIGHTBLUE;
    Paint cellHover = Color.GREENYELLOW;
    Paint cellDownEmpty = Color.ALICEBLUE;
    Paint cellDownBomb = Color.RED;
    Paint cellFlag = Color.YELLOW;

    CellView[] cells;

    int boardSize;
    int n; // size of list of cells
    Stage primaryStage;

    static boolean gameOver;
    static boolean win;

    Text textFlagCount;

    ScoreController scoreController;

    // observable list
    ObservableList list;

    /**
     * Constructor
     * @param list The Observable List
     * @param boardSize The board size's length
     * @param primaryStage The JavaFX Stage
     * @param scoreController The score controller object
     */
    public BoardView(ObservableList list, int boardSize, Stage primaryStage, ScoreController scoreController) {
        this.list = list;

        this.scoreController = scoreController;
        this.gameOver = false;
        this.win = false;

        this.primaryStage = primaryStage;

        this.boardSize = boardSize;
        this.n = boardSize*boardSize;
        cells = new CellView[n];

        // size of cell image length in pixels

        textFlagCount = new Text("");
        textFlagCount.toFront();
        textFlagCount.setX(30);
        textFlagCount.setY(100);
        list.add(textFlagCount);


        // main menu button
        Button startGame = new Button("Main Menu");
        list.add(startGame);
        startGame.setOnMouseClicked(e -> {
            GameMode gameMode = new GameMode(primaryStage);
            scoreController.setRunning(false);
//            this.gameOver = true; // used to stop the game score timer when going to the main menu.
        });

//        // quit button
//        Button quitGame = new Button("Quit");
//        quitGame.setTranslateX(100);
//        list.add(quitGame);
//        quitGame.setOnMouseClicked(event -> {
//            GameMode gameMode = new GameMode(primaryStage);
//            scoreController.setRunning(false);
//            System.exit(0);
//        });

    }

    /**
     * Set the events of the object.
     * @param gameController The game controller object
     */
    public void setEvents(GameController gameController) {
        for(int i = 0; i < n; i++) {
            cells[i].setEvents(gameController);
        }
    }

    /**
     * Set the view
     * @param model The board model object
     */
    public void setView(BoardModel model) {

        // set the flag count
        textFlagCount.setText("Bombs: " + Integer.toString(model.getFlagCount()));

        for(int i = 0; i < n; i++) {
            if(model.cells[i].getState().getClass() == CellDefault.class) {
                cells[i].setFill(cellDefault); //.imageView.setImage(cellDefault);
            }
            else if(model.cells[i].getState().getClass() == CellHover.class) {
                cells[i].setFill(cellHover); //imageView.setImage((cellHover));
            }
            else if(model.cells[i].getState().getClass() == CellRevealedBomb.class) {
                cells[i].setFill(cellDownBomb); //imageView.setImage(cellDownBomb);
            }
            else if(model.cells[i].getState().getClass() == CellRevealedEmpty.class) {
                cells[i].setFill(cellDownEmpty); //imageView.setImage(cellDownEmpty);
                cells[i].setBombText(model.cells[i].getNumOfNeighboursIsBomb());
            }
            else if(model.cells[i].getState().getClass() == CellFlagged.class) {
                cells[i].setFill(cellFlag);
            }
        }


    }

    /**
     * Draw the win/game over view
     * @param model The board model
     */
    public void checkWin(BoardModel model) {
        // if game lose, show popup to notify user
        if(model.isGameOver()) {
            BoardView.gameOver = true;
            scoreController.setRunning(false);

            Stage dialog = new Stage();
            dialog.initOwner(primaryStage);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setAlwaysOnTop(true);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setPadding(new Insets(10, 10, 10, 10));
            dialogVbox.setSpacing(20);
            dialogVbox.getChildren().add(new Text("You have clicked on a mine! Game over!"));

            addRestartButton(list, dialogVbox, dialog);

            Scene dialogScene = new Scene(dialogVbox);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
        }

        else if(model.isWin()) {
            BoardView.win = true;
            scoreController.setRunning(false);

            Stage dialog = new Stage();
            dialog.initOwner(primaryStage);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setAlwaysOnTop(true);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setPadding(new Insets(10, 10, 10, 10));
            dialogVbox.setSpacing(20);

            // score text
            dialogVbox.getChildren().add(new Text("You win!"));
            int score = scoreController.getScore();
            dialogVbox.getChildren().add(new Text("Score: " + score));

            Text response = new Text("");
            dialogVbox.getChildren().add(response);

            // input field for name
            TextField textFieldName = new TextField("player");
            dialogVbox.getChildren().add(textFieldName);

            // button save score
            Button buttonSaveScore = new Button("Save score");
            dialogVbox.getChildren().add(buttonSaveScore);
            buttonSaveScore.setOnMouseClicked(event -> {
                int maxChar = 16;
                if(textFieldName.getCharacters().length() > maxChar) {
                    //TODO: show some prompt giving the user an indication that it's too long
                    System.out.println("Too many characters! " + maxChar + " max.");
                    response.setText("Too many characters! " + maxChar + " max.");
                    response.setFill(Color.RED);
                } else {
                    System.out.println("Saving score of player " + textFieldName.getText() + " with a score of " + score);
                    response.setText("Score Saved!");
                    response.setFill(Color.GREEN);
                    buttonSaveScore.setVisible(false);

                    // save the score to file
                    String filename = "hiscore.txt";
                    try {
//                        FileWriter fileWriter = new FileWriter(filename);
                        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(filename), true));
//                        printWriter.printf("%s %d", textFieldName.getText(), score);
                        // always remove whitespace in name
                        printWriter.append(textFieldName.getText().replaceAll("\\s+","") + " " + score + "\n");

                        printWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            addRestartButton(list, dialogVbox, dialog);

            // display
            Scene dialogScene = new Scene(dialogVbox, 200, 250);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
        }
    }

    /**
     * Render the restart button
     * @param list Observable list
     * @param dialogVbox Dialogue box
     * @param dialog The dialogue
     */
    private void addRestartButton(ObservableList list, VBox dialogVbox, Stage dialog) {
        // button restart game with current settings
        Button buttonRestart = new Button("Restart");
        dialogVbox.getChildren().add(buttonRestart);
        buttonRestart.setOnMouseClicked(event -> {
            System.out.println("Restarting game");

            if(GameMode.currentGameMode == GameMode.CLASSIC && GameMode.currentDifficulty == GameMode.EASY) {
                int boardSize = 8;
                this.list.clear();
                ScoreView scoreView = new ScoreView(list);
                BoardModel modelNew = new BoardModel(boardSize, GameMode.CLASSIC, GameMode.EASY);
                ScoreController scoreController = new ScoreController(scoreView, modelNew);
                ClassicBoardView view = new ClassicBoardView(list, boardSize, primaryStage, scoreController);
                GameController gameController = new GameController(modelNew, view);
            } else if(GameMode.currentGameMode == GameMode.CLASSIC && GameMode.currentDifficulty == GameMode.MEDIUM) {
                int boardSize = 12;
                ScoreView scoreView = new ScoreView(list);
                BoardModel modelNew = new BoardModel(boardSize, GameMode.CLASSIC, GameMode.MEDIUM);
                ScoreController scoreController = new ScoreController(scoreView, modelNew);
                ClassicBoardView view = new ClassicBoardView(list, boardSize, primaryStage, scoreController);
                GameController gameController = new GameController(modelNew, view);
            } else if(GameMode.currentGameMode == GameMode.CLASSIC && GameMode.currentDifficulty == GameMode.HARD) {
                int boardSize = 16;
                ScoreView scoreView = new ScoreView(list);
                BoardModel modelNew = new BoardModel(boardSize, GameMode.CLASSIC, GameMode.HARD);
                ScoreController scoreController = new ScoreController(scoreView, modelNew);
                ClassicBoardView view = new ClassicBoardView(list, boardSize, primaryStage, scoreController);
                GameController gameController = new GameController(modelNew, view);
            } else if(GameMode.currentGameMode == GameMode.HEX && GameMode.currentDifficulty == GameMode.EASY) {
                int boardSize = 8;
                ScoreView scoreView = new ScoreView(list);
                BoardModel modelNew = new BoardModel(boardSize, GameMode.HEX, GameMode.MEDIUM);
                ScoreController scoreController = new ScoreController(scoreView, modelNew);
                HexBoardView view = new HexBoardView(list, boardSize, primaryStage, scoreController);
                GameController gameController = new GameController(modelNew, view);
            } else if(GameMode.currentGameMode == GameMode.HEX && GameMode.currentDifficulty == GameMode.MEDIUM) {
                int boardSize = 12;
                ScoreView scoreView = new ScoreView(list);
                BoardModel modelNew = new BoardModel(boardSize, GameMode.HEX, GameMode.MEDIUM);
                ScoreController scoreController = new ScoreController(scoreView, modelNew);
                HexBoardView view = new HexBoardView(list, boardSize, primaryStage, scoreController);
                GameController gameController = new GameController(modelNew, view);
            } else if(GameMode.currentGameMode == GameMode.HEX && GameMode.currentDifficulty == GameMode.MEDIUM) {
                int boardSize = 16;
                ScoreView scoreView = new ScoreView(list);
                BoardModel modelNew = new BoardModel(boardSize, GameMode.HEX, GameMode.MEDIUM);
                ScoreController scoreController = new ScoreController(scoreView, modelNew);
                HexBoardView view = new HexBoardView(list, boardSize, primaryStage, scoreController);
                GameController gameController = new GameController(modelNew, view);
            }
            dialog.close();
        });
    }
}
