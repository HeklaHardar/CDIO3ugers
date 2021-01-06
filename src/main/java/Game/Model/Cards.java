package Game.Model;


import java.lang.reflect.Field;

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

    private String[] possibleFields = new String[2];
    private String[] possibleFields2 = new String[4];
    private boolean freeField = false;

    CardPicker cardPicker = new CardPicker();

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
        hasPositionChange= false;

    }

    //Resets the freefield boolean, if a card with that value has been drawn
    public void resetfreeField() {
        freeField = false;
    }


    public void CardPick() {

        //Calls cardPicker.DrawCard for a random int.
        switch (13/*cardPicker.DrawCard()*/) {
            case 1:
                cardText = "Tag ind på \n Rådhuspladsen.";
                PositionChange = 39;
                hasPositionChange= true;
                break;
            case 2:
                cardText = "Ryk frem til Grønningen. \n Hvis De passerer start, indkasserer Da kr. 200,00.";
                PositionChange = 24;
                hasPositionChange= true;
                break;
            case 3:
                cardText = "Tag med Øresundsbåden --- \n Flyt brikken frem, og hvis De.\n Passerer start, indkasser \n kr. 200,00.";
                PositionChange = 5;
                hasPositionChange= true;
                break;
            case 4:
            case 6:
            case 9:
                //Gå i fængsel
                cardText = "Gå i fængsel. Ryk direkte til \n fængsel. Selv om De passerer\n start, indkasserer De ikke \n kr. 200,00.";
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
                hasPositionChange= true;
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
                cardText = "Ryk brikken fremtil  det nærmeste \n dampskibselskab og betal ejeren to\n gange den leje, han eller er beret-\n " +
                        "tiget til. Hvis selskabet ikke ejes af \n nogen, kan De købe det af banken.";
                break;
            case 16:
                cardText = "Du har lavet\n alle dine lektier!\nMODTAG 2 MONOPOLY PENGE\n fra banken";
                money = 2;
                break;
            case 17:
                //Gratis rødt felt
                cardText = "GRATIS FELT! \n ryk frem til et rødt felt.\n Hvis det er ledigt, får du det GRATIS!\n Ellers skal du betale leje til ejeren.";
                possibleFields[0] = "Spillehallen";
                possibleFields[1] = "Biografen";
                freeField = true;
                break;
            case 18:
                cardText = "GRATIS FELT! \n Ryk frem til skaterparken\n for at lave det perfekte grind! \nHvis ingen ejer den \n får du den GRATIS! \n Ellers skal du BETALE  leje til ejeren.";
                hasExtraMoves = true;
                freeField = true;
                move = 700;
                break;
            case 19:
                //gratis lyseblåt eller rødt
                cardText = "GRATIS FELT! \n ryk frem til et lyseblåt eller rødt felt.\n Hvis det er ledigt, får du det GRATIS!\n Ellers skal du betale leje til ejeren.";

                possibleFields2[0] = "Slikbutikken";
                possibleFields2[1] = "Iskiosken";
                possibleFields2[2] = "Spillehallen";
                possibleFields2[3] = "Biografen";
                freeField = true;
                break;
            case 20:
                //gratis lysegrønt eller gult
                cardText = "GRATIS FELT! \n ryk frem til et lysegrønt eller gult felt.\n Hvis det er ledigt, får du det GRATIS!\n Ellers skal du betale leje til ejeren.";

                possibleFields2[1] = "Pizzariaet";
                possibleFields2[0] = "Burgerbaren";
                possibleFields2[2] = "Legetøjsbutikken";
                possibleFields2[3] = "Dyrehandlen";
                freeField = true;
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