package Game.controller;

import Game.Model.Fields.*;
import Game.Model.Player;

public class FieldController {
    // You have to set position or none of the FieldController Methods will work
    private Field[] fields = {new Start(0), new BuildableField(1), new ChanceCard(2), new BuildableField(3), new IncomeTax(4), new ShippingLine(5)
            , new BuildableField(6), new ChanceCard(7), new BuildableField(8), new BuildableField(9), new Prison(10), new BuildableField(11), new Brewery(12),
            new BuildableField(13), new BuildableField(14), new ShippingLine(15), new BuildableField(16), new ChanceCard(17), new BuildableField(18), new BuildableField(19)
            , new SafeZone(20), new BuildableField(21), new ChanceCard(22), new BuildableField(23), new BuildableField(24), new ShippingLine(25), new BuildableField(26)
            , new BuildableField(27), new Brewery(28), new BuildableField(29), new GoToPrison(30), new BuildableField(31), new BuildableField(32), new ChanceCard(33), new BuildableField(34)
            , new ShippingLine(35), new ChanceCard(36), new BuildableField(37), new ExtraordinaryTax(38), new BuildableField(39)};

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

    public String buildHouses(Player player, int playerNumber, int buildposition) {

        if (fields[buildposition] instanceof BuildableField && ((BuildableField) fields[buildposition]).getHouses() < 5 && hasAllFields(buildposition) && ((BuildableField) fields[buildposition]).getOwner() == playerNumber) {
            if (player.getBalance() < ((BuildableField) fields[buildposition]).getHouseCost()) {
                return "noMoney";
            }
            int colorCount = 0;
            int sameHouses = 0;
            for (Field field : fields) {
                if (field instanceof BuildableField) {
                    if (((BuildableField) fields[buildposition]).getColor() == ((BuildableField) field).getColor() && ((BuildableField) fields[buildposition]).getOwner() == ((BuildableField) field).getOwner()) {
                        colorCount++;
                        if (((BuildableField) fields[buildposition]).getHouses() <= ((BuildableField) field).getHouses()) {
                            sameHouses++;
                        }
                    }
                }
            }
            if (colorCount == sameHouses) {
                player.playerBalanceUpdate(-((BuildableField) fields[buildposition]).getHouseCost());
                player.playerWorthUpdate(((BuildableField) fields[buildposition]).getHouseCost());
                ((BuildableField) fields[buildposition]).buildHouse();
                ((BuildableField) fields[buildposition]).setRent(((BuildableField) fields[buildposition]).getRentPrices()[buildposition][((BuildableField) fields[buildposition]).getHouses()]);
                return "buildable";
            } else {
                return "houseRequirements";
            }
        }
        return "notbuildable";
    }

    public String RemoveHouses(Player player, int playerNumber, int buildposition) {

        if (fields[buildposition] instanceof BuildableField && ((BuildableField) fields[buildposition]).getHouses() <= 5 && ((BuildableField) fields[buildposition]).getHouses() > 0 && hasAllFields(buildposition) && ((BuildableField) fields[buildposition]).getOwner() == playerNumber) {
            int colorCount = 0;
            int sameHouses = 0;
            for (Field field : fields) {
                if (field instanceof BuildableField) {
                    if (((BuildableField) fields[buildposition]).getColor() == ((BuildableField) field).getColor() && ((BuildableField) fields[buildposition]).getOwner() == ((BuildableField) field).getOwner()) {
                        colorCount++;
                        if (((BuildableField) fields[buildposition]).getHouses() >= ((BuildableField) field).getHouses()) {
                            sameHouses++;
                        }
                    }
                }
            }
            if (colorCount == sameHouses) {
                player.playerBalanceUpdate(((BuildableField) fields[buildposition]).getHouseCost()/2);
                player.playerWorthUpdate(-((BuildableField) fields[buildposition]).getHouseCost());
                ((BuildableField) fields[buildposition]).removeHouse();
                ((BuildableField) fields[buildposition]).setRent(((BuildableField) fields[buildposition]).getRentPrices()[buildposition][((BuildableField) fields[buildposition]).getHouses()]);
                return "buildable";
            } else {
                return "houseRequirements";
            }
        }
        return "notbuilable";
    }

