package Game.controller;

import Game.Model.Fields.*;

public class FieldController {
    // You have to set position or none of the FieldController Methods will work
    private Field[] fields = {new Start(0), new BuildableField(1), new ChanceCard(2), new BuildableField(3), new IncomeTax(4), new ShippingLine(5)
            , new BuildableField(6), new ChanceCard(7), new BuildableField(8), new BuildableField(9), new Prison(10), new BuildableField(11), new Brewery(12),
            new BuildableField(13), new BuildableField(14), new ShippingLine(15), new BuildableField(16), new ChanceCard(17), new BuildableField(18), new BuildableField(19)
            , new SafeZone(20), new BuildableField(21), new ChanceCard(22), new BuildableField(23), new BuildableField(24), new ShippingLine(25), new BuildableField(26)
            , new BuildableField(27), new Brewery(28), new BuildableField(29), new GoToPrison(30), new BuildableField(31), new BuildableField(32), new ChanceCard(33), new BuildableField(34)
            , new ShippingLine(35), new ChanceCard(36), new BuildableField(37), new IncomeTax(38), new BuildableField(39)};

    private boolean inPrison = false;
    private boolean IncomeTax = false;
    private boolean ExtraordinaryTax = false;
    private int position = 1;

    public int isOwnable() {
        return fields[position].isOwnable();
    }

    public void resetPrisonStatus() {
        this.inPrison = false;
    }

    public Field getCurrentField(int currentField) {
        return fields[currentField];
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int[] getAvaiableHousePositions() {
        int[] avaiableHousePositions = new int[22];
        int arrayPosition = 0;
        int oldPosition = position;
        for (int i = 0; i < 40; i++) {
            this.position = i;
            if (hasAllFields(position)) {
                avaiableHousePositions[arrayPosition] = i;
                arrayPosition++;
            }
        }
        this.position = oldPosition;
        return avaiableHousePositions;
    }

    public void buildHouses(Player player, int playerNumber, int buildposition) {
        if (fields[buildposition] instanceof BuildableField && ((BuildableField) fields[buildposition]).getHouses() < 5 && hasAllFields(buildposition) && ((BuildableField) fields[buildposition]).getOwner() == playerNumber) {
            player.playerBalanceUpdate(-((BuildableField) fields[buildposition]).getHouseCost());
            ((BuildableField) fields[buildposition]).buildHouse();
            ((BuildableField) fields[buildposition]).setRent(((BuildableField) fields[buildposition]).getRentPrices()[buildposition][((BuildableField) fields[buildposition]).getHouses()]);
        }
    }


    public Field[] getFields() {
        return fields;
    }

    public void countShips() {
        int[] multirent = {0, 25, 50, 100, 200};

        if (fields[position] instanceof OwnableField) {
            for (int i = 0; i <= 39; i++) {
                int colorCount = 0;
                    if (fields[i] instanceof OwnableField) {
                        if (((OwnableField) fields[i]).getColor() == "Ship" && ((OwnableField) fields[i]).getOwner() == ((OwnableField) fields[position]).getOwner() && ((OwnableField) fields[i]).getOwner() != 0) {
                            colorCount = colorCount + 1;

                        }
                    }

                    ((OwnableField) fields[position]).setRent(multirent[colorCount]);
            }
        }
    }

    public void countBrewery(int BreweryDices) {

        if (fields[position] instanceof OwnableField) {
            for (int i = 0; i <= 39; i++) {
                int breweryCount = 0;

                if (fields[i] instanceof OwnableField) {

                        if (((OwnableField) fields[i]).getColor() == "Brewery" && ((OwnableField) fields[i]).getOwner() == ((OwnableField) fields[position]).getOwner() && ((OwnableField) fields[position]).getOwner() != 0) {
                            breweryCount = breweryCount + 1;
                        }
                }
                if (breweryCount == 1) {
                    ((OwnableField) fields[position]).setRent(BreweryDices * 4);
                }
                if (breweryCount == 2) {
                    ((OwnableField) fields[position]).setRent(BreweryDices * 10);
                }
            }
        }
    }

    public boolean hasAllFields(int testPosition) {
        if (fields[testPosition] instanceof OwnableField) {
            for (int i = 0; i <= 39; i++) {
                int colorCount = 0;
                if (fields[i] instanceof OwnableField) {

                    //Hvis du ejer 2 af blå eller lilla
                    if ((i != testPosition) && ((OwnableField) fields[i]).getColor() == ((OwnableField) fields[testPosition]).getColor()
                            && ((OwnableField) fields[i]).getOwner() == ((OwnableField) fields[testPosition]).getOwner() && ((OwnableField) fields[i]).getOwner() != 0) {
                        if ((((OwnableField) fields[testPosition]).getColor() == "Purple" || ((OwnableField) fields[testPosition]).getColor() == "Blue")) {
                            return true;
                        } else {
                                if (((OwnableField) fields[i]).getColor() == ((OwnableField) fields[testPosition]).getColor() && ((OwnableField) fields[i]).getOwner() == ((OwnableField) fields[testPosition]).getOwner() && ((OwnableField) fields[testPosition]).getOwner() != 0 && i != testPosition) {
                                    colorCount = colorCount + 1;
                                }
                        }
                    }
                }
                if (colorCount == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public int calculateRent() {

        if (fields[position] instanceof OwnableField) {
            if(fields[position] instanceof BuildableField && ((BuildableField) fields[position]).getHouses()==0 && hasAllFields(position)){
                return ((BuildableField) fields[position]).getRentPrices()[position][0]*2;
            }
            return ((OwnableField) fields[position]).getRent();
        }
        return 0;
    }

    public int getValue() {
        if (fields[position] instanceof ExtraordinaryTax || fields[position] instanceof OwnableField) {
            return fields[position].getValue();
        }
        return 0;
    }

    public boolean isInPrison() {
        if (fields[position] instanceof GoToPrison) {
            return ((GoToPrison) fields[position]).isGoToPrison();
        } else {
            return false;
        }
    }

    public int[] getmortageValues() {
        return mortgageValues;
    }

    public boolean isExtraordinaryTax() {
        if (fields[position] instanceof ExtraordinaryTax) {
            return ExtraordinaryTax = true;
        } else {
            return false;
        }
    }

    public boolean isIncomeTax() {
        if (fields[position] instanceof IncomeTax) {
            return IncomeTax = true;
        } else {
            return false;
        }
    }


    public boolean getdrawCard() {
        boolean drawcard = false;
        if (fields[position] instanceof ChanceCard) {
            drawcard = true;
        }
        return drawcard;
    }

    public void setOwnedFields(int playerNumber) {
        if (fields[position] instanceof OwnableField) {
            if (((OwnableField) fields[position]).getOwner()==0) {
                ((OwnableField) fields[position]).setOwner(playerNumber);
            }
        }
    }
}