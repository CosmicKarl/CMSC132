package list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

/**
 * 
 * @author Jacob Knapo 
 * 		   CMSC 132 Herman 
 * 	       3/10/2015 
 *         Proj 3 Linked list 
 *         Create a singly list of unordered elements. Can add one type of data type.
 *         Comparator must be defined for data type added
 * @param <T>
 */
public class UnorderedList<T> implements Iterable<T>,
		Comparable<UnorderedList<T>> {

	//Inner Node class. Only keeps tract of next reference
	protected class Node<D> {
		D data;
		Node<D> next; // Next element

		Node(D d, Node<D> next) {
			this.next = next;
			this.data = d; // Makes generic type data
		}
	}

	protected Node<T> head;
	protected Comparator<T> comparator;

	/*
	 * Constructor initializes comparator and sets head to point to null
	 */
	public UnorderedList(Comparator<T> comparator) {
		this.comparator = comparator;
		head = new Node<T>(null, null);
	}

	// Connects newElt to end of list
	public void add(T newElt) {
		Node<T> curr = head;

		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = new Node<T>(newElt, null);

	}

	/* 
	 * Counts length of link list
	 */
	public int length() {
		// If head points to null then size is zero
		if (head.next == null) {
			return 0;
		}

		Node<T> curr = head;
		int size = 0;
		// Count while you iterate over the loop
		while (curr.next != null) {
			curr = curr.next;
			size++;
		}
		return size;

	}

	/*
	 * Clears all elements of link list
	 */
	public void clear() {
		// Clip head and set to point to null for call garbege collector on all
		// other elements
		head = new Node<T>(null, null);
	}

	/*
	 * ToString method that returns string in this pattern:
	 * "data" + " " + "data" + ...
	 */
	public String toString() {
		String s = "";
		Node<T> curr = head.next;

		if (head.next == null) {
			return s;
		}

		s = curr.data.toString();
		while (curr.next != null) {
			curr = curr.next;
			s += " " + curr.data.toString();
		}

		return s;
	}

	/*
	 * Return element at indexed position in list
	 */
	public T elementAtPos(int index) throws IndexOutOfBoundsException {
		// If greater than length throw exception
		if (index >= (this.length())) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> curr = head.next;
		int item = 0;

		// Iterate over list until find index
		while (curr.next != null && item != index) {
			item++;
			curr = curr.next;
		}

		return curr.data;
	}

	/*
	 * Count the number of occurrence of element passed
	 */
	public int countElement(T element) {
		int found = 0; // number of occurrences of element
		Node<T> curr = head.next;

		if (head.next == null) { // If empty list return 0
			return found;
		}

		while (curr.next != null) {
			// Found element
			if (comparator.compare(element, curr.data) == 0) {
				found++;
			}
			curr = curr.next;
		}
		// Check last node if == to element
		if (comparator.compare(element, curr.data) == 0) {
			found++;
		}
		return found;
	}

	/*
	 * Adds elements of otherList to thisList. Adds just value not reference so both
	 * list are are unique. If list being added is empty does nothing.
	 */
	public void append(UnorderedList<T> otherList) {
		// If otherList we are appending has no elements don't add otherwise.......
		if (otherList.head.next != null) {
			
			Node<T> curr = otherList.head.next;
			while (curr.next != null) {
				//Can jsut 
				this.add(curr.data);
				curr = curr.next;
			}
			this.add(curr.data);
		}

	}

	/*
	 * Compares two lists using passed in comparator:
	 * First once a mismatch in elements are found return:
	 * positive if thislist element value is greater or negative if otherlist
	 * Second compares size of list if all elements are equal up to a certain point:
	 * again positive if thislist size is greater or negative if otherlist
	 * Last return 0 if both list are equal
	 */
	public int compareTo(UnorderedList<T> otherList) {
		Node<T> thisCurr = head;
		Node<T> othCurr = otherList.head;
		boolean done = false;

		while (thisCurr.next != null && othCurr.next != null && done != true) {
			thisCurr = thisCurr.next;
			othCurr = othCurr.next;
			// If letter doesn't match break loop
			if (comparator.compare(thisCurr.data, othCurr.data) != 0) {
				done = true;
			}
		}
		// None matching return which character is greater
		if (comparator.compare(thisCurr.data, othCurr.data) != 0) {
			return comparator.compare(thisCurr.data, othCurr.data);
			// Current obj list is shorter return neg
		} else if (thisCurr.next == null && othCurr.next != null) {
			return -1;
			// Current obj list is longer return pos
		} else if (thisCurr.next != null && othCurr.next == null) {
			return 1;
			// All were matching character return zero
		} else {
			return 0;
		}
	}

	/*
	 * Creates inner class to make Unordered list iterable.
	 */
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> curr = head;

			/*
			 * Says if there is another element in list
			 */
			@Override
			public boolean hasNext() {
				// If not null then has another element
				if (curr.next != null) {
					return true;
				}
				return false;
			}

			// Returns an next element of list. If at end of list
			// throw NoSuchElementException.
			@Override
			public T next() {
				if (curr == null) {
					throw new NoSuchElementException();
				}
				curr = curr.next;
				return curr.data;
			}
		};

	}

	/*
	 * Removes a specific element num times and return how many were actually removed.
	 * So if num is greater than actual number of elements removed we still just return
	 * how many were found and removed.
	 */
	public int removeNumOccurrences(T element, int num) {
		int tot = num;
		Node<T> curr = head.next; // Current node
		Node<T> prev = head; // Previous node

		while (curr != null && tot > 0) {
			// If equals element and is last element
			if (comparator.compare(curr.data, element) == 0
					&& curr.next == null) {
				tot--;
				prev.next = null;
				// if equals element somewhere in the middle
			} else if (comparator.compare(curr.data, element) == 0) {
				tot--;
				prev.next = curr.next;
				curr.next = null;
				curr = prev;
			}
			prev = curr;
			curr = curr.next;
		}
		return num - tot;
	}

	/*
	 * Removes elements from one position all the way to next including that
	 * element
	 */
	public void removeRange(int fromPos, int toPos) {
		Node<T> curr = head.next; // Current node
		Node<T> prev = head; // Previous node
		int dist = toPos - fromPos + 1; // distance to remove including last
										// position
		int pos = 0;
		int size = this.length() - 1; // length of list - 1
		fromPos = (fromPos < 0) ? 0 : fromPos; // if fromPos less than zero just
												// make it zero
		toPos = (toPos > size) ? (size) : toPos; // If toPos greater than size
													// of list then change to
													// size of list
		// Do nothing if toPos not greater than fromPos
		if (fromPos <= toPos) {
			while (dist != 0) {
				// Found starting position to start removing elements
				if (pos >= fromPos) {
					dist--;
					prev.next = curr.next;
					curr.next = null;
					curr = prev;
				}
				pos++;
				prev = curr;
				curr = curr.next;
			}
		}

	}

}
