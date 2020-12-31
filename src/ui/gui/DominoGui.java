package ui.gui;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import static ui.gui.DominoSideGui.drawDominoSide;

/*GUI Domino object class*/
public class DominoGui extends Node {

    public static void drawDomino (Pane train, int leftSide, int rightSide, boolean center) {

        HBox domino = new HBox();
        domino.setTranslateX(5);

        drawDominoSide(leftSide, domino);
        drawDominoSide(rightSide, domino);

        train.getChildren().add(domino);
        if (center) {
            domino.setTranslateX(450);
            domino.setTranslateY(250);
        }

    }

}
