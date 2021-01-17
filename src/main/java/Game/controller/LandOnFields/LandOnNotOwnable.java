package Game.controller.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.Model.Player;

public class LandOnNotOwnable {

    LandOnChanceCard landOnChanceCard = new LandOnChanceCard();
    LandOnGoToPrison landOnGoToPrison = new LandOnGoToPrison();
    private int totalPlayerWorth;

    public void NotOwnable(MatadorGui matadorGUI, FieldController fieldProperties, Cards cards, Player currentPlayer, int playerAmount, int playerID, Player[] player) {

        if (fieldProperties.getdrawCard()) {
            landOnChanceCard.ChanceCard(matadorGUI, cards, playerAmount, playerID, player);
            cards.resetStats();
        }

        //Updates current players position, incase chance card moved them.
        fieldProperties.setPosition(currentPlayer.getCurrentPosition());

        if (fieldProperties.isIncomeTax()) {
            totalPlayerWorth = currentPlayer.getPlayerAccountWorth() + currentPlayer.getBalance();
            if (matadorGUI.getIncomeTax((int) (totalPlayerWorth * 0.1)) == 1) {
                currentPlayer.playerBalanceUpdate(-200);
            } else {
                currentPlayer.playerBalanceUpdate(-(int) (totalPlayerWorth * 0.1));
            }
        }

        if (fieldProperties.isInPrison()) {
            landOnGoToPrison.GoToPrison(matadorGUI, currentPlayer, fieldProperties, playerID);
        }

        if (fieldProperties.isExtraordinaryTax()) {
            matadorGUI.showMessage("Du skal betale extraordinærskat som er på kr. 100,00");
            currentPlayer.playerBalanceUpdate(fieldProperties.getValue());
        }
        matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());
    }
}