/**
 * CS 2365, Sec 1, Spring 2020
 * Mallory Rasco
 * Project 3 - Bang! Dice Game
 */

package project3;

import java.util.*;

/**
 *
 * @author Mallory Rasco
 * 
 * To make and keep track of all the players Roles in the Game
 *      including when a player has successfully won
 */
public class MasterRole {
    
    /**
     * Creates possible roles based on number of players in game
     */
    private final ArrayList<String> roles = new ArrayList<>();
    
    /**
     * holds index of the winner of the game
     */
    private int winner;
    
    /**
     * class constructor
     * @param players : number of players in game
     */
    public MasterRole(int players)
    {
        makeRoles(players);
        this.winner = -1;
    }
    
    /**
     * makes an ArrayList of the possible roles based on the number of players
     * @param numPlayers : number of players in game
     */
    private void makeRoles(int numPlayers)
    {
        if(numPlayers >= 4 && numPlayers <= 8)
        {
            roles.add("Sheriff");
            roles.add("Renegade");
            roles.add("Outlaw");
            roles.add("Outlaw");
            
            numPlayers -= 4;
            
            if(numPlayers > 0)
            {
                roles.add("Deputy");
                numPlayers--;
                
                if(numPlayers > 0)
                {
                    roles.add("Outlaw");
                    numPlayers--;
                    
                    if(numPlayers > 0)
                    {
                        roles.add("Deputy");
                        numPlayers--;
                        
                        if(numPlayers > 0)
                        {
                            roles.add("Renegade");
                        }
                    }
                }
            }
        }
    }
    
    /**
     * gives a player their role from the ArrayList
     * @param player " player to assign role to
     */
    public void assignRole(Player player)
    {
        player.setRole(new Role(roles));
    }
    
    /**
     * gives all players their roles from ArrayList
     * @param players : all players in the game in Array
     */
    public void assignRole(Player[] players)
    {
        for(int i=0; i < players.length; i++)
        {
            players[i].setRole(new Role(roles));
        }
    }
    
    /**
     * finds the winner of the game
     * @param players : all the players in the game
     * @return : the index of the winner or -1 if no one has won yet
     */
    public int whoWon(Player[] players)
    {
        int i=0;
        
        while(this.winner == -1 && i<players.length)
        {
            if(players[i].getRole().getWon(players) == true)
            {
                winner = i;
            }
            
            i++;
        }
        return winner;
    }
    
    
}
