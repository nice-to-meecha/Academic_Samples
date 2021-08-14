package problem01.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import problem01.view.InvalidArgumentException;

public class CompleteTodoTest {

  CompleteTodo completeTodo;
  CompleteTodo same;
  CompleteTodo diffID;

  Todo todo;
  Todo todo2;
  List<Todo> todoList;

  private Integer id;

  @Before
  public void setUp() throws Exception {
    completeTodo = new CompleteTodo(1);
    same = new CompleteTodo(1);
    diffID = new CompleteTodo(5);

    todo = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 1);
    todo2 = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 2);
    todoList = new ArrayList<>();
    todoList.add(todo);
    todoList.add(todo2);
  }

  @Test
  public void getId() {
    assertTrue(completeTodo.getId().equals(1));
  }

  @Test
  public void performTaskSuccessful() {
    completeTodo.performTask(todoList);
    assertTrue(todoList.get(0).getCompleted());
    assertFalse(todoList.get(1).getCompleted());
    diffID.performTask(todoList);
  }

  @Test(expected = InvalidArgumentException.class)
  public void performTaskUnsuccessful() throws InvalidArgumentException {
    diffID.validateArgs(todoList);
  }

  @Test
  public void validateArgs() {

  }

  @Test
  public void testEquals() {
    // Null case
    assertFalse(completeTodo.equals(null));
    // Different object type
    assertFalse(completeTodo.equals(new NullPointerException()));
    // Equal to itself
    assertTrue(completeTodo.equals(completeTodo));
    // Equal to object same values
    assertTrue(completeTodo.equals(same));
    // Not equal to different ID value
    assertFalse(completeTodo.equals(diffID));
  }

  @Test
  public void testHashCode() {
    // Same
    assertTrue(completeTodo.hashCode() == completeTodo.hashCode());
    // Consistent
    assertTrue(completeTodo.hashCode() == same.hashCode());
    // Different
    assertFalse(completeTodo.hashCode() == diffID.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("CompleteTodo{id=1}", completeTodo.toString());
  }
}