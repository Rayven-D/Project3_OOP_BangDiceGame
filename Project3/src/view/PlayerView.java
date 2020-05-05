//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import project3.Player;

/**
 *
 * @author shreyesh
 */
public class PlayerView implements Components<StackPane>{
    
    private String playerName;
    private int oneBullet, threeBullet, bullets, number;
    private int width, height, arrows;
    
    //Create a constructor that takes in a Player object and create the necessary labels
    
    public PlayerView(Player player, int width, int height){
        this.playerName = player.getCharacter().getName();
        this.number = player.getNum();
        this.arrows = player.getArrows();
        this.bullets = player.getCharacter().getLifePoints();
        this.width = width;
        this.height = height;
    }
    
    public StackPane display(){
        // Components
        setBullets();
        VBox component = new VBox();
        StackPane group = new StackPane();
        Label player = new Label(playerName.replace('_', ' '));
        Label playerNumber = new Label("Player: "+Integer.toString(number+1));
        Label singleBullets = new Label("Single:  "+Integer.toString(oneBullet));
        Label multiBullets = new Label("Multi: "+ Integer.toString(threeBullet));
        Label arrowLabel = new Label("Arrows: "+ Integer.toString(arrows));
        //"Arrows: "+ Integer.toString(arrows
        component.getChildren().addAll(playerNumber, player, singleBullets, multiBullets, arrowLabel);
        group.getChildren().add(component);
        
        group.setStyle("-fx-background-color: #000000; "
                + "-fx-padding: 20 20 20 20; "
                + "-fx-border-radius: 10;" 
                + "-fx-border-width: 1;" 
                + "-fx-border-color: #FC3D44;");
        return group;
    }
    
    public void setBullets(){
        threeBullet = bullets/3;
        oneBullet = bullets%3;
    }

}
