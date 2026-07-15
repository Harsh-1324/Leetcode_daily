class Solution {
public:
    vector<vector<int>> ans;
    vector<int> curr;

    void solve(vector<int>& candidates, int target, int index) {
        if (target == 0) {
            ans.push_back(curr);
            return;
        }

        for (int i = index; i < candidates.size(); i++) {
            if (i > index && candidates[i] == candidates[i - 1])
                continue; // skip duplicates

            if (candidates[i] > target)
                break;

            curr.push_back(candidates[i]);
            solve(candidates, target - candidates[i], i + 1);
            curr.pop_back();
        }
    }

    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        solve(candidates, target, 0);
        return ans;
    }
};