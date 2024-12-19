package Modul4PraktikumQueue;
import java.util.Scanner;

public class Queue_dll<S> {
    Scanner scanner = new Scanner(System.in);
    private Node<S> head, tail;
    private int size;

    public Queue_dll() {
        head = null;
        tail = null;
        size = 0;
    }
    public static void main(String[] args) {
        Queue_dll<Character> queue = new Queue_dll<>();
        int keluar = 0;
        do {
            queue.menu();
        } while (keluar == 0);
    }
    public void enqueue(S item) {
        Node<S> newNode = new Node<>(item);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return;
        }
        System.out.println("Dequeued Value: " + tail.data);
        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }
    public void printAll() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return;
        }
        Node<S> current = head;
        System.out.println("Isi Queue:");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void menu() {
        System.out.println("\n===================================");
        System.out.println("Ketik 1/enqueue ");
        System.out.println("Ketik 2/dequeue ");
        System.out.println("Ketik 3/print ");
        System.out.print("Masukkan operasi yang akan dilakukan: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Masukkan item yang akan di-enqueue : ");
                String nilai = scanner.next().substring(0, 1);
                enqueue((S) (Character) nilai.charAt(0));
                break;
            case 2:
                dequeue();
                break;
            case 3:
                printAll();
                break;
            default:
                System.out.println("\nOperasi tidak valid");
                break;
        }
    }
}
class Node<S> {
    S data;
    Node<S> next;
    Node<S> prev;

    Node(S data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
// package Modul4PraktikumQueue;
// import java.util.Scanner;

// public class Queue_dll {
//     Scanner scanner = new Scanner(System.in);
//     private Node head, tail;
//     private int size;

//     public Queue_dll() {
//         head = null;
//         tail = null;
//         size = 0;
//     }
//     public static void main(String[] args) {
//         Queue_dll queue = new Queue_dll();
//         int keluar = 0;
//         do {
//             queue.menu();
//         } while (keluar == 0);
//     }
//     public void enqueue(char item) {
//         Node newNode = new Node(item);
//         if (isEmpty()) {
//             head = tail = newNode;
//         } else {
//             newNode.next = head;
//             head.prev = newNode;
//             head = newNode;
//         }
//         size++;
//     }
//     public void dequeue() {
//         if (isEmpty()) {
//             System.out.println("Queue kosong");
//             return;
//         }
//         System.out.println("Dequeued Value: " + tail.data);
//         if (size == 1) {
//             head = tail = null;
//         } else {
//             tail = tail.prev;
//             tail.next = null;
//         }
//         size--;
//     }
//     public void printAll() {
//         if (isEmpty()) {
//             System.out.println("Queue kosong");
//             return;
//         }
//         Node current = head;
//         System.out.println("Isi Queue:");
//         while (current != null) {
//             System.out.print(current.data + " ");
//             current = current.next;
//         }
//         System.out.println();
//     }
//     public boolean isEmpty() {
//         return size == 0;
//     }
//     public void menu() {
//         System.out.println("\n===================================");
//         System.out.println("Ketik 1/enqueue ");
//         System.out.println("Ketik 2/dequeue ");
//         System.out.println("Ketik 3/print ");
//         System.out.print("Masukkan operasi yang akan dilakukan: ");
//         int choice = scanner.nextInt();
//         switch (choice) {
//             case 1:
//                 System.out.print("Masukkan huruf yang akan di-enqueue : ");
//                 char item = scanner.next().charAt(0);
//                 enqueue(item);
//                 break;
//             case 2:
//                 dequeue();
//                 break;
//             case 3:
//                 printAll();
//                 break;
//             default:
//                 System.out.println("\nOperasi tidak valid");
//                 break;
//         }
//     }
// }
// class Node {
//     char data;
//     Node next;
//     Node prev;

//     Node(char data) {
//         this.data = data;
//         this.next = null;
//         this.prev = null;
//     }
// }
