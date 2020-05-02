/*
// Jeffrey Tessitore
// CS 2365 - Object-Oriented Programming
// Section 002
// Project 3
 */
package project3.project3_OOP;
import java.util.*;

/**
 *
 * @author parra
 */
public class Comp extends Player implements CompBrain {
    
    /**
     * an ArrayList made up of Integer types containing which other players
     * the computer considers an ally. Used in determining moves
     */
    private ArrayList<Integer> allies;
    
    /**
     * an ArrayList made up of Integer types containing which other players
     * the computer considers an enemy. Used in determining moves
     */
    private ArrayList<Integer> enemies;
    
    /**
     * an ArrayList made up of Integer types containing which other players
     * the computer considers as neutral. Used in determining moves if no 
     * allies or enemies are available.
     */
    private ArrayList<Integer> neutral;
    
    /***
     * 
     * @param playerNum - an integer containing the player number
     */
    public Comp(int playerNum) {
        super(playerNum,false);
        allies = new ArrayList<Integer>();
        enemies = new ArrayList<Integer>();
        neutral = new ArrayList<Integer>();
    }
    
    /***
     * Determines if the player given by playerNum is an ally by doing a linear
     * search through the allies ArrayList
     * @param playerNum - the integer containing the player number
     * @return - a boolean containing if said player is an ally
     */
    @Override
    public boolean detAlly(int playerNum) {
        // working on this with group
        return false;
    }
    
    /***
     * Finds the best move for this Computer Player
     */
    @Override
    public void findBestMove() {
        // working on this with group
    }
    
    /***
     * Finds the target for this Computer Player
     * @return - an integer containing the player to target
     */
    @Override
    public int findTarget() {
        // working on this with group
        return 0;
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
            if(die.getFace() == "Gatling"){
                numGat++;
            }
            else if(die.getFace() == "Dynamite"){
                numDyn++;
            }
            if(die.getFace() == "Arrow"){
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
                if(die.getFace() == "One" || die.getFace() == "Two"){
                    die.setChooseRoll(true);
                }
            }
        }
        else{
            for(Die die: dice){
                if(die.getFace() == "Beer"){
                    die.setChooseRoll(true);
                }
            }
        }
                
        //always reroll a bullet (loudmouth Die)
        for(Die die: dice){
            if(die.getFace() == "Bullet"){
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
                if(die.getFace() == "Whiskey")
                    die.setChooseRoll(true);
            }
        }
        
        //the computer will like to duel, but won't risk it if low health
        if(health < 3){
            for(Die die: dice){
                if(die.getFace() == "Whiskey"){
                    die.setChooseRoll(true);
                }
            }
        }
        
        dice = rDice.rollDice(dice);
        
        return dice;
    }
    // should we have some sort of "detectMove" method for determining 
    // allies/enemies?
    
}
