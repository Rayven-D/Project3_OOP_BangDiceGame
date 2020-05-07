//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import com.sun.javafx.application.PlatformImpl;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.animation.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javafx.util.*;
import project3.RollDice;
import project3.Die;
import project3.Player;
import project3.Game;


/**
 *
 * @author shreyesh
 */


public class Board extends Application{
    
    /**
     * The general padding size used
     */
    public final int PADDING_SIZE = 40;

    /**
     *  The current roll that happens in the game
     */
    public static List<Die> curRoll ;
    
    //The stage for the game
    Stage window;
    
    
    //Defining the user
    private static Player user;
    
    
    /**
     * User Integer Attributes 
     */
    public static int lifePoints,
    numberOfArrows =0,
    numberOfArrowsOnTheTable,
    wantExtension,
    numPlayers,
    oneBullet =0,
    threeBullets =0;
    
    // User String Attributes 
    public static String userRole,
    userCharacter;

    /**
     *  Layout properties
     */
    public static HBox currentDiceSelection, inventory ;
    private static VBox leftPlayers = new VBox(100);
    private static HBox bottomPlayers = new HBox(100), topPlayers = new HBox(100);
    public HBox tokens = new HBox(PADDING_SIZE);
    
   

    /**
     *  Die Buttons
     */
    public Button firstDie, secondDie,thirdDie, fourthDie, fifthDie, sixthDie;
    
    public Button rollDice;

  
    //Tokens 
    Token singleBullet = new Token("Bullet", 0, "assets/bullet.png", 64, 64);
    Token multipleBullet = new Token("Three Bullets", 0, "assets/ammunition.png", 64, 64);
    Token arrows = new Token("Arrows", 0, "assets/indian.png", 64, 64);
    
    Label turnText;
    Label arrowsOnTheTable;
    Game game;
    public boolean nextRoll;
    
