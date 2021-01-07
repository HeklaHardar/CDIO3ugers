// The method Game() contains the logic of the monopoly game. The method first instantiates several objects and uses a for loop to create players.
// Then a while loop iterates once for each round in the game. Inside the while loop, a for loop iterates once for each turn.
// This for loops make sure that a players turn starts with rolling the dices, checks if the player is in prison,
// controls chance cards and terminates game if a players number of points is below zero etc.


package Game.controller;                                                           // package

import Game.Model.*;                                                               // imports classes Cards, Dices and Fieldproperties
import Game.View.MatadorGui;                                                       // imports class MatadorGui
import gui_main.*;                                                                 // unused import



public class Game {                                                                // Game class is defined

    private final Player[] player = new Player[6];                                 // An array called "player" of type Player[] is declared and instantiated
    boolean isWinnerWinnerChickenDinner = false;
    /*private int temporary;
    private int balanceid;
    private int finalbalanceid;*/

    public static void main(String[] args) throws InterruptedException {           // psvm method creates object called "game" and calls Game method

        Game game = new Game();                                                    //Instantiating game object

        game.Game();                                                               //plays the game

    }


    public void Game() throws InterruptedException {                               // Game method is defined


        Cards cards = new Cards();                                                 // object cards of class Cards is declared
        MatadorGui matadorGUI = new MatadorGui();                                  // object matadorGui of class MatadorGui is declared
        Dices dices = new Dices();                                                 // object dices of class Dices is declared


        Menu menu = new Menu();                                                    // object menu of class Menu is declared
        matadorGUI.createGui();
        menu.startGame(matadorGUI);                                                // startGame method lets user enter number of players, and saves it as playerNumber
                                                                                   // The method also lets user enter player names and saves them in string[] called Players
        Fieldproperties properties = new Fieldproperties();                        // object properties of class Fieldproperties is declared


        for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {                                // getPlayerAmount method is a getter for variable playerNumber, so for loop iterates once per player
            player[i] = new Player(menu.playernamesToString()[i]);                             // Length of string[] named Player equals number of players. Correct number of players are created??
            player[i].starterScore(menu.getPlayerAmount());                                    // method gives each player 1500 points before start. Notice that argument (number of players) is not used
            matadorGUI.guiPlayers(player[i].playerString(), player[i].playerBalance(), i);     // creates player and car and makes car visible
        }

        while (!isWinnerWinnerChickenDinner) {                                    // while loop continues until a player wins. One iteration for each round
            for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {               // One iteration for each turn in a round
                matadorGUI.gui.getUserButtonPressed("Det er " + player[i].playerString() + "'s tur", "Slå terning");   // prints text and asks user to press "Slå terning"
                dices.roll();                                                     // object dices calls roll() method from dices class. This method calls roll() method from die class for each die
                matadorGUI.ShowDie(dices.Die1(), dices.Die2());                   // ShowDie method calls setDice() method. This method shows two dices and a number of pips for each when game is played

                player[i].releaseFromPrison(player[i].isInPrison());                                                 //checks if the player is in prison and releases him if he is
                matadorGUI.moveCars(i, player[i].currentPosition(), player[i].updatePosition(dices.getValue()));     //Moves the car on the GUI and checks if player is over start.
                matadorGUI.updateGuiBalance(i, player[i].playerBalance());                                           // changes a players number of points (the method calls another method named setBalance())?????

                if (player[i].currentPosition() == 2 || player[i].currentPosition() == 7 || player[i].currentPosition() == 17 ||      //If-block is executed if player has landed on a chance card field.
                        player[i].currentPosition() == 22 || player[i].currentPosition() == 33 || player[i].currentPosition() == 36 /* Change this value */) {
                    while (true) {                                                                                                    // while loop only iterates once?

                        cards.CardPick();                                                                        //Draws card and checks what card has been drawn and completes the actions on the card
                        matadorGUI.displayCard(cards.cardToString());                                            // displayCard() calls another method name displayChanceCard(cardText) that sets and shows text in center of board
                        matadorGUI.gui.getUserButtonPressed(cards.cardToString(), "ok" );               // prints cardText and asks user to press "ok"

                        if (cards.isHasExtraMoves()) {                                                          // the if-block is executed if the drawn card allows player to move a given number of spaces
                            matadorGUI.moveCars(i, player[i].currentPosition(), player[i].updatePosition(cards.move()));
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());

                        }
                        if (cards.isHasPositionChange()) {                                                     // the if-block is executed if the drawn card allows player to go to a specific field
                            matadorGUI.moveCars(i, player[i].currentPosition(), player[i].setPosition(cards.getPositionChange()));
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());

                        }
                        if (cards.HasPrisonChance()) {                                                         // the if-block is executed if the drawn card tells player to go to prison????
                            if (!player[i].getPrisonCard()){
                                matadorGUI.moveToPrison(i, player[i].currentPosition());
                                matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                            }
                            else if (player[i].getPrisonCard()){                                               // else if-block is executed if the player has a "prison card" which means he avoids prison
                                player[i].updatePrisonCard(false);
                            }

                        }

                        player[i].playerBalanceUpdate(cards.extraMoney());
                        matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        if (cards.isHasPrisonCard()) {                                                        // the if-block is executed if the drawn card can release player from prison
                            player[i].updatePrisonCard(true);
                        }
                        if (cards.isHasSammenskudsgilde()) {                                                  // the if-block is executed if the drawn card is "Sammenskudsgilde"
                            for (int y = 0; y < menu.getPlayerAmount(); y++) {
                                player[y].playerBalanceUpdate(-25);
                                matadorGUI.updateGuiBalance(y, player[y].playerBalance());
                            }
                            player[i].playerBalanceUpdate(menu.getPlayerAmount()*25);
                            matadorGUI.updateGuiBalance(i, player[i].playerBalance());
                        }


                        cards.resetStats();                                                                  // the method resets all the booleans, that controls the above if-blocks, to zero.
                        break;                                                                               // so that the player is ready to draw a new chance card in the next round
                    }




                }
                cards.resetStats();                                                                         // same method is called in if-block but outside while loop ????



                    properties.Fieldproperties(player[i].currentPosition());                               //Checks the properties of the field that the player landed on
                    if (properties.getOwnedFields()[player[i].currentPosition()] != i + 1) {               // if-block is executed if the player does not own the field

                        if(cards.isFreeField() && properties.getOwnedFields()[player[i].currentPosition()] == 0)      // if-block is executed if field is free
                            player[i].playerBalanceUpdate(0);                                                         // points are updated for a player
                        else
                            player[i].playerBalanceUpdate(-properties.calculateValue(player[i].currentPosition()));   // pays for the field???????
                        cards.resetfreeField();

                        if (properties.getOwnedFields()[player[i].currentPosition()] != 0) {                          // if-block is executed if the field is owned
                            player[properties.getOwnedFields()[player[i].currentPosition()] - 1].playerBalanceUpdate(properties.getValue());
                            matadorGUI.updateGuiBalance(properties.getOwnedFields()[player[i].currentPosition()] - 1, player[properties.getOwnedFields()[player[i].currentPosition()] - 1].playerBalance());
                        }
                    }
                    matadorGUI.updateGuiBalance(i, player[i].playerBalance());                                              // The method updates the number of points for a player in the gui

                    matadorGUI.landOnField(i, player[i].currentPosition(), player[i].playerString(), properties.getOwningStatus(), properties.getOwnedFields());   // updates information and sends it to gui

                    properties.setOwnedFields(properties.getOwnedFields(), player[i].currentPosition(), i);                 // updates information and sends it to gui


                if (properties.isInPrison()) {                                                                             // the if-block is executed if player is sent to prison
                    matadorGUI.moveToPrison(i,player[i].currentPosition());                                                // car is moved to prison field (this method uses setCar() method)
                    player[i].setInPrison();                                                                               // sets inPrison to true and position = 6 ????
                    properties.resetPrisonStatus();                                                                        // sets inPrison to false
                }

                    if (player[i].playerBalance() < 0) {                                                                   // if-block is executed if points/money is below zero and will eventually terminate game

                        int[] balances = new int[menu.getPlayerAmount()];                                                  // int[] named balances is declared and initialized
                        for(int j=0; j<                                                                                    // For loop iterates once for each player
                                balances.length; j++) {
                            int money = player[j].playerBalance();                                                         // Variable money is set to number of points/money for player j
                            balances[j] = money;                                                                           // The variable is put into the int[] named balances
                        }
                        int temporary=0;
                        int balanceid = 0;
                        int finalbalanceid = 0;

                        for(int balance:balances){
                            if(temporary<balance){
                                temporary = balance;
                                finalbalanceid = balanceid;

                            }
                                balanceid+=1;
                        }
                            matadorGUI.showMessage("Vinderen er: " + player[finalbalanceid].playerString() + " med " + player[finalbalanceid].playerBalance() + " penge");   // prints sentence
                        isWinnerWinnerChickenDinner = true;
                        break;
                }

            }

        }


    }


}
