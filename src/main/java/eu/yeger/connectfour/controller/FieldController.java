package eu.yeger.connectfour.controller;

import eu.yeger.connectfour.model.Field;
import eu.yeger.connectfour.model.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class FieldController {

    private static Color DEFAULT_COLOR = Color.GRAY;

    private Field field;

    private GameController gc;

    private Circle circle;

    public void init(final Field field, final GameController gc, final Circle circle) {
        Objects.requireNonNull(field);
        Objects.requireNonNull(gc);
        Objects.requireNonNull(circle);
        this.field = field;
        this.gc = gc;
        this.circle = circle;

        updateCircleColor();

        addPropertyChangeListener();
        addEventListeners();
    }

    private void addPropertyChangeListener() {
        field.addPropertyChangeListener(Field.PROPERTY_player,evt -> updateCircleColor());
    }

    private void updateCircleColor() {
         Player player = field.getPlayer();
        if (player != null) {
            circle.setFill(Color.valueOf(player.getColor()));
        } else {
            circle.setFill(DEFAULT_COLOR);
        }
    }

    private void addEventListeners() {
        circle.setOnMouseClicked(event -> attemptToPlaceStone());
    }

    private void attemptToPlaceStone() {
        gc.placeStone(field.getGame().getCurrentPlayer(), field);
    }
}
