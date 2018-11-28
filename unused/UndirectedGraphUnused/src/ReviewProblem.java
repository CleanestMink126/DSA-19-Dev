public class ReviewProblem {

    public static int minimumLengthSubArray(int[] A, int desiredSum) {
        if (A == null || A.length == 0)
            return 0;
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        while (j < A.length) {
            sum += A[j++];
            while (sum >= desiredSum) {
                min = Math.min(min, j - i);
                sum -= A[i++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

}
