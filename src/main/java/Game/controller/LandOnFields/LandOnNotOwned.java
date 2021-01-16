package Game.controller.LandOnFields;

import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.Model.Player;

public class LandOnNotOwned {

    private String playerName;
    private int decision;


    public void notOwned(MatadorGui matadorGUI, Player currentPlayer, FieldController properties, int playerID, Player[] players, String[] playerNames) {
        while (true) {

            decision = matadorGUI.getBuyField(properties.getFields()[currentPlayer.getCurrentPosition()].getName());

            if (decision == 1 && currentPlayer.getBalance() >= properties.getValue()) {
                currentPlayer.playerBalanceUpdate(-properties.getValue());
                matadorGUI.landOnField(playerID, currentPlayer.getCurrentPosition(), currentPlayer.playerString(), properties.isOwnable(), ((OwnableField) properties.getFields()[currentPlayer.getCurrentPosition()]).getOwner());
                properties.setOwnedFields(playerID + 1);
                currentPlayer.playerWorthUpdate(properties.getValue());
                break;
            } else if (decision == 1 && currentPlayer.getBalance() < properties.getValue()) {
                matadorGUI.showMessage("Du har ikke nok penge til at købe feltet");
            }

            while (true) {
                    if (matadorGUI.getAuction(properties.getFields()[currentPlayer.getCurrentPosition()].getName()).equals("Ja")) {

                        playerName = matadorGUI.gui.getUserString("Indtast navnet på spilleren der vil købe feltet: ");

                        for (Player s : players
                        ) {
                            if (s.playerString().toLowerCase().equals(playerName.toLowerCase()) && s.getBalance() >= properties.getValue()) {
                                //  s.playerBalanceUpdate();
                                s.playerBalanceUpdate(-properties.getValue());
                                matadorGUI.landOnField(s.getPlayerID(), currentPlayer.getCurrentPosition(), s.playerString(), properties.isOwnable(), ((OwnableField) properties.getFields()[currentPlayer.getCurrentPosition()]).getOwner());
                                properties.setOwnedFields(s.getPlayerID() + 1);
                                matadorGUI.updateGuiBalance(s.getPlayerID(), s.getBalance());
                                s.playerWorthUpdate(properties.getValue());
                                break;
                            } else if (s.playerString().toLowerCase().equals(playerName.toLowerCase()) && s.getBalance() < properties.getValue() && s.isInGame()) {
                                matadorGUI.showMessage(s.playerString() + ", du har ikke nok penge til at købe feltet.");
                                
                            } else if (s.playerString().toLowerCase().equals(playerName.toLowerCase()) && !s.isInGame()) {
                                matadorGUI.showMessage(s.playerString() + ", du er ikke med i spillet mere");

                            }
                        }
                    } else {
                        break;
                    }
                }
            break;

        }
        matadorGUI.updateGuiBalance(playerID, currentPlayer.getBalance());
    }
}