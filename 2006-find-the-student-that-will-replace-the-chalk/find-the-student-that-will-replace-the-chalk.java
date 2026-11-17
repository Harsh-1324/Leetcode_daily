
class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        long total = 0;

        // Calculate total chalk required
        for (int c : chalk) {
            total += c;
        }

        // Skip complete rounds
        k %= total;

        // Find the student who cannot solve
        for (int i = 0; i < chalk.length; i++) {
            if (k < chalk[i]) {
                return i;
            }
            k -= chalk[i];
        }

        return 0;
    }
}
