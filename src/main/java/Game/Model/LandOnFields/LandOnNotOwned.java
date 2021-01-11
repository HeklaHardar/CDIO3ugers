package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnNotOwned extends LandOnOwnable {


    public LandOnNotOwned(MatadorGui GUI, FieldController properties, Player currentPlayer, Cards cards) {
        super(GUI, properties, cards);
    }
}
