package Game.controller.LandOnFields;


import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnNotOwnable {

    LandOnChanceCard landOnChanceCard = new LandOnChanceCard();
    LandOnGoToPrison landOnGoToPrison = new LandOnGoToPrison();


    public void NotOwnable(MatadorGui matadorGUI, FieldController fieldProperties, Cards cards, Player currentPlayer, int playerAmount, int playerID, Player[] player) {

        if (fieldProperties.getdrawCard()) {


            landOnChanceCard.ChanceCard(matadorGUI, cards, playerAmount, playerID, player);

            cards.resetStats();

        } else if (fieldProperties.isInPrison()) {

            landOnGoToPrison.GoToPrison(matadorGUI, currentPlayer, fieldProperties, playerID);

        }


    }
}