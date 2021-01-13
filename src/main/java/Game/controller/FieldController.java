package Game.controller;

import Game.Model.Fields.*;

public class FieldController {
    // You have to set position or none of the FieldController Methods will work
    private Field[] fields = {new Start(), new BuildableField(), new ChanceCard(), new BuildableField(), new IncomeTax(), new ShippingLine()
            , new BuildableField(), new ChanceCard(), new BuildableField(), new BuildableField(), new Prison(), new BuildableField(), new Brewery(),
            new BuildableField(), new BuildableField(), new ShippingLine(), new BuildableField(), new ChanceCard(), new BuildableField(), new BuildableField()
            , new SafeZone(), new BuildableField(), new ChanceCard(), new BuildableField(), new BuildableField(), new ShippingLine(), new BuildableField()
            , new BuildableField(), new Brewery(), new BuildableField(), new GoToPrison(), new BuildableField(), new BuildableField(), new ChanceCard(), new BuildableField()
            , new ShippingLine(), new ChanceCard(), new BuildableField(), new IncomeTax(), new BuildableField()};

    private String[] fieldColors = {"", "Blue", "", "Blue", "", "Ship"
            , "Orange", "", "Orange", "Orange", "", "Yellow", "Brewery",
            "Yellow", "Yellow", "Ship", "Grey", "", "Grey", "Grey"
            , "", "Red", "", "Red", "Red", "Ship", "Sand"
            , "Sand", "Brewery", "Sand", "", "Yellow", "Yellow", "", "Yellow"
            , "Ship", "", "Purple", "", "Purple"};

    private int[] fieldValues = {0, 60, 0, 60, 200, 200
            , 100, 0, 100, 120, 0, 140, 150,
            140, 160, 200, 180, 0, 180, 200
            , 0, 220, 0, 220, 240, 200, 260
            , 260, 150, 280, 0, 300, 300, 0, 320
            , 200, 0, 350, 200, 400};
    private int[] houseCosts = {0, 50, 0, 50, 0, 0
            , 50, 0, 50, 50, 0, 100, 0,
            100, 100, 0, 100, 0, 100, 100
            , 0, 150, 0, 150, 150, 0, 150
            , 150, 0, 150, 0, 200, 200, 0, 200
            , 0, 0, 200, 0, 200};

    private int[][] housePrices = {{}, {10, 30, 90, 160, 250}, {}, {20, 60, 180, 320, 540}, {}, {200, 200, 200, 200, 200}
            , {30, 90, 270, 400, 550}, {}, {30, 90, 270, 400, 550}, {40, 100, 300, 450, 600}, {}, {50, 150, 450, 625, 750}, {150, 150, 150, 150, 150},
            {50, 150, 450, 625, 750}, {60, 180, 500, 700, 900}, {200, 200, 200, 200, 200}, {70, 200, 550, 750, 950}, {}, {70, 200, 550, 750, 950}, {80, 220, 600, 800, 1000}
            , {}, {90, 250, 700, 875, 1050}, {}, {90, 250, 700, 875, 1050}, {100, 300, 750, 925, 1100}, {200, 200, 200, 200, 200}, {110, 330, 800, 1150}
            , {110, 330, 800, 975, 1150}, {}, {120, 360, 850, 1025, 1200}, {}, {130, 390, 900, 1100, 1275}, {130, 390, 900, 1100, 1275}, {}, {150, 450, 1000, 1200, 1400}
            , {200, 200, 200, 200, 200}, {}, {175, 500, 1100, 1300, 1500}, {}, {200, 600, 1400, 1700, 2000}};

    private int[] Houses = new int[40];

