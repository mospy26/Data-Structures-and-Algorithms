public interface Linked<E> {

 /**
  * Returns the number of elements in the list
  * @return number of elements in list
  */
  int size();

 /**
  * Checks if the list is empty
  * @return true if the list is empty else false
  */
  boolean isEmpty();

  /**
   * Returns the first element of the list
   * @return the first element of the list
   */
  Position<E> first();

  /**
   * Returns the first element of the list
   * @return the first element of the list
   */
  Position<E> last();

  /**
   * Inserts element in between two positions in the list
   * @param p1 the position after which the element must be inserted
   * @param e the element to be inserted
   * @param p2 the position before which the element must be inserted
   * @return the position representing the inserted element
   */
  Position<E> insertInBetween(Position<E> p1, E e, Position<E> p2);

  /**
   * Inserts element after a position in the list
   * @param p the position after which the element must be inserted
   * @param e the element to be inserted
   * @return the position representing the inserted element
   */
  Position<E> insertAfter(Position<E> p, E e);

  /**
   * Inserts element before a position in the list
   * @param p the position before which the element must be inserted
   * @param e the element to be inserted
   * @return the position representing the inserted element
   */
  Position<E> insertBefore(Position<E> p, E e);

  /**
   * Removes an element from the list at position p
   * @param p the position from which the element is to be removed
   * @return the element that was removed
   */
  E remove(Position<E> p);

  /**
   * Sets an element of the list with the given element at given position
   * @param p the position where the element must be set
   * @param e the element to be set
   * @return the replaced element
   */
  E set(Position<E> p, E e);

  /**
   * Sets an element of the list with the given element at given position
   * @param p the position before which the element is to be returned
   * @param e the element to be set
   * @return the replaced element
   */
  Position<E> before(Position<E> p);

 /**
  * Inserts an element at the end of the list
  * @param e the element to be inserted
  * @return the position representing the inserted element
  */
  public Position<E> insertLast(E e);

 /**
  * Returns the position of an element lying after the specified position
  * @param p the position after which the element is to be retrived
  * @return the position representing the next element
  */
  public Position<E> after(Position<E> p);

 /**
  * Inserts the given element to the start of the list
  * @param e the element to be added
  * @return the position representing the inserted element
  */
  public Position<E> insertFirst(E e);

 /**
  * Clears the current List
  */
  public void clear();
}
