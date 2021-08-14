package problem01.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import problem01.Task;
import problem01.view.InvalidArgumentException;

/**
 * A task for adding a to-do item.
 */
public class AddTodo implements Task {

  private static final Integer MIN_PRIO = 1;
  private static final Integer MAX_PRIO = 3;

  private String todoText;
  private boolean completed;
  private String dueDate;
  private LocalDate dueLocalDate;
  private Integer priority;
  private String category;
  private Integer id;

  /**
   * Constructor for the class
   *
   * @param id - ID field of the Todo to be added
   */
  public AddTodo(Integer id) {
    this.todoText = "";
    this.completed = false;
    this.dueDate = null;
    this.priority = null;
    this.category = null;
    this.dueLocalDate = null;
    this.id = id;
  }

  /**
   * Sets the todoTest description field
   * @param todoText - the description of the Todo to be added
   */
  public void setTodoText(String todoText) {
    this.todoText = todoText;
  }

  /**
   * Sets the completed field
   * @param completed - the completed field
   */
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  /**
   * Sets the dueDate field
   * @param dueDate - the dueDate field
   */
  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * Sets the category field
   * @param category - the category field
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Sets the priority field
   * @param priority - the priority field
   */
  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  /**
   * Returns the priority field
   * @return - the priority field
   */
  public Integer getPriority() {
    return this.priority;
  }

  /**
   * Returns the category field
   * @return - the category field
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Returns the todoText field
   * @return - the todoText field
   */
  public String getTodoText() {
    return this.todoText;
  }

  /**
   * Returns the completed field
   * @return - the completed field
   */
  public boolean isCompleted() {
    return this.completed;
  }

  /**
   * Returns the dueDate field
   * @return - due date of Todo
   */
  public String getDueDate() {
    return this.dueDate;
  }

  /**
   * Returns the dueLocalDate field. This field is initially assigned to null, is set to a
   * proper LocalDate object after the string deuDate is converted to LocalDate in validateArgs()
   * @return - the dueLocalDate field
   */
  public LocalDate getDueLocalDate() {
    return this.dueLocalDate;
  }

  /**
   * Returns the ID field
   * @return the ID field
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Performs the current task, using the appropriate fields.
   * Adds a new Todo to a List of Todos passed in, returns the new updated List
   *
   * @param todoList list of Todos being managed
   * @return - list of Todos updated after current task performed
   */
  @Override
  public List<Todo> performTask(List<Todo> todoList) {
    Todo todo = new Todo(this.todoText, this.completed, this.dueLocalDate, this.priority, this.category,
        this.id);
    todoList.add(todo);
    return todoList;
  }

  /**
   * Checks that the given arguments for the task are valid.
   * @throws InvalidArgumentException - Thrown if combination of args are invalid
   */
  @Override
  public void validateArgs(List<Todo> todoList) throws InvalidArgumentException {
    if (this.dueDate!= null) {
      try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        this.dueLocalDate = LocalDate.parse(this.dueDate, formatter);
      } catch (DateTimeParseException e) {
        throw new InvalidArgumentException("For the provided due date, must be a valid date in the"
            + "format mm/dd/yyyy");
      }
    }

    if (this.priority != null) {
      if (this.priority < MIN_PRIO || this.priority > MAX_PRIO) {
        throw new InvalidArgumentException("Priority can only be se to 1, 2, or 3");
      }
    }

    if (this.todoText.equals("")) {
      throw new InvalidArgumentException("--todo-text <description of todo> is required if --add-todo "
          + "is provided");
    }
  }

  /**
   * Overrides the default equals() method to compare param object to this object
   *
   * @param o - object for comparison
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
    AddTodo addTodo = (AddTodo) o;
    return this.completed == addTodo.completed &&
        Objects.equals(this.todoText, addTodo.todoText) &&
        Objects.equals(this.dueDate, addTodo.dueDate) &&
        Objects.equals(this.dueLocalDate, addTodo.dueLocalDate) &&
        Objects.equals(this.priority, addTodo.priority) &&
        Objects.equals(this.category, addTodo.category) &&
        Objects.equals(this.id, addTodo.id);
  }

  /**
   * Overrides the default hashCode() method, returns the hashcode of the object
   *
   * @return - hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.todoText, this.completed, this.dueDate,
        this.dueLocalDate, this.priority, this.category);
  }

  /**
   * Overrides the default toString() method, returns a string representation of the object
   *
   * @return - string representation of object
   */
  @Override
  public String toString() {
    return "AddTodo{" +
        "todoText='" + this.todoText + '\'' +
        ", completed=" + this.completed +
        ", dueDate='" + this.dueDate + '\'' +
        ", dueLocalDate=" + this.dueLocalDate +
        ", priority=" + this.priority +
        ", category='" + this.category + '\'' +
        ", ID=" + this.id +
        '}';
  }
}
