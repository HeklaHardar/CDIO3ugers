package Game.controller;

import Game.View.MatadorGui;

public class Menu {

    // Defines variables
    private final String[] Players = new String[6];
    private int MinPlayers = 2;
    private int MaxPlayers = 6;


    private int playerNumber;

    public void startGame(MatadorGui juniorGui) {
        while (true) {
            // Input number of players
               playerNumber = juniorGui.gui.getUserInteger("Velkommen til spillet! \n" +
                    "Indtast antallet af spillere, mellem " + MinPlayers + " og " + MaxPlayers + " spillere.");
            if (playerNumber <= MaxPlayers && playerNumber >= MinPlayers)
                break;
            else
                juniorGui.gui.showMessage("ugyldig antal spillere");
        }
        // Youngest player name+rest of player names
        for (int i = 0 ; i < playerNumber; i++) {
            if(i == 0) {
                Players[i] = juniorGui.gui.getUserString("Indtast navnet på den yngste spiller");
            }
            else {
                Players[i] = juniorGui.gui.getUserString("Indtast navn for spiller " + (i + 1) + ": ");
            }

            if(Players[i].isEmpty() || Players[i].startsWith(" ")){
                juniorGui.gui.showMessage("Ugyldigt navn");
                i--;
            }
        }
    }

    public String[] playernamesToString(){return Players;}

    public int getPlayerAmount(){return playerNumber;}

}