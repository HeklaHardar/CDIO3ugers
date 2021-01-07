package Game.Model;

public class OwnableField extends Field{

    protected boolean ownable = true;

    public boolean isOwnable() {
        return ownable;
    }

    public void setOwnable(boolean ownable) {
        this.ownable = ownable;
    }
}
