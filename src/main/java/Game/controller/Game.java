package Game.controller;

import Game.Model.*;
import Game.View.MatadorGui;

import java.util.Arrays;


public class Game {

    private final Player[] player = new Player[6];
    boolean isWinnerWinnerChickenDinner = false;
    private int temporary;
    private int balanceid;
    private int finalbalanceid;
    private int round = 0;

    public static void main(String[] args) throws InterruptedException {
        //Instantiating game object

        Game game = new Game();

        //plays the game
        game.Game();

    }


    public void Game() throws InterruptedException {


        Cards cards = new Cards();
        MatadorGui matadorGUI = new MatadorGui();
        Dices dices = new Dices();
        PrisonConditions prisonproperties = new PrisonConditions();


        GameStart menu = new GameStart();
        matadorGUI.createGui();
        menu.startGame(matadorGUI);
        FieldController fieldProperties = new FieldController();
        fieldProperties.createFields();


        for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
            player[i] = new Player(menu.playernamesToString()[i]);
            player[i].starterScore(menu.getPlayerAmount());
            matadorGUI.guiPlayers(player[i].playerString(), player[i].playerBalance(), i);
        }

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
                while(playerAction!=1){

                    String selection = matadorGUI.getUserSelection("Vælg en plads at bygge på", "Rødovrevej", "Hvidovre", "Roskildevej", "Valby \n Langgade", "Allégade", "Frederiksberg \n Allé",
                            "Bülowsvej", "Gl. Kongevej", "Bernstoffsvej", "Hellerupvej", "Strandvej", "Trianglen", "Østerbro-\ngade \n", "Grønningen",
                            "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade",  "Amagertorv", "Vimmelskaftet","Nygade", "Frederiks-\nberggade \n", "Rådhus-\npladsen \n");
                    boolean titleFound = false;
                    for (int field: fieldProperties.getAvaiableHousePositions()){
                        if(titleFound) {break;}
                            if (field != 0) {
                                if (fieldProperties.getFieldTitles()[field] == selection) {
                                    int buildindex = 0;

                                    for (String title : fieldProperties.getFieldTitles()) {
                                        if (title == selection) {
                                            titleFound = true;

                                            if (titleFound) {
                                                fieldProperties.buildHouses(player[i], i+1, buildindex);

                                                matadorGUI.buyHouse(buildindex, fieldProperties.getHouses()[buildindex],fieldProperties.getOwnedFields(), i+1);
                                                matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                                                playerAction = matadorGUI.getPlayerAction(player[i].playerString());
                                                break;
                                            }

                                        } else {
                                            buildindex++;
                                        }


                                    }
                                }
                            }

                    }
                    if(!titleFound) {
                        matadorGUI.showMessage("Du kan ikke bygge her");
                        playerAction = matadorGUI.getPlayerAction(player[i].playerString());
                    }

                }
                dices.roll();
                matadorGUI.ShowDie(dices.Die1(), dices.Die2());


                //Moves the car on the GUI and checks if player is over start.
                matadorGUI.moveCars(i, player[i].currentPosition(), player[i].updatePosition(dices.getValue()));
                matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                fieldProperties.setPosition(player[i].currentPosition());

                //Checks if player has landed on a chancecard field.
                if (fieldProperties.getdrawCard()) {
                    while (true) {

                        //Draws card and checks what card has been drawn and completes the actions on the card
                        cards.CardPick();
                        matadorGUI.displayCard(cards.cardToString());
                        matadorGUI.gui.getUserButtonPressed(cards.cardToString(), "ok");

                        if (cards.isHasExtraMoves()) {
                            matadorGUI.moveCars(i, player[i].currentPosition(), player[i].updatePosition(cards.move()));
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());

                        }
                        if (cards.isHasPositionChange()) {
                            matadorGUI.moveCars(i, player[i].currentPosition(), player[i].setPosition(cards.getPositionChange()));
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());

                        }
                        if (cards.HasPrisonChance()) {
                            if (!player[i].getPrisonCard()) {
                                matadorGUI.moveToPrison(i, player[i].currentPosition());
                                player[i].setInPrison();
                                matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                            } else if (player[i].getPrisonCard()) {
                                player[i].updatePrisonCard(false);
                            }

                        }

                        player[i].playerBalanceUpdate(cards.extraMoney());
                        matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        if (cards.isHasPrisonCard()) {
                            player[i].updatePrisonCard(true);
                        }
                        if (cards.isHasSammenskudsgilde()) {
                            for (int y = 0; y < menu.getPlayerAmount(); y++) {
                                player[y].playerBalanceUpdate(-25);
                                matadorGUI.updateGuiBalance(y, player[y].playerBalance());
                            }
                            player[i].playerBalanceUpdate(menu.getPlayerAmount() * 25);
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        }
                        if (cards.isHasMatadorlegat()) {
                            for (int y = 0; y < menu.getPlayerAmount(); y++) {
                                player[y].playerBalanceUpdate(-25);
                                matadorGUI.updateGuiBalance(y, player[y].playerBalance());
                            }
                            player[i].playerBalanceUpdate(menu.getPlayerAmount() * 25);
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        }


                        cards.resetStats();
                        break;
                    }


                }
                cards.resetStats();


                //Checks the properties of the field that the player landed on
                if (fieldProperties.getOwnedFields()[player[i].currentPosition()] == 0 && fieldProperties.isOwnable() == 1) {
                    if (matadorGUI.getBuyField() == 1) {
                        player[i].playerBalanceUpdate(-fieldProperties.getValue());
                    }
                    matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                }

                //Pays rent if a field is owned
                if (fieldProperties.getOwnedFields()[player[i].currentPosition()] != 0) {
                    matadorGUI.showMessage(player[i].playerString() + ", du er landet på en ejendom ejet af "+ player[fieldProperties.getOwnedFields()[player[i].currentPosition()] - 1].playerString() + " og betaler " + fieldProperties.calculateRent() + " kr. i husleje");
                    player[fieldProperties.getOwnedFields()[player[i].currentPosition()] - 1].playerBalanceUpdate(fieldProperties.calculateRent());
                    player[i].playerBalanceUpdate(-fieldProperties.calculateRent());
                    matadorGUI.updateGuiBalance(fieldProperties.getOwnedFields()[player[i].currentPosition()] - 1, player[fieldProperties.getOwnedFields()[player[i].currentPosition()] - 1].playerBalance());
                }
                matadorGUI.updateGuiBalance(i, player[i].playerBalance());

                matadorGUI.landOnField(i, player[i].currentPosition(), player[i].playerString(), fieldProperties.isOwnable(), fieldProperties.getOwnedFields());

                fieldProperties.setOwnedFields(i+1);

                if (fieldProperties.isInPrison()) {
                    matadorGUI.showMessage(player[i].playerString() + " skal i fængsel");
                    matadorGUI.moveToPrison(i,player[i].currentPosition());
                    player[i].setInPrison();
                    fieldProperties.resetPrisonStatus();
                }
                if (player[i].playerBalance() < 0) {

                    int[] balances = new int[menu.getPlayerAmount()];
                    for(int j=0; j<
                            balances.length; j++) {
                        int money = player[j].playerBalance();
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
                    matadorGUI.showMessage("Vinderen er: " + player[finalbalanceid].playerString() + " med " + player[finalbalanceid].playerBalance() + " penge");
                    isWinnerWinnerChickenDinner = true;
                    break;
                }

            }

        }


    }


}
