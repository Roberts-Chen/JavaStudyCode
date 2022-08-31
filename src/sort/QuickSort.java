package sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    /**
     * 快速排序算法
     * @param nums
     * @return
     */
    public void quickSort(int[] nums, int lo, int hi) {
        if (hi - lo < 2) {
            return;
        }
        int mid = partition(nums, lo, hi);
        quickSort(nums, lo, mid);
        quickSort(nums, mid + 1, hi);
    }


    public int partition(int[] nums, int lo, int hi) {
        // 任选一个元素与首元素进行交换
//        Random random = new Random();
//        int randomIdx = lo + random.nextInt() % (hi - lo);
//        int swap = nums[lo];
//        nums[lo] = nums[randomIdx];
//        nums[randomIdx] = swap;
        hi--;
        int pivot = nums[lo];
        while (lo < hi) {
            while (lo < hi) {
                if (nums[hi] > pivot) {
                    hi--;
                } else {
                    nums[lo] = nums[hi];
                    lo++;
                    break;
                }
            }
            while (lo < hi) {
                if (nums[lo] < pivot) {
                    lo++;
                } else {
                    nums[hi] = nums[lo];
                    hi--;
                    break;
                }
            }
        }
        nums[lo] = pivot;
        System.out.println(Arrays.toString(nums));
        return lo;
    }

    public int partition2(int[] nums, int low, int high) {
        int pivot = nums[high];
        int pointer = low;
        for (int i = low; i < high; i++) {
            if (nums[i] <= pivot) {
                int temp = nums[pointer];
                nums[pointer] = nums[i];
                nums[i] = temp;
                pointer++;
            }
        }
        int temp = nums[pointer];
        nums[pointer] = nums[high];
        nums[high] = temp;
        return pointer;
    }

    public void quickSort2(int[] nums, int low, int high) {
        if (low < high) {
            int position = partition2(nums, low, high);
            quickSort(nums, low, position - 1);
            quickSort(nums, position + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] nums = {9, 7, 6, 8, 1, 4, 5, 2, 3};
        new QuickSort().quickSort2(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
