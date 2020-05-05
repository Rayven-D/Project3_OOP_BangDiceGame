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
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
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
    public static List<Die> curRoll ;
    
    Stage window;
    
    // State Properties - Controller 
    public static int lifePoints,numberOfArrows =0, numberOfArrowsOnTheTable, wantExtension, numPlayers, oneBullet =0, threeBullets =0;
    public static String userRole, userCharacter;
    public static HBox currentDiceSelection,  inventory ;
    private static VBox leftPlayers = new VBox(100);
    private static HBox bottomPlayers = new HBox(100), topPlayers = new HBox(100);
    private static Player user;
    public static boolean askUserInput = false;
    public static Button firstDie, secondDie, thirdDie, fourthDie, fifthDie, sixthDie;
    public HBox tokens = new HBox(PADDING_SIZE);
  
    
    Token singleBullet = new Token("Bullet", 0, "assets/bullet.png", 64, 64);
    Token multipleBullet = new Token("Three Bullets", 0, "assets/ammunition.png", 64, 64);
    Token arrows = new Token("Arrows", 0, "assets/indian.png", 64, 64);
    
    
    public Board(){
        
    }
    

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }
    
//    public void startGUI(){
//        launch();
//    }
    
    public static int wantExtensionsIncluded(){
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
    
    public void setDistributionOfBullets(){
        oneBullet = user.getHealth()%3;
        threeBullets = user.getHealth()/3;
    }
    
    public int wantToUseAbility(){
        ConfirmDialogBox confirm = new ConfirmDialogBox(user.getCharacter().getSpecialAbility(), "Do you want to use your ability?");
        return confirm.display();
    }
    
    public int whomDoYouWantToAttack(Integer[] attackPlayerIndices){
        String message = "You can either attack Player "+
                attackPlayerIndices[0]+" or "+
                attackPlayerIndices[1]+"!";
        String title = "Attack!" ;
        AttackDialogBox attack = new AttackDialogBox(message, title);
        return attack.display();
    }
    
    public int doYouWantToUseYourAbility(){
        String message = user.getCharacter().getSpecialAbility();
        String title = "To use the ability or not to use";
        AbilityDialogBox dialogbox = new AbilityDialogBox(message, title);
        return dialogbox.display();
    }
    
    public void checkDieAction(Die die){
       
    }
    
    public HBox displayDice(List<Die> dice){
        
        Die die1 = (Die) dice.get(0);
        Die die2 = (Die) dice.get(1);
        Die die3 = (Die) dice.get(2);
        Die die4 = (Die) dice.get(3);
        Die die5 = (Die) dice.get(4);
        
        firstDie = DieView.assignDie(die1);
        firstDie.setOnAction(e->{
            firstDie.setVisible(false);
        });
        
        secondDie = DieView.assignDie(die2);
        secondDie.setOnAction(e->{
            secondDie.setVisible(false);
        });
        
        thirdDie = DieView.assignDie(die3);
        thirdDie.setOnAction(e->{
            thirdDie.setVisible(false);
        });
        
        fourthDie = DieView.assignDie(die4);
        fourthDie.setOnAction(e->{
            fourthDie.setVisible(false);
        });
        
        fifthDie = DieView.assignDie(die5);
        fifthDie.setOnAction(e->{
            fifthDie.setVisible(false);
        });
        

        HBox diceLayout = new HBox(20);
        diceLayout.getChildren().addAll(firstDie, secondDie, thirdDie, fourthDie, fifthDie);
        
        return diceLayout;
    }
    

    
    public void createPlayerCards(Player[] players){
        
        bottomPlayers.getChildren().clear();
        topPlayers.getChildren().clear();
        leftPlayers.getChildren().clear();

       for(Player player: players){
           if(player.isUser()){
               tokens.getChildren().clear();
               user = player;
               setDistributionOfBullets();
               arrows.curVal= user.getArrows();
               tokens.getChildren().addAll(singleBullet.display(), 
                    multipleBullet.display(), arrows.display() );
               continue;
               
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
    
    public void updateDie(List<Die> dice){
        if(dice!=null){
            inventory.getChildren().clear();
            inventory.getChildren().add(displayDice(dice));
        }
    }
    
    

    /**
     *
     * @param stage
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
        wantExtension = wantExtensionsIncluded();
        numPlayers = getNumberOfPlayers();
    
        //Initialize the Game class
        Game game = new Game(numPlayers, 1);
        
        
        //Anonymous Players
        Player[] players = game.getPlayers();
        
        //Create Player cards
        createPlayerCards(players);
        

        /*
              ********  RIGHT PANE ELEMENTS (USER) *******
        */
        
        //User Attributes
        VBox roleCard = AttributeCard.display("Role",user.getRole().getName());
        VBox characterCard = AttributeCard.display("Character", user.getCharacter().getName().replace('_',' '));
        HBox userInfo = new HBox(PADDING_SIZE+20);
        userInfo.getChildren().addAll(roleCard, characterCard);
        
        
        //User Actions  
        StackPane userRoll = new StackPane();
        Button rollDice = new Button("Roll Dice");        
        userRoll.getChildren().addAll(rollDice);
        
       
        /*
              ********  CENTER PANE ELEMENTS *******
        */
            
        inventory = new HBox(PADDING_SIZE);
        rollDice.setOnAction(e-> { 
            inventory.getChildren().clear();
            RollDice dice = new RollDice();
            inventory.getChildren().add(displayDice(dice.getDice()));
        });
       
        
        Label diceText = new Label("DICE");
        diceText.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; ");
        StackPane dicePane = new StackPane();
        dicePane.getChildren().addAll(diceText);
        
        Label arrowsOnTheTable = new Label("ARROWS");
        arrowsOnTheTable.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold; fx-padding-bottom: 140px");
        StackPane arrowTextPane = new StackPane();
        arrowTextPane.getChildren().addAll(arrowsOnTheTable);  
       
        Token boardArrows = new Token("", game.middleArrows, "assets/arrow.png", 120, 120);
        
     
         
        
        //Board Layout
        HBox topPane = new HBox();
        topPane.getChildren().addAll(topPlayers);
        topPane.setStyle("-fx-padding: 0 100 0 100");

        
        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(leftPlayers);
        leftPane.setStyle("-fx-padding: 100 0 100 50");

        
        StackPane centerView = new StackPane();
        VBox center = new VBox(PADDING_SIZE);
        center.getChildren().addAll(dicePane,inventory, arrowTextPane, boardArrows.display());
        center.setStyle("-fx-padding: 100 25 0 25;");
        centerView.getChildren().addAll(center);
        
        HBox bottomPane = new HBox();
        bottomPane.getChildren().addAll(bottomPlayers);
        bottomPane.setStyle("-fx-padding: 0 100 0 100");

        
        VBox rightPane = new VBox(PADDING_SIZE);
        rightPane.getChildren().addAll(userInfo, tokens, userRoll); //ADD ATTACKS IF NECESSARY
        rightPane.setStyle( "-fx-padding: 50 30 30 50; ");
        
        
        BorderPane boardLayout = createBorderPane(topPane, bottomPane, leftPane, rightPane, centerView);
  
        
    Task updateArrowsInTheGame = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                    while (true) {
                            updateMessage(Integer.toString(game.middleArrows));
                            try {
                                    Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                    break;
                            }
                    }
                    return null;
            }
    };
    
        //Keeps updating the arrowsOnTheTable label on the basis of the message property
//        arrowsOnTheTable.textProperty().bind(updateArrowsInTheGame.messageProperty());
        Thread t2 = new Thread(updateArrowsInTheGame);
        t2.setName("Tesk Time Updater");
        t2.setDaemon(true);
        t2.start();
        
        
        

    Task playTurn = new Task<Void>(){
        @Override 
        protected Void call() throws Exception{
            while(true){
                
//                final List<Die> temp = 
                game.turn();
                for(int i=0; i< game.getPlayers().length; i++){
                    game.won = game.getPlayers()[i].getRole().getWon(game.getPlayers());
                    if(game.won){
                        System.out.println("Here - END");
                        break;    
                    }
                }
                if(game.won){
                    System.out.println("Here - END");
                    break;
                }
                
                game.nextTurn();
                
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
                    
                }
               
                
                try {
                        Thread.sleep(5000);
                } catch (InterruptedException ex) {
                        break;
                }
                
                Platform.runLater(()->{
                    createPlayerCards(game.getPlayers());
                    updateDie(game.finalRoll);
                    
                });
                
                if(game.won){
                   PlatformImpl.runAndWait(()->{
                        if(game.getPlayers()[game.playerTurn].getStatus()){
                            doYouWantToUseYourAbility();
                        }
                        else{
                        OkayDialogBox youLost = new OkayDialogBox("GAME OVER", game.getPlayers()[game.playerTurn-1].getCharacter().getName() + " WON!!!");
                        youLost.display();
                        System.exit(0);
                        }
                    }); 
                }
                
            }
            return null;
        }
    };    
    
    Thread t1 = new Thread(playTurn);
    t1.setDaemon(true);
    t1.start();
    
        
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
    
    
  

