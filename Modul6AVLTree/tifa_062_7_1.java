package Modul6AVLTree;
// import java.util.*;
import java.util.Scanner;

public class tifa_062_7_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        AVL_Database tree = new AVL_Database();

        for(int i=0; i<n; i++){
            String perintah = in.nextLine();
            if(perintah.equals("TAMBAH BARANG")) {
                String[] items = in.nextLine().split(";");
                for (String item : items) {
                    String[] details = item.split(",");
                    String itemName = details[0];
                    int itemPrice = Integer.parseInt(details[1]);
                    tree.root = tree.insert(tree.root, itemName, itemPrice);
                }
                System.out.println("Data " + items.length + " barang berhasil ditambah");
                // System.out.println();
            }
            else if(perintah.equals("CARI BARANG")){
                String criteria = in.nextLine();
                String[] criteriaSplit = criteria.split(" ");
                String operator = criteriaSplit[0];
                for (int j = 1; j < criteriaSplit.length - 1; j++) {
                    operator += " " + criteriaSplit[j];
                }
                int price = Integer.parseInt(criteriaSplit[criteriaSplit.length - 1]);
                int count = tree.search(tree.root, operator, price, 0);
                if (count == 0)
                    System.out.println("Data barang tidak ditemukan");
            }
            if (i != n - 1) {
                System.out.println();
            }
            // else System.out.println("Perintah tidak valid");
        }
    }
}

class NodeAVL_Database {
    String name;
    int price;
    public int height;
    NodeAVL_Database left;
    NodeAVL_Database right;

    NodeAVL_Database(String name, int price){
        this.name = name;
        this.price = price;
        this.height = 1;
        left = right = null;
    }
}

class AVL_Database {
    NodeAVL_Database root;
    int angka = 1;

    public int height(NodeAVL_Database N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }
    public int max(int a, int b){
        return (a > b) ? a : b;
    }

