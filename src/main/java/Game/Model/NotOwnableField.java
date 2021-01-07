package Game.Model;

public class NotOwnableField extends OwnableField {
    protected boolean ownable = false;

    @Override
    public boolean isOwnable() {
        return ownable;
    }

    @Override
    public void setOwnable(boolean ownable) {
        this.ownable = ownable;
    }
}
