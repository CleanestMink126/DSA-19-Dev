import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntCircleTest {

    @Test
    public void testCircle1() {
        int[] arr = new int[]{1,2,3,4,5,6};
        assertEquals(126, IntCircle.maxValue(arr));
    }

    @Test
    public void testCircle2() {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        assertEquals(4680, IntCircle.maxValue(arr));
    }

    @Test
    public void testCircle3() {
        int[] arr = new int[]{2,4,6,8,9,4,400,2,4,24,67,2,3,34,100};

        assertEquals(2680860, IntCircle.maxValue(arr));
    }

    @Test
    public void testCircle4() {
        int[] arr  = new int[]{2,4,6,8,9,6,4,2,4,24,67,2,3,34,100};

        assertEquals(161548, IntCircle.maxValue(arr));
    }

    @Test
    public void testCircle5() {
        //Verified by brute force
        int[] arr = new int[]{342, 136, 255, 461, 154, 243};

        assertEquals(29405886, IntCircle.maxValue(arr));
    }

    @Test
    public void testCircle6() {
        //Verified by brute force
        int[] arr = new int[]{98, 293, 353,  92, 474, 471, 293, 243, 403,  75, 179,  36, 359, 220, 270};

        assertEquals(124925525, IntCircle.maxValue(arr));
    }

    @Test
    public void testCircle7() {
        //Verified by brute force
        int[] arr =  new int[]{475, 388, 387,  67, 343, 225, 241, 375, 302, 190,  84, 322, 226, 238, 187, 153,  86, 289};

        assertEquals(135643782, IntCircle.maxValue(arr));
    }

    @Test
    public void testCircle8() {
        //Verified by brute force
        int[] arr = new int[]{453, 107, 109, 161, 246, 344, 404,  25, 257,  85, 339,  87, 290, 486, 307, 466,  88, 465,
            332, 348, 489};

        assertEquals(243039864, IntCircle.maxValue(arr));
    }



}
