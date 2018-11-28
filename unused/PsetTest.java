package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ADTs.StackADT;
import your_code.MyStack;
import your_code.PsetProblems;


public class PsetTest {

    @Test
    public void testLongestValidSubstring() {
        assertEquals(PsetProblems.longestValidSubstring("(()))("), 4);
        assertEquals(PsetProblems.longestValidSubstring("(()()))()"), 6);
        assertEquals(PsetProblems.longestValidSubstring("))(("), 0);
    }

    @Test
    public void testSortStackLimitedMemory() {
        StackADT<Integer> s = new MyStack();
        s.push(4);
        s.push(2);
        s.push(7);
        s.push(1);
        s.push(6);
        s.push(19);
        s.push(2);

        StackADT<Integer> r = PsetProblems.sortStackLimitedMemory(s);

        assertEquals((int) r.pop(), 1);
        assertEquals((int) r.pop(), 2);
        assertEquals((int) r.pop(), 2);
        assertEquals((int) r.pop(), 4);
        assertEquals((int) r.pop(), 6);
        assertEquals((int) r.pop(), 7);
        assertEquals((int) r.pop(), 19);
    }

}
