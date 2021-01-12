package Game.controller.LandOnFields;

import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnOwned {

    public void Owned(MatadorGui matadorGUI, FieldController fieldProperties, Player[] player, int playerID, int dices) {

        matadorGUI.showMessage(player[playerID].playerString() + ", du er landet på en ejendom ejet af " + player[fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1].playerString() + " og betaler " + fieldProperties.calculateRent(dices) + " kr. i husleje");

        player[fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1].playerBalanceUpdate(fieldProperties.calculateRent(dices));

        player[playerID].playerBalanceUpdate(-fieldProperties.calculateRent(dices));

        matadorGUI.updateGuiBalance(fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1, player[fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1].getBalance());

        matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());

    }

}
