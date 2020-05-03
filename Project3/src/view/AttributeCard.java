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
    
    public enum Role {
        DEPUTY("assets/Deputy.png"),
        RENEGADE("assets/Renegade.png"),
        OUTLAW("assets/Outlaw.png"),
        SHERIFF("assets/Sheriff.png");
        
        public String url;
        
        Role(String url){
            this.url = url;
        }
        public static String getImage(String role){
            switch(role.toLowerCase()){
                case "deputy":
                    return Role.DEPUTY.url;
                case "outlaw": 
                    return Role.OUTLAW.url;
                case "sherrif":
                    return Role.SHERIFF.url;
                case "renegade":
                    return Role.RENEGADE.url;
                default:
                    return Role.RENEGADE.url;
            }
        }
    }
        
    
    public static VBox display(String label, String role){
        //Components
        Label roleLabel = new Label(label);
        Button icon = new Button();
        Label userRole = new Label(role);
        
        if(label.equals("Role")){
            String url = Role.getImage(role);
            icon.setPrefSize(163, 228);

            icon.setStyle( "-fx-background-image: url("+ url
                + ");"
                + "");
            
        }
            
        //Layouts
        VBox card = new VBox();
        card.getChildren().addAll(roleLabel, icon, userRole);
        
        return card;
    }
}
