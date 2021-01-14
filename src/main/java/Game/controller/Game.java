package Game.controller;

import Game.Model.*;
import Game.Model.Fields.BuildableField;
import Game.Model.Fields.Field;
import Game.controller.LandOnFields.LandOnField;
import Game.View.MatadorGui;

import java.util.Arrays;


public class Game {

    private final Player[] player = new Player[6];
    boolean isWinnerWinnerChickenDinner = false;
    private int temporary;
    private int balanceid;
    private int finalbalanceid;
    private int round = 0;

    public static void main(String[] args){
        //Instantiating game object

        Game game = new Game();

        //plays the game
        game.Game();

    }


    public void Game(){


        Cards cards = new Cards();
        MatadorGui matadorGUI = new MatadorGui();
        Dices dices = new Dices();
        PrisonConditions prisonproperties = new PrisonConditions();
        rollOfDices rollCheck = new rollOfDices();


        GameStart menu = new GameStart();
        matadorGUI.createGui();
        menu.startGame(matadorGUI);
        FieldController fieldProperties = new FieldController();

        for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
            player[i] = new Player(menu.playernamesToString()[i]);
            matadorGUI.guiPlayers(player[i].playerString(), player[i].getBalance(), i);
        }

        LandOnField landOnField = new LandOnField(matadorGUI, fieldProperties,cards,menu.getPlayerAmount(),player);

        while (!isWinnerWinnerChickenDinner) {
            round = round + 1;
            for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
                //checks if the player is in prison and releases him if he is.
                if (player[i].isInPrison()) {
                    prisonproperties.Release(player[i], dices, matadorGUI, i);
                    if (player[i].isInPrison()) {
                        player[i].InPrison();
                        continue;
                    }
                }
                int playerAction = matadorGUI.getPlayerAction(player[i].playerString());
                while(playerAction!=1) {

                    String selection = matadorGUI.getUserSelection("Vælg en plads at bygge på", "Rødovrevej", "Hvidovre", "Roskildevej", "Valby \n Langgade", "Allégade", "Frederiksberg \n Allé",
                            "Bülowsvej", "Gl. Kongevej", "Bernstoffsvej", "Hellerupvej", "Strandvej", "Trianglen", "Østerbro-\ngade \n", "Grønningen",
                            "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "Amagertorv", "Vimmelskaftet", "Nygade", "Frederiks-\nberggade \n", "Rådhus-\npladsen \n");
                    boolean buildable = false;
                    for (Field field : fieldProperties.getFields()) {
                        if (field instanceof BuildableField && field.getName() == selection) {
                            fieldProperties.buildHouses(player[i], i + 1, field.getPosition());
                            matadorGUI.buyHouse(field.getPosition(), ((BuildableField) field).getHouses(), ((BuildableField) field).getOwner(), i + 1);
                            matadorGUI.updateGuiBalance(i, player[i].getBalance());
                            buildable = true;
                            break;
                        }
                    }
                    if(!buildable) {
                        matadorGUI.showMessage("Du kan ikke bygge her");
                    }
                    playerAction = matadorGUI.getPlayerAction(player[i].playerString());
                }

                dices.roll();
                matadorGUI.ShowDie(dices.Die1(), dices.Die2());

                if(rollCheck.twoOfTheSameThreeTimes(dices.Die1(),dices.Die2(),i,player[i],matadorGUI)){
                    matadorGUI.showMessage("Du slog to ens tre gange i træk, og rykker til gå i fængsel");
                    fieldProperties.setPosition(30);
                    landOnField.FieldPosition(player[i].getCurrentPosition(),player[i],i, 1);
                    continue;
                }
                //Moves the car on the GUI and checks if player is over start.
                matadorGUI.moveCars(i, player[i].getCurrentPosition(), player[i].updatePosition(1));
                matadorGUI.updateGuiBalance(i, player[i].getBalance());
                fieldProperties.setPosition(player[i].getCurrentPosition());



                landOnField.FieldPosition(player[i].getCurrentPosition(),player[i],i, 1);

                if (player[i].getBalance() < 0) {

                    int[] balances = new int[menu.getPlayerAmount()];
                    for(int j=0; j<
                            balances.length; j++) {
                        int money = player[j].getBalance();
                        balances[j] = money;
                    }
                    temporary=0;
                    balanceid = 0;
                    finalbalanceid = 0;

                    for(int balance:balances){
                        if(temporary<balance){
                            temporary = balance;
                            finalbalanceid = balanceid;

                        }
                        balanceid+=1;
                    }
                    matadorGUI.showMessage("Vinderen er: " + player[finalbalanceid].playerString() + " med " + player[finalbalanceid].getBalance() + " penge");
                    isWinnerWinnerChickenDinner = true;
                    break;
                }

                if(rollCheck.twoOfTheSame(dices.Die1(), dices.Die2())) {
                    i = i - 1;
                    matadorGUI.showMessage("Du slog to ens og får en ekstra tur");
                }

            }

        }


    }


}
