package Game.controller.LandOnFields;

import Game.Model.Cards;
import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.Model.Player;

public class LandOnField {

    private final MatadorGui matadorGUI;
    private final FieldController fieldProperties;
    private final Cards cards;
    private final int playerAmount;
    private final Player[] player;
    private final String[] playerNames;

    LandOnOwnable landOnOwnable = new LandOnOwnable();
    LandOnNotOwnable landOnNotOwnable = new LandOnNotOwnable();

    public LandOnField(MatadorGui matadorGUI, FieldController fieldProperties, Cards cards, int playerAmount, Player[] player, String[] playerNames) {

        this.matadorGUI = matadorGUI;
        this.fieldProperties = fieldProperties;
        this.cards = cards;
        this.playerAmount = playerAmount;
        this.player = player;
        this.playerNames = playerNames;
    }

    public void FieldPosition(int currentPosition, Player currentPlayer, int playerID, int dices) {

        if (!(fieldProperties.getCurrentField(currentPosition) instanceof OwnableField)) {
            landOnNotOwnable.NotOwnable(matadorGUI, fieldProperties, cards, currentPlayer, playerAmount, playerID, player);
        }
        fieldProperties.setPosition(currentPlayer.getCurrentPosition());
        if (fieldProperties.getCurrentField(currentPlayer.getCurrentPosition()) instanceof OwnableField) {
            landOnOwnable.Ownable(matadorGUI, fieldProperties, currentPlayer, playerID, player, dices, playerNames, cards);
        }
        cards.setChanceCard15or25(false);
    }
}