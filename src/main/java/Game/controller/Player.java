package Game.controller;

import Game.Model.Account;

public class Player {

    private boolean prisonCard = false;
    private int position = 0;
    private boolean inPrison = false;
    private int oldPosition;
    private int roundsinprison;

    // Determines variables
    Account account = new Account();
    private String Player;

    // Gets the player's name
    public Player(String s) {
        Player = s;
    }

    public boolean getPrisonCard(){return prisonCard;}

    public String playerString() {
        return Player;
    }

    //Keeps track of the currentplayers score
    public int getBalance(){return account.getScore();}
    //Updates the currentplayers score
    public void playerBalanceUpdate(int update){account.updateScore(update);}

    public void playerWorthUpdate (int playerWorthUpdate){
        account.updateWorth(playerWorthUpdate);
    }

    public int getPlayerAccountWorth (){
        return account.AccountWorth();
    }

    // put player in prison
    public void setInPrison(){
            inPrison = true;
            position = 10;
            roundsinprison = 1;
    }
    public void InPrison(){
        roundsinprison += 1;
    }
    public int getRoundsinprison(){
        return roundsinprison;
    }
    // is player in prison?
    public boolean isInPrison(){
        return inPrison;
    }

    // release player from prison & checks if player has prisonCard and uses it if true
    public void releaseFromPrison(){
        inPrison = false;
    }

    //Sets the prisonCard to true, if the player draws the prisonCard
    public void updatePrisonCard(boolean Card){
        prisonCard = Card;
    }

    // Check where player is currently
    public int getCurrentPosition(){
        return position;
    }

    //Easy way to move the player without having to count the die value
    public int setPosition(int newPosition){
        if(getCurrentPosition() > newPosition) {
            account.updateScore(200);
        }
        position = newPosition;
        return position;
    }
    // Move player with die
    public int updatePosition(int die){
        oldPosition = position;
        position += die;

        if(position > 39){
            position = position - 40;
            account.updateScore(200);
        }
        else if (position < 0) {
            position = 39;
        }
        return position;
    }
}