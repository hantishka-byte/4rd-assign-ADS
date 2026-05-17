# Assignment 4 - Graph Traversal and Representation System

## A. Project Overview

This project implements a graph traversal system in Java. The graph is stored using an adjacency list, which is an efficient way to represent vertices and the edges connected to each vertex.

A graph is made of two main parts:

- **Vertices**: the nodes of the graph. In this project, each vertex has an integer ID such as `0`, `1`, `2`, and so on.
- **Edges**: the connections between vertices. The implemented graph is undirected, so an edge from vertex `0` to vertex `1` also means vertex `1` is connected back to vertex `0`.

The experiment creates graphs with `10`, `30`, and `100` vertices. Each vertex is connected to the next vertex, and extra edges are added between vertices that are two positions apart. This creates a connected graph that can be used to test traversal algorithms.

Two graph traversal algorithms are implemented:

- **Breadth-First Search (BFS)** explores the graph level by level using a queue.
- **Depth-First Search (DFS)** explores as deeply as possible before backtracking, using recursion.

## B. Class Descriptions

### Vertex Class

The `Vertex` class represents a single node in the graph.

Main responsibility:

- Stores a unique integer ID for each vertex.

Important method:

- `getId()` returns the vertex ID.

### Edge Class

The `Edge` class represents a connection between two vertices.

Main responsibility:

- Stores a source vertex and a destination vertex.

Important methods:

- `getSource()` returns the starting vertex.
- `getDestination()` returns the ending vertex.

### Graph Class

The `Graph` class contains the main graph structure and traversal algorithms.

Main responsibilities:

- Stores the graph using an adjacency list.
- Adds vertices to the graph.
- Adds undirected edges between vertices.
- Prints the graph structure.
- Performs BFS traversal.
- Performs DFS traversal.

### Adjacency List Representation

The graph is represented with:

```java
Map<Integer, List<Integer>> adjacencyList
```

Each key in the map is a vertex ID, and the list contains all neighboring vertices connected to that vertex.

Example:

```text
0 -> 1 2
1 -> 0 2 3
2 -> 0 1 3 4
```

This means vertex `0` is connected to vertices `1` and `2`, vertex `1` is connected to vertices `0`, `2`, and `3`, and so on.

The adjacency list representation is useful because it stores only existing edges, making it space efficient for many graphs.

Space complexity:

```text
O(V + E)
```

Where:

- `V` is the number of vertices.
- `E` is the number of edges.

## C. Algorithm Descriptions

### Breadth-First Search (BFS)

BFS visits vertices level by level, starting from a selected vertex. In this project, BFS starts from vertex `0`.

Step-by-step explanation:

1. Create a set to store visited vertices.
2. Create a queue to control the traversal order.
3. Mark the starting vertex as visited.
4. Add the starting vertex to the queue.
5. Remove the first vertex from the queue.
6. Print the current vertex.
7. Check each neighbor of the current vertex.
8. If a neighbor has not been visited, mark it as visited and add it to the queue.
9. Repeat until the queue is empty.

Use cases:

- Finding the shortest path in an unweighted graph.
- Level-order traversal.
- Network broadcasting.
- Checking graph connectivity.

Time complexity:

```
O(V + E)
```

BFS visits every vertex once and checks every edge connected to those vertices.

### Depth-First Search (DFS)

DFS visits one path as deeply as possible before backtracking. In this project, DFS starts from vertex `0` and uses a recursive helper method.

Step-by-step explanation:

1. Create a set to store visited vertices.
2. Start at the selected vertex.
3. Mark the current vertex as visited.
4. Print the current vertex.
5. Check each neighbor of the current vertex.
6. If a neighbor has not been visited, recursively call DFS on that neighbor.
7. When there are no unvisited neighbors, backtrack to the previous vertex.
8. Continue until all reachable vertices have been visited.

Use cases:

- Detecting cycles.
- Solving maze and pathfinding problems.
- Topological sorting in directed acyclic graphs.
- Exploring connected components.

Time complexity:

```
O(V + E)
```

DFS visits every vertex once and checks each edge during traversal.

## D. Experimental Results

The program tests BFS and DFS on graphs with `10`, `30`, and `100` vertices. Execution time is measured with `System.nanoTime()`.

Execution time comparison:

| Graph Size | BFS Time (ns) | DFS Time (ns) |
|---:|---:|---:|
| 10 vertices | 3,764,600 | 251,200 |
| 30 vertices | 638,700 | 603,900 |
| 100 vertices | 1,504,400 | 1,655,600 |

### Observations and Patterns

The traversal order for this graph is mostly sequential because the graph connects each vertex to the next one and also to the vertex two positions ahead. Starting from vertex `0`, both BFS and DFS visit all vertices from `0` to the final vertex.

The execution times generally increase as the graph becomes larger, especially from `30` to `100` vertices. The `10` vertex BFS time is higher than expected because very small timing measurements can be affected by JVM startup, warm-up, printing to the console, and system overhead. Overall, both BFS and DFS follow the expected `O(V + E)` time complexity.

## E. Screenshots

Screenshots should be stored in:

```text
docs/screenshots/
```

Required screenshots:

### Graph Structure Output

Shows the adjacency list printed for the graph with `10` vertices.

```text
0 -> 1 2
1 -> 0 2 3
2 -> 0 1 3 4
3 -> 1 2 4 5
4 -> 2 3 5 6
5 -> 3 4 6 7
6 -> 4 5 7 8
7 -> 5 6 8 9
8 -> 6 7 9
9 -> 7 8
```

Suggested file:

```text
docs/screenshots/1
```

### BFS Traversal Output

Shows BFS traversal starting from vertex `0`.

```text
BFS Traversal:
0 1 2 3 4 5 6 7 8 9
```

Suggested file:

```text
docs/screenshots/2
```

### DFS Traversal Output

Shows DFS traversal starting from vertex `0`.

```text
DFS Traversal:
0 1 2 3 4 5 6 7 8 9
```

Suggested file:

```text
docs/screenshots/3
```

### Performance Results

Shows the execution times for graph sizes `10`, `30`, and `100`.

```text
GRAPH SIZE: 10
BFS Execution Time: 3764600 ns
DFS Execution Time: 251200 ns

GRAPH SIZE: 30
BFS Execution Time: 638700 ns
DFS Execution Time: 603900 ns

GRAPH SIZE: 100
BFS Execution Time: 1504400 ns
DFS Execution Time: 1655600 ns
```

Suggested file:

```text
docs/screenshots/4
```

## F. Reflection

This assignment helped me understand how graphs can be represented and traversed in Java. The adjacency list made it easy to store each vertex with its connected neighbors, and it was more efficient than storing a full matrix for this type of graph. I also learned why a visited set is important: without it, BFS and DFS could revisit the same vertices again and again, especially because the graph is undirected.

BFS and DFS are similar because both can visit all reachable vertices, but they explore the graph in different ways. BFS uses a queue and visits nearby vertices first, which makes it useful for shortest path problems in unweighted graphs. DFS uses recursion and explores deeply before backtracking, which makes it useful for tasks such as cycle detection, path exploration, and connected component analysis. One challenge during implementation was making sure edges were added in both directions for an undirected graph and making sure traversal did not repeat already visited vertices.

## Repository Structure

```text
assignment4-graph/
|-- src/
|   |-- Vertex.java
|   |-- Edge.java
|   |-- Graph.java
|   |-- Experiment.java
|   `-- Main.java
|-- docs/
|   `-- screenshots/
|-- README.md
`-- .gitignore
```
