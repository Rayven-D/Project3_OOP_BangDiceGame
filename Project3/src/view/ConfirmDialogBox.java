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


/**
 *
 * @author shreyesh
 */
public class ConfirmDialogBox {
        
    /**
     * The message to be displayed in the Dialog Box
     */
    public String message;

    /**
     * The title to be displayed alongside the message in the Dialog Box
     */
    public String title;
    private boolean userChoice = false; 
    
    /**
     * Class Constructor
     * @param message The message to be displayed within the Dialog Box
     * @param title THe title to be displayed in the Dialog Box
     */
    public ConfirmDialogBox(String message, String title){
        this.message = message;
        this.title = title;
    }
    
    /**
     *
     * @return An integer (0 or 1) indicating the userChoice
     */
    public int display(){
        
        //DIALOG BOX STAGE SET UP
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        window.setTitle(this.title);
        window.setMinWidth(250);
        
        //DIALOG BOX ATTRIBUTES
        Label messageLabel = new Label(this.message);
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");
        
        //DIALOG BOX BUTTON FUNCTIONALITY
        yesButton.setOnAction(e -> {
            setUserChoice(true);
            window.close();
        });
        
        noButton.setOnAction(e -> {
            setUserChoice(false);
            window.close();
        });
        
        //DIALOG BOX LAYOUT
        VBox confirmBoxLayout = new VBox(10);
        HBox optionsLayout = new HBox(50);
        optionsLayout.getChildren().addAll(yesButton, noButton);
        optionsLayout.setAlignment(Pos.CENTER);
        
        confirmBoxLayout.getChildren().addAll(messageLabel, optionsLayout);
        confirmBoxLayout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(confirmBoxLayout);
        window.setScene(scene);
        window.showAndWait(); 
        
        return this.userChoice?1:0;
    }
    
    /**
     *
     * @param choice The option that the user chooses in the Dialog Box
     * @return A pointer to the userChoice variable
     */
    public boolean setUserChoice(boolean choice){
         return this.userChoice = choice;
    }
    
    
    
    
}
