
import java.util.*;

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        int ans = Integer.MIN_VALUE;

        for (int left = 0; left < n; left++) {
            int[] rowSum = new int[m];

            for (int right = left; right < n; right++) {

                // Calculate row-wise sums
                for (int i = 0; i < m; i++) {
                    rowSum[i] += matrix[i][right];
                }

                // Find maximum subarray sum <= k
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);

                int prefixSum = 0;

                for (int sum : rowSum) {
                    prefixSum += sum;

                    Integer target = set.ceiling(prefixSum - k);

                    if (target != null) {
                        ans = Math.max(ans, prefixSum - target);
                    }

                    set.add(prefixSum);
                }

                if (ans == k) {
                    return k;
                }
            }
        }

        return ans;
    }
}
