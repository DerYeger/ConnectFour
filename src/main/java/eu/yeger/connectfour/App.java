package eu.yeger.connectfour;

import eu.yeger.connectfour.controller.GameController;
import eu.yeger.connectfour.model.Game;
import eu.yeger.connectfour.view.GameScreenBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameController gc = new GameController();
        final Game game = gc.init();
        final Scene scene = new Scene(new GameScreenBuilder().getGameScreen(game, gc));
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
