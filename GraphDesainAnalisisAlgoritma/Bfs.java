// package GraphDesainAnalisisAlgoritma;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class DigraphList {
    int besarUkuran;
    NodeGraphList[] list;

    public void printList() {
        for (int i = 0; i < besarUkuran; i++) {
            System.out.print(i + 1 + " -> ");
            NodeGraphList pointer = list[i];
            while (pointer != null) {
                System.out.print(pointer.item + " (" + pointer.nilai + ")");
                if (pointer.next != null) {
                    System.out.print(" -> ");
                }
                pointer = pointer.next;
            }
            System.out.println();
        }
    }

    public void addEdge(int node1, int node2, int nilai) {
        if (list[node1 - 1] == null) {
            list[node1 - 1] = new NodeGraphList(node2, nilai);
        } else {
            NodeGraphList pointer = list[node1 - 1];
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = new NodeGraphList(node2, nilai);
        }
    }

    public void getNodes() {
        System.out.print("Nodes: ");
        for (int i = 0; i < besarUkuran; i++) {
            System.out.print(i + 1);
            if (i != besarUkuran - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public void BFS(int mulai) {
        boolean[] visited = new boolean[besarUkuran];
        Queue<Integer> queue = new LinkedList<>();
        visited[mulai - 1] = true;

        queue.add(mulai);
        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            mulai = queue.poll();
            System.out.print(mulai + " ");
            NodeGraphList pointer = list[mulai - 1];
            while (pointer != null) {
                if (!visited[pointer.item - 1]) {
                    visited[pointer.item - 1] = true;
                    queue.add(pointer.item);
                }
                pointer = pointer.next;
            }
        }
        System.out.println();
    }

    public DigraphList(int besarUkuran) {
        this.besarUkuran = besarUkuran;
        list = new NodeGraphList[besarUkuran];
    }
}

class NodeGraphList {
    NodeGraphList next;
    int nilai;
    int item;

    public NodeGraphList(int item, int nilai) {
        this.nilai = nilai;
        this.next = null;
        this.item = item;
    }
}

public class Bfs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int jumlahNode = Integer.parseInt(scanner.nextLine());

        DigraphList objDigraph = new DigraphList(jumlahNode);

        System.out.print("Enter edges for the digraph (format: node1,node2,weight; separated by spaces): ");
        String digraph = scanner.nextLine();
        
        String[] pisahDigraph = digraph.split(" ");
        for (String edge : pisahDigraph) {
            String[] scannerDigraph = edge.split(",");
            objDigraph.addEdge(Integer.parseInt(scannerDigraph[0]), Integer.parseInt(scannerDigraph[1]), Integer.parseInt(scannerDigraph[2]));
        }

        System.out.println("\nWeighted Digraph List");
        objDigraph.getNodes();
        objDigraph.printList();
        objDigraph.BFS(1);
    }
}


