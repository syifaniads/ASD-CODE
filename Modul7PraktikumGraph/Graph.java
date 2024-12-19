package Modul7PraktikumGraph;
import java.util.*;

public class Graph {
    private int[][] adjacencyMatrix;
    private int numberOfNodes;

    public Graph(int n) {
        numberOfNodes = n;
        adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE);
            adjacencyMatrix[i][i] = 0; // Distance to itself is 0
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(0, 4, 1);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 4, 8);
        graph.addEdge(4, 3, 3);
        graph.printGraph("Initial graph state:");
        System.out.println("-".repeat(30));
        graph.BFS(0);
        System.out.println("-".repeat(30));
        graph.DFS(0);
        System.out.println("-".repeat(30));
        graph.dijkstra(0, 4);
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyMatrix[source][destination] = weight;
    }

    public void printGraph(String message) {
        System.out.println("===========================================");
        System.out.println("PROGRAM ASD GRAPH SYIFANI ADILLAH SALSABILA");
        System.out.println("===========================================");
        System.out.println(message);
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.print("[" + i + "]");
            for (int j = 0; j < numberOfNodes; j++) {
                if (adjacencyMatrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("-> 0");
                } else {
                    System.out.print("-> " + adjacencyMatrix[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("===========================================");
    }

    public void BFS(int startNode) {
        boolean[] visited = new boolean[numberOfNodes];
        Queue queue = new Queue();
        visited[startNode] = true;
        queue.enqueue(startNode);
        System.out.println("BFS starting from node " + startNode);
        boolean firstNode = true;
        while (!queue.isEmpty()) {
            int currentNode = queue.dequeue();
            if (firstNode) {
                System.out.print(currentNode);
                firstNode = false;
            } else {
                System.out.print("->" + currentNode);
            }
            for (int i = 0; i < numberOfNodes; i++) {
                if (adjacencyMatrix[currentNode][i] != Integer.MAX_VALUE && !visited[i]) {
                    visited[i] = true;
                    queue.enqueue(i);
                }
            }
        }
        System.out.println();
    }

    public void DFS(int startNode) {
        boolean[] visited = new boolean[numberOfNodes];
        Stack stack = new Stack();
        stack.push(startNode);
        System.out.println("DFS starting from node " + startNode);
        boolean firstNode = true;
        while (!stack.isEmpty()) {
            int currentNode = stack.pop();
            if (!visited[currentNode]) {
                if (firstNode) {
                    System.out.print(currentNode);
                    firstNode = false;
                } else {
                    System.out.print("->" + currentNode);
                }
                visited[currentNode] = true;
                for (int i = numberOfNodes - 1; i >= 0; i--) {
                    if (adjacencyMatrix[currentNode][i] != Integer.MAX_VALUE && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        System.out.println();
    }

    public void dijkstra(int startNode, int endNode) {
        int[] distances = new int[numberOfNodes];
        boolean[] visited = new boolean[numberOfNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;
        for (int i = 0; i < numberOfNodes; i++) {
            int nearestNode = -1;
            for (int j = 0; j < numberOfNodes; j++) {
                if (!visited[j] && (nearestNode == -1 || distances[j] < distances[nearestNode])) {
                    nearestNode = j;
                }
            }
            if (distances[nearestNode] == Integer.MAX_VALUE) break;
            visited[nearestNode] = true;
            for (int v = 0; v < numberOfNodes; v++) {
                if (adjacencyMatrix[nearestNode][v] != Integer.MAX_VALUE &&
                    distances[nearestNode] + adjacencyMatrix[nearestNode][v] < distances[v]) {
                    distances[v] = distances[nearestNode] + adjacencyMatrix[nearestNode][v];
                }
            }
        }
        System.out.println("Shortest distance from node " + startNode + " to node " + endNode + " is " + distances[endNode]);
        System.out.println("[" + startNode + "]" + "->" + distances[endNode] + "->" + endNode);
    }
}

class NodeQueue {
    int data;
    NodeQueue next;
    NodeQueue(int data) {
        this.data = data;
        this.next = null;
    }
}

class NodeStack {
    int data;
    NodeStack next;
    NodeStack(int data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    private NodeStack head, tail;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(int data) {
        NodeStack newNode = new NodeStack(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            return 0;
        }
        int previousData = tail.data;
        if (size == 1) {
            head = tail = null;
        } else {
            NodeStack current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
        return previousData;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            NodeStack current = head;
            while (current != null) {
                System.out.print(current.data + "");
                current = current.next;
            }
            System.out.println();
        }
    }
}

class Queue {
    private NodeQueue head, tail;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(int data) {
        NodeQueue newNode = new NodeQueue(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            return 0;
        }
        int dequeuedData = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return dequeuedData;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            NodeQueue current = head;
            while (current != null) {
                System.out.print(current.data + "");
                current = current.next;
            }
            System.out.println();
        }
    }
}
