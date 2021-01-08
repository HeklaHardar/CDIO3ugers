package Game.Model;

public class OwnableField extends Field{
    protected String color;
    protected boolean ownable = true;
    protected int rent;


    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public boolean isOwnable() {
        return ownable;
    }

    public void setOwnable(boolean ownable) {
        this.ownable = ownable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
