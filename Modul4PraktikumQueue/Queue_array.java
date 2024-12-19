package Modul4PraktikumQueue;
import java.util.Scanner;

public class Queue_array{
    Scanner scanner = new Scanner(System.in);
    private int choice, i;
    private char item; 
    private final int MAX_SIZE = 100; 
    private char[] arr_stack = new char[MAX_SIZE]; 
    private int count = 0;
    private static int keluar = 0;
    private int rear = 0, front = 0;

    public static void main(String[] args) {
        Queue_array queue = new Queue_array();
        do {
            queue.menu();
        } while (keluar == 0);
    }
    public void enqueue(char item) { 
        if (rear == MAX_SIZE) {
            System.out.print("\n Queue Penuh");
        } else {
            System.out.print("\n Queue No.urut (index) : " + rear + ", Queue : " + item);
            arr_stack[rear++] = item; 
        }
    }
    public void dequeue() {
        if (front == rear)
            System.out.print("\n Queue kosong");
        else {
            System.out.print("\n DeQueue Value : " + arr_stack[front]);
            front++;
        }
    }
    public void printAll() {
        System.out.print("\n Queue Size : " + (rear - front));
        for (i = front; i < rear; i++)
            System.out.print("\n No.Urut (index) : " + (i - front) + ", Value : " + arr_stack[i]);
    }
    public void menu() {
        System.out.print("\n===================================");
        System.out.print("\nKetik 1/enquque ");
        System.out.print("\nKetik 2/dequeue ");
        System.out.print("\nKetik 3/print ");
        System.out.print("\nMasukkan operasi yang akan dilakukan: ");
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                {
                    System.out.print("Masukkan huruf yang akan di-enqueue : ");
                    item = scanner.next().charAt(0);
                    enqueue(item);
                }
                break;
            case 2:
                dequeue();
                break;
            case 3:
                printAll();
                break;
            default:
            System.out.print("\n===================================");
            System.out.print("\nKetik 1/enqueue ");
            System.out.print("\nKetik 2/dequeue ");
            System.out.print("\nKetik 3/print ");
            System.out.print("\nMasukkan operasi yang akan dilakukan: ");
                rear = 0;
                break;
        }
    }
}
