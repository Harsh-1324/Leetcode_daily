import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    public String longestWord(String[] words) {
        // Sort words lexicographically
        Arrays.sort(words);
        
        HashSet<String> built = new HashSet<>();
        String result = "";
        
        for (String w : words) {
            // A word is valid if it's a single character or its prefix (length - 1) is already built
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                built.add(w);
                
                // Update result only if the current word is strictly longer
                if (w.length() > result.length()) {
                    result = w;
                }
            }
        }
        
        return result;
    }
}