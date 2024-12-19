package Queue;

import java.util.Scanner;

class Queue<S> {
    private NodeQueue<S> front, rear;

    public Queue() {
        front = rear = null;
    }

    public int size() {
        int count = 0;
        NodeQueue<S> temp = front;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(S data) {
        NodeQueue<S> newNode = new NodeQueue<>(data);

        if (rear == null) {
            front = rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    public S dequeue() {
        if (front == null)
            return null;

        S data = front.data;
        front = front.next;

        if (front == null)
            rear = null;

        return data;
    }

    public void riffleShuffle(int s) {
        for (int i = 0; i < s; i++) {
            Queue<S> q1 = new Queue<>();
            Queue<S> q2 = new Queue<>();

            int halfSize = (size() + 1) / 2;
            for (int j = 0; j < halfSize; j++) {
                q1.enqueue(dequeue());
            }
            while (!isEmpty()) {
                q2.enqueue(dequeue());
            }
            while (!q1.isEmpty() || !q2.isEmpty()) {
                if (!q1.isEmpty()) {
                    enqueue(q1.dequeue());
                }
                if (!q2.isEmpty()) {
                    enqueue(q2.dequeue());
                }
            }
        }
    }

    public void printQueue() {
        NodeQueue<S> temp = front;
        while (temp != null) {
            System.out.print(temp.data);
            temp = temp.next;
        }
        System.out.println();
    }
}
public class SulapKartu<S> {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String initialDeck = scanner.nextLine();
        int s = scanner.nextInt();

        Queue<Character> deck = new Queue<>();
        for (char c : initialDeck.toCharArray()) {
            deck.enqueue(c);
        }

        deck.riffleShuffle(s);
        deck.printQueue();
    }
}
class NodeQueue<S> {
    S data;
    NodeQueue<S> next;

    NodeQueue(S d) {
        data = d;
        next = null;
    }
}

