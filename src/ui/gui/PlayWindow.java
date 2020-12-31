package ui.gui;

import gamengine.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static gamengine.Turn.*;
import static ui.gui.TrainGui.drawTrain;

/*GUI play menu window object class*/
public class PlayWindow {
    static Train train;

    public static void playWindow (Player player, Board board) {
        Stage stage = new Stage();
        VBox comp = new VBox();
        HBox handPane = new HBox();
        HBox handLabel = new HBox();
        HBox choicePane = new HBox();
        Button playButton = new Button("Play");
        Button drawButton = new Button("Draw");
        Button passButton = new Button("Pass");
        Button quitButton = new Button("Quit");
        choicePane.setPadding(new Insets(10,0,0,200));
        choicePane.setSpacing(20);
        handLabel.setSpacing(50);
        handLabel.setPadding(new Insets(15,2,3,35));
        handPane.setPadding(new Insets(10,0,0,10));

        drawTrain(handPane, player.getHand().getHand(), 'h');
        for (Domino d:
             player.getHand().getHand()) {
            Label l = new Label(Integer.toString(player.getHand().getHand().indexOf(d) + 1));
            handLabel.getChildren().add(l);
        }
        ComboBox handChoice = new ComboBox(FXCollections.observableArrayList(player.getHand().getHand()));
        ComboBox trainChoice = new ComboBox();

        for (Player p:
             board.getPlayerList()) {
            trainChoice.getItems().add(p.getId());
        }

        trainChoice.getItems().add(board.getPlayerList().size() + 1);

        choicePane.getChildren().add(handChoice);
        choicePane.getChildren().add(trainChoice);
        choicePane.getChildren().add(playButton);
        choicePane.getChildren().add(drawButton);
        choicePane.getChildren().add(passButton);
        choicePane.getChildren().add(quitButton);

        comp.getChildren().add(handPane);
        comp.getChildren().add(handLabel);
        comp.getChildren().add(choicePane);
        Scene stageScene = new Scene(comp, 900, 300);
        stage.setX(800);
        stage.setY(800);
        stage.setTitle("Player " + player.getId() + "'s Turn                       " +
                (board.getPlayerList().size()+1) + " to play mexican train");
        stage.setScene(stageScene);
        playButton.setOnAction(event -> {
            int trainInt = (int)trainChoice.getValue();
            if (trainInt == board.getPlayerList().size() + 1)
                train = board.getMexican();
            else
                train = board.getPlayerList().get(trainInt - 1).getTrain();


            if (train.getValue() != -1)
                play(train,
                        player.getHand().getHand(),
                        player.getHand().getHand().indexOf(handChoice.getValue()),
                        train.getValue(),
                        player,
                        board);
            else
                play(train,
                        player.getHand().getHand(),
                        player.getHand().getHand().indexOf(handChoice.getValue()),
                        board.getEngineValue(),
                        player,
                        board);

            stage.close();
        });
        drawButton.setOnAction(event -> {
            draw(board.getBoneyard(), board.getBoneyard().getBoneyardNum(), player.getHand(), player);
            stage.close();
        });
        passButton.setOnAction(event -> {
            pass(player, board, board.getPlayerTurn());
            stage.close();
        });
        quitButton.setOnAction(event -> {
            board.setEngineValue(-1);
            stage.close();
        });
        stage.showAndWait();
    }
}
