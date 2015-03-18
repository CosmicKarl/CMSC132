package list;

// (c) Larry Herman, 2015.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {

  public int compare(Integer a, Integer b) {
    if (a.intValue() < b.intValue())
      return -1;
    else
      if (a.intValue() == b.intValue())
        return 0;
      else return 1;
  }

}
