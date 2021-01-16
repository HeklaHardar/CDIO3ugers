package Game.controller;

import Game.Model.Fields.*;
import Game.Model.Player;
import Game.View.MatadorGui;
import Game.controller.FieldController;

public class Mortgage {
    private int Count;
    private String temp;
    private String[] OwnedFields;
    private String mortgageChoice;
    private boolean hasHouses;
    private String Color = null;
    private int size;
    private Field[] fields;

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
                            Count += 1;
                        }
                    }
                }

                mortgageChoice = matadorGUI.getUserSelection("Vælg hvad du vil pantsætte: ", OwnedFields);

                Count = 0;
                hasHouses = false;
                fields = fieldProperties.getFields();
                size = fieldProperties.getFields().length;
                Color = null;

                for (int i = 0;i<size;i++){
                    if (fields[i] instanceof BuildableField) {
                        if (fields[i].getName().equals(mortgageChoice) && !(((BuildableField) fields[i]).getColor().equals(Color))) {
                            Color = ((BuildableField) fields[i]).getColor();
                            i = 0;
                            continue;
                        }
                    }
                    if (fields[i] instanceof OwnableField && fields[i] instanceof BuildableField) {
                        if ((((BuildableField) fields[i]).getHouses() > 0) && !(fields[i].getName().equals(mortgageChoice)) && Color == ((BuildableField) fields[i]).getColor()) {
                            hasHouses = true;
                        }
                    }}

                for (Field field : fieldProperties.getFields()
                ) {
                    if (field instanceof BuildableField) {

                        if (field.getName().equals(mortgageChoice) && !(((BuildableField) field).getHouses() > 0) && !hasHouses) {
                            fieldProperties.setPosition(Count);
                            currentPlayer.playerBalanceUpdate(((OwnableField) field).getMortgageValue());
                            matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
                            matadorGUI.setMortgage(Count, ((OwnableField) field).getMortgageValue());
                            fieldProperties.setOwnedFields(playerID + 10);
                            matadorGUI.RentOnField(fieldProperties);

                        } else if (field.getName().equals(mortgageChoice) && (((BuildableField) field).getHouses() > 0) || hasHouses && field.getName().equals(mortgageChoice)) {
                            matadorGUI.showMessage("Du har huse som du skal sælge før du kan pantsætte");
                        }
                    }
                    if (field.getName().equals(mortgageChoice) && (field instanceof ShippingLine || field instanceof Brewery)) {
                            fieldProperties.setPosition(Count);
                            currentPlayer.playerBalanceUpdate(((OwnableField) field).getMortgageValue());
                            matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
                            matadorGUI.setMortgage(Count, ((OwnableField) field).getMortgageValue());
                            fieldProperties.setOwnedFields(playerID + 10);
                            matadorGUI.RentOnField(fieldProperties);
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
                            Count += 1;
                        }
                    }
                }

                mortgageChoice = matadorGUI.getUserSelection("Vælg hvad du vil købe tilbage: ",OwnedFields);

                Count = 0;

                for (Field field:fieldProperties.getFields()
                ) {
                    if (field instanceof OwnableField) {
                        if (field.getName().equals(mortgageChoice) && currentPlayer.getBalance() >= (int) (((OwnableField) field).getMortgageValue()*1.1)) {
                            fieldProperties.setPosition(Count);
                            currentPlayer.playerBalanceUpdate(-(int) (((OwnableField) field).getMortgageValue() * 1.1));
                            matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
                            matadorGUI.UnsetMortgage(Count, fieldProperties);
                            fieldProperties.setOwnedFields(playerID + 1);
                            matadorGUI.RentOnField(fieldProperties);
                        }

                        else if (field.getName().equals(mortgageChoice)) {
                            matadorGUI.showMessage("Du har ikke råd til at købe pantsætningen tilbage");
                        }
                    }
                    Count += 1;
                }
                break;
            }
        }
    }