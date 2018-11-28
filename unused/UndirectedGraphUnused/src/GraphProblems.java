import java.util.*;

public class GraphProblems {

    /**
     * Use DFS to mark all vertices connected to v.
     *
     * @param marked  marked[v] is whether v has been marked.
     */
    private static void dfsConnected(Graph g, int v, Set<Integer> marked) {
        marked.add(v);
        for (int n : g.getNeighbors(v))
            if (!marked.contains(n))
                dfsConnected(g, n, marked);
    }

    public static boolean connected(Graph g, int v, int u) {
        // Use a set to keep track of which vertices have been visited
        Set<Integer> marked = new HashSet<>();
        dfsConnected(g, v, marked);
        return marked.contains(u);
    }

    public static List<Integer> topologicalOrder(Digraph g) {
        LinkedList<Integer> topoOrder = new LinkedList<>();
        Set<Integer> marked = new HashSet<>();
        for (int v : g.vertices())
            if (!marked.contains(v))
                dfsTopo(g, v, topoOrder, marked);
        return topoOrder;
    }

    private static void dfsTopo(Digraph g, int v, LinkedList<Integer> topoOrder, Set<Integer> marked) {
        marked.add(v);
        for (int n : g.getNeighbors(v))
            if (!marked.contains(n))
                dfsTopo(g, n, topoOrder, marked);
        // Add the current vertex to the head of a LinkedList
        topoOrder.addFirst(v);
    }

    public static boolean hasCycle(UndirectedGraph g) {
        Set<Integer> marked = new HashSet<>();
        // Check if there's a cycle starting from each vertex using DFS
        for (int v : g.vertices()) {
            if (!marked.contains(v)) {
                if (dfsCycle(g, v, -1, marked)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param cameFrom the previous vertex visited.
     */
    private static boolean dfsCycle(Graph g, int v, int cameFrom, Set<Integer> marked) {
        marked.add(v);
        for (int n : g.getNeighbors(v)) {
            if (!marked.contains(n)) {
                if (dfsCycle(g, n, v, marked)) {
                    return true;
                }
            }
            // if we reach a marked vertex and it's not the one we came from, we've found
            // a cycle.
            else if (n != cameFrom) {
                return true;
            }
        }
        return false;
    }

}
