package Game.Model;

public class NotOwnableField extends Field {
    protected boolean ownable = false;

    public boolean isOwnable() {
        return ownable;
    }

    public void setOwnable(boolean ownable) {
        this.ownable = ownable;
    }
}
