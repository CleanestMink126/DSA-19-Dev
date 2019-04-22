import java.util.Arrays;
import java.util.HashMap;

public class BalloonCircle{
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
            System.out.println(Arrays.toString(DP[i]));
        }


        return max;
    }


    public static void main(String[] args){
//        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
//        System.out.println(maxValue(arr));
//        arr = new int[]{1,2,3,4,5,6};
//        System.out.println(maxValue(arr));
//        arr = new int[]{2,4,6,8,9,4,400,2,4,24,67,2,3,34,100};
//        System.out.println(maxValue(arr));
//        arr = new int[]{2,4,6,8,9,6,4,2,4,24,67,2,3,34,100};
//        System.out.println(maxValue(arr));
        int[] arr = new int[]{342, 136, 255, 461, 154, 243};
        System.out.println(maxValue(arr));
        System.out.println(29405886);
        arr = new int[]{98, 293, 353,  92, 474, 471, 293, 243, 403,  75, 179,  36, 359, 220, 270};
        System.out.println(maxValue(arr));
        System.out.println(124925525);


        arr = new int[]{98, 293, 353,  92, 474, 471, 293, 243, 403,  75, 179,  36, 359, 220, 270};
        System.out.println(maxValue(arr));
        System.out.println(124925525);


        arr = new int[]{475, 388, 387,  67, 343, 225, 241, 375, 302, 190,  84, 322, 226, 238, 187, 153,  86, 289};
        System.out.println(maxValue(arr));
        System.out.println(135643782);

        arr = new int[]{453, 107, 109, 161, 246, 344, 404,  25, 257,  85, 339,  87, 290, 486, 307, 466,  88, 465,
                332, 348, 489};
        System.out.println(maxValue(arr));
        System.out.println(243039864);






    }

}