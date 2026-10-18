import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();

        for (int[] row : matrix) {
            StringBuilder pattern = new StringBuilder();

            for (int num : row) {
                pattern.append(num == row[0] ? '0' : '1');
            }

            String key = pattern.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int max = 0;

        for (int count : map.values()) {
            max = Math.max(max, count);
        }

        return max;
    }
}