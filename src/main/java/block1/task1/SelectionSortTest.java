package block1.task1;

public class SelectionSortTest {
    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] arr = { 64, 25, 12, 22, 11 };

        selectionSort.sort(arr);
        System.out.println("Sorted array");
        selectionSort.printArray(arr);
    }
}
