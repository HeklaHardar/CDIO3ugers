package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnGoToPrison {

    public void GoToPrison(MatadorGui matadorGUI, Player currentPlayer, FieldController fieldProperties, int playerID) {
        matadorGUI.showMessage(currentPlayer.playerString() + " skal i fængsel");
        matadorGUI.moveToPrison(playerID, currentPlayer.getCurrentPosition());
        currentPlayer.setInPrison();
        fieldProperties.resetPrisonStatus();
    }


}
