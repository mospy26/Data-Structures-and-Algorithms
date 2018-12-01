public class LinkedList<E> implements Linked<E> {
 private class Node<E> implements Position<E> {
  private Node<E> prev;
  private Node<E> next;
  private E element;

  public Node(E element) {
   this.element = element;
   this.prev = null;
   this.next = null;
  }

  public Node<E> getPrev() {return prev;}
		public Node<E> getNext() {return next;}
		public E getElement() {return element;}
		public void setPrev(Node<E> prev) {this.prev = prev;}
		public void setNext(Node<E> next) {this.next= next;}
		public void setElement(E element) {this.element = element;}
 }

 // The header sentinel
	private Node<E> header;

	// The trailer sentinel
	private Node<E> trailer;

	// The number of positions in the list, not counting sentinels
	private int size;

	// This constructor creates an empty list
	public LinkedList() {
		header = new Node<E>(null);
		trailer = new Node<E>(null);
		header.setNext(trailer);
		trailer.setPrev(header);
		size = 0;
	}

 // constructor creates a list with specified elements
 public LinkedList(E[] elements) {
  header = new Node<E>(elements[0]);
  trailer = new Node<E>(elements[elements.length - 1]);
  for(int i = 0; i < elements.length; i++) {
    this.insert(elements[i]);
  }
  size = elements.length;
 }

 @Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return (this.size==0);
	}

	@Override
	public Position<E> first() {

		//if the list is empty, header and trailer are null
		if(this.isEmpty()) {
			return null;
		}

		//return the header position of the current linked positional list
		return (Position<E>)this.header;
	}

	@Override
	public Position<E> last() {

		//if list is empty, return null
		if(this.isEmpty()) {
			return null;
		}

		//return the trailer position of the current liked positional list
		return (Position<E>)this.trailer;
	}

	@Override
	public Position<E> before(Position<E> p) {

		//get the node at position p
		Node<E> n = (Node<E>)p;

		//return p's previous position
		return (Position<E>)n.getPrev();
	}

	@Override
	public Position<E> after(Position<E> p) {

		//get the node at position p
		Node<E> n = (Node<E>)p;

		//return p's next position
		return (Position<E>)n.getNext();
	}

	@Override
	public Position<E> insertFirst(E e) {
		Node<E> node;

		//if list is empty, this would be the first node - setting header and trailer appropriately
		if(this.isEmpty()) {
			node = new Node<E>(e);
			this.header = node;
			this.trailer = node;
		}

		//if the list is not empty, insert the first element and update list's header along with the newly added node
		else {
			node = new Node<E>(e);
			node.setPrev(null);
			node.setNext(this.header);
			this.header.setPrev(node);
			this.header = node;
		}
		//size increases by one as a new node is added
		size++;
		//return the position of the newly added node
		return (Position<E>)node;
	}

	@Override
	public Position<E> insertLast(E e) {
		Node<E> node;
		//if list is empty, this would be the first node - setting header and trailer appropriately
		if(this.isEmpty()) {
			node = new Node<E>(e);
			this.header = node;
			this.trailer = node;
		}
		//if the list is not empty, insert the last element and update list's trailer along with the newly added node
		else {
			node = new Node<E>(e);
			node.setPrev(this.trailer);
			node.setNext(null);
			this.trailer.setNext(node);
			this.trailer = node;
		}
		//size increases by one as a new node is added
		size++;
		//return the position of the newly added node
		return (Position<E>)node;
	}

	@Override
	public Position<E> insertBefore(Position<E> p, E e) {

		Node<E> n = null;

		Node<E> newNode = null;
		//if the list is not empty, add the node appropriately along with updating details of it
		if(!this.isEmpty()) {
			n = (Node<E>)p;

			Node<E> previousNode = n.getPrev();
			newNode = new Node<E>(e);

   //if the previous node is not null, means that the specified position is not header
			if(previousNode!=null) {
				previousNode.setNext(newNode);
				newNode.setPrev(previousNode);
			}

   //previous node of n is null so the new node is supposed to be the header
			else {
				this.header=newNode;
				newNode.setPrev(null);
			}

			n.setPrev(newNode);

			newNode.setNext(n);
		}
		//if the list is empty, again this node is the first node
		else {
			newNode = new Node<E>(e);
			newNode.setPrev(null);
			newNode.setNext(null);
			this.header=newNode;
			this.trailer=newNode;
		}
		//as a node is added, the size increases by one
		size++;
		//return the position of the new node
		return (Position<E>)newNode;
	}

	@Override
	public Position<E> insertAfter(Position<E> p, E e) {
		//n is node at position p
		Node<E> n=null;
		//newNode is the newNode to be added to the list
		Node<E> newNode = null;
		//if the list is not empty, add the node appropriately along with updating details of it
		if(!this.isEmpty()) {
			n = (Node<E>)p;
			newNode = new Node<E>(e);
			//latterNode is n's latter node
			Node<E> latterNode = n.getNext();
			//if the latter node is not null, means that the specified position is not trailer
			if(latterNode!=null) {
				latterNode.setPrev(newNode);
				newNode.setNext(latterNode);
			}
			//latter node of n is null so the new node is supposed to be the trailer
			else {
				this.trailer=newNode;
				newNode.setNext(null);
			}
			//the new node is supposed to be after n
			n.setNext(newNode);
			//n is supposed to be before the new node
			newNode.setPrev(n);
		}
		//if the list is empty, again this node is the first node
		else {
			newNode = new Node<E>(e);
			newNode.setPrev(null);
			newNode.setNext(null);
			this.header=newNode;
			this.trailer=newNode;
		}
		//as a node is added, the size increases by one
		size++;
		//return the position of the new node
		return (Position<E>)newNode;
	}

	@Override
	public E set(Position<E> p, E e) {
		//removed element is the element to be removed
		E removedElement=null;
		//n is the element that is replacing the element removedElement
		Node<E> n=null;
		//if the list is not empty, replace the node's element with the specified element
		if(!this.isEmpty()) {
			n = (Node<E>)p;
			//get removed element
			removedElement=n.getElement();
			n.setElement(e);
		}
		//return the removed element
		return removedElement;
	}

 @Override
 public Position<E> insertInBetween(Position<E> p1, E e, Position<E> p2) {
  Node<E> node = new Node<E>(e);

  Node<E> previous = (Node<E>)p1;
  Node<E> nextNode = (Node<E>)p2;

  node.setPrev((Node<E>)p1);
  node.setNext((Node<E>)p2);
  previous.setNext(node);
  nextNode.setPrev(previous);

  return (Position<E>)node;
 }

	@Override
	public E remove(Position<E> p) {
		//n is the node which is to be removed
		Node<E> n = null;
		//if the list is not empty, remove the element while appropriately updating the list
		if(!this.isEmpty()) {
			//get node at position p which is to be removed
			n = (Node<E>)p;
			p = null;
			//previous node of n
			Node<E> previousNode = n.getPrev();
			//latter node of n
			Node<E> latterNode = n.getNext();
			if(previousNode!=null)
				previousNode.setNext(latterNode);
			//if previous node is null, means that the node to be removed is the header
			else
				this.header = latterNode;
			if(latterNode!=null)
				latterNode.setPrev(previousNode);
			//if latter node is null, means that the node to be removed is the trailer
			else
				this.trailer = previousNode;
			//remove all links of the node which is to be removed
			n.setPrev(null);
			n.setNext(null);
			//size decreases by one as the node is removed
			size--;
		}
		//return the removed element
		return n.getElement();
	}

 /**
  * This will insert an element at the end of the list
  * @param e the element to insert
  * @return the position representing the inserted element e
  */
 public Position<E> insert(E e) {
  Position<E> p = this.insertLast(e);
  return p;
 }

 public void clear() {
  while(this.size > 0) {
   this.remove(this.trailer);
  }
 }

 public String toString() {
  String list = "";
  Node<E> n = this.header;
  if (n == null) {
   list += "{EMPTY_LIST}";
  }
  else {
   list += "{";
   while (n.getNext() != null) {
    list += n.getElement() + " ,";
    n = n.getNext();
   }
   list += n.getElement() + "}";
  }
  return list;
 }

 //Some testing
 public static void main(String[] args) {
  LinkedList<Integer> list = new LinkedList<Integer>();
  list.insert(5);
  list.insert(10);
  list.insert(1);
  LinkedList<Integer> list2 = new LinkedList<Integer>(new Integer[]{1,2,3,4,5,6,6,7,8,8});
  System.out.println(list2);
  list2.clear();
  System.out.println(list2);
  list2.insert(1);
  list2.insert(3);
  System.out.println(list2);
 }
}
