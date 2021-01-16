package Game.Model;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReader {

    private String[] Text;
    private int Counter = 0;
    private String filePath;

    //Provide FileName to find the files path **FILE MUST BE IN THE TextFiles FOLDER**
    public TextReader(String FileName) {

        //Finds the filepath from the folders it is held within, and its name
        try {
            Path path = Paths.get("", "src", "Assets", "TextFiles", FileName);
            filePath = String.valueOf(path.toAbsolutePath());
        } catch (FileSystemNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader1;
        BufferedReader reader2;
        int counter = 1;

        try {
            //Reads the file and checks how many lines are present to make an array appropriate for the file length
            reader1 = new BufferedReader(new FileReader((String.valueOf(filePath))));
            String Line = "";
            while (Line != null) {
                Line = reader1.readLine();
                if (Line == null)
                    break;
                Counter += 1;
            }
            reader1.close();
            String[] textLines = new String[Counter];

            //Reads the file and puts each line into it's own place in the array
            reader2 = new BufferedReader(new FileReader(String.valueOf(filePath)));
            String TextLine = reader2.readLine();
            textLines[0] = TextLine;
            while (TextLine != null) {
                TextLine = reader2.readLine();
                if (TextLine == null)
                    break;
                textLines[counter] = TextLine;
                counter += 1;
            }
            Text = textLines;
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getText() {
        return Text;
    }

    public int[] getInts() {

        int size = Text.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(Text[i]);
        }
        return arr;
    }
}