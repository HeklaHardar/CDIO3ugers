package Game.controller.LandOnFields;

import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnOwned {

    public void Owned(MatadorGui matadorGUI, FieldController fieldProperties, Player[] player, int playerID, int dices) {

        matadorGUI.showMessage(player[playerID].playerString() + ", du er landet på en ejendom ejet af " + player[((OwnableField)fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() - 1].playerString() + " og betaler " + fieldProperties.calculateRent() + " kr. i husleje");

        player[((OwnableField)fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() - 1].playerBalanceUpdate(fieldProperties.calculateRent());

        player[playerID].playerBalanceUpdate(-fieldProperties.calculateRent());

        matadorGUI.updateGuiBalance(((OwnableField)fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() - 1, player[((OwnableField)fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() - 1].getBalance());

        matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());

    }
}