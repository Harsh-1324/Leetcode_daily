import java.util.*;

class Solution {
    private static final int MAX_COUNT = 19900;
    private static final int LIMIT = 1000000;
    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockedSet = new HashSet<>();
        for (int[] b : blocked) {
            blockedSet.add(hash(b[0], b[1]));
        }

        return bfs(source, target, blockedSet) && bfs(target, source, blockedSet);
    }

    private boolean bfs(int[] start, int[] dest, Set<Long> blockedSet) {
        Queue<long[]> queue = new LinkedList<>();
        Set<Long> visited = new HashSet<>();
        
        queue.offer(new long[]{start[0], start[1]});
        visited.add(hash(start[0], start[1]));

        while (!queue.isEmpty()) {
            long[] curr = queue.poll();
            long x = curr[0];
            long y = curr[1];

            if (x == dest[0] && y == dest[1]) {
                return true;
            }

            if (visited.size() > MAX_COUNT) {
                return true;
            }

            for (int[] dir : directions) {
                long nx = x + dir[0];
                long ny = y + dir[1];

                if (nx >= 0 && nx < LIMIT && ny >= 0 && ny < LIMIT) {
                    long h = hash(nx, ny);
                    if (!blockedSet.contains(h) && !visited.contains(h)) {
                        visited.add(h);
                        queue.offer(new long[]{nx, ny});
                    }
                }
            }
        }

        return visited.size() > MAX_COUNT;
    }

    private long hash(long x, long y) {
        return (x << 32) | y;
    }
}