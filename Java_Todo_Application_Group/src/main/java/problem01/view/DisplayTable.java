package problem01.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import problem01.Task;
import problem01.controller.CompareDate;
import problem01.controller.ComparePriority;
import problem01.controller.IteratorFactory;
import problem01.controller.Todo;

/**
 * A task for displaying the table of to-dos, according to specifications given
 */
public class DisplayTable implements Task {
  private boolean showIncomplete;
  private String showCategory;
  private boolean sortByDate;
  private boolean sortByPriority;

  /**
   * Constructor for the class
   */
  public DisplayTable() {
    this.showIncomplete = false;
    this.showCategory = null;
    this.sortByDate = false;
    this.sortByPriority = false;
  }

  /**
   * Sets the showIncomplete field
   * @param showIncomplete - the showIncomplete field
   */
  public void setShowIncomplete(boolean showIncomplete) {
    this.showIncomplete = showIncomplete;
  }

  /**
   * Sets the showCategory field
   * @param showCategory - the showCategory field
   */
  public void setShowCategory(String showCategory) {
    this.showCategory = showCategory;
  }

  /**
   * Sets the sortByDate field
   * @param sortByDate - the sortByDate field
   */
  public void setSortByDate(boolean sortByDate) {
    this.sortByDate = sortByDate;
  }

  /**
   * Sets the sortByPriority field
   * @param sortByPriority - the sortByPriority field
   */
  public void setSortByPriority(boolean sortByPriority) {
    this.sortByPriority = sortByPriority;
  }

  /**
   * Returns the showIncomplete field
   * @return - the showIncomplete field
   */
  public boolean isShowIncomplete() {
    return this.showIncomplete;
  }

  /**
   * Returns the showCategory field
   * @return - the showCategory field
   */
  public String getShowCategory() {
    return this.showCategory;
  }

  /**
   * Returns the sortByDate field
   * @return - the sortByDate field
   */
  public boolean isSortByDate() {
    return this.sortByDate;
  }

  /**
   * Returns the sortByPriority
   * @return - the sortByPriority field
   */
  public boolean isSortByPriority() {
    return this.sortByPriority;
  }

  /**
   * Performs the current task, using the appropriate fields.
   * Displays a table of Todos according to arguments provided
   *
   * @param todoList list of Todos being managed
   * @return - list of Todos updated after current task performed
   */
  @Override
  public List<Todo> performTask(List<Todo> todoList) {
    List<Todo> alteredList = todoList;
    if (this.showIncomplete) {
      Iterator<Todo> incompleteIterator = IteratorFactory.createIterator(
          "incomplete", alteredList);
      alteredList = filterResults(incompleteIterator);
    }
    if (this.showCategory != null) {
      Iterator<Todo> categoryIterator = IteratorFactory.createIterator(
          this.showCategory, alteredList);
      alteredList = filterResults(categoryIterator);
    }
    if (this.sortByDate) {
      Comparator<Todo> compareDate = new CompareDate();
      alteredList.sort(compareDate);
    }
    else if (this.sortByPriority) {
      Comparator<Todo> comparePriority = new ComparePriority();
      alteredList.sort(comparePriority);
    }

    int emptyList = 0;
    if (!(alteredList.size() == emptyList)) {
      GenerateDisplay displayTable = new GenerateDisplay(alteredList);
      displayTable.printTable();
    }

    return alteredList;
  }

  /**
   * Filters a provided To-do List, based on the specified filter, as stored
   * by the iterator (Iterator Pattern).
   *
   * @param iterator - the iterator which will filter a To-do list, according
   *                 to the specified filter
   *
   * @return a filtered To-do List, based on the specified filter, as stored
   * by the iterator.
   */
  private List<Todo> filterResults(Iterator<Todo> iterator) {
    List<Todo> filteredList = new ArrayList<>();
    while (iterator.hasNext()) {
      filteredList.add(iterator.next());
    }
    return filteredList;
  }

  /**
   * Checks that the given arguments for the task are valid.
   *
   * @throws InvalidArgumentException - Thrown if combination of args are invalid
   */
  @Override
  public void validateArgs(List<Todo> todoList) throws InvalidArgumentException {
    if (this.sortByDate && this.sortByPriority) {
      throw new InvalidArgumentException("--sort-by-priority cannot be combined with "
          + "--sort-by-date");
    }

    boolean present = false;
    if (this.showCategory != null && this.showIncomplete) {
      for (Todo todo : todoList) {
        if (todo.getCategory() != null) {
          if (todo.getCategory().equals(this.showCategory) &&
              todo.getCompleted().equals(false)) {
            present = true;
          }
        }
      }
      if (!present) {
        throw new InvalidArgumentException("No incomplete, '" + this.showCategory +
            "' todos were found.");
      }
    }
    else if (this.showCategory != null) {
      this.checkCategory(todoList);
    }
    else if (this.showIncomplete) {
      this.checkIncomplete(todoList);
    }
  }

  /**
   * Determines whether if any Todos within the provided List are incomplete.
   *
   * @param todoList - To-do list to be assessed for incomplete To-do membership.
   *
   * @return boolean indicating whether if any Todos within the provided List are incomplete.
   *
   * @throws InvalidArgumentException - Thrown if combination of args are invalid
   */
  private boolean checkIncomplete(List<Todo> todoList) throws InvalidArgumentException {
    boolean present = false;
    for (Todo todo : todoList) {
      if (todo.getCompleted().equals(false)) {
        present = true;
        break;
      }
    }
    if (!present) {
      throw new InvalidArgumentException("No incomplete todos were found.");
    }
    return present;
  }

  /**
   * Determines whether if any Todos within the provided List have the indicated category.
   *
   * @param todoList - To-do list to be assessed for category To-do membership.
   *
   * @return boolean indicating whether if any Todos within the provided List have the
   * indicated category.
   *
   * @throws InvalidArgumentException - Thrown if combination of args are invalid
   */
  private boolean checkCategory(List<Todo> todoList) throws InvalidArgumentException {
    boolean present = false;
    for (Todo todo : todoList) {
      if (todo.getCategory() != null) {
        if (todo.getCategory().equals(this.showCategory)) {
          present = true;
          break;
        }
      }
    }
    if (!present) {
      throw new InvalidArgumentException("No todos to be displayed with the given category were "
          + "found");
    }
    return present;
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
    DisplayTable that = (DisplayTable) o;
    return this.showIncomplete == that.showIncomplete &&
        this.sortByDate == that.sortByDate &&
        this.sortByPriority == that.sortByPriority &&
        Objects.equals(this.showCategory, that.showCategory);
  }

  /**
   * Overrides the default hashCode() method, returns the hashcode of the object
   *
   * @return - hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.showIncomplete, this.showCategory, this.sortByDate,
        this.sortByPriority);
  }

  /**
   * Overrides the default toString() method, returns a string representation of the object
   *
   * @return - string representation of object
   */
  @Override
  public String toString() {
    return "DisplayTable{" +
        "showIncomplete=" + this.showIncomplete +
        ", showCategory='" + this.showCategory + '\'' +
        ", sortByDate=" + this.sortByDate +
        ", sortByPriority=" + this.sortByPriority +
        '}';
  }
}