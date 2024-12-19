package Sort;
import java.util.Scanner;

public class fani_052_9_2 {
    private static dataKelompok[] kelompok;
    private static int jumlahKelompok;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int masukkan = scanner.nextInt();
        scanner.nextLine(); // Move to the next line after reading integer
        kelompok = new dataKelompok[masukkan];
        jumlahKelompok = 0;

        while (masukkan-- > 0) {
            String perintah = scanner.nextLine();
            switch (perintah.split(" ")[0]) {
                case "insert":
                    String dataNilaiKelompok[] = scanner.nextLine().split(";");
                    for (String data : dataNilaiKelompok) {
                        String[] dataKelompok = data.split(" ");
                        String namaKelompok = dataKelompok[0];
                        int nilaiPemaparan = Integer.parseInt(dataKelompok[1]);
                        int nilaiKreativitas = Integer.parseInt(dataKelompok[2]);
                        int nilaiDiskusi = Integer.parseInt(dataKelompok[3]);
                        int jumlahVoting = Integer.parseInt(dataKelompok[4]);
                        String topikLomba = dataKelompok[5];
                        kelompok[jumlahKelompok++] = new dataKelompok(namaKelompok, nilaiPemaparan, nilaiKreativitas, nilaiDiskusi, jumlahVoting, topikLomba);
                    }
                    System.out.println("Berhasil memasukkan " + dataNilaiKelompok.length + " data kelompok");
                    break;

                case "find":
                    String kategori = perintah.split(" ")[1];
                    if (kategori.equals("umum")) {
                        cariJuaraUmum();
                    } else if (kategori.equals("favorit")) {
                        cariJuaraFavorit();
                    } else {
                        cariJuaraPerTopik(kategori);
                    }
                    break;
            }
        }
        scanner.close();
    }

    private static void cariJuaraUmum() {
        dataKelompok[] sortedKelompok = sortKelompokByTotal(kelompok, jumlahKelompok);
        System.out.println("Juara umum:");
        for (int i = 0; i < Math.min(5, jumlahKelompok); i++) {
            System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].namaKelompok, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVoting);
        }
    }

    private static void cariJuaraFavorit() {
        dataKelompok[] sortedKelompok = sortKelompokByVote(kelompok, jumlahKelompok);
        System.out.println("\nJuara favorit:");
        for (int i = 0; i < Math.min(2, jumlahKelompok); i++) {
            System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].namaKelompok, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVoting);
        }
    }

    private static void cariJuaraPerTopik(String topik) {
        dataKelompok[] kelompokPerTopik = new dataKelompok[jumlahKelompok];
        int jumlahPerTopik = 0;
        for (int i = 0; i < jumlahKelompok; i++) {
            if (kelompok[i].topikLomba.equalsIgnoreCase(topik)) {
                kelompokPerTopik[jumlahPerTopik++] = kelompok[i];
            }
        }
        if (jumlahPerTopik == 0) {
            System.out.printf("\nJuara %s:\n", topik);
            return;
        }
        dataKelompok[] sortedKelompok = sortKelompokByTotal(kelompokPerTopik, jumlahPerTopik);
        System.out.printf("\nJuara %s:\n", topik);
        for (int i = 0; i < Math.min(3, jumlahPerTopik); i++) {
            System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].namaKelompok, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVoting);
        }
    }

    // Fungsi BubbleSort untuk Total Score
    public static void kodeBubbleSort(dataKelompok[] kelompok, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (kelompok[j].nilaiTotal < kelompok[j + 1].nilaiTotal) {
                    // Swap kelompok[j] dengan kelompok[j + 1]
                    dataKelompok temp = kelompok[j];
                    kelompok[j] = kelompok[j + 1];
                    kelompok[j + 1] = temp;
                }
            }
        }
    }

    // Fungsi Sort untuk Total Score
    private static dataKelompok[] sortKelompokByTotal(dataKelompok[] kelompok, int jumlah) {
        dataKelompok[] sorted = new dataKelompok[jumlah];
        System.arraycopy(kelompok, 0, sorted, 0, jumlah);
        kodeBubbleSort(sorted, jumlah);
        return sorted;
    }

    // Fungsi Sort untuk Vote Count
    private static dataKelompok[] sortKelompokByVote(dataKelompok[] kelompok, int jumlah) {
        dataKelompok[] sorted = new dataKelompok[jumlah];
        System.arraycopy(kelompok, 0, sorted, 0, jumlah);
        for (int i = 0; i < jumlah - 1; i++) {
            for (int j = 0; j < jumlah - 1 - i; j++) {
                if (sorted[j].jumlahVoting < sorted[j + 1].jumlahVoting) {
                    dataKelompok temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }
}

class dataKelompok {
    String namaKelompok;
    int nilaiPemaparan;
    int nilaiKreativitas;
    int nilaiDiskusi;
    int jumlahVoting;
    String topikLomba;
    int nilaiTotal;

    dataKelompok(String namaKelompok, int nilaiPemaparan, int nilaiKreativitas, int nilaiDiskusi, int jumlahVoting, String topikLomba) {
        this.namaKelompok = namaKelompok;
        this.nilaiPemaparan = nilaiPemaparan;
        this.nilaiKreativitas = nilaiKreativitas;
        this.nilaiDiskusi = nilaiDiskusi;
        this.jumlahVoting = jumlahVoting;
        this.topikLomba = topikLomba;
        this.nilaiTotal = hitungNilaiTotal();
    }

    private int hitungNilaiTotal() {
        return (int) Math.round((nilaiPemaparan * 0.20) + (nilaiKreativitas * 0.50) + (nilaiDiskusi * 0.30));
    }

    @Override
    public String toString() {
        return namaKelompok + ": " + nilaiTotal + ", " + jumlahVoting;
    }
}

// import java.util.Scanner;
// //PakOgahMagangPanitia
// public class fani_052_9_2 {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner (System.in);
//         int masukkan = scanner.nextInt();
//         scanner.nextLine(); //ganti line karena dari integer
//         dataKelompok[] PakOgahMagangPanitia = new dataKelompok[masukkan];
//         while(masukkan--> 0){
//             String perintah = scanner.nextLine();
//             switch(perintah){
//                 case "insert":
//                 String dataNilaiKelompok[] = scanner.nextLine().split(";");
//                 for(int i=0; i < dataNilaiKelompok.length; i++){
//                     String[] dataKelompok = scanner.nextLine.split(" ");
//                     String namaKelompok = dataKelompok[0];
//                     int nilaiPemaparan = Integer.parseInt(dataKelompok[1]);
//                     int nilaiKreativitas = Integer.parseInt(dataKelompok[2]);
//                     int nilaiDiskusi = Integer.parseInt(dataKelompok[3]);
//                     int jumlahVoting = Integer.parseInt(dataKelompok[4]);
//                     String topikLomba = dataKelompok[5];
//                     kelompok[i] = new dataKelompok(namaKelompok,nilaiPemaparan, nilaiKreativitas, nilaiDiskusi, jumlahVoting, topikLomba);
//                     kodeBubbleShort(kelompok);
//                     System.out.println("Berhasil memasukkan " + dataNilaiKelompok.length + "data kelompok");
//                     break;
//                 }
//                 case "find":
//                 String kategori = perintah.split(" ")[1];
//                 if (kategori.equals("umum")){
//                     cariJuaraUmum();
//                 }else if(kategori.equals("favorit")){
//                     cariJuaraFavorit();
//                 }else{
//                     cariJuaraPerTopik(kategori);
//                 }
//             }
//         }
//         scanner.close();
//     }
//     private static void cariJuaraUmum() {
//         Kelompok[] sortedKelompok = sortKelompokByTotal(kelompok, jumlahKelompok);
//         System.out.println("Juara umum:");
//         for (int i = 0; i < Math.min(5, jumlahKelompok); i++) {
//             System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].nama, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVote);
//         }
//     }
//     private static void cariJuaraFavorit() {
//         Kelompok[] sortedKelompok = sortKelompokByVote(kelompok, jumlahKelompok);
//         System.out.println("Juara favorit:");
//         for (int i = 0; i < Math.min(2, jumlahKelompok); i++) {
//             System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].nama, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVote);
//         }
//     }
//     private static void cariJuaraPerTopik(String topik) {
//         Kelompok[] kelompokPerTopik = new Kelompok[50];
//         int jumlahPerTopik = 0;
//         for (int i = 0; i < jumlahKelompok; i++) {
//             if (kelompok[i].topik.equalsIgnoreCase(topik)) {
//                 kelompokPerTopik[jumlahPerTopik++] = kelompok[i];
//             }
//         }
//         Kelompok[] sortedKelompok = sortKelompokByTotal(kelompokPerTopik, jumlahPerTopik);
//         System.out.printf("Juara %s:\n", topik);
//         for (int i = 0; i < Math.min(3, jumlahPerTopik); i++) {
//             System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].nama, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVote);
//         }
//     }
//     // Fungsi BubbleShort
//     public static void kodeBubbleShort(Barang[] barang) {
//         int n = barang.length;
//         for (int i = 0; i < n - 1; i++) {
//             for (int j = 0; j < n - 1 - i; j++) {
//                 if (barang[j].hargaBarang > barang[j + 1].hargaBarang) {
//                     // Swap barang[j] dengan barang[j + 1]
//                     Barang temp = barang[j];
//                     barang[j] = barang[j + 1];
//                     barang[j + 1] = temp;
//                 }
//             }
//         }
//     }
//     private static void cariJuaraPerTopik(String topik) {
//         Kelompok[] kelompokPerTopik = new Kelompok[50];
//         int jumlahPerTopik = 0;
//         for (int i = 0; i < jumlahKelompok; i++) {
//             if (kelompok[i].topik.equalsIgnoreCase(topik)) {
//                 kelompokPerTopik[jumlahPerTopik++] = kelompok[i];
//             }
//         }
//         Kelompok[] sortedKelompok = sortKelompokByTotal(kelompokPerTopik, jumlahPerTopik);
//         System.out.printf("Juara %s:\n", topik);
//         for (int i = 0; i < Math.min(3, jumlahPerTopik); i++) {
//             System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].nama, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVote);
//         }
//     }

//     private static Kelompok[] sortKelompokByTotal(Kelompok[] kelompok, int jumlah) {
//         Kelompok[] sorted = new Kelompok[jumlah];
//         System.arraycopy(kelompok, 0, sorted, 0, jumlah);
//         for (int i = 0; i < jumlah - 1; i++) {
//             for (int j = 0; j < jumlah - 1 - i; j++) {
//                 if (sorted[j].nilaiTotal < sorted[j + 1].nilaiTotal) {
//                     Kelompok temp = sorted[j];
//                     sorted[j] = sorted[j + 1];
//                     sorted[j + 1] = temp;
//                 }
//             }
//         }
//         return sorted;
//     }

//     private static Kelompok[] sortKelompokByVote(Kelompok[] kelompok, int jumlah) {
//         Kelompok[] sorted = new Kelompok[jumlah];
//         System.arraycopy(kelompok, 0, sorted, 0, jumlah);
//         for (int i = 0; i < jumlah - 1; i++) {
//             for (int j = 0; j < jumlah - 1 - i; j++) {
//                 if (sorted[j].jumlahVote < sorted[j + 1].jumlahVote) {
//                     Kelompok temp = sorted[j];
//                     sorted[j] = sorted[j + 1];
//                     sorted[j + 1] = temp;
//                 }
//             }
//         }
//         return sorted;
//     }
// }


// class dataKelompok{
//     String namaKelompok;
//     int nilaiPemaparan;
//     int nilaiKreativitas;
//     int nilaiDiskusi;
//     int jumlahVoting;
//     String topikLomba;
//     int nilaiTotal;

//     dataKelompok(String namaKelompok, int nilaiPemaparan, int nilaiKreativitas, int nilaiDiskusi, int jumlahVoting, String topikLomba){
//         this.namaKelompok = namaKelompok;
//         this.nilaiPemaparan = nilaiPemaparan;
//         this.nilaiKreativitas = nilaiKreativitas;
//         this.nilaiDiskusi = nilaiDiskusi;
//         this.jumlahVoting = jumlahVoting;
//         this.topikLomba = topikLomba;
//     }
// }
