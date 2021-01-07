package Game.Model;

public class GoToPrison extends NotOwnableField{
    protected boolean goToPrison = true;

    public boolean isGoToPrison() {
        return goToPrison;
    }

    public void setGoToPrison(boolean goToPrison) {
        this.goToPrison = goToPrison;
    }
}
