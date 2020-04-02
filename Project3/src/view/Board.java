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
import project3.RollDice;


/**
 *
 * @author shreyesh
 */


public class Board extends Application{
    
    public final int PADDING_SIZE = 40;
    
    Stage window;
    
    // State Properties - Controller 
    private static int numberOfSingleBullet;
    private static int numberOfMultipleBullets;
    private static int numberOfArrows;
    private static String userRole; 
    private static String userCharacter;
    private static int numberOfArrowsOnTheTable;
    
    
    public void setNumberOfSingleBullet(){
        
    }
    
    
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
        
        
        //Selected Token 
        
        
        
        //Roll Dice  
        StackPane userRoll = new StackPane();
        Button rollDice = new Button("Roll Dice");
        userRoll.getChildren().addAll(rollDice);
        
        //Center Console
        Label diceText = new Label("Dice");
        Label arrowsOnTheTable = new Label("Arrows");
        HBox inventory = new HBox(PADDING_SIZE);
        rollDice.setOnAction(e-> {
            //TEMPORARY CONTROLLER FOR TESTING
            inventory.getChildren().clear();
            RollDice die = new RollDice();  
            inventory.getChildren().add(DieView.display(die.getDice()));
        });
        
        
        
        //
        HBox userAttacks = new HBox(PADDING_SIZE);
        Button attackLeft = new Button("Attack Left");
        Button attackRight = new Button("Attack Right");
        
        userAttacks.getChildren().addAll(attackLeft, attackRight);
        
        
        
        //Board Layout
        VBox center = new VBox(PADDING_SIZE);
        center.getChildren().addAll(inventory);
        VBox rightPane = new VBox(PADDING_SIZE);
        rightPane.getChildren().addAll(userInfo, tokens, userRoll, userAttacks); 
        BorderPane boardLayout = new BorderPane();
        boardLayout.setRight(rightPane);
        boardLayout.setCenter(center);
        
        
        Scene game = new Scene(boardLayout, 800, 800);
        window.setScene(game);
        window.show();
    }
    
    
   
}
