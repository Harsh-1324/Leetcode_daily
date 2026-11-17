
import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> result = new ArrayList<>();
        int n = words.length;
        int i = 0;

        while (i < n) {

            int j = i;
            int letters = 0;

            // Greedily find words that fit in this line
            while (j < n &&
                   letters + words[j].length() + (j - i) <= maxWidth) {

                letters += words[j].length();
                j++;
            }

            int count = j - i;
            int spaces = maxWidth - letters;

            StringBuilder line = new StringBuilder();

            // Last line or single word
            if (j == n || count == 1) {

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        line.append(" ");
                    }
                }

                // Add remaining spaces at the end
                while (line.length() < maxWidth) {
                    line.append(" ");
                }

            } else {

                int gaps = count - 1;
                int evenSpaces = spaces / gaps;
                int extraSpaces = spaces % gaps;

                for (int k = i; k < j; k++) {

                    line.append(words[k]);

                    if (k < j - 1) {

                        int currentSpaces = evenSpaces;

                        // Extra spaces go to left gaps
                        if (k - i < extraSpaces) {
                            currentSpaces++;
                        }

                        for (int s = 0; s < currentSpaces; s++) {
                            line.append(" ");
                        }
                    }
                }
            }

            result.add(line.toString());

            // Move to the next line
            i = j;
        }

        return result;
    }
}
