package sort;

import java.util.Arrays;

/**
 * 冒泡排序算法
 */
public class BubbleSort {
    public int[] bubbleSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            boolean isSorted = false;
            for (int j = 0; j < len - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int swap = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = swap;
                    isSorted = true;
                }
            }
            if (!isSorted) {
                break;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 5, 4, 7, 9, 8, 6};
        int[] sorted = new BubbleSort().bubbleSort(nums);
        System.out.println(Arrays.toString(sorted));
    }
}
