package eu.yeger.connectfour.controller;

import eu.yeger.connectfour.model.Field;
import eu.yeger.connectfour.model.Game;
import eu.yeger.connectfour.model.tables.GameTable;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class GameScreenController {

    private Game game;

    private GameController gc;

    private GridPane gridPane;

    public void init(final Game game, final GameController gc, final GridPane gridPane) {
        Objects.requireNonNull(game);
        Objects.requireNonNull(gc);
        Objects.requireNonNull(gridPane);
        this.game = game;
        this.gc = gc;
        this.gridPane = gridPane;

        addFieldCircles();
    }

    private void addFieldCircles() {
        Field rowHead = new GameTable(game)
                .expandFields()
                .filter(f -> f.getTop() == null && f.getLeft() == null)
                .toSet()
                .iterator()
                .next();

        for (int y = 0; y < gc.getHeight(); y++) {
            Field field = rowHead;
            for (int x = 0; x < gc.getWidth(); x++) {
                addFieldCircle(field, x, y);
                field = field.getRight();
            }
            rowHead = rowHead.getBottom();
        }
    }

    private void addFieldCircle(final Field field, final int xPos, final int yPos) {
        final Circle circle = new Circle(50);
        gridPane.add(circle, xPos, yPos);
        new FieldController().init(field, gc, circle);
    }
}
