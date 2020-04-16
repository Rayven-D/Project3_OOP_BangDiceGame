/*
// Jeffrey Tessitore
// CS 2365 - Object-Oriented Programming
// Section 002
// Project 3
 */
package project3;
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
    
    // should we have some sort of "detectMove" method for determining 
    // allies/enemies?
    
}
