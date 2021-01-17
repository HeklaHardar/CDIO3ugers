package Game.controller;

import Game.Model.Fields.BuildableField;
import Game.Model.Fields.Field;
import Game.Model.Fields.OwnableField;
import Game.Model.Player;
import Game.View.MatadorGui;

public class LosingConditions {

    private PlayerChoice losingChoices;
    private MatadorGui losingConditionsGUI;
    private Mortgage losingMortgage;
    private FieldController losingController;
    private Player[] player;
    private BuildingController buildingController;

    private int Counter;

    public LosingConditions(MatadorGui losingConditionsGUI, PlayerChoice losingChoices, Player[] player, Mortgage losingMortgage, FieldController losingController, BuildingController buildingController) {

        this.losingChoices = losingChoices;
        this.losingConditionsGUI = losingConditionsGUI;
        this.losingMortgage = losingMortgage;
        this.losingController = losingController;
        this.player = player;
        this.buildingController = buildingController;
    }

    public void CheckPlayerWorth(Player currentPlayer, int playerID) {
        Counter = 0;

        if (currentPlayer.getBalance() < 0) {
            while (currentPlayer.getBalance() < 0) {
                int playerAction = losingConditionsGUI.getPlayLossActions(currentPlayer.playerString(), losingChoices.LosingChoices(playerID, currentPlayer, player));

                if (playerAction == 1) {
                    buildingController.SellHouses(playerID);
                } else if (playerAction == 2) {

                    currentPlayer.setInGame(false);
                    losingConditionsGUI.removeCar(playerID, currentPlayer.getCurrentPosition());
                    losingConditionsGUI.setName(currentPlayer.playerString(), playerID);
                    losingConditionsGUI.updateGuiBalance(playerID, 0);
                    for (Field field : losingController.getFields()
                    ) {
                        if (field instanceof OwnableField) {
                            if (((OwnableField) field).getOwner() == playerID + 1 || ((OwnableField) field).getOwner() == playerID + 10) {


                                if(field instanceof BuildableField) {
                                    losingController.destroyHouses(Counter);
                                    losingConditionsGUI.sellHouse(field.getPosition(), 0, ((BuildableField) field).getOwner(), playerID + 1);
                                }
                                ((OwnableField) field).setOwner(0);
                                losingConditionsGUI.unsetField(Counter, String.valueOf(((OwnableField) field).getValue()), field.isOwnable());

                            }
                        }
                        Counter++;
                    }
                    break;
                } else if (playerAction == 3) {
                    losingMortgage.SellMortgage(losingConditionsGUI, losingController, currentPlayer, playerID);
                }
            }
        }
    }
}