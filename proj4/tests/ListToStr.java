package tests;

// (c) Larry Herman, 2015.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.List;
import java.util.Iterator;

public class ListToStr {

  // converts a list to a string, for use in testing the various methods
  public static <T> String listToStr(List<T> list) {
    Iterator<T> iter= list.iterator();
    String s= "";

    while (iter.hasNext()) {
      s += iter.next();
      if (iter.hasNext())
        s += " ";
    }

    return s;
  }

}
