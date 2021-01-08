package Game.Model;

public class GoToPrison extends NotOwnableField{
    protected boolean inPrison = true;

    public boolean isGoToPrison() {
        return inPrison;
    }

    public void setGoToPrison(boolean goToPrison) {
        this.inPrison = inPrison;
    }
}
