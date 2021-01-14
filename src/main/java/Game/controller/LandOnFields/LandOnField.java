package Game.controller.LandOnFields;

import Game.Model.Cards;
import Game.Model.Fields.OwnableField;
import Game.View.MatadorGui;
import Game.controller.FieldController;
import Game.controller.Player;

public class LandOnField {

    private final MatadorGui matadorGUI;
    private final FieldController fieldProperties;
    private final Cards cards;
    private final int playerAmount;
    private final Player[] player;

    LandOnOwnable landOnOwnable = new LandOnOwnable();
    LandOnNotOwnable landOnNotOwnable = new LandOnNotOwnable();

    public LandOnField(MatadorGui matadorGUI, FieldController fieldProperties, Cards cards, int playerAmount, Player[] player) {

        this.matadorGUI = matadorGUI;
        this.fieldProperties = fieldProperties;
        this.cards = cards;
        this.playerAmount = playerAmount;
        this.player = player;
    }

    public void FieldPosition(int currentPosition, Player currentPlayer, int playerID, int dices) {

        if (!(fieldProperties.fields(currentPosition) instanceof OwnableField)) {
            landOnNotOwnable.NotOwnable(matadorGUI, fieldProperties, cards, currentPlayer, playerAmount, playerID, player);
        }
        fieldProperties.setPosition(currentPlayer.getCurrentPosition());
        if (fieldProperties.fields(currentPlayer.getCurrentPosition()) instanceof OwnableField) {
            landOnOwnable.Ownable(matadorGUI, fieldProperties, currentPlayer, playerID, player, dices, cards);  // ADDED CARDS
        }
        cards.setChanceCard15or25(false);  // added   (optimalt skulle dette gøres i resetStats, og resetStats stå her
    }
}