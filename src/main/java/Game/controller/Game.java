package Game.controller;

import Game.Model.*;
import Game.Model.Fields.BuildableField;
import Game.Model.Fields.Field;
import Game.controller.LandOnFields.LandOnField;
import Game.View.MatadorGui;


public class Game {

    private Player[] player;
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
        Mortgage mortgage = new Mortgage();
        LosingConditions losingConditions = new LosingConditions();

        GameStart menu = new GameStart();
        matadorGUI.createGui();
        menu.startGame(matadorGUI);
        FieldController fieldProperties = new FieldController();
        PlayerChoice TurnChoices = new PlayerChoice(fieldProperties);

        player = new Player[menu.getPlayerAmount()];


        for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
            player[i] = new Player(menu.playernamesToString()[i],i);
            matadorGUI.guiPlayers(player[i].playerString(), player[i].getBalance(), i);
        }

        LandOnField landOnField = new LandOnField(matadorGUI, fieldProperties, cards, menu.getPlayerAmount(), player, menu.playernamesToString());

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
                while(true) {
                    int playerAction = matadorGUI.getPlayerAction(player[i].playerString(), TurnChoices.PlayerChoices(i, player[i], player));

                    if (playerAction == 1) {
                        break;
                    }
                    if (playerAction == 3) {
                        mortgage.SellMortgage(matadorGUI, fieldProperties, player[i], i);
                    }
                    if (playerAction == 4) {
                        mortgage.BuyMortgage(matadorGUI, fieldProperties, player[i], i);

                    }
                    if (playerAction == 2) {

                        String selection = matadorGUI.getUserSelection("Vælg en plads at bygge på", "Rødovrevej", "Hvidovre", "Roskildevej", "Valby  Langgade", "Allégade", "Frederiksberg  Allé",
                                "Bülowsvej", "Gl. Kongevej", "Bernstoffsvej", "Hellerupvej", "Strandvej", "Trianglen", "Østerbro-gade ", "Grønningen",
                                "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "Amagertorv", "Vimmelskaftet", "Nygade", "Frederiks-berggade ", "Rådhus-pladsen ");
                        boolean buildable = false;
                        for (Field field : fieldProperties.getFields()) {
                            if (field instanceof BuildableField && field.getName() == selection) {
                                fieldProperties.buildHouses(player[i], i + 1, field.getPosition());
                                matadorGUI.buyHouse(field.getPosition(), ((BuildableField) field).getHouses(), ((BuildableField) field).getOwner(), i + 1);
                                matadorGUI.updateGuiBalance(i, player[i].getBalance());
                                buildable = true;
                                matadorGUI.RentOnField(fieldProperties);
                                break;
                            }
                        }
                        if (!buildable) {
                            matadorGUI.showMessage("Du kan ikke bygge her");
                        }
                    }
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
                matadorGUI.moveCars(i, player[i].getCurrentPosition(), player[i].updatePosition(matadorGUI.getMoveDebug()));
                matadorGUI.updateGuiBalance(i, player[i].getBalance());
                fieldProperties.setPosition(player[i].getCurrentPosition());


                landOnField.FieldPosition(player[i].getCurrentPosition(),player[i],i, dices.getValue());
                matadorGUI.RentOnField(fieldProperties);
                losingConditions.CheckPlayerWorth(player[i],matadorGUI,TurnChoices,i,player,mortgage,fieldProperties);

                /*if (player[i].getBalance() < 0) {

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
                }*/

                if(rollCheck.twoOfTheSame(dices.Die1(), dices.Die2())) {
                    i = i - 1;
                    matadorGUI.showMessage("Du slog to ens og får en ekstra tur");
                }
            }
        }
    }
}