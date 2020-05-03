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
public class User extends Player {
    
    private List keptDice;
    
    public User(int playerNum) {
        super(playerNum,true);
        keptDice = new ArrayList<Die>();
    }
    
    public void move() {
        // insert stuff
        RollDice rDice = new RollDice("");
        List dice = rDice.getDice();
        promptDice(dice);
        promptTarget();
    }
    
    private LinkedList promptDice(List dice) {
        return null;
    }
    
    private int promptTarget() {
        return 0;
    }
    
}
