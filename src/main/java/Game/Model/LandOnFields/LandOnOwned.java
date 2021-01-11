package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnOwned extends LandOnOwnable {
    public LandOnOwned(MatadorGui GUI, FieldController properties, Player currentPlayer, Cards cards) {
        super(GUI, properties, cards);
    }
}
