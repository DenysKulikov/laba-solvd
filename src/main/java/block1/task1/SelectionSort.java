package block1.task1;

public class SelectionSort {
    public void sort(int[] arr) {
        int arrayLength = arr.length;

        // One by one move boundary of unsorted subArray
        for (int i = 0; i < arrayLength - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < arrayLength; j++) {
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            }

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public void printArray(int[] arr) {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }
}
