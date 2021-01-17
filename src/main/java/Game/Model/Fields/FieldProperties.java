package Game.Model.Fields;

public class FieldProperties {
    private final String[] FIELDCOLORS = {"", "Blue", "", "Blue", "", "Ship"
            , "Orange", "", "Orange", "Orange", "", "Yellow", "Brewery",
            "Yellow", "Yellow", "Ship", "Grey", "", "Grey", "Grey"
            , "", "Red", "", "Red", "Red", "Ship", "Sand"
            , "Sand", "Brewery", "Sand", "", "Yellow", "Yellow", "", "Yellow"
            , "Ship", "", "Purple", "", "Purple"};

    private final int[] FIELDVALUES = {0, 60, 0, 60, 200, 200
            , 100, 0, 100, 120, 0, 140, 150,
            140, 160, 200, 180, 0, 180, 200
            , 0, 220, 0, 220, 240, 200, 260
            , 260, 150, 280, 0, 300, 300, 0, 320
            , 200, 0, 350, 200, 400};
    private final int[] HOUSECOSTS = {0, 50, 0, 50, 0, 0
            , 50, 0, 50, 50, 0, 100, 0,
            100, 100, 0, 100, 0, 100, 100
            , 0, 150, 0, 150, 150, 0, 150
            , 150, 0, 150, 0, 200, 200, 0, 200
            , 0, 0, 200, 0, 200};
    private final int[] MORTGAGEVALUES = {0, 30, 0, 30, 100, 100
            , 50, 0, 50, 60, 0, 70, 75,
            70, 80, 100, 90, 0, 90, 100
            , 0, 110, 0, 110, 120, 100, 130
            , 130, 75, 140, 0, 150, 150, 0, 160
            , 100, 0, 175, 100, 200};

    private final int[][] RENTPRICES = {{}, {2, 10, 30, 90, 160, 250}, {}, {4, 20, 60, 180, 320, 540}, {}, {25}
            , {6, 30, 90, 270, 400, 550}, {}, {6, 30, 90, 270, 400, 550}, {8, 40, 100, 300, 450, 600}, {}, {10, 50, 150, 450, 625, 750}, {15},
            {10, 50, 150, 450, 625, 750}, {12, 60, 180, 500, 700, 900}, {25}, {14, 70, 200, 550, 750, 950}, {}, {14, 70, 200, 550, 750, 950}, {16, 80, 220, 600, 800, 1000}
            , {}, {18, 90, 250, 700, 875, 1050}, {}, {18, 90, 250, 700, 875, 1050}, {20, 100, 300, 750, 925, 1100}, {25}, {22, 110, 330, 800, 1150}
            , {22, 110, 330, 800, 975, 1150}, {15}, {22, 120, 360, 850, 1025, 1200}, {}, {26, 130, 390, 900, 1100, 1275}, {26, 130, 390, 900, 1100, 1275}, {}, {28, 150, 450, 1000, 1200, 1400}
            , {25}, {}, {35, 175, 500, 1100, 1300, 1500}, {}, {50, 200, 600, 1400, 1700, 2000}};

    private int[] Houses = new int[40];

    private final String[] FIELDTITLES = {"Start", "Rødovrevej", "", "Hvidovre", "", "Øresund A/S", "Roskildevej", "", "Valby  Langgade", "Allégade", "", "Frederiksberg  Allé", "Tuborg",
            "Bülowsvej", "Gl. Kongevej", "D. F. D. S.", "Bernstoffsvej", "", "Hellerupvej", "Strandvej", "Helle", "Trianglen", "", "Østerbro-gade ", "Grønningen", "Y. K.",
            "Bredgade", "Kg. Nytorv", "Carlsberg", "Østergade", "", "Amagertorv", "Vimmelskaftet", "", "Nygade", "D/S Bornholm 1866 ", "", "Frederiks-berggade ", "", "Rådhus-pladsen "};

    private int[] ownedFields = new int[40];

    public String[] getFIELDCOLORS() {
        return FIELDCOLORS;
    }

    public int[][] getRENTPRICES() {
        return RENTPRICES;
    }

    public int[] getFIELDVALUES() {
        return FIELDVALUES;
    }

    public void setHouses(int position) {
        Houses[position] = Houses[position] + 1;
    }

    public void removeHouses(int position) {
        Houses[position] = Houses[position] - 1;
    }
    public void destroyHouses(int position){
        Houses[position] = 0;
    }

    public int[] getHOUSECOSTS() {
        return HOUSECOSTS;
    }

    public int[] getHouses() {
        return Houses;
    }

    public String[] getFieldTitles() {
        return FIELDTITLES;
    }

    public int getOwner(int position) {
        return ownedFields[position];
    }

    public void setOwner(int position, int ownerNumber) {
        ownedFields[position] = ownerNumber;
    }

    public int[] getMORTGAGEVALUES() {
        return MORTGAGEVALUES;
    }
}
