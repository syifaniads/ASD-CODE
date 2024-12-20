package BinaryTree.KodinganKakaknya;

public class MainBT {

    public static void main(String[] args) {
        BT bt = new BT();
        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bt.add(5);
        bt.add(6);
        bt.add(7);

        //         1
        //      2     3
        //    4   5  6  7
        System.out.print("inorder: ");
        bt.inorder(bt.root);

    }
}

class NodeBT {

    //  root
    //B      C
    int data;
    NodeBT left, right;

    public NodeBT(int data) {
        this.data = data;
        left = right = null;
    }
}

class BT {

    NodeBT root;
    //  root
    //B      C

    public void add(int input) {
        if (root == null) {
            root = new NodeBT(input);
            return;
        }
        //add(2)
        //add(3)

        //kondisi:
        //             1
        //      2            3
        Queue queue = new Queue();
        queue.enqueue(root);
        // queue : 3
        while (!queue.isEmpty()) {

            NodeBT pointer = queue.dequeue();
            // pointer Node: 1
            if (pointer.left == null) {
                pointer.left = new NodeBT(input);
                return;
            } else {
                queue.enqueue(pointer.left);
            }

            if (pointer.right == null) {
                pointer.right = new NodeBT(input);
                return;
            } else {
                queue.enqueue(pointer.right);
            }
        }
    }

    //pre-order?
    //post-order?
    public void inorder(NodeBT root2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inorder'");
    }

    class NodeQueue {

        NodeBT data;
        NodeQueue next;

        public NodeQueue(NodeBT data) {
            this.data = data;
            this.next = null;
        }
    }

    class Queue {

        NodeQueue head, tail;
        int size = 0;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void enqueue(NodeBT data) {
            NodeQueue input = new NodeQueue(data);
            if (isEmpty()) {
                head = tail = input;
            } else {
                tail.next = input;
                tail = input;
            }
            size++;
        }

        public NodeBT dequeue() {
            if (!isEmpty()) {
                NodeBT temp = head.data;
                head = head.next;
                size--;
                return temp;
            }
            return null;
        }
    }
}