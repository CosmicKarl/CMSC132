package tests;

// (c) Larry Herman, 2015.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import list.UnorderedList;
import list.OrderedList;
import list.IntegerComparator;
import list.CharacterComparator;

import java.util.Iterator;
import org.junit.*;
import static org.junit.Assert.*;

/* Some sample lists are created below, that various tests can use.  The two
 * comparators right below can also be used by multiple tests.  Note that
 * the methods that construct and return the sample lists, and some of the
 * tests, call a generic helper method initList().  Although this
 * PublicTests class is not a generic class, Java allows having generic
 * methods in a non-generic class (this wasn't covered earlier in class) .
 * The method's type parameter will be instantiated with a specific type
 * when the method is invoked, so it can be called on lists and arrays with
 * different element types.
 */

public class PublicTests {

  // multiple tests can use these
  static IntegerComparator intComparator= new IntegerComparator();
  static CharacterComparator charComparator= new CharacterComparator();

  // Creates UnorderedLists of different lengths, and checks their lengths
  // using length().
  @Test public void testPublic1() {
    UnorderedList<Integer> list= new UnorderedList<Integer>(intComparator);

    assertEquals(0, list.length());

    list.add(132);
    assertEquals(1, list.length());

    list= sampleList1();  // replacing its previous contents
    assertEquals(6, list.length());
  }

  // Tests that clear() removes all of the elements from an UnorderedList,
  // and that elements can be added back afterwards.
  @Test public void testPublic2() {
    UnorderedList<Integer> list= sampleList1();

    list.clear();
    assertEquals(0, list.length());

    list.add(9);
    list.add(8);
    list.add(7);
    assertEquals(3, list.length());
  }

  // Creates UnorderedLists of different lengths, and checks their contents
  // using toString().
  @Test public void testPublic3() {
    UnorderedList<Integer> list= new UnorderedList<Integer>(intComparator);

    assertEquals("", list.toString());

    list.add(132);
    assertEquals("132", list.toString());

    list= sampleList1();  // replacing its previous contents
    assertEquals("30 18 68 45 40 23", list.toString());
  }

  // Tests calling elementAtPos() on various positions of different
  // UnorderedLists.
  @Test public void testPublic4() {
    UnorderedList<Integer> list= sampleList1();

    assertEquals((Integer) 18, list.elementAtPos(1));
    assertEquals((Integer) 68, list.elementAtPos(2));
    assertEquals((Integer) 45, list.elementAtPos(3));
    assertEquals((Integer) 40, list.elementAtPos(4));
  }

  // Tests that elementAtPos() throws the expected exception when called
  // with an index that is larger than the position of the last element in
  // its current object UnorderedList.
  @Test(expected= IndexOutOfBoundsException.class) public void testPublic5() {
    UnorderedList<Integer> list= sampleList1();

    list.elementAtPos(7);
  }

  // Tests that countElement() returns 0 on an element that does not occur
  // in an UnorderedList, and 1 when called on an element that occurs once
  // in a list.
  @Test public void testPublic6() {
    UnorderedList<Character> list= new UnorderedList<Character>(charComparator);

    assertEquals(0, list.countElement('x'));  // list is empty

    list= sampleList3();  // replacing its previous contents
    assertEquals(0, list.countElement('x'));
    assertEquals(1, list.countElement('b'));
  }

  // Tests that countElement() properly counts elements that occur multiple
  // times in an UnorderedList.
  @Test public void testPublic7() {
    UnorderedList<Character> list= sampleList3();

    assertEquals(3, list.countElement('a'));
  }

  // Tests append() on an UnorderedList.
  @Test public void testPublic8() {
    UnorderedList<Character> list1= sampleList3();
    UnorderedList<Character> list2=
                             new UnorderedList<Character>(charComparator);

    initList(list2, new Character[]{'m', 'a', 'r', 's', 'u', 'p', 'i', 'a',
                                    'l'});
    list1.append(list2);
    assertEquals("k o a l a b e a r m a r s u p i a l", list1.toString());
  }

  // Tests calling compareTo() on two UnorderedLists that have identical
  // contents.
  @Test public void testPublic9() {
    UnorderedList<Integer> list1= sampleList1();
    UnorderedList<Integer> list2= sampleList1();

    assertEquals(0, list1.compareTo(list2));
    assertEquals(0, list2.compareTo(list1));
  }

  // Tests calling compareTo() on two UnorderedLists that have differing
  // contents.
  @Test public void testPublic10() {
    UnorderedList<Character> list1=
                             new UnorderedList<Character>(charComparator);
    UnorderedList<Character> list2=
                             new UnorderedList<Character>(charComparator);

    initList(list1, new Character[]{'b', 'a', 'n', 'a', 'n', 'a'});
    initList(list2, new Character[]{'g', 'r', 'a', 'p', 'e'});

    assertTrue(list1.compareTo(list2) < 0);
    assertTrue(list2.compareTo(list1) > 0);
  }

