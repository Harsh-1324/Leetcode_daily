import java.util.Arrays;

class Solution {
    // Memoization table to store results for each bitmask state
    private Integer[] memo;
    private int[][] gcdCache;

    public int maxScore(int[] nums) {
        int m = nums.length;
        memo = new Integer[1 << m];
        gcdCache = new int[m][m];

        // Precompute GCD for all pairs to speed up calculations
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                gcdCache[i][j] = gcd(nums[i], nums[j]);
            }
        }

        return dp(0, nums);
    }

    private int dp(int mask, int[] nums) {
        // Base case: all elements are used
        if (mask == (1 << nums.length) - 1) {
            return 0;
        }

        // Return memoized result if already computed
        if (memo[mask] != null) {
            return memo[mask];
        }

        int maxScore = 0;
        int op = (Integer.bitCount(mask) / 2) + 1;

        // Try all pairs of unused elements
        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) == 0) {
                for (int j = i + 1; j < nums.length; j++) {
                    if ((mask & (1 << j)) == 0) {
                        int nextMask = mask | (1 << i) | (1 << j);
                        int score = op * gcdCache[i][j] + dp(nextMask, nums);
                        maxScore = Math.max(maxScore, score);
                    }
                }
            }
        }

        return memo[mask] = maxScore;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}