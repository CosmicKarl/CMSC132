package tests;

import java.util.ArrayList;
import java.util.List;

import recursion.Recursive;

import org.junit.*;

import static org.junit.Assert.*;

public class StudentTests {

	// Adds all elements of an array of any type to a list of the same type.
	private static <T> List<T> initList(List<T> list, T[] arr) {
		for (T elt : arr)
			list.add(elt);

		return list;
	}

	// Returns a multiple-element list containing Characters.
	private static List<Character> sampleList1() {
		return initList(new ArrayList<Character>(), new Character[] { 'n', 'i',
				't', 'w', 'i', 't' });
	}

	// Returns a multiple-element list containing Integers.
	private static List<Integer> sampleList2() {
		return initList(new ArrayList<Integer>(), new Integer[] { 8, 1, 9, 2,
				3, 4, 5, 7, 6, 5 });
	}

	private static List<Integer> sampleList3() {
		return initList(new ArrayList<Integer>(), new Integer[] { 9, 8, 7, 6,
				5, 3 });
	}

	private static List<Integer> sampleList4() {
		return initList(new ArrayList<Integer>(), new Integer[] { 9, 9, 7, 9,
				5, 9 });
	}
	
	private static List<Integer> sampleList5() {
		return initList(new ArrayList<Integer>(), new Integer[] { 9, 9, 9,
				 9 });
	}

	// Test hasNoneSmaller
	@Test
	public void HasNoneSmallerTest() {
		// Test if can detect first element as smaller
		assertFalse(Recursive.hasNoneSmaller(sampleList2(), 9));

		// test if last element is smaller
		assertTrue(Recursive.hasNoneSmaller(sampleList3(), 3));

		// Test if statement is true
		assertTrue(Recursive.hasNoneSmaller(sampleList3(), 3));
	}

	@Test
	public void lastIdexOfTest() {
		// Test element as end point
		assertEquals(5, Recursive.lastIdxOf(sampleList1(), 't'));

		// Test if element is the first element
		assertEquals(0, Recursive.lastIdxOf(sampleList1(), 'n'));
	}

	@Test
	public void insertAfterTest() {
		// Test inserting element after first one, at a double occurrence, and at
		// the end
		List<Integer> list = Recursive.insertAfter(sampleList4(), 9, 666);

		assertEquals("9 666 9 666 7 9 666 5 9 666", ListToStr.listToStr(list));
	}
	
	@Test public void removeNumTimesTest() {
		//Test Removing First element and that passed list is not effected
	    List<Integer> list1= Recursive.removeNumTimes(sampleList4(), 9, 1);

	    assertEquals("9 7 9 5 9", ListToStr.listToStr(list1));
	    
	    //Tests removing all eles found, removing ele at begginig, 
	    //and tests if num is greater than actual number of occerences of elt
	    // that is will remove max found
	    List<Integer> list2= Recursive.removeNumTimes(sampleList4(), 9, 5);

	    assertEquals("7 5", ListToStr.listToStr(list2));
	    
	    //Tests removing all elements and empty list is returned
	    List<Integer> list3= Recursive.removeNumTimes(sampleList5(), 9, 5);

	    assertEquals("", ListToStr.listToStr(list3));
	  }
}
