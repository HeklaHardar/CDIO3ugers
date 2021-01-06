package Game.Model;

public class Fieldproperties {
    private int value;
    private int owningStatus = 1;
    private int[] ownedFields = new int[40];
    private String[] fieldColors = {"","Blue","","Blue","","Rederi","Orange","","Orange","Orange","","Brown",
            "Bryggeri","Brown","Brown","Rederi","Grey","","Grey","Grey","","Red","","Red","Red","Rederi","Sand","Sand","Bryggeri","Sand","","Yellow","Yellow","","Yellow","Rederi","","Purple","","Purple"};

    private String color;
    private boolean inPrison = false;
    private boolean drawCard = false;


    //owningStatus: 1 = ownable, 2 = owned, 3 = not ownable
    public void Fieldproperties(int position){
        switch (position){
            case 1:
                // Rødovrevej
                this.value = 60;
                this.owningStatus = 1;
                this.color = "Blue";
                break;
            case 2:
            case 7:
            case 17:
            case 22:
            case 33:
            case 36:
                // Prøv lykken
                this.value = 0;
                this.owningStatus = 3;
                this.drawCard = true;
                break;
            case 3:
                // Hvidovre
                this.value = 60;
                this.owningStatus = 1;
                this.color = "Blue";
                break;
            case 4:
                // Indkomsskat
                this.owningStatus = 3;
                break;

            case 5:
                // Øresund rederi
                this.value = 200;
                this.owningStatus = 1;
                this.color = "Rederi";
                break;

            case 6:
            // Roskildevej
                this.value = 100;
                this.owningStatus = 1;
                this.color = "Orange";
                break;

            case 8:
             // Valby Langgade
                this.value = 100;
                this.owningStatus = 1;
                this.color = "Orange";
                break;

            case 9:
                // Allégade
                this.value = 120;
                this.owningStatus = 1;
                this.color = "Orange";
                break;

            case 10:
                // Fængsel
                this.owningStatus = 3;

                break;

            case 11:
                //Frederiksberg Allé
                this.value = 140;
                this.owningStatus = 1;
                this.color = "Brown";
                break;

            case 12:
                // Tuborg Bryggeri
                this.value = 150;
                this.owningStatus = 1;
                this.color = "Bryggeri";
                break;

            case 13:
                //Bülowsvej
                this.value = 140;
                this.owningStatus = 1;
                this.color = "Brown";
                break;

            case 14:
                //Gl. Kongevej
                this.value = 160;
                this.owningStatus = 1;
                this.color = "Brown";
                break;

            case 15:
                // D.F.D.s. rederi
                this.value = 200;
                this.owningStatus = 1;
                this.color = "Rederi";
                break;

            case 16:
                //Bernstoffsvej
                this.value = 180;
                this.owningStatus = 1;
                this.color = "Grey";
                break;

            case 18:
                //Hellerupvej
                this.value = 180;
                this.owningStatus = 1;
                this.color = "Grey";
                break;

            case 19:
                //Strandvej
                this.value = 200;
                this.owningStatus = 1;
                this.color = "Grey";
                break;

            case 20:
                //Helle
                this.owningStatus = 3;
                break;

            case 21:
                //Trianglen
                this.value = 220;
                this.owningStatus = 1;
                this.color = "Red";
                break;

            case 23:
                //Østerbrogade
                this.value = 220;
                this.owningStatus = 1;
                this.color = "Red";
                break;

            case 24:
                //Grønningen
                this.value = 240;
                this.owningStatus = 1;
                this.color = "Red";
                break;

            case 25:
                // Y.K rederi
                this.value = 200;
                this.owningStatus = 1;
                this.color = "Rederi";
                break;

            case 26:
                //Bredgade
                this.value = 260;
                this.owningStatus = 1;
                this.color = "Sand";
                break;

            case 27:
                //Kg. Nytorv
                this.value = 260;
                this.owningStatus = 1;
                this.color = "Sand";
                break;

            case 28:
                // Carlsberg Bryggeri
                this.value = 150;
                this.owningStatus = 1;
                this.color = "Bryggeri";
                break;

            case 29:
                //Østergade
                this.value = 280;
                this.owningStatus = 1;
                this.color = "Sand";
                break;

            case 30:
                // Gå i fængsel
                this.owningStatus = 3;
                this.inPrison = true;
                this.value = 0;
                break;

            case 31:
                //Amagertorv
                this.value = 300;
                this.owningStatus = 1;
                this.color = "Yellow";
                break;

            case 32:
                //Vimmelskaftet
                this.value = 300;
                this.owningStatus = 1;
                this.color = "Yellow";
                break;

            case 34:
                //Nygade
                this.value = 320;
                this.owningStatus = 1;
                this.color = "Yellow";
                break;

            case 35:
                // Bornholm rederi
                this.value = 200;
                this.owningStatus = 1;
                this.color = "Rederi";
                break;

            case 37:
                //Frederiksberggade
                this.value = 350;
                this.owningStatus = 1;
                this.color = "Purple";
                break;

            case 38:
                //Ekstraordinær statsskat
                this.owningStatus = 3;
                break;
            case 39:
                //Rådhuspladsen
                this.value = 400;
                this.owningStatus = 1;
                this.color = "Purple";
                break;

            default:
                this.owningStatus=3;

        }



    }
    public int calculateValue(int position) {
        for (int i=0 ; i <= 38; i++){
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
