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
 * To keep track of a players Role
 *      including their allies and enemies
 */
public class Role {
    
    /**
     * the role for the player
     */
    private String name;
    
    /**
     * an ArrayList of the player's allies in the game
     *      based on their role
     */
    private final ArrayList<String> allies = new ArrayList<>();
    
    /**
     * an ArrayList of the player's enemies in the game
     *      based on their role
     */
    private final ArrayList<String> enemies = new ArrayList<>();
    
    /**
     * whether the player has won the game
     */
    private boolean won;
    
    /**
     * class constructor
     * @param roles : an ArrayList of the possible roles for the player
     */
    public Role(ArrayList<String> roles)
    {
        setName(roles);
        setAllies();
        setEnemies();
        this.won = false;
    }
    
    /**
     * class mutator : give player role based on availability in ArrayList
     * @param roles : roles available to player
     */
    private void setName(ArrayList<String> roles)
    {
        int rand = (int)(Math.random()*(roles.size() - 1));
        
        this.name = roles.get(rand);
        roles.remove(rand);
    }
    
    /**
     * class accessor
     * @return the role of the player
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * class mutator : add allies to ArrayList based on player's role
     */
    private void setAllies()
    {
        if(name.equals("Sheriff") || name.equals("Deputy"))
        {
            allies.add("Deputy");
            
            if(name.equals("Deputy"))
                allies.add("Sheriff");
        }
        else
        {
            if(name.equals("Outlaw"))
            {
                allies.add("Outlaw");
                allies.add("Renegade");
            }
        }
    }
    
    /**
     * class mutator : add enemies to ArrayList based on player's role
     */
    private void setEnemies()
    {
        if(name.equals("Sheriff") || name.equals("Deputy"))
        {
            enemies.add("Outlaw");
            enemies.add("Renegade");
        }
        else
        {
            if(name.equals("Outlaw")|| name.equals("Renegade"))
            {
                enemies.add("Sheriff");
                enemies.add("Deputy");
                
                if(name.equals("Renegade"))
                {
                    enemies.add("Outlaw");
                    enemies.add("Renegade");
                }
            }
        }
    }
    
    /**
     * class accessor
     * @return the ArrayList of allies
     */
    public ArrayList<String> getAllies()
    {
        return allies;
    }
    
    /**
     * class accessor
     * @return the ArrayList of enemies
     */
    public ArrayList<String> getEnemies()
    {
        return enemies;
    }
    
    /**
     * determines whether player has won the game or not
     * @param players : all the players in an Array
     * @return : boolean of whether current player has won or not
     */
    public boolean getWon(Player[] players)
    {
        int i=0;
        boolean losing = false;
        
        switch(this.name)
        {
            case("Sheriff"): case("Deputy"):
                while(losing == false && i < players.length)
                {
                    if(players[i].getRole().getName().equalsIgnoreCase("outlaw") || players[i].getRole().getName().equalsIgnoreCase("renegade")) //Get role has been added temporarily. Please fill. 
                    {
                        if(players[i].getStatus() == true)
                        {
                            losing = true;
                        }
                    }
                    i++;
                }
                break;
            case("Outlaw"):
                while(losing == false && i < players.length)
                {
                    if(players[i].getRole().getName().equalsIgnoreCase("sheriff"))
                    {
                        if(players[i].getStatus() == true)
                        {
                            losing = true;
                        }
                    }
                    i++;
                }
                break;
            case("Renegade"):
                while(losing == false && i < players.length)
                {
                   
                    if(players[i].getStatus() == true && !players[i].getRole().getName().equalsIgnoreCase("renegade"))
                    {
                        losing = true;
                    }
                    i++;
                }
                break;                   
        }
        if(losing == false)
        {
            won = true;
        }
        
        return won;
    }
    
}