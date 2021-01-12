package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnNotOwned {

    public void notOwned(MatadorGui matadorGUI, Player currentPlayer, FieldController properties, int playerID) {

        if (matadorGUI.getBuyField() == 1) {
            currentPlayer.playerBalanceUpdate(-properties.getValue());
            matadorGUI.landOnField(playerID, currentPlayer.getCurrentPosition(), currentPlayer.playerString(), properties.isOwnable(), properties.getOwnedFields());
            properties.setOwnedFields(playerID + 1);
        }
        matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
    }

}
