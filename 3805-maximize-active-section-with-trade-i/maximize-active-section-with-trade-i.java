class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        String t = "1" + s + "1";
        int n = t.length();

        ArrayList<Character> type = new ArrayList<>();
        ArrayList<Integer> len = new ArrayList<>();

        int i = 0;
        while (i < n) {
            char ch = t.charAt(i);
            int j = i;
            while (j < n && t.charAt(j) == ch) j++;

            type.add(ch);
            len.add(j - i);

            i = j;
        }

        int best = 0;

        // pattern: 0-run, 1-run, 0-run
        for (int k = 1; k + 1 < type.size(); k++) {
            if (type.get(k) == '1'
                    && type.get(k - 1) == '0'
                    && type.get(k + 1) == '0') {

                best = Math.max(best,
                        len.get(k - 1) + len.get(k + 1));
            }
        }

        return ones + best;
    }
}