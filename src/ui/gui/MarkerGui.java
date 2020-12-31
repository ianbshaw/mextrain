package ui.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*GUI marker object class*/
public class MarkerGui {

    public static void drawMarker (Pane pane, boolean check, int id) {
        VBox vBox = new VBox();
        Rectangle marker = new Rectangle(5,5, Color.WHITE);
        Label playerId = new Label();
        if (id == 1) {
            playerId.setText("1");
            playerId.setTranslateX(505);
            playerId.setTranslateY(265);
            marker.setTranslateX(505);
            marker.setTranslateY(258);
        } else if (id == 2) {
            playerId.setText("2");
            playerId.setTranslateX(440);
            playerId.setTranslateY(270);
            marker.setTranslateX(440);
            marker.setTranslateY(265);
        } else if (id == 3) {
            playerId.setText("3");
            playerId.setTranslateX(486);
            playerId.setTranslateY(285);
            marker.setTranslateX(480);
            marker.setTranslateY(280);
        } else if (id == 4) {
            playerId.setText("4");
            playerId.setTranslateX(480);
            playerId.setTranslateY(230);
            marker.setTranslateX(475);
            marker.setTranslateY(238);
        }

        vBox.getChildren().add(marker);
        vBox.getChildren().add(playerId);

        if (check) marker.setFill(Color.BLACK);
        else marker.setFill(Color.WHITE);

        pane.getChildren().add(vBox);

    }
}
