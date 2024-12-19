package DoubleLinkedList;

import java.io.*;
import java.util.*;

public class DasarCDLLPakOgah{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        DLL dll = new DLL();
        
        while (n-- > 0) {
            String[] input = scanner.nextLine().split(" ");
            
            if (input[0].equalsIgnoreCase("ADD")) {
                if (input[1].equalsIgnoreCase("FIRST")) {
                    dll.addFirst(Integer.parseInt(input[2]));
                } else if (input[1].equalsIgnoreCase("LAST")) {
                    dll.addLast(Integer.parseInt(input[2]));
                }
            } else if (input[0].equalsIgnoreCase("PRINT")) {
                if (input[1].equalsIgnoreCase("ALL")) {
                    dll.printToLast();
                } else if (input[1].equalsIgnoreCase("SPECIAL")) {
                    dll.printSpecial();
                }
            } else if (input[0].equalsIgnoreCase("REMOVE")) {
                if (input[1].equalsIgnoreCase("FIRST")) {
                    dll.removeFirst();
                } else if (input[1].equalsIgnoreCase("LAST")) {
                    dll.removeLast();
                }
            } else if (input[0].equalsIgnoreCase("IS")) {
                if (!dll.isExist(Integer.parseInt(input[2]))) {
                    System.out.println("NO");
                } else {
                    System.out.println("YES");
                }
            }
        }
    }
}
class DLL{
    NodeDLL head, tail;
    int size = 0;
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void addLast(int data) {
        NodeDLL input = new NodeDLL(data);
        if (isEmpty()) {
            head = tail = input;
        } else {
            input.prev = tail;
            tail.next = input;
            tail = input;
        }
        tail.next = head;
        head.prev = tail;
        size++;
    }
    public void addFirst(int data) {
        NodeDLL input = new NodeDLL(data);
        if (isEmpty()) {
            head = tail = input;
        } else {
            input.next = head;
            head.prev = input;
            head = input;
        }
        tail.next = head;
        head.prev = tail;
        size++;
    }    
    public void removeLast() {
        if (isEmpty()) {
            System.out.println("DATA IS EMPTY!");
            return;
        }
        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
        size--;
    }
    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("DATA IS EMPTY!");
            return;
        }
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        size--;
    }    
    public void printToLast() {
        if (isEmpty()){
            return;
        }
        NodeDLL pointer = head;
        while (pointer != tail) {
            System.out.print(pointer.data + " ");
            pointer = pointer.next;
        }
        System.out.print(pointer.data);
        System.out.println();
    }
    public boolean isExist(int data) {
        NodeDLL pointer = head;
        while (pointer != null) {
            if (pointer.data == data)
                return true;
            pointer = pointer.next;
            if (pointer == head)
                break;
        }
        return false;
    }
    public void printSpecial(){
        NodeDLL pointer = tail;
        while (pointer.next!=tail){
            System.out.print(pointer.data + " ");
            pointer = pointer.next;
        }
        System.out.print(pointer.data);
        System.out.println();
    }
}
class NodeDLL{
    NodeDLL next;
    NodeDLL prev;
    int data;
    
    public NodeDLL(int data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}