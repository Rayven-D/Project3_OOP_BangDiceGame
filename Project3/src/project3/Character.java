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
    
    /**
     * !!!!!!!!!!!MAKE SURE TO INCLUDE THIS WITHIN THE GAME CLASS!!!!!!!!!!
     * enum for all the specific characters within the game
     */
    public enum Characters{
 
        BART_CASSIDY(8), 
        BLACK_JACK(8), 
        CALAMITY_JANET(8), 
        EL_GRINGO(7), 
        JESSE_JONES(9), 
        JOURDONNAIS(7), 
        KIT_CARLSON(7), 
        LUCKY_DUKE(8), 
        PAUL_REGRET(9), 
        PEDRO_RAMIREZ(8), 
        ROSE_DOOLAN(9), 
        SID_KETCHUM(8), 
        SLAB_THE_KILLER(8), 
        SUZY_LAFAYETTE(8), 
        VULTURE_SAM(9), 
        WILLY_THE_KID(8);

        
        public int lifePoints;
    
        Characters(int lifePoints){
            this.lifePoints = lifePoints;
        }
        
       
   }
   
    
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
    public Character(Characters name, String specialAbility){
        this.name = name.toString();
        this.lifePoints = name.lifePoints;
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
    
    public void setName(String name){
        this.name = name;
    }
    
    private void setLifePoints(int lifePoints){
        this.lifePoints = lifePoints;
    }
    
    public void setSpecialAbility(String specialAbility){
        this.specialAbility = specialAbility;
    }
    
    /**
     *
     * @param numberOfPoints - The number of life points player loses
     * @return  0 life points, player is eliminated
     */
    public int loseLifePoints(int numberOfPoints){
        int points= getLifePoints()-numberOfPoints;
        if(points>=0)
        {
            setLifePoints(points);
            return points;
        }
        return 0;
        
    }
    
    /**
     *
     * @param numberOfPoints - The number of life points player gains
     * @return life points that the player started with. 
     */
    public int gainLifePoints(int numberOfPoints){
        int points= getLifePoints()+numberOfPoints;
        if(points<=lifePointsInTheBeginning){
            setLifePoints(points);
            return points;
        }
        return lifePointsInTheBeginning;
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){
  
        Character character = new Character(Characters.BART_CASSIDY, "Take an arrow instead of losing life point");
        System.out.println(character.getName());
        System.out.println(character.getSpecialAbility());
        System.out.println(character.getLifePoints());
        System.out.println(character.loseLifePoints(1));
        System.out.println(character.gainLifePoints(1));
        System.out.println(character.gainLifePoints(1)+". THIS IS AN INVALID PLAY");
        System.out.println(character.getLifePoints()+ " DOES NOT CHANGE");
    }
    
    
}
