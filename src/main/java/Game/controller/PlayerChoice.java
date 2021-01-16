package Game.controller;

import Game.Model.Fields.BuildableField;
import Game.Model.Fields.OwnableField;
import Game.Model.Player;
import Game.controller.FieldController;

public class PlayerChoice {

    private FieldController fieldProperties;
    private boolean hasOwnedField;
    private boolean hasOwnedMortgageField;
    private boolean hasOwnedAllFields;
    private boolean hasOwnedBuildings;
    private String[] Options;

    public PlayerChoice(FieldController fieldProperties) {
        this.fieldProperties = fieldProperties;


    }

    public String[] PlayerChoices(int playerID, Player currentPlayer, Player[] Player) {
        hasOwnedField = false;
        hasOwnedMortgageField = false;
        hasOwnedAllFields = false;
        for (int i = 0; i < 40; i++) {
            if (fieldProperties.getFields()[i] instanceof OwnableField) {
                if (((OwnableField) fieldProperties.getFields()[i]).getOwner() == playerID + 1) {
                    hasOwnedField = true;
                }
                if (((OwnableField) fieldProperties.getFields()[i]).getOwner() == playerID + 10) {
                    hasOwnedMortgageField = true;
                }
                if (fieldProperties.getFields()[i] instanceof BuildableField) {
                    if (fieldProperties.hasAllFields(i) && ((OwnableField) fieldProperties.getFields()[i]).getOwner() == playerID + 1) {
                        hasOwnedAllFields = true;
                    }
                }
            } else {
                continue;
            }
        }
        if (hasOwnedAllFields && hasOwnedMortgageField && hasOwnedField) {
            return Options = new String[]{"Pantsæt", "Køb pantsætning tilbage", "Bygninger", "Slå med terningerne"};
        } else if (hasOwnedMortgageField && hasOwnedField) {
            return Options = new String[]{"Pantsæt", "Køb pantsætning tilbage", "Slå med terningerne"};
        } else if (hasOwnedAllFields && hasOwnedField) {
            return Options = new String[]{"Pantsæt", "Bygninger", "Slå med terningerne"};
        } else if (hasOwnedMortgageField) {
            return Options = new String[]{"Køb pantsætning tilbage", "Slå med terningerne"};
        } else if (hasOwnedAllFields) {
            return Options = new String[]{"Pantsæt", "Bygninger", "Slå med terningerne"};
        } else if (hasOwnedField) {
            return Options = new String[]{"Pantsæt", "Slå med terningerne"};
        } else {
            return Options = new String[]{"Slå med terningerne"};
        }
    }

    public String[] LosingChoices(int playerID, Player currentPlayer, Player[] Player) {
        hasOwnedField = false;
        hasOwnedMortgageField = false;
        hasOwnedAllFields = false;
        hasOwnedBuildings = false;
        for (int i = 0; i < 40; i++) {
            if (fieldProperties.getFields()[i] instanceof OwnableField) {
                if (((OwnableField) fieldProperties.getFields()[i]).getOwner() == playerID + 1) {
                    hasOwnedField = true;
                }
                if (((OwnableField) fieldProperties.getFields()[i]).getOwner() == playerID + 10) {
                    hasOwnedMortgageField = true;
                }
                if (fieldProperties.getFields()[i] instanceof BuildableField) {
                    if (((BuildableField) fieldProperties.getFields()[i]).getOwner() == playerID + 1 && ((BuildableField) fieldProperties.getFields()[i]).getHouses() > 0) {

                        hasOwnedBuildings = true;

                    }
                }
                if (fieldProperties.hasAllFields(i) && ((OwnableField) fieldProperties.getFields()[i]).getOwner() == playerID + 1) {
                    hasOwnedAllFields = true;
                }
            } else {
                continue;
            }
        }
        if (hasOwnedBuildings && hasOwnedField) {
            return Options = new String[]{"Pantsæt", "Sælg bygninger", "Giv op"};
        } else if (hasOwnedField) {
            return Options = new String[]{"Pantsæt", "Giv op"};
        } else {
            return Options = new String[]{"Giv op"};
        }
    }
}