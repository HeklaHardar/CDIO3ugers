package Game.controller;

import Game.Model.*;
import Game.View.MatadorGui;


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

                matadorGUI.gui.getUserButtonPressed("Det er " + player[i].playerString() + "'s tur", "Slå terning");
                dices.roll();
                matadorGUI.ShowDie(dices.Die1(), dices.Die2());

                //Moves the car on the GUI and checks if player is over start.
                matadorGUI.moveCars(i, player[i].currentPosition(), player[i].updatePosition(2));
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

                        if (cards.isChanceCard15or25() || cards.isHasPositionChange()) {                                                                                                                   // added if-block
                            matadorGUI.moveCars(i, player[i].currentPosition(), player[i].setPosition(cards.distToNearestShippingLine(player[i].currentPosition())));
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        }

                        if (cards.isHasPositionChange() && !cards.isChanceCard15or25()) {       // changed line
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

                fieldProperties.setPosition(player[i].currentPosition());                 // line added
                //Checks the properties of the field that the player landed on
                if (fieldProperties.getOwnedFields()[player[i].currentPosition()] == 0 && fieldProperties.isOwnable() == 1) {
                    if (matadorGUI.getBuyField() == 1) {
                        player[i].playerBalanceUpdate(-fieldProperties.calculateValue());
                    }
                    matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                }

                //Pays rent if a field is owned
                if (fieldProperties.getOwnedFields()[player[i].currentPosition()] != 0) {
                    matadorGUI.showMessage(player[i].playerString() + ", du er landet på en ejendom ejet af "+ player[fieldProperties.getOwnedFields()[player[i].currentPosition()] - 1].playerString() + " og betaler " + fieldProperties.getRent() + " kr. i husleje");
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
