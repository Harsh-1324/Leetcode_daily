import java.util.*;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int pushes = 0;
        int position = 0;

        // Traverse frequencies from largest to smallest
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;

            pushes += freq[i] * (position / 8 + 1);
            position++;
        }

        return pushes;
    }
}