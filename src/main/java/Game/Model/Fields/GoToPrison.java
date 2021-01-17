package Game.Model.Fields;

public class GoToPrison extends NotOwnableField {
    protected boolean inPrison = true;

    public GoToPrison(int position) {
        super(position);
    }

    public boolean isGoToPrison() {
        return inPrison;
    }
}