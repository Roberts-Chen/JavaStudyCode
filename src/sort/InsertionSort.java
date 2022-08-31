package sort;

import java.util.Arrays;

public class InsertionSort {
    public int[] insertionSort(int[] nums) {
        // 算法步骤
        // 1.将第一个元素确定为已排序元素，第一个元素所在区间确定为已排序空间，而后面的所有元素确定为未排序元素，即未排序空间
        // 2.取出未排序区间的第一个元素a，与排序区间的最后一个元素b进行比较，如果a<b，则将b向后移动
        // 3.重复步骤2
        // 4.找到a>b的元素b之后，将a插入到b的后面
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int a = nums[i], j;
            for (j = i - 1; j >= 0; j--) {
                if (a < nums[j]) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = a;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 5, 4, 7, 9, 8, 6};
        int[] sorted = new InsertionSort().insertionSort(nums);
        System.out.println(Arrays.toString(sorted));
    }
}

/**
 * 2, 3, 1, 5, 4, 7, 9, 8, 6
 * j  i
 * 3, 3, 1, 5, 4, 7, 9, 8, 6
 * j  i  a = 3
 * 2 4 3
 *   j i
 * 2 4 4
 * j   i
 */