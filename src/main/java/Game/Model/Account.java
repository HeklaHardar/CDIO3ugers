package Game.Model;

public class Account {

    private int points;
    private int totalWorth;

    public Account() {points = 1500;}

    // Updates player score
    public void updateScore(int sum) {points += sum;}

    // Returns new score
    public int getScore() {return points;}

    public int AccountWorth() {
        return totalWorth;
    }

    public void updateWorth(int newWorth){
        totalWorth += newWorth;

    }
}