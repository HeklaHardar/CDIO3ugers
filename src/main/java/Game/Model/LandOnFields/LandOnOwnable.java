package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnOwnable{

    LandOnNotOwned landonNotOwned = new LandOnNotOwned();
    LandOnOwned landOnOwned = new LandOnOwned();


    public void Ownable (MatadorGui GUI, FieldController properties, Cards cards, int currentPosition, Player currentPlayer, int i){

        if (properties.getOwnedFields()[currentPlayer.getCurrentPosition()] == 0 && properties.isOwnable() == 1){
            landonNotOwned.notOwned();
        }
        else if(properties.getOwnedFields()[currentPlayer.getCurrentPosition()] != 0){
            landOnOwned.Owned();
        }





    }
}
