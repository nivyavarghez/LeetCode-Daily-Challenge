import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class primeSub {
    public boolean primeSubOperation(int[] nums) {
        final int kMax = 1000;
        final List<Integer> primes = sieveEratosthenes(kMax);
        int prevNum = 0;
        for (int num : nums) {
            int adjustedNum = num;
            final int i = firstGreaterEqual(primes, adjustedNum - prevNum);
            if (i > 0) {
                adjustedNum -= primes.get(i - 1);
            }
            if (adjustedNum <= prevNum) {
                return false;
            }
            prevNum = adjustedNum;
        }
        return true;
    }

    private List<Integer> sieveEratosthenes(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    private int firstGreaterEqual(List<Integer> A, int target) {
        final int i = Collections.binarySearch(A, target);
        return i < 0 ? -i - 1 : i;
    }

    public static void main(String[] args) {
        primeSub solution = new primeSub();
        int[] nums = {4, 9, 6, 10};
        boolean result = solution.primeSubOperation(nums);
        System.out.println("Output: " + result);
    }
}
