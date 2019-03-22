package eu.yeger.connectfour.view;

import eu.yeger.connectfour.controller.GameController;
import eu.yeger.connectfour.controller.GameScreenController;
import eu.yeger.connectfour.model.Game;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class GameScreenBuilder {

    public Parent getGameScreen(final Game game, final GameController gc) {
        return buildGameScreen(game, gc);
    }

    private GridPane buildGameScreen(final Game game, final GameController gc) {
        final GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        new GameScreenController().init(game, gc, gridPane);
        return gridPane;
    }
}
