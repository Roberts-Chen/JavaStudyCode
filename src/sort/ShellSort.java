package sort;

import java.util.Arrays;

/**
 * 希尔排序就是普通插入排序的优化版本
 * 平均时间复杂度为O(nlogn)
 */
public class ShellSort {
    public int[] shellSort(int[] nums) {
        // 希尔排序步骤
        // 采用希尔增量gap=length/2
        // 从第一个增量序列的第二个元素开始向后遍历，也就是从i=gap开始向后遍历，那么i-gap就是插入排序中有序区间的最后一个元素
        // 依次类推，i-2*gap、i-3*gap就是整个有序区间，在细节操作上和普通插入排序是一样的，只不过这里将整个数组划分成多个增量序列
        // 因此每次的步移就是gap
        int len = nums.length;
        int gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int preIndex = i - gap;
                int cur = nums[i];
                while (preIndex >= 0 && nums[preIndex] > cur) {
                    nums[preIndex + gap] = nums[preIndex];
                    preIndex -= gap;
                }
                nums[preIndex + gap] = cur;
            }
            gap /= 2;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 5, 4, 7, 9, 8, 6};
        int[] sorted = new ShellSort().shellSort(nums);
        System.out.println(Arrays.toString(sorted));
    }
}
