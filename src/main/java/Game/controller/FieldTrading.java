package Game.controller;

import Game.Model.Fields.*;
import Game.Model.Player;
import Game.View.MatadorGui;

public class FieldTrading {

    private final FieldController fieldProperties;
    private int count;
    private final Player[] player;
    private final MatadorGui matadorGUI;
    private String[] ownedFields;
    private String tradeChoice;
    private String playerChoice;

    private String color = null;
    private int size;
    private Field[] fields;
    private boolean hasHouses;

    public FieldTrading(MatadorGui matadorGUI, FieldController fieldProperties, Player[] player) {

        this.matadorGUI = matadorGUI;
        this.fieldProperties = fieldProperties;
        this.player = player;

    }

    public void Trade(int playerID, Player currentPlayer) {
        count = 0;
        while (true) {

            for (int y = 0; y < 40; y++) {
                if (fieldProperties.getFields()[y] instanceof OwnableField) {
                    if (((OwnableField) fieldProperties.getFields()[y]).getOwner() == playerID + 1) {
                        count += 1;
                    }
                }
            }
            ownedFields = new String[count];
            count = 0;

            for (int u = 0; u < 40; u++) {

                if (fieldProperties.getFields()[u] instanceof OwnableField) {
                    if (((OwnableField) fieldProperties.getFields()[u]).getOwner() == playerID + 1) {
                        ownedFields[count] = fieldProperties.getFields()[u].getName();
                        count += 1;
                    }
                }
            }

            tradeChoice = matadorGUI.getUserSelection("Vælg hvilken grund du vil sælge til en anden spiller: ", ownedFields);

            count = 0;
            hasHouses = false;
            fields = fieldProperties.getFields();
            size = fieldProperties.getFields().length;
            color = null;

            for (int i = 0; i < size; i++) {
                if (fields[i] instanceof BuildableField) {
                    if (fields[i].getName().equals(tradeChoice) && !(((BuildableField) fields[i]).getColor().equals(color))) {
                        color = ((BuildableField) fields[i]).getColor();
                        i = 0;
                        continue;
                    }
                }
                if (fields[i] instanceof OwnableField && fields[i] instanceof BuildableField) {
                    if ((((BuildableField) fields[i]).getHouses() > 0) && !(fields[i].getName().equals(tradeChoice)) && color == ((BuildableField) fields[i]).getColor()) {
                        hasHouses = true;
                    }
                }
            }

            for (Field field : fieldProperties.getFields()
            ) {
                if (field instanceof BuildableField) {
                    if (field.getName().equals(tradeChoice) && !(((BuildableField) field).getHouses() > 0) && !hasHouses) {
                        playerChoice = matadorGUI.gui.getUserString("Prisen på feltet er: kr. " + ((BuildableField) field).getValue() + ". Indtast navnet på spilleren, som vil købe feltet: " );
                        for (Player player : player
                        ) {
                            if (playerChoice.toLowerCase().equals(player.playerString().toLowerCase()) && player.getBalance() >= ((BuildableField) field).getValue()) {

                                fieldProperties.setPosition(count);

                                //Pays owner of the field
                                currentPlayer.playerBalanceUpdate(((OwnableField) field).getValue());
                                matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());

                                //Withdraws from the new owner
                                player.playerBalanceUpdate(-((OwnableField) field).getValue());
                                matadorGUI.updateGuiBalance(player.getPlayerID(), player.getBalance());

                                //Sets the new owner as the owner of the field
                                fieldProperties.tradeOwnedField(player.getPlayerID() + 1);

                                //Sets color and
                                matadorGUI.tradeField(player.getPlayerID(), count, player.playerString(), fieldProperties.isOwnable());
                                matadorGUI.RentOnField(fieldProperties);

                            } else if (field.getName().equals(tradeChoice) && (((BuildableField) field).getHouses() > 0) || hasHouses && field.getName().equals(tradeChoice)) {
                                matadorGUI.showMessage("Du har huse som du skal sælge før du kan sælge denne grund");
                            }
                        }
                    }
                }
                if (field.getName().equals(tradeChoice) && (field instanceof ShippingLine || field instanceof Brewery)) {
                    playerChoice = matadorGUI.gui.getUserString("Indtast navnet på spilleren, som vil købe feltet: ");

                    for (Player player : player) {
                        if (playerChoice.toLowerCase().equals(player.playerString().toLowerCase()) && player.getBalance() >= ((OwnableField) field).getValue()) {

                            fieldProperties.setPosition(count);

                            currentPlayer.playerBalanceUpdate(((OwnableField) field).getValue());
                            matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());

                            player.playerBalanceUpdate(-((OwnableField) field).getValue());
                            matadorGUI.updateGuiBalance(player.getPlayerID(), player.getBalance());

                            fieldProperties.tradeOwnedField(player.getPlayerID() + 1);
                            matadorGUI.tradeField(player.getPlayerID(), count, player.playerString(), fieldProperties.isOwnable());
                            matadorGUI.RentOnField(fieldProperties);
                        }
                    }
                }
                count += 1;
            }
            break;
        }

    }




}



