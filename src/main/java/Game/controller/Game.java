package Game.controller;

import Game.Model.*;
import Game.View.MatadorGui;



public class Game {

    private final Player[] player = new Player[6];
    boolean isWinnerWinnerChickenDinner = false;
    private int temporary;
    private int balanceid;
    private int finalbalanceid;

    public static void main(String[] args) {
        //Instantiating game object
        Game game = new Game();

        //plays the game
        game.Game();

    }


    public void Game() {


        Cards cards = new Cards();
        MatadorGui matadorGUI = new MatadorGui();
        Dices dices = new Dices();


        Menu menu = new Menu();
        matadorGUI.createGui();
        menu.startGame(matadorGUI);
        Fieldproperties properties = new Fieldproperties();


        for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
            player[i] = new Player(menu.playernamesToString()[i]);
            player[i].starterScore(menu.getPlayerAmount());
            matadorGUI.guiPlayers(player[i].playerString(), player[i].playerBalance(), i);
        }

        while (!isWinnerWinnerChickenDinner) {
            for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
                matadorGUI.gui.getUserButtonPressed("Det er " + player[i].playerString() + "'s tur","Slå terning");
                dices.roll();
                matadorGUI.ShowDie(dices.Die1(), dices.Die2());

                //checks if the player is in prison and releases him if he is.
                player[i].releaseFromPrison(player[i].isInPrison());
                //Moves the car on the GUI and checks if player is over start.
                matadorGUI.moveCars(i, player[i].currentPosition(), player[i].updatePosition(dices.getValue()));

                //Checks if player has landed on a chancecard field.
                if(player[i].currentPosition()==3 ||player[i].currentPosition()==9||player[i].currentPosition()==15||player[i].currentPosition()==21){
                    while(true) {

                        //Draws card and checks what card has been drawn and completes the actions on the card
                        cards.CardPick();
                        matadorGUI.displayCard(cards.cardToString());
                        matadorGUI.gui.getUserButtonPressed(cards.cardToString(),"ok");

                        if (cards.isHasExtraMoves()) {
                            matadorGUI.moveCars(i, player[i].currentPosition(), player[i].updatePosition(cards.move()));
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());

                        }

                        if (cards.isHasintOptions()) {
                            matadorGUI.moveCars(i, player[i].currentPosition(), player[i].updatePosition(matadorGUI.getIntSelection(cards.cardToString(), cards.min(), cards.max())));
                        }
                        player[i].playerBalanceUpdate(cards.extraMoney());
                        matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        if (cards.isHasPrisonCard()) {
                            player[i].updatePrisonCard(true);
                        }
                        if (cards.isHasBirthday()) {
                            for (int y = 0; y <= menu.getPlayerAmount() - 1; y++) {
                                player[y].playerBalanceUpdate(-1);
                                matadorGUI.updateGuiBalance(y, player[y].playerBalance());
                            }
                            player[i].playerBalanceUpdate(menu.getPlayerAmount());
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        }
                        if (cards.isHasStringOptions()) {
                            matadorGUI.moveCars(i, player[i].currentPosition(), player[i].setPosition(matadorGUI.getStringSelection(cards.getPossibleFields())));
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        }
                        if (cards.isMoveOrCard()) {
                            if (matadorGUI.getMoveOrCard() == 1)
                                cards.setDrawAnother(true);
                            else {
                                cards.setDrawAnother(false);
                                matadorGUI.moveCars(i,player[i].currentPosition(),player[i].setPosition(player[i].updatePosition(1)));
                            }
                            cards.resetStats();
                        }
                        //Checks if player has chosen to draw another card, if so reruns the loop
                        if(cards.isDrawAnother()) {

                            cards.setDrawAnother(false);
                        }
                        else
                            break;

                    }
                    cards.resetStats();
                }


                    //Checks the properties of the field that the player landed on
                    properties.Fieldproperties(player[i].currentPosition());
                    if (properties.getOwnedFields()[player[i].currentPosition()] != i + 1) {

                        if(cards.isFreeField() && properties.getOwnedFields()[player[i].currentPosition()] == 0)
                            player[i].playerBalanceUpdate(0);
                        else
                            player[i].playerBalanceUpdate(-properties.calculateValue(player[i].currentPosition()));
                        cards.resetfreeField();

                        //Pays rent if a field is owned
                        if (properties.getOwnedFields()[player[i].currentPosition()] != 0) {
                            player[properties.getOwnedFields()[player[i].currentPosition()] - 1].playerBalanceUpdate(properties.getValue());
                            matadorGUI.updateGuiBalance(properties.getOwnedFields()[player[i].currentPosition()] - 1, player[properties.getOwnedFields()[player[i].currentPosition()] - 1].playerBalance());
                        }
                    }
                    matadorGUI.updateGuiBalance(i, player[i].playerBalance());

                    matadorGUI.landOnField(i, player[i].currentPosition(), player[i].playerString(), properties.getOwningStatus(), properties.getOwnedFields());

                    properties.setOwnedFields(properties.getOwnedFields(), player[i].currentPosition(), i);


                if (properties.isInPrison()) {
                    matadorGUI.moveToPrison(i,player[i].currentPosition());
                    player[i].setInPrison();
                    properties.resetPrisonStatus();
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
