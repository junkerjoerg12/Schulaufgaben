package Multithreading.test;

public class Test implements Runnable {
  private int zahl;

  public Test(int zahl) {
    this.zahl = zahl;
  }

  @Override
  public void run() {
    while (true) {
      System.out.println(zahl);

      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  }
}