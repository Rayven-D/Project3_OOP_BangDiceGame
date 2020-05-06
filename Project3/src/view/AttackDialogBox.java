//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author shreyesh
 */
public class AttackDialogBox extends ConfirmDialogBox {
    
    //The option that user choices after the dialog box is opened
    private int userChoice;
    
    /**
     * An enum to assign where the user wants to attack
     */
    public static enum Attack {
        LEFT, 
        RIGHT;
    }
    
    /**
     *
     * @param message The message to be displayed in the dialog box
     *
     * @param title The title to be displayed with the dialog box
     */
    public AttackDialogBox(String message, String title){
        super(message, title); 
    }
    
    /**
     *
     * @return An integer representing the userChoice
     */
    public int display(){
        //STAGING THE DIALOG BOX
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        window.setTitle(this.title);
        window.setMinWidth(250);
        
        //DIALOG BOX PROPERTIES
        Label messageLabel = new Label(this.message);
        Button attackLeft = new Button("Attack Left");
        Button attackRight = new Button("Attack Right");
        
        //DIALOG BOX BUTTON FUNCTIONALITY
        attackLeft.setOnAction(e -> {
            userChoice = Attack.LEFT.ordinal();
            window.close();
        });
        
        attackRight.setOnAction(e -> {
            userChoice = Attack.RIGHT.ordinal();
            window.close();
        });
        
        //DIALOG BOX LAYOUT
        VBox confirmBoxLayout = new VBox(10);
        HBox optionsLayout = new HBox(50);
        optionsLayout.getChildren().addAll(attackLeft, attackRight);
        optionsLayout.setAlignment(Pos.CENTER);
        
        confirmBoxLayout.getChildren().addAll(messageLabel, optionsLayout);
        confirmBoxLayout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(confirmBoxLayout);
        window.setScene(scene);
        window.showAndWait(); 
        
        return userChoice;
    }
}
