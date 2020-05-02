/**
*CS 2365, section 1, Spring 2020
*Cole Trammell
*Project 3
 */
package project3;

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author Cole Trammell
 */
public class RollDice {
    
    private LinkedList dice = new LinkedList();
    
    String[] Face = new String[]{"Arrow", "One", "Two", "Dynamite", "Gatling", "Beer"};
    
    Die die = new Die("one",true,false);
    
    /**
     *constructor for RollDice object
     */
    public RollDice(){
        Die die1 = new Die("One",true,true);
        Die die2 = new Die("One",true,true);
        Die die3 = new Die("One",true,true);
        Die die4 = new Die("One",true,true);
        Die die5 = new Die("One",true,true);
        Die die6 = new Die("One",true,true);
        
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);
        dice.add(die6);
        
        dice = rollDice(dice);
        
    }
    
    /**
     * rolls the dice if the player chooses to and they are able to be rolled
     * @param dice: the LinkedList of Die objects
     * @return a LinkedList of rerolled dice
     */
    public LinkedList rollDice(LinkedList dice){
        
        for(int i = 0; i < 6; i++){
            this.die = (Die) dice.get(i);
            
            if(die.getReroll() && die.getChooseRoll()){
                int randomNum = ThreadLocalRandom.current().nextInt(0, 6);

                this.die.setFace(Face[randomNum]);

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
     * @param remainingDice - The remaining dice that the user has not chosen
     * @return A set of new re-rolled dice
     */
    public LinkedList reRollDice(LinkedList remainingDice){
        String face;
        boolean reRoll;
        boolean chooseRoll = false; 
        
        for(int i=0 ; i<remainingDice.size(); i++){
            Die curDie = (Die) remainingDice.get(i);
            if(curDie.getReroll()==false){
                return remainingDice;
            }
        }
        for(int i=0; i<remainingDice.size(); i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0,6);
            face = Face[randomNum];
            reRoll = face.equals("Dynamite")? false: true;
            Die curDie = new Die(face, reRoll, chooseRoll);
            remainingDice.set(i, curDie);
        }
        return remainingDice;
    }
    
    
    /**
     *
     * @return the linkedList of Die objects
     */
    public LinkedList getDice(){
        return dice;
    }
    /**
    *test case for RollDice class
     * @param args
    **/
    public static void main(String[] args)  
    {  
        String reroll;
        String choose;
        
        RollDice dices = new RollDice();
        
        LinkedList dice =  dices.getDice();
        
        Die die1 = (Die) dice.get(0);
        Die die2 = (Die) dice.get(1);
        Die die3 = (Die) dice.get(2);
        Die die4 = (Die) dice.get(3);
        Die die5 = (Die) dice.get(4);
        Die die6 = (Die) dice.get(5);
        
        System.out.println("\t" + die1.getFace());
        System.out.println("\t" + die2.getFace());
        System.out.println("\t" + die3.getFace());
        System.out.println("\t" + die4.getFace());
        System.out.println("\t" + die5.getFace());
        System.out.println("\t" + die6.getFace());
        
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
            if(reroll.contains("6")){
                die6.setChooseRoll(true);
            }

            dice = dices.rollDice(dice);

            die1 = (Die) dice.get(0);
            die2 = (Die) dice.get(1);
            die3 = (Die) dice.get(2);
            die4 = (Die) dice.get(3);
            die5 = (Die) dice.get(4);
            die6 = (Die) dice.get(5);

            System.out.println("\t" + die1.getFace());
            System.out.println("\t" + die2.getFace());
            System.out.println("\t" + die3.getFace());
            System.out.println("\t" + die4.getFace());
            System.out.println("\t" + die5.getFace());
            System.out.println("\t" + die6.getFace());
            
            System.out.print("Do you want to reroll?\n Y/N: ");
            choose = input.nextLine();
        }
    } //end of main method
}