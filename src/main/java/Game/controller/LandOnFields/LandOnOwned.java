package Game.controller.LandOnFields;

import Game.Model.Cards;
import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.Model.Player;

public class LandOnOwned {

    public void Owned(MatadorGui matadorGUI, FieldController fieldProperties, Player[] player, int playerID, int dices, Cards cards) {

        matadorGUI.showMessage(player[playerID].playerString() + ", du er landet på en ejendom ejet af " + player[((OwnableField) fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() - 1].playerString() + " og betaler " + fieldProperties.calculateRent(dices, cards.isChanceCard15or25()) + " kr. i husleje");

        player[((OwnableField) fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() - 1].playerBalanceUpdate(fieldProperties.calculateRent(dices, cards.isChanceCard15or25()));

        player[playerID].playerBalanceUpdate(-fieldProperties.calculateRent(dices, cards.isChanceCard15or25()));

        matadorGUI.updateGuiBalance(((OwnableField) fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() - 1, player[((OwnableField) fieldProperties.getFields()[player[playerID].getCurrentPosition()]).getOwner() - 1].getBalance());

        matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());

    }
}