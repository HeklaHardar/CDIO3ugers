package Game.controller;

import Game.Model.*;
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
        PrisonConditions prisonproperties = new PrisonConditions();

        GameStart menu = new GameStart();
        matadorGUI.createGui();
        menu.startGame(matadorGUI);
        FieldController fieldProperties = new FieldController();
        fieldProperties.createFields();


        for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
            player[i] = new Player(menu.playernamesToString()[i]);
            matadorGUI.guiPlayers(player[i].playerString(), player[i].getBalance(), i);
        }

        LandOnField landOnField = new LandOnField(matadorGUI, fieldProperties, cards, menu.getPlayerAmount(), player);

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
                } else {
                    int playerAction = matadorGUI.getPlayerAction(player[i].playerString());
                    while (playerAction != 1) {

                        String selection = matadorGUI.getUserSelection("Vælg en plads at bygge på", "Rødovrevej", "Hvidovre", "Roskildevej", "Valby  Langgade", "Allégade", "Frederiksberg  Allé",
                                "Bülowsvej", "Gl. Kongevej", "Bernstoffsvej", "Hellerupvej", "Strandvej", "Trianglen", "Østerbro-gade ", "Grønningen",
                                "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "Amagertorv", "Vimmelskaftet", "Nygade", "Frederiks-berggade ", "Rådhus-pladsen ");
                        boolean titleFound = false;
                        for (int field : fieldProperties.getAvaiableHousePositions()) {
                            if (titleFound) {
                                break;
                            }
                            if (field != 0) {
                                if (fieldProperties.getFieldTitles()[field] == selection) {
                                    int buildindex = 0;

                                    for (String title : fieldProperties.getFieldTitles()) {
                                        if (title == selection) {
                                            titleFound = true;

                                            if (titleFound) {
                                                fieldProperties.buildHouses(player[i], i + 1, buildindex);

                                                matadorGUI.buyHouse(buildindex, fieldProperties.getHouses()[buildindex], fieldProperties.getOwnedFields(), i + 1);
                                                matadorGUI.updateGuiBalance(i, player[i].getBalance());
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
                        if (!titleFound) {
                            matadorGUI.showMessage("Du kan ikke bygge her");
                            playerAction = matadorGUI.getPlayerAction(player[i].playerString());
                        }

                    }
                    dices.roll();
                }
                matadorGUI.ShowDie(dices.Die1(), dices.Die2());

                //Moves the car on the GUI and checks if player is over start.
                matadorGUI.moveCars(i, player[i].getCurrentPosition(), player[i].updatePosition( 19/*dices.getValue()*/));
                matadorGUI.updateGuiBalance(i, player[i].getBalance());
                fieldProperties.setPosition(player[i].getCurrentPosition());

                landOnField.FieldPosition(player[i].getCurrentPosition(), player[i], i, dices.getValue());
                //Checks if player has landed on a chancecard field.

                if (player[i].getBalance() < 0) {

                    int[] balances = new int[menu.getPlayerAmount()];
                    for (int j = 0; j <
                            balances.length; j++) {
                        int money = player[j].getBalance();
                        balances[j] = money;
                    }
                    temporary = 0;
                    balanceid = 0;
                    finalbalanceid = 0;

                    for (int balance : balances) {
                        if (temporary < balance) {
                            temporary = balance;
                            finalbalanceid = balanceid;
                        }
                        balanceid += 1;
                    }
                    matadorGUI.showMessage("Vinderen er: " + player[finalbalanceid].playerString() + " med " + player[finalbalanceid].getBalance() + " penge");
                    isWinnerWinnerChickenDinner = true;
                    break;
                }
            }
        }
    }
}