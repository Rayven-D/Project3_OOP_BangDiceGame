package project3;

import java.util.*;

public class Game {


    Player players[]; 
    private int playerTurn;
    private int numPlayers;
    private int middleArrows;
    private int lossLifeIndians;

    public Game(int numPlayers, int userPlayers) {
        this.numPlayers = numPlayers;
        this.middleArrows = 9;
        this.playerTurn = 0;
        players = new Player[numPlayers];
        int i = 0;
        for(; i < userPlayers; i++){
            players[i] = new Player(i, true);
        }
        for(; i < numPlayers; i++){
            players[i] = new Player(i, false);
        }
        setUp();
        for(int j = 0; j < numPlayers; j++){
            System.out.println(players[j].getCharacter().getName() + players[j].getCharacter().getSpecialAbility());
        }
        setUp();
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
                case "pedro_ramirez":
                    playerCharacter.setSpecialAbility("Each time you lose a life point, you may discard one of your arrows. You still lose a life point.");
                    break;
                case "rose_doolan":
                    playerCharacter.setSpecialAbility("You may use (1) or (2) for players sitting one place further from you.");
                    break;
                case "sid_ketchum":
                    playerCharacter.setSpecialAbility("At the beginning of your turn, any player of your choice gains one life point. You can choose yourself.");
                    break;
                case "slab_the_killer":
                    playerCharacter.setSpecialAbility("Once per turn, you can use a (beer) to double a (1) or (2). It takes two life points from target player instead of one. The beer does not give you any life points.");
                    break;
                case "suzy_lafayette":
                    playerCharacter.setSpecialAbility("If you didn't roll any (1)'s or (2)'s, you gain two life points. Applies at the end of your turn");
                    break;
                case "vulture_sam":
                    playerCharacter.setSpecialAbility("Each time another player is eliminated, you gain two life points.");
                    break;
                case "willy_the_kid":
                    playerCharacter.setSpecialAbility("You only need 2 (gatling guns) to use the Gatling Gun. You can only use the Gatling Gun one per turn.");
                    break;
                default:
                    playerCharacter.setSpecialAbility("NULL");
                    break;
            }
        }
        
        
        
        Random randomRole = new Random();
        int [] roleIndex = new int[numPlayers];
        for(int i = 0; i < roleIndex.length; i++)
            roleIndex[i] = -1;
        
        for(int i = 0; i < roleIndex.length; i++){
            int randomInt = randomRole.nextInt(numPlayers);
            for(int j = 0; j <= i; j++){
                if(roleIndex[j] != randomInt && i == j){
                    roleIndex[j] = randomInt;
                }
            }
        }
        
        for(int i = 0; i < players.length; i++){
        }
    }
    
    public void runGame(){
        
    }

    private void turn(){
        Character playerChar = players[playerTurn].getCharacter();
        if(playerChar.getName().equalsIgnoreCase("sid_ketchum")){
            int chosenPlayer = 0; // index of chosen player
            int gainHealth = players[chosenPlayer].getCharacter().gainLifePoints(2);
            players[chosenPlayer].setHealth(gainHealth);
        }
        int rolls = 3;
        if(playerChar.getName().equalsIgnoreCase("lucky_duke")){
            rolls++;
        }
        
        int numDyn = 0;
        Die allDynamite[] = new Die[3];
        allDynamite[0] = null;
        allDynamite[1] = null;
        allDynamite[2] = null;
        
        List<Die> finalRoll = null;
        
        boolean lafayette = true;
        
        boolean rollAgain = true;
        
        for(int i = 0; i < rolls; i++){
            RollDice diceroll = new RollDice("none");
            List<Die> roll = diceroll.rollDice(diceroll.getDice());    
  
            
            /*
            This for loop resolves arrows and dynamite, as they are resolved while
                the player is still rolling
            */
            for(int rollIterator = 0; rollIterator < roll.size(); rollIterator++){
                String currFace = roll.get(i).getFace().toLowerCase();
                if(currFace.equalsIgnoreCase("arrow")){
                    int a = players[i].getArrows();
                    a++;
                    players[i].setArrows(a);
                }
                else if(currFace.equalsIgnoreCase("dynamite")){
                    Die d = roll.get(i);
                    d.setReroll(false);
                    d.setChooseRoll(false);
                    allDynamite[numDyn] = d;
                    numDyn++;
                }  
            }
            if(numDyn == 3){
                break;
            }
            if(playerChar.getName().equalsIgnoreCase("black_jack")){
                for(int j = 0; j < allDynamite.length; j++){
                    if(allDynamite[i] == null){
                        break;
                    }
                    allDynamite[i].setReroll(true);
                    allDynamite[i].setChooseRoll(true);
                }           
            }
            if(!rollAgain  || (i+1) == rolls){
                writeDiceRoll(roll);                
                finalRoll = roll;
                break;
            }
            writeDiceRoll(roll);
        }
        
        int gatling = 0;
        int beerKiller = 0;
        /*
        This for loop resolved the beer and gatling
        */
        for(Die d: finalRoll){
            String faceName = d.getFace();
            if(faceName.equalsIgnoreCase("beer")){
                if(players[playerTurn].getCharacter().getName().equalsIgnoreCase("slab_the_killer")){
                    if(/*he chooses to double 1 or 2 instead */){
                        beerKiller++; 
                        continue;
                    }
                }
                int chosenPlayer = 0; // index of chosen player
                
                if(players[chosenPlayer].getCharacter().getName().equalsIgnoreCase("jesse_jones")&& playerTurn == chosenPlayer && players[chosenPlayer].getHealth() <= 4){
                    players[chosenPlayer].setHealth(players[chosenPlayer].getCharacter().gainLifePoints(2));
                }
                else{
                    players[chosenPlayer].setHealth(players[chosenPlayer].getCharacter().gainLifePoints(1));
                }
            }
            if(faceName.equalsIgnoreCase("gatling")){
                gatling++;
            }
        }
        if(playerChar.getName().equalsIgnoreCase("kit_carlson") && gatling > 0){
            if(/*he chooses to use gatling to discard*/){
                for(int i = 0; i < gatling; i++){
                    int chosen = 0; //index of chosen player, -1 if done choosing
                    if(chosen == -1)
                        break;
                    players[chosen].setArrows(players[chosen].getArrows() - 1);
                }
            }
        }
        if((gatling == 2 && playerChar.getName().equalsIgnoreCase("willy_the_kid")) || gatling >= 3){
            for(int i = 0; i < players.length; i++){
                if(i == playerTurn){
                    continue;
                }else if(players[i].getCharacter().getName().equalsIgnoreCase("paul_regret")){
                    continue;
                }
                else{
                    players[i].setHealth(players[i].getCharacter().loseLifePoints(1));
                    
                }
            }
        }
        
        /*
        Finally, after all is resolved, we resolve the one and two die face
        */
        for(Die d: finalRoll){
            String faceName = d.getFace();
            if(faceName.equalsIgnoreCase("one")){
                lafayette = false;
                int spacesFromPlayer = 1;
                if(playerChar.getName().equalsIgnoreCase("calamity_janet")){
                    /*ask user if they want to do two instead*/
                    if(true){
                        spacesFromPlayer = 2;
                    }
                }
                if(playerChar.getName().equalsIgnoreCase("rose_doolan")){
                    //asks if user wants to use one for one place furhter
                    if(true){
                        spacesFromPlayer++;
                    }
                }
                /*player chooses spacesFromPlayer spaces away*/
                int targetPlayer = 0;
                loseLife(players[playerTurn], players[targetPlayer]);
                if(beerKiller > 0){
                    loseLife(players[playerTurn], players[targetPlayer]);
                }
            }
            if(faceName.equalsIgnoreCase("two")){
                lafayette = false;
                int spacesFromPlayer = 2;
                if(playerChar.getName().equalsIgnoreCase("calamity_janet")){
                    /*ask user if they want to do one instead*/
                    if(true){
                        spacesFromPlayer = 1;
                    }
                }
                if(playerChar.getName().equalsIgnoreCase("rose_doolan")){
                    //asks if user wants to use two for one place furhter
                    if(true){
                        spacesFromPlayer++;
                    }
                }
                /*player chooses spacesFromPlayer spaces away*/
                int targetPlayer = 0;
                loseLife(players[playerTurn], players[targetPlayer]);
                if(beerKiller > 0){
                    loseLife(players[playerTurn], players[targetPlayer]);
                }
            }
        }
        
        
        //turn resolution 
        //check if any players have 0 or less life points
        for(Player p: players){
            if(p.getHealth() <= 0){
                p.setStatus(false);
                for(int i = 0; i < players.length; i++){
                    if(players[i].getStatus() && players[i].getCharacter().getName().equalsIgnoreCase("vulture_sam")){
                        players[i].setHealth(players[i].getCharacter().gainLifePoints(2));
                    }
                }
            }
        }
        
    }
   
   public void loseLife(Player attacker, Player target){
       int loss = 1;
       if(target.getCharacter().getName().equalsIgnoreCase("bart_cassidy")){
           if(/*chooses to take arrow instead*/){
               int a = target.getArrows();
               a++;
               target.setArrows(a);
               middleArrows--;
               if(middleArrows == 0){
                   indianAttack();
               }
           }
       }
       else if(target.getCharacter().getName().equalsIgnoreCase("el_gringo")){
           int a = attacker.getArrows();
           a++;
           attacker.setArrows(a);
           middleArrows--;
           if(middleArrows == 0)
                indianAttack();
           int loseHealth = target.getCharacter().loseLifePoints(loss);
           target.setHealth(loseHealth);
       }
       else if(target.getCharacter().getName().equalsIgnoreCase("pedro_ramirez")){
          if(/*chooses to discard an arrow*/){
              target.setArrows(target.getArrows() - 1);
              middleArrows++;
          }
          int loseHealth = target.getCharacter().loseLifePoints(loss);
           target.setHealth(loseHealth);
       }
       else{
           int loseHealth = target.getCharacter().loseLifePoints(loss);
           target.setHealth(loseHealth);
       }
   }

   public void indianAttack(){
       for(int i = 0; i < players.length; i++){
           if(players[i].getCharacter().getName().equalsIgnoreCase("jourdonnais")){
               players[i].setHealth(players[i].getCharacter().loseLifePoints(1));
               middleArrows += players[i].getArrows();
               continue;
           }
           else{
              int numArr = players[i].getArrows();
              players[i].setHealth(players[i].getCharacter().loseLifePoints(numArr)); 
              middleArrows += numArr;
           }
       }
   }

   public void writeDiceRoll(List<Die> roll){
       //displays the dice roll to all players
       
   }
   
   public void writePlayers(){
       
   }
   
    public static void main(String [] args){
        Game g = new Game(4,1);
    }
}