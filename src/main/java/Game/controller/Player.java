package Game.controller;

import Game.Model.Account;

public class Player {

    private boolean prisonCard = false;
    private int position = 0;
    private boolean inPrison = false;
    private int oldPosition;


    // Determines variables
    Account account = new Account();
    private String Player;

    // Gets the player's name
    public Player(String s) {
        Player = s;
    }

    public String playerString() {
        return Player;
    }

    //Keeps track of the currentplayers score
    public int playerBalance(){return account.score();}
    //Updates the currentplayers score
    public void playerBalanceUpdate(int update){account.updateScore(update);}

    public void starterScore(int players){account.initializeScore(players);}

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
    public int currentPosition(){
        return position;
    }

    //Easy way to move the player without having to count the die value
    public int setPosition(int newPosition){
        if(currentPosition() > newPosition) {
            account.updateScore(2);
        }
        position = newPosition;
        return position;
    }
    // Move player with die
    public int updatePosition(int die){
        oldPosition = currentPosition();
        position += die;
        if(die == 900) {
            position = 0;
            account.updateScore(2);
        }
        else if(die == 800){
            position = 23;
        }
        else if(die == 700){
            if (oldPosition > 10)
                account.updateScore(2);
            position = 10;
        }
        else if(position > 23){
            position = position - 24;
            account.updateScore(2);
        }
        return position;
    }

}