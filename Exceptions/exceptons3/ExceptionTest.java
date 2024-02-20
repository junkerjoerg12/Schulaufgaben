package Exceptions.exceptons3;

public class ExceptionTest {
  public static void main(String[] args) {
    // Hier wandeln Sie die Übergabeparameter in Integer-Werte um
    // Hier muss irgendwie die Fehlerbehandlung erfolgen
    int dividend = 0;// Integer.parseInt(args[0]);
    int divisor = 0;// Integer.parseInt(args[1]);
    double ergebnis;
    try {
      ergebnis = divide(dividend, divisor);
      System.out.println("Das Ergebnis der Division lautet: " + ergebnis);
    } catch (DivisionByZeroException e) {
      // System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  // Eine Methode, die eine DivisionByZeroException wirft
  public static double divide(int dividend, int divisor) throws DivisionByZeroException {
    if (divisor == 0) {
      throw new DivisionByZeroException();
    } else {
      return dividend / divisor;
    }
  }
}