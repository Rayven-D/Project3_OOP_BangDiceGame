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
    
    public static final int PADDING_SIZE = 20;
    
    public static VBox display(String type, int curVal, String url){
        // Components
        Label tokenType = new Label(type);
        Button icon = new Button();
        Label tokenVal = new Label(Integer.toString(curVal));
        
        icon.setStyle("-fx-background-color: #ffffff ;-fx-background-image: url("
                + url
                + ");");
        icon.setDisable(true);
        icon.setPrefSize(64, 64);
        
        StackPane align = new StackPane();
        align.getChildren().addAll(icon, tokenVal);
        
        //Layouts
        VBox tokenCard = new VBox(PADDING_SIZE);
        tokenCard.getChildren().addAll(tokenType, align);
        
        return tokenCard;
    }

}
