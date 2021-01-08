package Game.Model.Fields;

public class GoToPrison extends NotOwnableField {
    protected boolean inPrison = true;

    public boolean isGoToPrison() {
        return inPrison;
    }

    public void setGoToPrison(boolean goToPrison) {
        this.inPrison = inPrison;
    }
}
