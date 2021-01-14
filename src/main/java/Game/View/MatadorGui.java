package Game.View;

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

    public void ShowDie(int DieGui, int Die2Gui) {
        gui.setDice(DieGui, Die2Gui);
    }

    public void moveCars(int currentPlayer, int currentPosition, int newPosition) {
        fields[currentPosition].setCar(player[currentPlayer], false);
        fields[newPosition].setCar(player[currentPlayer], true);
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
        } else return;
    }

    public void displayCard(String cardText) {
        gui.displayChanceCard(cardText);
    }

    public String getUserSelection(String msg, String... options){
        return gui.getUserSelection(msg, options);
    }

    public int getPlayerAction(String playerName) {
        stringChoice = gui.getUserButtonPressed("Det er spiller " + playerName +" s tur.", "Byg hus/hotel", "Slå med terningerne");
        switch (stringChoice) {
            case "Slå med terningerne":
                return 1;
            case "Byg hus/hotel":
                return 2;
        }
        return 0;
    }

    public int getBuyField(){
        stringChoice = gui.getUserButtonPressed("Vil du købe feltet? ", "Ja", "Nej");
        switch (stringChoice){
            case "Ja":
                return 1;
            case "Nej":
                return 2;
        }
        return 0;
    }

    public void buyHouse(int position,int housecount, int owner, int playerNumber){
        if(gui.getFields()[position] instanceof GUI_Street && housecount>0 && housecount < 5 && owner==playerNumber){
            ((GUI_Street) gui.getFields()[position]).setHouses(housecount);
        }
        if(gui.getFields()[position] instanceof GUI_Street && housecount == 5 && owner==playerNumber){
            ((GUI_Street) gui.getFields()[position]).setHotel(true);
        }
    }
}