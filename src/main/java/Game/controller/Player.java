package Game.controller;

import Game.Model.Account;

public class Player {

    private boolean prisonCard = false;
    private int position = 0;
    private boolean inPrison = false;
    private int oldPosition;
    public int TotalFields = 40;


    // Determines variables
    Account account = new Account();
    private String Player;

    // Gets the player's name
    public Player(String s) {
        Player = s;
    }

    public String getPlayerString() {
        return Player;
    }

    //Keeps track of the currentplayers score
    public int getBalance(){return account.getScore();}
    //Updates the currentplayers score
    public void playerBalanceUpdate(int update){account.updateScore(update);}

//    public void starterScore(){account.initializeScore();}

    // put player in prison
    public void setInPrison(){
            inPrison = true;
            position = 6;
    }
    // is player in prison?
    public boolean isInPrison(){
        return inPrison;
    }

    // release player from prison & checks if player has prisonCard and uses it if true
    public void releaseFromPrison(boolean inPrison){
        if(inPrison && prisonCard){
            this.inPrison = false;
            prisonCard = false;
        }
        else if(inPrison) {
            account.updateScore(-1);
            this.inPrison = false;
        }
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
        oldPosition = getCurrentPosition();
        position += die;

        if(position > TotalFields - 1){
            position = position - TotalFields;
            account.updateScore(200);
        }
        return position;
    }

}