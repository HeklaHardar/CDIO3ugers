package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnField {

    private MatadorGui GUI;
    private FieldController properties;
    private Player currentPlayer;
    protected Cards cards;




    public LandOnField(MatadorGui GUI, FieldController properties, Player currentPlayer, Cards cards){

        this.GUI = GUI;
        this.properties = properties;
        this.currentPlayer = currentPlayer;
        this.cards = cards;

    }

    public void FieldPosition (int CurrentPosition){



    }

}