  // Tests the functionality of the list iterator by calling it on an
  // UnorderedList.
  @Test public void testPublic11() {
    UnorderedList<Character> list= sampleList3();
    Iterator<Character> iter= list.iterator();
    String s= "";

    while (iter.hasNext())
      s += iter.next();

    assertEquals("koalabear", s);
  }

  // Tests whether two different iterators can simultaneously iterate over
  // the same UnorderedList.  The test uses nested iterators to see whether
  // an UnorderedList contains any duplicate elements, and does this twice,
  // once for a list with no duplicates, and once for a list with
  // duplicates.  This could actually be coded somewhat more efficiently
  // without using nested iterators, but the whole purpose is to test two
  // iterators iterating over the same list.
  @Test public void testPublic12() {
    UnorderedList<Character> list1=
                             new UnorderedList<Character>(charComparator);
    UnorderedList<Character> list2=
                             new UnorderedList<Character>(charComparator);
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

  // Tests whether removeNumOccurrences() successfully removes an element
  // from an UnorderedList.
  @Test public void testPublic13() {
    UnorderedList<Integer> list= sampleList1();

    list.removeNumOccurrences(68, 1);
    assertEquals("30 18 45 40 23", list.toString());
  }

  // Tests calling removeNumOccurrences() on UnorderedLists when the
  // number of occurrences to be removed is greater than 1.
  @Test public void testPublic14() {
    UnorderedList<Character> list= sampleList3();

    list.removeNumOccurrences('a', 2);
    assertEquals(1, list.countElement('a'));
    assertEquals("k o l b e a r", list.toString());
  }

  // Tests calling removeRange() to remove a few elements of an
  // UnorderedList.
  @Test public void testPublic15() {
    UnorderedList<Integer> list= sampleList1();

    list.removeRange(2, 3);
    assertEquals("30 18 40 23", list.toString());
  }

  // Tests a few of the methods with an OrderedList, to ensure that they
  // still work correctly.  (These should be methods that do not need to be
  // overridden by OrderedList.)  Note that we can have an UnorderedList
  // reference referring to a OrderedList.
  @Test public void testPublic16() {
    UnorderedList<Integer> list1= new OrderedList<Integer>(intComparator);
    UnorderedList<Integer> list2= sampleList2();

    assertEquals(0, list1.length());
    assertEquals("", list1.toString());

    list1.add(132);
    assertEquals(1, list1.length());
    assertEquals("132", list1.toString());

    list1= sampleList2();  // replacing its previous contents
    assertEquals(6, list1.length());
    assertEquals(1, list1.countElement(30));

    assertEquals(0, list1.compareTo(list2));
    assertEquals(0, list2.compareTo(list1));

    list1.add(30);  // test adding a second copy of an element
    assertEquals(2, list1.countElement(30));

    list1.clear();
    assertEquals(0, list1.length());
  }

  // Tests whether add() inserts elements into an OrderedList in the right
  // (sorted) order, using toString() and elementAtPos().  Note that we can
  // have an UnorderedList reference referring to a OrderedList.
  @Test public void testPublic17() {
    UnorderedList<Character> list= new OrderedList<Character>(charComparator);

    initList(list, new Character[]{'k', 'o', 'a', 'l', 'a', 'b', 'e', 'a',
                                   'r'});

    assertEquals("a a a b e k l o r", list.toString());

    assertEquals(new Character('a'), list.elementAtPos(1));
    assertEquals(new Character('a'), list.elementAtPos(2));
    assertEquals(new Character('b'), list.elementAtPos(3));
    assertEquals(new Character('e'), list.elementAtPos(4));
    assertEquals(new Character('k'), list.elementAtPos(5));
    assertEquals(new Character('l'), list.elementAtPos(6));
    assertEquals(new Character('o'), list.elementAtPos(7));
  }

  // private utility methods ////////////////////////////////////////////

  // adds all elements of an array of any type to a list of the same type-
  // which could be an UnorderedList, or an OrderedList
  private static <T> UnorderedList<T> initList(UnorderedList<T> list,
                                               T[] arr) {
    for (T elt : arr)
      list.add(elt);

    return list;
  }

  // returns an UnorderedList with several Integer elements
  private static UnorderedList<Integer> sampleList1() {
    UnorderedList<Integer> list= new UnorderedList<Integer>(intComparator);

    initList(list, new Integer[]{30, 18, 68, 45, 40, 23});

    return list;
  }

  // returns an OrderedList with several Integer elements
  private static OrderedList<Integer> sampleList2() {
    OrderedList<Integer> list= new OrderedList<Integer>(intComparator);

    initList(list, new Integer[]{30, 18, 68, 45, 40, 23});

    return list;
  }

  // returns an UnorderedList with several Character elements
  private static UnorderedList<Character> sampleList3() {
    UnorderedList<Character> list= new UnorderedList<Character>(charComparator);

    initList(list, new Character[]{'k', 'o', 'a', 'l', 'a', 'b', 'e', 'a',
                                   'r'});

    return list;
  }

}
