package Game.controller;

import Game.Model.Dices;
import Game.Model.Player;
import Game.View.MatadorGui;

public class PrisonConditions {
    private String stringChoice;
    public void Release (Player PrisonPlayer, Dices PrisonDices, MatadorGui PrisonGUI, int CurrentPlayer){
        PrisonDices.roll();
        if (PrisonPlayer.getPrisonCard() && PrisonPlayer.getBalance() >= 50 && PrisonPlayer.getRoundsinprison() < 3){
            stringChoice = PrisonGUI.gui.getUserButtonPressed(PrisonPlayer.playerString()+", du har siddet i fængsel i "+ PrisonPlayer.getRoundsinprison() +" runde(r) vælg hvordan du vil løslades: ", "Brug dit benådelses kort","Betal kr. 50,00", "Slå med terningerne");
            switch (stringChoice){
                case "Brug dit benådelses kort":
                    PrisonPlayer.releaseFromPrison();
                    PrisonPlayer.updatePrisonCard(false);
                    break;
                case "Betal kr. 50,00":
                    PrisonPlayer.releaseFromPrison();
                    PrisonPlayer.playerBalanceUpdate(-50);
                    PrisonGUI.updateGuiBalance(CurrentPlayer, PrisonPlayer.getBalance());
                    break;
                case "Slå med terningerne":
                    PrisonGUI.ShowDie(PrisonDices.Die1(), PrisonDices.Die2());
                    if (PrisonDices.Die1() == PrisonDices.Die2()){
                        PrisonPlayer.releaseFromPrison();
                        PrisonGUI.showMessage("Du slog to ens");
                    }
                    else{
                        PrisonGUI.showMessage("Du slog ikke to ens");
                    }
            }
        }
        else if (PrisonPlayer.getPrisonCard() && PrisonPlayer.getRoundsinprison() < 3){
                stringChoice = PrisonGUI.gui.getUserButtonPressed(PrisonPlayer.playerString()+", du har siddet i fængsel i "+ PrisonPlayer.getRoundsinprison() +" runde(r) vælg hvordan du vil løslades: ", "Brug dit benådelses kort", "Slå med terningerne");
                switch (stringChoice){
                    case "Brug dit benådelses kort":
                        PrisonPlayer.releaseFromPrison();
                        PrisonPlayer.updatePrisonCard(false);
                        break;
                    case "Slå med terningerne":
                        PrisonGUI.ShowDie(PrisonDices.Die1(), PrisonDices.Die2());
                        if (PrisonDices.Die1() == PrisonDices.Die2()){
                            PrisonPlayer.releaseFromPrison();
                            PrisonGUI.showMessage("Du slog to ens");
                        }
                        else{
                            PrisonGUI.showMessage("Du slog ikke to ens");
                        }
                }
        }
        else if (PrisonPlayer.getBalance() >= 50 && PrisonPlayer.getRoundsinprison() < 3){
            stringChoice = PrisonGUI.gui.getUserButtonPressed(PrisonPlayer.playerString()+", du har siddet i fængsel i "+ PrisonPlayer.getRoundsinprison() +" runde(r) vælg hvordan du vil løslades: ", "Betal kr. 50,00", "Slå med terningerne");
            switch (stringChoice){
                case "Betal kr. 50,00":
                    PrisonPlayer.releaseFromPrison();
                    PrisonPlayer.playerBalanceUpdate(-50);
                    PrisonGUI.updateGuiBalance(CurrentPlayer, PrisonPlayer.getBalance());
                    break;
                case "Slå med terningerne":
                    PrisonGUI.ShowDie(PrisonDices.Die1(), PrisonDices.Die2());
                    if (PrisonDices.Die1() == PrisonDices.Die2()){
                        PrisonPlayer.releaseFromPrison();
                        PrisonGUI.showMessage("Du slog to ens");
                    }
                    else{
                        PrisonGUI.showMessage("Du slog ikke to ens");
                    }
            }
        }
        else if (PrisonPlayer.getRoundsinprison() < 3) {
            stringChoice = PrisonGUI.gui.getUserButtonPressed(PrisonPlayer.playerString()+", du har siddet i fængsel i "+ PrisonPlayer.getRoundsinprison() +" runde(r), slå to ens for at komme ud af fængslet: ", "Slå med terningerne");
            switch (stringChoice){
                case "Slå med terningerne":
                    PrisonGUI.ShowDie(PrisonDices.Die1(), PrisonDices.Die2());
                    if (PrisonDices.Die1() == PrisonDices.Die2()){
                        PrisonPlayer.releaseFromPrison();
                        PrisonGUI.showMessage("Du slog to ens");
                    }
                    else{
                        PrisonGUI.showMessage("Du slog ikke to ens");
                    }
            }
        }
        else{
            PrisonGUI.showMessage(PrisonPlayer.playerString() + " har siddet i fængsel i 3 runder og løslades nu for kr. 50,00");
            PrisonPlayer.playerBalanceUpdate(-50);
            PrisonGUI.updateGuiBalance(CurrentPlayer, PrisonPlayer.getBalance());
            PrisonPlayer.releaseFromPrison();
        }
    }
}