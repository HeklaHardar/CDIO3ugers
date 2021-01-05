package Model;

import java.lang.Math;

public class Die {
    // Defines variables
    private int max;
    private int min = 1;
    private int Value;

    // Determines the maximum value of the die and rolls it
    public Die(int maximum) {
        max = maximum + 1;
        roll();
    }

    public int getValue() {
        return Value;
    }

    public int roll() {
        Value = (int) (Math.random() * (max - min) + min);
        return Value;
    }
}