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
 */
public class Node<T> {
        
    private T data;
    private Node next, previous;
    
    public Node(T data){
        this.data = data;
        this.next = null;
        this.previous = null;  
    }
    
    public Node getNext(){
        return this.next;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
    
    public Node getPrevious(){
        return this.previous;
    }
    
    public void setPrevious(Node previous){
        this.previous = previous;
    }
    
    public void setData(T data){
        this.data = data;
    }
    
    public T getData(){
        return this.data;
    }
}
