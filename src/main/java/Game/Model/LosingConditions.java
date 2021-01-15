package Game.Model;

import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.PlayerChoice;

public class LosingConditions {

    public void CheckPlayerWorth(Player currentPlayer, MatadorGui LosingConditionsGUI, PlayerChoice LosingChoices, int playerID, Player[] Player, Mortgage LosingMortgage, FieldController LosingController) {

        while (currentPlayer.getBalance() < 0) {
            if (currentPlayer.getPlayerAccountWorth() > 0) {
                int playerAction = LosingConditionsGUI.getPlayerAction(currentPlayer.playerString(), LosingChoices.LosingChoices(playerID, currentPlayer, Player));

                if (playerAction == 3){
                    LosingMortgage.SellMortgage(LosingConditionsGUI,LosingController, currentPlayer, playerID);
                }
            }
            else {
                currentPlayer.setInGame(false);
            }
        }
    }
}