package ex3_4;

import java.util.Random;

/**
 * Created by M.Dien on 21/11/2017.
 * MIAGE Student (M1)
 */

public class RandomTab {

    private static final Random RAND = new Random(10);
    // NEW tableau aléatoire
    public static int[] createRandomArray(int length) {
        int[] a = new int[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = RAND.nextInt(10000);
        }
        return a;
    }
}
