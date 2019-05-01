import java.util.Arrays;
import java.util.HashMap;

//Solution Nick Steelman May 2018 for DSA

public class IntCircle{
    public static int maxValue(int[] circle) {
        //Our DP array axis is start index by legnth run that signify the maximum
        //value from poping ballons in that range
        //This is a bottom up implementation
        int[][] DP = new int[circle.length+1][circle.length+1];
        //We start with the smallest array size of 3 and work our way up to the
        //largest aka circle size
        for(int length = 3; length <= circle.length;length+=3){
            //We find the ideal starting at each index
            for(int i = 0; i < circle.length; i++){
                int j = (i + length); //non inclusive
                int max = -Integer.MAX_VALUE;
                //Guess or Recurrence Relation
                //Given this range,what the last thing we pop?
                //this must include the first 3, last 3, or both end with one in the middle
                //Since we essentially run through every start and end with i and j
                //we hit evey case aka check every combination of 3 eventually
                //But with this we account for the pops that need to happen to
                //ge those three together
                for(int k = 1; k < length; k+=3){
                    int value = 0;
                    value += circle[i]*circle[(j-1)%circle.length]*circle[(i+k)%circle.length];
                    if(k != 1){//add max at right side of k
                        value += DP[(i+1)%circle.length][k-1];
                    }
                    if(k != length-2){//add the max at the left side of k
                        value += DP[(i+k+1)%circle.length][length-k-2];
                    }
                    if(value > max){
                        max = value;
                    }
                }
                int value = 0;//Check if we pop the first 3 in range
                value += circle[i]*circle[(i+2)%circle.length]*circle[(i+1)%circle.length];
                if(length > 3){
                    value += DP[(i+3)%circle.length][length-3];
                }
                if(value > max) max = value;

                value = 0;//Check if we pop the last 3 in rnage
                value += circle[(j-1)%circle.length]*circle[(j-2)%circle.length]*circle[(j-3)%circle.length];
                if(length > 3){
                    value += DP[(i)%circle.length][length-3];
                }
                if(value > max) max = value;


                DP[i][length] = max;
            }
        }
        //Loop through our array to find the max value
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
