/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int left, int right) {

        // Base case
        if (left > right) {
            return null;
        }

        // Find middle element
        int mid = left + (right - left) / 2;

        // Create root node
        TreeNode root = new TreeNode(nums[mid]);

        // Build left subtree
        root.left = construct(nums, left, mid - 1);

        // Build right subtree
        root.right = construct(nums, mid + 1, right);

        return root;
    }
}
