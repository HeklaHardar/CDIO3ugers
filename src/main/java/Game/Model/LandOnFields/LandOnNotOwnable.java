package Game.Model.LandOnFields;


import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnNotOwnable extends LandOnField {


    public LandOnNotOwnable(MatadorGui GUI, FieldController properties, Player currentPlayer, Cards cards) {
        super(GUI, properties, currentPlayer, cards);
    }
}
