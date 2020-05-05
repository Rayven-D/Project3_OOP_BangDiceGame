/*
// Jeffrey Tessitore
// CS 2365 - Object-Oriented Programming
// Section 002
// Project 3
 */
package project3;
import java.util.*;
import project3.Die;
import project3.RollDice;

/**
 *
 * @author Jeffrey Tessitore
 */
public class Comp extends Player implements CompBrain {
    
    /**
     * an ArrayList made up of Integer types containing which other players
     * the computer considers an ally. Used in determining moves
     */
    private ArrayList<String> allies;
    
    /***
     * 
     * @param playerNum - an integer containing the player number
     */
    public Comp(int playerNum) {
        super(playerNum,false);
        allies = new ArrayList<String>();
    }
    
    /***
     * @author Jeffrey Tessitore
     * Determines if the player given by role is an ally by doing a linear
     * search through the allies ArrayList
     * @param role - the string containing the role to determine
     * @return - an integer value of -1, 0, or 1 depending on the following:
     *      -1: The role is SHERIFF and this Comp is either an OUTLAW or the last RENEGADE
     *       0: The role is "UNKNOWN" (any other role, which the Comp shouldn't know)
     *       1: The role is SHERIFF and this Comp is a DEPUTY
     */
    @Override
    public int detAlly(String role) {
        if (role.equals("Sheriff")) {
            if (this.getRole().getName().equals("Deputy")) {
                return 1;
            }
            else if (this.getRole().getName().equals("Outlaw")) {
                return -1;
            }
            else {  // this player MUST be a Renegade
                if (playersAlive == 2)
                    return -1;
                else {
                    return 1; // The Renegade will not want to kill the sheriff and end the game if there are too many players
                }
            }
        }
        else
            return 0;
    }
    
    private void getAllyList() {
        ArrayList<String> allylist = this.getRole().getAllies();
        for (String s : allylist) {
            allies.add(s);
        }
    }
    
    /***
     * @author Jeffrey Tessitore
     * Finds the best move for this Computer Player
     * These Computer players aren't very smart and can only determine if a
     * player is an ally or enemy based on if they are the SHERIFF
     * @param pots - potential targets for this comp player
     * @return - The integer containing the player num to target
     */
    @Override
    public int findBestTarget(int[] pots) {
        if (pots[0] == pots[1]) // if the potential targets are equal (only one other player)
            return pots[0];
        int[] isAlly = new int[2];
        for (int i = 0; i < 2; ++i) {
            isAlly[i] = detAlly(players.get(pots[i]).getRole().getName());
        }
        if (isAlly[0] == -1 || isAlly[1] == -1) {
            return (isAlly[0] == -1 ? pots[0] : pots[1]);
        }
        else if (isAlly[0] == 1 || isAlly[1] == 1) {
            return (isAlly[0] == 1 ? pots[1] : pots[0]);
        }
        else {
            int rand = (int)(Math.random()*2); // returns either 0 or 1
            return pots[rand];
        }
    }
    
    /***
     * @author Jeffrey Tessitore
     * Finds the target for this Computer Player
     * @param spaces - The number of spaces away from the current Comp that
     * they may target
     * @return - an integer containing the player to target
     */
    @Override
    public int findTarget(int spaces) {
        getAllyList();
        int[] potTargs = new int[2]; // potential targets
        potTargs[0] = (this.getNum() - spaces < 1) ? (numPlayers + (this.getNum() - spaces)) : (this.getNum() - spaces);
        potTargs[1] = (this.getNum() + spaces > numPlayers) ? (this.getNum() + spaces - numPlayers) : (this.getNum() + spaces);
        return findBestTarget(potTargs);
    }
    
    /***
     *@author Cole Trammell
     * @param incExp1 - boolean that determines if Expansion 1 is included in the game
     * @param incExp2 - boolean that determines if Expansion 2 is included in the game
     * @param health - value of characters health
     * @return RollDice object
     */
    public RollDice compRollDice(boolean incExp1, boolean incExp2, int health){
        RollDice rDice = new RollDice();
        
        rDice.setExp1(incExp1);
        rDice.setExp2(incExp2);
        if(health >= 2){
            rDice.setExp1Die("Coward");
        }
        else{
            rDice.setExp1Die("LoudMouth");
        }
        
        List<Die> dice = new ArrayList<>();
        
        dice = rDice.getDice();
        
        for(Die die: dice){
            die.setChooseRoll(true);
            die.setReroll(true);
        }
        rDice.rollDice(dice);
        
        return rDice;
    }
    
    /***
     * @author Cole Trammell
     *  This handles how the computer will choose to reroll the dice, most of the
     * strategy chosen in general is a good strategy, but it is very selfish, as
     * the choices only concern the stats of the comp choosing what to reroll
     * @param rDice - A RollDice Object
     * @param health -  the value of the characters health
     * @return Dice, an ArrayList of Die objects that have be rerolled
     */
    public List<Die> compReroll(RollDice rDice, int health){
        int numDyn = 0;
        int numGat = 0;
        
        List<Die> dice = new ArrayList<>();
        
        boolean incExp1 = rDice.getExp1();
        boolean incExp2 = rDice.getExp2();
        
        for(Die die: dice){
            if("Gatling".equals(die.getFace())){
                numGat++;
            }
            else if("Dynamite".equals(die.getFace())){
                numDyn++;
            }
            if("Arrow".equals(die.getFace())){
                die.setChooseRoll(true);
            }
        }
        
        //if 2 or more of dynamite are in the roll, then stop rerolling
        //so it doesn't get worse
        if(numDyn >= 2){
            return dice;
        }
        
        //keep gatling guns if there is at least 2
        if(numGat < 2){
                for(Die die: dice){
                    if(die.getFace() == "Gatling"){
                        die.setChooseRoll(true);
                    }
                }
        }
        
        //keep beer and reroll shots if low health, otherwise keep shots and reroll beer
        if(health <= 4){
            for(Die die: dice){
                if("One".equals(die.getFace()) || "Two".equals(die.getFace())){
                    die.setChooseRoll(true);
                }
            }
        }
        else{
            for(Die die: dice){
                if("Beer".equals(die.getFace())){
                    die.setChooseRoll(true);
                }
            }
        }
                
        //always reroll a bullet (loudmouth Die)
        for(Die die: dice){
            if("Bullet".equals(die.getFace())){
                die.setChooseRoll(true);
            }
        }
        
        //the comp will only roll a coward when low health, so always keep a double beer
    
        //though it's not the best move, it won't hurt either for the comp to always
        //keep the broken arrow especially since it is only one face on one die that
        //is only rolled when low health
        
        //comp will keep whiskey if only missing a couple points of health
        if(health > 6){
            for(Die die: dice){
                if("Whiskey".equals(die.getFace()))
                    die.setChooseRoll(true);
            }
        }
        
        //the computer will like to duel, but won't risk it if low health
        if(health < 3){
            for(Die die: dice){
                if("Whiskey".equals(die.getFace())){
                    die.setChooseRoll(true);
                }
            }
        }
        
        dice = rDice.rollDice(dice);
        
        return dice;
    }
  
}
    // should we have some sort of "detectMove" method for determining 
    // allies/enemies?
  