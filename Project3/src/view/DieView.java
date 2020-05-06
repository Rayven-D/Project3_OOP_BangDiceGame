//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import project3.Die;

/**
 *
 * @author shreyesh
 */
public class DieView{
    
    //SIZE Attributes

    /**
     *  THE WIDTH AND HEIGHT ASSOCIATED WITH A SINGULAR DIE VIEW
     */
    public static final int WIDTH = 120;
    public static final int HEIGHT = 120;

    /**
     * The buttons that are going to serve as the Die in the GUI
     */
    public static Button firstButton,secondButton, thirdButton,fourthButton, fifthButton, sixthButton;
    
    //Current Dice as per the roll

    //An enum to identify the URL associated with a specific die face
    public enum AssetsURL{
        ARROW("assets/arrow.png"),
        BEER("assets/beer.png"),
        DYNAMITE("assets/dynamite.png"),
        GATLING("assets/gatling.png"),
        ONE("assets/1.png"),
        TWO("assets/2.png"),
        INSTANT_BULLET("assets/instant_bulet.png"),
        DUEL("assets/duel.png"),
        BROKEN_ARROW("assets/broken_arrow.png"),
        DOUBLE_ONE("assets/double_one.png"),
        DOUBLE_TWO("assets/double_two.png"),
        DOUBLE_BEER("assets/double_beer.png"),
        WHISKEY("assets/whiskey.png");
        
        public String url;
        
        AssetsURL(String url){
            this.url = url;
        }

        /**
         *
         * @param face The face of the die
         * @return The asset url associated with a specific face
         */
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
                case "bullet":
                    return AssetsURL.INSTANT_BULLET.url;
                case "done":
                    return AssetsURL.DOUBLE_ONE.url;
                case "dtwo":
                    return AssetsURL.DOUBLE_TWO.url;
                case "dbeer":
                    return AssetsURL.DOUBLE_BEER.url;
                case "brokena":
                    return AssetsURL.BROKEN_ARROW.url;
                case "whiskey":
                    return AssetsURL.WHISKEY.url;
                case "duel":
                    return AssetsURL.DUEL.url;
                    
                default:
                    return AssetsURL.TWO.url;
            }
        }
        
    };
    
    /**
     *  To display the Dice rolled to the user
     * @param dice The list of dice rolled in a particular turn
     * @return An HBox consisting of all the pictorial representations of a die
     */
    public static HBox display(List<Die> dice){
        
        Die die1 = (Die) dice.get(0);
        Die die2 = (Die) dice.get(1);
        Die die3 = (Die) dice.get(2);
        Die die4 = (Die) dice.get(3);
        Die die5 = (Die) dice.get(4);
        
       //BUTTON FUNCTIONALITIES
        firstButton = assignDie(die1);
        firstButton.setOnAction(e->{
            firstButton.setVisible(false);  
        });
        
        secondButton = assignDie(die2);
        secondButton.setOnAction(e->{
            secondButton.setVisible(false);
            

        });
        
        thirdButton = assignDie(die3);
        thirdButton.setOnAction(e->{
            thirdButton.setVisible(false);
            
        });
        
        fourthButton = assignDie(die4);
        fourthButton.setOnAction(e->{
            fourthButton.setVisible(false);
        });
        
        fifthButton = assignDie(die5);
        fifthButton.setOnAction(e->{
            fifthButton.setVisible(false);
        });
        

        HBox diceLayout = new HBox(20);
        diceLayout.getChildren().addAll(firstButton, secondButton, thirdButton, fourthButton, fifthButton);
        
        return diceLayout;
    }
    
    /**
     *
     * @param die A Die that has been rolled
     * @return A JavaFX Button representation of the Die
     */
    public static Button assignDie(Die die){
        
        String url = AssetsURL.getImage(die.getFace());
        
        Button button = new Button();
        button.setPrefSize(WIDTH, HEIGHT);
        button.setStyle( "-fx-background-image: url("+ url
                + ");"
                + "");
        
        return button;
    }
    
   
    
}
