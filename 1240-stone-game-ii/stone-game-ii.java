class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // Suffix sum
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // memo[i][M]
        int[][] memo = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }

        return dp(0, 1, piles, suffix, memo);
    }

    private int dp(int i, int M, int[] piles, int[] suffix, int[][] memo) {
        int n = piles.length;

        if (i >= n) {
            return 0;
        }

        if (memo[i][M] != -1) {
            return memo[i][M];
        }

        // Can take all remaining piles
        if (i + 2 * M >= n) {
            return suffix[i];
        }

        int best = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M; X++) {
            int opponent = dp(
                i + X,
                Math.max(M, X),
                piles,
                suffix,
                memo
            );

            best = Math.max(best, suffix[i] - opponent);
        }

        memo[i][M] = best;
        return best;
    }
}