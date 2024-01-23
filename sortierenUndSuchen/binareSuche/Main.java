package binareSuche;

public class Main {
  public static void main(String[] args) {
    int[] zahlen = new int[5000000];
    for (int i = 0; i < zahlen.length; i++) {
      zahlen[i] = i;
      // System.out.println(zahlen[i]);
    }
    // System.out.println(suchebinaerRekursiv(zahlen, 47, 0, zahlen.length));
    System.out.println(suchebinaerIterativ(zahlen, 0));
  }

  public static int suchebinaerIterativ(int[] array, int gesucht) {
    int obergrenze = array.length;
    int untergrenze = 0;
    int zaehler = 0;
    while (true) {
      zaehler++;
      int mitte = untergrenze + (obergrenze - untergrenze) / 2;
      if (array[mitte] == gesucht) {
        System.out.println("duchgaenge: " + zaehler);
        return mitte;
      } else if (obergrenze <= untergrenze) {
        return -1;
      } else if (array[mitte] < gesucht) {
        untergrenze = mitte + 1;
      } else if (array[mitte] > gesucht) {
        obergrenze = mitte - 1;
      }
    }
  }

  public static int suchebinaerRekursiv(int[] array, int gesucht, int untergrenze, int obergrenze) {
    int mitte = untergrenze + (obergrenze - untergrenze) / 2;
    if (array[mitte] == gesucht) {
      return mitte;
    } else if (obergrenze <= untergrenze) {
      return -1;
    } else if (array[mitte] < gesucht) {
      return suchebinaerRekursiv(array, gesucht, mitte + 1, obergrenze);
    } else if (array[mitte] > gesucht) {
      return suchebinaerRekursiv(array, gesucht, untergrenze, mitte - 1);
    } else {
      return -1;
    }
  }

}
