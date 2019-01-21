public class Edge<V> {
 private final V source;
 private final V destination;
 private int weight;

 public Edge(V source, V destination) {
  this.source = source;
  this.destination = destination;
  this.weight = 0;
 }

 public Edge(V source, V destination, int weight) {
  this(source, destination);
  this.weight = weight;
 }
 public V getSource() { return this.source; }
 public V getDestination() { return this.destination; }
 public void setWeight(int weight) { this.weight = weight; }
 public int getWeight() { return this.weight; }

 public String toString() {
  if(this.weight == 0) return "(" + this.source + "," + this.destination + ")";
  else return "(" + this.source + "," + this.destination + " -> " + this.weight + ")";
 }
}
