package problem01.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import problem01.controller.Todo;
import problem01.view.InvalidNullArgumentException;

/**
 * Class which writes all stored To-do information within a provided
 * .csv file.
 */
public class WriteCSV implements IWriteCSV {

  private static final String[] TODO_GETTERS = new String[]{"getId", "getText",
      "getCompleted", "getDueDate", "getPriority", "getCategory"};
  private static final String[] HEADER_TITLES = new String[]{"id", "text",
      "completed", "due", "priority", "category"};
  private static final DateTimeFormatter DATE_FORMATTER =
      DateTimeFormatter.ofPattern("M/d/uuuu");
  private static final int TOTAL_COLUMNS = 6;
  private static final int DATE_COLUMN = 3;

  private List<Todo> todoList;
  private List<Integer> headerColumnOrder;

  /**
   * Generates a WriteCSV object, utilizing the list of Todos to be written
   * to a .csv file as well as the order of columns, in which to write the
   * information.
   *
   * @param todoList - the list of Todos to be written to a .csv file
   * @param headerColumnOrder - the order of columns, in which the To-do information
   *                          will be written
   *
   * @throws InvalidNullArgumentException - thrown when a null argument is utilized
   * in unsupported fashion.
   */
  public WriteCSV(List<Todo> todoList, List<Integer> headerColumnOrder)
      throws InvalidNullArgumentException{
    CheckNullUtils.evaluate(todoList);
    CheckNullUtils.evaluate(headerColumnOrder);

    this.todoList = todoList;
    this.headerColumnOrder = headerColumnOrder;
  }

  /**
   * Fills a csv with previously stored and/or altered information,
   * regarding Todos.
   *
   * @param csv - the file to which the Todos -- newly generated or altered --
   *                will be written
   *
   * @throws IOException - input/output exception, which is thrown if an error is
   * encountered while writing to newFile
   * @throws InvalidNullArgumentException - thrown when a null argument is utilized
   * in unsupported fashion.
   * @throws NoSuchMethodException - thrown if a method name utilized within getMethod()
   * is non-existent.
   * @throws InvocationTargetException - thrown if the method called by invoke() throws
   * an exception.
   * @throws IllegalAccessException - thrown if the method called by invoke() is
   * inaccessible.
   */
  @Override
  public void writeContentsToCSV(BufferedWriter csv) throws IOException,
      InvalidNullArgumentException, NoSuchMethodException, InvocationTargetException,
      IllegalAccessException {
    CheckNullUtils.evaluate(csv);

    this.writeHeaderToCSV(csv);

    int i, startCount = 0, avoidCommaAtEnd = 1;
    for (Todo todo: this.todoList) {
      i = startCount;
      StringBuilder row = new StringBuilder();
      for (Integer columnIndex : this.headerColumnOrder) {
        String entry = this.checkEntry(todo, columnIndex);
        row.append("\"").append(entry).append("\"");
        if (i < TOTAL_COLUMNS - avoidCommaAtEnd) {
          row.append(",");
        }
        i++;
      }
      csv.write(row + "\n");
    }
  }

  /**
   * Provides the appropriate String representation of a To-do field, in order to
   * write it to a csv file. If unknown (null), the field is stored as a question
   * mark (?).
   *
   * @param todo - the Todo object whose field is to be assessed for storage purposes
   * @param columnIndex - the specific column with which this information is associated
   *
   * @return appropriate String representation of a To-do field, specifically for storage
   * within a csv file.
   *
   * @throws NoSuchMethodException - thrown if a method name utilized within getMethod()
   * is non-existent.
   * @throws InvocationTargetException - thrown if the method called by invoke() throws
   * an exception.
   * @throws IllegalAccessException - thrown if the method called by invoke() is
   * inaccessible.
   */
  private String checkEntry(Todo todo, Integer columnIndex) throws NoSuchMethodException,
      InvocationTargetException, IllegalAccessException{
    String entry;
    Method method = Todo.class.getMethod(TODO_GETTERS[columnIndex]);
    if (columnIndex == DATE_COLUMN && method.invoke(todo) != null) {
      entry = todo.getDueDate().format(DATE_FORMATTER);
    }
    else {
      entry = method.invoke(todo) == null ? "?" : method.invoke(todo).toString();
    }
    return entry;
  }

  /**
   * Writes the row containing header information to the provided .csv file
   *
   * @param csv - the file to which the Todos -- newly generated or altered --
   *                will be written
   *
   * @throws IOException - input/output exception, which is thrown if an error is
   * encountered while writing to newFile
   */
  private void writeHeaderToCSV(BufferedWriter csv) throws IOException {
    int i = 0, avoidCommaAtEnd = 1;
    StringBuilder row = new StringBuilder();
    for (Integer columnIndex: this.headerColumnOrder) {
      row.append("\"").append(HEADER_TITLES[columnIndex]).append("\"");
      if (i < TOTAL_COLUMNS - avoidCommaAtEnd) {
        row.append(",");
      }
      i++;
    }
    csv.write(row + "\n");
  }

  /**
   * Determines whether a WriteCSV object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a WriteCSV object
   *
   * @return boolean indicating whether a WriteCSV object is equivalent
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
    WriteCSV writeCSV = (WriteCSV) o;
    return Objects.equals(this.todoList, writeCSV.todoList) &&
        Objects.equals(this.headerColumnOrder, writeCSV.headerColumnOrder);
  }

  /**
   * Determines the hashcode of a WriteCSV object
   *
   * @return the hashcode of a WriteCSV object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.todoList, this.headerColumnOrder);
  }

  /**
   * Provides a string representation of a WriteCSV object
   *
   * @return a string representation of a WriteCSV object
   */
  @Override
  public String toString() {
    return "WriteCSV {" +
        "todoList = " + this.todoList +
        ", headerColumnOrder = " + this.headerColumnOrder +
        '}';
  }
}
