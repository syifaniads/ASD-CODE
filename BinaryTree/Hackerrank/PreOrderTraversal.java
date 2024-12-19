package BinaryTree.Hackerrank;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
public class PreOrderTraversal {
    public static void preOrder(TreeNode root) {
        if (root != null) {
            // Print nilai dari root node
            System.out.print(root.val + " ");

            // Cek nilai node kanan dan kiri untuk menentukan urutan
            if (root.right != null && root.left != null) {
                if (root.right.val > root.left.val) {
                    // Jika nilai node kanan lebih besar, kunjungi node kanan terlebih dahulu
                    preOrder(root.right);
                    preOrder(root.left);
                } else {
                    // Jika nilai node kiri lebih besar atau sama, kunjungi node kiri terlebih dahulu
                    preOrder(root.left);
                    preOrder(root.right);
                }
            } else if (root.right != null) {
                // Jika hanya ada node kanan, kunjungi node kanan
                preOrder(root.right);
            } else if (root.left != null) {
                // Jika hanya ada node kiri, kunjungi node kiri
                preOrder(root.left);
            }
        }
    }

    public static void main(String[] args) {
        // Contoh penggunaan
        // Buat pohon biner
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        // Lakukan pre-order traversal
        System.out.println("Pre-order traversal:");
        preOrder(root);
    }
}

