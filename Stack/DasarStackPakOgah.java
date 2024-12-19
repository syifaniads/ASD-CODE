package Stack;

import java.io.*;
import java.util.*;

class Stack<T> {
    NodeStack<T> head, tail;
    int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public T pop() {
        if (size == 0) {
            return null;
        }
        T dataSebelumnya = tail.data;
        if (size == 1) {
            head = tail = null;
        } else {
            NodeStack<T> pointer = head;
            while (pointer.next != tail) {
                pointer = pointer.next;
            }
            pointer.next = null;
            tail = pointer;
        }
        size--;
        return dataSebelumnya;
    }
    public void push(T data) {
        NodeStack<T> scanner = new NodeStack<>(data);
        if (isEmpty()) {
            head = tail = scanner;
        } else {
            tail.next = scanner;
            tail = scanner;
        }
        size++;
    }
}
public class DasarStackPakOgah {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String masukkanKata= scanner.nextLine();
        // mengubah ke huruf kecil semua
        String cleanedString = masukkanKata.replaceAll("\\s+", "").toLowerCase();
        switch (isPalindrome(cleanedString) ? "palindrome" : "notPalindrome") {
            case "palindrome":
                System.out.println(masukkanKata+ " adalah palindrome.");
                break;
            case "notPalindrome":
                System.out.println(masukkanKata+ " bukan palindrome.");
                break;
        }
    }
    public static boolean isPalindrome(String str) {
        Stack<Character> stack = new Stack<>();
        int length = str.length();
        int mid = length / 2;
        // Memasukkan setengah karakter pertama ke dalam stack
        for (int i = 0; i < mid; i++) {
            stack.push(str.charAt(i));
        }
        // periksa karakter setelah setengah bagian dengan keluar dari stack jika panjang string ganjil, maka karakter di tengah tidak perlu dibandingkan
        int i = mid + (length % 2 == 0 ? 0 : 1);
        while (i < length) {
            char currentChar = str.charAt(i);
            Character poppedChar = stack.pop();

            if (poppedChar == null || currentChar != poppedChar) {
                return false;
            }
            i++;
        }

        return true;
    }
}
class NodeStack<T> {
    T data;
    NodeStack<T> next;

    NodeStack(T data) {
        this.data = data;
        this.next = null;
    }
}
