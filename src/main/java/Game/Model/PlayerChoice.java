package Game.Model;

import Game.Model.Fields.OwnableField;
import Game.controller.FieldController;
import Game.controller.Player;

public class PlayerChoice {

    private FieldController fieldProperties;
    private boolean hasOwnedField;
    private boolean hasOwnedMortgageField;
    private boolean hasOwnedAllFields;
    private String[] Options;

    public PlayerChoice(FieldController fieldProperties){
        this.fieldProperties = fieldProperties;


    }

    public String[] PlayerChoices(int playerID, Player currentPlayer, Player[] Player){
        hasOwnedField = false;
        hasOwnedMortgageField = false;
        hasOwnedAllFields = false;
        for (int i = 0;i<40;i++){
            if (fieldProperties.getFields()[i] instanceof OwnableField) {
            if(((OwnableField)fieldProperties.getFields()[i]).getOwner() == playerID + 1){
                hasOwnedField = true;
            }
            if (((OwnableField)fieldProperties.getFields()[i]).getOwner() == playerID + 10){
                hasOwnedMortgageField = true;
            }
            if (fieldProperties.hasAllFields(i) && ((OwnableField)fieldProperties.getFields()[i]).getOwner() == playerID + 1){
                hasOwnedAllFields = true;
            }
            }
            else {
                continue;
            }
        }
        if (hasOwnedAllFields && hasOwnedMortgageField && hasOwnedField){
            return Options = new String[]{"Pantsæt", "Køb pantsætning tilbage", "Byg hus/hotel","Slå med terningerne"};
        }
        else if (hasOwnedMortgageField && hasOwnedField) {
            return Options = new String[]{"Pantsæt", "Køb pantsætning tilbage","Slå med terningerne"};
        }
        else if (hasOwnedAllFields && hasOwnedField){
            return Options = new String[]{"Pantsæt","Byg hus/hotel","Slå med terningerne"};
        }
        else if (hasOwnedMortgageField){
            return Options = new String[]{"Køb pantsætning tilbage","Slå med terningerne"};
        }
        else if (hasOwnedAllFields){
            return Options = new String[]{"Pantsæt","Byg hus/hotel","Slå med terningerne"};
        }
        else if (hasOwnedField){
            return Options = new String[]{"Pantsæt","Slå med terningerne"};
        }
        else{
            return Options = new String[]{"Slå med terningerne"};
        }
    }
}