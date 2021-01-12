package Game.Model;

public class Account {

    private int points;

    public Account() {points = 1500;}

    // Updates player score
    public void updateScore(int sum) {points += sum;}

    // Returns new score
    public int getScore() {return points;}

}