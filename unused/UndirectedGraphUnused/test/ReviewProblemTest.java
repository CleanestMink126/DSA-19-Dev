
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReviewProblemTest {
    private int[] A1, A2, A3;


    /**
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        A1 = new int[]{2,3,1,2,4,3};
        A2 = new int[]{4, 5, 4, 9, 8, 2, 0, 1, 2, 4, 5, 4, 8, 1, 0, 8, 3, 1, 5, 1, 3, 6, 2, 3, 0, 4, 0, 7, 3, 9};
    }

    /**
     * Test method for {@link ReviewProblem#minimumLengthSubArray(int[], int)}.
     */
    @Test
    public void testMinimumLengthSubArray() {
        assertEquals(ReviewProblem.minimumLengthSubArray(A1, 4), 1);
        assertEquals(ReviewProblem.minimumLengthSubArray(A1, 7), 2);
        assertEquals(ReviewProblem.minimumLengthSubArray(A1, 8), 3);
        assertEquals(ReviewProblem.minimumLengthSubArray(A2, 1), 1);
        assertEquals(ReviewProblem.minimumLengthSubArray(A2, 2), 1);
        assertEquals(ReviewProblem.minimumLengthSubArray(A2, 9), 1);
        assertEquals(ReviewProblem.minimumLengthSubArray(A2, 12), 2);
        assertEquals(ReviewProblem.minimumLengthSubArray(A2, 17), 2);
        assertEquals(ReviewProblem.minimumLengthSubArray(A2, 28), 5);
        assertEquals(ReviewProblem.minimumLengthSubArray(A2, 49), 12);
        assertEquals(ReviewProblem.minimumLengthSubArray(A2, 87), 24);
    }
}