    public NodeAVL_Database rightRotate(NodeAVL_Database y){
        NodeAVL_Database x = y.left;
        NodeAVL_Database T2 = x.right;
        // rotasi kanan
        x.right = y;
        y.left = T2;
        // perbarui tinggi dari node
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    public NodeAVL_Database leftRotate(NodeAVL_Database x){
        NodeAVL_Database y = x.right;
        NodeAVL_Database T2 = y.left;
        // rotasi kiri
        y.left = x;
        x.right = T2;
        // perbarui tinggi dari node
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public int getBalance(NodeAVL_Database N){
        if(N == null) return 0;
        return height(N.left) - height(N.right);
    }

    NodeAVL_Database insert(NodeAVL_Database node, String nama, int harga) {
        if (node == null)
            return (new NodeAVL_Database(nama, harga));

        if (harga < node.price) {
            node.left = insert(node.left, nama, harga);
        }
        else if (harga > node.price) {
            node.right = insert(node.right, nama, harga);
        }
        else return node;

        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && harga < node.left.price) {
            return rightRotate(node);
        }

        if (balance < -1 && harga > node.right.price) {
            return leftRotate(node);
        }

        if (balance > 1 && harga > node.left.price) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && harga < node.right.price) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    public int search(NodeAVL_Database current, String lookFor, int price, int angka) {
        if (current == null) return angka;
        if (lookFor.equals("LEBIH DARI")) {
            if (current.price > price) {
                angka = search(current.right, lookFor, price, angka);
                System.out.println((angka + 1) + ". " + current.name + " = Rp" + current.price);
                angka = search(current.left, lookFor, price, angka + 1);
            } else {
                angka = search(current.right, lookFor, price, angka);
            }
        }
        else if (lookFor.equals("KURANG DARI")) {
            if (current.price < price) {
                angka = search(current.right, lookFor, price, angka);
                System.out.println((angka + 1) + ". " + current.name + " = Rp" + current.price);
                angka = search(current.left, lookFor, price, angka + 1);
            }
            else angka = search(current.left, lookFor, price, angka);

        }
        else if (lookFor.equals("SAMA DENGAN")) {
            angka = search(current.left, lookFor, price, angka);
            if (current.price == price) {
                System.out.println(current.name + " = Rp" + current.price);
                angka++;
            }
            angka = search(current.right, lookFor, price, angka);
        }
        return angka;
    }

    // public void inorder(NodeAVL_Database current){
    //     if (current.left != null) {
    //         inorder(current.left);
    //     }
    //     System.out.print(current.name + " = Rp" + current.price);
    //     if (current.right != null) {
    //         inorder(current.right);
    //     }
    // }
}
// class AVL {
//     NodeAVL root;
//     int nomorlebih = 0;
//     int nomorkurang = 0;
//      public int height(NodeAVL N) {
//         if (N == null) {
//             return 0;
//         }
//         return N.height;
//     }
//     int nomorLebih = 1;
//     int nomorKurang = 1;
//     boolean search(NodeAVL root, String condition, int harga) {
//         if (root == null) {
//             return false;
//         }
//         if (condition.equals("LEBIH DARI")) {
//             boolean found = false;
//             boolean leftFound = search(root.right, condition, harga);
//             if (root.data > harga) {
//                 System.out.println(nomorLebih++ + ". " + root.barang + " = Rp" + root.data);
//                 found = true;
//             }
//             boolean rightFound = search(root.left, condition, harga);
//             return found || leftFound || rightFound;
//         } else if (condition.equals("KURANG DARI")) {
//             boolean rightFound = search(root.right, condition, harga);
//             boolean found = false;
//             if (root.data < harga) {
//                 System.out.println(nomorKurang++ + ". " + root.barang + " = Rp" + root.data);
//                 found = true;
//             }
//             boolean leftFound = search(root.left, condition, harga);
            
//             return leftFound || found || rightFound;
//         } else if (condition.equals("SAMA DENGAN")) {
//             boolean leftFound = search(root.left, condition, harga);
//             boolean found = false;
//             if (root.data == harga) {
//                 System.out.println(root.barang + " = Rp" + root.data);
//                 found = true;
//             }
//             boolean rightFound = search(root.right, condition, harga);
//             return leftFound || found || rightFound;
//         }
//         return false;
//     }
//     public void add(String barang, int data) {
//         root = add(root, barang, data);
//     }        
//     public NodeAVL leftRotate(NodeAVL x) {
//         NodeAVL y = x.right;
//         NodeAVL T2 = y.left;
//         // rotasi kiri
//         y.left = x;
//         x.right = T2;
//         // perbarui tinggi dari node
//         x.height = Math.max(height(x.left), height(x.right)) + 1;
//         y.height = Math.max(height(y.left), height(y.right)) + 1;
//         return y;
//     }
//     public int getBalance(NodeAVL N) {
//         if (N == null) {
//             return 0;
//         }
//         return height(N.left) - height(N.right);
//     } 
//     public NodeAVL rightRotate(NodeAVL y) {
//         NodeAVL x = y.left;
//         NodeAVL T2 = x.right;
//         // rotasi kanan
//         x.right = y;
//         y.left = T2;
//         // perbarui tinggi dari node
//         y.height = Math.max(height(y.left), height(y.right)) + 1;
//         x.height = Math.max(height(x.left), height(x.right)) + 1;
//         return x;
//     }
//     public NodeAVL add(NodeAVL node, String barang, int data) {
//         if (node == null){
//             return (new NodeAVL(barang, data));
//         }
//         if (data < node.data) {
//             node.left = add(node.left, barang, data);
//         }
//         else if (data > node.data) {
//             node.right = add(node.right, barang, data);
//         }
//         else {
//             return node;
//         }
//         node.height = 1 + Math.max(height(node.left), height(node.right));
//         int balance = getBalance(node);
//         // right rotation (left of left)
//         if (balance > 1 && data < node.left.data) {
//             return rightRotate(node);
//         }
//         // left rotation (right of right)
//         if (balance < -1 && data > node.right.data) {
//             return leftRotate(node);
//         }
//         // left-right rotation (right of left)
//         if (balance > 1 && data > node.left.data) {
//             node.left = leftRotate(node.left);
//             return rightRotate(node);
//         }
//         // right-left rotation (left of right)
//         if (balance < -1 && data < node.right.data) {
//             node.right = rightRotate(node.right);
//             return leftRotate(node);
//         }
//         return node;
//     } 
// }
// public class DatabasePakOgah {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int masukkan = scanner.nextInt();
//         scanner.nextLine();

//         AVL avl = new AVL();
//         while (masukkan-- > 0) {
//             String perintah[] = scanner.nextLine().split(" ");
//             if (perintah[0].equals("TAMBAH")) {
//                 String barang[] = scanner.nextLine().split(";");
//                 for (int i = 0; i < barang.length; i++) {
//                     String a[] = barang[i].split(",");
//                     avl.add(a[0], Integer.parseInt(a[1]));
//                 }
//                 System.out.println("Data " + barang.length + " barang berhasil ditambah\n");
//             } else if (perintah[0].equals("CARI")) {
//                 String find[] = scanner.nextLine().split(" ");
//                 String condition = find[0] + " " + find[1];
//                 int harga = Integer.parseInt(find[2]);
//                 if (!avl.search(avl.root, condition, harga)) {
//                     System.out.println("Data barang tidak ditemukan\n");
//                 } else {
//                     System.out.println();
//                 }
//             } else {
//             }
//         }
//     }
// }
// class NodeAVL {
//     int height;
//     String barang;
//     NodeAVL left, right;
//     int data;

//     NodeAVL(String barang, int data) {
//         this.barang = barang;
//         this.data = data;
//         height = 1;
//         left = right = null;
//     }
// }