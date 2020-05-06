//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.application.Application;
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
public class AbilityDialogBox extends Application{
    
    /**
     * Determines the user choice! 
     */
    public int userChoice;
    Stage window;
    public String message="", title="";

    /**
     * Sets the user choice depending on the button click
     * @param choice The user choice
     */
    public void setUserChoice(int choice){
        this.userChoice = choice;
    }

    /**
     *
     * @param stage The stage to display the dialog box
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle(title);
   
        window.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        window.setTitle(this.title);
        window.setMinWidth(250);
        
        //DIALOG BOX FUNDAMENTAL ATTRIBUTES
        Label messageLabel = new Label(this.message);
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");
        
        //DIALOG BOX BUTTON FUNCTIONALITY
        yesButton.setOnAction(e -> {
            System.out.println("yes");
            window.close();
        });
        
        noButton.setOnAction(e -> {
            System.out.println("no");
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
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        
        launch(args);
    }

    
}
