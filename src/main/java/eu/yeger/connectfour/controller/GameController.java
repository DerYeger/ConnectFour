package eu.yeger.connectfour.controller;

import eu.yeger.connectfour.model.Field;
import eu.yeger.connectfour.model.Game;
import eu.yeger.connectfour.model.Player;
import eu.yeger.connectfour.model.tables.GameTable;
import eu.yeger.connectfour.util.Direction;

import java.util.*;

public class GameController {

    private static final ArrayList<String> PLAYER_COLORS = new ArrayList<>(Arrays.asList("Green", "Red"));

    private Game game;

    private int width;
    private int height;

    private int stoneLimit;

    private int streakLength;

    public Game init(final int width, final int height, final int streakLength) {
        this.width = width;
        this.height = height;
        stoneLimit = width * height / 2;
        this.streakLength = streakLength;

        game = new Game();

        initPlayers();
        initFields();

        return game;
    }

    private void initPlayers() {
        final Iterator<String> playerColorIterator = PLAYER_COLORS.iterator();
        new Player()
                .setColor(playerColorIterator.next())
                .setGame(game)
                .setCurrentGame(game);
        new Player()
                .setColor(playerColorIterator.next())
                .setGame(game);
    }

    private void initFields() {
        Queue<Field> topFieldQueue = new LinkedList<>();
        for (int y = 0; y < height; y++) {
            Field leftField = null;
            for (int x = 0; x < width; x++) {
                Field currentField = new Field();
                currentField.setGame(game);
                currentField.setLeft(leftField);

                if (y != 0) {
                    currentField.setTop(topFieldQueue.poll());
                }

                leftField = currentField;
                topFieldQueue.offer(currentField);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void swapCurrentPlayer() {
        game.setCurrentPlayer(getOpponent(game.getCurrentPlayer()));
    }

    public Player getOpponent(final Player player) {
        return new GameTable(game)
                .expandPlayers()
                .filter(p -> !p.equals(player))
                .toSet()
                .iterator()
                .next();
    }

    public boolean placeStone(final Player player, final Field field) {
        if (!canPlaceStone(field)) return false;
        Field currentField = field;
        Field nextField = field.getBottom();
        while (nextField != null && canPlaceStone(nextField)) {
            currentField = nextField;
            nextField = nextField.getBottom();
        }
        currentField.setPlayer(player);
        swapCurrentPlayer();
        return true;
    }

    private boolean canPlaceStone(final Field field) {
        return field.getPlayer() == null;
    }

    public Player checkWinner() {
        for (Player player : game.getPlayers()) {
            if (hasWon(player)) return player;
        }
        return null;
    }

    public boolean hasWon(final Player player) {
        for (Field field : player.getFields()) {
            if (isHeadOfStreak(field)) return true;
        }
        return false;
    }

    private boolean isHeadOfStreak(final Field field) {
        for (Direction direction : Direction.values()) if (isHeadOfStreakInDirection(field, direction)) return true;
        return false;
    }

    private boolean isHeadOfStreakInDirection(final Field field, final Direction direction) {
        final Player owner = field.getPlayer();
        Field currentField = getFieldInDirection(field, direction);
        for (int i = 0; i < streakLength - 1; i++) {
            if (currentField == null || currentField.getPlayer() == null || !currentField.getPlayer().equals(owner)) {
                return false;
            }
            currentField = getFieldInDirection(currentField, direction);
        }
        return true;
    }

    private Field getFieldInDirection(final Field field, final Direction direction) {
        switch (direction) {
            case DOWN:
                return field.getBottom();
            case RIGHT:
                return field.getRight();
            case DOWN_RIGHT:
                return field.getBottom() == null ? null : field.getBottom().getRight();
            case UP_RIGHT:
                return field.getTop() == null ? null : field.getTop().getRight();
            default:
                return null;
        }
    }

    public boolean stoneLimitReached() {
        return new GameTable(game)
                .expandPlayers()
                .expandFields()
                .toSet()
                .size() == 2 * stoneLimit;
    }
}
