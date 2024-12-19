package BinaryTree.Hackerrank;
import java.util.Scanner;

//KODE MAUL


public class DirektoriPakOgah{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BT bt = new BT();
        int n = sc.nextInt();
        sc.nextLine();
        while (n-- > 0) {
            String s = sc.nextLine();
            switch (s) {
                case "TAMBAH":
                    String[] a = sc.nextLine().split(" ");
                    bt.add(a[0], Integer.parseInt(a[1]));
                    System.out.println("Direktori " + a[0] + " berhasil ditambah dengan memori sebesar " + a[1]);
                    break;
                case "HITUNG":
                    String b = sc.nextLine();
                    int memory = bt.calculateMemory(b);
                    if (memory != -1) {
                        System.out.println("Besar memori " + b + " = " + memory);
                    } else {
                        System.out.println("Direktori tidak ditemukan");
                    }
                    break;
                case "CARI":
                    String c = sc.nextLine();
                    String ans = bt.search(c);
                    if (ans != null) {
                        System.out.println(ans);
                    } else {
                        System.out.println("Direktori tidak ditemukan");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
class NodeBT {
    String data;
    int memory; 
    NodeBT left, right;

    public NodeBT(String data, int memory){
        this.data = data;
        this.memory = memory; 
        this.left = this.right = null;
    }
}

class BT {
    NodeBT root;

    public void add(String input, int memory){ 
        if (root == null) {
            root = new NodeBT(input, memory); 
            return;
        }
        Queue queue = new Queue();
        queue.enqueue(root);
        while(!queue.isEmpty()){
            NodeBT pointer = queue.dequeue();
            if(pointer.left == null){
                pointer.left = new NodeBT(input, memory); 
                return;
            }
            else{
                queue.enqueue(pointer.left);
            }
            if(pointer.right == null){
                pointer.right = new NodeBT(input, memory);
                return;
            }
            else{
                queue.enqueue(pointer.right);
            }
        }
    }

    public String search(String data) {
        return searchPath(root, data, "");
    }

    public String searchPath(NodeBT current, String data, String path) {
        if (current == null) {
            return "Direktori tidak ditemukan";
        }
        if (data.equals(current.data)) {
            return path.trim().isEmpty() ? data : path + " > " + data;
        }
        String newPath = path.trim().isEmpty() ? current.data : path + " > " + current.data;
        String leftSearch = searchPath(current.left, data, newPath);
        if (!leftSearch.equals("Direktori tidak ditemukan")) {
            return leftSearch;
        }
        return searchPath(current.right, data, newPath);
    }

    public int calculateMemory(String data) {
        NodeBT node = findNode(root, data);
        if (node == null) {
            return -1;
        }
        return calculateMemoryRec(node);
    }

    public int calculateMemoryRec(NodeBT root) {
        if (root == null) {
            return 0;
        }
        return root.memory + calculateMemoryRec(root.left) + calculateMemoryRec(root.right); 
    }

    public NodeBT findNode(NodeBT root, String data) {
        if (root == null || root.data.equals(data)) {
            return root;
        }
        if (data.compareTo(root.data) < 0) {
            return findNode(root.left, data);
        }
        return findNode(root.right, data);
    }
}

class NodeQueue{
    NodeBT data;
    NodeQueue next;

    public NodeQueue(NodeBT data){
        this.data = data;
        this.next = null;
    }
}

class Queue{
    NodeQueue head, tail;
    int size = 0;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(NodeBT data){
        NodeQueue input = new NodeQueue(data);
        if (isEmpty()) {
            head = tail = input;
        }
        else {
            tail.next = input;
            tail = input;
        }
        size++;
    }

    public NodeBT dequeue(){
        if (!isEmpty()) {
            NodeBT temp = head.data;
            head = head.next;
            size--;
            return temp;
        }
        return null;
    }
}
// public class DirektoriPakOgah {
//     public static void main(String[] args) {
//         Scanner input = new Scanner(System.in);
//         int n = input.nextInt();
//         input.nextLine(); 

//         BT tree = new BT();

//         for (int i = 0; i < n; i++) {
//             String command = input.next();
//             switch (command) {
//                 case "TAMBAH":
//                     String directoryName = input.next();
//                     int directoryMemory = input.nextInt();
//                     tree.add(directoryName, directoryMemory);
//                     System.out.println("Direktori " + directoryName + " berhasil ditambah dengan memori sebesar " + directoryMemory);
//                     break;
//                 case "CARI":
//                     directoryName = input.next();
//                     String path = tree.search(directoryName);
//                     if (!path.equals("Direktori tidak ditemukan")) {
//                         System.out.println(path);
//                     } else {
//                         System.out.println("Direktori tidak ditemukan");
//                     }
//                     break;
//                 case "HITUNG":
//                     directoryName = input.next();
//                     int memory = tree.calculateMemory(directoryName);
//                     if (memory != -1) {
//                         System.out.println("Besar memori " + directoryName + " = " + memory);
//                     } else {
//                         System.out.println("Direktori tidak ditemukan");
//                     }
//                     break;
//             }
//             if(input.hasNextLine()) { // Prepare for the next input
//                 input.nextLine();
//             }
//         }
//         input.close();
//     }
// }

// class NodeBT {
//     String data;
//     int memory; 
//     NodeBT left, right;

//     public NodeBT(String data, int memory){
//         this.data = data;
//         this.memory = memory; 
//         this.left = this.right = null;
//     }
// }

// class BT {
//     NodeBT root;

//     public void add(String input, int memory){ 
//         if (root == null) {
//             root = new NodeBT(input, memory); 
//             return;
//         }
//         Queue queue = new Queue();
//         queue.enqueue(root);
//         while(!queue.isEmpty()){
//             NodeBT pointer = queue.dequeue();
//             if(pointer.left == null){
//                 pointer.left = new NodeBT(input, memory); 
//                 return;
//             }
//             else{
//                 queue.enqueue(pointer.left);
//             }
//             if(pointer.right == null){
//                 pointer.right = new NodeBT(input, memory);
//                 return;
//             }
//             else{
//                 queue.enqueue(pointer.right);
//             }
//         }
//     }

//     public String search(String data) {
//         return searchPath(root, data, "");
//     }

//     public String searchPath(NodeBT current, String data, String path) {
//         if (current == null) {
//             return "Direktori tidak ditemukan";
//         }
//         if (data.equals(current.data)) {
//             return path.trim().isEmpty() ? data : path + " > " + data;
//         }
//         String newPath = path.trim().isEmpty() ? current.data : path + " > " + current.data;
//         String leftSearch = searchPath(current.left, data, newPath);
//         if (!leftSearch.equals("Direktori tidak ditemukan")) {
//             return leftSearch;
//         }
//         return searchPath(current.right, data, newPath);
//     }

//     public int calculateMemory(String data) {
//         NodeBT node = findNode(root, data);
//         if (node == null) {
//             return -1;
//         }
//         return calculateMemoryRec(node);
//     }

//     public int calculateMemoryRec(NodeBT root) {
//         if (root == null) {
//             return 0;
//         }
//         return root.memory + calculateMemoryRec(root.left) + calculateMemoryRec(root.right); 
//     }

//     public NodeBT findNode(NodeBT root, String data) {
//         if (root == null || root.data.equals(data)) {
//             return root;
//         }
//         if (data.compareTo(root.data) < 0) {
//             return findNode(root.left, data);
//         }
//         return findNode(root.right, data);
//     }
// }

// class NodeQueue{
//     NodeBT data;
//     NodeQueue next;

//     public NodeQueue(NodeBT data){
//         this.data = data;
//         this.next = null;
//     }
// }

// class Queue{
//     NodeQueue head, tail;
//     int size = 0;

//     public int size(){
//         return size;
//     }

//     public boolean isEmpty(){
//         return size == 0;
//     }

//     public void enqueue(NodeBT data){
//         NodeQueue input = new NodeQueue(data);
//         if (isEmpty()) {
//             head = tail = input;
//         }
//         else {
//             tail.next = input;
//             tail = input;
//         }
//         size++;
//     }

//     public NodeBT dequeue(){
//         if (!isEmpty()) {
//             NodeBT temp = head.data;
//             head = head.next;
//             size--;
//             return temp;
//         }
//         return null;
//     }
// }
// public class DirektoriPakOgah {
//         public static void main(String[] args) {
//             Scanner input = new Scanner(System.in);
//             int n = input.nextInt();
//             input.nextLine(); 
    
//             BT tree = new BT();
    
//             for (int i = 0; i < n; i++) {
//                 String command = input.next();
//                 switch (command) {
//                     case "TAMBAH":
//                         String directoryName = input.next();
//                         int directoryMemory = input.nextInt();
//                         tree.add(directoryName, directoryMemory);
//                         System.out.println("Direktori " + directoryName + " berhasil ditambah dengan memori sebesar " + directoryMemory);
//                         break;
//                     case "CARI":
//                         directoryName = input.next();
//                         String path = tree.search(directoryName);
//                         if (!path.equals("Direktori tidak ditemukan")) {
//                             System.out.println(path);
//                         } else {
//                             System.out.println("Direktori tidak ditemukan");
//                         }
//                         break;
//                     case "HITUNG":
//                         directoryName = input.next();
//                         int memory = tree.calculateMemory(directoryName);
//                         if (memory != -1) {
//                             System.out.println("Besar memori " + directoryName + " = " + memory);
//                         } else {
//                             System.out.println("Direktori tidak ditemukan");
//                         }
//                         break;
//                 }
//                 if(input.hasNextLine()) { // Prepare for the next input
//                     input.nextLine();
//                 }
//             }
//             input.close();
//         }
//     } 
//     class NodeBT {
//         String data;
//         int memory; 
//         NodeBT left, right;
    
//         public NodeBT(String data, int memory){
//             this.data = data;
//             this.memory = memory; 
//             this.left = this.right = null;
//         }
//     }
//     class BT {
//         NodeBT root;
    
//         public void add(String input, int memory){ 
//             if (root == null) {
//                 root = new NodeBT(input, memory); 
//                 return;
//             }
//             Queue queue = new Queue();
//             queue.enqueue(root);
//             while(!queue.isEmpty()){
//                 NodeBT pointer = queue.dequeue();
//                 if(pointer.left == null){
//                     pointer.left = new NodeBT(input, memory); 
//                     return;
//                 }
//                 else{
//                     queue.enqueue(pointer.left);
//                 }
//                 if(pointer.right == null){
//                     pointer.right = new NodeBT(input, memory);
//                     return;
//                 }
//                 else{
//                     queue.enqueue(pointer.right);
//                 }
//             }
//         }
//         public String search(String data) {
//             return searchPath(root, data, "");
//         }
//         public String searchPath(NodeBT current, String data, String path) {
//             if (current == null) {
//                 return "Direktori tidak ditemukan";
//             }
//             if (data.equals(current.data)) {
//                 return path.trim().isEmpty() ? data : path + " > " + data;
//             }
//             String newPath = path.trim().isEmpty() ? current.data : path + " > " + current.data;
//             String leftSearch = searchPath(current.left, data, newPath);
//             if (!leftSearch.equals("Direktori tidak ditemukan")) {
//                 return leftSearch;
//             }
//             return searchPath(current.right, data, newPath);
//         }
//         public int calculateMemory(String data) {
//             NodeBT node = findNode(root, data);
//             if (node == null) {
//                 return -1;
//             }
//             return calculateMemoryRec(node);
//         }
//         public int calculateMemoryRec(NodeBT root) {
//             if (root == null) {
//                 return 0;
//             }
//             return root.memory + calculateMemoryRec(root.left) + calculateMemoryRec(root.right); 
//         }
//         public NodeBT findNode(NodeBT root, String data) {
//             if (root == null || root.data.equals(data)) {
//                 return root;
//             }
//             if (data.compareTo(root.data) < 0) {
//                 return findNode(root.left, data);
//             }
//             return findNode(root.right, data);
//         }
//     }
//     class NodeQueue{
//         NodeBT data;
//         NodeQueue next;
    
//         public NodeQueue(NodeBT data){
//             this.data = data;
//             this.next = null;
//         }
//     }
    
//     class Queue{
//         NodeQueue head, tail;
//         int size = 0;
    
//         public int size(){
//             return size;
//         }
    
//         public boolean isEmpty(){
//             return size == 0;
//         }
    
//         public void enqueue(NodeBT data){
//             NodeQueue input = new NodeQueue(data);
//             if (isEmpty()) {
//                 head = tail = input;
//             }
//             else {
//                 tail.next = input;
//                 tail = input;
//             }
//             size++;
//         }
    
//         public NodeBT dequeue(){
//             if (!isEmpty()) {
//                 NodeBT temp = head.data;
//                 head = head.next;
//                 size--;
//                 return temp;
//             }
//             return null;
//         }
//     }
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int n = Integer.parseInt(scanner.nextLine().trim());
//         SLL parkirList = new SLL();

//         for (int i = 0; i < n; i++) {
//             String command = scanner.nextLine().trim();
//             switch (command) {
//                 case "KENDARAAN MASUK":
//                     String pengemudi = scanner.nextLine().trim();
//                     String kendaraanPlat = scanner.nextLine().trim();
//                     parkirList.insert(pengemudi, kendaraanPlat);
//                     break;
//                 case "KENDARAAN KELUAR":
//                     String kendaraanPlatKeluar = scanner.nextLine().trim();
//                     parkirList.delete(kendaraanPlatKeluar);
//                     break;
//                 case "CARI KENDARAAN":
//                     String kendaraanPlatCari = scanner.nextLine().trim();
//                     parkirList.search(kendaraanPlatCari);
//                     break;
//             }
//         }
//         scanner.close();
//     }
// }

    

// public class MainBT {
//     public static void main(String[] args) {
//         BT bt = new BT();
//         bt.add(1);
//         bt.add(2);
//         bt.add(3);
//         bt.add(4);
//         bt.add(5);
//         bt.add(6);
//         bt.add(7);
//         //         1
//         //      2     3
//         //    4   5  6  7
//         NodeBT result = bt.search(bt.root, 8);
//         if (result != null) {
//             System.out.println("Found the node with value: " + result.data);
//         } else {
//             System.out.println("Value not found in the BST.");
//         }
//     }
// }

// class NodeBT {
//     int data;
//     NodeBT left, right;

//     public NodeBT(int data) {
//         this.data = data;
//         left = right = null;
//     }
// }

// class BT {
//     NodeBT root;

//     public void add(int input) {
//         if (root == null) {
//             root = new NodeBT(input);
//             return;
//         }
//         Queue queue = new Queue();
//         queue.enqueue(root);
//         while (!queue.isEmpty()) {
//             NodeBT pointer = queue.dequeue();
//             if (pointer.left == null) {
//                 pointer.left = new NodeBT(input);
//                 return;
//             } else {
//                 queue.enqueue(pointer.left);
//             }
//             if (pointer.right == null) {
//                 pointer.right = new NodeBT(input);
//                 return;
//             } else {
//                 queue.enqueue(pointer.right);
//             }
//         }
//     }

//     public void inorder(NodeBT current) {
//         if (current.left != null) {
//             inorder(current.left);
//         }
//         System.out.print(current.data + " ");
//         if (current.right != null) {
//             inorder(current.right);
//         }
//     }

//     public void preorder(NodeBT current) {
//         if (current != null) {
//             System.out.print(current.data + " ");
//             preorder(current.left);
//             preorder(current.right);
//         }
//     }

//     public void postorder(NodeBT current) {
//         if (current != null) {
//             postorder(current.left);
//             postorder(current.right);
//             System.out.print(current.data + " ");
//         }
//     }

//     public NodeBT search(NodeBT current, int target) {
//         if (current == null) {
//             return null;
//         } else if (current.data == target) {
//             return current;
//         } else if (target < current.data) {
//             return search(current.left, target);
//         } else {
//             return search(current.right, target);
//         }
//     }

//     class NodeQueue {
//         NodeBT data;
//         NodeQueue next;

//         public NodeQueue(NodeBT data) {
//             this.data = data;
//             this.next = null;
//         }
//     }

//     class Queue {
//         NodeQueue head, tail;
//         int size = 0;

//         public int size() {
//             return size;
//         }

//         public boolean isEmpty() {
//             return size == 0;
//         }

//         public void enqueue(NodeBT data) {
//             NodeQueue input = new NodeQueue(data);
//             if (isEmpty()) {
//                 head = tail = input;
//             } else {
//                 tail.next = input;
//                 tail = input;
//             }
//             size++;
//         }

//         public NodeBT dequeue() {
//             if (!isEmpty()) {
//                 NodeBT temp = head.data;
//                 head = head.next;
//                 size--;
//                 return temp;
//             }
//             return null;
//         }
//     }
// }
