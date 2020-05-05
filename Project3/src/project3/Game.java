package project3;

import java.util.*;
import view.Board;

public class Game {


    private Player players[]; 
    public int playerTurn;
    private int numPlayers;
    public int middleArrows;
    private int lossLifeIndians;
    private CyclicDoublyLinkedList<Player> playerSeating;
    private MasterRole roles; 
    public boolean isUser;
    public boolean won;

    public Game(int numPlayers, int userPlayers) {
        this.numPlayers = numPlayers;
        this.middleArrows = 9;
        this.playerTurn = ((int)Math.random()*100) % this.numPlayers;
        roles = new MasterRole(numPlayers);
        players = new Player[numPlayers];
        int i = 0;
        for(; i < userPlayers; i++){
            players[i] = new User(i);
        }
        for(; i < numPlayers; i++){
            players[i] = new Comp(i);
        }
        setUp();
    }

    public Game() {
        
    }

    public void runGame(){
        for(Player p : players){
                System.out.println(p.getRole().getName() + " " + p.getCharacter().getName() + " " + p.getHealth());
                if(p.isUser()){
                    isUser = true;
                }
        }
        
        won = false;
        while(true){
            turn();
            for(int i = 0; i < players.length; i++){
                won = players[i].getRole().getWon(players);
                if(won){
                    System.out.println(players[i].getCharacter().getName() + " WON!!!");
                    break;
                }
            }
            if(won){
                break;
            }
            System.out.println();
            for(Player p: players){
                System.out.println(p.getCharacter().getName() + " " + p.getHealth());
            }
            System.out.println();
            nextTurn();
        }
        
    }
    
    public void nextTurn() {
        playerTurn = (playerTurn + 1) % (numPlayers);

    }

