package list;


import java.util.Comparator;



/**
 * 
 * @author Jacob Knapo 
 * 		   CMSC 132 Herman 
 * 	       3/10/2015 
 *         Proj 3 Linked list 
 *  	   Similar to Unordered list except anytime an element is
 *         added its put in order from lowest to highest based on comparator that
 *         has been passed through.
 * @param <T>
 */
public class OrderedList<T> extends UnorderedList<T> {


	/*
	 * Constructor of Ordered list
	 * Inherits comparator and head field from parent.
	 * Head is declared null by parent.
	 */
	public OrderedList(Comparator<T> compare) {
		super(compare);
	}

	/*
	 * Adds item to list keeping the order when adding. 
	 */
	public void add(T element) {
		Node<T> curr = head;

		boolean found = false;
		while (curr.next != null && found != true) {
			// If find duplicate of element add here
			if (curr.next.data == element) {
				found = true; // breaks loop'
				// If need to add number as first element
			} else if (curr.data == null
					&& super.comparator.compare(element, curr.next.data) < 0) {
				found = true;
				// if Add in between two numbers
			} else if (curr.data != null
					&& super.comparator.compare(element, curr.data) > 0
					&& super.comparator.compare(element, curr.next.data) < 0) {
				found = true;
			} else {
				curr = curr.next;
			}
		}

		// If last element just add to end
		if (curr.next == null) {
			curr.next = new Node<T>(element, null);
			// Add was somewhere in the middle so splice list to add
		} else {
			Node<T> newEl = new Node<T>(element, curr.next);
			curr.next = newEl;
		}
	}
	//Similar to Unordered functions removeNumOccurrences() but since its an 
	//ordered list if we go past element we are looking for then 
	//we know that we are done looking through list
	public int removeNumOccurrences(T element, int num) {
		int tot = num;
		Node<T> curr = head.next; // Current node
		Node<T> prev = head; // Previous node
		boolean first_occur=false, done  = false;	//first occurence of element found
		

		while (curr != null && tot > 0 && done != true) {
			if (comparator.compare(curr.data, element) == 0){
				first_occur = true; //found first ccurence of element
				
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
			//Iterated past element so we are done searching through list
			}else if(first_occur == true){
				done = true; 
			}
			prev = curr;
			curr = curr.next;
		}
		return num - tot;
	}
	
	//SImliar to Unordered list countElement() but since ordered list 
	//if we iterate past element to a different element 
	//then we know to stop looking 
	public int countElement(T element) {
		int found = 0; // number of occurrences of element
		Node<T> curr = head.next;
		//First occurrence of element and tells loop when to stop
		boolean first_occur=false, done = false; 
		
		if (head.next == null) { // If empty list return 0
			return found;
		}

		while (curr.next != null && done!=true) {
			if(super.comparator.compare(element, curr.data) == 0){
				first_occur=true;
				// Found element
				if (super.comparator.compare(element, curr.data) == 0) {
					found++;
				}
			//Iterated past element so we are done searching through list
			}else if(first_occur==true){
				done=true;
			}
			curr = curr.next;
		}
		// Check last node if == to element
		if (super.comparator.compare(element, curr.data) == 0) {
			found++;
		}
		return found;
	}

}
