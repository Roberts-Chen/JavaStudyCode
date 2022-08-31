package sort;

import java.util.Arrays;

public class MergeSort {

    public int[] mergeSort(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return nums;
        }
        int middle = len / 2;
        int[] nums1 = Arrays.copyOfRange(nums, 0, middle);
        int[] nums2 = Arrays.copyOfRange(nums, middle, len);
        // 将原数组分成两个子数组去进行归并，然后通过递归，一步步的得到有序的数组
        return merge(mergeSort(nums1), mergeSort(nums2));
    }

    /**
     * 归并两个有序的子数组
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] merge(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int idx = 0, idx1 = 0, idx2 = 0;
        int[] result = new int[len1 + len2];
        while (idx1 < len1 && idx2 < len2) {
            if (nums1[idx1] <= nums2[idx2]) {
                result[idx] = nums1[idx1];
                idx1++;
            } else {
                result[idx] = nums2[idx2];
                idx2++;
            }
            idx++;
        }
        if (idx1 < len1) {
            while (idx1 < len1) {
                result[idx] = nums1[idx1];
                idx++;
                idx1++;
            }
        }
        if (idx2 < len2) {
            while (idx2 < len2) {
                result[idx] = nums2[idx2];
                idx++;
                idx2++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {9, 7, 6, 8, 1, 4, 5, 2, 3};
        int[] sorted = new MergeSort().mergeSort(nums);
        System.out.println(Arrays.toString(sorted));
    }
}
