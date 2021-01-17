package Game.Model.Fields;

public class IncomeTax extends NotOwnableField {
    protected int tax = 200;

    public IncomeTax(int position) {
        super(position);
    }

    public int getTax() {
        return tax;
    }
}