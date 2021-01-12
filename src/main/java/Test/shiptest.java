package Test;

import Game.controller.FieldController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class shiptest {

    @Test
    void countShips() {
        FieldController field =new FieldController();
        field.createFields();

        field.setPosition(5);
        field.setOwnedFields(1);
        System.out.println(field.countShips());

        field.setPosition(15);
        field.setOwnedFields(1);
        System.out.println(field.countShips());

        field.setPosition(25);
        field.setOwnedFields(1);
        System.out.println(field.countShips());

        field.setPosition(35);
        field.setOwnedFields(1);
        field.setPosition(5);
        field.countShips();
        System.out.println(field.countShips());


    }
}