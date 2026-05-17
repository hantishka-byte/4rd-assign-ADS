public class Experiment {

    // Run BFS and DFS on graph
    public void runTraversals(Graph g) {

        System.out.println("\nBFS Traversal:");

        long startBFS = System.nanoTime();

        g.bfs(0);

        long endBFS = System.nanoTime();

        System.out.println("BFS Execution Time: "
                + (endBFS - startBFS) + " ns");


        System.out.println("\nDFS Traversal:");

        long startDFS = System.nanoTime();

        g.dfs(0);

        long endDFS = System.nanoTime();

        System.out.println("DFS Execution Time: "
                + (endDFS - startDFS) + " ns");
    }


    // Create graphs with different sizes
    public void runMultipleTests() {

        int[] sizes = {10, 30, 100};

        for (int size : sizes) {

            System.out.println("\n============================");
            System.out.println("GRAPH SIZE: " + size);
            System.out.println("============================");

            Graph graph = new Graph();

            // Add vertices
            for (int i = 0; i < size; i++) {
                graph.addVertex(new Vertex(i));
            }

            // Add edges
            for (int i = 0; i < size - 1; i++) {

                graph.addEdge(i, i + 1);

                // Additional edges for complexity
                if (i + 2 < size) {
                    graph.addEdge(i, i + 2);
                }
            }

            // Print small graph only
            if (size == 10) {

                System.out.println("\nGraph Structure:");
                graph.printGraph();
            }

            runTraversals(graph);
        }
    }

    public void printResults() {

        System.out.println("\nAll experiments completed successfully.");
    }
}