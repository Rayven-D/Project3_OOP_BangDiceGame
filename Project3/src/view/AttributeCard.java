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
import javafx.scene.control.*;


/**
 *
 * @author shreyesh
 */
public class AttributeCard {
    
    /**
     * To help the AttributeCard class get the asset URL for the roles.
     */
    public enum Role {

        DEPUTY("assets/Deputy.png"),
        RENEGADE("assets/Renegade.png"),
        OUTLAW("assets/Outlaw.png"),
        SHERIFF("assets/Sheriff.PNG");

        public String url;
        
        Role(String url){
            this.url = url;
        }

        /**
         *
         * @param role
         * @return
         */
        public static String getImage(String role){
            switch(role.toLowerCase()){
                case "deputy":
                    return Role.DEPUTY.url;
                case "outlaw": 
                    return Role.OUTLAW.url;
                case "sheriff":
                    return Role.SHERIFF.url;
                case "renegade":
                    return Role.RENEGADE.url;
                default:
                    return Role.RENEGADE.url;
            }
        }
    }
        
    /**
     *
     * @param label The name associated with the Attribute
     * @param role The role associated with the Attribute
     * @return
     */
    public static VBox display(String label, String role){
        //ATTRIBUTE BOX COMPONENTS
        Label roleLabel = new Label(label);
        Button icon = new Button();
        Label userRole = new Label(role);
        
        
        // ASSIGNING THE IMAGE VIEW FOR THE ATTRIBUTE
        if(label.equals("Role")){
            String url = Role.getImage(role);
            icon.setPrefSize(163, 228);

            icon.setStyle( "-fx-background-image: url("+ url
                + ");"
                + "");
            
        }
        else if(label.equals("Character")){
            String url = "assets/"+role.replace(' ', '_').toLowerCase()+".png";
            icon.setPrefSize(135, 135);

            icon.setStyle( "-fx-background-image: url("+ url
                + ");"
                + "");
        }
        
             
        // ATTRIBUTE BOX LAYOUT
        VBox card = new VBox();
        card.getChildren().addAll(roleLabel, icon, userRole);
        
        return card;
    }
}
