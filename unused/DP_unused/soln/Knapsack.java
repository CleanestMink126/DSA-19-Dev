public class Knapsack {

    public static int maxValue(int knapsackSize, int[] S, int[] V) {
        assert S.length == V.length;
        int N = S.length;
        int[][] DP = new int[N+1][knapsackSize+1];
        for (int i = N-1; i >= 0; i--) {
            for (int j = 1; j <= knapsackSize; j++) {
                DP[i][j] = DP[i+1][j];
                if (S[i] <= j)
                    DP[i][j] = Math.max(DP[i][j], DP[i+1][j-S[i]] + V[i]);
            }
        }
        return DP[0][knapsackSize];
    }

}
