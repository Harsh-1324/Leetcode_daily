class Solution {
    public int findFinalValue(int[] nums, int original) {
        boolean found;

        do {
            found = false;

            for (int num : nums) {
                if (num == original) {
                    original = original * 2;
                    found = true;
                    break;
                }
            }
        } while (found);

        return original;
    }
}