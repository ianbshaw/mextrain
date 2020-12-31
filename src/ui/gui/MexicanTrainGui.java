package ui.gui;

/*Program to display GUI for Mexican Train domino game
 * Ian Bradshaw
 * CS351 Project 2*/

import gamengine.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static gamengine.BoardSetup.shuffle;
import static gamengine.ComputerTurn.computerTurn;
import static gamengine.Round.*;
import static gamengine.Turn.*;
import static ui.gui.PlayWindow.playWindow;

/*Main class to start GUI version of Mexican Train using game engine package*/
public class MexicanTrainGui extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        /*pane variables for main GUI window that contains the game board*/
        BorderPane root = new BorderPane();
        Pane pane = new Pane();

        root.setCenter(pane);

        stage.setScene(new Scene(root, 1000 ,1000));
        stage.show();

        /*create game board object*/
        Board board = new Board();

        /*game setup*/
        InitWindow.initWindow(board);

        /*check for game end*/
        while (checkGameEnd(board)) {
            /*Set game score and start game turn*/
            stage.setTitle(getScore(board));
            roundStart(board, pane);
        }

        Player winner = checkGameWinner(board.getPlayerList());
        System.out.println("Player " + winner.getId() + " wins!!");
        System.out.println(board.toString());
        stage.setTitle(getScore(board) + "  Player " + winner.getId() + " wins!!");
        BoardWindow.drawBoard(pane, board);

    }

    /*logic for game turn for human and computer*/
    public void roundStart (Board board, Pane pane) {

        /*check for round end*/
        if (roundEnd(board.getPlayerList(), board)) {
            verbose("Round end, shuffling...");
            shuffle(board);
            return;
        }

        /*clear board pane*/
        if (!pane.getChildren().isEmpty()) pane.getChildren().clear();

        int turn = board.getPlayerTurn();

        Player player = board.getPlayerList().get((turn - 1));
        player.setOpenTrain(true);
        /*draw board to gui*/
        BoardWindow.drawBoard(pane, board);

        /*logic for computer player*/
        if (player.isComputer()) {
            computerTurn(board, player);
            return;
        }

        /*window for human player turn*/
        playWindow(board.getPlayerList().get(turn - 1), board);
    }

    /*string to use title as scoreboard*/
    public String getScore(Board board) {
        StringBuilder scores = new StringBuilder("Mexican Train Game - ");

        for (Player p : board.getPlayerList()) {
            scores.append("  Player ").append(p.getId()).append(": ").append(p.getScore());
        }
        return scores.toString();
    }
}

