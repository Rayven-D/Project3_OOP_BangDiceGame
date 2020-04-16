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
    
    private LinkedList keptDice;
    
    public User(int playerNum) {
        super(playerNum,true);
        keptDice = new LinkedList();
    }
    
    public void move() {
        // insert stuff
        RollDice rDice = new RollDice();
        LinkedList dice = rDice.getDice();
        promptDice(dice);
        promptTarget();
    }
    
    private LinkedList promptDice(LinkedList dice) {
        return null;
    }
    
    private int promptTarget() {
        return 0;
    }
    
}
