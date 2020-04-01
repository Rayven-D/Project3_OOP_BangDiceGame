/*
*   CS 2365 OOP Spring 2020
*   Shreyesh Arangath 
*   shreyesh.arangath@ttu.edu
*/


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafx.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author shreyesh
 */
public class Grid extends Application  {

   Button button;
   Stage window;
    
    public static void main(String[] args) {
       launch(args); //Set up the program as JavaFX application
    }
    
    

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Title of the Window"); //Sets the title of the window 

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        Label nameLabel = new Label("Username");
        GridPane.setConstraints(nameLabel, 0, 0);
        
        TextField nameInput = new TextField("Bucky");
        GridPane.setConstraints(nameInput,1,0); //child, Column, row
        
        Label passLabel = new Label("Password");
        GridPane.setConstraints(nameLabel, 0, 1);
        
        TextField passInput = new TextField();
        GridPane.setConstraints(passInput, 1,1);
        
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);
        
        Scene scene = new Scene(grid);
        window.setScene(scene);
                
        window.show();
        
    }


    }
    
    
    

