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
    
    public String name;
    public int lifePoints;
    public String specialAbility;
    public static ArrayList<String> all;
    
    public Character(){
        this.name = "";
        this.lifePoints = 0;
        this.specialAbility="";
    }
    
    public Character(String name, int lifePoints, String specialAbility){
        this.name = name;
        this.lifePoints = lifePoints;
        this.specialAbility = specialAbility;
        
    }
}
