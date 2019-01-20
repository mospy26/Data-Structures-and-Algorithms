public class VertexNotFoundException extends Exception {

 public VertexNotFoundException(Object element) {
  super("Vertex " + element + " does not exist in the graph!");
 }
}
