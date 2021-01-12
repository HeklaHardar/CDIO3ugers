package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnChanceCard {


    public void ChanceCard(MatadorGui matadorGUI, Cards cards, int playerAmount, int playerID, Player[] player) {

        while (true) {

            //Draws card and checks what card has been drawn and completes the actions on the card
            cards.CardPick();
            matadorGUI.displayCard(cards.cardToString());
            matadorGUI.gui.getUserButtonPressed("", "ok");

            if (cards.isHasExtraMoves()) {
                matadorGUI.moveCars(playerID, player[playerID].getCurrentPosition(), player[playerID].updatePosition(cards.move()));
                matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());

            }
            if (cards.isHasPositionChange()) {
                matadorGUI.moveCars(playerID, player[playerID].getCurrentPosition(), player[playerID].setPosition(cards.getPositionChange()));
                matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());

            }
            if (cards.HasPrisonChance()) {
                if (!player[playerID].getPrisonCard()) {
                    matadorGUI.moveToPrison(playerID, player[playerID].getCurrentPosition());
                    player[playerID].setInPrison();
                    matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());
                } else if (player[playerID].getPrisonCard()) {
                    player[playerID].updatePrisonCard(false);
                }

            }

            player[playerID].playerBalanceUpdate(cards.extraMoney());
            matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());
            if (cards.isHasPrisonCard()) {
                player[playerID].updatePrisonCard(true);
            }
            if (cards.isHasSammenskudsgilde()) {
                for (int y = 0; y < playerAmount; y++) {
                    player[y].playerBalanceUpdate(-25);
                    matadorGUI.updateGuiBalance(y, player[y].getBalance());
                }
                player[playerID].playerBalanceUpdate(playerAmount * 25);
                matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());
            }
            if (cards.isHasMatadorlegat()) {
                for (int y = 0; y < playerAmount; y++) {
                    player[y].playerBalanceUpdate(-25);
                    matadorGUI.updateGuiBalance(y, player[y].getBalance());
                }
                player[playerID].playerBalanceUpdate(playerAmount * 25);
                matadorGUI.updateGuiBalance(playerID, player[playerID].getBalance());
            }


            cards.resetStats();
            break;
        }


    }


}

