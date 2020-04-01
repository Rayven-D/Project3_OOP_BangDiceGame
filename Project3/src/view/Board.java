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
    
    public final int PADDING_SIZE = 40;
    
    Stage window;
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        
        
        //Right Pane elements
        
        //User Attributes
        VBox roleCard = AttributeCard.display("Role", "Renegade");
        VBox characterCard = AttributeCard.display("Character", "Billy Jackson");
        HBox userInfo = new HBox(PADDING_SIZE);
        userInfo.getChildren().addAll(roleCard, characterCard);
        
        //User Tokens
        VBox singleBullet = Token.display("Bullet", 4);
        VBox multipleBullets = Token.display("Three Bullets", 4);
        VBox arrows = Token.display("Arrows", 6);
        HBox tokens = new HBox(PADDING_SIZE);
        tokens.getChildren().addAll(singleBullet, multipleBullets, arrows);
        
        //Board Layout
        VBox rightPane = new VBox(PADDING_SIZE);
        rightPane.getChildren().addAll(userInfo, tokens);
        BorderPane boardLayout = new BorderPane();
        boardLayout.setRight(rightPane);
        
        
        Scene game = new Scene(boardLayout, 800, 800);
        window.setScene(game);
        window.show();
    }
    
   
}
