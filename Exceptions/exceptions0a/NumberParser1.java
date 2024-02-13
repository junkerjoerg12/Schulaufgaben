package Exceptions.exceptions0a;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberParser1 {
  public NumberParser1(String number) {
    DecimalFormat numberFormat = new DecimalFormat();
    try {
      Number xNumber = numberFormat.parse(number);
      double x = xNumber.doubleValue();
      System.out.println("Die eingegebene Zahl = " + x);
    } catch (ParseException pEx) {
      System.out.println("Der eingegebene Parameter konnte nicht in eine Zahl umgewandelt werden.");
    }
  }

  public static void main(String[] args) {
    try {
      new NumberParser1(args[0]);
    } catch (ArrayIndexOutOfBoundsException aiobEx) {
      System.out.println("Das Programm erwartet eine Zahl als Eingabeparameter.");
    }
  }
}