package Game.Model;

public class Dices {

    // Creates two dices
    Die die1 = new Die(6);
    Die die2 = new Die(6);

    // Gets the sum of both dices
    public int getValue() {
        return die1.getValue() + die2.getValue();
    }

    // Rolls both dices
    public Dices roll() {
        die1.roll();
        die2.roll();
        return this;
    }

    @Override
    public String toString() {
        return
                die1.getValue() +
                        ", " + die2.getValue();
    }

    public int Die1(){
        return
                die1.getValue();
    }
    public int Die2(){
        return
                die2.getValue();
    }
}