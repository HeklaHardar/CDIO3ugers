package Game.Model.LandOnFields;

import Game.Model.Cards;
import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnField {

    protected MatadorGui GUI;
    protected FieldController properties;
    protected Cards cards;
    protected Player currentPlayer;
    protected int playerAmount;
    protected  int playerNumber;

    LandOnOwnable landOnOwnable = new LandOnOwnable();
    LandOnNotOwnable landOnNotOwnable = new LandOnNotOwnable();


    public LandOnField(MatadorGui GUI, FieldController properties, Cards cards, int playerAmount) {

        this.GUI = GUI;
        this.properties = properties;
        this.cards = cards;
        this.currentPlayer = currentPlayer;
        this.playerAmount = playerAmount;
        this.playerNumber = playerNumber;

    }

    //properties.setPosition(currentPosition);

    public void FieldPosition(int currentPosition, int playerNumber, Player currentPlayer) {



        if (!(properties.fields(currentPosition) instanceof OwnableField)) {
            landOnNotOwnable.NotOwnable(GUI, properties, cards, currentPlayer, playerAmount, playerNumber);
        }
        if(properties.fields(currentPosition) instanceof OwnableField){
            landOnOwnable.Ownable(GUI, properties, cards, currentPosition, currentPlayer, playerNumber);
        }


    }

}
