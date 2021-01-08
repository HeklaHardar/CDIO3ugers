package Game.Model.Fields;

import Game.Model.Fields.Field;

public class OwnableField extends Field {
    protected String color;
    protected int rent;

    public OwnableField() {
        ownable = 1;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
