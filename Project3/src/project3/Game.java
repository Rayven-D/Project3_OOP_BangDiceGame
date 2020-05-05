package project3;

import java.util.*;

public class Game {


    private Player players[]; 
    private int playerTurn;
    public boolean won;
    private int numPlayers;
    private int alivePlayers;
    private int middleArrows;
    private boolean chiefArrow;
    private int lossLifeIndians;
    private boolean zombieOutbreak;
    private CyclicDoublyLinkedList<Player> playerSeating;
    private MasterRole roles; 
    private List<Integer> boneyardCards = new ArrayList<Integer>();
    private List<Integer> drawnBYCards = new ArrayList<Integer>();
    private boolean expansions;

    public Game(int numPlayers, int userPlayers) {
        this.numPlayers = numPlayers;
        this.alivePlayers = numPlayers;
        this.middleArrows = 9;
        this.zombieOutbreak = false;
        this.playerTurn = ((int)Math.random()*100) % this.numPlayers;
        roles = new MasterRole(numPlayers);
        players = new Player[numPlayers];
        
        expansions = true; //are expansions included or not
        
        int i = 0;
        for(; i < userPlayers; i++){
            players[i] = new Player(i, true);
        }
        for(; i < numPlayers; i++){
            players[i] = new Player(i, false);
        }
        setUp();
        runGame();
    }

    public Game() {
    }

    public void runGame(){
        for(Player p : players){
                System.out.println(p.getRole().getName() + " " + p.getCharacter().getName() + " " + p.getHealth());
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
        
        if(expansions == true){
            for(int i = 0; i < 20; i++){
                int temp = i % 3;
                boneyardCards.add(temp);
            }
            Collections.shuffle(boneyardCards);
        }

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
                case "apache_kid":
                    playerCharacter.setSpecialAbility("If you roll an ARROW, you may take the Indian Chief's Arrow from another Player");
                    break;
                case "bill_noface":
                    playerCharacter.setSpecialAbility("Apply ARROW results only after your last roll");
                    break;
                default:
                    playerCharacter.setSpecialAbility("NULL");
                    break;
            }
        }
        
        
        
