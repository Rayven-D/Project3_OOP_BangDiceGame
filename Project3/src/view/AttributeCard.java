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
public class AttributeCard {
    
    public static VBox display(String label, String role){
        //Components
        Label roleLabel = new Label(label);
        Label userRole = new Label(role);
        
        //Layouts
        VBox card = new VBox();
        card.getChildren().addAll(roleLabel, userRole);
        
        return card;
    }
}
