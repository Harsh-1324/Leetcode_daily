
class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {

        while (k > 0) {

            int minIndex = 0;

            // Find the first occurrence of minimum
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[minIndex]) {
                    minIndex = i;
                }
            }

            // Multiply the minimum element
            nums[minIndex] = nums[minIndex] * multiplier;

            k--;
        }

        return nums;
    }
}
