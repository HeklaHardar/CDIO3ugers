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
          //  player[i].starterScore();
            matadorGUI.guiPlayers(player[i].getPlayerString(), player[i].getBalance(), i);
        }

        while (!isWinnerWinnerChickenDinner) {
            for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
                matadorGUI.gui.getUserButtonPressed("Det er " + player[i].getPlayerString() + "'s tur", "Slå terning");
                dices.roll();
                matadorGUI.ShowDie(dices.Die1(), dices.Die2());

                //checks if the player is in prison and releases him if he is.
                player[i].releaseFromPrison(player[i].isInPrison());
                //Moves the car on the GUI and checks if player is over start.
                matadorGUI.moveCars(i, player[i].getCurrentPosition(), player[i].updatePosition(dices.getValue()));

                //Checks if player has landed on a chancecard field.
                if (player[i].getCurrentPosition() == 2000 /* Change this value */) {
                    while (true) {

                        //Draws card and checks what card has been drawn and completes the actions on the card
                        cards.CardPick();
                        matadorGUI.displayCard(cards.cardToString());
                        matadorGUI.gui.getUserButtonPressed(cards.cardToString(), "ok");

                        if (cards.isHasExtraMoves()) {
                            matadorGUI.moveCars(i, player[i].getCurrentPosition(), player[i].updatePosition(cards.move()));
                            matadorGUI.updateGuiBalance(i, player[i].getBalance());

                        }


                        player[i].playerBalanceUpdate(cards.extraMoney());
                        matadorGUI.updateGuiBalance(i, player[i].getBalance());
                        if (cards.isHasPrisonCard()) {
                            player[i].updatePrisonCard(true);
                        }
                        if (cards.isHasBirthday()) {
                            for (int y = 0; y <= menu.getPlayerAmount() - 1; y++) {
                                player[y].playerBalanceUpdate(-1);
                                matadorGUI.updateGuiBalance(y, player[y].getBalance());
                            }
                            player[i].playerBalanceUpdate(menu.getPlayerAmount());
                            matadorGUI.updateGuiBalance(i, player[i].getBalance());
                        }


                        cards.resetStats();
                    }




                }
                cards.resetStats();



                    //Checks the properties of the field that the player landed on
                    properties.Fieldproperties(player[i].getCurrentPosition());
                    if (properties.getOwnedFields()[player[i].getCurrentPosition()] != i + 1) {

                        if(cards.isFreeField() && properties.getOwnedFields()[player[i].getCurrentPosition()] == 0)
                            player[i].playerBalanceUpdate(0);
                        else
                            player[i].playerBalanceUpdate(-properties.calculateValue(player[i].getCurrentPosition()));
                        cards.resetfreeField();

                        //Pays rent if a field is owned
                        if (properties.getOwnedFields()[player[i].getCurrentPosition()] != 0) {
                            player[properties.getOwnedFields()[player[i].getCurrentPosition()] - 1].playerBalanceUpdate(properties.getValue());
                            matadorGUI.updateGuiBalance(properties.getOwnedFields()[player[i].getCurrentPosition()] - 1, player[properties.getOwnedFields()[player[i].getCurrentPosition()] - 1].getBalance());
                        }
                    }
                    matadorGUI.updateGuiBalance(i, player[i].getBalance());

                    matadorGUI.landOnField(i, player[i].getCurrentPosition(), player[i].getPlayerString(), properties.getOwningStatus(), properties.getOwnedFields());

                    properties.setOwnedFields(properties.getOwnedFields(), player[i].getCurrentPosition(), i);


                if (properties.isInPrison()) {
                    matadorGUI.moveToPrison(i,player[i].getCurrentPosition());
                    player[i].setInPrison();
                    properties.resetPrisonStatus();
                }

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
                            matadorGUI.showMessage("Vinderen er: " + player[finalbalanceid].getPlayerString() + " med " + player[finalbalanceid].getBalance() + " penge");
                        isWinnerWinnerChickenDinner = true;
                        break;
                }

            }

        }


    }


}
