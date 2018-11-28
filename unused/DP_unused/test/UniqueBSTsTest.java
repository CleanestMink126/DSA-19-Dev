import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniqueBSTsTest {

    @Test
    public void testBST1() {
        assertEquals(UniqueBSTs.uniqueBSTs(1), 1);
    }

    @Test
    public void testBST2() {
        assertEquals(UniqueBSTs.uniqueBSTs(2), 2);
    }

    @Test
    public void testBST3() {
        assertEquals(UniqueBSTs.uniqueBSTs(3), 5);
    }

    @Test
    public void testBST4() {
        assertEquals(UniqueBSTs.uniqueBSTs(4), 14);
    }

    @Test
    public void testBST5() {
        assertEquals(UniqueBSTs.uniqueBSTs(5), 42);
    }

    @Test
    public void testBST6() {
        assertEquals(UniqueBSTs.uniqueBSTs(10), 16796);
    }
}
