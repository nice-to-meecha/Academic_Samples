package problem01.view;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import problem01.controller.Todo;

public class GenerateDisplayTest {

  private GenerateDisplay display;
  private GenerateDisplay display2;
  private Todo schoolTodo1 = new Todo("Finish A6", true,
      LocalDate.of(2021, 4, 21), 2, "School", 1);
  private Todo hobbyTodo = new Todo("Make clothes", false, null, null,
      "Hobby", 2);
  private Todo schoolTodo2 = new Todo("Finish HW9", false,
      LocalDate.of(2021, 4, 22), 1, "school", 3);
  private Todo cookingTodo = new Todo("Make tamales", false,
      LocalDate.of(2021, 5, 10), 3, null, 4);
  List<Todo> todoList = new ArrayList<>();

  @Before
  public void setUp() throws Exception {
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    display = new GenerateDisplay(todoList);
    display2 = new GenerateDisplay(todoList);
  }

  @Test
  public void printTable() {
    // Displays the table
    display.printTable();
  }

  @Test
  public void testEqualsReflexivity() {
    assertTrue(display.equals(display));
  }

  @Test
  public void testEqualsSymmetry() {
    assertTrue(display.equals(display2) &&
        display2.equals(display));
  }

  @Test
  public void testEqualsTransitivity() {
    GenerateDisplay display3 = new GenerateDisplay(todoList);
    assertTrue(display.equals(display2) &&
        display2.equals(display3) &&
        display.equals(display3));
  }

  @Test
  public void testEqualsConsistency() {
    boolean result = display.equals(display2);
    assertEquals(result, display.equals(display2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(display.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(display.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() {
    List<Todo> shortList = new ArrayList<>(Arrays.asList(schoolTodo2, hobbyTodo));
    GenerateDisplay smallerList = new GenerateDisplay(shortList);
    assertFalse(display.equals(smallerList));

    display.printTable();
    assertFalse(display.equals(display2));
  }

  @Test
  public void testHashCodeSelfConsistency() {
    assertTrue(display.hashCode() == display.hashCode());
  }

  @Test
  public void testHashCodeConsistency() {
    assertTrue(display.hashCode() == display2.hashCode());
  }

  @Test
  public void testHashCodeDifferent() {
    List<Todo> shortList = new ArrayList<>(Arrays.asList(schoolTodo2, hobbyTodo));
    GenerateDisplay smallerList = new GenerateDisplay(shortList);
    assertFalse(display.hashCode() == smallerList.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("TemporaryDisplay {" +
            "todoList = [Todo{text='Finish A6', "
            + "completed=true, dueDate=2021-04-21, priority=2, category='School'" +
            ", ID=1}, Todo{text='Make clothes', completed=false, dueDate=null, "
            + "priority=null, category='Hobby', ID=2}, Todo{text='Finish HW9', "
            + "completed=false, dueDate=2021-04-22, priority=1, category='school'" +
            ", ID=3}, Todo{text='Make tamales', completed=false, dueDate=2021-05-10, "
            + "priority=3, category='null', ID=4}], tokenizedTodos = []}",
        display.toString());
  }
}