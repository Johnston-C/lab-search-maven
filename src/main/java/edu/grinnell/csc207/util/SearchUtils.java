package edu.grinnell.csc207.util;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Assorted utilities for searching structures.
 *
 * @author Your Name Here
 * @author Your Name Here
 * @author Samuel A. Rebelsky (starter code)
 */
public class SearchUtils {

  // +---------+
  // | Globals |
  // +---------+

  private static int count;

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <=
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int iterativeBinarySearch(int[] vals, int val) throws Exception {
    reset();
    int min = 0;
    int max = vals.length - 1;
    count++;
    while(min <= max) {
      count++;
      if (val == vals[min / 2 + max / 2 + (min % 2) * (max % 2)]) {
        return min / 2 + max / 2 + (min % 2) * (max % 2);
      } else if (vals[min / 2 + max / 2 + (min % 2) * (max % 2)] > val) {
        max = min / 2 + max / 2 + (min % 2) * (max % 2) - 1;
      } else {
        min = min / 2 + max / 2 + (min % 2) * (max % 2) + 1;
      } // if / else if / else
    } // while
    throw new Exception("Value does not exist in the array.");
  } // iterativeBinarySearch

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <=
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int recursiveBinarySearch(int[] vals, int val) throws Exception {
    reset();
    count++;
    return rbsHelper(vals, 0, vals.length - 1, val);
  } // recursiveBinarySearch

  /**
   * Search for val in a subarray of values, return the index of an 
   * instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param lb
   *   The lower bound of the area of interest (inclusive)
   * @param ub
   *   The upper bound of the area of interest (exclusive)
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i between lb and ub s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <=
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int rbsHelper(int[] vals, int lb, int ub, int val) throws Exception {
    if(lb <= ub) {
      count++;
      if (val == vals[lb / 2 + ub / 2 + (lb % 2) * (ub % 2)]) {
        return lb / 2 + ub / 2 + (lb % 2) * (ub % 2);
      } else if (vals[lb / 2 + ub / 2 + (lb % 2) * (ub % 2)] > val) {
        return rbsHelper(vals, lb, lb / 2 + ub / 2 + (lb % 2) * (ub % 2) - 1, val);
      } else {
        return rbsHelper(vals, lb / 2 + ub / 2 + (lb % 2) * (ub % 2) + 1, ub, val);
      } // if / else if / else
    } // if
    throw new Exception("Value does not exist in the array.");
  } // rbsHelper

  static public void reset() {
    count = 0;
  }

  static public int getLastTime() {
    return count;
  }

  public static <T> int binarySearch(T[] values, T value, Comparator<T> compare) throws Exception {
    reset();
    int min = 0;
    int max = values.length - 1;
    count++;
    while(min <= max) {
      count++;
      if (compare.compare(values[min / 2 + max / 2 + (min % 2) * (max % 2)] ,value) == 0) {
        return min / 2 + max / 2 + (min % 2) * (max % 2);
      } else if (compare.compare(values[min / 2 + max / 2 + (min % 2) * (max % 2)] ,value) > 0) {
        max = min / 2 + max / 2 + (min % 2) * (max % 2) - 1;
      } else {
        min = min / 2 + max / 2 + (min % 2) * (max % 2) + 1;
      } // if / else if / else
    } // while
    throw new Exception("Value does not exist in the array.");  
} // binarySearch


  // +----------------+----------------------------------------------
  // | Public methods |
  // +----------------+

  /**
   * Search values for the first value for which pred holds.
   *
   * @param <T> 
   *   The type of values we're examining
   * @param values
   *   The iterable we're searching
   * @param pred
   *   The predicate used to determine whether or not the value is
   *   acceptable
   * 
   * @return the first mathcing element.
   *
   * @throws Exception
   *   If no matching value is found.
   */
  public static <T> T search(Iterable<T> values, Predicate<? super T> pred) 
      throws Exception {
    for (T value : values) {
      if(pred.test(value)){
        return value;
      }
    } // for
    throw new Exception("Unimplemented");
  } // search(Iterable<T>, Predicate<? super T>)

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <=
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  public static int binarySearch(int[] vals, int val) throws Exception {
    return iterativeBinarySearch(vals, val);
    // return recursiveBinarySearch(vals, val);
  } // binarySearch

} // class SearchUtils
