package Exceptions.exceptions1;

import java.util.Scanner;

public class Exception2 {
  public static void main(String[] args) {
    double ergebnis;
    int a;
    int b;
    Scanner tastatur = new Scanner(System.in);
    System.out.println("Bitte Dividend eingeben");
    a = tastatur.nextInt();
    System.out.println("Bitte Divisor eingeben");
    b = tastatur.nextInt();
    try {
      ergebnis = division(a, b);
      System.out.println(ergebnis);
    } catch (ArithmeticException e) {
      e.getMessage();
      e.printStackTrace();
    }
    tastatur.close();
  }

  public static double division(int a, int b) throws ArithmeticException {
    return a / b;
  }
}