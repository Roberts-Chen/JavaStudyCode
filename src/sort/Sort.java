package sort;

import java.util.Arrays;

public class Sort {
    public int[] bubbleSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            boolean isSorted = false;
            for (int j = 0; j < len - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int swap = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = swap;
                    isSorted = true;
                }
            }
            if (!isSorted) break;
        }
        return nums;
    }

    public int[] insertionSort(int[] nums) {
        int len = nums.length;
        // 分为待排序区间和有序区间
        for (int i = 1; i < len; i++) {
            int preIndex = i - 1;
            int current = nums[i];
            while (preIndex >= 0 && nums[preIndex] > nums[i]) {
                nums[i] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = current;
        }
        return nums;
    }

    public int[] shellSort(int[] nums) {
        // 希尔排序是希尔通过简单插入排序优化得来，引入gap的概念将原数组划分为几个小数组
        int len = nums.length;
        int gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int preIndex = i - gap;
                int current = nums[i];
                while (preIndex >= 0 && nums[preIndex] > current) {
                    nums[preIndex + gap] = nums[preIndex];
                    preIndex -= gap;
                }
                nums[preIndex + gap] = current;
            }
            gap /= 2;
        }
        return nums;
    }



    // 堆排序所要用到的全局变量，用来记录堆的长度
    private int heapLen;

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void buildMaxHeap(int[] nums) {
        int len = nums.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(nums, i);
        }
    }

    /**
     * 调整整个堆
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

    /**
     * 堆排序算法
     * @param nums
     * @return
     */
    public int[] heapSort(int[] nums) {
        int len = nums.length;
        heapLen = len;
        buildMaxHeap(nums);
        for (int i = len - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapLen--;
            heapify(nums, 0);
        }
        return nums;
    }


    public static void main(String[] args) {
        int[] nums = {9, 7, 6, 8, 1, 4, 5, 2, 3};
        Sort sort = new Sort();
        int[] sorted = sort.bubbleSort(nums);
        System.out.println(Arrays.toString(sorted));


        int[] sortedByInsertionSort = sort.insertionSort(nums);
        System.out.println(Arrays.toString(sortedByInsertionSort));

        int[] sortedByShellSort = sort.shellSort(nums);
        System.out.println(Arrays.toString(sortedByShellSort));

        int[] sortedByHeapSort = sort.heapSort(nums);
        System.out.println(Arrays.toString(sortedByHeapSort));
    }
}
