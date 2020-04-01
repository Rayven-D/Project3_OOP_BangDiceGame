//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

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
public class Inventory {
    
    public static final int PADDING_SIZE = 40;

    public static VBox display(){
        VBox inventory = new VBox(PADDING_SIZE);
        return inventory;
    }
}
