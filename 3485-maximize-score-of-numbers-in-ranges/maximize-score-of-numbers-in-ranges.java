import java.util.Arrays;

class Solution {
    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        
        long low = 0;
        long high = (long) 2e9 + 7; // Safe upper bound for maximum possible absolute difference
        long ans = 0;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canCheck(start, d, mid)) {
                ans = mid; // Valid score, try for a larger score
                low = mid + 1;
            } else {
                high = mid - 1; // Too large, try smaller score
            }
        }
        
        return (int) ans;
    }
    
    private boolean canCheck(int[] start, int d, long target) {
        long prev = start[0]; // Greedily pick the smallest possible element for the first interval
        
        for (int i = 1; i < start.length; i++) {
            long nextVal = Math.max((long) start[i], prev + target);
            
            // Check if the chosen value exceeds the upper bound of the current interval
            if (nextVal > (long) start[i] + d) {
                return false;
            }
            prev = nextVal;
        }
        
        return true;
    }
}