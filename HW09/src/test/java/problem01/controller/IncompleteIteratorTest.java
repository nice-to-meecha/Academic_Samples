package problem01.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class IncompleteIteratorTest {

  private IncompleteIterator iterator;
  private IncompleteIterator iterator2;
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
    todoList = new ArrayList<>();
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    todoList.add(schoolTodo3);
    iterator = new IncompleteIterator(todoList);
    iterator2 = new IncompleteIterator(todoList);
  }

  @Test
  public void hasNext() {
    assertTrue(iterator.hasNext());
    iterator.next();
    assertTrue(iterator.hasNext());
    iterator.next();
    assertTrue(iterator.hasNext());
    iterator.next();
    assertFalse(iterator.hasNext());
  }

  @Test
  public void next() {
    assertTrue(iterator.hasNext());
    assertEquals(hobbyTodo, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(schoolTodo2, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(cookingTodo, iterator.next());
    assertFalse(iterator.hasNext());
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void nextOutOfBounds() {
    assertTrue(iterator.hasNext());
    assertEquals(hobbyTodo, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(schoolTodo2, iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(cookingTodo, iterator.next());
    assertFalse(iterator.hasNext());
    iterator.next();
  }

  @Test
  public void testEqualsReflexivity() {
    assertTrue(iterator.equals(iterator));
  }

  @Test
  public void testEqualsSymmetry() {
    assertTrue(iterator.equals(iterator2) &&
        iterator2.equals(iterator));
  }

  @Test
  public void testEqualsTransitivity() {
    IncompleteIterator iterator3 = new IncompleteIterator(todoList);
    assertTrue(iterator.equals(iterator2) &&
        iterator2.equals(iterator3) &&
        iterator.equals(iterator3));
  }

  @Test
  public void testEqualsConsistency() {
    boolean result = iterator.equals(iterator2);
    assertEquals(result, iterator.equals(iterator2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(iterator.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(iterator.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() {
    IncompleteIterator diffIndex = new IncompleteIterator(todoList);
    diffIndex.next();
    assertFalse(iterator.equals(diffIndex));

    ArrayList<Todo> emptyList = new ArrayList<>();
    IncompleteIterator diffList = new IncompleteIterator(emptyList);
    assertFalse(iterator.equals(diffList));
  }

  @Test
  public void testHashCodeSelfConsistency() {
    assertTrue(iterator.hashCode() == iterator.hashCode());
  }

  @Test
  public void testHashCodeConsistency() {
    assertTrue(iterator.hashCode() == iterator2.hashCode());
  }

  @Test
  public void testHashCodeDifference() {
    ArrayList<Todo> emptyList = new ArrayList<>();
    IncompleteIterator diffList = new IncompleteIterator(emptyList);
    assertFalse(iterator.hashCode() == diffList.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("IncompleteIterator {" +
            "todoList = [Todo{text='Finish A6', "
            + "completed=true, dueDate=2021-04-21, priority=2, category='School'" +
            ", ID=1}, Todo{text='Make clothes', completed=false, dueDate=null, "
            + "priority=3, category='Hobby', ID=2}, Todo{text='Finish HW9', "
            + "completed=false, dueDate=2021-04-22, priority=1, category='school'" +
            ", ID=3}, Todo{text='Make tamales', completed=false, dueDate=2021-05-10, "
            + "priority=3, category='Cooking', ID=4}, Todo{text='Complete scholarship essay', "
            + "completed=true, dueDate=2021-04-15, priority=2, category='School', ID=5}], "
            + "currentIndex = 0}",
        iterator.toString());
  }
}