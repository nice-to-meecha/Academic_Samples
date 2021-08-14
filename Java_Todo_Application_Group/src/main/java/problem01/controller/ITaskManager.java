package problem01.controller;

import java.util.HashMap;
import problem01.Task;
import problem01.view.InvalidArgumentException;

/**
 * Interface describing the public functionality of a Task object,
 * including the ability generate an assortment of tasks (add To-do,
 * complete To-do, display To-do), as well as perform them.
 */
public interface ITaskManager {

  /**
   * Adds a task to the task list
   * @param task - task to be added
   */
  void addTask(Task task);

  /**
   * Performs argument validation on all the tasks in the task list
   *
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  void validateArgsAllTasks() throws InvalidArgumentException;

  /**
   * Performs all the tasks in the task list
   */
  void performAllTasks();

  /**
   * Creates a new AddTodo task. Takes in relevant arguments and uses the information to set the
   * appropriate fields of an AddTodo object using setters. Adds To-do object to the taskList.
   *
   * @param args - relevant command line arguments for creating this task
   *
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  void createAddTodo(HashMap<String, String> args) throws InvalidArgumentException;

  /**
   * Creates a new CompleteTodo task. Takes in relevant arguments and uses the information to set the
   * appropriate fields of an CompleteTodo object using setters. Adds To-do object to the taskList.
   *
   * @param id - id of the to-do
   *
   * @throws InvalidArgumentException - thrown if arguments are found to be invalid
   */
  void createCompleteTodo(String id) throws InvalidArgumentException;

  /**
   * Creates a new DisplayTable task. Takes in relevant arguments and uses the information to set the
   * appropriate fields of an DisplayTable object using setters. Adds To-do object to the taskList.
   *
   * @param args - relevant command line arguments for creating this task
   */
  void createDisplayTable(HashMap<String, String> args);
}
