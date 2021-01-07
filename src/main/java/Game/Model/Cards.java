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
    private boolean Matadorlegat = false;

    private String[] possibleFields = new String[2];
    private String[] possibleFields2 = new String[4];
    private boolean freeField = false;

    CardPicker cardPicker = new CardPicker();
    CardText TextforCards = new CardText();

    //Calls for cardPicker to scramble the cards.
    public Cards() {
        cardPicker.CardScrambler();
    }

    //Resets booleans for all the cards, so that they dont interfeer with one another
    public void resetStats() {
        hasExtraMoves = false;

        money = 0;
        hasPrisonCard = false;
        isSammenskudsgilde = false;
        hasPositionChange = false;

    }

    //Resets the freefield boolean, if a card with that value has been drawn
    public void resetfreeField() {
        freeField = false;
    }


    public void CardPick() {

        //Calls cardPicker.DrawCard for a random int.
        switch (cardPicker.DrawCard()) {
            case 1:
                cardText = TextforCards.getCards()[0];
                PositionChange = 39;
                hasPositionChange = true;
                break;
            case 2:
                cardText = TextforCards.getCards()[1];
                PositionChange = 24;
                hasPositionChange = true;
                break;
            case 3:
                cardText = TextforCards.getCards()[2];
                PositionChange = 5;
                hasPositionChange = true;
                break;
            case 4:
            case 6:
            case 9:
                //Gå i fængsel
                cardText = TextforCards.getCards()[8];
                PrisonChance = true;
                break;
            case 5:
                //Parkeringsbøde
                cardText = TextforCards.getCards()[4];
                money = -20;
                break;
            case 7:
            case 8:
                cardText = TextforCards.getCards()[7];
                move = -3;
                hasExtraMoves = true;
                //Ryk tre felter tilbage
                break;
            case 10:
                //Ryk frem til start
                cardText = TextforCards.getCards()[9];
                PositionChange = 0;
                hasPositionChange = true;
                break;
            case 11:
                // HUSE OG HOTELLER SKAL LAVE FØRST
                cardText = TextforCards.getCards()[10];
                break;
            case 12:
                // HUSE OG HOTELLER SKAL LAVE FØRST
                cardText = TextforCards.getCards()[11];
                break;
            case 13:
                cardText = TextforCards.getCards()[12];
                isSammenskudsgilde = true;
                break;
            case 14:
                cardText = TextforCards.getCards()[13];
                money = 200;
                break;
            case 15:
            case 25:
                cardText = TextforCards.getCards()[24];
                break;
            case 16:
                cardText = TextforCards.getCards()[15];
                money = -100;
                break;
            case 17:
                cardText = TextforCards.getCards()[16];
                money = -100;
                break;
            case 18:
            case 23:
                cardText = TextforCards.getCards()[17];
                money = -10;
                break;
            case 19:
                cardText = TextforCards.getCards()[18];
                money = -20;
                break;
            case 20:
                cardText = TextforCards.getCards()[19];
                money = -20;
                break;
            case 21:
                cardText = TextforCards.getCards()[20];
                money = 25;
                break;
            case 22:
            case 24:
                cardText = TextforCards.getCards()[21];
                hasPrisonCard = true;
                break;
            case 26:
                cardText = TextforCards.getCards()[25];
                money = 20;
                break;
            case 27:
                cardText = TextforCards.getCards()[26];
                money = 1;
                break;
            case 28:
                cardText = TextforCards.getCards()[27];
                money = 50;
                break;
            case 29:
                cardText = TextforCards.getCards()[28];
                money = 108;
                break;
            case 30:
                cardText = TextforCards.getCards()[29];
                money = 100;
                break;
            case 31:
                cardText = TextforCards.getCards()[30];
                money = 50;
                break;
            case 32:
                cardText = TextforCards.getCards()[31];
                //money = 50;
                break;
        }
    }

    public int extraFields() {
        return move;
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

    public boolean isHasMatadorlegat() {
        return Matadorlegat;
    }

    public void setHasExtraMoves(boolean hasExtraMoves) {
        this.hasExtraMoves = hasExtraMoves;
    }

    public boolean isFreeField() {
        return freeField;
    }

    public int move() {
        if (hasExtraMoves)
            return move;
        else
            return 0;
    }
}