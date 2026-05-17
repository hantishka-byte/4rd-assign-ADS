public class Experiment {


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


    public void runMultipleTests() {

        int[] sizes = {10, 30, 100};

        for (int size : sizes) {

            System.out.println("\n============================");
            System.out.println("GRAPH SIZE: " + size);
            System.out.println("============================");

            Graph graph = new Graph();


            for (int i = 0; i < size; i++) {
                graph.addVertex(new Vertex(i));
            }


            for (int i = 0; i < size - 1; i++) {

                graph.addEdge(i, i + 1);


                if (i + 2 < size) {
                    graph.addEdge(i, i + 2);
                }
            }


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