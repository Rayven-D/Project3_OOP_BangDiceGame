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
public class DropdownDialogBox extends ConfirmDialogBox{
    
    private int userChoice; //The option that the user chooses
    
    /**
     * Class constructor
     * @param message The message to be displayed within the DropdownDialogBox
     * @param title The title associated with the DropdownDialogBox
     */
    public DropdownDialogBox(String message, String title) {
        super(message, title);
    }
    
    /**
     *
     * @return An integer representing the number of players the user wants in the game
     */
    @Override
        public int display(){
            //DIALOG BOX STAGE SET UP
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
            window.setTitle(title);
            window.setMinWidth(750);
            
            //DIALOG BOX ATTRIBUTES
            Label messageLabel = new Label(message);
            Button proceed = new Button("Proceed");
            ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll(4,5,6,7,8);
            
            //DIALOG BOX BUTTON FUNCTIONALITY
            proceed.setOnAction(e -> {
                userChoice = getChoice(choiceBox);
                window.close();
            });      
            
            //DIALOG BOX LAYOUT
            VBox confirmBoxLayout = new VBox(10);
            HBox optionsLayout = new HBox(50);
            optionsLayout.getChildren().addAll(proceed);
            optionsLayout.setAlignment(Pos.CENTER);

            confirmBoxLayout.getChildren().addAll(messageLabel, choiceBox, optionsLayout);
            confirmBoxLayout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(confirmBoxLayout);
            window.setScene(scene);
            window.showAndWait(); 

            return userChoice;
        }
       

        private int getChoice(ChoiceBox<Integer> choiceBox){
            return choiceBox.getValue();
        }
        
    /**
     * Test Function
     * @param args
     */
    public static void main(String[] args){
            DropdownDialogBox db = new DropdownDialogBox("ya", "ya");
            db.display();
            
        }
}
