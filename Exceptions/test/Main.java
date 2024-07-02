package Exceptions.test;

public class Main {
    public static void main(String[] args) {
        try {
            ka(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ka(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ka(int nenner) throws NennerIstNullException {
        if (nenner == 0) {
            throw new NennerIstNullException();
        } else {
            System.out.println(nenner);
        }
    }
}