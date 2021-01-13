package Game.controller.LandOnFields;

import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnNotOwned {

    private String playerName;


    public void notOwned(MatadorGui matadorGUI, Player currentPlayer, FieldController properties, int playerID, Player[] players,String[] playerNames) {

        if (matadorGUI.getBuyField() == 1) {
            currentPlayer.playerBalanceUpdate(-properties.getValue());
            matadorGUI.landOnField(playerID, currentPlayer.getCurrentPosition(), currentPlayer.playerString(), properties.isOwnable(), properties.getOwnedFields());
            properties.setOwnedFields(playerID + 1);
        } else{

            playerName = matadorGUI.getAuction(playerNames);

            for (Player s:players
                 ) {
                if(s.playerString() == playerName){
                  //  s.playerBalanceUpdate();
                    s.playerBalanceUpdate(-properties.getValue());
                    matadorGUI.landOnField(s.getPlayerID(), currentPlayer.getCurrentPosition(), s.playerString(), properties.isOwnable(), properties.getOwnedFields());
                    properties.setOwnedFields(s.getPlayerID() + 1);
                    matadorGUI.updateGuiBalance(s.getPlayerID(),s.getBalance());
                }
            }
        }
        matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
    }
}