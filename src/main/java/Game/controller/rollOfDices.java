package Game.controller;

import Game.View.MatadorGui;

public class rollOfDices {
    private int dice1;
    private int dice2;
    private int counter = 0;
    private int position;
    public rollOfDices() {
    }
    public boolean twoOfTheSame(int dice1, int dice2){
        if(dice1 == dice2){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean twoOfTheSameThreeTimes(int dice1, int dice2, int playernumber, Player player, MatadorGui gui){
        if(twoOfTheSame(dice1,dice2)){
            counter++;
        }
        else{
            counter=0;
        }
        if(counter==3){
            gui.moveCars(playernumber,player.getCurrentPosition(),player.setPosition(30));
            counter=0;
            return true;
        }
        else{
            return false;
        }
    }
}
