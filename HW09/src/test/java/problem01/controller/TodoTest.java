package problem01.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {


  Todo todo;
  Todo same;
  Todo diffText;
  Todo diffCompleted;
  Todo diffDueDate;
  Todo diffPriority;
  Todo diffCategory;
  Todo diffID;

  private String text;
  private Boolean completed;
  private LocalDate dueDate;
  private Integer priority;
  private String category;
  private Integer id;


  @Before
  public void setUp() throws Exception {
    text = "Description of this todo";
    completed = false;
    dueDate = LocalDate.parse("2021-04-20");
    priority = 1;
    category = "work";
    id = 1;
    todo = new Todo(text, completed, dueDate, priority, category, id);
    same = new Todo(text, completed, dueDate, priority, category, id);
    diffText = new Todo("Diff description", completed, dueDate, priority, category, id);
    diffCompleted = new Todo(text, true, dueDate, priority, category, id);
    diffDueDate = new Todo(text, completed, LocalDate.parse("2021-04-21"), priority, category, id);
    diffPriority = new Todo(text, completed, dueDate, 2, category, id);
    diffCategory = new Todo(text, completed, dueDate, priority, "school", id);
    diffID = new Todo(text, completed, dueDate, priority, category, 8);
  }

  @Test
  public void getText() {
    assertEquals(text, todo.getText());
  }

  @Test
  public void getCompleted() {
    assertEquals(completed, todo.getCompleted());
  }

  @Test
  public void getDueDate() {
    assertEquals(dueDate, todo.getDueDate());
  }

  @Test
  public void getPriority() {
    assertEquals(priority, todo.getPriority());
  }

  @Test
  public void getCategory() {
    assertEquals(category, todo.getCategory());
  }

  @Test
  public void getID() {
    assertEquals(id, todo.getId());
  }

  @Test
  public void setCompleted() {
    todo.setCompleted(true);
    assertTrue(todo.getCompleted());
  }

  @Test
  public void testEquals() {
    // Null case
    assertFalse(todo.equals(null));
    // Different object type
    assertFalse(todo.equals(new NullPointerException()));
    // Equal to itself
    assertTrue(todo.equals(todo));
    // Equal to object same values
    assertTrue(todo.equals(same));
    //Not equal to different Text
    assertFalse(todo.equals(diffText));
    //Not equal to different completed
    assertFalse(todo.equals(diffCompleted));
    //Not equal to different dueDate
    assertFalse(todo.equals(diffDueDate));
    //Not equal to different priority
    assertFalse(todo.equals(diffPriority));
    //Not equal to different category
    assertFalse(todo.equals(diffCategory));
    //Not equal to different ID
    assertFalse(todo.equals(diffID));
  }

  @Test
  public void testHashCode() {
    // Same
    assertTrue(todo.hashCode() == todo.hashCode());
    // Consistent
    assertTrue(todo.hashCode() == same.hashCode());
    // Different
    assertFalse(todo.hashCode() == diffCategory.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Todo{text='Description of this todo', completed=false,"
        + " dueDate=2021-04-20, priority=1, category='work', ID=1}", todo.toString());
  }
}