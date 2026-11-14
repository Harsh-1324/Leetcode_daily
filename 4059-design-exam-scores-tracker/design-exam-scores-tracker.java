
import java.util.*;

class ExamTracker {

    private List<Integer> times;
    private List<Long> prefix;

    public ExamTracker() {
        times = new ArrayList<>();
        prefix = new ArrayList<>();

        prefix.add(0L);
    }

    public void record(int time, int score) {
        times.add(time);

        long sum = prefix.get(prefix.size() - 1) + score;
        prefix.add(sum);
    }

    public long totalScore(int startTime, int endTime) {
        int left = lowerBound(startTime);
        int right = upperBound(endTime);

        return prefix.get(right) - prefix.get(left);
    }

    // First index where time >= target
    private int lowerBound(int target) {
        int left = 0;
        int right = times.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (times.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // First index where time > target
    private int upperBound(int target) {
        int left = 0;
        int right = times.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (times.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}


/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */