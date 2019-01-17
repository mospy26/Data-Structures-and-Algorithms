public class VertexDoesNotExistException extends Exception {

 public VertexDoesNotExistException(Object element) {
  super("Vertex " + element + " does not exist in the graph!");
 }
}
