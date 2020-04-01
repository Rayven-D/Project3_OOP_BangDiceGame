//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafx.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author shreyesh
 */
public class SceneChange extends Application {
    
    Stage window;
    Scene scene1, scene2;
    
    public static void main(String[] args) {
       launch(args); //Set up the program as JavaFX application
    }
    
    

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        
        Label label1 = new Label("Welcome to the first scene!");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> {
            window.setScene(scene2); //Changes the scene from scene1 to scene2 
        });
        
        //Layout 1- children are laid out in vertical column
        VBox layout1 = new VBox(20); //Spaced out 20px
        layout1.getChildren().addAll(label1, button1); 
        scene1 = new Scene(layout1, 200, 200);
        
        Button button2 = new Button("Go back to screen 1");
        button2.setOnAction(e->{
            window.setScene(scene1);
        });
        
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene (layout2, 600, 300);
        
        window.setScene(scene1);
        window.setTitle("Application");
        window.show();
        
    }

}