        Random randomRole = new Random();
        roles.assignRole(players);
    }

    private void turn(){
        /*
           Undead or Alive Expansion
        */
        if(players[playerTurn].getStatus() == false &&  !zombieOutbreak){
             System.out.println("Player " + players[playerTurn].getCharacter().getName() + " is eliminated.Skipping Turn");
             if(this.expansions == true){
                int temp = this.boneyardCards.get(0);
                if(temp == 0){
                    //no outbreak | resuffle card back into deck
                    Collections.shuffle(this.boneyardCards);
                    return;
                }
                else{
                    this.drawnBYCards.add(temp);
                    this.boneyardCards.remove(0);
                }
                int sum = 0;
                for(int drawn: this.drawnBYCards){
                    sum += drawn;
                }
                if(sum > this.alivePlayers){
                    outbreak();
                }
             }
             else{
                 return;
             }
            return;
        }
        Character playerChar = players[playerTurn].getCharacter();
        System.out.println(playerChar.getName() + "'s Turn");
        if(playerChar.getName().equalsIgnoreCase("sid_ketchum") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
            int chosenPlayer = 0; // index of chosen player
            int gainHealth = players[chosenPlayer].getCharacter().gainLifePoints(2);
            players[chosenPlayer].setHealth(gainHealth);
        }
        int rolls = 3;
        if(playerChar.getName().equalsIgnoreCase("lucky_duke") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
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
        RollDice diceroll = new RollDice();
        List<Die> roll = diceroll.getDice();
        if(zombieOutbreak){
            if(players[playerTurn].getZombie()){
                roll.remove(roll.size() - 1);
                roll.remove(roll.size() -2);
            }
        }
        for(int i = 0; i < rolls; i++){  
            roll = diceroll.rollDice(roll);
                        for(Die d: roll){
                System.out.print(d.getFace() + " ");
            }   
            System.out.println();
            
            /*
            This for loop resolves arrows and dynamite, as they are resolved while
                the player is still rolling
            */
            for(int rollIterator = 0; rollIterator < roll.size() || numDyn >= allDynamite.length; rollIterator++){
                String currFace = roll.get(i).getFace().toLowerCase();
                if(currFace.equalsIgnoreCase("arrow") && !players[playerTurn].getCharacter().getName().equalsIgnoreCase("bill_noface")){
                    if( chiefArrow == true){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                            Scanner s = new Scanner(System.in);
                            System.out.println("Do you want to use your ability? (yes/no)");
                            String ans = s.next();
                            if(ans.equalsIgnoreCase("yes"))
                                ability = true;
                        }
                        else{
                            ability = true;
                        }
                        /*ask user if he wants to take chief arrow if its in the middle*/
                        if(true)
                            players[playerTurn].setChiefArrow(true);
                    }else if(players[playerTurn].getCharacter().getName().equalsIgnoreCase("apache_kid") && !players[playerTurn].hasChiefArrow() && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                        boolean ability = false;
                        if(player[playerTurn].isUser()){
                            if(true){ //if apache_kid does want to take Indian Cheif's Arrow from another layer
                                for(int k = 0; k < numPlayers; k++){
                                    if(players[k].hasChiefArrow()){
                                        players[k].setChiefArrow(false);
                                        players[playerTurn].setChiefArrow(true);
                                    }
                                }
                            }
                        }
                    }
                    else{
                        int a = players[i].getArrows();
                        a++;
                        players[i].setArrows(a);
                        middleArrows--;
                        if(middleArrows == 0){
                            indianAttack();
                        }
                    }
                }
                else if(currFace.equalsIgnoreCase("brokena")){
                    //if player decised to return an arrow from any player to central pile
                    int targetIndex = 0;
                    if(true){
                        players[targetIndex].setArrows(players[targetIndex].getArrows() -1);
                        middleArrows++;
                    }
                }
                else if(currFace.equalsIgnoreCase("bullet")){
                    players[playerTurn].setHealth(players[playerTurn].getCharacter().loseLifePoints(1));
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
                if(playerChar.getName().equalsIgnoreCase("black_jack") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                    for(int j = 0; j < allDynamite.length; j++){
                        if(allDynamite[i] == null){
                            break;
                        }
                        allDynamite[i].setReroll(true);
                        allDynamite[i].setChooseRoll(true);
                    }           
                }
            }
           
            if(!rollAgain  || (i+1) == rolls || numDyn >= 3){
                //Belle Star Ability
                if(numDyn <3 && numDyn > 0 && players[playerTurn].getCharacter().getName().equalsIgnoreCase("belle_star") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster()))
                {
                    boolean ability = false;
                    if(players[playerTurn].isUser()){
                        Scanner s = new Scanner(System.in);
                        System.out.println("Do you want to use your ability? (yes/no)");
                        String ans = s.next();
                        if(ans.equalsIgnoreCase("yes"))
                            ability = true;
                    }
                    else{

                    }
                    //if he choses to use his ability
                    if(ability){
                        for(int k = 0; k < numDyn; k++){
                            int diceIndex = 0;//index of chosen dynamite die
                            roll.get(diceIndex).setFace("Gatling");
                        }
                    }
                }   
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
        
        /*
        Resolving the Wishkey Bottle
        */
        for(Die d: finalRoll){
            if(d.getFace().equalsIgnoreCase("whiskey")){
                players[playerTurn].setHealth(players[playerTurn].getCharacter().gainLifePoints(1));  
                if(players[playerTurn].getCharacter().getName().equalsIgnoreCase("greg_digger") && players[playerTurn].getStatus()){
                    players[playerTurn].setHealth(players[playerTurn].getCharacter().gainLifePoints(1));         
                }
            }
        }
        
        /*
       we resolve the one and two die face
        */
        int beerKiller = 0;
        for(Die d: finalRoll){
                if(players[playerTurn].getCharacter().getName().equalsIgnoreCase("slab_the_killer") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                    boolean ability = false;
                    boolean ability = false;
                    if(players[playerTurn].isUser()){
                        Scanner s = new Scanner(System.in);
                        System.out.println("Do you want to use your ability? (yes/no)");
                        String ans = s.next();
                        if(ans.equalsIgnoreCase("yes"))
                            ability = true;
                    }
                    else{

                    }
                    if(ability){/*he chooses to double 1 or 2 instead */
                        beerKiller++; 
                        continue;
                    }
                }
            String faceName = d.getFace();
            if(faceName.equalsIgnoreCase("one")|| faceName.equalsIgnoreCase("done")){
                lafayette = false;
                int doubleOne = 0;
                if(faceName.equalsIgnoreCase("done"))
                    doubleOne++;
                for(int i = 0 ; i <= doubleOne; i++){
                    int spacesFromPlayer = 1;
                    if(playerChar.getName().equalsIgnoreCase("calamity_janet") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                            Scanner s = new Scanner(System.in);
                            System.out.println("Do you want to use your ability? (yes/no)");
                            String ans = s.next();
                            if(ans.equalsIgnoreCase("yes"))
                                ability = true;
                        }
                        else{
    
                        }
                        if(ability){
                            spacesFromPlayer = 2;
                        }
                    }
                    if(playerChar.getName().equalsIgnoreCase("rose_doolan") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                            Scanner s = new Scanner(System.in);
                            System.out.println("Do you want to use your ability? (yes/no)");
                            String ans = s.next();
                            if(ans.equalsIgnoreCase("yes"))
                                ability = true;
                        }
                        else{
    
                        }
                        if(ability){
                            spacesFromPlayer++;
                        }
                    }
                    /*player chooses spacesFromPlayer spaces away*/
                    int targetPlayer = 0;
                    loseLife(players[playerTurn], players[targetPlayer]);
                    if(beerKiller > 0){
                        loseLife(players[playerTurn], players[targetPlayer]);
                        beerKiller--;
                    }
                }
            }
            if(faceName.equalsIgnoreCase("two")|| faceName.equalsIgnoreCase("dtwo")){
                lafayette = false;
                int doubleTwo = 0;
                if(faceName.equalsIgnoreCase("dtwo"))
                    doubleTwo++;
                for(int i = 0; i <= doubleTwo; i++){
                    int spacesFromPlayer = 2;
                    if(playerChar.getName().equalsIgnoreCase("calamity_janet") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                            Scanner s = new Scanner(System.in);
                            System.out.println("Do you want to use your ability? (yes/no)");
                            String ans = s.next();
                            if(ans.equalsIgnoreCase("yes"))
                                ability = true;
                        }
                        else{
    
                        }
                        if(ability){
                            spacesFromPlayer = 1;
                        }
                    }
                    if(playerChar.getName().equalsIgnoreCase("rose_doolan") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                            Scanner s = new Scanner(System.in);
                            System.out.println("Do you want to use your ability? (yes/no)");
                            String ans = s.next();
                            if(ans.equalsIgnoreCase("yes"))
                                ability = true;
                        }
                        else{
    
                        }
                        if(ability){
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
        }
        
        
        int gatling = 0;
        int doubleGatling = 0;
        /*
        This for loop resolved the beer and gatling
        */
        for(Die d: finalRoll){
            String faceName = d.getFace();
            if(faceName.equalsIgnoreCase("beer") || faceName.equalsIgnoreCase("dbeer")){
                int chosenPlayer = 0; // index of chosen player
                
                if(players[chosenPlayer].getCharacter().getName().equalsIgnoreCase("jesse_jones")&& playerTurn == chosenPlayer && players[chosenPlayer].getHealth() <= 4 && players[chosenPlayer].getStatus()){
                    players[chosenPlayer].setHealth(players[chosenPlayer].getCharacter().gainLifePoints(2));
                }
                else{
                    players[chosenPlayer].setHealth(players[chosenPlayer].getCharacter().gainLifePoints(1));
                    if(faceName.equalsIgnoreCase("dbeer")){
                        chosenPlayer = 0; // index of chosen player
                        players[chosenPlayer].setHealth(players[chosenPlayer].getCharacter().gainLifePoints(1));

                    }
                }
            }
            if(faceName.equalsIgnoreCase("gatling")){
                gatling++;
            }
            if(faceName.equalsIgnoreCase("dgatling")){
                doubleGatling++;
            }
            if(players[playerTurn].getCharacter().getName().equalsIgnoreCase("bill_noface") && faceName.equalsIgnoreCase("arrow") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                 int a = players[playerTurn].getArrows();
                        a++;
                        players[playerTurn].setArrows(a);
                        middleArrows--;
                        if(middleArrows == 0){
                            indianAttack();
                }
            }
        }
        if(playerChar.getName().equalsIgnoreCase("kit_carlson") && gatling > 0 && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                boolean ability = false;
                if(players[playerTurn].isUser()){
                    Scanner s = new Scanner(System.in);
                    System.out.println("Do you want to use your ability? (yes/no)");
                    String ans = s.next();
                    if(ans.equalsIgnoreCase("yes"))
                        ability = true;
                }
                else{

                }
                if(ability){/*he chooses to use gatling to discard*/
                for(int i = 0; i < gatling; i++){
                    int chosen = 0; //index of chosen player, -1 if done choosing
                    if(chosen == -1)
                        break;
                    players[chosen].setArrows(players[chosen].getArrows() - 1);
                }
            }
        }
        if((gatling == 2 && playerChar.getName().equalsIgnoreCase("willy_the_kid")) || gatling >= 3 || doubleGatling >= 3  && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
            for(int i = 0; i < players.length; i++){
                if(i == playerTurn){
                    continue;
                }else if(players[i].getCharacter().getName().equalsIgnoreCase("paul_regret") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                    continue;
                }
                else{
                    if(gatling > 0){
                        players[i].setHealth(players[i].getCharacter().loseLifePoints(1));
                        gatling--;
                    }
                    else if(doubleGatling >= 0){
                        players[i].setHealth(players[i].getCharacter().loseLifePoints(2));
                        doubleGatling--;
                    }
                }
            }
        }
        
        /*
            Dueling (expansion 2)
        */
        int numDuels = 0;
        Die duelTemp = null;
        for(Die d: finalRoll){
            if(d.getFace().equalsIgnoreCase("duel")){
                numDuels++;
                duelTemp = d;
            }
        }
        for(int i = 0; i < numDuels; i++){
            //player chooses which player to target
            int indexTarget = 0;
            duels(players[playerTurn], players[indexTarget], duelTemp);
        }
        
        
        //turn resolution 
        //check if any players have 0 or less life points
        for(Player p: players){
            if(p.getHealth() <= 0){
                p.setStatus(false);
                this.alivePlayers--;
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
       if(target.getCharacter().getName().equalsIgnoreCase("bart_cassidy") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
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
       else if(target.getCharacter().getName().equalsIgnoreCase("el_gringo") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
           int a = attacker.getArrows();
           a++;
           attacker.setArrows(a);
           middleArrows--;
           if(middleArrows == 0)
                indianAttack();
           int loseHealth = target.getCharacter().loseLifePoints(loss);
           target.setHealth(loseHealth);
       }
       else if(target.getCharacter().getName().equalsIgnoreCase("pedro_ramirez") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
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
           if(players[i].hasChiefArrow()){
               int indexOfHighestArrowPlayer = 0;
               for(int j = 1; j < players.length; j++){
                   if(players[j].getArrows() > players[indexOfHighestArrowPlayer].getArrows()){
                       indexOfHighestArrowPlayer = j;
                   }else if(players[j].getArrows() == players[indexOfHighestArrowPlayer].getArrows()){
                       indexOfHighestArrowPlayer = -1;
                   }
               }
               if(i != indexOfHighestArrowPlayer){
                    players[i].setHealth(players[i].getCharacter().loseLifePoints(2)); 
               }
               players[i].setChiefArrow(false);
               this.chiefArrow = true;
           }
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
   
   
   public void duels(Player attacker, Player target, Die duel){
       boolean rolledDuel = false;
       int turn = 0; //even if target fails, odd if attacker fails
       String[] DuelFace = new String[]{"Whiskey", "Duel", "Arrow", "Gatling", "Duel", "Dynamite"};
       while(!rolledDuel){
           Random r = new Random();
           duel.setFace(DuelFace[r.nextInt(6)]);
           if(duel.getFace().equalsIgnoreCase("duel")){
               turn++;
               rolledDuel = true;
           }else{
                if(turn%2 == 0){
                    target.setHealth(target.getCharacter().loseLifePoints(1));
                }else{
                    attacker.setHealth(attacker.getCharacter().loseLifePoints(1));
                }
           }
       }
   }
   
   public void outbreak(){
       this.drawnBYCards = null;//remove all boneyard cards fromn play
       this.boneyardCards = null;
       
       for(Player p: players){
           if(p.getStatus() == false && p.getRole().getName().equalsIgnoreCase("renegade")){
               p.setZombieMaster(true);
           }
           else if(p.getStatus() == false){
               p.setZombie(true, this.alivePlayers);
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
    
    
   
    public static void main(String [] args){
        Game g = new Game(4,1);
    }
}