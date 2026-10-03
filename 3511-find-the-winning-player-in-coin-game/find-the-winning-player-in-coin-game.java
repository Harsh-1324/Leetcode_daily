class Solution {
    public String winningPlayer(int x, int y) {
        int turns = Math.min(x, y / 4);

        if (turns % 2 == 1) {
            return "Alice";
        } else {
            return "Bob";
        }
    }
}