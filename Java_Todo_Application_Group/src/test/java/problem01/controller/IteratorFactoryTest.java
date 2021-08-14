package problem01.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

public class IteratorFactoryTest {

  private IteratorFactory iteratorFactory;
  private IteratorFactory iteratorFactory2;
  private Todo schoolTodo1 = new Todo("Finish A6", true,
      LocalDate.of(2021, 4, 21), 2, "School", 1);
  private Todo hobbyTodo = new Todo("Make clothes", false, null, 3,
      "Hobby", 2);
  private Todo schoolTodo2 = new Todo("Finish HW9", false,
      LocalDate.of(2021, 4, 22), 1, "school", 3);
  private Todo cookingTodo = new Todo("Make tamales", false,
      LocalDate.of(2021, 5, 10), 3, "Cooking", 4);
  private Todo schoolTodo3 = new Todo("Complete scholarship essay", true,
      LocalDate.of(2021, 4, 15), 2, "School", 5);
  ArrayList<Todo> todoList;

  @Before
  public void setUp() throws Exception {
    iteratorFactory = new IteratorFactory();
    iteratorFactory2 = new IteratorFactory();

    todoList = new ArrayList<>();
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    todoList.add(schoolTodo3);
  }

  @Test
  public void createIncompleteIterator() {
    Iterator<Todo> incompleteIterator = IteratorFactory.createIterator(
        "incomplete", todoList);
    assertEquals(IncompleteIterator.class, incompleteIterator.getClass());
  }

  @Test
  public void createCategoryIterator() {
    Iterator<Todo> categoryIterator = IteratorFactory.createIterator(
        "School", todoList);
    assertEquals(CategoryIterator.class, categoryIterator.getClass());
  }

  @Test
  public void testEqualsReflexivity() {
    assertTrue(iteratorFactory.equals(iteratorFactory));
  }

  @Test
  public void testEqualsSymmetry() {
    assertTrue(iteratorFactory.equals(iteratorFactory2) &&
        iteratorFactory2.equals(iteratorFactory));
  }

  @Test
  public void testEqualsTransitivity() {
    IteratorFactory iteratorFactory3 = new IteratorFactory();
    assertTrue(iteratorFactory.equals(iteratorFactory2) &&
        iteratorFactory2.equals(iteratorFactory3) &&
        iteratorFactory.equals(iteratorFactory3));
  }

  @Test
  public void testEqualsConsistency() {
    boolean result = iteratorFactory.equals(iteratorFactory2);
    assertEquals(result, iteratorFactory.equals(iteratorFactory2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(iteratorFactory.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(iteratorFactory.equals(exception));
  }

  @Test
  public void testHashCodeSelfConsistency() {
    assertTrue(iteratorFactory.hashCode() == iteratorFactory.hashCode());
  }

  @Test
  public void testHashCodeConsistency() {
    assertTrue(iteratorFactory.hashCode() == iteratorFactory2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("IteratorFactory{}", iteratorFactory.toString());
  }
}