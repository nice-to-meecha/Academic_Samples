package problem01.controller;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A To-do object containing all its relevant fields.
 */
public class Todo {

  private final String text;
  private Boolean completed;
  private final LocalDate dueDate;
  private final Integer priority;
  private final String category;
  private final Integer id;

  /**
   * Constructor for the class
   * @param text - a description of the task to be done. This field is required
   * @param completed - indicates whether the task is completed or incomplete. If not specified, this
   * field should be false by default.
   * @param dueDate - a due date. This field is optional.
   * @param priority - an integer indicating the priority of the todo. This field is optional. If the user
   * chooses to specify a priority, it must be between 1 and 3, with 1 being the highest
   * priority. If no priority is specified, the todo can be treated as lowest priority (i.e. 3).
   * @param category - a user-specified String that can be used to group related todos, e.g.
   * school, work, home. This field is optional.
   * @param id - ID number of the Todo
   */
  public Todo(String text, Boolean completed, LocalDate dueDate, Integer priority,
      String category, Integer id) {
    this.text = text;
    this.completed = completed;
    this.dueDate = dueDate;
    this.priority = priority;
    this.category = category;
    this.id = id;
  }

  /**
   * Returns the text field
   * @return - the text field
   */
  public String getText() {
    return this.text;
  }

  /**
   * Returns the completed field
   * @return - the completed field
   */
  public Boolean getCompleted() {
    return this.completed;
  }

  /**
   * Returns the due date field
   * @return - the due date field
   */
  public LocalDate getDueDate() {
    return this.dueDate;
  }

  /**
   * Returns the priority field
   * @return - the priority field
   */
  public Integer getPriority() {
    return this.priority;
  }

  /**
   * returns teh category field
   * @return - the category field
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Returns the ID field
   * @return - the ID field
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Sets the completed field, is used by the CompleteTodo task
   * @param completed - the completed field
   */
  public void setCompleted(Boolean completed) {
    this.completed = completed;
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
    Todo todo = (Todo) o;
    return Objects.equals(this.text, todo.text) &&
        Objects.equals(this.completed, todo.completed) &&
        Objects.equals(this.dueDate, todo.dueDate) &&
        Objects.equals(this.priority, todo.priority) &&
        Objects.equals(this.category, todo.category) &&
        Objects.equals(this.id, todo.id);
  }

  /**
   * Overrides the default hashCode() method, returns the hashcode of the object
   *
   * @return - hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.text, this.completed, this.dueDate, this.priority,
        this.category, this.id);
  }

  /**
   * Overrides the default toString() method, returns a string representation of the object
   *
   * @return - string representation of object
   */
  @Override
  public String toString() {
    return "Todo{" +
        "text='" + this.text + '\'' +
        ", completed=" + this.completed +
        ", dueDate=" + this.dueDate +
        ", priority=" + this.priority +
        ", category='" + this.category + '\'' +
        ", ID=" + this.id +
        '}';
  }
}
