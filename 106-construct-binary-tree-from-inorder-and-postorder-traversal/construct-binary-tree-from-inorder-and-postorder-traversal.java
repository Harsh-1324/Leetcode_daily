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

import java.util.*;

class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    int postIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        // Store inorder values and their indices
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        postIndex = postorder.length - 1;

        return construct(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode construct(int[] inorder, int[] postorder,
                               int left, int right) {

        if (left > right) {
            return null;
        }

        // Last postorder element is the root
        int rootVal = postorder[postIndex--];

        TreeNode root = new TreeNode(rootVal);

        // Find root position in inorder
        int mid = map.get(rootVal);

        // Build right subtree first
        root.right = construct(inorder, postorder, mid + 1, right);

        // Build left subtree
        root.left = construct(inorder, postorder, left, mid - 1);

        return root;
    }
}
