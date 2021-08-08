package problem01.controller;

import java.util.List;
import java.util.Objects;
import problem01.Task;
import problem01.view.InvalidArgumentException;

/**
 * A task for completing a to-do item.
 */
public class CompleteTodo implements Task {

  private Integer id;

  /**
   * Constructor for the class
   * @param id - ID of Todo to be searched for from a list
   */
  public CompleteTodo(Integer id) {
    this.id = id;
  }

  /**
   * Returns the ID field of the class
   * @return - the ID field
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Performs the current task, using the appropriate fields.
   * Searches through a list of Todos for a Todo with a specific ID. If found, sets that Todo's
   * completed field to true;
   *
   * @param todoList list of Todos being managed
   * @return - list of Todos updated after current task performed
   */
  @Override
  public List<Todo> performTask(List<Todo> todoList) {
    for (Todo todo : todoList) {
      if (todo.getId().equals(this.id)) {
        todo.setCompleted(true);
      }
    }
    return todoList;
  }

  /**
   * Checks that the given arguments for the task are valid.
   *
   * @throws InvalidArgumentException - Thrown if combination of args are invalid
   */
  @Override
  public void validateArgs(List<Todo> todoList) throws InvalidArgumentException {
    Boolean hasID = false;
    for (Todo todo : todoList) {
      if (todo.getId().equals(this.id)) {
        hasID = true;
      }
    }
    if (!hasID) {
      throw new InvalidArgumentException("For completing a todo, there was no todo with the"
          + "ID provided.");
    }
  }

  /**
   * Overrides the default equals() method to compare param object to this object
   *
   * @param o- object for comparison
   * @return - whether the objects are equal or not
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    CompleteTodo that = (CompleteTodo) o;
    return Objects.equals(this.id, that.id);
  }

  /**
   * Overrides the default hashCode() method, returns the hashcode of the object
   *
   * @return - hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  /**
   * Overrides the default toString() method, returns a string representation of the object
   *
   * @return - string representation of object
   */
  @Override
  public String toString() {
    return "CompleteTodo{" +
        "id=" + this.id +
        '}';
  }
}
