//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author shreyesh
 */
public class Token {
    
    public static VBox display(String type, int curVal){
        // Components
        Label tokenType = new Label(type);
        Label tokenVal = new Label(Integer.toString(curVal));
        
        
        //Layouts
        VBox tokenCard = new VBox(40);
        tokenCard.getChildren().addAll(tokenType, tokenVal);
        
        return tokenCard;
    }

}
