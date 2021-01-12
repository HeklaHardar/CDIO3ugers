package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnOwned {

    public void Owned(MatadorGui matadorGUI, FieldController fieldProperties, Player[] player, int playerID) {

        matadorGUI.showMessage(player[playerID].playerString() + ", du er landet på en ejendom ejet af " + player[fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1].playerString() + " og betaler " + fieldProperties.calculateRent() + " kr. i husleje");

        player[fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1].playerBalanceUpdate(fieldProperties.calculateRent());

        player[playerID].playerBalanceUpdate(-fieldProperties.calculateRent());

        matadorGUI.updateGuiBalance(fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1, player[fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1].getBalance());


    }

}
