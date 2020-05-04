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
    
    private int userChoice;
    
    public DropdownDialogBox(String message, String title) {
        super(message, title);
    }
    
    /**
     *
     * @return
     */
    @Override
        public int display(){
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
            window.setTitle(title);
            window.setMinWidth(750);

            Label messageLabel = new Label(message);
            Button proceed = new Button("Proceed");
            
            ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
            
            choiceBox.getItems().addAll(3,4,5,6,7,8);
            
            proceed.setOnAction(e -> {
                userChoice = getChoice(choiceBox);
                window.close();
            });      

            VBox confirmBoxLayout = new VBox(10);
            HBox optionsLayout = new HBox(50);
            optionsLayout.getChildren().addAll(proceed);
            optionsLayout.setAlignment(Pos.CENTER);

            confirmBoxLayout.getChildren().addAll(messageLabel, choiceBox, optionsLayout);
            confirmBoxLayout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(confirmBoxLayout);
            window.setScene(scene);
            window.showAndWait(); // Display the window and before it returns it needs to be closed

            return userChoice;
        }
       

        private int getChoice(ChoiceBox<Integer> choiceBox){
            return choiceBox.getValue();
        }
        
        public static void main(String[] args){
            DropdownDialogBox db = new DropdownDialogBox("ya", "ya");
            db.display();
            
        }
}
