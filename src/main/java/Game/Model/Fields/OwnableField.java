package Game.Model.Fields;

public class OwnableField extends Field {
    protected String color;
    protected int rent;
    protected int value;


    public OwnableField(int position) {
        super(position);
        value = fieldProperties.getFieldValues()[position];
        rent = fieldProperties.getRentPrices()[position][0];
        color = fieldProperties.getFieldColors()[position];
        ownable = 1;
    }
    public int getOwner() {
        return fieldProperties.getOwner(position);
    }

    public void setOwner(int ownerNumber) {
        fieldProperties.setOwner(position,ownerNumber);
    }

    public int getValue() {
        return value;
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