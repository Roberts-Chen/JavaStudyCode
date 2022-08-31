package sort;

import java.util.Arrays;

public class HeapSort {

    private static int heapLen;

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public void buildMaxHeap(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            heapify(nums, i);
        }
    }

    /**
     * 验证并调整大顶堆
     * @param nums
     * @param i
     */
    public void heapify(int[] nums, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        if (left < heapLen && nums[left] > nums[largest]) {
            largest = left;
        }
        if (right < heapLen && nums[right] > nums[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(nums, i, largest);
            heapify(nums, largest);
        }
    }

    public int[] heapSort(int[] nums) {
        heapLen = nums.length;
        buildMaxHeap(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapLen--;
            heapify(nums, 0);
        }
        return nums;
    }


    public static void main(String[] args) {
        int[] nums = {9, 7, 6, 8, 1, 4, 5, 2, 3};
        int[] rest = new HeapSort().heapSort(nums);
        System.out.println(Arrays.toString(rest));
    }
}
