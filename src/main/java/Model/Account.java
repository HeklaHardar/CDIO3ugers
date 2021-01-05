package Model;

public class Account {

    private int points;

    public Account() {}

    // Updates player score
    public void updateScore(int sum) {points += sum;}

    // Returns new score
    public int score() {return points;}

    public void initializeScore(int players){

        points = 24 - 2 * players;

    }
}