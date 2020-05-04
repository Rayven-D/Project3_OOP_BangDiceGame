//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import project3.Player;

/**
 *
 * @author shreyesh
 */
public class PlayerView implements Components<StackPane>{
    
    private String playerName;
    private int oneBullet, threeBullet, bullets;
    private int width, height;
    
    //Create a constructor that takes in a Player object and create the necessary labels
    
    public PlayerView(Player player, int width, int height){
        this.playerName = player.getCharacter().getName();
        this.bullets = player.getCharacter().getLifePoints();
        this.width = width;
        this.height = height;
    }
    
    public StackPane display(){
        // Components
        setBullets();
        VBox component = new VBox();
        StackPane group = new StackPane();
//        Rectangle card = new Rectangle(width, height);
        Label player = new Label(playerName);
        Label singleBullets = new Label(Integer.toString(oneBullet));
        Label multiBullets = new Label(Integer.toString(threeBullet));
        
        //Card Attributes
//        card.setFill(Color.BLACK);
//        card.setStroke(Color.BLACK);
//        card.setArcHeight(10.0d); 
//        card.setArcWidth(10.0d); 
        
        component.getChildren().addAll(player, singleBullets, multiBullets);
        group.getChildren().add(component);
        
        group.setStyle("-fx-background-color: #000000");
        return group;
    }
    
    public void setBullets(){
        threeBullet = bullets/3;
        oneBullet = bullets%3;
    }

}
