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
    public Game game;
    
    /**
     * Ask the user if they want the extensions included
     */
    
    /**
     *
     * @param user
     */
    public BoardViewController(){
        //game = new Game();
        
        board = new Board();
    }
    
    /**
     *
     */
    public void initUser(){
     
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
        
        BoardViewController vc = new BoardViewController();
    }

}
