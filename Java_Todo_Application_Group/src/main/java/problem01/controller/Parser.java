package problem01.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import problem01.model.ValidateInput;
import problem01.view.InvalidArgumentException;

/**
 * The Parser class is responsible for parsing command line arguments and passing the pertinent
 * information to methods in the TaskManager for performing tasks.
 */
public class Parser implements IParser {

  private static final Integer NEXT_PREV_ARG = 1;

  private String[] args;
  private TaskManager taskManager;
  private ValidateInput validate;
  private String pathToFile;

  private Boolean addTodo;
  private Boolean completeTodo;
  private Boolean displayTodo;

  /**
   * Constructor for the class
   *
   * @param args - command line arguments
   */
  public Parser(String[] args) {
    this.args = args;
    this.taskManager = new TaskManager(new ArrayList<>());
    this.validate = new ValidateInput(this.args);
    this.pathToFile = "";

    this.addTodo = false;
    this.completeTodo = false;
    this.displayTodo = false;
  }


  /**
   * Returns a TaskManager object containing the tasks needed to be performed. Parses through the arguments
   * given, passes relevant command line arguments helper methods which pass relevant information to
   * methods in TaskManager
   *
   * @return - TaskManager containing list of all tasks
   * @throws InvalidArgumentException - thrown if Arguments found invalid
   */
  @Override
  public TaskManager parseCommandLine() throws InvalidArgumentException {
    for (int i = 0; i < this.args.length; i++) {
      if (this.args[i].equalsIgnoreCase(ArgConstants.ADD_TODO)) {
        this.checkAddArgs();
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.COMPLETE_TODO)) {
        this.checkCompleteArgs(i);
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.DISPLAY)) {
        this.checkDisplayArgs();
      }
    }
    this.validate.checkNoTask(this.addTodo, this.completeTodo, this.displayTodo);
    return this.taskManager;
  }

  /**
   * Checks the arguments provided related to the AddTodo task. Passes these arguments in an
   * organized HashMap to the TaskManager.
   *
   * @throws InvalidArgumentException - thrown if invalid arguments found
   */
  private void checkAddArgs() throws InvalidArgumentException{
    HashMap<String, String> addTodoArgs = new HashMap<>();

    for (int i = 0; i < this.args.length; i++) {
      if (this.args[i].equalsIgnoreCase(ArgConstants.TODO_TEXT)) {
        this.validate.checkFollowingArg(i, ArgConstants.TODO_TEXT, "<description of todo>");
        addTodoArgs.put(ArgConstants.TODO_TEXT, this.args[i + NEXT_PREV_ARG]);
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.COMPLETED)) {
        addTodoArgs.put(ArgConstants.COMPLETED, "true");
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.DUE)) {
        this.validate.checkFollowingArg(i, ArgConstants.DUE, "<due date>");
        addTodoArgs.put(ArgConstants.DUE, this.args[i + NEXT_PREV_ARG]);
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.PRIORITY)) {
        this.validate.checkFollowingArg(i, ArgConstants.PRIORITY, "<1, 2, or 3>");
        addTodoArgs.put(ArgConstants.PRIORITY, this.args[i + NEXT_PREV_ARG]);
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.CATEGORY)) {
        this.validate.checkFollowingArg(i, ArgConstants.CATEGORY, "<category>");
        addTodoArgs.put(ArgConstants.CATEGORY, this.args[i + NEXT_PREV_ARG]);
      }
    }
    this.addTodo = true;
    this.taskManager.createAddTodo(addTodoArgs);
  }

  /**
   * Checks the arguments related to the CompleteTodo task. Passes this information to the
   * task manager.
   *
   * @param i - location of arg in the String[]
   * @throws InvalidArgumentException - thrown if invalid arguments found
   */
  private void checkCompleteArgs(Integer i) throws InvalidArgumentException{
    this.validate.checkFollowingArg(i, ArgConstants.COMPLETE_TODO, "<id>");
    this.completeTodo = true;
    this.taskManager.createCompleteTodo(this.args[i + NEXT_PREV_ARG]);
  }

  /**
   * Checks the arguments provided related to the DisplayTodos task. Passes these arguments in an
   * organized HashMap to the TaskManager.
   *
   * @throws InvalidArgumentException - thrown if invalid arguments found
   */
  private void checkDisplayArgs() throws InvalidArgumentException {
    HashMap<String, String> createDisplayTableArgs = new HashMap<>();
    for (int i = 0; i < this.args.length; i++) {
      if (this.args[i].equalsIgnoreCase(ArgConstants.SHOW_INCOMPLETE)) {
        createDisplayTableArgs.put(ArgConstants.SHOW_INCOMPLETE, "true");
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.SHOW_CATEGORY)) {
        this.validate.checkFollowingArg(i, ArgConstants.SHOW_CATEGORY, "<category>");
        createDisplayTableArgs.put(ArgConstants.SHOW_CATEGORY, this.args[i + NEXT_PREV_ARG]);
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.SORT_BY_DATE)) {
        createDisplayTableArgs.put(ArgConstants.SORT_BY_DATE, "true");
      } else if (this.args[i].equalsIgnoreCase(ArgConstants.SORT_BY_PRIORITY)) {
        createDisplayTableArgs.put(ArgConstants.SORT_BY_PRIORITY, "true");
      }
    }
    this.displayTodo = true;
    this.taskManager.createDisplayTable(createDisplayTableArgs);
  }

  /**
   * Sets the taskManager field
   * @param todoList - list of Todos to pass to the TaskManager
   */
  public void setTaskManager(List<Todo> todoList) {
    this.taskManager = new TaskManager(todoList);
  }

  /**
   * Returns the taskManager field
   * @return - the taskManager field
   */
  public TaskManager getTaskManager() {
    return this.taskManager;
  }

  /**
   * Returns the path to file field
   *
   * @return - the path to file field
   * @throws InvalidArgumentException - thrown if invalid arguments found
   */
  public String getPathToFile() throws InvalidArgumentException {
    this.pathToFile = this.validate.checkCSV();
    return this.pathToFile;
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
    Parser parser = (Parser) o;
    return Arrays.equals(this.args, parser.args) &&
        Objects.equals(this.addTodo, parser.addTodo) &&
        Objects.equals(this.completeTodo, parser.completeTodo) &&
        Objects.equals(this.displayTodo, parser.displayTodo) &&
        Objects.equals(this.taskManager, parser.taskManager) &&
        Objects.equals(this.validate, parser.validate) &&
        Objects.equals(this.pathToFile, parser.pathToFile);
  }

  /**
   * Overrides the default hashCode() method, returns the hashcode of the object
   *
   * @return - hashcode of the object
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(this.taskManager, this.validate, this.pathToFile, this.addTodo,
            this.completeTodo, this.displayTodo);
    result = 31 * result + Arrays.hashCode(this.args);
    return result;
  }

  /**
   * Overrides the default toString() method, returns a string representation of the object
   *
   * @return - string representation of object
   */
  @Override
  public String toString() {
    return "Parser{" +
        "args=" + Arrays.toString(this.args) +
        ", taskManager=" + this.taskManager +
        ", validate=" + this.validate +
        ", pathToFile='" + this.pathToFile + '\'' +
        ", addTodo=" + this.addTodo +
        ", completeTodo=" + this.completeTodo +
        ", displayTodo=" + this.displayTodo +
        '}';
  }
}
