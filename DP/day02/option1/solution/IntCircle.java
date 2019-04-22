import java.util.Arrays;
import java.util.HashMap;

public class IntCircle{
    public static int maxValue(int[] circle) {
        int[][] DP = new int[circle.length+1][circle.length+1];
        for(int length = 3; length <= circle.length;length+=3){
            for(int i = 0; i < circle.length; i++){
                int j = (i + length); //non inclusive
                int max = -Integer.MAX_VALUE;
                for(int k = 1; k < length; k+=3){
                    int value = 0;
                    value += circle[i]*circle[(j-1)%circle.length]*circle[(i+k)%circle.length];
                    if(k != 1){
                        value += DP[(i+1)%circle.length][k-1];
                    }
                    if(k != length-2){
                        value += DP[(i+k+1)%circle.length][length-k-2];
                    }
                    if(value > max){
                        max = value;
                    }
                }

                int value = 0;
                value += circle[i]*circle[(i+2)%circle.length]*circle[(i+1)%circle.length];
                if(length > 3){
                    value += DP[(i+3)%circle.length][length-3];
                }
                if(value > max) max = value;

                value = 0;
                value += circle[(j-1)%circle.length]*circle[(j-2)%circle.length]*circle[(j-3)%circle.length];
                if(length > 3){
                    value += DP[(i)%circle.length][length-3];
                }
                if(value > max) max = value;


                DP[i][length] = max;
            }
        }
        int max = -Integer.MAX_VALUE;
        for(int i =0; i < circle.length;i++){
            if(DP[i][circle.length] > max){
                max = DP[i][circle.length];
            }
//            System.out.println(Arrays.toString(DP[i]));
        }


        return max;
    }

}