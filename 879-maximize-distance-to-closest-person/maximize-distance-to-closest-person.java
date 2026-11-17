
class Solution {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int maxDist = 0;
        int prev = -1;

        for (int i = 0; i < n; i++) {

            if (seats[i] == 1) {

                if (prev == -1) {
                    // Leading empty seats
                    maxDist = i;
                } else {
                    // Empty seats between two people
                    maxDist = Math.max(maxDist, (i - prev) / 2);
                }

                prev = i;
            }
        }

        // Trailing empty seats
        maxDist = Math.max(maxDist, n - 1 - prev);

        return maxDist;
    }
}
