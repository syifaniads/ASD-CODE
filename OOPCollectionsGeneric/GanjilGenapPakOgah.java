package OOPCollectionsGeneric;
import java.io.*;
import java.util.*;
import java.util.Scanner;

class mengecek {
    public static <S> void mengecekmasukkan(S something) {
        if (something instanceof Integer) {
            int bilangan = (Integer) something;
            if (bilangan > 0) {
                System.out.println(bilangan + " adalah angka");
            } else {
                System.out.println(bilangan + " adalah angka minus");
            }
        } else if (something instanceof String) {
            System.out.println(something + " adalah kata");
        }
    }
    public static void main(String[] arguments) {
        Scanner scanner = new Scanner(System.in);
        int angka = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < angka; i++) {
            String masukkan = scanner.nextLine();
            if (i % 2 == 0) {
                int bilangan = Integer.parseInt(masukkan);
                mengecekmasukkan(bilangan);
            } else {
                mengecekmasukkan(masukkan);
            }
        }

        scanner.close();
    }
}

public class GanjilGenapPakOgah {
    public static void main(String[] arguments) {
     mengecek.main(arguments);
    }
}