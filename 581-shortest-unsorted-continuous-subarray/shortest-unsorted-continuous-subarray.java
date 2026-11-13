
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;

        int left = -1;
        int right = -1;

        int maxSeen = Integer.MIN_VALUE;
        int minSeen = Integer.MAX_VALUE;

        // Find right boundary
        for (int i = 0; i < n; i++) {
            if (nums[i] < maxSeen) {
                right = i;
            } else {
                maxSeen = nums[i];
            }

            // Find left boundary
            int j = n - 1 - i;

            if (nums[j] > minSeen) {
                left = j;
            } else {
                minSeen = nums[j];
            }
        }

        if (right == -1) {
            return 0;
        }

        return right - left + 1;
    }
}
