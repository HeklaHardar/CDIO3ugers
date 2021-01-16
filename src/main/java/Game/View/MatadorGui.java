package Game.View;

import Game.Model.Fields.Brewery;
import Game.Model.Fields.OwnableField;
import Game.controller.FieldController;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class MatadorGui {

    public GUI gui;
    private GUI_Field[] fields = new GUI_Field[40];
    private GUI_Player[] player = new GUI_Player[6];
    private GUI_Car[] car = new GUI_Car[6];
    private Color[] colors = {Color.black, new Color(175, 4, 182), new Color(255, 255, 255), new Color(139, 33, 33), new Color(0, 255, 0), new Color(0, 255, 225)};
    private GUI_Car.Type[] type = {GUI_Car.Type.CAR, GUI_Car.Type.RACECAR, GUI_Car.Type.UFO, GUI_Car.Type.TRACTOR, GUI_Car.Type.CAR, GUI_Car.Type.RACECAR};
    private String stringChoice;
    private String[] fieldTitles = {"Rødovrevej", "", "Hvidovre", "", "Øresund A/S", "Roskildevej", "", "Valby \n Langgade", "Allégade", "", "Frederiksberg \n Allé", "Tuborg",
            "Bülowsvej", "Gl. Kongevej", "D. F. D. S.", "Bernstoffsvej", "", "Hellerupvej", "Strandvej", "Helle", "Trianglen", "", "Østerbro-\ngade \n", "Grønningen", "Y. K.",
            "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "", "Amagertorv", "Vimmelskaftet", "", "Nygade", "D/S Bornholm 1866 \n", "", "Frederiks-\nberggade \n", "", "Rådhus-\npladsen \n"};

    public MatadorGui() {

        // Laver array
        Color[] fieldColors = {new Color(0, 95, 167), new Color(255, 65, 0), new Color(78, 48, 0, 255),
                new Color(104, 105, 91), new Color(213, 31, 23), new Color(255, 227, 147), new Color(252, 184, 27), new Color(109, 50, 144)};

        // Laver orange startfelt
        fields[0] = new GUI_Start("Start", "", "Her starter du", Color.ORANGE, Color.WHITE);

        // Fylder resten af feltenre ud med veje
        for (int i = 1; i < 40; i++) {
            if (i == 2 || i == 7 || i == 17 || i == 22 || i == 33 || i == 36) {
                fields[i] = new GUI_Chance();
                fields[i].setDescription("Træk et chancekort");
            } else if (i == 4) {
                fields[i] = new GUI_Tax();
                fields[i].setTitle("Betal indkomstskat");
                fields[i].setSubText("10% eller 200 kr.");
                fields[i].setDescription("Betal indkomstskat \n 10% eller 200 kr.");
            } else if (i == 5) {
                fields[i] = new GUI_Shipping();
                fields[i].setTitle(fieldTitles[i - 1]);
                fields[i].setSubText("Pris: 200 kr.");
                fields[i].setDescription(fieldTitles[i - 1] + "\n Pris: 200 kr.");
            } else if (i == 10) {
                fields[i] = new GUI_Jail();
                fields[i].setSubText("Fængsel");
                fields[i].setDescription("Fængsel");
            } else if (i == 12) {
                fields[i] = new GUI_Brewery();
                fields[i].setTitle(fieldTitles[i - 1]);
                fields[i].setSubText("Pris: 150 kr.");
            } else if (i == 20) {
                fields[i] = new GUI_Refuge();
                fields[i].setTitle(fieldTitles[i - 1]);
                fields[i].setSubText(fieldTitles[i - 1]);
                fields[i].setDescription(fieldTitles[i - 1]);
            } else if (i == 15) {
                fields[i] = new GUI_Shipping();
                fields[i].setTitle(fieldTitles[i - 1]);
                fields[i].setSubText("Pris: 200 kr.");
                fields[i].setDescription(fieldTitles[i - 1] + "\n Pris: 200 kr.");
            } else if (i == 25) {
                fields[i] = new GUI_Shipping();
                fields[i].setTitle(fieldTitles[i - 1]);
                fields[i].setSubText("Pris: 200 kr.");
                fields[i].setDescription(fieldTitles[i - 1] + "\n Pris: 200 kr.");
            } else if (i == 28) {
                fields[i] = new GUI_Brewery();
                fields[i].setTitle(fieldTitles[i - 1]);
                fields[i].setSubText("Pris: 150 kr.");
            } else if (i == 30) {
                fields[i] = new GUI_Jail();
                fields[i].setSubText("Gå i fængsel");
                fields[i].setDescription("Ryk i fængsel");
            } else if (i == 35) {
                fields[i] = new GUI_Shipping();
                fields[i].setTitle(fieldTitles[i - 1]);
                fields[i].setSubText("Pris: 200 kr.");
                fields[i].setDescription(fieldTitles[i - 1] + "\n Pris: 200 kr.");
            } else if (i == 38) {
                fields[i] = new GUI_Tax();
                fields[i].setTitle("Extraordinær \n statsskat");
                fields[i].setSubText("betal 100 kr.");
                fields[i].setDescription("Extraordinær \n statsskat \n betal 100 kr.");
            } else {
                fields[i] = new GUI_Street(
                        "Street " + i,
                        "Price: " + (i * 50),
                        "This is a street",
                        "100",
                        Color.RED,
                        Color.BLACK
                );
                fields[i].setTitle(fieldTitles[i - 1]);
                if (i == 1 || i == 3) {
                    fields[i].setBackGroundColor(fieldColors[0]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 60 kr.");
                } else if (i == 6 || i == 8) {
                    fields[i].setBackGroundColor(fieldColors[1]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 100 kr.");
                } else if (i == 9) {
                    fields[i].setBackGroundColor(fieldColors[1]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 120 kr.");
                } else if (i == 11 || i == 13) {
                    fields[i].setBackGroundColor(fieldColors[2]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 140 kr.");
                } else if (i == 14) {
                    fields[i].setBackGroundColor(fieldColors[2]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 160 kr.");
                } else if (i == 16 || i == 18) {
                    fields[i].setBackGroundColor(fieldColors[3]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 180 kr.");
                } else if (i == 19) {
                    fields[i].setBackGroundColor(fieldColors[3]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 200 kr.");
                } else if (i == 21 || i == 23) {
                    fields[i].setBackGroundColor(fieldColors[4]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 220 kr.");
                } else if (i == 24) {
                    fields[i].setBackGroundColor(fieldColors[4]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 240 kr.");
                } else if (i == 26 || i == 27) {
                    fields[i].setBackGroundColor(fieldColors[5]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 260 kr.");
                } else if (i == 29) {
                    fields[i].setBackGroundColor(fieldColors[5]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 280 kr.");
                } else if (i == 31 || i == 32) {
                    fields[i].setBackGroundColor(fieldColors[6]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 300 kr.");
                } else if (i == 34) {
                    fields[i].setBackGroundColor(fieldColors[6]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 320 kr.");
                } else if (i == 37) {
                    fields[i].setBackGroundColor(fieldColors[7]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 350 kr.");
                } else if (i == 39) {
                    fields[i].setBackGroundColor(fieldColors[7]);
                    fields[i].setDescription(fieldTitles[i - 1]);
                    fields[i].setSubText("Pris: 400 kr.");
                }
            }
        }
    }

    public void createGui() {
        this.gui = new GUI(fields, new Color(255, 255, 255));
        GUI.setNull_fields_allowed(true);
    }

    public void guiPlayers(String name, int balance, int i) {
        car[i] = new GUI_Car(colors[i], colors[i], type[i], GUI_Car.Pattern.CHECKERED);
        player[i] = new GUI_Player(name, balance, car[i]);
        gui.addPlayer(player[i]);
        fields[0].setCar(player[i], true);

    }

    public int getMoveDebug(){

        return gui.getUserInteger("Hvor mange felter vil du rykke?");

    }

    public void ShowDie(int DieGui, int Die2Gui) {
        gui.setDice(DieGui,0,1,7, Die2Gui,0,2,7);
    }

    public void moveCars(int currentPlayer, int currentPosition, int newPosition) {
        fields[currentPosition].setCar(player[currentPlayer], false);
        fields[newPosition].setCar(player[currentPlayer], true);
    }

    public void removeCar(int currentPlayer, int currentPosition){
        fields[currentPosition].setCar(player[currentPlayer],false);
    }

    public void moveToPrison(int currentPlayer, int currentPosition) {
        fields[currentPosition].setCar(player[currentPlayer], false);
        fields[10].setCar(player[currentPlayer], true);
    }

    public void updateGuiBalance(int i, int balance) {
        player[i].setBalance(balance);
    }

    public void showMessage(String message) {
        gui.showMessage(message);
    }

    public void landOnField(int currentPlayer, int currentField, String playerName, int ownable, int owner) {
        GUI_Field field = gui.getFields()[currentField];
        if (ownable == 1 && owner == 0) {
            GUI_Ownable o = (GUI_Ownable) field;
            o.setBorder(colors[currentPlayer]);
            o.setOwnerName(playerName);
           /* o.setSubText();*/

        } else return;
    }

    public void unsetField(int currentField, String price, int ownable){
        GUI_Field field = gui.getFields()[currentField];
        if (ownable == 1) {
            GUI_Ownable o = (GUI_Ownable)field;
            o.setBorder(Color.black);
            o.setOwnerName("");
            o.setSubText("Pris: " + price + " kr.");
        }


    }

    /*public void RentSubText(int FieldRent){

    }*/

    public void displayCard(String cardText) {
        gui.displayChanceCard(cardText);
    }

    public String getUserSelection(String msg, String... options){
        return gui.getUserSelection(msg, options);
    }

    public int getPlayerAction(String playerName, String[] Options) {
        stringChoice = gui.getUserButtonPressed("Det er spiller " + playerName +" s tur.",Options);
        switch (stringChoice) {
            case "Slå med terningerne":
                return 1;
            case "Bygninger":
                return 2;
            case "Pantsæt":
                return 3;
            case "Køb pantsætning tilbage":
                return 4;
            case "Sælg hus/hotel":
                return 5;
            case "Byg hus/hotel":
                return 6;
        }
        return 0;
    }

    public int getPlayLossActions (String playerName, String[] Options){

        stringChoice = gui.getUserButtonPressed(playerName +", du er løbet tør for penge, vælg en af mulighederne for at fortsætte spillet: ",Options);
        switch (stringChoice) {
            case "Sælg bygninger":
                return 1;
            case "Giv op":
                return 2;
            case "Pantsæt":
                return 3;

        }
        return 0;


    }

    public int getBuyField(String fieldName){
        stringChoice = gui.getUserButtonPressed("Vil du købe "+ fieldName +"?", "Ja", "Nej");
        switch (stringChoice){
            case "Ja":
                return 1;
            case "Nej":
                return 2;
        }
        return 0;
    }

    public void setMortgage(int position, int mortgageValues){

        fields[position].setSubText("PANTSAT");
        fields[position].setDescription("Pris: kr. "+ (mortgageValues * 1.1)+",00");
    }

    public void UnsetMortgage(int position,FieldController fieldpropertiesUnsetMortgage){

        fields[position].setSubText("Leje: " + ((OwnableField)fieldpropertiesUnsetMortgage.getFields()[position]).getRent() + " kr.");
        fields[position].setDescription(fieldTitles[position]);
    }

    public void RentOnField(FieldController fieldpropertiesRentonField){
        for (int y = 0; y < 40; y++){
            if (fieldpropertiesRentonField.getFields()[y] instanceof OwnableField && !(fieldpropertiesRentonField.getFields()[y] instanceof Brewery)) {
                if (((OwnableField) fieldpropertiesRentonField.getFields()[y]).getOwner() < 10 && ((OwnableField) fieldpropertiesRentonField.getFields()[y]).getOwner() != 0) {
                    fieldpropertiesRentonField.setPosition(y);
                    fields[y].setSubText("Leje: " + ( fieldpropertiesRentonField.calculateRent(1) + " kr."));
                }
            }
        }
    }



    public String getAuction(String fieldName) {
        stringChoice = gui.getUserButtonPressed("Er der en anden spiller der vil købe " + fieldName + "?", "Ja", "Nej");
        if (stringChoice.equals("Ja")) {
            return "Ja";
        }else
            return "No";
    }


    public void buyHouse(int position,int housecount, int owner, int playerNumber){
        if(gui.getFields()[position] instanceof GUI_Street && housecount>0 && housecount < 5 && owner==playerNumber){
            ((GUI_Street) gui.getFields()[position]).setHouses(housecount);
        }
        if(gui.getFields()[position] instanceof GUI_Street && housecount == 5 && owner==playerNumber){
            ((GUI_Street) gui.getFields()[position]).setHotel(true);
        }
    }

    public void sellHouse(int position,int housecount, int owner, int playerNumber){
        if(gui.getFields()[position] instanceof GUI_Street && housecount>=0 && housecount < 5 && owner==playerNumber){
            ((GUI_Street) gui.getFields()[position]).setHouses(housecount);
        }
        if(gui.getFields()[position] instanceof GUI_Street && housecount == 5 && owner==playerNumber){
            ((GUI_Street) gui.getFields()[position]).setHotel(false);
            ((GUI_Street) gui.getFields()[position]).setHouses(housecount);
        }
    }

    public int getIncomeTax(int totalPlayerWorth){
        stringChoice = gui.getUserButtonPressed("Hvordan vil du betale skat? \n 10 % af dine samlede værdier svarer til: "+ totalPlayerWorth, "200 kr.", "10 % af dine samlede værdier");
        switch (stringChoice){
            case "200 kr.":
                return 1;
            case "10 % af dine samlede værdier":
                return 2;
        }
        return 0;
    }
}