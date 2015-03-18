package tests;

import org.junit.*;

import list.UnorderedList;
import list.OrderedList;
import list.IntegerComparator;
import list.CharacterComparator;

import java.util.Iterator;

import static org.junit.Assert.*;

public class StudentTests {
	// multiple tests can use these
	static IntegerComparator intComparator= new IntegerComparator();
	static CharacterComparator charComparator= new CharacterComparator();
	
	private static UnorderedList<Integer> sampleList1() {
		UnorderedList<Integer> list = new UnorderedList<Integer>(intComparator);

		initList(list, new Integer[] { 30, 18, 68, 45, 40, 23 });

		return list;
	}
	
	 private static OrderedList<Integer> sampleList2() {
		    OrderedList<Integer> list= new OrderedList<Integer>(intComparator);

		    initList(list, new Integer[]{30, 18, 68, 45, 40, 23});

		    return list;
	}
	
	private static UnorderedList<Character> sampleList3() {
	    UnorderedList<Character> list= new UnorderedList<Character>(charComparator);

	    initList(list, new Character[]{'k', 'k', 'k', 'a', 'k'});

	    return list;
	  }
	
	private static OrderedList<Character> sampleList4() {
		OrderedList<Character> list= new OrderedList<Character>(charComparator);

	    initList(list, new Character[]{'k', 'k', 'k', 'a', 'k'});

	    return list;
	  }
	private static OrderedList<Character> sampleList5() {
		OrderedList<Character> list= new OrderedList<Character>(charComparator);

	    initList(list, new Character[]{'k', 'k', 'k', 'a', 'k','l','l'});

	    return list;
	  }

	private static <T> UnorderedList<T> initList(UnorderedList<T> list, T[] arr) {
		for (T elt : arr)
			list.add(elt);

		return list;
	}
	
	
	//Checks append for ordered list
	@Test public void appendOrd(){
		OrderedList<Character> list1= sampleList4();
		OrderedList<Character> list2= sampleList4();
		list1.append(list2);
		assertEquals("a a k k k k k k k k",list1.toString());
		list1.removeRange(1, 5);
		
		assertEquals("a k k k k",list1.toString());
		
		//test that list are unique from each other
		list2.clear();
		assertEquals("a k k k k",list1.toString());
		
		
	}
	//Test removeOccurence for ordered list for ordered list
	@Test public void remoCurrOrd(){
		OrderedList<Character> list1= sampleList5();
		OrderedList<Character> list2= sampleList5();
		
		list1.append(list2); //Give more characters to remove
		
		//Tests remove elements starting from begging node
		list1.removeNumOccurrences('a',3);
		assertEquals("k k k k k k k k l l l l",list1.toString()); 
		
		//Test removing elements with end node
		list1.removeNumOccurrences('l',5);
		assertEquals("k k k k k k k k",list1.toString());	
		
	}
	
	//test countelements of an ordered list on ordered list
	@Test public void countElem(){
		OrderedList<Character> list1= sampleList5();
		OrderedList<Character> list2= sampleList5();
		
		list1.append(list2); //Give more characters to count
		assertEquals(4, list1.countElement('l'));
		assertEquals(8, list1.countElement('k'));
		assertEquals(2, list1.countElement('a'));
		
	}
	
	
	
	 // Tests whether two different iterators can simultaneously iterate over
	  // the same OrderedList.  The test uses nested iterators to see whether
	  // an OrderedList contains any duplicate elements, and does this twice,
	  // once for a list with no duplicates, and once for a list with
	  // duplicates.  This could actually be coded somewhat more efficiently
	  // without using nested iterators, but the whole purpose is to test two
	  // iterators iterating over the same list.
	
	  @Test public void TestIteratorOrderedList() {
		  OrderedList<Character> list1=
	                             new OrderedList<Character>(charComparator);
		  OrderedList<Character> list2=
	                             new OrderedList<Character>(charComparator);
	    Iterator<Character> outer1, inner1;
	    Iterator<Character> outer2, inner2;
	    char[] list1Chars= {'n', 'o', 'd', 'u', 'p', 'l', 'i', 'c', 'a', 't', 'e',
	                        's'};
	    char[] list2Chars= {'r', 'e', 'p', 'e', 'a', 't', 'e', 'd'};
	    Character curCharOuter, curCharInner;
	    boolean foundDuplicate;
	    int i;

	    // we initialize the lists this way because we need to ensure that all
	    // objects in lists are unique
	    for (i= 0; i < list1Chars.length; i++)
	      list1.add(new Character(list1Chars[i]));
	    for (i= 0; i < list2Chars.length; i++)
	      list2.add(new Character(list2Chars[i]));

	    // check for a list without duplicates
	    outer1= list1.iterator();
	    foundDuplicate= false;
	    while (outer1.hasNext()) {
	      curCharOuter= outer1.next();
	      inner1= list1.iterator();

	      while (inner1.hasNext()) {
	        curCharInner= inner1.next();
	        // check for aliasing- we don't want to conclude that an element is
	        // duplicated by comparing it to itself
	        if (curCharOuter != curCharInner && curCharOuter.equals(curCharInner))
	          foundDuplicate= true;
	      }
	    }

	    assertFalse(foundDuplicate);

	    // check for a list with duplicates
	    outer2= list2.iterator();
	    foundDuplicate= false;
	    while (outer2.hasNext()) {
	      curCharOuter= outer2.next();
	      inner2= list2.iterator();

	      while (inner2.hasNext()) {
	        curCharInner= inner2.next();
	        // check for aliasing- we don't want to conclude that an element is
	        // duplicated by comparing it to itself
	        if (curCharOuter != curCharInner && curCharOuter.equals(curCharInner))
	          foundDuplicate= true;
	      }
	    }

	    assertTrue(foundDuplicate);
	  }
	  
	  //Tests changing head reference node to new list
	  @Test public void testIsEqual() {
		    UnorderedList<Integer> list= new UnorderedList<Integer>(intComparator);

		    assertEquals("", list.toString());

		    list.add(132);
		    assertEquals("132", list.toString());

		    list= sampleList1();  // replacing its previous contents
		    assertEquals("30 18 68 45 40 23", list.toString());
		  }
	  
	// Tests calling elementAtPos() at end and beginning of list
	  @Test public void ElmAtPosEnds() {
	    UnorderedList<Integer> list= sampleList1();

	    assertEquals((Integer) 23, list.elementAtPos(5));
	    assertEquals((Integer) 30, list.elementAtPos(0));
	  }
}
