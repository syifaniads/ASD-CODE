package Modul8PraktikumSort.TugasPraktikkum.nomorSatu;

import java.util.Scanner;

public class BubbleSort {
    private static int iterations = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("===========BUBBLE SORT===========");
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

        bubbleSort(array);

        System.out.printf("\nHASIL PENGURUTAN ARRAY MENGGUNAKAN BUBBLE SORT: ");
        for (int nilai : array) {
            System.out.print(nilai + " ");
        }
        System.out.print("\nBANYAK ITERASI YANG DIBUTUHKAN UNTUK SATU PROSES IALAH: " + iterations);
    }

    public static void bubbleSort(int[] array) {
        int nilaiElemenArray = array.length;
        boolean swapped;
        for (int i = 0; i < nilaiElemenArray - 1; i++) {
            swapped = false;
            for (int j = 0; j < nilaiElemenArray - 1 - i; j++) {
                iterations++;
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}