import java.util.Arrays;

public class Solution {
    private int m, n;
    private int[][][][][] memo;
    // Base-3 powers for rolling mask: p3[i] = 3^i
    private int[] p3;
    // Happiness scores
    private static final int INTROVERT = 1;
    private static final int EXTROVERT = 2;
    private static final int[][] H = {
        {0, 0, 0},
        {0, -60, -10}, // Introvert interaction: -30 each neighbor
        {0, -10, 40}   // Extrovert interaction: +20 each neighbor
    };
    private static final int[] START_H = {0, 120, 40};

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.n = n;
        this.p3 = new int[n + 1];
        p3[0] = 1;
        for (int i = 1; i <= n; i++) {
            p3[i] = p3[i - 1] * 3;
        }

        // memo[row][col][introCount][extroCount][mask]
        // max values: m=5, n=5, intro=6, extro=6, mask=243
        memo = new int[m][n][introvertsCount + 1][extrovertsCount + 1][p3[n]];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= introvertsCount; k++) {
                    for (int l = 0; l <= extrovertsCount; l++) {
                        Arrays.fill(memo[i][j][k][l], -1);
                    }
                }
            }
        }

        return dfs(0, 0, introvertsCount, extrovertsCount, 0);
    }

    private int dfs(int r, int c, int intro, int extro, int mask) {
        if (r == m) return 0;
        if (c == n) return dfs(r + 1, 0, intro, extro, mask);
        if (memo[r][c][intro][extro][mask] != -1) {
            return memo[r][c][intro][extro][mask];
        }

        // Option 1: Leave cell empty
        int nextMask = (mask % p3[n - 1]) * 3;
        int res = dfs(r, c + 1, intro, extro, nextMask);

        // Option 2: Place Introvert
        if (intro > 0) {
            int currentType = INTROVERT;
            int gain = START_H[currentType];
            gain += getNeighborGain(r, c, currentType, mask);
            int newMask = (mask % p3[n - 1]) * 3 + currentType;
            res = Math.max(res, gain + dfs(r, c + 1, intro - 1, extro, newMask));
        }

        // Option 3: Place Extrovert
        if (extro > 0) {
            int currentType = EXTROVERT;
            int gain = START_H[currentType];
            gain += getNeighborGain(r, c, currentType, mask);
            int newMask = (mask % p3[n - 1]) * 3 + currentType;
            res = Math.max(res, gain + dfs(r, c + 1, intro, extro - 1, newMask));
        }

        return memo[r][c][intro][extro][mask] = res;
    }

    private int getNeighborGain(int r, int c, int type, int mask) {
        int gain = 0;
        // Check West neighbor (if not first column)
        if (c > 0) {
            int westType = mask % 3;
            gain += H[type][westType];
        }
        // Check North neighbor (if not first row)
        if (r > 0) {
            int northType = mask / p3[n - 1];
            gain += H[type][northType];
        }
        return gain;
    }
}