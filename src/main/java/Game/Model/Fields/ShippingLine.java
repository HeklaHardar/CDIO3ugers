package Game.Model.Fields;

public class ShippingLine extends OwnableField {

    public ShippingLine(int position) {
        // int[] multirent;
        super(position);
        value = 200;
        rent = 25;

        color = "Shipping";
    }
}