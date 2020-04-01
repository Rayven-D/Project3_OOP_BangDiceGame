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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author shreyesh
 */
public class JavaFXTest extends Application  {

   Button button;
   Stage window;
    
    public static void main(String[] args) {
       launch(args); //Set up the program as JavaFX application
    }
    
    

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Title of the Window"); //Sets the title of the window 
        
        button = new Button();
        button.setText("Click Me");
        
        window.setOnCloseRequest(e-> {
            e.consume(); //If the user requests the event, stop! We are going to take care of it. 
            closeProgram();
                }); // Whenever they click the close button on the window.
        
        Button closeButton = new Button("Close Window");
        
        closeButton.setOnAction(e->closeProgram());
   
        
        HBox layout = new HBox(40); //To align the button within the stage
        layout.getChildren().addAll(button, closeButton);
        
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }
    
    private void closeProgram(){
        boolean result = ConfirmBox.display("Confirm Box", "Are you sure you want to do this? ");
        if(result)
            window.close();
    }


    }
    
    
    

