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
import project3.RollDice;
import project3.Die;
import project3.Player;
import project3.Game;
import project3.MasterRole;

/**
 *
 * @author shreyesh
 */


public class Board extends Application{
    
    /**
     * The general padding size used
     */
    public final int PADDING_SIZE = 40;
    
    Stage window;
    
    // State Properties - Controller 
    public static int lifePoints,numberOfArrows, numberOfArrowsOnTheTable, wantExtension, numPlayers;
    public static String userRole, userCharacter;
    public static HBox currentDiceSelection;
    private VBox leftPlayers = new VBox(100);
    private HBox bottomPlayers, topPlayers = new HBox(100);
    

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }
    
    public int wantExtensionsIncluded(){
        ConfirmDialogBox dialogBox = new ConfirmDialogBox("Do you wish to play with extensions? ", "Extenions.. Mate?");
        return dialogBox.display();
    }
    
    /**
     * Get the number of players playing the game
     */
    public int getNumberOfPlayers(){
        DropdownDialogBox dropdown = new DropdownDialogBox("Select the number of players you want in the game", "How many friends you got ?");
        return dropdown.display();
    }
    
    public createPlayerCards(){
        
    }

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        //Set the stage for the game
        window = stage;
        window.setTitle("Bang! The Dice Game");
        
        //Get the necessary input from the user
        wantExtension = wantExtensionsIncluded();
        numPlayers = getNumberOfPlayers();
        
         
        //Initialize the Game class
        Game game = new Game(numPlayers, 1);
  
        //Anonymous Players
        Player[] players = game.getPlayers();
        
        //Create
        
//        leftPlayers.getChildren().addAll(player.display(175, 75), player.display(175, 75));
//        bottomPlayers.getChildren().addAll(player.display(175, 75), player.display(175, 75));
//        topPlayers.getChildren().addAll(player.display(175, 75), player.display(175, 75));
        
        //Right Pane elements
        
        //User Attributes
        VBox roleCard = AttributeCard.display("Role", "Renegade");
        VBox characterCard = AttributeCard.display("Character", "Billy Jackson");
        HBox userInfo = new HBox(PADDING_SIZE+20);
        userInfo.getChildren().addAll(roleCard, characterCard);

        
        //User Tokens
        VBox singleBullet = Token.display("Bullet", 4, "assets/bullet.png", 64, 64);
        VBox multipleBullets = Token.display("Three Bullets", 4, "assets/ammunition.png", 64, 64);
        VBox arrows = Token.display("Arrows", 6, "assets/indian.png", 64, 64);
        HBox tokens = new HBox(PADDING_SIZE);
        tokens.getChildren().addAll(singleBullet, multipleBullets, arrows);
        
        
        //Roll Dice  
        StackPane userRoll = new StackPane();
        Button rollDice = new Button("Roll Dice");
        
        rollDice.setId("button-attack");
        userRoll.getChildren().addAll(rollDice);
        
        StackPane attacks = new StackPane();
        HBox userAttacks = new HBox(PADDING_SIZE);
        
        Button attackLeft = new Button("Attack Left");  
        attackLeft.setId("button-attack");
        
        Button attackRight = new Button("Attack Right");
        attackRight.setId("button-attack");
        
        userAttacks.getChildren().addAll(attackLeft, attackRight);
        attacks.getChildren().addAll(userAttacks);
        
                
        HBox inventory = new HBox(PADDING_SIZE);
        rollDice.setOnAction(e-> { 
            inventory.getChildren().clear();
            RollDice die = new RollDice();  
            inventory.getChildren().add(DieView.display(die.getDice()));
        });
        
        
        // Selected Dice
        HBox userSelectedDice = new HBox();
        
        
        
        //Center Console
        Label diceText = new Label("DICE");
        diceText.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; ");
        StackPane dicePane = new StackPane();
        dicePane.getChildren().addAll(diceText);
        
        Label arrowsOnTheTable = new Label("ARROWS");
        arrowsOnTheTable.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; fx-padding-bottom: 140px");
        StackPane arrowTextPane = new StackPane();
        arrowTextPane.getChildren().addAll(arrowsOnTheTable);  
        
        Label currentArrowsOnTheTable = new Label(Integer.toString(numberOfArrowsOnTheTable));
        StackPane curArrowPane = new StackPane();
        curArrowPane.getChildren().addAll(arrowsOnTheTable);  
        
        VBox boardArrows = Token.display("", 6, "assets/arrow.png", 120, 120);
        
     
         
        
        //Board Layout
        HBox topPane = new HBox();
        topPane.getChildren().addAll(topPlayers);
        topPane.setStyle("-fx-padding: 0 500 0 500");

        
        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(leftPlayers);
        leftPane.setStyle("-fx-padding: 250 0 250 50");

        
        StackPane centerView = new StackPane();
        VBox center = new VBox(PADDING_SIZE);
        center.getChildren().addAll(dicePane,inventory, arrowTextPane,curArrowPane, boardArrows);
        center.setStyle("-fx-padding: 150 25 50 25;");
        centerView.getChildren().addAll(center);
        
        HBox bottomPane = new HBox();
        bottomPane.getChildren().addAll(bottomPlayers);
        bottomPane.setStyle("-fx-padding: 0 500 0 500");

        
        VBox rightPane = new VBox(PADDING_SIZE);
        rightPane.getChildren().addAll(userInfo, tokens, userRoll, userAttacks); 
        rightPane.setStyle( "-fx-padding: 50 30 30 50; ");
        
        
        BorderPane boardLayout = createBorderPane(topPane, bottomPane, leftPane, rightPane, centerView);
        
        
        Scene gameView = new Scene(boardLayout, 1980, 1024);
        gameView.getStylesheets().add("styles/Bang.css");
        window.setScene(gameView);
        window.show();
    }
    
    /**
     *
     * @param topPane  The top pane for the game layout
     * @param bottomPane The bottom pane for the game layout
     * @param leftPane  The left pane for the game layout
     * @param rightPane  The right pane for the game layout
     * @param center  The center pane for the game layout
     * @return The layout for the game
     */
    public BorderPane createBorderPane(HBox topPane, HBox bottomPane, VBox leftPane, VBox rightPane, StackPane center){
        
        BorderPane boardLayout = new BorderPane();
        boardLayout.setStyle("-fx-background-color: #ffffff ;-fx-background-image: url("
                + "assets/background.jpg"
                + ");"
                + "-fx-object-fit: contain;");
        boardLayout.setRight(rightPane);
        boardLayout.setCenter(center);
        boardLayout.setLeft(leftPane);
        boardLayout.setBottom(bottomPane);
        boardLayout.setTop(topPane);
        
        return boardLayout;
    }
    
  
}
