package Queue;

import java.util.Scanner;
import java.util.*;

class PQ<S> {
    NodePQ<S> head, tail;
    int size = 0;

    public void print() {
        if (isEmpty()) {
            System.out.println("Tidak ada tugas dalam antrian\n");
        } else {
            NodePQ<S> pointer = head;
            System.out.println("Tugas dalam antrian:");
            while (pointer != null) {
                System.out.println("- " + pointer.nilai + " (prioritas " + pointer.priority + ")");
                pointer = pointer.next;
            }
            System.out.println();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void dequeue() {
        if (!isEmpty()) {
            System.out.println("Menjalankan Tugas \"" + head.nilai + "\" dengan prioritas " + head.priority + "\n");
            head = head.next;
            size--;
        } else {
            System.out.println("Tidak ada tugas yang bisa dilaksanakan\n");
        }
    }

    public void enqueue(S nilai, int priority) {
        NodePQ<S> task = new NodePQ<>(nilai, priority);
        if (isEmpty()) {
            head = tail = task;
            System.out.println("Tugas \"" + task.nilai + "\" telah ditambahkan dengan prioritas " + task.priority + "\n");
        } else {
            if (task.priority < head.priority) {
                task.next = head;
                head = task;
                System.out.println("Tugas \"" + task.nilai + "\" telah ditambahkan dengan prioritas " + task.priority + "\n");
            } else {
                NodePQ<S> pointer = head;
                while (pointer.next != null && pointer.next.priority <= task.priority) {
                    pointer = pointer.next;
                }
                task.next = pointer.next;
                pointer.next = task;
                System.out.println("Tugas \"" + task.nilai + "\" telah ditambahkan dengan prioritas " + task.priority + "\n");
            }
        }
        size++;
    }
}

class NodePQ<S> {
    S nilai;
    int priority;
    NodePQ<S> next;

    public NodePQ(S nilai, int priority) {
        this.priority = priority;
        this.nilai = nilai;
        this.next = null;
    }
}
public class PakOgahSusahNentuinTugas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PQ<String> tugas = new PQ<>();
        int masukkan = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < masukkan; i++) {
            String input = scanner.nextLine();
            if ("ADD".equals(input)) {
                String nilai = scanner.nextLine();
                int prior = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                tugas.enqueue(nilai, prior);
            } else if ("EXECUTE".equals(input)) {
                tugas.dequeue();
            } else if ("DISPLAY".equals(input)) {
                tugas.print();
            }
        }
    }
}
