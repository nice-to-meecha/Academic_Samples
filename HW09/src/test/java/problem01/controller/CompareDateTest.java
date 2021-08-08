package problem01.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CompareDateTest {

  private CompareDate compareDate;
  private Todo schoolTodo1 = new Todo("Finish A6", true,
      LocalDate.of(2021, 4, 21), 2, "School", 1);
  private Todo hobbyTodo = new Todo("Make clothes", false, null, 3,
      "Hobby", 2);
  private Todo schoolTodo2 = new Todo("Finish HW9", false,
      LocalDate.of(2021, 4, 22), 1, "school", 3);
  private Todo cookingTodo = new Todo("Make tamales", false,
      null, 3, "Cooking", 4);
  private Todo schoolTodo3 = new Todo("Complete scholarship essay", true,
      LocalDate.of(2021, 4, 15), 2, "School", 5);
  List<Todo> todoList;

  @Before
  public void setUp() throws Exception {
    todoList = new ArrayList<>();
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    todoList.add(schoolTodo3);
    compareDate = new CompareDate();
  }

  @Test
  public void compareBefore() {
    assertTrue(compareDate.compare(schoolTodo1, schoolTodo2) < 0);
  }

  @Test
  public void compareAfter() {
    assertTrue(compareDate.compare(schoolTodo2, schoolTodo3) > 0);
  }

  @Test
  public void compareSame() {
    assertTrue(compareDate.compare(hobbyTodo, hobbyTodo) == 0);
  }

  @Test
  public void compareBothNullDifferentIds() {
    assertTrue(compareDate.compare(hobbyTodo, cookingTodo) < 0);
  }

  @Test
  public void compareTodoOneNull() {
    assertTrue(compareDate.compare(hobbyTodo, schoolTodo3) > 0);
  }

  @Test
  public void compareTodoTwoNull() {
    assertTrue(compareDate.compare(schoolTodo3, hobbyTodo) < 0);
  }

  @Test
  public void sort() {
    List<Todo> newOrder = new ArrayList<>();
    newOrder.add(schoolTodo3);
    newOrder.add(schoolTodo1);
    newOrder.add(schoolTodo2);
    newOrder.add(hobbyTodo);
    newOrder.add(cookingTodo);
    assertNotEquals(newOrder, todoList);
    todoList.sort(compareDate);
    assertEquals(newOrder, todoList);
  }
}