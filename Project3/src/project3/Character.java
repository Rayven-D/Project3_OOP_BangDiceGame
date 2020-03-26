//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project3;

import java.util.ArrayList;

/**
 *
 * @author shreyesh
 */
public class Character {
    
    private String name;

    private int lifePoints;

    private String specialAbility;

    private static ArrayList<String> all;
    

    public Character(){
        this.name = "";
        this.lifePoints = 0;
        this.specialAbility="";
    }
    
    /**
     *
     * @param name
     * @param lifePoints
     * @param specialAbility
     */
    public Character(String name, int lifePoints, String specialAbility){
        this.name = name;
        this.lifePoints = lifePoints;
        this.specialAbility = specialAbility;
    }
    
    /**
     *
     * @return the name of the character
     */
    public String getName(){
        String tempName = this.name;
        return tempName;
    }

    /**
     *
     * @return the special ability of the character associated with the player
     */
    public String getSpecialAbility(){
        String tempAbility = this.specialAbility;
        return tempAbility;
    }
    
    /**
     *
     * @return The current life points the player has
     */
    public int getLifePoints(){
        int lifePoint  = this.lifePoints;
        return lifePoint;
    }
    
    private void setName(String name){
        this.name = name;
    }
    
    private void setLifePoints(int lifePoints){
        this.lifePoints = lifePoints;
    }
    
    private void setSpecialAbility(String specialAbility){
        this.specialAbility = specialAbility;
    }
    
    /**
     *
     * @param numberOfPoints - The number of life points player loses
     */
    public void loseLifePoints(int numberOfPoints){
        setLifePoints(getLifePoints()-numberOfPoints);
    }
    
    
    
    
}
