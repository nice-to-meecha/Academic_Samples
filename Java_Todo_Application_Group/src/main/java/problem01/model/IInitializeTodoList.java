package problem01.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import problem01.controller.Todo;
import problem01.view.InvalidArgumentException;

/**
 * Interface describing the public functionality of an InitializeTodoList object,
 * comprising of the ability to generate a List of Todos from a provided Map,
 * storing relevant information.
 */
public interface IInitializeTodoList {

  /**
   * Generates a List of Todos, based on the provided information stored within
   * a csv file.
   *
   * @return To-do list, retaining information previously stored within a csv file
   *
   * @throws InvalidArgumentException - thrown when an inappropriate entry is provided
   * for an associated To-do field.
   */
  List<Todo> parseCSVEntries() throws InvalidArgumentException;

}
