package problem01.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ComparePriorityTest {

  private ComparePriority comparePriority;
  private Todo schoolTodo1 = new Todo("Finish A6", true,
      LocalDate.of(2021, 4, 21), 2, "School", 1);
  private Todo hobbyTodo = new Todo("Make clothes", false, null, null,
      "Hobby", 2);
  private Todo schoolTodo2 = new Todo("Finish HW9", false,
      LocalDate.of(2021, 4, 22), 1, "school", 3);
  private Todo cookingTodo = new Todo("Make tamales", false,
      null, 3, "Cooking", 4);
  private Todo schoolTodo3 = new Todo("Complete scholarship essay", true,
      LocalDate.of(2021, 4, 15), 2, "School", 5);
  private Todo sleepTodo = new Todo("Get decent sleep", false,
      null, null, "Life", 6);
  List<Todo> todoList;

  @Before
  public void setUp() throws Exception {
    todoList = new ArrayList<>();
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    todoList.add(schoolTodo3);
    todoList.add(sleepTodo);
    comparePriority = new ComparePriority();
  }

  @Test
  public void compareBefore() {
    assertTrue(comparePriority.compare(schoolTodo2, schoolTodo1) < 0);
  }

  @Test
  public void compareAfter() {
    assertTrue(comparePriority.compare(cookingTodo, schoolTodo3) > 0);
  }

  @Test
  public void compareSame() {
    assertTrue(comparePriority.compare(schoolTodo3, schoolTodo3) == 0);
  }

  @Test
  public void compareNullDifferentIds() {
    assertTrue(comparePriority.compare(hobbyTodo, sleepTodo) < 0);
  }

  @Test
  public void compareTodoOneNull() {
    assertTrue(comparePriority.compare(sleepTodo, schoolTodo1) > 0);
  }

  @Test
  public void compareTodoTwoNull() {
    assertTrue(comparePriority.compare(schoolTodo2, hobbyTodo) < 0);

  }

  @Test
  public void sort() {
    List<Todo> newOrder = new ArrayList<>();
    newOrder.add(schoolTodo2);
    newOrder.add(schoolTodo1);
    newOrder.add(schoolTodo3);
    newOrder.add(cookingTodo);
    newOrder.add(hobbyTodo);
    newOrder.add(sleepTodo);
    assertNotEquals(newOrder, todoList);
    todoList.sort(comparePriority);
    assertEquals(newOrder, todoList);
  }
}