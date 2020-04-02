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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private static int numberOfArrowsOnTheTable = 9;
    
    
    public void setNumberOfSingleBullet(){
        
    }
    
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Bang! The Dice Game");
        
   
        //Anonymous Players
        StackPane player1 = new StackPane();
        player1.getChildren().addAll(Player.display(50, 50));
        
        
        
        //Right Pane elements
        
        //User Attributes
        VBox roleCard = AttributeCard.display("Role", "Renegade");
        VBox characterCard = AttributeCard.display("Character", "Billy Jackson");
        HBox userInfo = new HBox(PADDING_SIZE);
        userInfo.getChildren().addAll(roleCard, characterCard);
        
        //User Tokens
        VBox singleBullet = Token.display("Bullet", 4, "assets/bullet.png");
        VBox multipleBullets = Token.display("Three Bullets", 4, "assets/ammunition.png");
        VBox arrows = Token.display("Arrows", 6, "assets/indian.png");
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
        Label currentArrowsOnTheTable = new Label(Integer.toString(numberOfArrowsOnTheTable));
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
        VBox leftPane = new VBox(PADDING_SIZE);
        leftPane.getChildren().addAll(player1);
        leftPane.setStyle("-fx-padding: 50 25 50 25");

        
        StackPane centerView = new StackPane();
        VBox center = new VBox(PADDING_SIZE);
        center.getChildren().addAll(diceText,inventory, arrowsOnTheTable, currentArrowsOnTheTable);
        center.setStyle("-fx-padding: 200 25 200 25;");
        centerView.getChildren().addAll(center);
        
        VBox rightPane = new VBox(PADDING_SIZE);
        rightPane.getChildren().addAll(userInfo, tokens, userRoll, userAttacks); 
        rightPane.setStyle("-fx-background-color: #D05353; -fx-padding: 50 50 50 50");
        BorderPane boardLayout = new BorderPane();
        
        
        //Final Pane Set Up
        boardLayout.setRight(rightPane);
        boardLayout.setCenter(center);
        boardLayout.setLeft(leftPane);
        
        
        Scene game = new Scene(boardLayout, 1980, 1024);
        game.getStylesheets().add("styles/Bang.css");
        window.setScene(game);
        window.show();
    }
    
    
   
}
