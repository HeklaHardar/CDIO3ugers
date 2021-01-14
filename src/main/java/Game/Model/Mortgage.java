package Game.Model;

import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class Mortgage {
    private int Count;
    private String temp;
    private String[] OwnedFields;
    private String mortgageChoice;

    public void Mortage(MatadorGui matadorGUI, FieldController fieldProperties, Player currentPlayer, int playerID, Player[] player, int dices) {

        Count = 0;
        //(fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] != 0 && player[fieldProperties.getOwnedFields()[player[playerID].getCurrentPosition()] - 1].playerString() != player[playerID].playerString())

        while(true) {

            for(int y = 0; y < 40; y++) {

                if (fieldProperties.getOwnedFields()[y] == playerID + 1) {
                    Count += 1;
                }

            }
            OwnedFields = new String[Count];
            Count = 0;

                for (int u = 0; u < 40; u++){


                    if(fieldProperties.getOwnedFields()[u] == playerID+1){
                        OwnedFields[Count] = fieldProperties.getFieldTitles()[u];
                        System.out.println(OwnedFields[Count]);
                        Count += 1;
                    }

                }

               mortgageChoice = matadorGUI.getUserSelection("Vælg hvad du vil pantsætte: ",OwnedFields);

                Count = 0;

            for (String s:fieldProperties.getFieldTitles()
                 ) {
                if(s.equals(mortgageChoice)){
                    fieldProperties.setPosition(Count);
                    currentPlayer.playerBalanceUpdate(fieldProperties.getmortageValues()[Count]);
                    matadorGUI.updateGuiBalance(playerID,currentPlayer.getBalance());
                    matadorGUI.setMortgage(Count, fieldProperties.getmortageValues());
                    fieldProperties.setOwnedFields(playerID + 10);

                }
                Count += 1;
            }
            break;
            }
        }
    }