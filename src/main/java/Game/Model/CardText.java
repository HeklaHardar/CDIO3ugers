package Game.Model;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CardText {

    private String[] Cards;
    private String filePath;

    public CardText()  {

        try {
            Path path = Paths.get("", "src", "Assets", "TextFiles", "CardText.txt");
            filePath = String.valueOf(path.toAbsolutePath());
        }catch(FileSystemNotFoundException e){
            e.printStackTrace();
        }

        BufferedReader reader;
        int counter = 1;
        String[] cardNames = new String[32];
        try {
            reader = new BufferedReader(new FileReader(String.valueOf(filePath)));
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
