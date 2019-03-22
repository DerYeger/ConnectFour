package eu.yeger.connectfour.controller;

import eu.yeger.connectfour.model.Field;
import eu.yeger.connectfour.model.Game;
import eu.yeger.connectfour.model.Player;
import eu.yeger.connectfour.model.tables.GameTable;
import eu.yeger.connectfour.model.tables.PlayerTable;

import java.util.*;

public class GameController {

    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;

    private static final int REQUIRED_STREAK = 4;

    private static final int STONE_COUNT = WIDTH * HEIGHT / 2;

    private static final ArrayList<String> PLAYER_COLORS = new ArrayList<>(Arrays.asList("R", "G"));

    private Game game;

    public Game init() {
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
        for (int y = 0; y < HEIGHT; y++) {
            Field leftField = null;
            for (int x = 0; x < WIDTH; x++) {
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
        if (player.getFields().size() >= STONE_COUNT) return false;
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

    //returns the winner or null if no player has won (yet)
    public Player checkWinner() {
        for (Player player : game.getPlayers()) {
            if (checkIfHasWon(player)) return player;
        }
        return null;
    }

    private boolean checkIfHasWon(final Player player) {
        //filters fields that can not be part of a streak
        LinkedHashSet<Field> fields = new PlayerTable(player)
                .expandFields()
                .filter(this::fieldHasFriendlyNeighbors)
                .toSet();

        for (Field field : fields) {
            if (isPartOfStreak(field)) return true;
        }

        return false;
    }

    private boolean fieldHasFriendlyNeighbors(final Field field) {
        final Player owner = field.getPlayer();
        return (field.getLeft() != null && field.getLeft().getPlayer() != null && field.getLeft().getPlayer().equals(owner))
                || (field.getTop() != null && field.getTop().getPlayer() != null && field.getTop().getPlayer().equals(owner))
                || (field.getRight() != null && field.getRight().getPlayer() != null && field.getRight().getPlayer().equals(owner))
                || (field.getBottom() != null && field.getBottom().getPlayer() != null && field.getBottom().getPlayer().equals(owner));
    }

    private boolean isPartOfStreak(final Field field) {
        //checks any possible position
        for (int firstSubStreakLength = 0; firstSubStreakLength < REQUIRED_STREAK; firstSubStreakLength++) {
            final int secondSubStreakLength = REQUIRED_STREAK - firstSubStreakLength - 1;
            //horizontal streak
            if (checkSubStreak(field, firstSubStreakLength, 0) && checkSubStreak(field, secondSubStreakLength, 2)) return true;
            //vertical streak
            if (checkSubStreak(field, firstSubStreakLength, 1) && checkSubStreak(field, secondSubStreakLength, 3)) return true;
            //top left to bottom right streak
            if (checkSubStreak(field, firstSubStreakLength, 4) && checkSubStreak(field, secondSubStreakLength, 6)) return true;
            //top right to bottom left streak
            if (checkSubStreak(field, firstSubStreakLength, 5) && checkSubStreak(field, secondSubStreakLength, 7)) return true;
        }
        return false;
    }

    //subStreakLength does not include the passed field
    private boolean checkSubStreak(final Field field, final int subStreakLength, final int direction) {
        final Player owner = field.getPlayer();
        Field currentField = getFieldByDirection(field, direction);
        for (int l = 0; l < subStreakLength; l++) {
            if (currentField == null || currentField.getPlayer() == null || !currentField.getPlayer().equals(owner)) {
                return false;
            }
            currentField = getFieldByDirection(currentField, direction);
        }
        //only reaches this point of sub-streak in given direction is valid
        return true;
    }

    //directions: 0 left, 1 up, 2 right, 3 bottom, 4 topLeft, 5 topRight, 6 bottomRight, 7 bottomLeft
    private Field getFieldByDirection(final Field field, final int direction) {
        switch (direction) {
            case 0:
                return field.getLeft();
            case 1:
                return field.getTop();
            case 2:
                return field.getRight();
            case 3:
                return field.getBottom();
            case 4:
                return field.getTop() != null ? field.getTop().getLeft() : null;
            case 5:
                return field.getTop() != null ? field.getTop().getRight() : null;
            case 6:
                return field.getBottom() != null ? field.getBottom().getRight() : null;
            case 7:
                return field.getBottom() != null ? field.getBottom().getLeft() : null;
            default:
                return null;
        }
    }

    public void drawFields() {
        final StringBuilder sb = new StringBuilder();
        //initialized with top left field
        Field rowHead = new GameTable(game)
                .expandFields()
                .filter(f -> f.getTop() == null && f.getLeft() == null)
                .toSet()
                .iterator()
                .next();

        while (rowHead != null) {
            Field currentField = rowHead;
            while (currentField != null) {
                sb.append(currentField.getPlayer() == null ? '_' : currentField.getPlayer().getColor());
                currentField = currentField.getRight();
            }
            sb.append('\n');
            rowHead = rowHead.getBottom();
        }

        final String fieldString = sb.toString();

        System.out.println(fieldString);
    }
}
