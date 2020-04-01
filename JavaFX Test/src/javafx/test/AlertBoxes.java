//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafx.test;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 *
 * @author shreyesh
 */
public class AlertBoxes {
    
    public static void display(String title, String message){
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label label = new Label(message);
        label.setText(message);
        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(e->{
            window.close();
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait(); // Display the window and before it returns it needs to be closed
    }

}
