package Test;

import Game.controller.FieldController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldControllerTest {

    @Test
    void getFields() {
        FieldController field = new FieldController();
        System.out.println(field.getFields().length);
        System.out.println(field.getRent2().length);

    }

    @Test
    void getRent2() {
    }
}