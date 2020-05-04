//CS 2450 001 
//Shreyesh Arangath 
//shreyesh.arangath@ttu.edu

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project3;

/**
 *
 * @author shreyesh
 */


public class CyclicDoublyLinkedList<T> {
    
    private Node start, end;
    public int sizeOfList =0;
    
    public CyclicDoublyLinkedList(){
        start = null;
        end = null;
    }
    
    public void insert(T data){
        Node<T> tempNewNode = new Node<>(data);
        if(start == null){
            start = tempNewNode;
            end = tempNewNode;
            tempNewNode.setNext(tempNewNode);
            tempNewNode.setPrevious(tempNewNode);
        }
        else{
            tempNewNode.setPrevious(end);
            end.setNext(tempNewNode);
            start.setPrevious(tempNewNode);
            tempNewNode.setNext(start);
            end = tempNewNode;
            
        }
        sizeOfList++;
    }
    
    public static void main(String[] args){
        CyclicDoublyLinkedList<Integer> ll = new CyclicDoublyLinkedList<>();
        ll.insert(0);
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(4);
        ll.insert(5);
        ll.insert(6);
        ll.insert(7);
        
        
        int distance =1;
        Node cur = ll.start;
        for(int i=0; i<=distance; i++){
            if(i == distance){
                System.out.println(cur.getData());
                break;
            }
            cur = cur.getNext();
        }
        
        cur = ll.start;
        for(int i=0; i<=distance; i++){
            if(i == distance){
                System.out.println(cur.getData());
                break;
            }
            cur = cur.getPrevious();
        }
    }
    
}


