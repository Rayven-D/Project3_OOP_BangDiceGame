//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;


import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 *
 * @author shreyesh
 */
public class Token implements Components<VBox>{
    

    /**
     * Token attributes
     */
    public String type, url;
    public int curVal, width,height;
    
    /**
     *
     * @param type The type of value to be stored in the label for the Token
     * @param curVal The current value of the token
     * @param url The URL associated with the Token
     * @param width The width of the token
     * @param height THe height of the token
     */
    public Token(String type, int curVal, String url, int width, int height){
        this.type = type;
        this.curVal = curVal;
        this.url = url;
        this.width = width;
        this.height = height;
    }
    
    /**
     * To display the token! 
     * @return A VBox with the Token attributes and ImageView
     */
    @Override
    public VBox display(){
        // Components
        Label tokenType = new Label(type);
        Button icon = new Button();
        Label tokenVal = new Label(Integer.toString(curVal));
        
        icon.setStyle("-fx-background-color: #ffffff ;-fx-background-image: url("
                + url
                + ");");
        icon.setDisable(true);
        icon.setPrefSize(width, height);
        
        StackPane align = new StackPane();
        align.getChildren().addAll(icon, tokenVal);
        
        //Layouts
        VBox tokenCard = new VBox(PADDING_SIZE);
        tokenCard.getChildren().addAll(tokenType, align);
        
        return tokenCard;
    }

}
