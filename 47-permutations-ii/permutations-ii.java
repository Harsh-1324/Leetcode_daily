import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the array to group duplicate numbers together
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }
    
    private void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        // If the current permutation is of the same length as nums, add it to results
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            // If the element is already used, skip it
            if (used[i]) continue;
            
            // To handle duplicates: if the current element is equal to the previous element,
            // and the previous element was NOT used in this branch, skip it to avoid duplicates.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            
            // Choose
            used[i] = true;
            current.add(nums[i]);
            
            // Explore
            backtrack(nums, current, used, result);
            
            // Un-choose (Backtrack)
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}