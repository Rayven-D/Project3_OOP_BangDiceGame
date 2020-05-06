/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author shreyesh
 * @param <T> The type of layout user decides to design in the interface
 */
public interface Components<T> {
    
    /**
     *  The padding size associated with the Component Interface
     */
    public final int PADDING_SIZE = 20;

    /**
     *
     * @return The layout they want to output
     */
    public T display();
    
}
