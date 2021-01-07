package Test;

import Game.Model.OwnableField;
import org.junit.jupiter.api.Test;

class OwnableFieldTest {

    @Test
    void isOwnable() {
        OwnableField field1 = new OwnableField();
        field1.setColor("Red");
        System.out.println(field1.getColor());
        System.out.println(field1.isOwnable());
    }

    @Test
    void setOwnable() {
    }
}