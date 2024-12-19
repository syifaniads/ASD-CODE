package OOPCollectionsGeneric;
import java.util.*;

class Barang {
    String kodeBarang;
    String namaBarang;
    String kategori;
    String harga;
    String stok;

    Barang(String kodeBarang, String namaBarang, String kategori, String harga, String stok) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }
}
public class PengelompokanDataBarangPakOgah{
    public static void main(String[] args) {
        Set<String> kategoriKategori = new LinkedHashSet<>();
        Map<String, ArrayList<Barang>> mp = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < input; i++) { 
            String kodeBarang = scanner.nextLine();
            String namaBarang = scanner.nextLine();
            String kategori = scanner.nextLine();
            String harga = scanner.nextLine();
            String stok = scanner.nextLine();
            kategoriKategori.add(kategori);
            Barang barang = new Barang(kodeBarang, namaBarang, kategori, harga, stok);
            if (!mp.containsKey(kategori)) {
                mp.put(kategori, new ArrayList<>());
            }
            mp.get(kategori).add(barang);
        }
        
        for (String categories : kategoriKategori) {
            System.out.println("Berdasarkan kategori " + categories + ":\n");
            for (Barang barang : mp.get(categories)) {
                System.out.println("Kode Barang: " + barang.kodeBarang);
                System.out.println("Nama Barang: " + barang.namaBarang);
                System.out.println("Harga: Rp." + barang.harga);
                System.out.println("Stok: " + barang.stok + " unit\n");
            }
        }
    }
}