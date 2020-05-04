package project3;

//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shreyesh
 * @param <T>
 */
public class Node<T> {
        
    private T data;
    private Node next, previous;
    
    /**
     *
     * @param data Data to be inserted into the node
     */
    public Node(T data){
        this.data = data;
        this.next = null;
        this.previous = null;  
    }
    
    /**
     *
     * @return Get the next node
     */
    public Node getNext(){
        return this.next;
    }
    
    /**
     *
     * @param next The next node to be set
     */
    public void setNext(Node next){
        this.next = next;
    }
    
    /**
     *
     * @return Get the previous node
     */
    public Node getPrevious(){
        return this.previous;
    }
    
    /**
     *
     * @param previous The previous node to be set
     */
    public void setPrevious(Node previous){
        this.previous = previous;
    }
    
    /**
     *
     * @param data Data to be set
     */
    public void setData(T data){
        this.data = data;
    }
    
    /**
     *
     * @return Get the data from the current node
     */
    public T getData(){
        return this.data;
    }
    
}
