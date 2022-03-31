package algorythm.Practice.inflearn;

import org.w3c.dom.ranges.RangeException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class practice {
    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5};

        int[] newt = Arrays.copyOfRange(test,0, test.length/2);
        newt[1] = 100;

        System.out.println("newt = " + Arrays.toString(newt));
        System.out.println("test = " + Arrays.toString(test));
    }


    public static int[] bubbleSort(int[] array){
        boolean flag = true;
        while(flag){
            flag = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    flag = true;
                }
            }
        }

        return array;
    }

    public static int[] selectionSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[min] > array[j]) min = j;

            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }

        return array;
    }

    public static int[] insertionSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1 ; j > 0 ; j--) {
                if (array[j] < array[j - 1]){
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;

                }else
                    break;
            }
        }
        return array;
    }

    public static int[] quickSort(int[] array){
        if (array.length == 1)
            return array;
        else{
            int pivot = array[0];
            ArrayList<Integer> leftList = new ArrayList();
            ArrayList<Integer> rightList = new ArrayList();

            for (int i = 1; i < array.length; i++) {
                if (array[i]< pivot) leftList.add(array[i]);
                if (array[i]>= pivot) rightList.add(array[i]);
            }
            int[] leftArray = new int [leftList.size()];
            int[] rightArray = new int [rightList.size()];
            for (int i = 0; i < leftList.size(); i++) leftArray[i] = leftList.get(i);
            for (int i = 0; i < rightList.size(); i++) rightArray[i] = rightList.get(i);

            int[] leftArraySorted = quickSort(leftArray);
            int[] rightArraySorted = quickSort(rightArray);
            for (int i = 0; i < array.length; i++) {
                if (i < leftArraySorted.length) array[i] = leftArraySorted[i];
                if (i == leftArraySorted.length) array[i] = pivot;
                if (i > leftArraySorted.length) array[i] = rightArraySorted[i];
            }
        }

        return array;
    }

    int[] split(int[] array) {
        if (array.length == 1) return array;
        else{
            int[] left = Arrays.copyOfRange(array, 0, array.length/2);
            int[] right = Arrays.copyOfRange(array, array.length/2, array.length);

            return merge(split(left), split(right));
        }
    }



    int[] merge(int[] left, int[] right) {
        int[] array = new int[left.length + right.length];
        int lPointer = 0;
        int rPointer = 0;

        int i = 0;
        while (lPointer < left.length && rPointer < right.length) {
            if (left[lPointer] < right[rPointer])
                array[i] = left[lPointer++];
            else
                array[i] = right[rPointer++];
            i++;
        }

        if (lPointer > rPointer)
            for (int j = i; j < array.length; j++)
                array[j] = right[rPointer++];
        else
            for (int j = i; j < array.length; j++)
                array[j] = left[lPointer++];

        return array;
    }

    boolean binarySearceh(int[] array, int findData) {
        if (array.length <= 1) {
            if (array[0] == findData) return true;
            else return false;
        }
        else{
            int mid = array[array.length/2];
            if(mid == findData) return true;

            if (mid < findData)
                return binarySearceh(Arrays.copyOfRange(array, array.length/2, array.length), findData);
            else
                return binarySearceh(Arrays.copyOfRange(array, 0, array.length/2), findData);
        }
    }
}
