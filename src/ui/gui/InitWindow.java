package ui.gui;

import gamengine.Board;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static gamengine.BoardSetup.gameInit;

/*GUI init game window*/
public class InitWindow {

    public static void initWindow (Board board) {
        Stage stage = new Stage();
        VBox comp = new VBox();
        comp.setPadding(new Insets(50,0,0,150));
        comp.setSpacing(20);
        HBox buttonPane = new HBox();
        buttonPane.setPadding(new Insets(0,0,0,150));
        Button start = new Button("Start");
        buttonPane.getChildren().add(start);
        Label playerNumLabel = new Label("How many human players?");
        Label compNumLabel = new Label("How many computer players?");
        comp.getChildren().add(playerNumLabel);

        TextField playerNum = new TextField();
        playerNum.setMaxWidth(100);
        comp.getChildren().add(playerNum);
        comp.getChildren().add(compNumLabel);
        TextField computerNum = new TextField();
        computerNum.setMaxWidth(100);
        comp.getChildren().add(computerNum);
        comp.getChildren().add(buttonPane);

        Scene stageScene = new Scene(comp, 600, 300);
        stage.setScene(stageScene);

        start.setOnAction(event -> {
            int p = 0;
            int c = 0;
            if (!playerNum.getText().isEmpty())
                p = Integer.parseInt(playerNum.getText());
            if (!computerNum.getText().isEmpty())
                c = Integer.parseInt(computerNum.getText());

            if (p + c < 2) {
                board.setEngineValue(-1);
                stage.close();
            }

            gameInit(p ,c, board, board.getEngineValue());
            stage.close();
        });

        stage.showAndWait();

    }

}
