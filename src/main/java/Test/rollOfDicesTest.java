package Test;

import Game.controller.rollOfDices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class rollOfDicesTest {

    @Test
    void twoOfTheSame() {
        rollOfDices rollequals = new rollOfDices();
        assertEquals(true,rollequals.twoOfTheSame(1,1));
    }
}