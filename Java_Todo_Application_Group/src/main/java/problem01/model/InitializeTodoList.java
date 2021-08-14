package problem01.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import problem01.controller.Parser;
import problem01.controller.TaskManager;
import problem01.controller.Todo;
import problem01.view.InvalidArgumentException;

/**
 * Class which generates a To-do list, based on a provided Map, retaining
 * all information to be stored within a To-do object.
 */
public class InitializeTodoList implements IInitializeTodoList {

  private static final int TOTAL_COLUMNS = 6;
  private static final int COMPLETED_INDEX = 2;
  private static final int ARRAY_INITIALIZER = 1000;
  private static final String[] CLI_FLAGS = new String[]{"n/a","--todo-text", "--completed",
  "--due", "--priority", "--category"};

  private Map<String, ArrayList<String>> todoMap;
  private List<Integer> headerColumnOrder;

  /**
   * Generates an InitializeTodoList object, utilizing a Map retaining all information
   * previously stored within a csv file as well as the column order of the file.
   *
   * @param todoMap - the map retaining all information garnered from a csv file
   * @param headerColumnOrder - the order of headers of the csv file
   */
  public InitializeTodoList(Map<String, ArrayList<String>> todoMap,
      List<Integer> headerColumnOrder) {
    this.todoMap = todoMap;
    this.headerColumnOrder = headerColumnOrder;
  }

  /**
   * Generates a List of Todos, based on the provided information stored within
   * a csv file.
   *
   * @return To-do list, retaining information previously stored within a csv file
   *
   * @throws InvalidArgumentException - thrown when an inappropriate entry is provided
   * for an associated To-do field.
   */
  @Override
  public List<Todo> parseCSVEntries() throws InvalidArgumentException {
    List<String[]> addTodoArgs = this.generateArgumentList();
    List<Todo> todoList = new ArrayList<>();
    for (String[] args : addTodoArgs) {
      int index = 0;
      while (args[index] != null) {
        index++;
      }
      Parser parser = new Parser(Arrays.copyOf(args, index));
      parser.setTaskManager(todoList);
      TaskManager taskManager = parser.parseCommandLine();
      taskManager.validateArgsAllTasks();
      taskManager.performAllTasks();
    }
    return todoList;
  }

  /**
   * Generates an argument list, mimicking that of the command line, so as to utilize
   * the Parser and TaskManager to check csv file entries.
   *
   * @return a List of String Arrays, each bearing CLI-like elements, to create Todos, using
   * the Parser and TaskManager.
   */
  private List<String[]> generateArgumentList() {
    List<String[]> addTodoArgsList = new ArrayList<>();
    List<String> idList = new ArrayList<>(this.todoMap.keySet());
    int i, loopStart = 1;
    for (String id : idList) {
      String[] addTodoArgs = new String[ARRAY_INITIALIZER];
      int argumentIndex = 0;
      addTodoArgs[argumentIndex++] = "--add-todo";
      if (!id.equals("header")) {
        for (i = loopStart; i < TOTAL_COLUMNS; i++) {
          String entry = this.todoMap.get(id).get(i);
          if (!entry.equals("?") && !entry.equals("false")) {
            addTodoArgs[argumentIndex++] = (CLI_FLAGS[i]);
            if (i != COMPLETED_INDEX) {
              addTodoArgs[argumentIndex++] = (this.todoMap.get(id).get(
                  this.headerColumnOrder.get(i)));
            }
          }
        }
      }
      int secondArgument = 1;
      if (addTodoArgs[secondArgument] != null) {
        addTodoArgsList.add(addTodoArgs);
      }
    }
    return addTodoArgsList;
  }

  /**
   * Determines whether an InitializeTodoList object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with an InitializeTodoList object
   *
   * @return boolean indicating whether an InitializeTodoList object is equivalent
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
    InitializeTodoList that = (InitializeTodoList) o;
    return Objects.equals(this.todoMap, that.todoMap) &&
        Objects.equals(this.headerColumnOrder, that.headerColumnOrder);
  }

  /**
   * Determines the hashcode of an InitializeTodoList object
   *
   * @return the hashcode of an InitializeTodoList object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.todoMap, this.headerColumnOrder);
  }

  /**
   * Provides a string representation of an InitializeTodoList object
   *
   * @return a string representation of an InitializeTodoList object
   */
  @Override
  public String toString() {
    return "InitializeTodoList {" +
        "todoMap = " + this.todoMap +
        ", headerColumnOrder = " + this.headerColumnOrder +
        '}';
  }

  /**
   * Returns the map retaining all information garnered from a csv file
   *
   * @return the map retaining all information garnered from a csv file
   */
  public Map<String, ArrayList<String>> getTodoMap() {
    return this.todoMap;
  }

  /**
   * Returns the provided order of known header columns.
   *
   * @return the provided order of known header columns.
   */
  public List<Integer> getHeaderColumnOrder() {
    return this.headerColumnOrder;
  }
}
