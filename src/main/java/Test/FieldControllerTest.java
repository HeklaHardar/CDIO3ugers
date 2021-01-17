package Test;

import Game.Model.Fields.BuildableField;
import Game.Model.Player;
import Game.controller.FieldController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldControllerTest {

    @Test
    void buildHouses() {
        Player p1 = new Player("1",1);
        String expected = "notbuildable";
        FieldController fieldController = new FieldController();
        assertEquals(expected, fieldController.buildHouses(p1, 1,1));


        expected = "buildable";
        fieldController.setPosition(1);
        fieldController.setOwnedFields(1);
        fieldController.setPosition(3);
        fieldController.setOwnedFields(1);
        assertEquals(expected,fieldController.buildHouses(p1, 1,1));
        expected = "houseRequirements";
        assertEquals(expected,fieldController.buildHouses(p1, 1,1));
    }

    @Test
    void removeHouses() {
        Player p1 = new Player("1",1);
        String expected;
        FieldController fieldController = new FieldController();


        expected = "buildable";
        fieldController.setPosition(1);
        fieldController.setOwnedFields(1);
        fieldController.setPosition(3);
        fieldController.setOwnedFields(1);
        assertEquals(expected,fieldController.buildHouses(p1, 1,1));
        fieldController.RemoveHouses(p1,1,1);
        assertEquals(0,((BuildableField)fieldController.getFields()[1]).getHouses());
    }

    @Test
    void hasAllFields() {
        Player p1 = new Player("1",1);
        boolean expected =true;
        FieldController fieldController = new FieldController();

        fieldController.setPosition(1);
        fieldController.setOwnedFields(1);
        fieldController.setPosition(3);
        fieldController.setOwnedFields(1);
        assertEquals(expected,fieldController.hasAllFields(1));
    }
}