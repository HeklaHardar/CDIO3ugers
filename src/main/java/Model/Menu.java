package Model;

import Game.View.JuniorGui;

public class Menu {

    // Defines variables and scanner
    private final String[] Players = new String[4];

    private int playerNumber;

    public void startGame(JuniorGui juniorGui) {
        while (true) {
            // Input number of players
               playerNumber = juniorGui.gui.getUserInteger("Velkommen til spillet! \n" +
                    "Indtast antallet af spillere");
            if (playerNumber <= 4 && playerNumber >= 2)
                break;
            else
                juniorGui.gui.showMessage("ugyldig antal spillere");
        }
        // Youngest player name+rest of player names
        for (int i = 1; i <= playerNumber; i++) {
            if(i==1)
                Players[i - 1] = juniorGui.gui.getUserString("Indtast navnet på den yngste spiller");
            else
                Players[i - 1] = juniorGui.gui.getUserString("Indtast navn for spiller " + i + ": ");

        }
    }

    public String[] playernamesToString(){return Players;}

    public int getPlayerAmount(){return playerNumber;}

}