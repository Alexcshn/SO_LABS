import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void sortArray(int[] arr, int k) {
        Heap minHeap = new Heap();
        // Add first k+1 items to the min heap
        for (int i = 0; i <= k && i < arr.length; i++) {
            minHeap.insert(arr[i]);
        }

        int index = 0;
        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = minHeap.remove();
            minHeap.insert(arr[i]);
        }

        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.remove();
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {6, 5, 3, 2, 8, 10, 9};
        int k1 = 3;
        System.out.println("Initial Array 1: " + Arrays.toString(arr1));
        sortArray(arr1, k1);
        System.out.println("Sorted Array 1: " + Arrays.toString(arr1));

        int[] arr2 = {10, 9, 8, 7, 4, 70, 60, 50};
        int k2 = 4;
        System.out.println("Initial Array 2: " + Arrays.toString(arr2));
        sortArray(arr2, k2);
        System.out.println("Sorted Array 2: " + Arrays.toString(arr2));
    }
}


class Heap {
    private List<Integer> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    private void heapifyUp(int index) {
        if (index == 0) {
            return;
        }
        int parentIndex = (index - 1) / 2;
        if (heap.get(parentIndex) > heap.get(index)) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;
        if (leftChildIndex < heap.size() && heap.get(leftChildIndex) < heap.get(smallest)) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex) < heap.get(smallest)) {
            smallest = rightChildIndex;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(int element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public Integer remove() {
        if (heap.isEmpty()) {
            return null;
        }
        int removedItem = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return removedItem;
    }

    public Integer peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}