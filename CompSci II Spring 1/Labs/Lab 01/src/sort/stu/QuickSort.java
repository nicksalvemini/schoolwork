package sort.stu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Perform an out of place quick sort on a native array of integers.
 * <p>
 * quicksort:
 * best=O(nlogn)
 * worst=O(n^2)
 *
 * @author RIT CS
 */
public class QuickSort {
    /**
     * Partition the array for values less than the pivot.
     *
     * @param data the full array of data
     * @param pivot the pivot
     * @return data less than the pivot
     */
    private static ArrayList<Integer> partitionLess(ArrayList<Integer> data, int pivot) {
        ArrayList<Integer> less = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            if(data.get(i) < pivot){less.add(data.get(i));}
        }
        return less;
    }

    /**
     * Partition the array for values equal to the pivot.
     *
     * @param data the full array of data
     * @param pivot the pivot
     * @return data equal to the pivot
     */
    private static ArrayList<Integer> partitionEqual(ArrayList<Integer> data, int pivot) {
        ArrayList<Integer> equal = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            if(data.get(i) == pivot){equal.add(data.get(i));}
        }
        return equal;
    }

    /**
     * Partition the array for values greater than the pivot.
     *
     * @param data the full array of data
     * @param pivot the pivot
     * @return data greater than  the pivot
     */
    private static ArrayList<Integer> partitionGreater(ArrayList<Integer> data, int pivot) {
        ArrayList<Integer> greater = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            if(data.get(i) > pivot){greater.add(data.get(i));}
        }
        return greater;
    }

    /**
     * Performs a quick sort and returns a newly sorted array.
     *
     * @param data the data to be sorted
     * @return a sorted array
     */
    public static ArrayList<Integer> quickSort(ArrayList<Integer> data) {
        if(data.size() == 0){return data;}
        else{
            int pivot = data.get(0);
            ArrayList<Integer> less = quickSort(partitionLess(data, pivot)),
                               equal = partitionEqual(data, pivot),
                               greater = quickSort(partitionGreater(data, pivot)),
                               result = less;
            for(int i = 0; i < equal.size(); ++i){result.add(equal.get(i));}
            for(int i = 0; i < greater.size(); ++i){result.add(greater.get(i));}
            return(result);
        }
    }

    /**
     * Test function for quickSort.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        int[][] DATA = {
                {},
                {0},
                {0, 1},
                {1, 0},
                {0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0},
                {9, 5, 2, 4, 3, 8, 0, 4, 0, 9}
        };

        for (int[] data : DATA) {
            // create two lists of the data
            List<Integer> sortData = Arrays.stream(data).boxed().collect(Collectors.toList());
            List<Integer> sorted = Arrays.stream(data).boxed().collect(Collectors.toList());
            // quick sort returns a new sorted list
            sortData = quickSort((ArrayList<Integer>) sortData);
            // use built in sort to compare against
            Collections.sort(sorted);
            // show the results of the comparison
            System.out.print("quickSort: " + Arrays.stream(data).boxed().collect(Collectors.toList()) +
                    " result: " + sortData);
            System.out.println(sortData.equals(sorted) ? " OK" : " FAIL");
        }
    }
}
