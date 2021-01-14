package Game.Model;

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

    public String[] PlayerChoices(int playerID, Player currentPlayer){
        for (int i = 0;i<40;i++){
            if(fieldProperties.getOwnedFields()[i] == playerID + 1){
                hasOwnedField = true;
            }
            if (fieldProperties.getOwnedFields()[i] == playerID + 10){
                hasOwnedMortgageField = true;
            }
            if (fieldProperties.hasAllFields(currentPlayer.getCurrentPosition())){
                hasOwnedAllFields = true;
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