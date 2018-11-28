import java.util.*;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {

        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> cameFrom = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(v);
        visited.add(v);

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int neigh : g.getNeighbors(current)) {
                if (!visited.contains(neigh)) {
                    cameFrom.put(neigh, current);
                    visited.add(neigh);
                    q.offer(neigh);
                }
            }
        }

        if (!visited.contains(w))
            return null;

        LinkedList<Integer> path = new LinkedList<>();
        int curr = w;
        path.add(w);
        while (curr != v) {
            curr = cameFrom.get(curr);
            path.addFirst(curr);
        }
        return path;

    }

    public static int distanceBetween(Graph g, int v, int w) {
        List<Integer> path = shortestPath(g, v, w);
        if (path == null)
            return -1;
        return path.size() - 1;
    }

}