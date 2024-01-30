package objekteSortieren;

public class Student {
  private int id;
  private String name;
  private String vorname;

  public Student(String name, String vorname, int id) {
    this.name = name;
    this.vorname = vorname;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getvorname() {
    return vorname;
  }
}
