package Game.controller.LandOnFields;

import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnOwnable {

    LandOnNotOwned landonNotOwned = new LandOnNotOwned();
    LandOnOwned landOnOwned = new LandOnOwned();


    public void Ownable(MatadorGui matadorGUI, FieldController fieldProperties, Player currentPlayer, int playerID, Player[] player, int dices, String[] playerNames) {

        if (((OwnableField)fieldProperties.getFields()[currentPlayer.getCurrentPosition()]).getOwner() == 0 && fieldProperties.isOwnable() == 1) {
            landonNotOwned.notOwned(matadorGUI, currentPlayer, fieldProperties, playerID,player,playerNames);
        } else if (((OwnableField)fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() < 10 && ((OwnableField)fieldProperties.getFields()[currentPlayer.getCurrentPosition()]).getOwner() != 0 && player[((OwnableField)fieldProperties.getFields()[currentPlayer.getCurrentPosition()]).getOwner() - 1].playerString() != player[playerID].playerString()) {
            landOnOwned.Owned(matadorGUI, fieldProperties, player, playerID, dices);
        }
    }
}