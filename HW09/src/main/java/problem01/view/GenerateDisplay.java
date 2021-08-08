package problem01.view;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import problem01.controller.Todo;

/**
 * Class which displays a collection of Todos within a user-friendly format.
 */
public class GenerateDisplay implements IGenerateDisplay {
  private static final int TOTAL_COLUMNS = 6;
  private static final DateTimeFormatter DATE_FORMATTER =
      DateTimeFormatter.ofPattern("M/d/uuuu");
  private static final ArrayList<String> HEADER = new ArrayList<>(
      Arrays.asList("ID", "Text", "Completed", "Due Date", "Priority", "Category"));
  private static final int[] HEADER_COLUMN_LENGTHS = new int[]{2, 4, 9, 8, 8, 8};

  private final List<Todo> todoList;
  private List<ArrayList<String>> tokenizedTodos;


  /**
   * Produces a GenerateDisplay object, utilizing the List of Todos that will
   * be displayed. The tokenizedTodos are initialized as well.
   *
   * @param todoList - the List of todos which will be displayed in a user-friendly format
   */
  public GenerateDisplay(List<Todo> todoList) {
    this.todoList = todoList;
    this.tokenizedTodos = new ArrayList<>();
  }

  /**
   * Displays the provided collection of Todos within in a table,
   * which is able to conform to the size of input.
   */
  @Override
  public void printTable() {
    int[] columnWidths = this.calculateColumnWidths();
    StringBuilder rowFormatter = new StringBuilder();
    this.formatTable(columnWidths, rowFormatter);
    this.printBorder(columnWidths);

    System.out.printf(rowFormatter.toString(), HEADER.toArray());
    this.printBorder(columnWidths);

    for (ArrayList<String> tokenizedTodos: this.tokenizedTodos) {
      System.out.printf(rowFormatter.toString(), tokenizedTodos.toArray());
    }

    this.printBorder(columnWidths);
  }

  /**
   * Prints horizontal edges of the table, establishing visual means of separation.
   *
   * @param columnWidths - an int Array, carrying the maximum widths of each column
   */
  private void printBorder(int[] columnWidths) {
    StringBuilder border = new StringBuilder("+");
    int i, loopStart = 0, accountForSpaces = 2;
    for (int width: columnWidths) {
      for (i = loopStart; i < (width + accountForSpaces); i++) {
        border.append("-");
      }
      border.append("+");
    }
    System.out.println(border.toString());
  }

  /**
   * Produces a string, based on the pre-determined column widths, which
   * can be utilized with System.out.printf to print the table rows
   * in a specific, user-friendly format.
   *
   * @param columnWidths - an int Array, carrying the maximum widths of each column
   * @param rowFormatter - a String within which user-friendly formatting will be stored
   */
  private void formatTable(int[] columnWidths, StringBuilder rowFormatter) {
    for (int width: columnWidths) {
      rowFormatter.append("| %").append(width).append("s ");
    }
    rowFormatter.append("|\n");
  }

  /**
   * Determines the width for each column, based on the maximum length
   * of associated input.
   *
   * @return an int Array, carrying the maximum widths of each column.
   */
  private int[] calculateColumnWidths() {
    this.tokenizeTodos();
    int[] columnWidths = HEADER_COLUMN_LENGTHS;
    int i, loopStart = 0;
    for (i = loopStart; i < TOTAL_COLUMNS; i++) {
      for (ArrayList<String> tokenizedTodo: this.tokenizedTodos) {
        int inputLength = tokenizedTodo.get(i).length();
        if (inputLength > columnWidths[i]) {
          columnWidths[i] = inputLength;
        }
      }
    }
    return columnWidths;
  }

  /**
   * Places the String version of each field of a To-do object within an ArrayList,
   * allowing for easy each field to be assessed (via calculateColumnWidths) and
   * printed easily.
   */
  private void tokenizeTodos() {
    for (Todo todo: this.todoList) {
      ArrayList<String> newTodo = new ArrayList<>();
      newTodo.add(todo.getId().toString());
      newTodo.add(todo.getText());
      newTodo.add(todo.getCompleted().toString());
      String date = todo.getDueDate() != null ? todo.getDueDate().format(DATE_FORMATTER) : "";
      newTodo.add(date);
      String priority = todo.getPriority() != null ? todo.getPriority().toString() : "";
      newTodo.add(priority);
      String category = todo.getCategory() != null ? todo.getCategory() : "";
      newTodo.add(category);
      this.tokenizedTodos.add(newTodo);
    }
  }

  /**
   * Determines whether a GenerateDisplay object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a GenerateDisplay object
   *
   * @return boolean indicating whether a GenerateDisplay object is equivalent
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
    GenerateDisplay that = (GenerateDisplay) o;
    return Objects.equals(this.todoList, that.todoList) &&
        Objects.equals(this.tokenizedTodos, that.tokenizedTodos);
  }

  /**
   * Determines the hashcode of a GenerateDisplay object
   *
   * @return the hashcode of a GenerateDisplay object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.todoList, this.tokenizedTodos);
  }

  /**
   * Provides a string representation of a GenerateDisplay object
   *
   * @return a string representation of a GenerateDisplay object
   */
  @Override
  public String toString() {
    return "TemporaryDisplay {" +
        "todoList = " + this.todoList +
        ", tokenizedTodos = " + this.tokenizedTodos +
        '}';
  }
}
