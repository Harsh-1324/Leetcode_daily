
class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] freq = new int[101];
        int maxFreq = 0;
        int total = 0;

        for (int num : nums) {
            freq[num]++;

            if (freq[num] > maxFreq) {
                maxFreq = freq[num];
                total = maxFreq;
            } else if (freq[num] == maxFreq) {
                total += maxFreq;
            }
        }

        return total;
    }
}
