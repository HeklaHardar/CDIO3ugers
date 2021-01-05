package Game.Model;

public class Fieldproperties {
    private int value;
    private int owningStatus = 1;
    private int[] ownedFields = new int[24];
    private String[] fieldColors = {"","Green","Green","","Cyan","Cyan","","Pink","Pink","","Orange","Orange",
            "","Red","Red","","Yellow","Yellow","","DarkGreen","DarkGreen","","Blue","Blue"};
    private String color;
    private boolean inPrison = false;
    private boolean drawCard = false;


    //owningStatus: 1 = ownable, 2 = owned, 3 = not ownable
    public void Fieldproperties(int position){
        switch (position){
            case 1:
            case 2:
                this.value = 1;
                this.owningStatus = 1;
                this.color = "Green";

                break;
            case 3:
            case 9:
            case 15:
            case 21:
                this.value = 0;
                this.owningStatus = 3;
                this.drawCard = true;
                break;
            case 4:
            case 5:
                this.value = 1;
                this.owningStatus = 1;
                this.color = "Cyan";
                break;
            case 6:
            case 12:
            case 0:
                this.value = 0;
                this.owningStatus = 3;
                break;
            case 7:
            case 8:
                this.value = 2;
                this.owningStatus = 1;
                this.color = "Pink";
                break;
            case 10:
            case 11:
                this.value = 2;
                this.owningStatus = 1;
                this.color = "Orange";
                break;
            case 13:
            case 14:
                this.value = 3;
                this.owningStatus = 1;
                this.color = "Red";
                break;
            case 16:
            case 17:
                this.value = 3;
                this.owningStatus = 1;
                this.color = "Yellow";
                break;
            case 18:
                this.owningStatus = 3;
                this.inPrison = true;
                this.value = 0;
                break;
            case 19:
            case 20:
                this.value = 4;
                this.owningStatus = 1;
                this.color = "DarkGreen";
                break;
            case 22:
            case 23:
                this.value = 5;
                this.owningStatus = 1;
                this.color = "Blue";
                break;


        }



    }
    public int calculateValue(int position) {
        for (int i=0 ; i <= 23; i++){
            if((i!=position) && fieldColors[i] == fieldColors[position] && (ownedFields[i] == ownedFields[position] && ownedFields[i]!=0)){
                    value = value*2;
            }
        }
        return value;
    }

    public int getValue() {
        return value;
    }

    public int getOwningStatus() {
        return owningStatus;
    }


    public boolean isInPrison() {
        return inPrison;
    }

    public void resetPrisonStatus(){
        this.inPrison = false;

    }

    public int[] getOwnedFields() {
        return ownedFields;
    }

    public boolean getdrawCard(){
        return drawCard;
    }


    public void setOwnedFields(int[] ownedFields, int position, int player) {
        if(this.ownedFields[position]==0) {
            this.ownedFields[position] = player + 1;
        }
    }

}
