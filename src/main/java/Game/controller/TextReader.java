package Game.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class TextReader {
    private int[] fieldValues = readFieldValues("Priser.txt");
    System.out.println(Arrays.toString(data));


    public static int[] readFieldValues(String file){
        int i;
        try {

            File f = new File(file);
            Scanner s = new Scanner(f);
            int ctr = 0;
            while (s.hasNextInt()){
                ctr++;
                s.nextInt();
            }
            int[] arr = new int[ctr];

            Scanner s1 = new Scanner(f);

            for(int i = 0; < arr.length;
            i++)
            arr[i] = s1.nextInt();

            return arr;

        }
        catch(Exception e){
            return null;
        }
    }
}
