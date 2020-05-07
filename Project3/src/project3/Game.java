/**
 * CS 2450 001
 * Rayven Jan Deray
 * rayven.deray@ttu.edu
 * 
 */



package project3;

import java.util.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import view.Board;


/**
 * 
 * @author Rayven
 * Collaborators:
 *      Shreyesh
 *      Jeffery
 */
public class Game {

    /**
     * The list of players in the game
     */
    private Player players[]; 
    /**
     * The index of current player's turn
     */
    public int playerTurn;
    /*
        A boolean that if true, there is a winner and the game stops
    */
    public boolean won;
    
    /**
     * The number of players in the game
     */
    private int numPlayers;
    
    /**
     * the number of players that are still alive in the game
     */
    private int alivePlayers;
    
    /**
     * The number of arrows in the middle pile
     */
    public int middleArrows;
    
    /**
     * How many rolls has the current player done
     */
    public int rollNumber;
    
    /**
     * True if the chief's arrow is in the middle pile, false if its with a player
     */
    private boolean chiefArrow;
    
    /**
     * true if there are more zombies that alive, which triggers an outbreak
     */
    private boolean zombieOutbreak;
    
    /**
     * For the GUI, has the players in a doubly-linked list for easy addition to GUI
     */
    private CyclicDoublyLinkedList<Player> playerSeating;
    
    /**
     * The role class
     */
    private MasterRole roles; 
    
    /**
     * All the boneyard cards
     */
    private List<Integer> boneyardCards = new ArrayList<Integer>();
    
    /**
     * All the drawn boneyard cards
     */
    private List<Integer> drawnBYCards = new ArrayList<Integer>();
    
    /**
     * True if the user wants the expansions to be listed 
     */
    private boolean expansions;
    
    /**
     * The current role
     */
    public List<Die> roll;
    
    /**
     * The role that occured last and kept by player
     */
    public List<Die> finalRoll;
    
    /**
     * If the player wants to roll again or not. True if we roll again
     */
    public boolean rollAgain;
    
    /**
     * Mostly for the GUI, used to signify if we are at the point where the player chooses to reroll their dice
     */
    public boolean chooseReroll;
    
    /**
     * The board class that is working to display the GUI
     */
    private Board gameBoard;
    
    /**
     * Constructor for the Game Class
     * @param numPlayers number of players playing the game
     * @param userPlayers number of users playing the game
     * @param gameBoard  the board being used
     */
    public Game(int numPlayers, int userPlayers, Board gameBoard) {
        this.numPlayers = numPlayers;
        this.alivePlayers = numPlayers;
        this.middleArrows = 9;
        this.zombieOutbreak = false;
        this.playerTurn = ((int)Math.random()*100) % this.numPlayers;
        roles = new MasterRole(numPlayers);
        players = new Player[numPlayers];
        
        this.gameBoard = gameBoard;
        int exp = this.gameBoard.wantExtensionsIncluded();
        if(exp == 1){
            this.expansions = true;
        }else{
            this.expansions = false;
        }//are expansions included or not
        
        int i = 0;
        for(; i < userPlayers; i++){
            players[i] = new User(i);
        }
        for(; i < numPlayers; i++){
            players[i] = new Comp(i);
        }
        setUp();
    }

    /**
     * The base constructor, does nothing
     */    
    public Game() {
    }

    /**
     * Get the current turn's Player object
     * @return Player = the current Turn's player object
     */
    public Player getPlayerTurn(){
        return players[playerTurn];
    }
    
    /**
     * Sets the player's turn to be the next players.
     * Modded by numPlayers to keep within array index
     */
    public void nextTurn() {
        playerTurn = (playerTurn + 1) % (numPlayers);

    }
    
