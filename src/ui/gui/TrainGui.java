package ui.gui;

import gamengine.Domino;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.List;

import static ui.gui.DominoGui.drawDomino;

/*GUI train object class*/
public class TrainGui {

    public static void drawTrain (Pane board, List<Domino> dominoes, char pos) {
        HBox topTrain;
        HBox bottomTrain;
        Pane train;
        topTrain = new HBox();
        bottomTrain = new HBox();
        train = new Pane();
        train.getChildren().addAll(topTrain, bottomTrain);
        topTrain.setSpacing(5);
        bottomTrain.setSpacing(5);
        int i = 0;
        for (Domino d:
                dominoes) {

            if ((i % 2 ) == 0 )
                drawDomino(topTrain, d.getLeft(), d.getRight(), false);
            else {
                drawDomino(bottomTrain, d.getLeft(), d.getRight(), false);
            }
            i++;
        }
        bottomTrain.setTranslateX(25);
        bottomTrain.setTranslateY(30);

        if (pos == 'm') {
            train.setRotate(90);
            train.setTranslateX(300);
            train.setTranslateY(350);
            board.getChildren().add(train);
        } else if (pos == (char) 1) {
            train.setTranslateX(510);
            train.setTranslateY(250);
            board.getChildren().add(train);
        } else if (pos == (char) 2) {
            train.setRotate(180);
            train.setTranslateX(440);
            train.setTranslateY(275);
            board.getChildren().add(train);
        } else if (pos == (char) 3) {
            train.setRotate(90);
            train.setTranslateX(480);
            train.setTranslateY(280);
            board.getChildren().add(train);
        } else if (pos == (char) 4) {
            train.setRotate(270);
            train.setTranslateX(470);
            train.setTranslateY(240);
            board.getChildren().add(train);
        } else if (pos == 'h') {
            HBox newTrain = new HBox();
            newTrain.setSpacing(5);
            newTrain.getChildren().addAll(topTrain,bottomTrain);
            bottomTrain.setTranslateX(0);
            bottomTrain.setTranslateY(0);
            board.getChildren().add(newTrain);
        }
    }
}
