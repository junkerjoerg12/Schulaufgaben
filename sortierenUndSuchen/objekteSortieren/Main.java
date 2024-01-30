package objekteSortieren;

public class Main {
  public static void main(String[] args) {
    Seminar seminar = new Seminar("technick", 0);
    for (int i = 0; i < 200; i++) {
      seminar.hinzufügenStudent(new Student("null", "null", 845324 - i * 5));
    }
    seminar.sortierenShell();
    for (Student i : seminar.hat) {
      System.err.println(i.getId());
    }
  }
}
