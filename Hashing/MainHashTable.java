package Hashing;
import java.util.Scanner;
public class MainHashTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        HashTable hashTable = new HashTable(size);
        scanner.nextLine();
        int operationsCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (int i = 0; i < operationsCount; i++) {
            String operation = scanner.nextLine().trim();
            if (operation.startsWith("tambah")) {
                hashTable.tambah(scanner.nextLine().trim());
            } else if (operation.startsWith("cari")) {
                int nis = scanner.nextInt();
                hashTable.cari(nis);
            } else if (operation.startsWith("hapus")) {
                int nis = scanner.nextInt();
                scanner.nextLine();
                hashTable.hapus(nis);
            }
        }

        scanner.close();
    }
}

class HashTable {
    private Siswa[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.table = new Siswa[size];
    }

    private int hashFunction(int nis) {
        return nis % size;
    }
    public void tambah(String data) {
        String[] entries = data.split(";");
        int count = 0;

        for (String entry : entries) {
            String[] parts = entry.trim().split(" ", 2);
            if (parts.length < 2) {
                System.out.println("Format input tidak valid.");
                return;
            }
            int nis;
            try {
                nis = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                System.out.println("NIS harus berupa bilangan bulat.");
                return;
            }
            String nama = parts[1];

            int index = hashFunction(nis);
            int originalIndex = index;

            while (table[index] != null && table[index].nis != nis) {
                index = (index + 1) % size;
                if (index == originalIndex) {
                    // Table is full
                    System.out.println("Table penuh, tidak dapat menambahkan data lebih lanjut.");
                    return;
                }
            }

            if (table[index] == null) {
                table[index] = new Siswa(nis, nama);
                count++;
            }
            System.out.println(index);
            System.out.println(originalIndex);
        }

        System.out.println("Berhasil memasukkan " + count + " data siswa");
    }
     public void cari(int nis) {
        int index = hashFunction(nis);
        int originalIndex = index;

        while (table[index] != null) {
            if (table[index].nis == nis) {
                System.out.println("NIS " + nis + " adalah " + table[index].nama + ", ditemukan pada indeks " + index);
                return;
            }
            index = (index + 1) % size;
            if (index == originalIndex) {
                break;
            }
        }
        System.out.println("Data siswa dengan NIS " + nis + " tidak ditemukan");
    }
    public void hapus(int nis) {
        int index = hashFunction(nis);
        int originalIndex = index;

        while (table[index] != null) {
            if (table[index].nis == nis) {
                table[index] = null; // Menandai entri sebagai dihapus
                System.out.println("Data siswa dengan NIS " + nis + " berhasil dihapus");
                return;
            }
            index = (index + 1) % size;
            if (index == originalIndex) {
                break;
            }
        }
        System.out.println("Data siswa dengan NIS " + nis + " tidak ditemukan");
    }

    private class Siswa {
        int nis;
        String nama;

        Siswa(int nis, String nama) {
            this.nis = nis;
            this.nama = nama;
        }
    }
}
