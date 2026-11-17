
class Solution {
    public int countSpecialIntegers(int[] nums) {
        boolean[] seen = new boolean[101];
        boolean[] special = new boolean[101];

        int prev = -1;

        for (int num : nums) {
            if (num != prev) {
                if (seen[num]) {
                    special[num] = false;
                } else {
                    seen[num] = true;
                    special[num] = true;
                }
            }
            prev = num;
        }

        int count = 0;

        for (int i = 1; i <= 100; i++) {
            if (special[i]) {
                count++;
            }
        }

        return count;
    }
}
