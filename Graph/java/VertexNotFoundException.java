public class VertexNotFoundException extends RuntimeException {

 public VertexNotFoundException(Object element) {
  super("Vertex " + element + " does not exist in the graph!");
 }
}
