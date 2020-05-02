/**
*CS 2365, section 1, Spring 2020
*Cole Trammell
*Project 3
 */
package project3.project3_OOP;

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cole Trammell
 */
public class RollDice {
    
    private List<Die> dice = new ArrayList<>();
    
    String[] Face = new String[]{"Arrow", "One", "Two", "Dynamite", "Gatling", "Beer"};
    
    String[] LoudFace = new String[]{"Bullet", "Gatling", "Dynamite", "One", "Two", "Arrow"};
    
    String[] CowardFace = new String[]{"Beer", "BrokenA", "Dynamite", "Arrow", "One", "DBeer"};
    
    String[] DuelFace = new String[]{"Whiskey", "Duel", "Arrow", "Gatling", "Duel", "Dynamite"};
    
    Die die = new Die("one",true,false);
    
    private boolean incExp1;
    
    private boolean incExp2;
    
    String exp1Die;
    
    Die Duel = new Die("One", true, true);
    
    /**
     *constructor for RollDice object
     * @param exp1Die: A string for which expansion die if any the player wants
     * to include in their role.
     */
    public RollDice(String exp1Die){
        
        this.exp1Die = exp1Die;
        
        Die die1 = new Die("One",true,true);
        Die die2 = new Die("One",true,true);
        Die die3 = new Die("One",true,true);
        Die die4 = new Die("One",true,true);
        Die die5 = new Die("One",true,true);
        
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);
        
        dice = rollDice(dice);
        
    }
    
    
    
    /**
     * rolls the dice if the player chooses to and they are able to be rolled
     * @param dice: the ArrayList of Die objects
     * @return a ArrayList of rerolled dice
     */
    public List rollDice(List dice){
        
        for(int i = 0; i < 5; i++){
            this.die = (Die) dice.get(i);
            
            if(die.getReroll() && die.getChooseRoll()){
                int randomNum = ThreadLocalRandom.current().nextInt(0, 6);

                if(i == 2 && this.incExp1 && this.exp1Die == "LoudMouth"){
                    this.die.setFace(LoudFace[randomNum]);
                }
                else if(i == 2 && this.incExp1 && this.exp1Die == "Coward"){
                    this.die.setFace(CowardFace[randomNum]);
                }
                else if((i == 3 || i == 4) && this.incExp2){
                    this.die.setFace(DuelFace[randomNum]);
                }
                else{
                    this.die.setFace(Face[randomNum]);
                }
                
                if(Face[randomNum] == "Dynamite"){
                    die.setReroll(false);
                }
                else{
                    this.die.setReroll(true);
                }

                this.die.setChooseRoll(false);

                dice.set(i, this.die);
            }
        }
        
        return dice;
    }
    
    /**
     *
     * @return the face of the rolled duel die
     */
    public String duel(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
        this.Duel.setFace(DuelFace[randomNum]);
        return this.Duel.getFace();
    }
    
    /**
     *
     * @return the ArrayList of Die objects
     */
    public List getDice(){
        return dice;
    }
    
    /**
     *
     * @param incExp1: sets if the players chooses to include expansion 1
     */
    public void setExp1(boolean incExp1){
        this.incExp1 = incExp1;
    }
    
    /**
     *
     * @param incExp2: sets if the player chooses to include expansion 2
     */
    public void setExp2(boolean incExp2){
        this.incExp2 = incExp2;
    }
    
    
    /**
    *test case for RollDice class
     * @param args
    **/
    public static void main(String[] args)  
    {  
        String reroll;
        String choose;
        
        
        
        RollDice dices = new RollDice("LoudMouth");
        
        dices.setExp1(true);
        dices.setExp2(true); 
        
        List<Die> dice =  dices.getDice();
        
        Die die1 = (Die) dice.get(0);
        Die die2 = (Die) dice.get(1);
        Die die3 = (Die) dice.get(2);
        Die die4 = (Die) dice.get(3);
        Die die5 = (Die) dice.get(4);
        
        System.out.println("\t" + die1.getFace());
        System.out.println("\t" + die2.getFace());
        System.out.println("\t" + die3.getFace());
        System.out.println("\t" + die4.getFace());
        System.out.println("\t" + die5.getFace());
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Do you want to reroll?\nY/N: ");
        choose = input.nextLine();
        
        while(choose.contains("Y") || choose.contains("y")){
            System.out.print("Choose dice to reroll: ");

            reroll = input.nextLine();

            if(reroll.contains("1")){
                die1.setChooseRoll(true);
            }
            if(reroll.contains("2")){
                die2.setChooseRoll(true);
            }
            if(reroll.contains("3")){
                die3.setChooseRoll(true);
            }
            if(reroll.contains("4")){
                die4.setChooseRoll(true);
            }
            if(reroll.contains("5")){
                die5.setChooseRoll(true);
            }

            dice = dices.rollDice(dice);

            die1 = (Die) dice.get(0);
            die2 = (Die) dice.get(1);
            die3 = (Die) dice.get(2);
            die4 = (Die) dice.get(3);
            die5 = (Die) dice.get(4);

            System.out.println("\t" + die1.getFace());
            System.out.println("\t" + die2.getFace());
            System.out.println("\t" + die3.getFace());
            System.out.println("\t" + die4.getFace());
            System.out.println("\t" + die5.getFace());
            
            System.out.print("Do you want to reroll?\n Y/N: ");
            choose = input.nextLine();
            
            String duel = dices.duel();
            
            System.out.println("\t" + duel);
        }
    } //end of main method
}
