package Game.controller;

import Game.Model.*;
import Game.Model.Fields.*;

public class FieldController {

    private Field[] fields = {new Start(),new BuildableField(),new ChanceCard(), new BuildableField(), new IncomeTax(), new ShippingLine()
    , new BuildableField(), new ChanceCard(), new BuildableField(), new BuildableField(), new Prison(), new BuildableField(), new Brewery(),
    new BuildableField(), new BuildableField(), new ShippingLine(), new BuildableField(), new ChanceCard(), new BuildableField(),new BuildableField()
    ,new SafeZone(), new BuildableField(), new ChanceCard(), new BuildableField(), new BuildableField(), new ShippingLine(), new BuildableField()
    ,new BuildableField(), new Brewery(), new BuildableField(), new GoToPrison(), new BuildableField(), new BuildableField(), new ChanceCard(), new BuildableField()
    , new ShippingLine(), new ChanceCard(), new BuildableField(), new IncomeTax(), new BuildableField()};

    private String[] fieldColors = {"","Blue","", "Blue", "", "Ship"
            , "Orange", "", "Orange", "Orange", "", "Yellow", "Brewery",
            "Yellow", "Yellow", "Ship", "Grey", "", "Grey","Grey"
            ,"", "Red","", "Red", "Red", "Ship", "Sand"
            ,"Sand", "Brewery", "Sand", "", "Yellow", "Yellow", "", "Yellow"
            , "Ship", "", "Purple", "", "Purple"};

    private int[] fieldValues = {0,60,0,60, 200, 200
            , 100, 0, 100, 120, 0, 140, 150,
            140, 160, 200, 180, 0, 180,200
            ,0, 220, 0, 220, 240, 200, 260
            ,260, 150, 280, 0, 300, 300, 0, 320
            , 200, 0, 350, 200, 400};

    private String[] fieldTitles = {"Start","Rødovrevej", "", "Hvidovre", "", "Øresund A/S", "Roskildevej", "", "Valby \n Langgade", "Allégade", "", "Frederiksberg \n Allé", "Tuborg",
            "Bülowsvej", "Gl. Kongevej", "D. F. D. S.", "Bernstoffsvej", "", "Hellerupvej", "Strandvej", "Helle", "Trianglen", "", "Østerbro-\ngade \n", "Grønningen", "Y. K.",
            "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "", "Amagertorv", "Vimmelskaftet", "", "Nygade", "D/S Bornholm 1866 \n", "", "Frederiks-\nberggade \n", "", "Rådhus-\npladsen \n"};

    private int[] ownedFields = new int[40];

    private int[] rent = {0,2,0,4,0,25
            , 6, 0, 6, 8, 0, 10, 15, 10, 12, 25, 14, 0, 14,16
            ,0, 18, 0, 18, 20, 25, 22
            ,22, 15, 22, 0, 26, 26, 0, 28
            , 25, 0, 35, 0, 50};
    private boolean inPrison = false;
    private int position = 1;

    public Field[] createFields(){
        int fieldcount = 0;
        for(Field field:fields){

            if(field instanceof OwnableField){
                field.setName(fieldTitles[fieldcount]);
                ((OwnableField) field).setColor(fieldColors[fieldcount]);
                ((OwnableField) field).setRent(rent[fieldcount]);
                field.setValue(fieldValues[fieldcount]);
            }
            else if(field instanceof ShippingLine){
                field.setName(fieldTitles[fieldcount]);
            }
            else if(field instanceof Brewery){
                field.setName(fieldTitles[fieldcount]);
            }

            fieldcount = fieldcount+1;
        }
        return fields;



    }
    public int isOwnable(){
        return fields[position].isOwnable();
    }
    public void resetPrisonStatus(){
        this.inPrison = false;

    }

    public void setPosition(int position){
        this.position = position;
    }

    public Field[] getFields() {
        return fields;
    }

    public int getPosition() {
        return position;
    }

    public int calculateValue() {

            int value = fields[position].getValue();
            if(fields[position] instanceof IncomeTax){
                return value;

            }
            if(fields[position].isOwnable()!=1){
                return 0;
            }
            if (fields[position] instanceof OwnableField) {
                for (int i = 1; i <= 39; i++) {
                    if (fields[i] instanceof OwnableField) {
                        if ((i != position) && ((OwnableField) fields[i]).getColor() == ((OwnableField) fields[position]).getColor()
                                && ownedFields[i] == ownedFields[position] && ownedFields[i] != 0) {
                            value = fields[position].getValue() * 2;
                        }

                    }
                }

            }
            return value;
    }
    public int calculateRent() {

        int rent = ((OwnableField) fields[position]).getRent();
        try {

            if (fields[position] instanceof OwnableField) {

                for (int i = 0; i <= 39; i++) {
                    int colorCount = 0;
                    if (fields[i] instanceof OwnableField) {

                        //Hvis du ejer 2 af blå eller lilla
                        if ((i != position) && ((OwnableField) fields[i]).getColor() == ((OwnableField) fields[position]).getColor()
                                && ownedFields[i] == ownedFields[position] && ownedFields[i] != 0) {
                            if ((((OwnableField) fields[position]).getColor() == "Purple" || ((OwnableField) fields[position]).getColor() == "Blue")) {
                                rent = ((OwnableField) fields[position]).getRent() * 2;
                            } else {
                                int k = 0;
                                for (String color : fieldColors) {
                                    if (color == ((OwnableField) fields[position]).getColor() && ownedFields[k] == ownedFields[position] && ownedFields[k] != 0 && k!=position) {
                                        colorCount = colorCount + 1;
                                    }
                                    k++;
                                }
                                if (colorCount == 2) {
                                    rent = ((OwnableField) fields[position]).getRent() * 2;
                                }

                            }
                        }
                    }
                }
            }
        }
        catch (ClassCastException e){ return 0; }
        return rent;
    }
    public String getColor(){
        return ((OwnableField)fields[position]).getColor();
    }
    public int getValue() {
        return fields[position].getValue();
    }

    public int getRent() {
        if (fields[position] instanceof OwnableField){
            return  ((OwnableField) fields[position]).getRent();
        }
        else return 0;

    }

    public int getOwningStatus() {
        if(fields[position] instanceof OwnableField){
            return ownedFields[position];
        }
            return 10;
    }


    public boolean isInPrison() {
        if(fields[position] instanceof GoToPrison) {
            return inPrison = true;
        }
        else{
            return false;
        }
    }

    public String[] getFieldColors() {
        return fieldColors;
    }

    public int[] getFieldValues() {
        return fieldValues;
    }

    public String[] getFieldTitles() {
        return fieldTitles;
    }

    public int[] getOwnedFields() {
        return ownedFields;
    }

    public boolean getdrawCard(){
        boolean drawcard = false;
        if(fields[position] instanceof ChanceCard){
            drawcard = true;
        };
        return drawcard;
    }


    public void setOwnedFields(int playerNumber) {
        if(fields[position]instanceof OwnableField) {
            if (ownedFields[position] == 0) {
                ownedFields[position] = playerNumber;
            }
        }
    }
}
