package Game.Model.Fields;

public class ExtraordinaryTax extends NotOwnableField{
protected int tax;
    public ExtraordinaryTax(int position) {
        super(position);
        tax = -100;
    }
    public int getTax() {
        return tax;
    }
}
