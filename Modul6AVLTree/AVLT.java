package Modul6AVLTree;
import java.util.*;

class Node {
    int data;
    int tinggi; // tinggi node
    Node pKiri;
    Node pKanan;
    Node pInduk; // pointer ke induk

    // constructor node
    public Node(int dt, int tg, Node pKi, Node pKa, Node pI) {
        this.data = dt;
        this.tinggi = tg;
        this.pKiri = pKi;
        this.pKanan = pKa;
        this.pInduk = pI;
    }
}

public class AVLT {
    private Node root;

    public AVLT() {
        root = null;
    }
public static void main(String[] args) {
    AVLT t = new AVLT();
    Scanner scanner = new Scanner(System.in);
    int choice = 0;
    while (choice != 5) {
        System.out.println("============================================================");
        System.out.println("===================PROGRAM AVLTREE==========================");
        System.out.println("============================================================");
        System.out.println("Menu:");
        System.out.println("1. Sisip Data");
        System.out.println("2. Hapus Data");
        System.out.println("3. Cari Data");
        System.out.println("4. Tampilkan Data");
        System.out.println("5. Keluar");
        System.out.print("Pilih: ");
        choice = scanner.nextInt();
        System.out.println("============================================================");
        switch (choice) {
            case 1:
                System.out.print("Masukkan data yang ingin disisipkan: ");
                int dataSisip = scanner.nextInt();
                t.sisipSatuDt(dataSisip);
                System.out.println("Data berhasil disisipkan.");
                break;
            case 2:
                System.out.print("Masukkan data yang ingin dihapus: ");
                int dataHapus = scanner.nextInt();
                if (t.hapusDt(dataHapus)) {
                    System.out.println("Data berhasil dihapus.");
                } else {
                    System.out.println("Data tidak ditemukan.");
                }
                break;
            case 3:
                System.out.print("Masukkan data yang ingin dicari: ");
                int dataCari = scanner.nextInt();
                if (t.cariDt(dataCari)) {
                    System.out.println("Data ditemukan.");
                } else {
                    System.out.println("Data tidak ditemukan.");
                }
                break;
            case 4:
                t.tampilkanData();
                break;
            case 5:
                System.out.println("Terima kasih!");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }
    scanner.close();
}

    // cari dt di tree, mengembalikan true jika ditemukan
    // dan false jika tidak
    public boolean cariDt(int dt) {
        Node temp = root;
        while (temp != null) {
            if (dt == temp.data) 
                return true;
            else if (dt < temp.data) 
                temp = temp.pKiri;
            else 
                temp = temp.pKanan;
        }
        return false; // dt tidak ditemukan
    }
    public boolean hapusDt(int dt) {
        // Panggil metode rekursif untuk menghapus node
        root = hapusDt(root, dt);
        return true; // Jika berhasil, selalu kembalikan true untuk sekarang
    }
    
    private Node hapusDt(Node root, int dt) {
        // Jika root adalah null, berarti node tidak ditemukan, kembalikan null
        if (root == null) return root;
    
        // Cari node yang akan dihapus di subtree kiri atau kanan
        if (dt < root.data)
            root.pKiri = hapusDt(root.pKiri, dt);
        else if (dt > root.data)
            root.pKanan = hapusDt(root.pKanan, dt);
        else {
            // Node dengan nilai dt ditemukan, lakukan penghapusan
            // Kasus 1: Node merupakan daun atau node tidak memiliki anak
            if (root.pKiri == null || root.pKanan == null) {
                Node temp = null;
                if (temp == root.pKiri)
                    temp = root.pKanan;
                else
                    temp = root.pKiri;
    
                // Jika node tidak memiliki anak, atau hanya memiliki satu anak
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // Node memiliki satu anak
                    root = temp;
            } else {
                // Kasus 2: Node memiliki dua anak
                // Cari node terkecil pada subtree kanan (successor)
                Node temp = successor(root.pKanan);
    
                // Salin nilai successor ke node saat ini
                root.data = temp.data;
    
                // Hapus node successor
                root.pKanan = hapusDt(root.pKanan, temp.data);
            }
        }
    
        // Jika tree hanya memiliki satu node
        if (root == null)
            return root;
    
        // Update tinggi dan seimbangkan pohon setelah penghapusan
        root.tinggi = Math.max(tinggi(root.pKiri), tinggi(root.pKanan)) + 1;
        int balance = getBalance(root);
    
        // Jika node menjadi tidak seimbang, maka ada 4 kemungkinan rotasi
        // Kasus 1: Left Left
        if (balance > 1 && getBalance(root.pKiri) >= 0)
            return rotateRight(root);
    
        // Kasus 2: Left Right
        if (balance > 1 && getBalance(root.pKiri) < 0) {
            root.pKiri = rotateLeft(root.pKiri);
            return rotateRight(root);
        }
    
        // Kasus 3: Right Right
        if (balance < -1 && getBalance(root.pKanan) <= 0)
            return rotateLeft(root);
    
        // Kasus 4: Right Left
        if (balance < -1 && getBalance(root.pKanan) > 0) {
            root.pKanan = rotateRight(root.pKanan);
            return rotateLeft(root);
        }
    
        return root;
    }
    
    // Metode untuk mendapatkan node successor (terkecil) dari subtree kanan
    private Node successor(Node node) {
        Node current = node;
    
        // Melakukan perulangan hingga menemukan node terkecil (paling kiri)
        while (current.pKiri != null)
            current = current.pKiri;
    
        return current;
    }
    
    // Metode untuk mendapatkan keseimbangan pohon
    private int getBalance(Node node) {
        if (node == null) return 0;
        return tinggi(node.pKiri) - tinggi(node.pKanan);
    }
    
    // Rotasi ke kanan
    private Node rotateRight(Node y) {
        Node x = y.pKiri;
        Node T2 = x.pKanan;
    
        // Lakukan rotasi
        x.pKanan = y;
        y.pKiri = T2;
    
        // Update tinggi
        y.tinggi = Math.max(tinggi(y.pKiri), tinggi(y.pKanan)) + 1;
        x.tinggi = Math.max(tinggi(x.pKiri), tinggi(x.pKanan)) + 1;
    
        // Kembalikan root baru
        return x;
    }
    
    // Rotasi ke kiri
    private Node rotateLeft(Node x) {
        Node y = x.pKanan;
        Node T2 = y.pKiri;
    
        // Lakukan rotasi
        y.pKiri = x;
        x.pKanan = T2;
    
        // Update tinggi
        x.tinggi = Math.max(tinggi(x.pKiri), tinggi(x.pKanan)) + 1;
        y.tinggi = Math.max(tinggi(y.pKiri), tinggi(y.pKanan)) + 1;
    
        // Kembalikan root baru
        return y;
    }
    public void tampilkanData() {
        System.out.println("Data dalam tree (inorder traversal):");
        tampilkanData(root);
        System.out.println();
    }
    
    private void tampilkanData(Node node) {
        if (node == null)
            return;
        tampilkanData(node.pKiri);
        System.out.print(node.data + " ");
        tampilkanData(node.pKanan);
    }
    
    // sisip dt ke dalam tree, returns true if berhasil,
    // false jika gagal
    // tree diseimbangkan menggunakan algoritma AVL
       // sisip dt ke dalam tree, returns true if berhasil,
    // false jika gagal
    // tree diseimbangkan menggunakan algoritma AVL
    public boolean sisipDt(String input) {
        String[] values = input.split(","); // Memisahkan nilai-nilai berdasarkan koma
        int count = 0; // Untuk menghitung jumlah node yang disisipkan
        for (String value : values) {
            int dt = Integer.parseInt(value.trim()); // Mengonversi nilai dari string ke integer
            if (sisipSatuDt(dt)) { // Memanggil metode sisipSatuDt untuk menyisipkan satu nilai
                count++;
            }
        }
        return count == values.length; // Mengembalikan true jika semua nilai berhasil disisipkan
    }

    private boolean sisipSatuDt(int dt) {
        if (root == null) {
            // sisip dt di root
            root = new Node(dt, 1, null, null, null);
            return true;
        } else {
            // mulai dari root
            Node temp = root;
            Node prev = null;
            // cari lokasi penyisipan dt
            while (temp != null) {
                if (dt == temp.data)
                    return false;
                else if (dt < temp.data) {
                    prev = temp;
                    temp = temp.pKiri;
                } else {
                    prev = temp;
                    temp = temp.pKanan;
                }
            }
            // buat node baru
            temp = new Node(dt, 1, null, null, prev);
            if (dt < prev.data)
                prev.pKiri = temp; // sisip di pKiri
            else
                prev.pKanan = temp; // sisip di pKanan

            // mulai dari node yang disisipkan dan
            // bergerak menuju root
            while (temp != null) {
                // subtree pKiri dan pKanan memenuhi kondisi AVL
                if (Math.abs(tinggi(temp.pKiri) - tinggi(temp.pKanan)) <= 1) {
                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                } else if (tinggi(temp.pKiri) - tinggi(temp.pKanan) >= 2 && tinggi(temp.pKiri.pKiri) >= tinggi(temp.pKiri.pKanan)) {
                    // kasus 1 algoritma AVL
                    Node parent = temp.pInduk;
                    Node pKiri = temp.pKiri;
                    temp.pKiri = pKiri.pKanan;
                    if (temp.pKiri != null)
                        temp.pKiri.pInduk = temp;
                    pKiri.pKanan = temp;
                    temp.pInduk = pKiri;
                    pKiri.pInduk = parent;
                    if (parent == null)
                        root = pKiri;
                    else if (parent.pKiri == temp)
                        parent.pKiri = pKiri;
                    else
                        parent.pKanan = pKiri;

                    // hitung tinggi subtree pKanan
                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                    temp = pKiri;
                    // hitung tinggi dari root
                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                } else if (tinggi(temp.pKanan) - tinggi(temp.pKiri) >= 2 && tinggi(temp.pKanan.pKanan) >= tinggi(temp.pKanan.pKiri)) {
                    // kasus 2 algoritma AVL
                    Node parent = temp.pInduk;
                    Node pKanan = temp.pKanan;
                    temp.pKanan = pKanan.pKiri;
                    if (temp.pKanan != null)
                        temp.pKanan.pInduk = temp;
                    pKanan.pKiri = temp;
                    temp.pInduk = pKanan;
                    pKanan.pInduk = parent;
                    if (parent == null)
                        root = pKanan;
                    else if (parent.pKanan == temp)
                        parent.pKanan = pKanan;
                    else
                        parent.pKiri = pKanan;

                    // hitung tinggi subtree pKanan
                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                    temp = pKanan;
                    // hitung tinggi dari root
                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                } else if (tinggi(temp.pKiri) - tinggi(temp.pKanan) >= 2 && tinggi(temp.pKiri.pKanan) >= tinggi(temp.pKiri.pKiri)) {
                    // kasus 3 dari algoritma AVL
                    Node parent = temp.pInduk;
                    Node pKiripKanan = temp.pKiri.pKanan;
                    temp.pKiri.pKanan = pKiripKanan.pKiri;
                    if (temp.pKiri.pKanan != null)
                        temp.pKiri.pKanan.pInduk = temp.pKiri;
                    pKiripKanan.pKiri = temp.pKiri;
                    temp.pKiri.pInduk = pKiripKanan;
                    temp.pKiri = pKiripKanan.pKanan;
                    if (temp.pKiri != null)
                        temp.pKiri.pInduk = temp;
                    pKiripKanan.pKanan = temp;
                    temp.pInduk = pKiripKanan;
                    pKiripKanan.pInduk = parent;
                    if (parent == null)
                        root = pKiripKanan;
                    else if (parent.pKiri == temp)
                        parent.pKiri = pKiripKanan;
                    else
                        parent.pKanan = pKiripKanan;

                    // hitung tinggi subtree pKanan
                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                    temp = pKiripKanan;
                    // hitung tinggi dari root
                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                } else if (tinggi(temp.pKanan) - tinggi(temp.pKiri) >= 2 && tinggi(temp.pKanan.pKiri) >= tinggi(temp.pKanan.pKanan)) {
                    // kasus 4 dari algoritma AVL
                    Node parent = temp.pInduk;
                    Node pKananpKiri = temp.pKanan.pKiri;
                    temp.pKanan.pKiri = pKananpKiri.pKanan;
                    if (temp.pKanan.pKiri != null)
                        temp.pKanan.pKiri.pInduk = temp.pKanan;
                    pKananpKiri.pKanan = temp.pKanan;
                    temp.pKanan.pInduk = pKananpKiri;
                    temp.pKanan = pKananpKiri.pKiri;
                    if (temp.pKanan != null)
                        temp.pKanan.pInduk = temp;
                    pKananpKiri.pKiri = temp;
                    temp.pInduk = pKananpKiri;
                    pKananpKiri.pInduk = parent;
                    if (parent == null)
                        root = pKananpKiri;
                    else if (parent.pKanan == temp)
                        parent.pKanan = pKananpKiri;
                    else
                        parent.pKiri = pKananpKiri;

                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                    temp = pKananpKiri;
                    temp.tinggi = Math.max(tinggi(temp.pKiri), tinggi(temp.pKanan)) + 1;
                }
                temp = temp.pInduk;
            }
            // penyisipan berhasil
            return true;
        }
    }

    public int tinggi() {
        return root.tinggi;
    }

    private int tinggi(Node node) {
        if (node == null)
            return 0;
        else
            return node.tinggi;
    }

    // hitung node-node dari tree
    public int jumlahNode() {
        return jumlahNode(root);
    }

    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(Node r) {
        if (r == null)
            return;
        inOrder(r.pKiri);
        System.out.printf("-%d", r.data);
        inOrder(r.pKanan);
    }

    // hitung node-node dari tree
    private int jumlahNode(Node node) {
        if (node == null)
            return 0;
        else
            return 1 + jumlahNode(node.pKiri) + jumlahNode(node.pKanan);
    }
}
