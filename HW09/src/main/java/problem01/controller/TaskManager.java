package problem01.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import problem01.Task;
import problem01.view.DisplayTable;
import problem01.view.InvalidArgumentException;

/**
 * Acts as an invoker class in the Command design pattern. Stores a list of all tasks to be performed,
 * as well as methods for creating and performing these tasks.
 */
public class TaskManager implements ITaskManager {

  private static final Integer STARTING_ID = 0;
  private static final Integer MAX_NUM_TASK = 1;


  private List<Task> taskList;
  private List<Todo> todoList;
  Integer id;

  /**
   * Constructor for the class
   *
   * @param todoList - list of Todo items to be managed by the task Manager
   */
  public TaskManager(List<Todo> todoList) {
    this.todoList = todoList;
    this.taskList = new ArrayList<>();
    this.id = STARTING_ID;
  }

  /**
   * Generates unique IDs. Scans the list of Todos for the highest ID, begins creating new IDs
   * higher than that number.
   *
   * @return - unique ID number
   */
  private Integer generateID() {
    for (Todo todo : this.todoList) {
      if (todo.getId() > this.id) {
        this.id = todo.getId();
      }
    }
    this.id++;
    return this.id;
  }

  /**
   * Adds a task to the task list
   * @param task - task to be added
   */
  @Override
  public void addTask(Task task) {
    this.taskList.add(task);
  }

  /**
   * Performs argument validation on all the tasks in the task list
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  @Override
  public void validateArgsAllTasks() throws InvalidArgumentException {
    this.validateTaskList();
    for (Task task : this.taskList) {
      task.validateArgs(this.todoList);
    }
  }

  /**
   * Validates the taskList to ensure the limit to the number of tasks that can be performed is not
   * exceeded. Also moves displayTable objects to the back of the list, so that they are processed
   * last.
   *
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  private void validateTaskList() throws InvalidArgumentException {
    int addCount = 0;
    int displayCount = 0;
    for (int i = 0; i < this.taskList.size(); i++) {
      if (this.taskList.get(i) instanceof AddTodo) {
        addCount++;
      }
      if (this.taskList.get(i) instanceof DisplayTable) {
        this.taskList.add(this.taskList.get(i));
        this.taskList.remove(i);
        displayCount++;
      }
    }
    if (displayCount > MAX_NUM_TASK || addCount > MAX_NUM_TASK) {
      throw new InvalidArgumentException("Limits of tasks that can be performed:\n"
          + "--add-todo: 1\n--display: 1\n--complete-todo: no limit");
    }
  }

  /**
   * Performs all the tasks in the task list
   */
  public void performAllTasks() {
    for (Task task : this.taskList) {
      this.todoList = task.performTask(this.todoList);
    }
    this.taskList.clear();
  }

  /**
   * Creates a new AddTodo task. Takes in relevant arguments and uses the information to set the
   * appropriate fields of an AddTodo object using setters. Adds To-do object to the taskList.
   *
   * @param args - relevant command line arguments for creating this task
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  public void createAddTodo(HashMap <String, String> args) throws InvalidArgumentException {
    AddTodo addTodo = new AddTodo(this.generateID());
    if (args.containsKey(ArgConstants.TODO_TEXT)) {
      addTodo.setTodoText(args.get(ArgConstants.TODO_TEXT));
    }
    if (args.containsKey(ArgConstants.COMPLETED)) {
      addTodo.setCompleted(Boolean.parseBoolean(args.get(ArgConstants.COMPLETED)));
    }
    try {
      if (args.containsKey(ArgConstants.PRIORITY)) {
        addTodo.setPriority(Integer.valueOf(args.get(ArgConstants.PRIORITY)));
      }
    } catch (NumberFormatException e) {
      throw new InvalidArgumentException("--priority must be followed by 1, 2, or 3");
    }
    if (args.containsKey(ArgConstants.CATEGORY)) {
      addTodo.setCategory(args.get(ArgConstants.CATEGORY));
    }
    if (args.containsKey(ArgConstants.DUE)) {
      addTodo.setDueDate(args.get(ArgConstants.DUE));
    }
    this.addTask(addTodo);
  }

  /**
   * Creates a new CompleteTodo task. Takes in relevant arguments and uses the information to set the
   * appropriate fields of an CompleteTodo object using setters. Adds To-do object to the taskList.
   *
   * @param id - id of the to-do
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  public void createCompleteTodo(String id) throws InvalidArgumentException {
    try {
      CompleteTodo completeTodo = new CompleteTodo(Integer.valueOf(id));
      this.addTask(completeTodo);
    } catch (NumberFormatException e) {
      throw new InvalidArgumentException("--complete-todo must be followed by an id number");
    }
  }

  /**
   * Creates a new DisplayTable task. Takes in relevant arguments and uses the information to set the
   * appropriate fields of an DisplayTable object using setters. Adds To-do object to the taskList.
   *
   * @param args - relevant command line arguments for creating this task
   */
  public void createDisplayTable(HashMap<String, String> args) {
    DisplayTable displayTable = new DisplayTable();
    if (args.containsKey(ArgConstants.SHOW_INCOMPLETE)) {
      displayTable.setShowIncomplete(Boolean.parseBoolean(args.get(ArgConstants.SHOW_INCOMPLETE)));
    }
    if (args.containsKey(ArgConstants.SHOW_CATEGORY)) {
      displayTable.setShowCategory(args.get(ArgConstants.SHOW_CATEGORY));
    }
    if (args.containsKey(ArgConstants.SORT_BY_DATE)) {
      displayTable.setSortByDate(Boolean.parseBoolean(args.get(ArgConstants.SORT_BY_DATE)));
    }
    if (args.containsKey(ArgConstants.SORT_BY_PRIORITY)) {
      displayTable.setSortByPriority(Boolean.parseBoolean(args.get(ArgConstants.SORT_BY_PRIORITY)));
    }
    this.addTask(displayTable);
  }

  /**
   * Returns the task list
   * @return - the task list field
   */
  public List<Task> getTaskList() {
    return this.taskList;
  }

  /**
   * Returns the ID
   * @return the ID field
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Returns the todo list field
   *
   * @return the todo list
   */
  public List<Todo> getTodoList() {
    return this.todoList;
  }

  /**
   * Sets the ID field. Should only be used for testing purposes
   *
   * @param id - ID field
   */
  public void setId(Integer id) {
    this.id = id;
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
    TaskManager that = (TaskManager) o;
    return Objects.equals(this.taskList, that.taskList) &&
        Objects.equals(this.todoList, that.todoList);
  }

  /**
   * Overrides the default hashCode() method, returns the hashcode of the object
   *
   * @return - hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.taskList, this.todoList);
  }

  /**
   * Overrides the default toString() method, returns a string representation of the object
   *
   * @return - string representation of object
   */
  @Override
  public String toString() {
    return "TaskManager{" +
        "taskList=" + this.taskList +
        ", todoList=" + this.todoList +
        ", ID=" + this.id +
        '}';
  }
}
