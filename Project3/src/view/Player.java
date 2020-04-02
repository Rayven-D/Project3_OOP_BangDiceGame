//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author shreyesh
 */
public class Player {
    
    public static final int PADDING_SIZE = 20;
    
    public static StackPane display(int width, int height){
        // Components
        StackPane playerCard = new StackPane();
        
        Rectangle card = new Rectangle(width, height);
        card.setFill(Color.DARKGRAY);
        card.setArcHeight(10.0d); 
        card.setArcWidth(10.0d); 
        playerCard.getChildren().add(card);
        
        
        return playerCard;
    }

}
