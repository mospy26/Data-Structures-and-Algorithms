public class AlreadyExistsException extends Exception {

 public AlreadyExistsException(Object element) {
  super(element + " already exists in the graph!");
 }
}
