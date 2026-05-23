import java.util.*;

public class Graph {


    private Map<Integer, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }


    public void addVertex(Vertex v) {
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }


    public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }

    public void addEdge(int from, int to, int weight) {

        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            System.out.println("Both vertices must exist before adding an edge.");
            return;
        }

        Vertex fromVertex = new Vertex(from);
        Vertex toVertex = new Vertex(to);

        adjacencyList.get(from).add(new Edge(fromVertex, toVertex, weight));


        adjacencyList.get(to).add(new Edge(toVertex, fromVertex, weight));
    }


    public void printGraph() {

        for (int vertex : adjacencyList.keySet()) {

            System.out.print(vertex + " -> ");

            for (Edge edge : adjacencyList.get(vertex)) {
                System.out.print(edge.getDestination().getId()
                        + "(" + edge.getWeight() + ") ");
            }

            System.out.println();
        }
    }


    public void bfs(int start) {

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {

            int current = queue.poll();

            System.out.print(current + " ");

            for (Edge edge : adjacencyList.get(current)) {

                int neighbor = edge.getDestination().getId();

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }


    public void dfs(int start) {

        Set<Integer> visited = new HashSet<>();

        dfsRecursive(start, visited);

        System.out.println();
    }


    private void dfsRecursive(int current, Set<Integer> visited) {

        visited.add(current);

        System.out.print(current + " ");

        for (Edge edge : adjacencyList.get(current)) {

            int neighbor = edge.getDestination().getId();

            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }


    public void dijkstra(int start) {

        if (!adjacencyList.containsKey(start)) {
            System.out.println("Start vertex does not exist.");
            return;
        }

        int maxVertexId = Collections.max(adjacencyList.keySet());
        int[] distances = new int[maxVertexId + 1];
        boolean[] visited = new boolean[maxVertexId + 1];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        for (int i = 0; i < adjacencyList.size(); i++) {

            int current = findMinimumDistanceVertex(distances, visited);

            if (current == -1) {
                break;
            }

            visited[current] = true;

            for (Edge edge : adjacencyList.get(current)) {

                int neighbor = edge.getDestination().getId();
                int weight = edge.getWeight();

                if (!visited[neighbor]
                        && distances[current] != Integer.MAX_VALUE
                        && distances[current] + weight < distances[neighbor]) {

                    distances[neighbor] = distances[current] + weight;
                }
            }
        }

        System.out.println("\nDijkstra Shortest Paths from vertex " + start + ":");

        for (int vertex : adjacencyList.keySet()) {

            System.out.print("To vertex " + vertex + ": ");

            if (distances[vertex] == Integer.MAX_VALUE) {
                System.out.println("unreachable");
            } else {
                System.out.println(distances[vertex]);
            }
        }
    }


    private int findMinimumDistanceVertex(int[] distances, boolean[] visited) {

        int minimumDistance = Integer.MAX_VALUE;
        int minimumVertex = -1;

        for (int vertex : adjacencyList.keySet()) {

            if (!visited[vertex] && distances[vertex] < minimumDistance) {
                minimumDistance = distances[vertex];
                minimumVertex = vertex;
            }
        }

        return minimumVertex;
    }
}