    /**
     * Sets up the game. Gives all players their character and respective attribute
     * and gives all Players their roles
     */
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
            int randomInt = randomCharacter.nextInt(16);
            boolean used= true;
            while(used){
                randomInt = randomCharacter.nextInt(16);
                for(int j = 0; j <= i; j++){
                    if(randomInt == usedIndex[j]){
                        used = true;
                        break;
                    }
                    else if(j==i && usedIndex[j] != randomInt){
                        usedIndex[j] = randomInt;
                        used = false;
                    }
                }
            }
            playerCharacter  = new Character(Character.Characters.values()[randomInt], "");
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
                case "apache_kid":
                    playerCharacter.setSpecialAbility("If you roll an ARROW, you may take the Indian Chief's Arrow from another Player");
                    break;
                case "bill_noface":
                    playerCharacter.setSpecialAbility("Apply ARROW results only after your last roll");
                    break;
                case "belle_star":
                    playerCharacter.setSpecialAbility("After Each of your rolls, you can change one <dynamite> to <gatling>, not if you roll 3 <dynamite> or more");
                    break;
                case "greg_digger":
                    playerCharacter.setSpecialAbility("You may use each <wiskey> you roll twice");
                    break;
                default:
                    playerCharacter.setSpecialAbility("NULL");
                    break;
            }
        }
     
        roles.assignRole(players);
    }

    /**
     * Runs a full turn. Rolls dice, interprets, and then goes on to next player.
     * @param inventory - HBox used to help aid in updating the GUI
     */
    public void turn(HBox inventory){
        /*
           Undead or Alive Expansion
        */
        System.out.println("Middle Arrows: " + this.middleArrows);
        
        for(Player p: players){
            System.out.println(p.getCharacter().getName() + " " + p.getArrows() + " " + p.getHealth());
        }
        if(players[playerTurn].getStatus() == false &&  !zombieOutbreak){
             System.out.println("Player " + players[playerTurn].getCharacter().getName() + " is eliminated.Skipping Turn");
             JOptionPane.showMessageDialog(null, "Player " + players[playerTurn].getCharacter().getName() + " is eliminated.Skipping Turn");
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
            int chosenPlayer = playerTurn; // index of chosen player
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
        
        finalRoll = null;
        
        boolean lafayette = true;
        this.gameBoard.nextRoll = false;
        
       rollAgain = true;
        RollDice diceroll = new RollDice();
        roll = diceroll.getDice();
       
        if(zombieOutbreak){
            if(players[playerTurn].getZombie()){
                roll.remove(roll.size() - 1);
                roll.remove(roll.size() -2);
            }
        }
        for(int i = 0; i < rolls;    i++){  
           numDyn = 0;
           this.rollNumber = i+1;
            chooseReroll = false;
            System.out.println("Roll " + (i + 1));
            roll = diceroll.rollDice(roll);
                        for(Die d: roll){
                System.out.print(d.getFace() + " ");
            }   
            System.out.println();
        if(players[playerTurn].isUser())
        gameBoard.rollDice.setText("Roll Dice");
        while(!this.gameBoard.nextRoll){
         System.out.print("");
        }
        this.gameBoard.nextRoll = false;
        if(players[playerTurn].isUser())
        this.gameBoard.rollDice.setText("Select Dice");
            /*
            This for loop resolves arrows and dynamite, as they are resolved while
                the player is still rolling
            */
            for(int rollIterator = 0; rollIterator < roll.size() || numDyn >= allDynamite.length; rollIterator++){
                String currFace = roll.get(rollIterator).getFace().toLowerCase();
                if(currFace.equalsIgnoreCase("arrow") && !players[playerTurn].getCharacter().getName().equalsIgnoreCase("bill_noface")){
                    if( chiefArrow == true && this.expansions ){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                            int out =showPopUp();
                            if(out==0)
                                ability = true;
                        }
                        else{
                            ability = ((Comp)players[playerTurn]).abilityCheck("chief");
                        }
                        /*ask user if he wants to take chief arrow if its in the middle*/
                        if(ability)
                            players[playerTurn].setChiefArrow(true);
                    }else if(players[playerTurn].getCharacter().getName().equalsIgnoreCase("apache_kid") && !players[playerTurn].hasChiefArrow() && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                            int out =showPopUp();
                            if(out==0)
                                ability = true;
                        }
                        else{
                            ability = ((Comp)players[playerTurn]).abilityCheck("chief");
                        }
                        
                        if(ability){ //if apache_kid does want to take Indian Cheif's Arrow from another layer
                            for(int k = 0; k < numPlayers; k++){
                                if(players[k].hasChiefArrow()){
                                    players[k].setChiefArrow(false);
                                    players[playerTurn].setChiefArrow(true);
                                }
                            }
                        }
                        
                    }
                    else{
                        int a = players[playerTurn].getArrows();
                        a++;
                        players[playerTurn].setArrows(a);
                        middleArrows--;
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
                else if(currFace.equalsIgnoreCase("dynamite") && roll.get(rollIterator).getReroll() == true){
                    Die d = roll.get(rollIterator);
                    d.setReroll(false);
                    d.setChooseRoll(false);
                    allDynamite[numDyn] = d;
                    numDyn++;

                }  
                if(numDyn >= 3){
                    this.gameBoard.dynamite();
                    gameBoard.rollDice.setText("Continue");
                    return;
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
            if(middleArrows <= 0){
                indianAttack();
            }
            if(numDyn < 3 && i+1 != rolls && players[playerTurn].isUser()){
                if(players[playerTurn].isUser()){
                    chooseReroll = true;
                    JOptionPane.showMessageDialog(null, "Select What to Reroll!");
                }
                while(!this.gameBoard.nextRoll){    
                    System.out.print("");
                }
                if(gameBoard.firstDie.isVisible()){
                    roll.get(0).setChooseRoll(false);
                }
                if(gameBoard.secondDie.isVisible()){
                    roll.get(1).setChooseRoll(false);
                }
                if(gameBoard.thirdDie.isVisible()){
                    roll.get(2).setChooseRoll(false);
                }
                if(gameBoard.fourthDie.isVisible()){
                    roll.get(3).setChooseRoll(false);
                }
                if(gameBoard.fifthDie.isVisible()){
                    roll.get(4).setChooseRoll(false);
                }
                int rerollCount = 0;
                for(Die di: roll){
                    if(!di.getChooseRoll()){
                        rerollCount++;
                    }
                }
                if(rerollCount == 5){
                    rollAgain = false;
                }
            }
            this.gameBoard.nextRoll = false;
            if(!rollAgain  || i+1 == rolls || numDyn >= 3){
                gameBoard.rollDice.setText("Continue");
                //Belle Star Ability
                if(numDyn <3 && numDyn > 0 && players[playerTurn].getCharacter().getName().equalsIgnoreCase("belle_star") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster()))
                {
                    boolean ability = false;
                    if(players[playerTurn].isUser()){
                            int out =showPopUp();
                            if(out==0)
                            ability = true;
                    }
                    else{
                        ability = ((Comp)players[playerTurn]).abilityCheck("belle");
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
            //this.gameBoard.dynamite();
            players[playerTurn].setHealth(players[playerTurn].getCharacter().loseLifePoints(1));
            return;
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
                    if(players[playerTurn].isUser()){
                            int out =showPopUp();
                            if(out==0)
                            ability = true;
                    }
                    else{
                        ability = ((Comp)players[playerTurn]).abilityCheck("slab");
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
                            int out = showPopUp();
                            if(out==0)
                                ability = true;
                        }
                        else{
                            ability = ((Comp)players[playerTurn]).abilityCheck("calamity1");
                        }
                        if(ability){
                            spacesFromPlayer = 2;
                        }
                    }
                    if(playerChar.getName().equalsIgnoreCase("rose_doolan") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                            int out =showPopUp();
                            if(out==0)
                                ability = true;
                        }
                        else{
                            ability = ((Comp)players[playerTurn]).abilityCheck("rose");
                        }
                        if(ability){
                            spacesFromPlayer++;
                        }
                    }
                    /*player chooses spacesFromPlayer spaces away*/
                    int targetPlayer = (playerTurn + spacesFromPlayer) % numPlayers;
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
                        int out =showPopUp();
                            if(out==0)
                                ability = true;
                        }
                        else{
                            ability = ((Comp)players[playerTurn]).abilityCheck("calamity2");
                        }
                        if(ability){
                            spacesFromPlayer = 1;
                        }
                    }
                    if(playerChar.getName().equalsIgnoreCase("rose_doolan") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
                        boolean ability = false;
                        if(players[playerTurn].isUser()){
                        int out =showPopUp();
                            if(out==0)
                                ability = true;
                        }
                        else{
                            ability = ((Comp)players[playerTurn]).abilityCheck("rose");
                        }
                        if(ability){
                            spacesFromPlayer++;
                        }
                    }
                    /*player chooses spacesFromPlayer spaces away*/
                    int targetPlayer = (playerTurn + spacesFromPlayer) % numPlayers;

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
                            int out =showPopUp();
                            if(out==0)
                            ability = true;
                    }
                    else{
                        ability = ((Comp)players[playerTurn]).abilityCheck("kit");
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
        for(int i = 0; i < numDuels && this.expansions; i++){
            //player chooses which player to target
            int indexTarget = 0;
            duels(players[playerTurn], players[indexTarget], duelTemp);
        }
        
        
        //turn resolution 
        //check if any players have 0 or less life points
        if(lafayette){
            for(Player p: players){
                if(p.getCharacter().getName().equalsIgnoreCase("suzy_lafayette")){
                    p.setHealth(p.getCharacter().gainLifePoints(2));
                }
            }
        }
        for(Player p: players){
            if(p.getHealth() <= 0){
                p.setStatus(false);
                this.alivePlayers--;
                this.middleArrows = p.getArrows();
                p.setArrows(0);
                for(int i = 0; i < players.length; i++){
                    if(players[i].getStatus() && players[i].getCharacter().getName().equalsIgnoreCase("vulture_sam")){
                        players[i].setHealth(players[i].getCharacter().gainLifePoints(2));
                    }
                }
            }
        }
        
    }

   
   /**
    * Called by turn if any player loses life
    * @param attacker the person who is attacking, primarily the Player whose turn it is
    * @param target  the person who is the target chosen by Player
    */
   public void loseLife(Player attacker, Player target){
       int loss = 1;
       if(target.getCharacter().getName().equalsIgnoreCase("bart_cassidy") && (players[playerTurn].getStatus() || players[playerTurn].getZombieMaster())){
             boolean ability = false;
            if(players[playerTurn].isUser()){
            int out =showPopUp();
            if(out==0)
                ability = true;
            }
            else{
                ability = true;
            }
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
            if(players[playerTurn].isUser()){
            int out =showPopUp();
            if(out==0)
                ability = true;
            }
            else{
                ability = true;
            }
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

   /**
    * Whenever middleArrows == 0, and Indian Attack occurs, and everyone loses 1 hp
    */
   public void indianAttack(){
       System.out.println("INDIAN ATTACK");
       JOptionPane.showMessageDialog(null, "Indian Attack!!!");
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
               players[i].setArrows(i);
               players[i].setHealth(players[i].getCharacter().loseLifePoints(1));
               continue;
           }
           else{
              int numArr = players[i].getArrows();
              players[i].setHealth(players[i].getCharacter().loseLifePoints(numArr)); 
              players[i].setArrows(0);
           }
       }
       this.middleArrows = 9;
   }
   
   /**
    * Expansion 2 - Runs the process when a duel face was shown.
    * @param attacker - the player whose turn it is
    * @param target - the player chosen by the current turn player
    * @param duel  - a die with duel faces
    */
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
   

   /**
    * Expansion 2 Undead or Alive. Whenever there are more zombies than alive players
    * this method is called. We set the dead Renegade to be a zombie master (has all abilities) 
    * and the other dead players as zombies. Give them their respective health and stats.
    */
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

   /**
    * Sends to the GUI the dice Roll
    * @param roll List of dice
    */
   public void writeDiceRoll(List<Die> roll){
       //displays the dice roll to all player
       
   }
   
   /**
    * Sends to the GUI the list of players
    */
   public void writePlayers(){
       
   }
 
   /**
    * returns the array of players from Game
    * @return Player[] the array of all players in the game
    */
   public Player[] getPlayers(){
       Player[] tempPlayer = this.players;
       return tempPlayer;
   }
   
   /**
    * Calls the MasterRole class to give all players a role
    * @return Player[] the Array of all players in the game
    */
   public Player[] setRoles(){
       MasterRole mr = new MasterRole(numPlayers);
       mr.assignRole(players);
       return players;
   }
   
   
   /**
    * Assembles the player in a circle
    * @param players
    * @return CyclicDoublyLinkedList
    * 
    */
    public CyclicDoublyLinkedList createPlayerSeating(Player[] players){
        CyclicDoublyLinkedList<Player> ll = new CyclicDoublyLinkedList<>();
        for(Player player: players){
            ll.insert(player);
        }
        return ll;
    }
    
    /**
     * Returns the number/indexes of player you can attack
     * @param distance how far from current player
     * @param curPlayerNumber index of current player
     * @return Integer[] an array of intergers with all places viable for attacking
     */
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
    
    /**
     * Displays to the user if they want their ability to be used
     * @return integer of they choose yes (1) or no (0)
     */
    public int showPopUp(){
        ImageIcon icon = new ImageIcon("src/assets/bangg.PNG");
       int input =JOptionPane.showConfirmDialog(null, "Do want to use your ability?" + players[playerTurn].getCharacter().getSpecialAbility(), "Be honest...",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
      
        return input;
    }
    
   
    public static void main(String [] args){
    
    }
}