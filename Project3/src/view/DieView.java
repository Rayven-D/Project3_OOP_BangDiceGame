//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.Iterator;
import java.util.LinkedList;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import project3.Die;

/**
 *
 * @author shreyesh
 */
public class DieView{
    
    
    public enum AssetsURL{
        ARROW("assets/arrow.png"),
        BEER("assets/beer.png"),
        DYNAMITE("assets/dynamite.png"),
        GATLING("assets/gatling.png"),
        ONE("assets/1.png"),
        TWO("assets/2.png");
        
        public String url;
        
        AssetsURL(String url){
            this.url = url;
        }

        public static String getImage(String face){
            switch(face.toLowerCase()){
                case "arrow":
                    return AssetsURL.ARROW.url;
                case "beer": 
                    return AssetsURL.BEER.url;
                case "dynamite":
                    return AssetsURL.DYNAMITE.url;
                case "gatling":
                    return AssetsURL.GATLING.url;
                case "one": 
                    return AssetsURL.ONE.url;
                default:
                    return AssetsURL.TWO.url;
            }
        }
        
    };
    
    
    public static HBox display(LinkedList dice){
        
        Die die1 = (Die) dice.get(0);
        Die die2 = (Die) dice.get(1);
        Die die3 = (Die) dice.get(2);
        Die die4 = (Die) dice.get(3);
        Die die5 = (Die) dice.get(4);
        Die die6 = (Die) dice.get(5);
        
        
        //Components
        Button oneButton = new Button();
        oneButton.setOnAction(e -> {
            oneButton.setVisible(false);
        });
        oneButton.setPrefSize(128, 128);
        oneButton.setStyle("-fx-background-color: #ffffff; -fx-background-image: url("
                + AssetsURL.getImage(die1.getFace())
                + ");");
        
        
        Button twoButton = new Button();
        twoButton.setOnAction(e -> {
            twoButton.setVisible(false);
        });
        twoButton.setPrefSize(128, 128);       
        twoButton.setStyle("-fx-background-color: #ffffff; -fx-background-image: url("
                + AssetsURL.getImage(die2.getFace())
                + ");");
        
        Button threeButton = new Button();
        threeButton.setPrefSize(128, 128);      
        threeButton.setOnAction(e -> { 
            threeButton.setVisible(false);
        });
        threeButton.setStyle("-fx-background-color: #ffffff; -fx-background-image: url("
                + AssetsURL.getImage(die3.getFace())
                + ");");
        
        Button fourButton = new Button();
        fourButton.setPrefSize(128, 128);       
        fourButton.setOnAction(e -> {
            fourButton.setVisible(false);
        });
        fourButton.setStyle("-fx-background-color: #ffffff; -fx-background-image: url("
                + AssetsURL.getImage(die4.getFace())
                + ");");
        
        Button fiveButton = new Button();
        fiveButton.setPrefSize(128, 128);       
        fiveButton.setOnAction(e -> {
            fiveButton.setVisible(false);
        });
        fiveButton.setStyle("-fx-background-color: #ffffff; -fx-background-image: url("
                + AssetsURL.getImage(die5.getFace())
                + ");");
        
        Button sixButton = new Button();
        sixButton.setPrefSize(128, 128);       
        sixButton.setOnAction(e -> {
            sixButton.setVisible(false);
        });
        sixButton.setStyle("-fx-background-color: #ffffff; "
                + "-fx-background-image: url("
                + AssetsURL.getImage(die6.getFace())
                + ");");
        
      
        HBox diceLayout = new HBox(40);
        diceLayout.getChildren().addAll(oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton);
        
        return diceLayout;
    }
}
