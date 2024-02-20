package Exceptions.Exceptions2;

public class Freund {
  private String name;
  private byte alter;

  Freund(String name, byte alter) {
    if (alter < 0) {
      throw new IllegalArgumentException("Man kannnicht jünger als 0 Jahre sein");
    } else {
      this.alter = alter;
    }
    if (name == null) {
      throw new NullPointerException();
    } else {
      this.alter = alter;
    }
  }
}
