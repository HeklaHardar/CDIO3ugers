package Game.controller;

import Game.Model.Fields.BuildableField;
import Game.Model.Fields.Field;
import Game.Model.Player;
import Game.View.MatadorGui;

public class BuildingController {
    
    private MatadorGui BuildingGUI;
    private FieldController fieldController;
    private Player[] BuildingPlayer;
    
    public BuildingController(MatadorGui BuildingGUI, FieldController BuildingController, Player[] BuildingPlayer){
        this.BuildingGUI = BuildingGUI;
        this.fieldController = BuildingController;
        this.BuildingPlayer = BuildingPlayer;
    }
    public void BuildHouses(int playerID){

        String selection = BuildingGUI.getUserSelection("Vælg en plads at bygge på", "Rødovrevej", "Hvidovre", "Roskildevej", "Valby  Langgade", "Allégade", "Frederiksberg  Allé",
                "Bülowsvej", "Gl. Kongevej", "Bernstoffsvej", "Hellerupvej", "Strandvej", "Trianglen", "Østerbro-gade ", "Grønningen",
                "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "Amagertorv", "Vimmelskaftet", "Nygade", "Frederiks-berggade ", "Rådhus-pladsen ");
        for (Field field : fieldController.getFields()) {
            if (field instanceof BuildableField && field.getName() == selection) {
                String stringchoice = fieldController.buildHouses(BuildingPlayer[playerID], playerID + 1, field.getPosition());
                if(stringchoice=="buildable"){
                    BuildingGUI.buyHouse(field.getPosition(), ((BuildableField) field).getHouses(), ((BuildableField) field).getOwner(), playerID + 1);
                    BuildingGUI.updateGuiBalance(playerID, BuildingPlayer[playerID].getBalance());
                    BuildingGUI.RentOnField(fieldController);
                    break;
                }
                else if ((stringchoice=="houseRequirements")){
                    BuildingGUI.showMessage("Du skal have lige mange huse på alle grundene");
                    break;
                }
                else if(stringchoice=="noMoney"){
                    BuildingGUI.showMessage("Du har ikke nok penge til at købe hus her");
                    break;
                }
                else {
                    BuildingGUI.showMessage("Du kan ikke bygge her");
                    break;
                }
            }
        }
    }
    public void SellHouses(int playerID){

        String selection = BuildingGUI.getUserSelection("Vælg hvad du vil sælge", "Rødovrevej", "Hvidovre", "Roskildevej", "Valby  Langgade", "Allégade", "Frederiksberg  Allé",
                "Bülowsvej", "Gl. Kongevej", "Bernstoffsvej", "Hellerupvej", "Strandvej", "Trianglen", "Østerbro-gade ", "Grønningen",
                "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "Amagertorv", "Vimmelskaftet", "Nygade", "Frederiks-berggade ", "Rådhus-pladsen ");
        for (Field field : fieldController.getFields()) {
            if (field instanceof BuildableField && field.getName() == selection) {
                if(fieldController.RemoveHouses(BuildingPlayer[playerID], playerID + 1, field.getPosition())=="buildable"){
                    BuildingGUI.sellHouse(field.getPosition(), ((BuildableField) field).getHouses(), ((BuildableField) field).getOwner(), playerID + 1);
                    BuildingGUI.updateGuiBalance(playerID, BuildingPlayer[playerID].getBalance());
                    BuildingGUI.RentOnField(fieldController);
                    break;
                }
                else if ((fieldController.RemoveHouses(BuildingPlayer[playerID], playerID + 1, field.getPosition())=="houseRequirements")){
                    BuildingGUI.showMessage("Du skal have lige mange huse på alle grundene");
                }
                else {
                    BuildingGUI.showMessage("Du kan ikke bygge her");
                }
            }
        }
    }

    public void BuildingChoice(int playerID){
        String[] Options = {"Byg hus/hotel", "Sælg hus/hotel"};
        if(BuildingGUI.getPlayerAction(BuildingPlayer[playerID].playerString(),Options) == 5){
            SellHouses(playerID);
        }
        else {
            BuildHouses(playerID);
        }
    }
}