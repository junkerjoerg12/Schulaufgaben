package Multithreading.test;

public class Main {
  public static void main(String[] args) {
    Test test1 = new Test(1);
    Test test2 = new Test(2);

    Thread thread1 = new Thread(test1);
    Thread thread2 = new Thread(test2);

    thread1.start();

    try {
      Thread.sleep(150);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    thread2.start();
    // thread1.

  }

}
