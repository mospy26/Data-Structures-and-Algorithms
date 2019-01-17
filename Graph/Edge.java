public class Edge<V> {
 private final V source;
 private final V destination;

 public Edge(V source, V destination) { this.source = source; this.destination = destination; }
 V getSource() { return this.source; }
 V getDestination() { return this.destination; }

 public String toString() {
  return "(" + this.source + " , " + this.destination + ")";
 }
}
