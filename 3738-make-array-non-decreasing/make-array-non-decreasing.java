
class Solution {
    public int maximumPossibleSize(int[] nums) {

        int count = 1;
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] >= max) {
                count++;
                max = nums[i];
            }
        }

        return count;
    }
}
