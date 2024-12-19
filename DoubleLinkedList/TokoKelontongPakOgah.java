package DoubleLinkedList;

import java.util.Scanner;
import java.util.*;

class DLL<S> {
    Node<S> head, tail;

    DLL(){
        this.head = null;
        this.tail = null;
    }
        public void isExist(S barang) {
        Node<S> current = head;
        while (current != null) {
            if (current.data.equals(barang)) {
                System.out.println("Barang = " + current.data + "\nHarga = " + current.harga);
                System.out.println();
                return;
            }
            current = current.next;
        }
        System.out.println("Barang tidak ditemukan");
        System.out.println();
    }
    public void add(S barang, int price) {
        Node<S> input = new Node<>(barang, price);
        if (head == null) {
            head = input;
            tail = input;
        } else {
            input.next = head;
            head.prev = input;
            head = input;
        }
        System.out.println(barang + " berhasil ditambah");
        System.out.println();
    }
    public void count() {
        if (tail == null) {
            System.out.println("Tidak ada transaksi yang sedang dilakukan");
            System.out.println();
            return;
        }
        int total = 0;
        Node<S> current = head;
        while (current != null) {
            total += current.harga;
            current = current.next;
        }
        System.out.println("Total = " + total);
        System.out.println();
    }
    public void remove(S barang) {
        Node<S> curr = head;
        while (curr != null) {
            if (curr.data.equals(barang)) {
                if (curr == head && curr == tail) {
                    head = null;
                    tail = null;
                } else if (curr == head) {
                    head = head.next;
                    head.prev = null;
                } else if (curr == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }
                System.out.println(barang + " berhasil dihapus");
                System.out.println();
                return;
            }
            curr = curr.next;
        }
        System.out.println("Tidak dapat menghapus, barang tidak ditemukan");
        System.out.println();
    }
    public void print() {
        if (head == null) {
            System.out.println("Belum ada transaksi yang dibuat");
            System.out.println();
            return;
        }
        Node<S> current = head;
        System.out.print("Daftar Transaksi : ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println();
        System.out.println();
    }
}
public class TokoKelontongPakOgah{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        DLL<String> tokoKelontongPakOgah = new DLL<>();
        for (int i = 0; i < n; i++) {
            String perintah = scanner.nextLine();
            switch (perintah) {
                case "TAMBAH":
                    String barang = scanner.nextLine();
                    int price = scanner.nextInt();
                    if(scanner.hasNext()){
                       scanner.nextLine(); 
                    }
                    tokoKelontongPakOgah.add(barang, price);
                    break;
                case "HAPUS":
                    String hm = scanner.nextLine();
                    tokoKelontongPakOgah.remove(hm);
                    break;
                case "CARI":
                    String ehe = scanner.nextLine();
                    tokoKelontongPakOgah.isExist(ehe);
                    break;
                case "HITUNG":
                    tokoKelontongPakOgah.count();
                    break;
                case "CETAK":
                    tokoKelontongPakOgah.print();
                    break;
                default:
                    System.out.println("Perintah tidak valid");
                    break;
            }
        }
    }
}
class Node<S> {
    S data;
    int harga;
    Node<S> prev;
    Node<S> next;

    Node(S data, int harga) {
        this.data = data;
        this.harga = harga;
        this.prev = null;
        this.next = null;
    }
}
