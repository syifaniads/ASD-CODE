package BinaryTree.TugasPraktikkum;
import java.util.*;

class CompleteBinaryTree {
    private int[] array;
    private int size;
    private int capacity;
    private Scanner scanner;

    public CompleteBinaryTree(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        size = 0;
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================================");
        System.out.println("========PROGRAM COMPLETE BINARY TREE============");
        System.out.println("================================================");
        System.out.print("Masukkan kapasitas Complete Binary Tree: ");
        int capacity = scanner.nextInt();
        CompleteBinaryTree tree = new CompleteBinaryTree(capacity);
        tree.insertValuesInteractively();
        System.out.println("\n================================================");
        System.out.println("\nPreorder traversal:");
        tree.preorderTraversal();
        System.out.println("\nInorder traversal:");
        tree.inorderTraversal();
        System.out.println("\nPostorder traversal:");
        tree.postorderTraversal();
        System.out.println("\n================================================");
        scanner.close();
    }

    public void insertValuesInteractively() {
        System.out.println("Masukkan nilai-nilai (ketik stop untuk selesai):");
        System.out.println("================================================");
        while (true) {
            System.out.print("Masukkan nilai: ");
            String input = scanner.next();

            if (input.equals("stop")) {
                break;
            }
            int value = Integer.parseInt(input);
            insert(value);
        }
    }

    public void insert(int data) {
        if (size < capacity) {
            array[size++] = data;
        } else {
            System.out.println("Tree is full.");
        }
    }

    public void preorderTraversal() {
        preorder(0);
    }

    private void preorder(int index) {
        if (index >= size) return;
        System.out.print(array[index] + " ");
        preorder(2 * index + 1);
        preorder(2 * index + 2);
    }

    public void inorderTraversal() {
        inorder(0);
    }

    private void inorder(int index) {
        if (index >= size) return;
        inorder(2 * index + 1);
        System.out.print(array[index] + " ");
        inorder(2 * index + 2);
    }

    public void postorderTraversal() {
        postorder(0);
    }

    private void postorder(int index) {
        if (index >= size) return;
        postorder(2 * index + 1);
        postorder(2 * index + 2);
        System.out.print(array[index] + " ");
    }
}
