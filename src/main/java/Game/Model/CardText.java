package Game.Model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CardText {

    private String[] Cards;

    public CardText() {
        BufferedReader reader;
        int counter = 1;
        String[] cardNames = new String[32];
        try {
            reader = new BufferedReader(new FileReader("/Users/Lucas/IdeaProjects/CDIO3ugers/src/Assets/TextFiles/CardText.txt"));
            String card  = reader.readLine();
            cardNames[0] = card;
            while (card != null) {
                card = reader.readLine();
                if (card == null)
                    break;
                cardNames[counter] = card;
                counter += 1;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cards = cardNames;
    }
    public String[] getCards(){return Cards;}

}
