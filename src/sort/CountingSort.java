package sort;

import java.util.Arrays;

public class CountingSort {

    public int[] countingSort(int[] nums) {
        int maxNum = Arrays.stream(nums).max().getAsInt();
        int minNum = Arrays.stream(nums).min().getAsInt();
        int[] count = new int[maxNum - minNum + 1];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - minNum]++;
        }
        System.out.println("计数数组count-->" + Arrays.toString(count));
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        System.out.println("计数数组count-->" + Arrays.toString(count));
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int idx = count[nums[i] - minNum] - 1;
            res[idx] = nums[i];
            count[nums[i] - minNum]--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {9, 7, 6, 8, 1, 4, 5, 2, 3};
        int[] res = new CountingSort().countingSort(nums);
        System.out.println(Arrays.toString(res));
    }
}
