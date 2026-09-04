class Solution {
    public int vowelConsonantScore(String s) {
        int v = 0, c = 0;

        for (char ch : s.toCharArray()) {
            if ("aeiou".indexOf(ch) != -1)
                v++;
            else if (ch >= 'a' && ch <= 'z')
                c++;
        }

        return c == 0 ? 0 : v / c;
    }
}