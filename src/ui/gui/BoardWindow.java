package ui.gui;

import gamengine.Board;
import gamengine.Player;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static ui.gui.DominoGui.drawDomino;
import static ui.gui.MarkerGui.drawMarker;
import static ui.gui.TrainGui.drawTrain;

/*GUI Board object class*/
public class BoardWindow {

    public static void drawBoard(Pane root, Board board) {
        Pane boardPane = new BorderPane();

        boardPane.setTranslateY(150);
        drawDomino(boardPane, board.getEngineValue(), board.getEngineValue(), true);
        drawTrain(boardPane, board.getMexican().getTrain(), 'm');

        for (Player p:
                board.getPlayerList()) {
            drawTrain(boardPane, p.getTrain().getTrain(), (char)p.getId());

            drawMarker(boardPane, p.isOpenTrain(), p.getId());

        }
        root.getChildren().add(boardPane);
    }
}