    private String[] fieldTitles = {"Start", "Rødovrevej", "", "Hvidovre", "", "Øresund A/S", "Roskildevej", "", "Valby  Langgade", "Allégade", "", "Frederiksberg  Allé", "Tuborg",
            "Bülowsvej", "Gl. Kongevej", "D. F. D. S.", "Bernstoffsvej", "", "Hellerupvej", "Strandvej", "Helle", "Trianglen", "", "Østerbro-gade ", "Grønningen", "Y. K.",
            "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "", "Amagertorv", "Vimmelskaftet", "", "Nygade", "D/S Bornholm 1866 ", "", "Frederiks-berggade ", "", "Rådhus-pladsen "};

    private int[] ownedFields = new int[40];

    private int[] rent = {0, 2, 0, 4, 0, 25
            , 6, 0, 6, 8, 0, 10, 15, 10, 12, 25, 14, 0, 14, 16
            , 0, 18, 0, 18, 20, 25, 22
            , 22, 15, 22, 0, 26, 26, 0, 28
            , 25, 0, 35, 0, 50};
    private boolean inPrison = false;
    private boolean IncomeTax = false;
    private int position = 1;

    public Field[] createFields() {
        int fieldcount = 0;
        for (Field field : fields) {

            if (field instanceof OwnableField) {
                field.setName(fieldTitles[fieldcount]);
                ((OwnableField) field).setColor(fieldColors[fieldcount]);
                ((OwnableField) field).setRent(rent[fieldcount]);
                field.setValue(fieldValues[fieldcount]);
            } else if (field instanceof ShippingLine) {
                field.setName(fieldTitles[fieldcount]);
            } else if (field instanceof Brewery) {
                field.setName(fieldTitles[fieldcount]);
            }

            fieldcount = fieldcount + 1;
        }
        return fields;
    }

    public int isOwnable() {
        return fields[position].isOwnable();
    }

    public void resetPrisonStatus() {
        this.inPrison = false;
    }

    public Field fields(int currentField) {
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
        if (fields[buildposition] instanceof BuildableField && Houses[buildposition] < 5 && hasAllFields(buildposition) && ownedFields[buildposition] == playerNumber) {
            player.playerBalanceUpdate(-houseCosts[buildposition]);
            player.playerWorthUpdate(houseCosts[buildposition]);
            Houses[buildposition] = Houses[buildposition] + 1;
        } else {
            return;
        }
    }

    public int[] getHouses() {
        return Houses;
    }

    public Field[] getFields() {
        return fields;
    }

    public int countShips() {
        int[] multirent = {0, 25, 50, 100, 200};

        if (fields[position] instanceof OwnableField) {
            for (int i = 0; i <= 39; i++) {

                if (fields[i] instanceof OwnableField) {
                    int colorCount = 0;

                    int k = 0;
                    for (String color : fieldColors) {
                        if (color == "Ship" && ownedFields[k] == ownedFields[position] && ownedFields[k] != 0) {
                            colorCount = colorCount + 1;

                        }
                        k++;
                    }

                    return multirent[colorCount];
                }
            }
        }
        return 0;
    }

    public int countBrewery(int BreweryDices) {

        if (fields[position] instanceof OwnableField) {
            for (int i = 0; i <= 39; i++) {

                if (fields[i] instanceof OwnableField) {
                    int breweryCount = 0;


                    int k = 0;
                    for (String color : fieldColors) {
                        if (color == "Brewery" && ownedFields[k] == ownedFields[position] && ownedFields[k] != 0) {
                            breweryCount = breweryCount + 1;

                        }
                        k++;
                    }
                    if (breweryCount == 1) {
                        return BreweryDices * 4;
                    }
                    if (breweryCount == 2) {
                        return BreweryDices * 10;
                    }
                }
            }
        }
        return 0;
    }

    public boolean hasAllFields(int testPosition) {
        if (fields[testPosition] instanceof OwnableField) {
            for (int i = 0; i <= 39; i++) {
                int colorCount = 0;
                if (fields[i] instanceof OwnableField) {

                    //Hvis du ejer 2 af blå eller lilla
                    if ((i != testPosition) && ((OwnableField) fields[i]).getColor() == ((OwnableField) fields[testPosition]).getColor()
                            && ownedFields[i] == ownedFields[testPosition] && ownedFields[i] != 0) {
                        if ((((OwnableField) fields[testPosition]).getColor() == "Purple" || ((OwnableField) fields[testPosition]).getColor() == "Blue")) {
                            return true;
                        } else {
                            int k = 0;
                            for (String color : fieldColors) {
                                if (color == ((OwnableField) fields[testPosition]).getColor() && ownedFields[k] == ownedFields[testPosition] && ownedFields[k] != 0 && k != testPosition) {
                                    colorCount = colorCount + 1;
                                }
                                k++;
                            }
                            if (colorCount == 2) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int calculateRent(int Dices) {

        if (fields[position] instanceof OwnableField) {
            if (((OwnableField) fields[position]).getColor() == "Ship") {

                return countShips();
            } else if (((OwnableField) fields[position]).getColor() == "Brewery") {

                return countBrewery(Dices);
            } else if (hasAllFields(position)) {
                if (Houses[position] > 0) {
                    return housePrices[position][Houses[position] - 1];
                } else {
                    return rent[position] * 2;
                }
            }
            return rent[position];
        }

        return 0;
    }

    public int getValue() {
        if (fields[position] instanceof IncomeTax) {
            return fields[position].getValue();
        }
        if (fields[position] instanceof OwnableField) {
            return fields[position].getValue();
        }
        return 0;
    }

    public boolean isInPrison() {
        if (fields[position] instanceof GoToPrison) {
            return inPrison = true;
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

    public String[] getFieldColors() {
        return fieldColors;
    }

    public int[] getFieldValues() {
        return fieldValues;
    }

    public String[] getFieldTitles() {
        return fieldTitles;
    }

    public int[] getOwnedFields() {
        return ownedFields;
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
            if (ownedFields[position] == 0) {
                ownedFields[position] = playerNumber;
            }
        }
    }
}