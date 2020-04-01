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
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author shreyesh
 */


public class Board extends Application{
    
    Stage window;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        
        
        //Right Pane elements
        VBox roleCard = AttributeCard.display("Role", "Renegade");
        VBox characterCard = AttributeCard.display("Character", "Billy Jackson");
        HBox userInfo = new HBox();
        
        
        
        
        //Board Layout
        VBox rightPane = new VBox(40);
        BorderPane boardLayout = new BorderPane();
        
        
        Scene game = new Scene(boardLayout, 800, 800);
        window.setScene(game);
        window.show();
    }
    
   
}
