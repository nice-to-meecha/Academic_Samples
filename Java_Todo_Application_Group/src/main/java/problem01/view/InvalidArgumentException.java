package problem01.view;

/**
 * Exception extension, which throws exceptions when inappropriate arguments
 * are utilized in a multitude of situations.
 */
public class InvalidArgumentException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.  The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the {@link
   *                #getMessage()} method.
   */
  public InvalidArgumentException(String message) {
    super(message
        + "\nUsage:\n\n--csv-file <path/to/file> The CSV file containing the todos. "
        + "This option is required.\n"
        + "--add-todo Add a new todo. If this option is provided, then --todo-text must "
        + "also be provided.\n"
        + "--todo-text <description of todo> A description of the todo.\n"
        + "--completed (Optional) Sets the completed status of a new todo to true.\n"
        + "--due <due date> (Optional) Sets the due date of a new todo.Date must be in the format "
        + "MM/DD/YYYY\n"
        + "--priority <1, 2, or 3> (Optional) Sets the priority of a new todo. The value can be"
        + " 1, 2, or 3.\n"
        + "--category <a category name> (Optional) Sets the category of a new todo. The value can\n"
        + "be any String. Categories do not need to be pre-defined.\n"
        + "--complete-todo <id> Mark the Todo with the provided ID as complete.\n"
        + "--display Display todos. If none of the following optional arguments are "
        + "provided, displays all todos.\n"
        + "--show-incomplete (Optional) If --display is provided, only incomplete todos "
        + "should be displayed.\n"
        + "--show-category <category> (Optional) If --display is provided, only todos with the "
        + "given category should be displayed.\n"
        + "--sort-by-date (Optional) If --display is provided, sort the list of todos\n"
        + "by date order (ascending). Cannot be combined with --sort-by-priority.\n"
        + "--sort-by-priority (Optional) If --display is provided, sort the list of todos\n"
        + "by priority (ascending). Cannot be combined with --sort-by-date.\n"
    );
  }
}
