package Game.controller;

import Game.Model.*;
import Game.controller.LandOnFields.LandOnField;
import Game.View.MatadorGui;


public class Game {

    private Player[] player;
    boolean isWinnerWinnerChickenDinner = false;
    private int round = 0;
    private int remainingPlayers;

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
        rollOfDices rollCheck = new rollOfDices();
        Mortgage mortgage = new Mortgage();

        GameStart menu = new GameStart();
        matadorGUI.createGui();
        menu.startGame(matadorGUI);
        FieldController fieldProperties = new FieldController();
        PlayerChoice TurnChoices = new PlayerChoice(fieldProperties);

        player = new Player[menu.getPlayerAmount()];
        BuildingController buildingController = new BuildingController(matadorGUI, fieldProperties, player);
        LosingConditions losingConditions = new LosingConditions(matadorGUI, TurnChoices, player, mortgage, fieldProperties, buildingController);

        for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {
            player[i] = new Player(menu.playernamesToString()[i], i);
            matadorGUI.guiPlayers(player[i].playerString(), player[i].getBalance(), i);
        }

        remainingPlayers = menu.getPlayerAmount();
        LandOnField landOnField = new LandOnField(matadorGUI, fieldProperties, cards, menu.getPlayerAmount(), player, menu.playernamesToString());

        while (!isWinnerWinnerChickenDinner) {

            round = round + 1;
            for (int i = 0; i <= menu.getPlayerAmount() - 1; i++) {

                if (!player[i].isInGame()) {
                    continue;
                }

                remainingPlayers = menu.getPlayerAmount();
                for (Player players : player
                ) {
                    if (!players.isInGame()) {
                        remainingPlayers--;
                    }
                    if (remainingPlayers == 1) {
                        while (true) {
                            matadorGUI.showMessage(player[i].playerString() + " du har VUNDET spillet!");
                            System.exit(0);
                        }
                    }
                }

                //checks if the player is in prison and releases him if he is.
                if (player[i].isInPrison()) {
                    prisonproperties.Release(player[i], dices, matadorGUI, i);
                    if (player[i].isInPrison()) {
                        player[i].InPrison();
                        continue;
                    }
                }
                while (true) {
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
                        buildingController.BuildingChoice(i);

                    }
                }
                dices.roll();
                matadorGUI.ShowDie(dices.Die1(), dices.Die2());

                if (rollCheck.twoOfTheSameThreeTimes(dices.Die1(), dices.Die2(), i, player[i], matadorGUI)) {
                    matadorGUI.showMessage("Du slog to ens tre gange i træk, og rykker til gå i fængsel");
                    fieldProperties.setPosition(30);
                    landOnField.FieldPosition(player[i].getCurrentPosition(), player[i], i, 1);
                    continue;
                }
                //Moves the car on the GUI and checks if player is over start.
                matadorGUI.moveCars(i, player[i].getCurrentPosition(), player[i].updatePosition(matadorGUI.getMoveDebug()));
                matadorGUI.updateGuiBalance(i, player[i].getBalance());
                fieldProperties.setPosition(player[i].getCurrentPosition());


                landOnField.FieldPosition(player[i].getCurrentPosition(), player[i], i, dices.getValue());
                matadorGUI.RentOnField(fieldProperties);
                losingConditions.CheckPlayerWorth(player[i], i);

                if (player[i].isInGame()) {

                    if (rollCheck.twoOfTheSame(dices.Die1(), dices.Die2())) {
                        i = i - 1;
                        matadorGUI.showMessage("Du slog to ens og får en ekstra tur");
                    }
                }
            }
        }
    }
}