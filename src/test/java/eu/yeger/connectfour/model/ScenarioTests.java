package eu.yeger.connectfour.model;

import eu.yeger.connectfour.controller.GameController;
import eu.yeger.connectfour.model.tables.GameTable;

import org.junit.Assert;
import org.junit.Test;

public class ScenarioTests {

    @Test
    public void testScenario1() {
        final GameController gc = new GameController();
        final Game game = gc.init();

        final Player bob = game.getCurrentPlayer();
        final Player alice = gc.getOpponent(bob);

        final Field topLeft = new GameTable(game)
                .expandFields()
                .filter(f -> f.getTop() == null && f.getLeft() == null)
                .toSet()
                .iterator()
                .next();

        final Field topLeftMiddle = topLeft.getRight();
        final Field topRightMiddle = topLeftMiddle.getRight();
        final Field topRight = topRightMiddle.getRight();

        //actions
        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topRightMiddle));

        Assert.assertTrue(gc.placeStone(bob, topLeftMiddle));
        Assert.assertTrue(gc.placeStone(alice, topRightMiddle));

        Assert.assertTrue(gc.placeStone(bob, topRight));
        Assert.assertTrue(gc.placeStone(alice, topLeftMiddle));

        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topRightMiddle));

        Assert.assertTrue(gc.placeStone(bob, topRight));
        Assert.assertNull(gc.checkWinner());

        //asserts
        Assert.assertEquals(alice, game.getCurrentPlayer());
        Assert.assertEquals(5, bob.getFields().size());
        Assert.assertEquals(4, alice.getFields().size());

        gc.drawFields();
    }

    @Test
    public void testCheckHorizontalWinner() {
        final GameController gc = new GameController();
        final Game game = gc.init();

        final Player bob = game.getCurrentPlayer();
        final Player alice = gc.getOpponent(bob);

        final Field topLeft = new GameTable(game)
                .expandFields()
                .filter(f -> f.getTop() == null && f.getLeft() == null)
                .toSet()
                .iterator()
                .next();

        final Field topLeftMiddle = topLeft.getRight();
        final Field topRightMiddle = topLeftMiddle.getRight();
        final Field topRight = topRightMiddle.getRight();

        //actions
        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topLeft));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topRightMiddle));
        Assert.assertTrue(gc.placeStone(alice, topLeftMiddle));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topRight));
        Assert.assertTrue(gc.placeStone(alice, topLeftMiddle));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topRight));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeftMiddle));
        Assert.assertTrue(gc.placeStone(alice, topRightMiddle));
        Assert.assertEquals(alice, gc.checkWinner());

        //asserts
        Assert.assertEquals(bob, game.getCurrentPlayer());
        Assert.assertEquals(5, bob.getFields().size());
        Assert.assertEquals(5, alice.getFields().size());

        gc.drawFields();
    }

    @Test
    public void testCheckVerticalWinner() {
        final GameController gc = new GameController();
        final Game game = gc.init();

        final Player bob = game.getCurrentPlayer();
        final Player alice = gc.getOpponent(bob);

        final Field topLeft = new GameTable(game)
                .expandFields()
                .filter(f -> f.getTop() == null && f.getLeft() == null)
                .toSet()
                .iterator()
                .next();

        final Field topLeftMiddle = topLeft.getRight();
        final Field topRightMiddle = topLeftMiddle.getRight();
        final Field topRight = topRightMiddle.getRight();

        //actions
        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topLeftMiddle));
       // Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topRightMiddle));
       // Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topRight));
        //Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertEquals(bob, gc.checkWinner());

        //asserts
        Assert.assertEquals(alice, game.getCurrentPlayer());
        Assert.assertEquals(4, bob.getFields().size());
        Assert.assertEquals(3, alice.getFields().size());

        gc.drawFields();
    }

    @Test
    public void testCheckDiagonalWinner() {
        final GameController gc = new GameController();
        final Game game = gc.init();

        final Player bob = game.getCurrentPlayer();
        final Player alice = gc.getOpponent(bob);

        final Field topLeft = new GameTable(game)
                .expandFields()
                .filter(f -> f.getTop() == null && f.getLeft() == null)
                .toSet()
                .iterator()
                .next();

        final Field topLeftMiddle = topLeft.getRight();
        final Field topRightMiddle = topLeftMiddle.getRight();
        final Field topRight = topRightMiddle.getRight();

        //actions
        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topLeftMiddle));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeftMiddle));
        Assert.assertTrue(gc.placeStone(alice, topRightMiddle));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topRightMiddle));
        Assert.assertTrue(gc.placeStone(alice, topRight));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topRightMiddle));
        Assert.assertTrue(gc.placeStone(alice, topRight));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topRight));
        Assert.assertTrue(gc.placeStone(alice, topLeft));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topRight));
        Assert.assertEquals(bob, gc.checkWinner());

        //asserts
        Assert.assertEquals(alice, game.getCurrentPlayer());
        Assert.assertEquals(6, bob.getFields().size());
        Assert.assertEquals(5, alice.getFields().size());

        gc.drawFields();
    }

    @Test
    public void testCheckSecondDiagonalWinner() {
        final GameController gc = new GameController();
        final Game game = gc.init();

        final Player bob = game.getCurrentPlayer();
        final Player alice = gc.getOpponent(bob);

        final Field topLeft = new GameTable(game)
                .expandFields()
                .filter(f -> f.getTop() == null && f.getLeft() == null)
                .toSet()
                .iterator()
                .next();

        final Field topLeftMiddle = topLeft.getRight();
        final Field topRightMiddle = topLeftMiddle.getRight();
        final Field topRight = topRightMiddle.getRight();

        //actions
        Assert.assertTrue(gc.placeStone(bob, topRight));
        Assert.assertTrue(gc.placeStone(alice, topRightMiddle));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topRightMiddle));
        Assert.assertTrue(gc.placeStone(alice, topLeftMiddle));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeftMiddle));
        Assert.assertTrue(gc.placeStone(alice, topLeft));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeftMiddle));
        Assert.assertTrue(gc.placeStone(alice, topLeft));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertTrue(gc.placeStone(alice, topRight));
        Assert.assertNull(gc.checkWinner());

        Assert.assertTrue(gc.placeStone(bob, topLeft));
        Assert.assertEquals(bob, gc.checkWinner());

        //asserts
        Assert.assertEquals(alice, game.getCurrentPlayer());
        Assert.assertEquals(6, bob.getFields().size());
        Assert.assertEquals(5, alice.getFields().size());

        gc.drawFields();
    }
}
