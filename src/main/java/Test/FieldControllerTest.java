package Test;

import Game.controller.FieldController;
import Game.controller.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldControllerTest {

    @Test
    void getFields() {
        FieldController field = new FieldController();
        System.out.println(field.getFields().length);
        System.out.println(field.getFieldColors().length);
        System.out.println(field.getFieldTitles().length);
        System.out.println(field.getFieldValues().length);


    }

    @Test
    void getRent2() {
    }

    @Test
    void buildHouses() {
        FieldController field = new FieldController();
        Player p1 = new Player("Hello");
        p1.starterScore(1);
        field.setPosition(1);
        field.buildHouses(p1);
        System.out.println(field.getHouses()[1]);
        System.out.println(p1.playerBalance());
    }
}