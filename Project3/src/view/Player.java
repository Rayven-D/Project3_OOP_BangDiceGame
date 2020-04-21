//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author shreyesh
 */
public class Player {
    
    public static final int PADDING_SIZE = 20;
    private String playerName;
    private int oneBullet;
    private int threeBullet;
    
    //Create a constructor that takes in a Player object and create the necessary labels
    
    public Player(String playerName, int oneBullet, int threeBullet){
        this.playerName = playerName;
        this.oneBullet = oneBullet;
        this.threeBullet = threeBullet;
    }
    
    public StackPane display(int width, int height){
        // Components
        StackPane group = new StackPane();
        Rectangle card = new Rectangle(width, height);
        Label player = new Label(playerName);
        card.setFill(Color.BLACK);
        card.setStroke(Color.BLACK);
        card.setArcHeight(10.0d); 
        card.setArcWidth(10.0d); 
        group.getChildren().addAll(card,player);
        
        
        
        
        return group;
    }

}
