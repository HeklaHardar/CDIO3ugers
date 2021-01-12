package Game.controller.LandOnFields;

import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnOwnable {

    LandOnNotOwned landonNotOwned = new LandOnNotOwned();
    LandOnOwned landOnOwned = new LandOnOwned();


    public void Ownable(MatadorGui matadorGUI, FieldController fieldProperties, Player currentPlayer, int playerID, Player[] player) {

        if (fieldProperties.getOwnedFields()[currentPlayer.getCurrentPosition()] == 0 && fieldProperties.isOwnable() == 1) {
            landonNotOwned.notOwned(matadorGUI, currentPlayer, fieldProperties, playerID);
        } else if (fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] != 0 && player[fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1].playerString() != player[playerID].playerString()) {
            landOnOwned.Owned(matadorGUI, fieldProperties, player, playerID);
        }


    }
}
