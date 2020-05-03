/**
*CS 2365, section 1, Spring 2020
*Cole Trammell
*Project 3
 */
package project3.project3_OOP;

/**
 *
 * @author Cole Trammell
 */
public class Die {
    
    /**
     * the face of the die that is visible
     */
    private String face;
    
    /**
     * says if the die can be rerolled or not
     */
    private boolean reroll;
    
     /**
     * says if the player chose to roll the dice or not
     */
    private boolean chooseRoll;
    
    /**
     *constructor for Die Object
     * @param face: a string that denotes the face of the die that was rolled
     * @param reroll: a boolean that denotes if a die can be rerolled
     * @param chooseRoll: a boolean that denotes if a player wanted to reroll a die
     */
    public Die(String face, boolean reroll, boolean chooseRoll){
        setFace(face);
        setReroll(reroll);
        setChooseRoll(chooseRoll);
    }

    /**
     *sets the face attribute
     * @param face: a string that denotes the face of the die that was rolled
     */
    public void setFace(String face){
        this.face = face;
    }
    
    /**
     *sets the reroll attribute
     * @param reroll: a boolean that denotes if a die can be rerolled
     */
    public void setReroll(boolean reroll){
        this.reroll = reroll;
    }
    
    /**
     *sets the chooseRoll attribute
     * @param chooseRoll: a boolean that denotes if a player wanted to reroll a die
     */
    public void setChooseRoll(boolean chooseRoll){
        this.chooseRoll = chooseRoll;
    }
    
    /**
     *
     * @return face attribute
     */
    public String getFace(){
        return this.face;
    }
    
    /**
     *
     * @return reroll attribute
     */
    public boolean getReroll(){
        return this.reroll;
    }
    
    /**
     *
     * @return chooseRoll attribute
     */
    public boolean getChooseRoll(){
        return this.chooseRoll;
    }
    
    /**
    *test case for Die class
     * @param args
    **/
    public static void main(String[] args)  
    {  
        Die die = new Die("One", true,false);
        System.out.println(die.getFace());
        System.out.println(die.getReroll());
    } //end of main method
    
    
}