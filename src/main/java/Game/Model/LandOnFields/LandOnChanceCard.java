package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnChanceCard {


    public void ChanceCard(MatadorGui matadorGUI, Cards cards, Player currentPlayer, int playerAmount, int playerNumber) {

        while (true) {

            //Draws card and checks what card has been drawn and completes the actions on the card
            cards.CardPick();
            matadorGUI.displayCard(cards.cardToString());
            matadorGUI.gui.getUserButtonPressed(cards.cardToString(), "ok");

            if (cards.isHasExtraMoves()) {
                matadorGUI.moveCars(playerNumber, currentPlayer.getCurrentPosition(), currentPlayer.updatePosition(cards.move()));
                matadorGUI.updateGuiBalance(playerNumber, currentPlayer.getBalance());

            }
            if (cards.isHasPositionChange()) {
                matadorGUI.moveCars(playerNumber, currentPlayer.getCurrentPosition(), currentPlayer.setPosition(cards.getPositionChange()));
                matadorGUI.updateGuiBalance(playerNumber, currentPlayer.getBalance());

            }
            if (cards.HasPrisonChance()) {
                if (!currentPlayer.getPrisonCard()) {
                    matadorGUI.moveToPrison(playerNumber, currentPlayer.getCurrentPosition());
                    currentPlayer.setInPrison();
                    matadorGUI.updateGuiBalance(playerNumber, currentPlayer.getBalance());
                } else if (currentPlayer.getPrisonCard()) {
                    currentPlayer.updatePrisonCard(false);
                }

            }

            currentPlayer.playerBalanceUpdate(cards.extraMoney());
            matadorGUI.updateGuiBalance(playerNumber, currentPlayer.getBalance());
            if (cards.isHasPrisonCard()) {
                currentPlayer.updatePrisonCard(true);
            }
            if (cards.isHasSammenskudsgilde()) {
                for (int y = 0; y < playerAmount; y++) {
                    currentPlayer.playerBalanceUpdate(-25);
                    matadorGUI.updateGuiBalance(y, currentPlayer.getBalance());
                }
                currentPlayer.playerBalanceUpdate(playerAmount * 25);
                matadorGUI.updateGuiBalance(playerNumber, currentPlayer.getBalance());
            }
            if (cards.isHasMatadorlegat()) {
                for (int y = 0; y < playerAmount; y++) {
                    currentPlayer.playerBalanceUpdate(-25);
                    matadorGUI.updateGuiBalance(y, currentPlayer.getBalance());
                }
                currentPlayer.playerBalanceUpdate(playerAmount * 25);
                matadorGUI.updateGuiBalance(playerNumber, currentPlayer.getBalance());
            }


            cards.resetStats();
            break;
        }


    }

}
