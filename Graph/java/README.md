
# Graph

This uses a adjacency list, containing the list of edges outgoing from a vertex in a graph.

### Algorithms implemented are as follows:

**BFS (aka Breadth First Search):**
- Runtime O(|V|+|E|) for adjacency lists, O(|V|^2) for adjacency matrix
- Shortest path (minimum number of edges between two vertices is given by its BFS. Shortest weight if all edges has same weight, otherwise use djikstras algorithm fir weighted graphs.

**Transitive closure:**
- Is a graph G' with same vertices, but with an edge between vertices in the graph that have a path in G.
- Functionality is added in BFS i.e. BFS can give transitive closure with some tweaks.
