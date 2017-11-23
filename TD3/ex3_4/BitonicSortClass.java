package ex3_4;

import java.util.Arrays;

/**
 * Created by M.Dien on 21/11/2017.
 * MIAGE Student (M1)
 */

public class BitonicSortClass {

    public static void bitonicSort(int[] a) {
        if (a.length >= 2) {
            // découpage
            int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

            // tri des deux parties
            bitonicSort(left);
            bitonicSort(right);
            // fusion des parties
            fusion(left, right, a);
        }
    }

    private static void fusion(int[] left, int[] right, int[] a) {
        int rightInc = 0;
        int leftInc = 0;
        for(int i = 0; i < a.length; i++) {
            if( (leftInc < left.length && left[leftInc] < right[rightInc]) || rightInc >= right.length) {
                a[i] = left[leftInc];
                leftInc++;			}
            else{
                a[i] = right[rightInc];
                rightInc++;
            }
        }
    }
    public static void main(String[] args){
        int[] a = RandomTab.createRandomArray(5);
        bitonicSort(a);
        System.out.println(Arrays.toString(a));
    }
}
