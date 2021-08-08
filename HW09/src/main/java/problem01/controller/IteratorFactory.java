package problem01.controller;

import java.util.Iterator;
import java.util.List;

/**
 * Class which makes use of the Factory pattern to produce Iterators
 * meant to filter through collections of Todos, based on completeness
 * or category.
 */
public class IteratorFactory {

  /**
   * Generates an empty IteratorFactory object
   */
  public IteratorFactory() {}

  /**
   * Generates the appropriate Iterator type, according to the provided
   * aspect by which the collection of Todos should be filtered.
   *
   * @param filter - the aspect of the Todos, for which matching Todos will be selected
   * @param todoList - the list of Todos that will be sifted through, according
   *                 to the provided filter
   *
   * @return an Iterator capable of filtering a collection of Todos, based on
   * the provided filter. Returns null if the provided filter is null.
   */
  public static Iterator<Todo> createIterator(String filter, List<Todo> todoList) {
    if (filter.equalsIgnoreCase("incomplete")) {
      return new IncompleteIterator(todoList);
    }
    return new CategoryIterator(filter, todoList);
  }

  /**
   * Determines whether an IteratorFactory object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with an IteratorFactory object
   *
   * @return boolean indicating whether an IteratorFactory object is equivalent
   * to another object
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  /**
   * Determines the hashcode of an IteratorFactory object
   *
   * @return the hashcode of an IteratorFactory object
   */
  @Override
  public int hashCode() {
    return 73;
  }

  /**
   * Provides a string representation of an IteratorFactory object
   *
   * @return a string representation of an IteratorFactory object
   */
  @Override
  public String toString() {
    return "IteratorFactory{}";
  }
}
