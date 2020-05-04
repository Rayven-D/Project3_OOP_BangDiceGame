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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
            window.setMinWidth(250);

            Label messageLabel = new Label(message);
            Button proceed = new Button();
            
            ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
            
            choiceBox.getItems().addAll(3,4,5,6,7,8);
            
            proceed.setOnAction(e -> {
                getChoice(choiceBox);
            });      

            VBox confirmBoxLayout = new VBox(10);
            HBox optionsLayout = new HBox(50);
            optionsLayout.getChildren().addAll(proceed);
            optionsLayout.setAlignment(Pos.CENTER);

            confirmBoxLayout.getChildren().addAll(messageLabel, optionsLayout);
            confirmBoxLayout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(confirmBoxLayout);
            window.setScene(scene);
            window.showAndWait(); // Display the window and before it returns it needs to be closed

            return userChoice;
        }
       

        private int getChoice(ChoiceBox<Integer> choiceBox){
            return choiceBox.getValue();
        }
}
