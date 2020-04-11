//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author shreyesh
 */
public class Player {
    
    public static final int PADDING_SIZE = 20;
    
    //Create a constructor that takes in a Player object and create the necessary labels
    
    public Group display(int width, int height){
        // Components
        Group group = new Group();
        Rectangle card = new Rectangle(width, height);
        card.setFill(Color.TRANSPARENT);
        card.setStroke(Color.BLACK);
        card.setArcHeight(10.0d); 
        card.setArcWidth(10.0d); 
        group.getChildren().add(card);
        
        
        return group;
    }

}
