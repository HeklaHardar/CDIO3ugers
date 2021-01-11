package Game.Model.LandOnFields;


import Game.Model.Cards;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnNotOwnable{

    LandOnChanceCard landOnChanceCard = new LandOnChanceCard();
    LandOnGoToPrison landOnGoToPrison = new LandOnGoToPrison();


    public void NotOwnable(MatadorGui GUI, FieldController properties, Cards cards, Player currentPlayer, int playerAmount, int playerNumber){

        if(properties.getdrawCard()){

            landOnChanceCard.ChanceCard(GUI,cards,currentPlayer,playerAmount,playerNumber);

            cards.resetStats();

        }
        else if(properties.isInPrison()){

            landOnGoToPrison.GoToPrison(GUI,currentPlayer,properties, playerNumber);

        }



    }


}
