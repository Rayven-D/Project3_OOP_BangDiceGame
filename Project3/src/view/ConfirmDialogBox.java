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
     *
     */
    public String message;

    /**
     *
     */
    public String title;
    private boolean userChoice = false; 
    
    /**
     *
     * @param message
     * @param title
     */
    public ConfirmDialogBox(String message, String title){
        this.message = message;
        this.title = title;
    }
    
    /**
     *
     * @return
     */
    public int display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        window.setTitle(this.title);
        window.setMinWidth(250);
        
        Label messageLabel = new Label(this.message);
        
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");
        
        yesButton.setOnAction(e -> {
            setUserChoice(true);
            window.close();
        });
        
        noButton.setOnAction(e -> {
            setUserChoice(false);
            window.close();
        });
        
        VBox confirmBoxLayout = new VBox(10);
        HBox optionsLayout = new HBox(50);
        optionsLayout.getChildren().addAll(yesButton, noButton);
        optionsLayout.setAlignment(Pos.CENTER);
        
        confirmBoxLayout.getChildren().addAll(messageLabel, optionsLayout);
        confirmBoxLayout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(confirmBoxLayout);
        window.setScene(scene);
        window.showAndWait(); // Display the window and before it returns it needs to be closed
        
        return this.userChoice?1:0;
    }
    
    /**
     *
     * @param choice
     * @return
     */
    public boolean setUserChoice(boolean choice){
         return this.userChoice = choice;
    }
    
    
    
    
}
