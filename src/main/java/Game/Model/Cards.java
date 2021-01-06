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
                cardText = "Ryk frem til Grønningen. \n Hvis De passerer start, indkasserer Da kr. 200,00.";
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
                cardText = "De har måttet vedtage en \n parkeringsbøde.\n Betal kr. 20,00 til banken.";
                money = -20;
                break;
            case 7:
            case 8:
                cardText = "Ryk tre felter tilbage";
                move = -3;
                hasExtraMoves = true;
                //Ryk tre felter tilbage
                break;
            case 10:
                //Ryk frem til start
                cardText = "Ryk frem til start.";
                PositionChange = 0;
                hasPositionChange = true;
                break;
            case 11:
                // HUSE OG HOTELLER SKAL LAVE FØRST
                cardText = "Kul- og kokspriserne er steget, \n og De skal betale: \n kr. 25,00 pr. hus og \n kr. 125,00 pr. hotel.";
                break;
            case 12:
                // HUSE OG HOTELLER SKAL LAVE FØRST
                cardText = "Ejendomsskatterne er steget, \n ekstraudgifterne er: \n kr. 50,00 pr. hus og \n kr. 125,00 pr. hotel.";
                break;
            case 13:
                cardText = "De har lagt penge ud til sammen- \n skudsgilde. Mærkværdigvis \n betaler alle straks. Modtag fra \n hver medspiller kr. 25,00.";
                isSammenskudsgilde = true;
                break;
            case 14:
                cardText = "Værdien af egen avl fra nyttehaven \n udgør kr. 200,00, \n som De modtager af banken.";
                money = 200;
                break;
            case 15:
            case 25:
                cardText = TextforCards.getCards()[24];
                break;
            case 16:
                cardText = "De har anskaffet et nyt dæk til\n Deres vogn.\n Indbetal kr. 100,00.";
                money = -100;
                break;
            case 17:
                cardText = "De har kørt frem for >> Fuld Stop<<\n Betal kr. 100,00 i bøde.";
                money = -100;
                break;
            case 18:
            case 23:
                cardText = "Betal for vognvask og smøring \n kr. 10,00.";
                money = -10;
                break;
            case 19:
                cardText = "De har været en tur i udlandet\n og haft for mange cigaretter med\nhjem. - Betal told kr. 20,00.";
                money = -20;
                break;
            case 20:
                cardText = "De har måttet vedtage en par-\nkeringsbøde.\n Betal kr. 20,00 til banken.";
                money = -20;
                break;
            case 21:
                cardText = "Grundet på dyrtiden har De fået\n gageforhøjelse. \n Modtag kr. 25,00.";
                money = 25;
                break;
            case 22:
            case 24:
                cardText = "I anledning af Kongens fødsels-\n dag benådes De herved for fængsel.\n Dette kort kan opbevares, indtil \n " +
                        "De får brug for det, eller De \n kan sælge det.";
                hasPrisonCard = true;
                break;
            case 26:
                cardText = "De har solgt Deres gamle klude.\n Modtag kr. 20,00.";
                money = 20;
                break;
            case 27:
                cardText = "De har rettidigt afleveret Deres\n abonnementskort. \n Depositum kr. 1,00 \n udbetales Dem af banken.";
                money = 1;
                break;
            case 28:
                cardText = "Manufakturvarerne er blevet bil-\n ligere og bedre, herved sparer De \n kr. 50,00. \n som De modtager af banken.";
                money = 50;
                break;
            case 29:
                cardText = "Efter auktionen på Assistens- \n huset, hvor De havde pantsat Deres tøj, modtager De ekstra \n kr. 108,00.";
                money = 108;
                break;
            case 30:
                cardText = "Deres præmieobligation er kom- \n met ud. De motager kr. 100,00 \n af banken.";
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