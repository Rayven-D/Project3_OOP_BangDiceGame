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
    
//    enum Name{
//     BART_CASSIDY, BLACK_JACK, CALAMITY_JANET, EL_GRINGO, JESSE_JONES, JOURDONNAIS, KIT_CARLSON, LUCKY_DUKE, 
//     PAUL_REGRET, PEDRO_RAMIREZ, ROSE_DOOLAN, SID_KETCHUM, SLAB_THE_KILLER, SUZY_LAFAYETTE, VULTURE_SAM, WILLY_THE_KID
//    };
//    
    
    private String name;

    private int lifePoints;

    private String specialAbility;

    private static ArrayList<String> all;
    
    /**
     *
     */
    public static int lifePointsInTheBeginning; 
    
    /**
     *
     */
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
    public Character(String name, String specialAbility, int lifePoints){
        this.name = name;
        this.lifePoints = lifePoints;
        lifePointsInTheBeginning = lifePoints;
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
     * @return  life points if its valid. -1 if it is not valid. 
     */
    public int loseLifePoints(int numberOfPoints){
        int points= getLifePoints()-numberOfPoints;
        if(points>=0){
            setLifePoints(points);
            return points;
        }
        else {
            return -1;
        }
        
    }
    
    /**
     *
     * @param numberOfPoints - The number of life points player gains
     * @return life points if its valid. -1 if it is not valid. 
     */
    public int gainLifePoints(int numberOfPoints){
        int points= getLifePoints()+numberOfPoints;
        if(points<=lifePointsInTheBeginning){
            setLifePoints(points);
            return points;
        }
        else 
        {
            return -1;
        }
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        
    }
    
    
}
