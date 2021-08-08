package problem01.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import problem01.view.InvalidArgumentException;

public class AddTodoTest {

  private String todoText;
  private boolean completed;
  private String dueDate;
  private LocalDate dueLocalDate;
  private Integer priority;
  private String category;
  private Integer id;

  AddTodo addTodo;
  AddTodo same;
  AddTodo diffTodoText;
  AddTodo diffCompleted;
  AddTodo diffDueDate;
  AddTodo diffDueLocalDate;
  AddTodo diffPriority;
  AddTodo diffCategory;
  AddTodo diffID;

  Todo todo;
  Todo todo2;
  List<Todo> todoList;

  @Before
  public void setUp() throws Exception {
    this.todoText = "";
    this.completed = false;
    this.dueDate = null;
    this.priority = null;
    this.category = null;
    this.dueLocalDate = null;
    this.id = 1;

    addTodo = new AddTodo(1);
    same = new AddTodo(1);

    diffTodoText = new AddTodo(1);
    diffTodoText.setTodoText("This is a description");

    diffCompleted = new AddTodo(1);
    diffCompleted.setCompleted(true);

    diffDueDate = new AddTodo(1);
    diffDueDate.setDueDate("04/20/2020");

    diffDueLocalDate = new AddTodo(1);
    diffDueLocalDate.setDueDate("04/20/2020"); // must vlaidate args first---------------------------

    diffPriority = new AddTodo(1);
    diffPriority.setPriority(3);

    diffCategory = new AddTodo(1);
    diffCategory.setCategory("work");

    diffID = new AddTodo(8);

    todo = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 1);
    todo2 = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 2);
    todoList = new ArrayList<>();
    todoList.add(todo);
    todoList.add(todo2);
  }

  @Test
  public void setTodoText() {
    addTodo.setTodoText("This is different");
    assertEquals("This is different", addTodo.getTodoText());
  }

  @Test
  public void setCompleted() {
    addTodo.setCompleted(true);
    assertTrue(addTodo.isCompleted());
  }

  @Test
  public void setDueDate() {
    addTodo.setDueDate("04/41/2020");
    assertEquals("04/41/2020", addTodo.getDueDate());
  }

  @Test
  public void setCategory() {
    addTodo.setCategory("home");
    assertEquals("home", addTodo.getCategory());
  }

  @Test
  public void setPriority() {
    addTodo.setPriority(1);
    assertTrue(addTodo.getPriority().equals(1));
  }

  @Test
  public void isCompleted() {
    assertFalse(addTodo.isCompleted());
  }

  @Test
  public void getDueDate() {
    assertEquals("04/20/2020", diffDueDate.getDueDate());
  }

  @Test
  public void getDueLocalDate() {
    try {
      diffDueLocalDate.setTodoText("description");
      diffDueLocalDate.validateArgs(todoList);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
      assertTrue(diffDueLocalDate.getDueLocalDate().equals(LocalDate.parse("2020-04-20")));
      assertTrue(diffDueLocalDate.getDueLocalDate().equals(LocalDate.parse("04/20/2020",
          formatter)));
    } catch (InvalidArgumentException e) {
    }
  }

  @Test
  public void getID() {
    assertTrue(addTodo.getId().equals(1));
  }

  @Test
  public void performTask() {
    Todo test = new Todo(this.todoText, this.completed, this.dueLocalDate, this.priority, this.category,
        this.id);
    List<Todo> todoList2 = new ArrayList<>();
    todoList2.add(todo);
    todoList2.add(todo2);
    todoList2.add(test);
    assertTrue(addTodo.performTask(todoList).equals(todoList2));
  }

  @Test
  public void testValidateArgs() {
    try {
      addTodo.setTodoText("Random text");
      addTodo.validateArgs(todoList);
    } catch (InvalidArgumentException e) {
    }
  }

  @Test(expected = InvalidArgumentException.class)
  public void validateArgsDateTime() throws InvalidArgumentException {
    addTodo.setDueDate("04-20-2020");
    addTodo.validateArgs(todoList);
  }

  @Test(expected = InvalidArgumentException.class)
  public void validateArgsLowPriority() throws InvalidArgumentException {
    addTodo.setDueDate("04/20/2020");
    addTodo.setPriority(0);
    addTodo.validateArgs(todoList);
  }

  @Test(expected = InvalidArgumentException.class)
  public void validateArgsHighPriority() throws InvalidArgumentException {
    addTodo.setDueDate("04/20/2020");
    addTodo.setPriority(4);
    addTodo.validateArgs(todoList);
  }

  @Test(expected = InvalidArgumentException.class)
  public void validateArgsMissingDescription() throws InvalidArgumentException {
    addTodo.setDueDate("04/20/2020");
    addTodo.setPriority(3);
    addTodo.validateArgs(todoList);
  }

  @Test
  public void testEquals() {
    // Null case
    assertFalse(addTodo.equals(null));
    // Different object type
    assertFalse(addTodo.equals(new NullPointerException()));
    // Equal to itself
    assertTrue(addTodo.equals(addTodo));
    // Equal to object same values
    assertTrue(addTodo.equals(same));
    //Different todoText
    assertFalse(addTodo.equals(diffTodoText));
    //Different completed
    assertFalse(addTodo.equals(diffCompleted));
    //Different dueDate
    assertFalse(addTodo.equals(diffDueDate));
    //Different dueLocalDate
    try {
      diffDueLocalDate.setTodoText("description");
      diffDueDate.setTodoText("description");
      diffDueLocalDate.validateArgs(todoList);
      assertFalse(diffDueDate.equals(diffDueLocalDate));
    } catch (InvalidArgumentException e) {
      e.printStackTrace();
    }
    //Different diffPriority
    assertFalse(addTodo.equals(diffPriority));
    //Different category
    assertFalse(addTodo.equals(diffCategory));
    //Different ID
    assertFalse(addTodo.equals(diffID));
  }

  @Test
  public void testHashCode() {
    // Same
    assertTrue(addTodo.hashCode() == addTodo.hashCode());
    // Consistent
    assertTrue(addTodo.hashCode() == same.hashCode());
    // Different
    assertFalse(addTodo.hashCode() == diffCompleted.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("AddTodo{todoText='', completed=false, dueDate='null', dueLocalDate=null,"
        + " priority=null, category='null', ID=1}", addTodo.toString());
  }
}