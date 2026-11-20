
class Solution {
    public int sumDecoded(long[] nums) {
        long MOD = 1_000_000_007L;
        long ans = 0;

        for (long num : nums) {
            int width = (int)(num % 10);
            long d = num / 10;

            int digits = String.valueOf(d).length();
            int yDigits = digits - width;

            long divisor = 1;

            for (int i = 0; i < yDigits; i++) {
                divisor *= 10;
            }

            long x = d / divisor;
            long y = d % divisor;

            ans = (ans + power(x, y, MOD)) % MOD;
        }

        return (int) ans;
    }

    private long power(long x, long y, long mod) {
        long result = 1;

        x %= mod;

        while (y > 0) {
            if ((y & 1) == 1) {
                result = (result * x) % mod;
            }

            x = (x * x) % mod;
            y >>= 1;
        }

        return result;
    }
}
