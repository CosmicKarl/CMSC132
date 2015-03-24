package recursion;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Jacob Knapo 
 * 		   CMSC 132 Herman 
 * 	       3/23/2015 
 *         Proj 4 recursion
 *         Class with static different static methods that can be called on a List.
 *
 */
public class Recursive {

  /**
   * Says if a list any elements smaller than elt passed.
   * @param list
   * @param elt
   * @return True: If elt <= all list[ele]
   * 		 False: If elt > than element of list
   */
  public static <T extends Comparable<T>> boolean hasNoneSmaller(List<T> list,
                                                                T elt) {
	//If list empty then return true
    if(list.size()==0){
    	return true;
    //This is for efficiency
    }else if(elt == null){
    	throw new NullPointerException();
    }else{
    	int index = 0;
    	return hasNoneSmaller(list, elt, index);
    }
    
  }
  
  //REcursive helper method for hasNoneSmaller
  private static <T extends Comparable<T>> boolean hasNoneSmaller(List<T> list,
          T elt, int index) {
	  //If you get to end of list then return true
	  if(index == list.size()){
		  return true;
	  //If elt <= list[element] do recursive call on next element
	  }else if(elt.compareTo(list.get(index)) <= 0){
		  return hasNoneSmaller(list, elt, ++index);		  
	  }
	  return false;
	  
  }

  /**
   * Finds last occurrence of element passed in list
   * @param list
   * @param element
   * @return index of element found in list. If not found -1 is returned
   */
  public static <T> int lastIdxOf(List <T> list, T element) {
    if(list.size() == 0){
    	return -1;
    }else if(element == null){
    	throw new NullPointerException();
    }else{
    	int index = list.size()-1;
    	return lastIdxOf(list, element, index);
    }
  }
  
  //Recursive helper method for lastIdxOf
  private static <T> int lastIdxOf(List<T> list, T element, int index) {
	  //Bases case: means went through whole list and ele was not found
	  if(index == -1){
		  return -1;
	  //If not equal current list element != ele grab 
	  //recursively grab next element
	  }else if(!(list.get(index).equals(element))){
		  return lastIdxOf(list, element, --index);
	  }
	  return index;
  }

  /**
   * Adds newElt to after every occurrence of elt in list. 
   * @param list
   * @param elt
   * @param newElt
   * @return A new list that is a copy of list with added elements. 
   * If elt is not found then just a copy of list is returned
   */
  public static <T> List<T> insertAfter(List<T> list, T elt, T newElt) {
	  List<T> copy = new ArrayList<T>();
	  return insertAfter(list, elt, newElt,copy,0);
  }
  
  //Recursive helper method insertAfter
  private static <T> List<T> insertAfter(List<T> list, T elt, T newElt,List<T> copy, int index){
	  if(index == list.size()){
		  return copy;
	  //If found elt then add list[ele] then newelt
	  }else if(list.get(index).equals(elt)){
		  copy.add(list.get(index));
		  copy.add(newElt);
		  insertAfter(list, elt, newElt,copy,++index);
	  //Just add list[ele] to copy
	  }else{
		  copy.add(list.get(index));
		  insertAfter(list, elt, newElt,copy,++index);
	  }
	  return copy;
  }

  /**
   * Removes num occurrences of elt in list. 
   * @param list
   * @param elt
   * @param num
   * @return Returns a copy of list with 0 or more elements removed. If num <= 0 
   * then just a copy of list is returned
   */
  public static <T> List<T> removeNumTimes(List<T> list, T elt, int num) {
	  List<T> copy = new ArrayList<T>();
	  return removeNumTimes(list, elt, num,copy,0);
  }  
  
  //Recursive helper method of removeNumTimes
  private static <T> List<T> removeNumTimes(List<T> list, T elt, int num,List<T> copy, int index){
	  if(index == list.size()){
		  return copy;
	  //If found ele to remove and num > 0 then dec num and skip over adding list[ele] to copy
	  }else if(list.get(index).equals(elt) && num > 0){
		  num--;
		  removeNumTimes(list, elt, num,copy,++index);
	  //Just add list[ele] to copy
	  }else{
		  copy.add(list.get(index));
		  removeNumTimes(list, elt, num,copy, ++index);
	  }
	  return copy;
  }

}
