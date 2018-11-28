import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    private static double cost(String[] words, int lo, int hi, int m) {
        if (hi <= lo)
            throw new IllegalArgumentException("Hi must be higher than Lo");
        int length = hi-lo-1; // account for spaces;
        for (int i = lo; i < hi; i++) {
            length += words[i].length();
        }
        if (length > m)
            return Double.POSITIVE_INFINITY;
        return Math.pow(m-length, 3);
    }

    public static List<Integer> justifyText(String[] w, int m) {

        double[] DP = new double[w.length + 1];
        DP[w.length] = 0;
        int[] lineBreaks = new int[w.length];
        lineBreaks[w.length-1] = w.length;

        for (int i = w.length-1; i >= 0; i--) {
            int endLine = i+1;
            double lowest_cost = Double.POSITIVE_INFINITY;
            for (int j = i+1; j <= w.length; j++) {
                double c = cost(w, i, j, m) + DP[j];
                if (c < lowest_cost) {
                    lowest_cost = c;
                    endLine = j;
                }
            }
            DP[i] = lowest_cost;
            lineBreaks[i] = endLine;
        }

        int index = 0;
        List<Integer> breaks = new LinkedList<>();
        while (index < w.length) {
            breaks.add(index);
            index = lineBreaks[index];
        }

        return breaks;

    }

}