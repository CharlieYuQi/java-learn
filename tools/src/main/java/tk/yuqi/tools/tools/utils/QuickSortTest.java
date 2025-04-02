package tk.yuqi.tools.tools.utils;

import java.util.Arrays;

public class QuickSortTest {


    /**
     * quick sort implement
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Sorts an array of integers using the quicksort algorithm.
     *
     * @param arr   the array of integers to be sorted
     * @param left  the starting index of the subarray to be sorted
     * @param right the ending index of the subarray to be sorted
     */
    public static void quickSort(int[] arr, int left, int right) {
        // Base case: if the left index is greater than or equal to the right index,
        // then the subarray has 0 or 1 elements and is already sorted.
        if (left >= right) {
            return;
        }

        // Partition the array and obtain the pivot index.
        int pivot = partition(arr, left, right);

        // Recursively sort the left subarray (elements smaller than the pivot).
        quickSort(arr, left, pivot - 1);

        // Recursively sort the right subarray (elements greater than the pivot).
        quickSort(arr, pivot + 1, right);
    }


    /**
     * Partition function to perform quicksort partitioning.
     *
     * @param arr   The array to be partitioned.
     * @param left  The left index of the partition.
     * @param right The right index of the partition.
     * @return The final position of the pivot after partitioning.
     */
    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[left]; // Select the pivot as the leftmost element
        int i = left; // Start with the left index
        int j = right; // Start with the right index

        while (i < j) {
            // Move j towards left until an element smaller than pivot is found
            while (i < j && arr[j] >= pivot) {
                j--;
            }

            if (i < j) {
                arr[i] = arr[j]; // Move the smaller element to the left side
                i++;
            }

            // Move i towards right until an element greater than pivot is found
            while (i < j && arr[i] <= pivot) {
                i++;
            }

            if (i < j) {
                arr[j] = arr[i]; // Move the greater element to the right side
                j--;
            }
        }

        arr[i] = pivot; // Place the pivot at its final sorted position
        return i;
    }

    /**
     * Generates a function comment for the given function body.
     *
     * @param  args      an array of command-line arguments
     * @return           void
     */
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7, 6, 3};
        quickSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
