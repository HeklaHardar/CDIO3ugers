package Game.Model;

public class Cards {

    //Instantiating variables
    private int move;
    private String cardText;
    private boolean hasExtraMoves = false;
    private int money;
    private boolean hasPrisonCard;
    private boolean PrisonChance = false;
    private boolean isSammenskudsgilde = false;
    private int PositionChange;
    private boolean hasPositionChange = false;
    private boolean isChanceCard15or25 = false;

    CardPicker cardPicker = new CardPicker();
    TextReader TextforCards = new TextReader("CardText.txt");

    //Calls for cardPicker to scramble the cards.
    public Cards() {
        cardPicker.CardScrambler();
    }

    //Resets booleans for all the cards, so that they dont interfere with one another
    public void resetStats() {
        hasExtraMoves = false;

        money = 0;
        hasPrisonCard = false;
        isSammenskudsgilde = false;
        hasPositionChange = false;
        PrisonChance = false;
    }

    public void CardPick() {
        //Calls cardPicker.DrawCard for a random int.
        switch (cardPicker.DrawCard()) {
            case 1:
                cardText = TextforCards.getText()[0];
                PositionChange = 39;
                hasPositionChange = true;
                break;
            case 2:
                cardText = TextforCards.getText()[1];
                PositionChange = 24;
                hasPositionChange = true;
                break;
            case 3:
                cardText = TextforCards.getText()[2];
                PositionChange = 5;
                hasPositionChange = true;
                break;
            case 4:
            case 6:
            case 9:
                //Gå i fængsel
                cardText = TextforCards.getText()[8];
                PrisonChance = true;
                break;
            case 5:
                //Parkeringsbøde
                cardText = TextforCards.getText()[4];
                money = -20;
                break;
            case 7:
            case 8:
                cardText = TextforCards.getText()[7];
                move = -3;
                hasExtraMoves = true;
                //Ryk tre felter tilbage
                break;
            case 10:
                //Ryk frem til start
                cardText = TextforCards.getText()[9];
                PositionChange = 0;
                hasPositionChange = true;
                break;
            case 13:
                cardText = TextforCards.getText()[12];
                isSammenskudsgilde = true;
                break;
            case 14:
                cardText = TextforCards.getText()[13];
                money = 200;
                break;
            case 15:
            case 25:
                cardText = TextforCards.getText()[24];
                isChanceCard15or25 = true;
                hasPositionChange = true;
                break;
            case 16:
                cardText = TextforCards.getText()[15];
                money = -100;
                break;
            case 17:
                cardText = TextforCards.getText()[16];
                money = -100;
                break;
            case 18:
            case 23:
                cardText = TextforCards.getText()[17];
                money = -10;
                break;
            case 19:
                cardText = TextforCards.getText()[18];
                money = -20;
                break;
            case 20:
                cardText = TextforCards.getText()[19];
                money = -20;
                break;
            case 21:
                cardText = TextforCards.getText()[20];
                money = 25;
                break;
            case 22:
            case 24:
                cardText = TextforCards.getText()[21];
                hasPrisonCard = true;
                break;
            case 26:
                cardText = TextforCards.getText()[25];
                money = 20;
                break;
            case 27:
                cardText = TextforCards.getText()[26];
                money = 1;
                break;
            case 28:
                cardText = TextforCards.getText()[27];
                money = 50;
                break;
            case 29:
                cardText = TextforCards.getText()[28];
                money = 108;
                break;
            case 30:
                cardText = TextforCards.getText()[29];
                money = 100;
                break;
            case 31:
                cardText = TextforCards.getText()[30];
                money = 50;
                break;
        }
    }

    public String cardToString() {
        return cardText;
    }

    public boolean isHasExtraMoves() {
        return hasExtraMoves;
    }

    public boolean HasPrisonChance() {
        return PrisonChance;
    }

    public boolean isHasPositionChange() {
        return hasPositionChange;
    }

    public int getPositionChange() {
        return PositionChange;
    }

    public int extraMoney() {
        return money;
    }

    public boolean isHasPrisonCard() {
        return hasPrisonCard;
    }

    public boolean isHasSammenskudsgilde() {
        return isSammenskudsgilde;
    }

    public boolean isChanceCard15or25() {
        return isChanceCard15or25;
    }

    public void setChanceCard15or25(boolean chanceCard15or25) {
        isChanceCard15or25 = chanceCard15or25;
    }

    public int distToNearestShippingLine(int currentPosition) {
        if (currentPosition == 36 || currentPosition == 2) {
            PositionChange = 5;
        } else if (currentPosition == 7) {
            PositionChange = 15;
        } else if (currentPosition == 17 || currentPosition == 22) {
            PositionChange = 25;
        } else if (currentPosition == 33) {
            PositionChange = 35;
        }
        return PositionChange;
    }

    public int move() {
        if (hasExtraMoves)
            return move;
        else
            return 0;
    }
}