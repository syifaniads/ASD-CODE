package Sort;
import java.util.Scanner;

class Kelompok {
    String nama;
    int nilaiPemaparan;
    int nilaiKreativitas;
    int nilaiDiskusi;
    int jumlahVote;
    String topik;
    int nilaiTotal;

    Kelompok(String nama, int nilaiPemaparan, int nilaiKreativitas, int nilaiDiskusi, int jumlahVote, String topik) {
        this.nama = nama;
        this.nilaiPemaparan = nilaiPemaparan;
        this.nilaiKreativitas = nilaiKreativitas;
        this.nilaiDiskusi = nilaiDiskusi;
        this.jumlahVote = jumlahVote;
        this.topik = topik;
        this.nilaiTotal = (int) Math.round((nilaiPemaparan * 0.2) + (nilaiKreativitas * 0.5) + (nilaiDiskusi * 0.3));
    }
}

public class Main {
    static Kelompok[] kelompok = new Kelompok[150]; // maksimal 50 per topik x 3 topik
    static int jumlahKelompok = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int jumlahPerintah = scanner.nextInt();
        scanner.nextLine(); // Mengonsumsi karakter newline

        for (int i = 0; i < jumlahPerintah; i++) {
            String perintah = scanner.nextLine();
            if (perintah.startsWith("insert")) {
                if (perintah.length() > 7) { // Pastikan ada data setelah "insert"
                    String data = perintah.substring(7);
                    String[] kelompokData = data.split(";");
                    for (String dataKelompok : kelompokData) {
                        String[] atribut = dataKelompok.trim().split(" ");
                        String nama = atribut[0];
                        int nilaiPemaparan = Integer.parseInt(atribut[1]);
                        int nilaiKreativitas = Integer.parseInt(atribut[2]);
                        int nilaiDiskusi = Integer.parseInt(atribut[3]);
                        int jumlahVote = Integer.parseInt(atribut[4]);
                        String topik = atribut[5];

                        if (jumlahKelompok < 150) {
                            kelompok[jumlahKelompok++] = new Kelompok(nama, nilaiPemaparan, nilaiKreativitas, nilaiDiskusi, jumlahVote, topik);
                        }
                    }
                    System.out.println("Berhasil memasukkan " + kelompokData.length + " data kelompok");
                } else {
                    System.out.println("Tidak ada data untuk dimasukkan");
                }
            } else if (perintah.startsWith("find")) {
                String kategori = perintah.split(" ")[1];
                if (kategori.equals("umum")) {
                    cariJuaraUmum();
                } else if (kategori.equals("favorit")) {
                    cariJuaraFavorit();
                } else {
                    cariJuaraPerTopik(kategori);
                }
            }
        }

        scanner.close();
    }

    private static void cariJuaraUmum() {
        Kelompok[] sortedKelompok = sortKelompokByTotal(kelompok, jumlahKelompok);
        System.out.println("Juara umum:");
        for (int i = 0; i < Math.min(5, jumlahKelompok); i++) {
            System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].nama, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVote);
        }
    }

    private static void cariJuaraFavorit() {
        Kelompok[] sortedKelompok = sortKelompokByVote(kelompok, jumlahKelompok);
        System.out.println("Juara favorit:");
        for (int i = 0; i < Math.min(2, jumlahKelompok); i++) {
            System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].nama, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVote);
        }
    }

    private static void cariJuaraPerTopik(String topik) {
        Kelompok[] kelompokPerTopik = new Kelompok[50];
        int jumlahPerTopik = 0;
        for (int i = 0; i < jumlahKelompok; i++) {
            if (kelompok[i].topik.equalsIgnoreCase(topik)) {
                kelompokPerTopik[jumlahPerTopik++] = kelompok[i];
            }
        }
        Kelompok[] sortedKelompok = sortKelompokByTotal(kelompokPerTopik, jumlahPerTopik);
        System.out.printf("Juara %s:\n", topik);
        for (int i = 0; i < Math.min(3, jumlahPerTopik); i++) {
            System.out.printf("%d. %s: %d, %d\n", i + 1, sortedKelompok[i].nama, sortedKelompok[i].nilaiTotal, sortedKelompok[i].jumlahVote);
        }
    }

    private static Kelompok[] sortKelompokByTotal(Kelompok[] kelompok, int jumlah) {
        Kelompok[] sorted = new Kelompok[jumlah];
        System.arraycopy(kelompok, 0, sorted, 0, jumlah);
        for (int i = 0; i < jumlah - 1; i++) {
            for (int j = 0; j < jumlah - 1 - i; j++) {
                if (sorted[j].nilaiTotal < sorted[j + 1].nilaiTotal) {
                    Kelompok temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    private static Kelompok[] sortKelompokByVote(Kelompok[] kelompok, int jumlah) {
        Kelompok[] sorted = new Kelompok[jumlah];
        System.arraycopy(kelompok, 0, sorted, 0, jumlah);
        for (int i = 0; i < jumlah - 1; i++) {
            for (int j = 0; j < jumlah - 1 - i; j++) {
                if (sorted[j].jumlahVote < sorted[j + 1].jumlahVote) {
                    Kelompok temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }
}
