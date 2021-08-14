package problem01.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Iterator class which can be utilized to return all Todos (within
 * a collection) of the same category as that provided.
 */
public class CategoryIterator implements Iterator<Todo> {

  private static final int STARTING_INDEX = 0;

  private String category;
  private List<Todo> todoList;
  private int currentIndex;

  /**
   * Generates a CategoryIterator object, utilizing the list that
   * will be iterated.
   *
   * @param category  - the category by which Todos will be filtered
   * @param todoList - the list to be iterated over, returning Todos belonging
   *                 to the provided category
   */
  public CategoryIterator(String category, List<Todo> todoList) {
    this.category = category;
    this.todoList = todoList;
    this.currentIndex = STARTING_INDEX;
  }

  /**
   * Determines whether there are remaining Todos (within the provided collection
   * of Todos) that belong to the specified category.
   *
   * @return boolean indicating whether there are remaining Todos (within the
   * provided collection of Todos) that belong to the specified category.
   */
  @Override
  public boolean hasNext() {
    if (this.currentIndex >= this.todoList.size()) {
      return false;
    }
    if (this.todoList.get(this.currentIndex).getCategory() != null &&
        this.todoList.get(this.currentIndex).getCategory().equalsIgnoreCase(this.category)) {
      return true;
    }
    else {
      this.currentIndex++;
      return this.hasNext();
    }
  }

  /**
   * Returns the next to-do (used hyphen to avoid yellow highlighting) in succession
   * of the provided collection that belongs to the specified category.
   *
   * @return the next to-do in succession of the provided collection that belongs
   * to the specified category.
   */
  @Override
  public Todo next() {
    Todo nextTodo = this.todoList.get(this.currentIndex);
    this.currentIndex++;
    return nextTodo;
  }

  /**
   * Determines whether a CategoryIterator object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a CategoryIterator object
   *
   * @return boolean indicating whether a CategoryIterator object is equivalent
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
    CategoryIterator that = (CategoryIterator) o;
    return this.currentIndex == that.currentIndex &&
        Objects.equals(this.category, that.category) &&
        Objects.equals(this.todoList, that.todoList);
  }

  /**
   * Determines the hashcode of a CategoryIterator object
   *
   * @return the hashcode of a CategoryIterator object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.category, this.todoList, this.currentIndex);
  }

  /**
   * Provides a string representation of a CategoryIterator object
   *
   * @return a string representation of a CategoryIterator object
   */
  @Override
  public String toString() {
    return "CategoryIterator {" +
        "category = '" + this.category + '\'' +
        ", todoList = " + this.todoList +
        ", currentIndex = " + this.currentIndex +
        '}';
  }
}