    /**
     * Constructor for the Board clASS 
     */
    public Board(){
    }
    

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }
    
    /**
     * The method to start the GUI
     */
    public void startGUI(){
        launch();
    }

    /**
     * To check if the user wants to play with the extensions or not
     * @return An integer value (0 or 1) representing if the user want the Extensions included
     */
    
    public int wantExtensionsIncluded(){
        ConfirmDialogBox dialogBox = new ConfirmDialogBox("Do you wish to play with extensions? ", "Extenions.. Mate?");
        return dialogBox.display();
    }
    
    /**
     * Get the number of players playing the game
     * @return 
     */
    private int getNumberOfPlayers(){
        DropdownDialogBox dropdown = new DropdownDialogBox("Select the number of players you want in the game", "How many friends you got ?");
        return dropdown.display();
    }
    
    /**
     * Distribute life points into pairs of 1's and 3's
     * 
     */
    private void setDistributionOfBullets(){
        oneBullet = user.getHealth()%3;
        threeBullets = user.getHealth()/3;
    }
    
    /**
     *  To check if the user wants to use their ability
     * @return An integer value (0 or 1) representing if the user wants to use the abilities
     */
    private int wantToUseAbility(){
        ConfirmDialogBox confirm = new ConfirmDialogBox(user.getCharacter().getSpecialAbility(), "Do you want to use your ability?");
        return 0;
    }
    
    /**
     *  To check whom the user wants to attack given a 1 or 2 on the roll
     * @param attackPlayerIndices
     * @return
     */
    private int whomDoYouWantToAttack(Integer[] attackPlayerIndices){
        String message = "You can either attack Player "+
                attackPlayerIndices[0]+" or "+
                attackPlayerIndices[1]+"!";
        String title = "Attack!" ;
        AttackDialogBox attack = new AttackDialogBox(message, title);
        return attack.display();
    }
    
    /**
     * To display to the user that a dynamite roll has occured
     * 
     * 
     */
    public void dynamite(){
        JOptionPane.showMessageDialog(null, "DYNAMITE!!!");
    }
    
    /**
     * To display to the user that an Indian attack is commencing
     */
    public void indianAttack(){
        JOptionPane.showMessageDialog(null, "INDIAN ATTACK");
    }
    
    public int doYouWantToUseYourAbility(){
//        String message = user.getCharacter().getSpecialAbility();
//        String title = "To use the ability or not to use";
//        AbilityDialogBox dialogbox = new AbilityDialogBox(message, title);
//        return dialogbox.display();
    return 0;
    }
    
    public void checkDieAction(Die die){
       
    }
    
    /**
     *
     * @param dice The final list of dice that has been rolled for a round
     * @return Displays the dice in a horizontal box layout
     */
    public HBox displayDice(List<Die> dice){
        
        Die die1 = (Die) dice.get(0);
        Die die2 = (Die) dice.get(1);
        Die die3 = (Die) dice.get(2);
        Die die4 = (Die) dice.get(3);
        Die die5 = (Die) dice.get(4);
        
        firstDie = DieView.assignDie(die1);
        firstDie.setOnAction(e->{
            if(!die1.getFace().equalsIgnoreCase("dynamite")&& game.getPlayerTurn().isUser())
                firstDie.setVisible(false);
            System.out.println(firstDie.isVisible());
        });
        
        secondDie = DieView.assignDie(die2);
        secondDie.setOnAction(e->{
            if(!die2.getFace().equalsIgnoreCase("dynamite")&& game.getPlayerTurn().isUser())
                secondDie.setVisible(false);
        });
        
        thirdDie = DieView.assignDie(die3);
        thirdDie.setOnAction(e->{
            if(!die3.getFace().equalsIgnoreCase("dynamite")&& game.getPlayerTurn().isUser())
                thirdDie.setVisible(false);
        });
        
        fourthDie = DieView.assignDie(die4);
        fourthDie.setOnAction(e->{
            if(!die4.getFace().equalsIgnoreCase("dynamite")&& game.getPlayerTurn().isUser())
                fourthDie.setVisible(false);
        });
        
        fifthDie = DieView.assignDie(die5);
        fifthDie.setOnAction(e->{
            if(!die5.getFace().equalsIgnoreCase("dynamite") && game.getPlayerTurn().isUser())
                fifthDie.setVisible(false);
        });
        

        HBox diceLayout = new HBox(20);
        diceLayout.getChildren().addAll(firstDie, secondDie, thirdDie, fourthDie, fifthDie);
        
        return diceLayout;
    }
    
    /**
     *  This method creates the player rectangles during the execution of the game
     * @param players The list of players playing the game
     */
    private void createPlayerCards(Player[] players){
        
        bottomPlayers.getChildren().clear();
        topPlayers.getChildren().clear();
        leftPlayers.getChildren().clear();

       for(Player player: players){
           if(player.isUser()){
               tokens.getChildren().clear();
               user = player;
               setDistributionOfBullets();
               arrows.curVal= user.getArrows();
               tokens.getChildren().addAll(singleBullet.display(), multipleBullet.display(), arrows.display() );
           }
           PlayerView card = new PlayerView(player, 175, 100);
           if (player.getNum()<3){
               bottomPlayers.getChildren().add(card.display());
           }
           else if(player.getNum()<5){
               leftPlayers.getChildren().add(card.display());
           }
           else if(player.getNum()<8){
               topPlayers.getChildren().add(card.display());
           }
       }
      
    }

    /**
     *  This method is part of the Game class parallel thread
     * @param dice The list of dice that you need to update at the end of each turn 
     */
    public void updateDie(List<Die> dice){

        if(dice!=null){
            inventory.getChildren().clear();
            inventory.getChildren().add(displayDice(dice));
        }
                    
        
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
    private BorderPane createBorderPane(HBox topPane, HBox bottomPane, VBox leftPane, VBox rightPane, StackPane center){
        
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
    
    

    /**
     *  This is the main JavaFX thread function from Application that is subjected to polymorphism 
     * @param stage The stage for the game to work
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        /*
              ********  INITIAL SET UP  *******
        */  
        
        //Set the stage for the game
        window = stage;
        window.setTitle("Bang! The Dice Game");
        
        //Get the necessary input from the user
        //wantExtension = wantExtensionsIncluded();
        numPlayers = getNumberOfPlayers();
    
        //Initialize the Game class
         game = new Game(numPlayers, 1, this);
        
        
        //Anonymous Players
        Player[] players = game.getPlayers();
        
        //Create Player cards
        createPlayerCards(game.getPlayers());


        /*
              ********  RIGHT PANE ELEMENTS (USER) *******
        */
        
        //User Attributes
        VBox roleCard = AttributeCard.display("Role",user.getRole().getName());
        VBox characterCard = AttributeCard.display("Character", user.getCharacter().getName().replace('_',' '));
        HBox userInfo = new HBox(PADDING_SIZE+20);
        userInfo.getChildren().addAll(roleCard, characterCard);

        
        
        //User Tokens
        setDistributionOfBullets();
       
        singleBullet.curVal = oneBullet;
        multipleBullet.curVal = threeBullets;
        arrows.curVal = numberOfArrows;
        
        
        //User Actions  
        StackPane userRoll = new StackPane();
        rollDice = new Button("Start Game");        
        userRoll.getChildren().addAll(rollDice);  
                
        inventory = new HBox(PADDING_SIZE);
        rollDice.setOnAction((event)-> { 
            if(game.chooseReroll == false){
               
                inventory.getChildren().clear();

                inventory.getChildren().add(displayDice(game.roll));

                game.rollAgain = true;
                this.nextRoll = true;
            }
            else{
               this.nextRoll = true;
            }
        });
       
        
        
        /*
              ********  CENTER PANE ELEMENTS *******
        */
        
        //DICE TITLE LABEL
        Label diceText = new Label("Dice");
         turnText = new Label(game.getPlayerTurn().getCharacter().getName()+ " " + game.rollNumber);
        diceText.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; ");
        turnText.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; ");
        StackPane dicePane = new StackPane();
        dicePane.getChildren().addAll(diceText);
        
            
        //ARROW TITLE LABEL
         arrowsOnTheTable = new Label("ARROWS");
        arrowsOnTheTable.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; fx-padding-bottom: 140px");
        StackPane arrowTextPane = new StackPane();
        arrowTextPane.getChildren().addAll(arrowsOnTheTable);  
        Token boardArrows = new Token("", game.middleArrows, "assets/arrow.png", 120, 120);
        
     
                
        /*
              ********  LAYOUT ELEMENTS *******
        */
        HBox topPane = new HBox();
        topPane.getChildren().addAll(topPlayers);
        topPane.setStyle("-fx-padding: 0 100 0 100");


        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(leftPlayers);
        leftPane.setStyle("-fx-padding: 100 0 100 50");


        StackPane centerView = new StackPane();
        VBox center = new VBox(PADDING_SIZE);
        center.getChildren().addAll(dicePane,turnText,inventory, arrowTextPane, boardArrows.display());
        center.setStyle("-fx-padding: 100 25 0 25;");
        centerView.getChildren().addAll(center);

        HBox bottomPane = new HBox();
        bottomPane.getChildren().addAll(bottomPlayers);
        bottomPane.setStyle("-fx-padding: 0 100 0 100");


        VBox rightPane = new VBox(PADDING_SIZE);
        rightPane.getChildren().addAll(userInfo, tokens, userRoll); 
        rightPane.setStyle( "-fx-padding: 50 30 30 50; ");


    Task playTurn = new Task<Void>(){
        @Override 
        protected Void call() throws Exception{
            while(true){  
                if(game.getPlayerTurn().isUser()){
                    JOptionPane.showMessageDialog(null, "Your Turn!");
                }else{
                    JOptionPane.showMessageDialog(null, game.getPlayerTurn().getCharacter().getName() + "'s Turn");
                }
                try{
                    Thread.sleep(200);
                }catch(Exception ex){}

                game.turn(inventory);
                for(int i=0; i< game.getPlayers().length; i++){
                    game.won = game.getPlayers()[i].getRole().getWon(game.getPlayers());
                    if(game.won){
                        break;    
                    }
                }
                if(game.won){
                    
                    break;
                }
                
               
                
                if(game.getPlayers()[game.playerTurn].isUser()){
                    PlatformImpl.runAndWait(()->{
                        if(game.getPlayers()[game.playerTurn].getStatus()){
                            doYouWantToUseYourAbility();
                        }
                        else{
                            OkayDialogBox youLost = new OkayDialogBox("YOU LOST", "OOPS, you LOSTTTT!!!");
                            youLost.display();
                            System.exit(0);
                        }
                    });
                    
                    //Break out of the loop if the game is won
                    if(game.won){
                        break;
                    }
                    
                    //Assign the next player tunr
                    game.nextTurn();    
                    if(game.getPlayerTurn().isUser()){
                        rollDice.setText("Roll Die");
                    }else{
                        rollDice.setText("Continue");
                     }
                    //If the current player is the user, do the following!
                    if(game.getPlayers()[game.playerTurn].isUser()){
                        PlatformImpl.runAndWait(()->{
                            //As long as user is alive! 
                            if(game.getPlayers()[game.playerTurn].getStatus()){
                                wantToUseAbility();
                            }
                            //If the user is dead!
                            else{
                                OkayDialogBox youLost = new OkayDialogBox("YOU LOST", "OOPS, you LOSTTTT!!!");
                                youLost.display();
                                System.exit(0);
                            }
                            }); 
                    }
                    
                    // THREAD HANDLING!!!!
                    try {
                            Thread.sleep(1000); //Sleep for 4s after every turn to simulate realism
                    } catch (InterruptedException ex) {
                            break;
                    }   
                    
                    //After the end of everyTurn, do this! 
                    Platform.runLater(()->{
                          createPlayerCards(game.getPlayers());
                          updateDie(game.finalRoll);
                          turnText.setText(game.getPlayerTurn().getCharacter().getName());
                          arrowsOnTheTable.setText("ARROWS: "+Integer.toString(game.middleArrows));

                      });
                }
                game.nextTurn();
                    if(game.getPlayerTurn().isUser()){
                        rollDice.setText("Roll Die");
                    }else{
                        rollDice.setText("Continue");
                     }
                 
            }
            return null;
        }  
    };
    
        BorderPane boardLayout = createBorderPane(topPane, bottomPane, leftPane, rightPane, centerView);
        
        //Start the game thread
        Thread gameThread = new Thread(playTurn);
        gameThread.setDaemon(true);
        gameThread.start();
        

        //Assign the gameView to the Stage
        Scene gameView = new Scene(boardLayout, 1980, 1024);
        gameView.getStylesheets().add("styles/Bang.css");
        window.setScene(gameView);
        window.show();
        
    }
    
}
    
    
  

