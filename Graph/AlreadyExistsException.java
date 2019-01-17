public class AlreadyExistsException extends Exception {

 public AlreadyExistsException(Object element) {
  super("Vertex " + element + " already exists in the graph!");
 }
}
