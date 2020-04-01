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
import static javafx.application.Application.launch;
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
public class Design extends Application  {

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
        nameLabel.setId("bold-label");
        nameLabel.setStyle("-fx-text-fill: #e8e8e8");
        GridPane.setConstraints(nameLabel, 0, 0);
        
        TextField nameInput = new TextField("Bucky");
        GridPane.setConstraints(nameInput,1,0); //child, Column, row
        
        Label passLabel = new Label("Password");
        GridPane.setConstraints(passLabel, 0, 1);
        
        TextField passInput = new TextField();
        GridPane.setConstraints(passInput, 1,1);
        
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);
        
        Button signUpButton = new Button("Sign Up");
        signUpButton.getStyleClass().add("button-blue");
        GridPane.setConstraints(signUpButton, 1, 3);
        
        
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, signUpButton);
        
        Scene scene = new Scene(grid);
        scene.getStylesheets().add("style/Viper.css");
        window.setScene(scene);
                
        window.show();
        
    }


    }
    
    
    

