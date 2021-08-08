package problem01.controller;

import problem01.view.InvalidArgumentException;

/**
 * Interface describing the public functionality of a Parser object,
 * comprising of the ability to parse CLI arguments.
 */
public interface IParser {

  /**
   * Returns a TaskManager object containing the tasks needed to be performed. Parses through the arguments
   * given, passes relevant command line arguments helper methods which pass relevant information to
   * methods in TaskManager
   *
   * @return - TaskManager containing list of all tasks
   * @throws InvalidArgumentException - thrown if Arguments found invalid
   */
  TaskManager parseCommandLine() throws InvalidArgumentException;

}
