
import java.util.*;

class Solution {
    private static final long OFFSET = 1_000_000_002L;

    public int[] makeParityAlternating(int[] nums) {
        int n = nums.length;

        int evenStartOps = 0;
        int oddStartOps = 0;

        // Calculate operations for both patterns
        for (int i = 0; i < n; i++) {
            int parity = nums[i] & 1;

            if (parity != (i & 1)) {
                evenStartOps++;
            }

            if (parity != ((i + 1) & 1)) {
                oddStartOps++;
            }
        }

        int minOps = Math.min(evenStartOps, oddStartOps);
        long minRange = Long.MAX_VALUE;

        // Check only patterns requiring minimum operations
        if (evenStartOps == minOps) {
            minRange = Math.min(
                minRange, minRangeForPattern(nums, 0)
            );
        }

        if (oddStartOps == minOps) {
            minRange = Math.min(
                minRange, minRangeForPattern(nums, 1)
            );
        }

        return new int[]{minOps, (int) minRange};
    }

    private long minRangeForPattern(int[] nums, int startParity) {
        int n = nums.length;

        long[] candidates = new long[2 * n];
        int size = 0;

        // Generate all possible values
        for (int i = 0; i < n; i++) {
            long x = nums[i];
            int expectedParity = (i + startParity) & 1;

            if (((int) x & 1) == expectedParity) {
                candidates[size++] = encode(x, i, n);
            } else {
                candidates[size++] = encode(x - 1, i, n);
                candidates[size++] = encode(x + 1, i, n);
            }
        }

        Arrays.sort(candidates, 0, size);

        int[] counts = new int[n];
        int covered = 0;
        int left = 0;

        long best = Long.MAX_VALUE;

        // Sliding window
        for (int right = 0; right < size; right++) {
            int rightIndex = (int) (candidates[right] % n);

            if (counts[rightIndex]++ == 0) {
                covered++;
            }

            while (covered == n) {
                long leftValue = candidates[left] / n - OFFSET;
                long rightValue = candidates[right] / n - OFFSET;

                best = Math.min(best, rightValue - leftValue);

                int leftIndex = (int) (candidates[left] % n);

                if (--counts[leftIndex] == 0) {
                    covered--;
                }

                left++;
            }
        }

        return best;
    }

    // Encode value and original index into one sortable long
    private long encode(long value, int index, int n) {
        return (value + OFFSET) * n + index;
    }
}
