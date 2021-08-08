package problem01;

import java.util.List;
import problem01.controller.Todo;
import problem01.view.InvalidArgumentException;

public interface Task {

  /**
   * Performs the current task, using the appropriate fields.
   *
   * @param todoList list of Todos being managed
   * @return - list of Todos updated after current task performed
   */
  List<Todo> performTask(List<Todo> todoList);

  /**
   * Checks that the given arguments for the task are valid.
   *
   * @param todoList - list of Todo items
   * @throws InvalidArgumentException - Thrown if combination of args are invalid
   */
  void validateArgs(List<Todo> todoList) throws InvalidArgumentException;
}
