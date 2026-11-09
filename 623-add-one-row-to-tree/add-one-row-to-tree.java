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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {

        // Special case: Insert new root
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int level = 1;

        // Reach the level just before the required depth
        while (level < depth - 1) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                if (curr.left != null) {
                    q.offer(curr.left);
                }

                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }

            level++;
        }

        // Insert new nodes
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            TreeNode newLeft = new TreeNode(val);
            TreeNode newRight = new TreeNode(val);

            // Preserve original subtrees
            newLeft.left = curr.left;
            newRight.right = curr.right;

            curr.left = newLeft;
            curr.right = newRight;
        }

        return root;
    }
}
