package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnGoToPrison extends LandOnNotOwnable {

    public void GoToPrison(MatadorGui GUI, Player currentPlayer, FieldController properties, int i){
        
        GUI.showMessage(currentPlayer.playerString() + " skal i fængsel");
        GUI.moveToPrison(i,currentPlayer.getCurrentPosition());
        currentPlayer.setInPrison();
        properties.resetPrisonStatus();
        
    }


}
