import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        Stack<Integer> stack = new Stack<>(); // Stores indices
        
        // Traverse twice to handle the circular array property
        for (int i = 0; i < 2 * n; i++) {
            int currIndex = i % n;
            
            // While stack is not empty and the current element is greater 
            // than the element corresponding to the index at the top of the stack
            while (!stack.isEmpty() && nums[stack.peek()] < nums[currIndex]) {
                result[stack.pop()] = nums[currIndex];
            }
            
            // Push index to stack only during the first pass
            if (i < n) {
                stack.push(currIndex);
            }
        }
        
        return result;
    }
}