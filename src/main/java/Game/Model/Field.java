package Game.Model;

public class Field {
    protected int value;
    protected String name;
    protected int ownable;

    public int isOwnable() {
        return ownable;
    }

    public void setOwnable(int ownable) {
        this.ownable = ownable;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
