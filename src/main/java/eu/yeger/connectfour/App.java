package eu.yeger.connectfour;

import eu.yeger.connectfour.controller.GameController;
import eu.yeger.connectfour.model.Game;
import eu.yeger.connectfour.model.Player;
import eu.yeger.connectfour.view.GameScreenBuilder;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class App extends Application {

    private Scene scene;

    @Override
    public void start(final Stage primaryStage) {
        scene = new Scene(startNewGame());
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Parent startNewGame() {
        GameController gc = new GameController();
        final Game game = gc.init(4, 4, 4);
        addPropertyChangeListeners(game, gc);
        return new GameScreenBuilder().getGameScreen(game, gc);
    }

    private void addPropertyChangeListeners(final Game game, final GameController gc) {
        for (Player player : game.getPlayers()) {
            player.addPropertyChangeListener(Player.PROPERTY_fields, evt -> {
                if (gc.hasWon(player)) {
                    showNewGameDialog(player + " has won!");
                } else if (gc.stoneLimitReached()) {
                    showNewGameDialog("Draw!");
                }
            });
        }
    }

    private void showNewGameDialog(final String title) {
        Alert gameOverAlert = new Alert(Alert.AlertType.CONFIRMATION);
        gameOverAlert.setTitle(title);
        gameOverAlert.setHeaderText(null);
        gameOverAlert.setContentText("Start a new game?");

        Optional<ButtonType> result = gameOverAlert.showAndWait();
        if (result.get() == ButtonType.OK){
            scene.setRoot(startNewGame());
        } else {
            Platform.exit();
        }
    }
}