    private void setUp(){

        Random randomCharacter = new Random();
        int usedIndex[] = new int[numPlayers];
        Character playerCharacter = new Character(Character.Characters.values()[0], "");

        for(int i = 0; i < numPlayers; i++){
            int randomInt;
            int usedIndexes[] = new int[numPlayers];
            boolean used= true;
            while(used){
                randomInt = randomCharacter.nextInt(16);
                for(int j = 0; j <= i; j++){
                    if(i == 0){
                        playerCharacter  = new Character(Character.Characters.values()[randomInt], "");
                        players[i].setCharacter(playerCharacter);
                        used = false;
                    }else if(randomInt == usedIndex[j]){
                        used = true;
                    }else{
                        playerCharacter  = new Character(Character.Characters.values()[randomInt], "");
                        players[i].setCharacter(playerCharacter);
                        used = false;
                    }
                }
            }
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
        roles.assignRole(players);
    }

    public List<Die> turn(){
        if(players[playerTurn].getStatus() == false){
             System.out.println("Player " + players[playerTurn].getCharacter().getName() + " is eliminated.Skipping Turn");
            return null;
        }
        Character playerChar = players[playerTurn].getCharacter();
        System.out.println(playerChar.getName() + "'s Turn");
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
            RollDice diceroll = new RollDice();
            List<Die> roll = diceroll.rollDice(diceroll.getDice());   
            Board.curRoll = roll;
            //Add a pop-up to tell the user that he is playing the game
            System.out.println("Roll " + i + " " );
            
            /*
            This for loop resolves arrows and dynamite, as they are resolved while
                the player is still rolling
            */
            for(int rollIterator = 0; rollIterator < roll.size() || numDyn >= allDynamite.length; rollIterator++){
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
            }
            for(Die d: roll){
                System.out.print(d.getFace() + " ");
            }   
            System.out.println();
            if(!rollAgain  || (i+1) == rolls || numDyn >= 3){
                writeDiceRoll(roll);                
                finalRoll = roll;
                break;
            }
            writeDiceRoll(roll);
        }
        System.out.println("");
        if(numDyn >= 3){
            players[playerTurn].setHealth(players[playerTurn].getCharacter().loseLifePoints(1));
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
                    boolean ability = false;
                    
                    if(isUser){
                        
                         Scanner s = new Scanner(System.in);
                        System.out.println("Do you want to use your ability? (yes/no)");
                        String ans = s.next();
                        if(ans.equalsIgnoreCase("yes"))
                             ability = true;
                        if(ability){/*he chooses to double 1 or 2 instead */
                            beerKiller++; 
                            continue;
                        }
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
                boolean ability = false;
                if(isUser){
                         Scanner s = new Scanner(System.in);
                        System.out.println("Do you want to use your ability? (yes/no)");
                        String ans = s.next();
                        if(ans.equalsIgnoreCase("yes"))
                             ability = true;
                        if(ability){/*he chooses to use gatling to discard*/
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
                    if(isUser){
                         Scanner s = new Scanner(System.in);
                        System.out.println("Do you want to use your ability? (yes/no)");
                        String ans = s.next();
                        if(ans.equalsIgnoreCase("yes"))
                             ability = true;
                        if(ability){
                        spacesFromPlayer = 2;
                    }
                }
                if(playerChar.getName().equalsIgnoreCase("rose_doolan")){
                        ability = false;
                        if(isUser){
                         Scanner s = new Scanner(System.in);
                        System.out.println("Do you want to use your ability? (yes/no)");
                        String ans = s.next();
                        if(ans.equalsIgnoreCase("yes"))
                             ability = true;
                        if(ability){
                        spacesFromPlayer++;
                    }
                }
                /*player chooses spacesFromPlayer spaces away*/
                int targetPlayer = 0;
                if (!players[playerTurn].isUser()) {
                    targetPlayer = ((Comp)players[playerTurn]).findTarget(spacesFromPlayer);
                }
                loseLife(players[playerTurn], players[targetPlayer]);
                if(beerKiller > 0){
                    loseLife(players[playerTurn], players[targetPlayer]);
                }
            }
            if(faceName.equalsIgnoreCase("two")){
                lafayette = false;
                spacesFromPlayer = 2;
                if(playerChar.getName().equalsIgnoreCase("calamity_janet")){
                    ability = false;
                     if(isUser){
                         Scanner s = new Scanner(System.in);
                        System.out.println("Do you want to use your ability? (yes/no)");
                        String ans = s.next();
                        if(ans.equalsIgnoreCase("yes"))
                             ability = true;
                        if(ability){
                        spacesFromPlayer = 1;
                    }
                }
                if(playerChar.getName().equalsIgnoreCase("rose_doolan")){
                    ability = false;
                     if(isUser){
                         Scanner s = new Scanner(System.in);
                        System.out.println("Do you want to use your ability? (yes/no)");
                        String ans = s.next();
                        if(ans.equalsIgnoreCase("yes"))
                             ability = true;
                        if(ability){
                        spacesFromPlayer++;
                    }
                }
                /*player chooses spacesFromPlayer spaces away*/
                int targetPlayer = 0;
                if (!players[playerTurn].isUser()) {
                    targetPlayer = ((Comp)players[playerTurn]).findTarget(spacesFromPlayer);
                }
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
 }
            }
        }
        }
        return finalRoll;
    }
    
   
   
   public void loseLife(Player attacker, Player target){
       int loss = 1;
       if(target.getCharacter().getName().equalsIgnoreCase("bart_cassidy")){
             boolean ability = false;
             Scanner s = new Scanner(System.in);
             System.out.println("Do you want to use your ability? (yes/no)");
             String ans = s.next();
             if(ans.equalsIgnoreCase("yes"))
                  ability = true;
             if(ability)/*chooses to take arrow instead*/{
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
          boolean ability = false;
          Scanner s = new Scanner(System.in);
          System.out.println("Do you want to use your ability? (yes/no)");
          String ans = s.next();
          if(ans.equalsIgnoreCase("yes"))
               ability = true;
          if(ability){ /*chooses to discard an arrow*/
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
       //displays the dice roll to all player
       
   }
   
   public void writePlayers(){
       
   }
   /*
    Collaborator: Shreyesh Arangath
   */
   public Player[] getPlayers(){
       Player[] tempPlayer = this.players;
       return tempPlayer;
   }
   
   public Player[] setRoles(){
       MasterRole mr = new MasterRole(numPlayers);
       mr.assignRole(players);
       return players;
   }
   
   
   //Assembles the players in a circle
    public CyclicDoublyLinkedList createPlayerSeating(Player[] players){
        CyclicDoublyLinkedList<Player> ll = new CyclicDoublyLinkedList<>();
        for(Player player: players){
            ll.insert(player);
        }
        return ll;
    }
    
    //Returns the index/number of players whom you can attack
    public Integer[] getPlayerToAttack(int distance, int curPlayerNumber){
        playerSeating = createPlayerSeating(players);
        int index=0;
        Node<Player> cur = playerSeating.start;
        Node<Player> initPlayer;
        Integer[] attackPlayerIndices = new Integer[2];
        for(int i=0; i<curPlayerNumber; i++){
            cur = cur.getNext();
        }
        initPlayer = cur;
        
        for(int i=0; i<=distance; i++){
            if(cur.getData().getStatus()){
                if(i==distance){
                    attackPlayerIndices[index] = cur.getData().getNum();
                    index++;
                }
                cur = cur.getNext();
            }
                
        }
        
        cur = initPlayer;
        
        for(int i=0; i<=distance; i++){
            if(cur.getData().getStatus()){
               if(i==distance){
                attackPlayerIndices[index] = cur.getData().getNum();
                index++;
                }
                cur = cur.getPrevious(); 
            }
            
        }
        
        return attackPlayerIndices;
    }
    
    public int getArrowsInTheMiddle(){
        return middleArrows;
    }
    
    
   
    public static void main(String [] args){
        Game g = new Game(4,1);
        g.runGame();
    }
}