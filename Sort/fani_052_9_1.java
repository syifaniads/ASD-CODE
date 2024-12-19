package Sort;
import java.util.Scanner;

public class fani_052_9_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Membaca jumlah wishlist yang akan dibeli
        int masukkan = scanner.nextInt();
        scanner.nextLine(); 
        Barang[] wishlist = new Barang[masukkan];
         // Membaca wishlist
        for (int i = 0; i < masukkan; i++) {
            String[] barang = scanner.nextLine().split(" ");
            String namaBarang = barang[0];
            int hargaBarang = Integer.parseInt(barang[1]);
            wishlist[i] = new Barang(namaBarang, hargaBarang);
        }
        // Membaca nominal uang yang dimiliki Pak Ogah
        int uang = scanner.nextInt();
        // Men-sorting barang berdasarkan harga
        kodeBubbleShort(wishlist);
        // Menampilkan wishlist yang sudah diurutkan
        System.out.println("Sorted Wishlist:");
        for (Barang barang : wishlist) {
            System.out.println(barang.namaBarang + " = " + barang.hargaBarang);
        }
        // Menyeleksi barang yang akan dibeli
        System.out.println("\nRecommendation:");
        int jumlahTotalHarga = 0;
        StringBuilder barangYangDibawa = new StringBuilder();
        for (Barang barang : wishlist) {
            if (jumlahTotalHarga + barang.hargaBarang <= uang) {
                if (barangYangDibawa.length() > 0) {
                    barangYangDibawa.append(", ");
                }
                barangYangDibawa.append(barang.namaBarang);
                jumlahTotalHarga += barang.hargaBarang;
            } else {
                break;
            }
        }
        // Menampilkan rekomendasi
        if (barangYangDibawa.length() == 0) {
            System.out.println("Items bought = ");
            System.out.println("Total spent = 0");
        } else {
            System.out.println("Items bought = " + barangYangDibawa);
            System.out.println("Total spent = " + jumlahTotalHarga);
        }

        scanner.close();
    }
    // Fungsi BubbleShort
    public static void kodeBubbleShort(Barang[] barang) {
        int n = barang.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (barang[j].hargaBarang > barang[j + 1].hargaBarang) {
                    // Swap barang[j] dengan barang[j + 1]
                    Barang temp = barang[j];
                    barang[j] = barang[j + 1];
                    barang[j + 1] = temp;
                }
            }
        }
    }
}
class Barang {
    String namaBarang;
    int hargaBarang;

    Barang(String namaBarang, int hargaBarang) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }
}
