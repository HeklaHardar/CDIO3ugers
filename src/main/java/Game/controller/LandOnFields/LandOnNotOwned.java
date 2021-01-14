package Game.controller.LandOnFields;

import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnNotOwned {

    public void notOwned(MatadorGui matadorGUI, Player currentPlayer, FieldController properties, int playerID) {

        if (matadorGUI.getBuyField() == 1) {
            currentPlayer.playerBalanceUpdate(-properties.getValue());
            matadorGUI.landOnField(playerID, currentPlayer.getCurrentPosition(), currentPlayer.playerString(), properties.isOwnable(), ((OwnableField)properties.getFields()[currentPlayer.getCurrentPosition()]).getOwner());
            properties.setOwnedFields(playerID + 1);
        }
        matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
    }
}