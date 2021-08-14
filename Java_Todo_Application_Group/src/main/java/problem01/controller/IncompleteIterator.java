package problem01.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Iterator class which can be utilized to return all Todos (within
 * a collection) that are considered "incomplete".
 */
public class IncompleteIterator implements Iterator<Todo> {

  private static final int STARTING_INDEX = 0;

  private List<Todo> todoList;
  private int currentIndex;

  /**
   * Generates an IncompleteIterator object, utilizing the list that
   * will be iterated.
   *
   * @param todoList - the list to be iterated over, returning incomplete Todos
   */
  public IncompleteIterator(List<Todo> todoList) {
    this.todoList = todoList;
    this.currentIndex = STARTING_INDEX;
  }

  /**
   * Determines whether there are remaining Todos (within the provided collection
   * of Todos) that are considered as incomplete.
   *
   * @return boolean indicating whether there are remaining Todos (within the
   * provided collection of Todos) that are considered as incomplete.
   */
  @Override
  public boolean hasNext() {
    if (this.currentIndex >= this.todoList.size()) {
      return false;
    }
    if (this.todoList.get(this.currentIndex).getCompleted().equals(false)) {
      return true;
    }
    else {
      this.currentIndex++;
      return this.hasNext();
    }
  }

  /**
   * Returns the next to-do (used hyphen to avoid yellow highlighting) in succession
   * of the provided collection that is considered as incomplete.
   *
   * @return the next to-do in succession of the provided collection that is
   * considered as incomplete.
   */
  @Override
  public Todo next() {
    Todo nextTodo = this.todoList.get(this.currentIndex);
    this.currentIndex++;
    return nextTodo;
  }

  /**
   * Determines whether a IncompleteIterator object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a IncompleteIterator object
   *
   * @return boolean indicating whether a IncompleteIterator object is equivalent
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
    IncompleteIterator that = (IncompleteIterator) o;
    return this.currentIndex == that.currentIndex &&
        Objects.equals(this.todoList, that.todoList);
  }

  /**
   * Determines the hashcode of a IncompleteIterator object
   *
   * @return the hashcode of a IncompleteIterator object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.todoList, this.currentIndex);
  }

  /**
   * Provides a string representation of a IncompleteIterator object
   *
   * @return a string representation of a IncompleteIterator object
   */
  @Override
  public String toString() {
    return "IncompleteIterator {" +
        "todoList = " + this.todoList +
        ", currentIndex = " + this.currentIndex +
        '}';
  }
}
