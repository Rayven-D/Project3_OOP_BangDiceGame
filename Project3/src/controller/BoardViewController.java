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
import project3.Character;
import project3.Game;
import view.Board;
import view.ConfirmDialogBox;
import view.DropdownDialogBox;


/**
 *
 * @author shreyesh
 */
public class BoardViewController {
    
    //User attributes
    private String role, character;
    private int lifePoints, arrows, numPlayers, wantExtension;

    /**
     *
     */
    public Board board;
    
    /**
     * Ask the user if they want the extensions included
     */
    
    /**
     *
     * @param user
     */
    public BoardViewController(){
        
    }
    
    /**
     *
     */
    public void initUser(){
        Board.lifePoints = lifePoints;
        Board.userCharacter = character;
        Board.userRole = role;
        Board.numberOfArrows = arrows;  
    }
    
    public int getNumPlayers(){
        return this.numPlayers;
    }
    
    public int getWantExtensions(){
        return this.wantExtension;
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){   
//        BoardViewController vc = new BoardViewController();
//        vc.getNumberOfPlayers();
//        vc.wantExtensionsIncluded();
//        Game game = new Game(vc.getNumPlayers(), 1);
        Board.main(args);
    }

}
