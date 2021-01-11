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

    LandOnOwnable landOnOwnable = new LandOnOwnable();
    LandOnNotOwnable landOnNotOwnable = new LandOnNotOwnable();





    public LandOnField(MatadorGui GUI, FieldController properties, Cards cards){

        this.GUI = GUI;
        this.properties = properties;
        this.cards = cards;

    }


    public void FieldPosition (int currentPosition){
        System.out.println("Checker hvad feltet er");

        properties.setPosition(currentPosition);

        if(properties.fields(currentPosition) instanceof OwnableField){
            System.out.println("Ownable");
            landOnOwnable.Ownable(GUI,properties,cards,currentPosition);


        }
        else {
            System.out.println("Not ownable");
            landOnNotOwnable.NotOwnable(GUI,properties,cards,currentPosition);

        }






    }

}
