package Game.Model;

public class OwnableField extends Field{

    protected String color;
    protected boolean ownable = true;
    protected String owner = "";

    public boolean isOwnable() {
        return ownable;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
