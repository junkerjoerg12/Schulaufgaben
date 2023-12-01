package sequenzielleSuche;

public class Main {
  public static void main(String[] args) {
    int[] zahlen = { 30, 7, 45, 15, 17, 102, 25, 100, 1, 14, 19, 30, 30 };
    // suchesequenziell(zahlen, 30);

    // System.out.println(sucheMax(zahlen));
    // System.out.println(sucheMin(zahlen));

    // sucheGerade(zahlen);
    System.out.println(checkPrime(30));
  }

  public static void suchesequenziell(int[] zahlen, int gesucht) {
    int durchgäenge = 0;
    boolean gefunden = false;
    for (int i = 0; i < zahlen.length; i++) {
      if (zahlen[i] == gesucht) {
        System.out.println("Gesucht befindet sich am Index " + i);
        gefunden = true;
      }
      durchgäenge++;
    }
    if (!gefunden) {
      System.out.println("gescuhte Zahl befindet sich nicht im Array");
    }
    System.out.println("Es wurden " + durchgäenge + " durchgänge gebraucht");
  }

  public static int sucheMax(int[] zahlen) {
    int max = zahlen[0];
    int index = -1;
    for (int i = 0; i < zahlen.length; i++) {
      if (zahlen[i] > max) {
        max = zahlen[i];
        index = i;
      }
    }
    return index;
  }

  public static int sucheMin(int[] zahlen) {
    int index = -1;
    int max = zahlen[0];
    for (int i = 0; i < zahlen.length; i++) {
      if (zahlen[i] < max) {
        max = zahlen[i];
        index = i;
      }
    }

    return index;
  }

  public static void sucheGerade(int[] zahlen) {
    for (int i = 0; i < zahlen.length; i++) {
      if ((zahlen[i] % 2) == 0) {
        System.out.println(i);
      }
    }
  }

  public static boolean checkPrime(int zahl) {
    for (int i = 2; i < zahl; i++) {
      if ((zahl % i) != 0) {
        return false;
      }
    }
    return true;
  }
}
