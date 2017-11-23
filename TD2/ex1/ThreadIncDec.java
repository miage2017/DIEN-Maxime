package ex1;

/**
 * Created by M.Dien on 16/11/2017.
 * MIAGE Student (M1)
 */

public class ThreadIncDec implements Runnable{
    static int maxIter = 1000;
    static int minIter = 1;



    public void run() {

    }

    public static void main(String[] args) throws Exception {
        Thread TA = new Thread(new Runnable() {
            String nom = "Thread TA";

            @Override
            public void run() {
                System.out.format("Initialisation [%s]\n", nom);
                for (int i = 1; i <= maxIter; i++) {
                    System.out.format("[%s] : %d\n", nom, i);
                }
            }
        });

        Thread TB = new Thread(new Runnable() {
            String nom = "Thread TB";

            @Override
            public void run() {
                System.out.format("Initialisation [%s]\n", nom);
                for (int i = 1000; i >= minIter; i--) {
                    System.out.format("[%s] : %d\n", nom, i);
                }
            }
        });
        TA.start();
        TA.join();

        TB.start();
        TB.join();
    }
}
