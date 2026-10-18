import java.util.*;

class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> answer = new ArrayList<>();

        for (int i = 0; i < l.length; i++) {
            int[] arr = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            Arrays.sort(arr);

            int diff = arr[1] - arr[0];
            boolean isArithmetic = true;

            for (int j = 2; j < arr.length; j++) {
                if (arr[j] - arr[j - 1] != diff) {
                    isArithmetic = false;
                    break;
                }
            }

            answer.add(isArithmetic);
        }

        return answer;
    }
}