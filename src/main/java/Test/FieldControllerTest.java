/*
package Test;

import Game.controller.FieldController;
import Game.controller.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FieldControllerTest {

    @Test
    void getFields() {
        FieldController field = new FieldController();
        System.out.println(field.getFields().length);

    }

    @Test
    void getRent2() {
    }

    @Test
    void buildHouses() {
        FieldController field = new FieldController();
        Player p1 = new Player("Hello");
*/
/*
        p1.starterScore(1);
*//*

        */
/*p1.starterScore(1);*//*

        field.setPosition(1);
        field.setOwnedFields(1);
        field.setPosition(3);
        field.setOwnedFields(1);
        field.setPosition(1);
        field.buildHouses(p1,1, 1);
        System.out.println(p1.getBalance());
    }

    @Test
    void returnAvaiableHousePositions() {
        FieldController field = new FieldController();
        Player p1 = new Player("Hello");
        field.setPosition(6);
        field.setOwnedFields(1);
        field.setPosition(8);
        field.setOwnedFields(1);
        field.setPosition(9);
        field.setOwnedFields(1);
        field.setPosition(1);
        field.buildHouses(p1,1,1);
        System.out.println(Arrays.toString(field.getAvaiableHousePositions()));
        System.out.println(p1.getBalance());
    }

    @Test
    void calculateRentHouse() {
        FieldController field = new FieldController();
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        field.setPosition(37);
        field.setOwnedFields(1);
        field.setPosition(39);
        field.setOwnedFields(1);
        field.setPosition(0);
        field.buildHouses(p1,1,39);
        field.buildHouses(p1,1,39);
        field.buildHouses(p1,1,39);
        field.buildHouses(p1,1,39);
        field.buildHouses(p1,1,39);
        field.setPosition(39);

    }
}*/
