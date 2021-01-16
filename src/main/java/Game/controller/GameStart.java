package Game.controller;

import Game.View.MatadorGui;

public class GameStart {

    //private final String[] Players = new String[6]

    private String[] Players;

    //Change this value to change minimum amount of players
    private int MinPlayers = 2;

    //Change this value to change maximum amount of players
    private int MaxPlayers = 6;

    private int playerNumber;

    public void startGame(MatadorGui matadorGUI) {
        while (true) {
            // Input number of players
            playerNumber = matadorGUI.gui.getUserInteger("Velkommen til spillet! \n" +
                    "Indtast antallet af spillere, mellem " + MinPlayers + " og " + MaxPlayers + " spillere.");
            if (playerNumber <= MaxPlayers && playerNumber >= MinPlayers)
                break;
            else
                matadorGUI.gui.showMessage("ugyldig antal spillere");
        }
        String[] Players = new String[playerNumber];
        this.Players = Players;
        // Youngest player name+rest of player names
        for (int i = 0 ; i < playerNumber; i++) {
            if(i == 0) {
                Players[i] = matadorGUI.gui.getUserString("Indtast navnet på den yngste spiller");
                if(Players[i].isEmpty() || Players[i].startsWith(" ")){
                    matadorGUI.gui.showMessage("Ugyldigt navn");
                    i--;
                    continue;
                }
            }

            else{
                boolean removePlayerName = false;
                Players[i] = matadorGUI.gui.getUserString("Indtast navn for spiller " + (i + 1) + ": ");
                if(Players[i].isEmpty() || Players[i].startsWith(" ")){
                    matadorGUI.gui.showMessage("Ugyldigt navn");
                    i--;
                    continue;
                }
                for (int j=0; j<i;j++){
                        if(Players[j].toLowerCase().equals(Players[i].toLowerCase()) && i!=j){
                            removePlayerName = true;
                        }
                    }
                if(removePlayerName){
                    matadorGUI.gui.showMessage("Du hedder det samme som en anden spiller, indtast andet navn");
                    Players[i]="";
                    i--;

                }
            }
        }
    }

    public String[] playernamesToString(){return Players;}

    public int getPlayerAmount(){return playerNumber;}
}