    public void destroyHouses(int buildPosition){

        ((BuildableField) fields[buildPosition]).destroyHouses();
        ((BuildableField) fields[buildPosition]).setRent(((BuildableField) fields[buildPosition]).getRentPrices()[buildPosition][((BuildableField) fields[buildPosition]).getHouses()]);



    }

    public Field[] getFields() {
        return fields;
    }

    public void countShips(boolean isChanceCard15or25) {
        int[] multirent = {0, 25, 50, 100, 200};

        if (fields[position] instanceof OwnableField) {
            int colorCount = 0;
            for (int i = 0; i <= 39; i++) {

                if (fields[i] instanceof OwnableField) {
                    if (((OwnableField) fields[i]).getColor() == "Ship" && ((OwnableField) fields[i]).getOwner() == ((OwnableField) fields[position]).getOwner() && ((OwnableField) fields[i]).getOwner() != 0) {
                        colorCount = colorCount + 1;
                    }
                }
            }
            if (!isChanceCard15or25) {
                ((OwnableField) fields[position]).setRent(multirent[colorCount]);
            } else if (isChanceCard15or25) {
                ((OwnableField) fields[position]).setRent(multirent[colorCount] * 2);
            }
        }
    }

    public void countBrewery(int BreweryDices) {

        if (fields[position] instanceof OwnableField) {
            int breweryCount = 0;
            for (int i = 0; i <= 39; i++) {

                if (fields[i] instanceof OwnableField) {

                    if (((OwnableField) fields[i]).getColor() == "Brewery" && ((OwnableField) fields[i]).getOwner() == ((OwnableField) fields[position]).getOwner() && ((OwnableField) fields[position]).getOwner() != 0) {
                        breweryCount = breweryCount + 1;
                    }
                }
            }
            if (breweryCount == 1) {
                ((OwnableField) fields[position]).setRent(BreweryDices * 4);
            } else if (breweryCount == 2) {
                ((OwnableField) fields[position]).setRent(BreweryDices * 10);
            }
        }
    }

    public boolean hasAllFields(int testPosition) {
        if (fields[testPosition] instanceof OwnableField) {
            int colorCount = 0;
            for (int i = 0; i <= 39; i++) {

                if (fields[i] instanceof OwnableField) {

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

    public int calculateRent(int rentDices, boolean isChanceCard15or25) {

        if (fields[position] instanceof OwnableField) {
            if (fields[position] instanceof ShippingLine) {
                countShips(isChanceCard15or25);
            } else if (fields[position] instanceof Brewery) {
                countBrewery(rentDices);
            }
            if (fields[position] instanceof BuildableField && ((BuildableField) fields[position]).getHouses() == 0 && hasAllFields(position)) {
                return ((BuildableField) fields[position]).getRentPrices()[position][0] * 2;
            }
            return ((OwnableField) fields[position]).getRent();
        }
        return 0;
    }

    public int getValue() {
        if (fields[position] instanceof OwnableField) {
            return ((OwnableField) fields[position]).getValue();
        } else if (fields[position] instanceof IncomeTax) {
            return ((IncomeTax) fields[position]).getTax();
        } else if (fields[position] instanceof ExtraordinaryTax) {
            return ((ExtraordinaryTax) fields[position]).getTax();
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
            if (((OwnableField) fields[position]).getOwner() == 0) {
                ((OwnableField) fields[position]).setOwner(playerNumber);
            } else if (playerNumber > 9) {
                ((OwnableField) fields[position]).setOwner(playerNumber);
            } else if (((OwnableField) fields[position]).getOwner() == 0) {
                ((OwnableField) fields[position]).setOwner(playerNumber);
            } else if (((OwnableField) fields[position]).getOwner() > 9) {
                ((OwnableField) fields[position]).setOwner(playerNumber);
            }
        }
    }

    public void tradeOwnedField(int playerNumber){

        ((OwnableField) fields[position]).setOwner(playerNumber);

    }
}