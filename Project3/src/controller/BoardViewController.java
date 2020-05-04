//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javafx.stage.Stage;
import project3.Player;
import view.Board;


/**
 *
 * @author shreyesh
 */
public class BoardViewController {
    
    //User attributes
    private String role, character;
    private int lifePoints, arrows, numPlayers;
    public Board board;
    
    public void getNumberOfPlayers(){
        DropdownDialogBox dropdown = new DropdownDialogBox("Select the number of players you want in the game", "How many friends you got ?");
        
    }
    
    public BoardViewController(Player player){
        this.role = player.getRole().getName();
        this.character = player.getCharacter().getName();
        this.lifePoints = player.getHealth();
        this.arrows = player.getArrows();
        this.initPlayer();
  
    }
    
    public void initPlayer(){
    Board.lifePoints = lifePoints;
    Board.userCharacter = character;
    Board.userRole = role;
    Board.numberOfArrows = arrows;  
    }
    
    public static void main(String[] args){
        Board.main(args);
    }

}
