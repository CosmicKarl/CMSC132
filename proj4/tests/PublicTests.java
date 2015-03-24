package tests;

// (c) Larry Herman, 2015.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.List;
import java.util.ArrayList;
import recursion.Recursive;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests the hasNoneSmaller() method in a straightforward, typical case.
  @Test public void testPublic1() {
    assertFalse(Recursive.hasNoneSmaller(sampleList1(), 'x'));
  }

  // Tests the lastIdxOf() method in a straightforward, typical case.
  @Test public void testPublic2() {
    assertEquals(3, Recursive.lastIdxOf(sampleList1(), 'w'));
  }

  // Tests the insertAfter() method in a straightforward, typical case.
  @Test public void testPublic3() {


  }

  // Tests the removeNumTimes() method in a straightforward, typical case.
  @Test public void testPublic4() {
    List<Integer> list= Recursive.removeNumTimes(sampleList2(), 9, 1);

    assertEquals("8 1 2 3 4 5 7 6 5", ListToStr.listToStr(list));
  }

  

  // Adds all elements of an array of any type to a list of the same type.
  private static <T> List<T> initList(List<T> list, T[] arr) {
    for (T elt : arr)
      list.add(elt);

    return list;
  }

  // Returns a multiple-element list containing Characters.
  private static List<Character> sampleList1() {
    return initList(new ArrayList<Character>(),
                    new Character[]{'n', 'i', 't', 'w', 'i', 't'});
  }

  // Returns a multiple-element list containing Integers.
  private static List<Integer> sampleList2() {
    return initList(new ArrayList<Integer>(),
                    new Integer[]{8, 1, 9, 2, 3, 4, 5, 7, 6, 5});
  }

}
