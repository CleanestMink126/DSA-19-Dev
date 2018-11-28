
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

    private UndirectedGraph g;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        g = new UndirectedGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
    }

    /**
     * Test method for {@link UndirectedGraph#hasEdgeBetween(int, int)}.
     */
    @Test
    public void testEdges() {

        assertEquals(g.hasEdgeBetween(0, 1), true);
        assertEquals(g.hasEdgeBetween(2, 0), true);
        assertEquals(g.hasEdgeBetween(1, 2), true);
        assertEquals(g.hasEdgeBetween(3, 1), true);
        assertEquals(g.hasEdgeBetween(2, 4), true);
        assertEquals(g.hasEdgeBetween(4, 3), true);

        assertEquals(g.hasEdgeBetween(0, 3), false);
        assertEquals(g.hasEdgeBetween(2, 3), false);
        assertEquals(g.hasEdgeBetween(4, 1), false);

    }


    /**
     * Test method for {@link UndirectedGraph#vertices()}.
     */
    @Test
    public void testVertices() {
        int counter = 0;
        for (int v : g.vertices()) {
            assertEquals(v, counter++);
        }
    }


    /**
     * Test method for {@link UndirectedGraph#numVertices()}.
     */
    @Test
    public void testNumVertices() {
        assertEquals(g.numVertices(), 5);
    }

    /**
     * Test method for {@link UndirectedGraph#numEdges()}.
     */
    @Test
    public void testNumEdges() {
        assertEquals(g.numEdges(), 6);
    }

    /**
     * Test method for {@link UndirectedGraph#getNeighbors(int)}.
     */
    @Test
    public void testGetNeighbors() {
        Iterable<Integer> neighbors0 = g.getNeighbors(0);
        assertEquals(iterableContains(neighbors0, 1), true);
        assertEquals(iterableContains(neighbors0, 2), true);
        assertEquals(iterableContains(neighbors0, 3), false);


        Iterable<Integer> neighbors1 = g.getNeighbors(1);
        assertEquals(iterableContains(neighbors1, 0), true);
        assertEquals(iterableContains(neighbors1, 3), true);
        assertEquals(iterableContains(neighbors1, 2), true);
        assertEquals(iterableContains(neighbors1, 1), false);
        assertEquals(iterableContains(neighbors1, 4), false);
    }

    private boolean iterableContains(Iterable iterable, Object item) {
        for (Object o : iterable) {
            if (o.equals(item))
                return true;
        }
        return false;
    }
}
