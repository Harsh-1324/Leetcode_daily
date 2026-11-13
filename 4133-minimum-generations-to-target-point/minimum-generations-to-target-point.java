
import java.util.*;

class Solution {
    public int minGenerations(int[][] points, int[] target) {
        boolean[][][] seen = new boolean[7][7][7];

        List<int[]> all = new ArrayList<>();

        // Generation 0
        for (int[] p : points) {
            if (p[0] == target[0] &&
                p[1] == target[1] &&
                p[2] == target[2]) {
                return 0;
            }

            if (!seen[p[0]][p[1]][p[2]]) {
                seen[p[0]][p[1]][p[2]] = true;
                all.add(p.clone());
            }
        }

        int generation = 0;

        while (true) {
            List<int[]> next = new ArrayList<>();

            int size = all.size();

            // Generate the next generation
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {

                    int[] a = all.get(i);
                    int[] b = all.get(j);

                    int x = (a[0] + b[0]) / 2;
                    int y = (a[1] + b[1]) / 2;
                    int z = (a[2] + b[2]) / 2;

                    if (!seen[x][y][z]) {
                        seen[x][y][z] = true;

                        if (x == target[0] &&
                            y == target[1] &&
                            z == target[2]) {
                            return generation + 1;
                        }

                        next.add(new int[]{x, y, z});
                    }
                }
            }

            // No new points means target is unreachable
            if (next.isEmpty()) {
                return -1;
            }

            all.addAll(next);
            generation++;
        }
    }
}
