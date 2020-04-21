import java.util.*;

import project3.RollDice;

public class Game {


    private Player players[]; 
    private int playerTurn;
    private int numPlayers;
    private int middleArrows;
    private int lossLifeIndians;

    public Game(int numPlayers) {

    }

    public Game() {

    }

    public void nextTurn() {
        playerTurn = (playerTurn + 1) % (numPlayers - 1);

    }

    private void setUp(){

        Random randomCharacter = new Random();

        for(int i = 0; i < numPlayers; i++){
            int randomInt = randomCharacter.nextInt(16);
            Character playerCharacter  = new Character(Character.Characters.values()[randomInt], "");
            players[i].setCharacter(playerCharacter);
            String charName = playerCharacter.getName().toLowerCase();
            switch(charName){
                case "bart_cassidy":
                    playerCharacter.setSpecialAbility("You may take an arrow intead of losing a life point (except to Indians or Dynamite). CANNOT be used to take the last remaining arrows in the pile.");           
                    break;
                case "black_jack":
                    playerCharacter.setSpecialAbility("You may re-roll dynamite only if you roll less than 3 in one turn. Follow usual rules if you roll 3 dynamites");
                    break;
                case "calamity_janet":
                    playerCharacter.setSpecialAbility("You can use (1) as (2) and vice-versa");
                    break;
                case "el_gringo":
                    playerCharacter.setSpecialAbility("When a player makes you lose one or more life points, they must take an arrow. Life points loss to Indians or Dynamite are not affected.");
                    break;
                case "jesse_jones":
                    playerCharacter.setSpecialAbility("If you have four life points or less, gain 2 if you use (beer) on yourself.");
                    break;
                case "jourdonnais":
                    playerCharacter.setSpecialAbility("You never lose more than one life point to the Indians.");
                    break;
                case "kit_carlson":
                    playerCharacter.setSpecialAbility("For each (gatling gun) you may discard one arrow from any player, including yourself. If you roll 3 (gatling guns) you still do their usual rules.");
                    break;
                case "lucky_duke":
                    playerCharacter.setSpecialAbility("You may make one extra re-roll");
                    break;
                case "paul_regret":
                    playerCharacter.setSpecialAbility("You never lose life points to the (gatling gun)");
                    break;
                case ""

        }

        Role roles = new Roles(numPlayers);
        ArrayList<Role> roleList = roles.getAllRoles(); 
        for(int i = 0; i < players.length; i++){
            players[i].setRole(roleList.get(i));
        }
    }

    private void turn(){
        Character playerChar = players[playerTurn].getCharacter();
        if(playerChar.getName().equalsIgnoreCase("sid_ketchum")){
            //player chooses who gets life point
            players[playerChosen].addLifePoint(1);
        }
        int rerolls = 2;
        if(playerChar.getName().equalsIgnoreCase("lucky_duke")){
            rerolls++;
        }
        
        for(int i = 0; i < rerolls; i++){
            RollDice diceroll = new RollDice()
            ArrayList<RollDice> roll = diceroll.rollDice(diceroll.getDice());    

            for(int rollIterator = 0; rollIterator < roll.size(); rollIterator++){

                if()
            }
        }

        
    }

    public void assessDice(ArrayList<RollDice> roll){

    }


}