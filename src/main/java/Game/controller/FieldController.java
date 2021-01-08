package Game.controller;

import Game.Model.*;

public class FieldController {
    private Field[] fields = new Field[40];
    private int[] ownedFields = new int[40];
    private int position = 0;
    public Field[] createFields(){
        int fieldcount = 0;
        for(Field field:fields){

            if(fieldcount == 1 ||fieldcount == 6 ||fieldcount == 16 ||fieldcount == 21
                    ||fieldcount == 32 || fieldcount == 35 ){
                fields[fieldcount] = new ChanceCard();
            }
            else if(fieldcount == 4 ||fieldcount == 14 ||fieldcount == 24 ||fieldcount == 34){
                fields[fieldcount] = new ShippingLine();
                ((ShippingLine)fields[fieldcount]).setRent(25);
            }
            else if(fieldcount == 3){
                fields[fieldcount] = new IncomeTax();
            }

            else if(fieldcount == 10){
                fields[fieldcount] = new Prison();
            }
            else if(fieldcount == 19){
                fields[fieldcount] = new SafeZone();
            }
            else if(fieldcount == 11||fieldcount == 27){
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

                    case 2:
                        own1.setColor("Blue");
                        own1.setName("Hvidovrevej");
                        own1.setValue(60);
                        own1.setRent(4);
                        fields[fieldcount]=own1;
                        break;

                    case 5:
                        own1.setColor("Orange");
                        own1.setName("Roskildevej");
                        own1.setValue(100);
                        own1.setRent(6);
                        fields[fieldcount]=own1;
                        break;

                    case 7:
                        own1.setColor("Orange");
                        own1.setName("Valby Langade");
                        own1.setValue(100);
                        own1.setRent(6);
                        fields[fieldcount]=own1;
                        break;

                    case 8:
                        own1.setColor("Orange");
                        own1.setName("Allegade");
                        own1.setValue(120);
                        own1.setRent(8);
                        fields[fieldcount]=own1;
                        break;

                    case 10:
                        own1.setColor("Brown");
                        own1.setName("Frederiksberg Alle");
                        own1.setValue(140);
                        own1.setRent(10);
                        fields[fieldcount]=own1;
                        break;

                    case 12:
                        own1.setColor("Brown");
                        own1.setName("Bülowsvej");
                        own1.setValue(140);
                        own1.setRent(10);
                        fields[fieldcount]=own1;
                        break;

                    case 13:
                        own1.setColor("Brown");
                        own1.setName("Gl. Kongevej");
                        own1.setValue(160);
                        own1.setRent(12);
                        fields[fieldcount]=own1;
                        break;

                    case 15:
                        own1.setColor("Gray");
                        own1.setName("Bernstoffervej");
                        own1.setValue(180);
                        own1.setRent(14);
                        fields[fieldcount]=own1;
                        break;

                    case 17:
                        own1.setColor("Gray");
                        own1.setName("Hellerupvej");
                        own1.setValue(180);
                        own1.setRent(14);
                        fields[fieldcount]=own1;
                        break;

                    case 18:
                        own1.setColor("Gray");
                        own1.setName("Strandvej");
                        own1.setValue(200);
                        own1.setRent(16);
                        fields[fieldcount]=own1;
                        break;

                    case 20:
                        own1.setColor("Red");
                        own1.setName("Trianglen");
                        own1.setValue(220);
                        own1.setRent(18);
                        fields[fieldcount]=own1;
                        break;

                    case 22:
                        own1.setColor("Red");
                        own1.setName("Østerbrogade");
                        own1.setValue(220);
                        own1.setRent(18);
                        fields[fieldcount]=own1;
                        break;

                    case 23:
                        own1.setColor("Red");
                        own1.setName("Grønningen");
                        own1.setValue(240);
                        own1.setRent(20);
                        fields[fieldcount]=own1;
                        break;

                    case 25:
                        own1.setColor("Sand");
                        own1.setName("Bredgade");
                        own1.setValue(260);
                        own1.setRent(22);
                        fields[fieldcount]=own1;
                        break;

                    case 26:
                        own1.setColor("Sand");
                        own1.setName("Kg. Nytorv");
                        own1.setValue(260);
                        own1.setRent(22);
                        fields[fieldcount]=own1;
                        break;

                    case 28:
                        own1.setColor("Sand");
                        own1.setName("Østergade");
                        own1.setValue(280);
                        own1.setRent(22);
                        fields[fieldcount]=own1;
                        break;

                    case 30:
                        own1.setColor("Yellow");
                        own1.setName("Amager Torv");
                        own1.setValue(300);
                        own1.setRent(26);
                        fields[fieldcount]=own1;
                        break;

                    case 31:
                        own1.setColor("Yellow");
                        own1.setName("Vimmelskaftet");
                        own1.setValue(300);
                        own1.setRent(26);
                        fields[fieldcount]=own1;
                        break;

                    case 33:
                        own1.setColor("Yellow");
                        own1.setName("Nygade");
                        own1.setValue(320);
                        own1.setRent(28);
                        fields[fieldcount]=own1;
                        break;

                    case 36:
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

                    case 40:
                        Start s1 = new Start();
                        fields[fieldcount]=s1;
                        break;
                }

            }
            fieldcount = fieldcount+1;
        }
        return fields;
    }

    public void setPosition(int position){
        this.position = position;
    }
    public int calculateValue() {
        int value = 0;
        for (int i=0 ; i <= 39; i++){
            if(fields[i] instanceof OwnableField){
                if((i!=position) && ((OwnableField) fields[i]).getColor() == ((OwnableField) fields[position]).getColor()
                        && ownedFields[i] == ownedFields[position] && ownedFields[i]!=0){
                    value = fields[i].getValue()*2;
                }
                else{
                    value=fields[i].getValue();
                }
            }
        }
        return value;
    }
    public int calculateRent() {
        int rent = 0;
        for (int i=0 ; i <= 39; i++){
            if(fields[i] instanceof OwnableField){
                if((i!=position) && ((OwnableField) fields[i]).getColor() == ((OwnableField) fields[position]).getColor()
                        && ownedFields[i] == ownedFields[position] && ownedFields[i]!=0){
                    rent = ((OwnableField)fields[i]).getRent()*2;
                }
                else{
                    rent=((OwnableField)fields[i]).getRent();
                }
            }
        }
        return rent;
    }

    public int getValue() {
        return fields[position].getValue();
    }
    public int getRent() {
        int rent = 0;
        if(fields[position]instanceof OwnableField){
            ((OwnableField) fields[position]).getRent();
        }
        return rent;
    }

    public int getOwningStatus() {
        if(fields[position] instanceof OwnableField){
            return ownedFields[position];
        }
        else{
            return 10;
        }
    }



    public boolean isInPrison() {
        if(fields[position] instanceof GoToPrison) {
            return ((GoToPrison) fields[position]).isGoToPrison();
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
