package eu.yeger.connectfour.model;

import org.fulib.Fulib;
import org.fulib.builder.ClassBuilder;
import org.fulib.builder.ClassModelBuilder;
import org.fulib.classmodel.ClassModel;

public class GenModel {

    public static void main(String[] args) {
        ClassModelBuilder cmb = Fulib.classModelBuilder("eu.yeger.connectfour.model", "src/main/java");

        //classes
        ClassBuilder game = cmb.buildClass("Game");
        ClassBuilder player = cmb.buildClass("Player");
        ClassBuilder field = cmb.buildClass("Field");


        //attributes
        player.buildAttribute("color", ClassModelBuilder.STRING);


        //associations
        game.buildAssociation(player, "players", ClassModelBuilder.MANY, "game", ClassModelBuilder.ONE);
        game.buildAssociation(player, "currentPlayer", ClassModelBuilder.ONE, "currentGame", ClassModelBuilder.ONE);
        game.buildAssociation(field, "fields", ClassModelBuilder.MANY, "game", ClassModelBuilder.ONE);

        player.buildAssociation(field, "fields", ClassModelBuilder.MANY, "player", ClassModelBuilder.ONE);

        field.buildAssociation(field, "top", ClassModelBuilder.ONE, "bottom", ClassModelBuilder.ONE);
        field.buildAssociation(field, "left", ClassModelBuilder.ONE, "right", ClassModelBuilder.ONE);

        //generate
        ClassModel cm = cmb.getClassModel();
        Fulib.generator().generate(cm);
        Fulib.tablesGenerator().generate(cm);
    }
}
