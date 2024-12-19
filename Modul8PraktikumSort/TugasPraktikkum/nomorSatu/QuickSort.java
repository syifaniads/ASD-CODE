package Modul8PraktikumSort.TugasPraktikkum.nomorSatu;
import java.util.Scanner;

public class QuickSort {
    private static int iterations = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("===========QUICK SORT===========");
        System.out.println("================================");
        System.out.print("INPUT JUMLAH ELEMEN TOTAL ARRAY: ");
        int masukkan = scanner.nextInt();
        scanner.nextLine();  // Consume newline character left by nextInt()
        int[] array = new int[masukkan];
        System.out.print("INPUT NILAI-NILAI DALAM ARRAY (DIPISAHKAN SPASI): ");
        String[] inputValues = scanner.nextLine().split(" ");
        for (int i = 0; i < masukkan; i++) {
            array[i] = Integer.parseInt(inputValues[i]);
        }
        quicksort(array, 0, array.length - 1);
        System.out.printf("\nHASIL PENGURUTAN ARRAY MENGGUNAKAN QUICK SORT: ");
        for (int nilai : array) {
            System.out.print(nilai + " ");
        }
        System.out.print("\nBANYAK ITERASI YANG DIBUTUHKAN UNTUK SATU PROSES IALAH: " + iterations);
    }

    public static int partition(int[] array, int l, int r) {
        iterations++;
        int pivot = array[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[r];
        array[r] = temp;
        return i + 1;
    }

    public static void quicksort(int[] array, int l, int r) {
        if (l < r) {
            int p = partition(array, l, r);
            quicksort(array, l, p - 1);
            quicksort(array, p + 1, r);
        }
    }
}