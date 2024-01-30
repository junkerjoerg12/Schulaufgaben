package objekteSortieren;

import java.util.ArrayList;

public class Seminar {
  private int seminarNr;
  private String bezeichnung;
  ArrayList<Student> hat = new ArrayList<>();

  public Seminar(String bezeichnung, int id) {
    this.bezeichnung = bezeichnung;
    seminarNr = id;
  }

  public void hinzufügenStudent(Student s) {
    hat.add(s);
  }

  public void sortierenBubble() {
    for (int i = 0; i < hat.size(); i++) {
      for (int j = 0; j < hat.size() - i - 1; j++) {
        if (hat.get(j).getId() > hat.get(j + 1).getId()) {
          Student temp = hat.get(j + 1);
          hat.set(j + 1, hat.get(j));
          hat.set(j, temp);
        }
      }
    }
  }

  public void sortierenInsertion() {
    for (int i = 0; i < hat.size(); i++) {
      int j = i - 1;
      Student temp = hat.get(i);
      while (j >= 0 && hat.get(j).getId() > temp.getId()) {
        hat.set(j + 1, hat.get(j));
        j--;
      }
      hat.set(j + 1, temp);
    }
  }

  public void sortierenQuick() {
    if (hat.size() <= 1) {
      return;
    } else {
      Student pivot = hat.get(hat.size() - 1);
      int j = 0;
      int i = j - 1;
      while (j <= hat.size() - 1) {
        if (hat.get(j).getId() < pivot.getId()) {
          i++;
          Student temp = hat.get(i);
          hat.set(i, hat.get(j));
          hat.set(j, temp);
        }
        j++;
      }
      i++;
      hat.set(hat.size() - 1, hat.get(i));
      hat.set(i, pivot);
      sortierenQuick(0, i - 1);
      sortierenQuick(i + 1, hat.size() - 1);

    }
  }

  private void sortierenQuick(int start, int end) {
    if (end - start <= 1) {
      return;
    } else {
      Student pivot = hat.get(end);
      int j = start;
      int i = j - 1;
      while (j <= end) {
        if (hat.get(j).getId() < pivot.getId()) {
          i++;
          Student temp = hat.get(i);
          hat.set(i, hat.get(j));
          hat.set(j, temp);
        }
        j++;
      }
      i++;
      hat.set(end, hat.get(i));
      hat.set(i, pivot);
      sortierenQuick(start, i - 1);
      sortierenQuick(i + 1, end);
    }
  }

  public void sortierenShell() {
    int n = hat.size();

    for (int gap = n / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < n; i++) {
        Student key = hat.get(i);
        int j = i;
        while (j >= gap && hat.get(j - gap).getId() > key.getId()) {
          hat.set(j, hat.get(j - gap));
          j -= gap;
        }
        hat.set(j, key);
      }
    }
  }

}
