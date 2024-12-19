package BinaryTree.Hackerrank;
//Pre-order Traversal of the above tree: 1-2-4-5-3-6-7
//minus di bagian print tree
import java.util.Scanner;
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class PakOgahNyobaBinaryTree {
    static int index;

    public static TreeNode constructTree(int[] preorder, int low, int high, int limit) {
        if (index >= preorder.length || low > high || limit <= 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[index++]);

        if (index < preorder.length && limit > 1) {
            int i;
            for (i = index; i < preorder.length; i++) {
                if (preorder[i] > root.val) {
                    break;
                }
            }
            root.left = constructTree(preorder, index, i - 1, limit - 1);
            root.right = constructTree(preorder, i, high, limit - 1);
        }

        return root;
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        } else {
            // Print nilai dari root node
            System.out.print(root.val + " ");
    
            // Cek apakah ada node di kiri
            if (root.left != null) {
                // Jika ada, traverse ke kiri terlebih dahulu
                printTree(root.left);
            }
    
            // Cek apakah ada node di kanan
            if (root.right != null) {
                // Jika ada, traverse ke kanan
                printTree(root.right);
            }
        }
    }
    public static int sumTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + sumTree(root.left) + sumTree(root.right);
    }

    public static int countTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countTree(root.left) + countTree(root.right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");
        int[] preorder = new int[7];
        for (int i = 0; i < Math.min(7, tokens.length); i++) {
            preorder[i] = Integer.parseInt(tokens[i]);
        }

        int limit = scanner.nextInt();
        index = 0;
        TreeNode root = constructTree(preorder, 0, preorder.length - 1, limit);

        System.out.print("Print tree : ");
        printTree(root);
        System.out.println();

        int sum = sumTree(root);
        System.out.println("Sum tree : " + sum);

        int count = countTree(root);
        System.out.println("Count tree : " + count);
    }
}
// public class PakOgahNyobaBinaryTree {        
//         public static class TreeNode {
//             String data;
//             TreeNode left;
//             TreeNode right;
    
//             public TreeNode(String data) {
//                 this.data = data;
//                 this.left = null;
//                 this.right = null;
//             }
//         }
    
//         public static void inOrderTraversal(TreeNode node) {
//             if (node == null) {
//                 return;
//             }
//             inOrderTraversal(node.left);
//             System.out.print(node.data + ", ");
//             inOrderTraversal(node.right);
//         }
    
//         public static void main(String[] args) {
//             Scanner scanner = new Scanner(System.in);
    
//             System.out.print("Masukkan nilai root:");
//             TreeNode root = new TreeNode(scanner.nextLine());
    
//             System.out.print("Masukkan nilai node A:");
//             TreeNode nodeA = new TreeNode(scanner.nextLine());
    
//             System.out.print("Masukkan nilai node B:");
//             TreeNode nodeB = new TreeNode(scanner.nextLine());
    
//             System.out.print("Masukkan nilai node C:");
//             TreeNode nodeC = new TreeNode(scanner.nextLine());
    
//             System.out.print("Masukkan nilai node D:");
//             TreeNode nodeD = new TreeNode(scanner.nextLine());
    
//             System.out.print("Masukkan nilai node E:");
//             TreeNode nodeE = new TreeNode(scanner.nextLine());
    
//             System.out.print("Masukkan nilai node F:");
//             TreeNode nodeF = new TreeNode(scanner.nextLine());
    
//             System.out.print("Masukkan nilai node G:");
//             TreeNode nodeG = new TreeNode(scanner.nextLine());
    
//             root.left = nodeA;
//             root.right = nodeB;
    
//             nodeA.left = nodeC;
//             nodeA.right = nodeD;
    
//             nodeB.left = nodeE;
//             nodeB.right = nodeF;
    
//             nodeF.left = nodeG;
    
//             // Traverse
//             System.out.print("Traversal in order:");
//             inOrderTraversal(root);
    
//             scanner.close();
//         }
//     }
