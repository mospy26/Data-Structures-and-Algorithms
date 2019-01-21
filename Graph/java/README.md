# Graph

This uses a adjacency list, containing the list of edges outgoing from a vertex in a graph.

### Algorithms implemented are as follows:

**BFS (aka Breadth First Search):**
- Runtime O(|V|+|E|) for adjacency lists, O(|V|^2) for adjacency matrix
- Shortest path (minimum number of edges between two vertices is given by its BFS. This gives shortest weight if all edges have same weight, otherwise use Dijkstra's algorithm for weighted graphs.
- There is a path between those vertices if and only if the end vertex appears in some layer in the BFS.

**Transitive closure:**
- Is a graph G' with same vertices, but with an edge between vertices in the graph that have a path in G.
- Functionality is added in BFS i.e. BFS can give transitive closure with some tweaks.

**DFS (aka Depth First Search):**
- Runtime is O(|V|+|E|)
- Easy to prove that: a graph is connected if and only if a DFS of the entire graph returns a single tree.
