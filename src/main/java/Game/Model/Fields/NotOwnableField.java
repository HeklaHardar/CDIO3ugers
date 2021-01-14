package Game.Model.Fields;

public class NotOwnableField extends Field {

    public NotOwnableField(int position) {
        super(position);
        ownable = 0;
    }
}