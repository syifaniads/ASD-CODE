package SinglyLinkedListPakOgah;

import java.util.Scanner;
import java.util.*;

class SLL {
    Node head;
        public void cari(String nomorPlat) {
        Node temp = head;
        while (temp != null) {
            if (temp.nomorPlat.equals(nomorPlat)) {
                System.out.println("Kendaraan sedang parkir");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Kendaraan tidak ada");
    }
        public void hapus(String nomorPlat) {
        Node temp = head, prev = null;
        while (temp != null && !temp.nomorPlat.equals(nomorPlat)) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Tidak dapat menghapus, kendaraan tidak ditemukan");
        } else {
            if (prev == null) {
                head = temp.next;
            } else {
                prev.next = temp.next;
            }
            System.out.println(temp.namaPengemudi + " berhasil keluar parkiran");
        }
    }
    public void tambah(String namaPengemudi, String nomorPlat) {
        Node newNode = new Node(namaPengemudi, nomorPlat);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println(namaPengemudi + " berhasil memakirkan kendaraan");
    }
}

public class ParkiranPakOgah {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int masukkan = Integer.parseInt(scanner.nextLine().trim());
        SLL parkirList = new SLL();

        int i = 0;
        while (i < masukkan) {
            String perintah = scanner.nextLine().trim();
            if (perintah.equals("KENDARAAN MASUK")) {
                String namaPengemudi = scanner.nextLine().trim();
                String noPlat = scanner.nextLine().trim();
                parkirList.tambah(namaPengemudi, noPlat); 
            } else if (perintah.equals("KENDARAAN KELUAR")) {
                String noPlatKeluar = scanner.nextLine().trim();
                parkirList.hapus(noPlatKeluar); 
            } else if (perintah.equals("CARI KENDARAAN")) {
                String noPlatCari = scanner.nextLine().trim();
                parkirList.cari(noPlatCari); 
            } else {
                System.out.println("Perintah tidak valid.");
            }
            i++;
        }
        scanner.close();
    }
}
class Node {
    String namaPengemudi;
    String nomorPlat;
    Node next;

    public Node(String namaPengemudi, String nomorPlat) {
        this.namaPengemudi = namaPengemudi;
        this.nomorPlat = nomorPlat;
        this.next = null;
    }
}