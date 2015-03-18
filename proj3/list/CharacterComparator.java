package list;

// (c) Larry Herman, 2015.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import java.util.Comparator;

public class CharacterComparator implements Comparator<Character> {

  public int compare(Character a, Character b) {
    if (a.charValue() < b.charValue())
      return -1;
    else
      if (a.charValue() == b.charValue())
        return 0;
      else return 1;
  }

}
