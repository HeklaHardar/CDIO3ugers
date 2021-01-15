package Game.Model.Fields;

public class BuildableField extends OwnableField{

    protected int houseCost;

    public BuildableField(int position) {
        super(position);
        this.houseCost = fieldProperties.getHouseCosts()[position];
    }

    public int getHouseCost() {
        return houseCost;
    }

    public int getHouses(){
        return fieldProperties.getHouses()[position];
    }

    public int[][] getRentPrices(){
        return fieldProperties.getRentPrices();
    }

    public void buildHouse(){
        fieldProperties.setHouses(position);
    }
}