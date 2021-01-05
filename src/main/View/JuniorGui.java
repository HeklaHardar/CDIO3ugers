package Game.View;

import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class JuniorGui {
   // private static JuniorGui instance;
    //private int playerNumber = 0;
    private int temp = 0;
    private int playerCount = 0;
    public GUI gui;
    private GUI_Field[] fields = new GUI_Field[24];
    private GUI_Player[] player = new GUI_Player[4];
    private GUI_Car[] car = new GUI_Car[4];
    private int moves;
    private Color[] colors = {Color.black, new Color(175, 4, 182),new Color(255, 255, 255),new Color(139, 33, 33)};
    private GUI_Car.Type[] type = {GUI_Car.Type.CAR, GUI_Car.Type.RACECAR, GUI_Car.Type.UFO, GUI_Car.Type.TRACTOR};
    GUI_Ownable o = (GUI_Ownable) fields[5];
    private String stringChoice;
    private String[] fieldTitles = {"Burgerbaren","Pizzariaet","","Slikbutikken","Iskiosken","","Museet","Biblioteket","","Skaterparken",
            "Svømmingpoolen","", "Spillehallen", "Biografen","","Legetøjsbutikken","Dyrehandlen","","Bowlinghallen","Zoo","","Vandlandet","Strandpromenaden",""};


    public JuniorGui(){
            // Laver array
            //GUI_Field[] fields = new GUI_Field[24];
            Color[] fieldColors = {Color.GREEN,Color.BLUE,Color.PINK,Color.YELLOW,Color.RED, Color.cyan, new Color(34,139,34), Color.ORANGE};

            // Laver orange startfelt
            fields[0] = new GUI_Start("Start", "", "Her starter du", Color.ORANGE, Color.WHITE);


        // Fylder resten af feltenre ud med veje
            for( int i=1; i<24; i++) {
                if (i == 3 || i == 9 || i == 15 || i == 21) {
                    fields[i] = new GUI_Chance();
                    fields[i].setDescription("Træk et chancekort");
                }
                else if(i==6){
                    fields[i] = new GUI_Jail();
                    fields[i].setSubText("På besøg");
                    fields[i].setDescription("På besøg");
                }
                else if(i==12) {
                    fields[i] = new GUI_Refuge();
                    fields[i].setTitle("Gratis parkering");
                    fields[i].setSubText("Gratis parkering");
                    fields[i].setDescription("Slap af for en tur");
                }
                else if(i==18){
                    fields[i] = new GUI_Jail();
                    fields[i].setSubText("Gå i fængsel");
                    fields[i].setDescription("Ryk i fængsel");
                }
                else {
                    fields[i] = new GUI_Street(
                            "Street " + i,
                            "Price: " + (i * 50),
                            "This is a street",
                            "100",
                            Color.PINK,
                            Color.BLACK
                    );
                    fields[i].setTitle(fieldTitles[i-1]);
                    if(3>i){
                        fields[i].setBackGroundColor(fieldColors[0]);
                        fields[i].setDescription(fieldTitles[i-1]);
                        fields[i].setSubText("Price: 1M");
                    }
                    else if(3<i&i<6){
                        fields[i].setBackGroundColor(fieldColors[5]);
                        fields[i].setDescription(fieldTitles[i-1]);
                        fields[i].setSubText("Price: 1M");
                    }
                    else if(6<i&i<9){
                        fields[i].setBackGroundColor(fieldColors[2]);
                        fields[i].setDescription(fieldTitles[i-1]);
                        fields[i].setSubText("Price: 2M");
                    }
                    else if(9<i&i<12){
                        fields[i].setBackGroundColor(fieldColors[7]);
                        fields[i].setDescription(fieldTitles[i-1]);
                        fields[i].setSubText("Price: 2M");
                    }
                    else if(12<i&i<15){
                        fields[i].setBackGroundColor(fieldColors[4]);
                        fields[i].setDescription(fieldTitles[i-1]);
                        fields[i].setSubText("Price: 3M");
                    }
                    else if(15<i&i<18){
                        fields[i].setBackGroundColor(fieldColors[3]);
                        fields[i].setDescription(fieldTitles[i-1]);
                        fields[i].setSubText("Price: 3M");
                    }
                    else if(18<i&i<21){
                        fields[i].setBackGroundColor(fieldColors[6]);
                        fields[i].setDescription(fieldTitles[i-1]);
                        fields[i].setSubText("Price: 4M");
                    }
                    else if(21<i&i<24){
                        fields[i].setBackGroundColor(fieldColors[1]);
                        fields[i].setDescription(fieldTitles[i-1]);
                        fields[i].setSubText("Price: 5M");
                        //ownable.setRent("20000");


                    }

                }
            }


    }

    public void createGui(){
        this.gui = new GUI(fields);
        GUI.setNull_fields_allowed(true);
    }

    public void guiPlayers(String name, int balance, int i){
        car[i] = new GUI_Car(colors[i], colors[i], type[i], GUI_Car.Pattern.CHECKERED);
        player[i] = new GUI_Player(name, balance,car[i]);
        gui.addPlayer(player[i]);
        fields[0].setCar(player[i],true);

        }

    public void ShowDie(int DieGui){
        gui.setDie(DieGui);
    }

    public void moveCars(int currentPlayer,int currentPosition, int newPosition){
        fields[currentPosition].setCar(player[currentPlayer],false);
        fields[newPosition].setCar(player[currentPlayer],true);
        }
        public void moveToPrison(int currentPlayer, int currentPosition){
            fields[currentPosition].setCar(player[currentPlayer],false);
            fields[6].setCar(player[currentPlayer],true);
        }

    public void updateGuiBalance(int i ,int balance){
        player[i].setBalance(balance);
        }

        public void showMessage (String message){
        gui.showMessage(message);
        }

    public void landOnField(int currentPlayer, int currentField, String playerName, int ownable, int[]ownedfields){
        GUI_Field field = gui.getFields()[currentField];
        if(ownable == 1 && ownedfields[currentField] == 0) {
            GUI_Ownable o = (GUI_Ownable) field;
            o.setBorder(colors[currentPlayer]);
            o.setOwnerName(playerName);
        }
        else return;
    }

    public String getFieldTitles(int index) {
        return fieldTitles[index];
    }

    public void displayCard(String cardText) {
        gui.displayChanceCard(cardText);
    }

    public int getIntSelection(String options, int min, int max) {
        while (true) {
            moves = gui.getUserInteger(options);
            if (moves <= max && moves >= min)
                return moves;
            else
                gui.showMessage("Ugyldigt input");
        }
    }
    public int getStringSelection(String[] options){

        stringChoice = gui.getUserSelection("Vælg hvor du vil rykke hen: ", options);
        switch (stringChoice) {
            case "Skaterparken":
                return 10;
            case "Swimmingpoolen":
                return 11;
            case "Slikbutikken":
                return 4;
            case "Iskiosken":
                return 5;
            case "Strandpromenaden":
                return 23;
            case "Vandlandet":
                return 22;
            case "Spillehallen":
                return 13;
            case "Biografen":
                return 14;
            case "Museet":
                return 7;
            case "Biblioteket":
                return 8;
            case "Burgerbaren":
                return 1;
            case "Pizzariaet":
                return 2;
            case "Legetøjsbutikken":
                return 16;
            case "Dyrehandlen":
                return 17;
            case "Bowlinghallen":
                return 19;
            case "Zoo":
                return 20;
        }
        return 0;


    }

    public int getMoveOrCard(){
        stringChoice = gui.getUserSelection("Vælg hvad du vil: ", "Træk et nyt kort", "Gå et felt frem");
        switch (stringChoice){
            case "Træk et nyt kort":
                return 1;
            case "Gå et felt frem":
                return 2;
        }
        return 0;
    }
}
