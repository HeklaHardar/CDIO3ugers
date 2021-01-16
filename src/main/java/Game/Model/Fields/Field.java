package Game.Model.Fields;

public class Field {

    protected String name;
    protected int ownable;
    protected int position;
    protected static FieldProperties fieldProperties = new FieldProperties();

    public Field(int position) {
        this.position = position;
        this.name = fieldProperties.getFIELDTITLES()[position];
    }


    public int getPosition() {
        return position;
    }

    public int isOwnable() {
        return ownable;
    }

    public String getName() {
        return name;
    }

}