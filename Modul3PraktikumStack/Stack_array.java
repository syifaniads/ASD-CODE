package Modul3PraktikumStack;
import java.util.Scanner;

public class Stack_array<T> {
    private Scanner scanner = new Scanner(System.in);
    private static final int MAX_SIZE = 20;
    private Object[] arrStack = new Object[MAX_SIZE];
    private int count = 0;
    private int keluar=0;
    public static void main(String[] args) {
        Stack_array<Character> stackArray = new Stack_array<>();
        do {
            stackArray.menu();
        } while (stackArray.keluar == 0);
    }
    public void push(T x) {
        if (count == MAX_SIZE) {
            System.out.print("\nStack Penuh");
        } else {
            arrStack[count++] = x;
            System.out.print("\n[PUSH] No urut/index : " + (count - 1) + ", Push : " + x);
        }
    }
    public void pop() {
        if (count == 0) {
            System.out.print("\nStack kosong");
        } else {
            System.out.print("\nPOP No urut/index : " + (count - 1) + ", Nilai : " + arrStack[--count]);
        }
    }
    public void printAll() {
        System.out.print("\nUkuran Stack : " + count);
        for (int i = count - 1; i >= 0; i--) {
            System.out.print("\nNo Urut/index : " + i + ", Nilai : " + arrStack[i]);
        }
    }
    public void menu() {
        System.out.print("\n===================================");
        System.out.print("\nKetik 1/push ");
        System.out.print("\nKetik 2/pop ");
        System.out.print("\nKetik 3/print ");
        System.out.print("\nMasukkan operasi yang akan dilakukan: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: 
            {
                System.out.print("Masukkan huruf yang akan dipush : ");
                Scanner input = new Scanner(System.in);
                String x = input.next().substring(0, 1);
                push((T) (Character) x.charAt(0));
            }
            break;
            case 2:
            pop();
            break;
            case 3:
            printAll();
            break;
            default:
            System.out.println("===================================");
            System.out.print("\nKetik 1/push ");
            System.out.print("\nKetik 2/pop ");
            System.out.print("\nKetik 3/print ");
            System.out.println("===================================");
            keluar = 1;
            break;
        }
    }
}
   