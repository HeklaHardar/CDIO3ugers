package Game.controller;

import Game.Model.Fields.Field;
import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;

public class Mortgage {
    private int Count;
    private String temp;
    private String[] OwnedFields;
    private String mortgageChoice;

    public void SellMortgage(MatadorGui matadorGUI, FieldController fieldProperties, Player currentPlayer, int playerID) {

        Count = 0;

        while(true) {

            for (int y = 0; y < 40; y++) {
                if (fieldProperties.getFields()[y] instanceof OwnableField) {
                    if (((OwnableField) fieldProperties.getFields()[y]).getOwner() == playerID + 1) {
                        Count += 1;
                    }
                }
            }
                OwnedFields = new String[Count];
                Count = 0;

                for (int u = 0; u < 40; u++) {
                    if (fieldProperties.getFields()[u] instanceof OwnableField) {
                        if (((OwnableField) fieldProperties.getFields()[u]).getOwner() == playerID + 1) {
                            OwnedFields[Count] = fieldProperties.getFields()[u].getName();
                            System.out.println(OwnedFields[Count]);
                            Count += 1;
                        }
                    }
                }

                mortgageChoice = matadorGUI.getUserSelection("Vælg hvad du vil pantsætte: ", OwnedFields);

                Count = 0;

                for (Field field : fieldProperties.getFields()
                ) {
                    if (field instanceof OwnableField) {
                        if (field.getName().equals(mortgageChoice)) {
                            fieldProperties.setPosition(Count);
                            currentPlayer.playerBalanceUpdate(((OwnableField) field).getMortgageValue());
                            matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
                            matadorGUI.setMortgage(Count, ((OwnableField) field).getMortgageValue());
                            fieldProperties.setOwnedFields(playerID + 10);

                        }
                    }
                    Count += 1;
                }
                break;
            }
        }
        public void BuyMortgage(MatadorGui matadorGUI, FieldController fieldProperties, Player currentPlayer, int playerID){

        Count = 0;

            while(true) {

                for(int y = 0; y < 40; y++) {
                    if (fieldProperties.getFields()[y] instanceof OwnableField) {
                        if (((OwnableField) fieldProperties.getFields()[y]).getOwner() == playerID + 10) {
                            Count += 1;
                        }
                    }
                }
                OwnedFields = new String[Count];
                Count = 0;

                for (int u = 0; u < 40; u++){

                    if (fieldProperties.getFields()[u] instanceof OwnableField) {
                        if (((OwnableField) fieldProperties.getFields()[u]).getOwner() == playerID + 10) {
                            OwnedFields[Count] = fieldProperties.getFields()[u].getName();
                            System.out.println(OwnedFields[Count]);
                            Count += 1;
                        }
                    }
                }

                mortgageChoice = matadorGUI.getUserSelection("Vælg hvad du vil købe tilbage: ",OwnedFields);

                Count = 0;

                for (Field field:fieldProperties.getFields()
                ) {
                    if (field instanceof OwnableField) {
                        if (field.getName().equals(mortgageChoice)) {
                            fieldProperties.setPosition(Count);
                            currentPlayer.playerBalanceUpdate(-(int) (((OwnableField) field).getMortgageValue() * 1.1));
                            matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
                            matadorGUI.UnsetMortgage(Count, fieldProperties);
                            fieldProperties.setOwnedFields(playerID + 1);

                        }
                    }
                    Count += 1;
                }
                break;
            }
        }
    }