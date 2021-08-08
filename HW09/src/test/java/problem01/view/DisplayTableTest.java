package problem01.view;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import problem01.controller.Todo;

public class DisplayTableTest {

  DisplayTable dt;
  DisplayTable same;
  DisplayTable diffShowIncomplete;
  DisplayTable diffShowCategory;
  DisplayTable diffSortByDate;
  DisplayTable diffSortByPriority;

  private boolean showIncomplete;
  private boolean showCategory;
  private boolean sortByDate;
  private boolean sortByPriority;

  private Todo schoolTodo1 = new Todo("Finish A6", true,
      LocalDate.of(2021, 4, 21), 2, "School", 1);
  private Todo hobbyTodo = new Todo("Make clothes", false, null, 3,
      null, 2);
  private Todo schoolTodo2 = new Todo("Finish HW9", false,
      LocalDate.of(2021, 4, 22), 1, "school", 3);
  private Todo cookingTodo = new Todo("Make tamales", false,
      LocalDate.of(2021, 5, 10), null, "Cooking", 4);

  List<Todo> todoList;
  Todo todo;

  @Before
  public void setUp() throws Exception {
    dt = new DisplayTable();
    same = new DisplayTable();
    diffShowIncomplete = new DisplayTable();
    diffShowCategory = new DisplayTable();
    diffSortByDate = new DisplayTable();
    diffSortByPriority = new DisplayTable();
    todoList = new ArrayList<>();
    todo = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 1);
    todoList.add(todo);
  }

  @Test
  public void setShowIncomplete() {
    dt.setShowIncomplete(true);
    assertTrue(dt.isShowIncomplete());
  }

  @Test
  public void setShowCategory() {
    dt.setShowCategory("work");
    assertEquals("work", dt.getShowCategory());
  }

  @Test
  public void setSortByDate() {
    dt.setSortByDate(true);
    assertTrue(dt.isSortByDate());
  }

  @Test
  public void setSortByPriority() {
    dt.setSortByPriority(true);
    assertTrue(dt.isSortByPriority());
  }

  @Test
  public void isShowIncomplete() {
    assertFalse(dt.isShowIncomplete());
  }

  @Test
  public void getCategory() {
    dt.setShowCategory("work");
    assertEquals("work", dt.getShowCategory());
  }

  @Test
  public void isSortByDate() {
    assertFalse(dt.isSortByDate());
  }

  @Test
  public void isSortByPriority() {
    assertFalse(dt.isSortByPriority());
  }

  @Test
  public void performTaskFilterIncompleteOnly() {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setShowIncomplete(true);
    List<Todo> filteredList = dt.performTask(todoList);

    List<Todo> expectedList = new ArrayList<>(Arrays.asList(hobbyTodo, schoolTodo2, cookingTodo));
    assertEquals(expectedList, filteredList);
  }

  @Test
  public void performTaskFilterCategoryOnly() {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setShowCategory("school");
    List<Todo> filteredList = dt.performTask(todoList);

    List<Todo> expectedList = new ArrayList<>(Arrays.asList(schoolTodo1, schoolTodo2));
    assertEquals(expectedList, filteredList);
  }

  @Test
  public void performTaskFilterIncompleteAndCategory() {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setShowIncomplete(true);
    dt.setShowCategory("school");
    List<Todo> filteredList = dt.performTask(todoList);

    List<Todo> expectedList = new ArrayList<>(Arrays.asList(schoolTodo2));
    assertEquals(expectedList, filteredList);
  }

  @Test
  public void performTaskSortByDate() {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setSortByDate(true);
    List<Todo> sortedList = dt.performTask(todoList);

    List<Todo> expectedList = new ArrayList<>(Arrays.asList(schoolTodo1, schoolTodo2,
        cookingTodo, hobbyTodo));
    assertEquals(expectedList, sortedList);
  }

  @Test
  public void performTaskSortByPriority() {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setSortByPriority(true);
    List<Todo> sortedList = dt.performTask(todoList);

    List<Todo> expectedList = new ArrayList<>(Arrays.asList(schoolTodo2, schoolTodo1,
        hobbyTodo, cookingTodo));
    assertEquals(expectedList, sortedList);
  }

  @Test
  public void performTaskNoDisplay() {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    dt.setShowCategory("Cooking");
    List<Todo> emptyList = dt.performTask(todoList);

    List<Todo> expectedList = new ArrayList<>();
    assertEquals(expectedList, emptyList);
  }

  @Test
  public void validateArgs() {
    try {
      dt.setSortByPriority(true);
      dt.setSortByDate(true);
      dt.validateArgs(todoList);
    } catch (InvalidArgumentException e) {
    }
    try {
      dt.setSortByDate(false);
      dt.setSortByPriority(false);
      dt.validateArgs(todoList);
    } catch (InvalidArgumentException e) {
    }
    try {
      dt.setSortByDate(true);
      dt.setSortByPriority(false);
      dt.validateArgs(todoList);
    } catch (InvalidArgumentException e) {
    }
    try {
      dt.setSortByDate(false);
      dt.setSortByPriority(true);
      dt.validateArgs(todoList);
    } catch (InvalidArgumentException e) {
    }
    try {
      dt.setSortByDate(false);
      dt.setSortByPriority(true);
      dt.validateArgs(todoList);
    } catch (InvalidArgumentException e) {
    }
    try {
      dt.setShowCategory("school");
      dt.validateArgs(todoList);
    } catch (InvalidArgumentException e) {
    }
  }

  @Test(expected = InvalidArgumentException.class)
  public void validateArgsFail() throws InvalidArgumentException {
    dt.setShowCategory("random");
    dt.validateArgs(todoList);
  }

  @Test
  public void validateArgsShowCategoryOnly() throws InvalidArgumentException {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setShowCategory("school");
    // No exception thrown
    dt.validateArgs(todoList);
  }

  @Test (expected = InvalidArgumentException.class)
  public void validateArgsShowCategoryOnlyException() throws InvalidArgumentException {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setShowCategory("eahoq91i1");
    dt.validateArgs(todoList);
  }

  @Test
  public void validateArgsShowIncompleteOnly() throws InvalidArgumentException {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setShowIncomplete(true);
    // No exception thrown
    dt.validateArgs(todoList);
  }

  @Test (expected = InvalidArgumentException.class)
  public void validateArgsShowIncompleteOnlyException() throws InvalidArgumentException {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    dt.setShowIncomplete(true);
    dt.validateArgs(todoList);
  }

  @Test
  public void validateArgsShowCategoryAndIncomplete() throws InvalidArgumentException {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    dt.setShowCategory("school");
    dt.setShowIncomplete(true);
    // No exception thrown
    dt.validateArgs(todoList);
  }

  @Test
  public void validateArgsShowCategoryAndCompleted() throws InvalidArgumentException {
    Todo schoolTodo3 = new Todo("Submit HW8", true,
        LocalDate.of(2021, 4, 8), 3, "school", 5);
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    todoList.add(schoolTodo3);
    dt.setShowCategory("school");
    dt.setShowIncomplete(true);
    // No exception thrown
    dt.validateArgs(todoList);
  }

  @Test (expected = InvalidArgumentException.class)
  public void validateArgsShowCategoryAndIncompleteException() throws InvalidArgumentException {
    todoList.remove(todo);
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(cookingTodo);
    dt.setShowCategory("school");
    dt.setShowIncomplete(true);
    dt.validateArgs(todoList);
  }

  @Test
  public void testEquals() {
    // Null case
    assertFalse(dt.equals(null));
    // Different object type
    assertFalse(dt.equals(new NullPointerException()));
    // Equal to itself
    assertTrue(dt.equals(dt));
    // Equal to object same values
    assertTrue(dt.equals(same));
    // Not equal to diff show incomplete
    diffShowIncomplete.setShowIncomplete(true);
    assertFalse(dt.equals(diffShowIncomplete));
    // Not equal to diff show category
    diffShowCategory.setShowCategory("true");
    assertFalse(dt.equals(diffShowCategory));
    // Not equal to diff show sort by date
    diffSortByDate.setSortByDate(true);
    assertFalse(dt.equals(diffSortByDate));
    // Not equal to diff sort by priority
    diffSortByPriority.setSortByPriority(true);
    assertFalse(dt.equals(diffSortByPriority));
  }

  @Test
  public void testHashCode() {
    // Same
    assertTrue(dt.hashCode() == dt.hashCode());
    // Consistent
    assertTrue(dt.hashCode() == same.hashCode());
    // Different
    diffShowCategory.setShowCategory("true");
    assertFalse(dt.hashCode() == diffShowCategory.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("DisplayTable{showIncomplete=false, showCategory='null', sortByDate=false,"
        + " sortByPriority=false}", dt.toString());
  }
}