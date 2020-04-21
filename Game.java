import java.util.*;

import project3.RollDice;

public class Game {

    private enum Characters{
        BART_CASSIDY(8), BLACK_JACK(8), CALAMITY_JANET(8), EL_GRINGO(7), JESSE_JONES(9), JOURDONNAIS(7), KIT_CARLSON(7),
        LUCKY_DUKE(8), PAUL_REGRET(9), PEDRO_RAMIREZ(8), ROSE_DOOLAN(9), SID_KETCHUM(8), SLAB_THE_KILLER(8),
        SUZY_LAFAYETTE(8), VULTURE_SAM(9), WILLY_THE_KID(8);

        public int lifePoints;

        Characters(int lifePoints) {
            this.lifePoints = lifePoints;
        }

    }

    private Player players[]; 
    private RollDice dRollDice
    private int playerTurn;
    private int numPlayers;
    private int middleArrows;

    public Game(int numPlayers) {

    }

    public Game() {

    }

    public void nextTurn() {
        playerTurn = (playerTurn + 1) % (numPlayers - 1);

    }

    private void setUp(){
        Role roles = new Roles(numPlayers);
        ArrayList<Role> roleList = roles.getAllRoles(); 
        for(int i = 0; i < players.length; i++){
            players[i].setRole(roleList.get(i));
        }
    }

    private void turn(){
        Character playerChar = players[playerTurn].getCharacter();
        if(playerChar.getName().equalsIgnoreCase(""))
        RollDice diceroll = new RollDice();
        ArrayList<RollDice> roll = diceroll.rollDice(diceroll.getDice());
        int rerolls = 2;
        
    }

    public void assessDice(ArrayList<RollDice> roll){

    }


}