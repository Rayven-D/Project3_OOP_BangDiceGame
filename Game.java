import java.util.*;


public class Game {

    public enum Characters{
        BART_CASSIDY(8), 
        BLACK_JACK(8), 
        CALAMITY_JANET(8), 
        EL_GRINGO(7), 
        JESSE_JONES(9), 
        JOURDONNAIS(7), 
        KIT_CARLSON(7), 
        LUCKY_DUKE(8), 
        PAUL_REGRET(9), 
        PEDRO_RAMIREZ(8), 
        ROSE_DOOLAN(9), 
        SID_KETCHUM(8), 
        SLAB_THE_KILLER(8), 
        SUZY_LAFAYETTE(8), 
        VULTURE_SAM(9), 
        WILLY_THE_KID(8);

        
        public int lifePoints;
    
        Characters(int lifePoints){
            this.lifePoints = lifePoints;
        }
        
       
   }

    //Player players[]; 
    int playerTurn;
    int numPlayers;
    int middleArrows;



    public Game(int numPlayers){

    }
    public Game(){

    }

    public void nextTurn(){
        playerTurn = (playerTurn + 1) % (numPlayers-1);

    }

}