package problem01.controller;

/**
 * A class containing constants representing command line arguments
 */
public class ArgConstants {

  public static final String CSV_FILE = "--csv-file";
  public static final String ADD_TODO = "--add-todo";
  public static final String TODO_TEXT = "--todo-text";
  public static final String COMPLETED = "--completed";
  public static final String DUE = "--due";
  public static final String PRIORITY = "--priority";
  public static final String CATEGORY = "--category";
  public static final String COMPLETE_TODO = "--complete-todo";
  public static final String DISPLAY = "--display";
  public static final String SHOW_INCOMPLETE = "--show-incomplete";
  public static final String SHOW_CATEGORY = "--show-category";
  public static final String SORT_BY_DATE = "--sort-by-date";
  public static final String SORT_BY_PRIORITY = "--sort-by-priority";

  /**
   * Generates an empty ArgConstants object.
   */
  private ArgConstants() {
  }
}
