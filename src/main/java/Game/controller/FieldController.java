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

            if(fieldcount == 2 ||fieldcount == 7 ||fieldcount == 17 ||fieldcount == 22
                    ||fieldcount == 33 || fieldcount == 36 ){
                fields[fieldcount] = new ChanceCard();
            }
            else if(fieldcount == 5 ||fieldcount == 15 ||fieldcount == 25 ||fieldcount == 35){
                fields[fieldcount] = new ShippingLine();
            }
            else if(fieldcount == 4 || fieldcount == 38){
                fields[fieldcount] = new IncomeTax();
            }

            else if(fieldcount == 10){
                fields[fieldcount] = new Prison();
            }
            else if(fieldcount == 30){
                fields[fieldcount] = new GoToPrison();
            }
            else if(fieldcount == 20){
                fields[fieldcount] = new SafeZone();
            }
            else if(fieldcount == 12||fieldcount == 28){
                fields[fieldcount] = new Brewery();
                ((Brewery)fields[fieldcount]).setRent(15);
            }
            else{
                OwnableField own1 = new OwnableField();
                switch (fieldcount){

                    case 1:

                        own1.setColor("Blue");
                        own1.setName("Rødovrevej");
                        own1.setRent(2);
                        own1.setValue(60);
                        fields[fieldcount]=own1;
                        break;

                    case 3:
                        own1.setColor("Blue");
                        own1.setName("Hvidovrevej");
                        own1.setValue(60);
                        own1.setRent(4);
                        fields[fieldcount]=own1;
                        break;

                    case 6:
                        own1.setColor("Orange");
                        own1.setName("Roskildevej");
                        own1.setValue(100);
                        own1.setRent(6);
                        fields[fieldcount]=own1;
                        break;

                    case 8:
                        own1.setColor("Orange");
                        own1.setName("Valby Langade");
                        own1.setValue(100);
                        own1.setRent(6);
                        fields[fieldcount]=own1;
                        break;

                    case 9:
                        own1.setColor("Orange");
                        own1.setName("Allegade");
                        own1.setValue(120);
                        own1.setRent(8);
                        fields[fieldcount]=own1;
                        break;

                    case 11:
                        own1.setColor("Brown");
                        own1.setName("Frederiksberg Alle");
                        own1.setValue(140);
                        own1.setRent(10);
                        fields[fieldcount]=own1;
                        break;

                    case 13:
                        own1.setColor("Brown");
                        own1.setName("Bülowsvej");
                        own1.setValue(140);
                        own1.setRent(10);
                        fields[fieldcount]=own1;
                        break;

                    case 14:
                        own1.setColor("Brown");
                        own1.setName("Gl. Kongevej");
                        own1.setValue(160);
                        own1.setRent(12);
                        fields[fieldcount]=own1;
                        break;

                    case 16:
                        own1.setColor("Gray");
                        own1.setName("Bernstoffervej");
                        own1.setValue(180);
                        own1.setRent(14);
                        fields[fieldcount]=own1;
                        break;

                    case 18:
                        own1.setColor("Gray");
                        own1.setName("Hellerupvej");
                        own1.setValue(180);
                        own1.setRent(14);
                        fields[fieldcount]=own1;
                        break;

                    case 19:
                        own1.setColor("Gray");
                        own1.setName("Strandvej");
                        own1.setValue(200);
                        own1.setRent(16);
                        fields[fieldcount]=own1;
                        break;

                    case 21:
                        own1.setColor("Red");
                        own1.setName("Trianglen");
                        own1.setValue(220);
                        own1.setRent(18);
                        fields[fieldcount]=own1;
                        break;

                    case 23:
                        own1.setColor("Red");
                        own1.setName("Østerbrogade");
                        own1.setValue(220);
                        own1.setRent(18);
                        fields[fieldcount]=own1;
                        break;

                    case 24:
                        own1.setColor("Red");
                        own1.setName("Grønningen");
                        own1.setValue(240);
                        own1.setRent(20);
                        fields[fieldcount]=own1;
                        break;

                    case 26:
                        own1.setColor("Sand");
                        own1.setName("Bredgade");
                        own1.setValue(260);
                        own1.setRent(22);
                        fields[fieldcount]=own1;
                        break;

                    case 27:
                        own1.setColor("Sand");
                        own1.setName("Kg. Nytorv");
                        own1.setValue(260);
                        own1.setRent(22);
                        fields[fieldcount]=own1;
                        break;

                    case 29:
                        own1.setColor("Sand");
                        own1.setName("Østergade");
                        own1.setValue(280);
                        own1.setRent(22);
                        fields[fieldcount]=own1;
                        break;

                    case 31:
                        own1.setColor("Yellow");
                        own1.setName("Amager Torv");
                        own1.setValue(300);
                        own1.setRent(26);
                        fields[fieldcount]=own1;
                        break;

                    case 32:
                        own1.setColor("Yellow");
                        own1.setName("Vimmelskaftet");
                        own1.setValue(300);
                        own1.setRent(26);
                        fields[fieldcount]=own1;
                        break;

                    case 34:
                        own1.setColor("Yellow");
                        own1.setName("Nygade");
                        own1.setValue(320);
                        own1.setRent(28);
                        fields[fieldcount]=own1;
                        break;

                    case 37:
                        own1.setColor("Purple");
                        own1.setName("Frederiksberg");
                        own1.setValue(350);
                        own1.setRent(35);
                        fields[fieldcount]=own1;
                        break;

                    case 39:
                        own1.setColor("Purple");
                        own1.setName("Rådhuspladsen");
                        own1.setValue(400);
                        own1.setRent(50);
                        fields[fieldcount]=own1;
                        break;

                    case 0:
                        Start s1 = new Start();
                        fields[fieldcount]=s1;
                        break;
                }

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
    public int[] getRent2(){
        return rent;
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
        try{
        if(fields[position] instanceof OwnableField) {
            for (int i = 0; i <= 39; i++) {
                if (fields[i] instanceof OwnableField) {
                    if ((i != position) && ((OwnableField) fields[i]).getColor() == ((OwnableField) fields[position]).getColor()
                            && ownedFields[i] == ownedFields[position] && ownedFields[i] != 0) {
                        rent = ((OwnableField) fields[position]).getRent() * 2;
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
           return  ((OwnableField) fields[position]).getRent();
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
