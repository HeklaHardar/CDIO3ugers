package Test;

import Game.Model.Dices;
import Game.controller.FieldController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BreweryTest {

    @Test
    void BreweryRent(){


        Dices dices = new Dices();
        FieldController fieldController = new FieldController();

        fieldController.setPosition(12);
        fieldController.setOwnedFields(1);

        for (int i = 0; i < 20 ; i++) {
            dices.roll();
            assertEquals(dices.getValue()*4,fieldController.calculateRent(dices.getValue(),false));
        }

        fieldController.setPosition(28);
        fieldController.setOwnedFields(1);

        for (int i = 0; i < 20 ; i++) {
            dices.roll();
            assertEquals(dices.getValue()*10,fieldController.calculateRent(dices.getValue(),false));
        }

    }

}
