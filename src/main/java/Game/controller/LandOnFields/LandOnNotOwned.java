package Game.controller.LandOnFields;

import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnNotOwned {

    private String playerName;


    public void notOwned(MatadorGui matadorGUI, Player currentPlayer, FieldController properties, int playerID, Player[] players,String[] playerNames) {

        if (matadorGUI.getBuyField(properties.getFields()[currentPlayer.getCurrentPosition()].getName()) == 1) {
            currentPlayer.playerBalanceUpdate(-properties.getValue());
            matadorGUI.landOnField(playerID, currentPlayer.getCurrentPosition(), currentPlayer.playerString(), properties.isOwnable(), ((OwnableField)properties.getFields()[currentPlayer.getCurrentPosition()]).getOwner());
            properties.setOwnedFields(playerID + 1);
            currentPlayer.playerWorthUpdate(properties.getValue());
        } else{

            playerName = matadorGUI.getAuction(playerNames, properties.getFields()[currentPlayer.getCurrentPosition()].getName());

            for (Player s:players
                 ) {
                if(s.playerString() == playerName){
                  //  s.playerBalanceUpdate();
                    s.playerBalanceUpdate(-properties.getValue());
                    matadorGUI.landOnField(s.getPlayerID(), currentPlayer.getCurrentPosition(), s.playerString(), properties.isOwnable(),((OwnableField)properties.getFields()[currentPlayer.getCurrentPosition()]).getOwner());
                    properties.setOwnedFields(s.getPlayerID() + 1);
                    matadorGUI.updateGuiBalance(s.getPlayerID(),s.getBalance());
                    s.playerWorthUpdate(properties.getValue());
                }
            }
        }
        matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
    }
}