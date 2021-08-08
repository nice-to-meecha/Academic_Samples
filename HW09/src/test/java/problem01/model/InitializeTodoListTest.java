package problem01.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import problem01.controller.Todo;
import problem01.view.InvalidArgumentException;
import problem01.view.InvalidNullArgumentException;

public class InitializeTodoListTest {

  private InitializeTodoList initializer;
  private InitializeTodoList initializer2;

  private ReadCSV readCSV = new ReadCSV();

  private ClassLoader classloader = Thread.currentThread().getContextClassLoader();
  private InputStream csvStream = classloader.getResourceAsStream("todos.csv");
  private InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
  private BufferedReader csv = new BufferedReader(csvStreamReader);


  @Before
  public void setUp() throws Exception {
    readCSV.splitCSV(csv);
    initializer = new InitializeTodoList(readCSV.getCSV(), readCSV.getHeaderTitleOrder());
    initializer2 = new InitializeTodoList(readCSV.getCSV(), readCSV.getHeaderTitleOrder());
  }

  @Test
  public void parseCSVEntries() throws InvalidArgumentException {
    List<Todo> actualList = initializer.parseCSVEntries();

    Todo todo1 = new Todo("Finish HW9", false,
        LocalDate.of(2020, 3, 22), 1, "school", 1);
    Todo todo2 = new Todo("Mail passport", true,
        LocalDate.of(2020, 2, 28), null, null, 2);
    Todo todo3 = new Todo("Study for finals", false,
        null, 2, "school", 3);
    Todo todo4 = new Todo("Clean the house", false,
        LocalDate.of(2020, 3, 22), 3, "home", 4);
    Todo todo5 = new Todo("Buy yarn for blanket, stuffed toy", true,
        null, null, "home", 5);
    List<Todo> expectedList = new ArrayList<>(Arrays.asList(todo1, todo2, todo3, todo4, todo5));

    assertEquals(expectedList, actualList);
  }

  @Test
  public void testEqualsReflexivity() {
    assertTrue(initializer.equals(initializer));
  }

  @Test
  public void testEqualsSymmetry() {
    assertTrue(initializer.equals(initializer2) &&
        initializer2.equals(initializer));
  }

  @Test
  public void testEqualsTransitivity() {
    InitializeTodoList initializer3 = new InitializeTodoList(readCSV.getCSV(),
        readCSV.getHeaderTitleOrder());
    assertTrue(initializer.equals(initializer2) &&
        initializer2.equals(initializer3) &&
        initializer.equals(initializer3));
  }

  @Test
  public void testEqualsConsistency() {
    boolean result = initializer.equals(initializer2);
    assertEquals(result, initializer.equals(initializer2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(initializer.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(initializer.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() throws IOException, InvalidNullArgumentException {
    InputStream diffStream = classloader.getResourceAsStream("diff_column_order.csv");
    InputStreamReader diffStreamReader = new InputStreamReader(diffStream);
    BufferedReader diff = new BufferedReader(diffStreamReader);

    ReadCSV readDiffCSV = new ReadCSV();
    readDiffCSV.splitCSV(diff);
    InitializeTodoList diffCSV = new InitializeTodoList(readDiffCSV.getCSV(),
        readCSV.getHeaderTitleOrder());
    assertFalse(initializer.equals(diffCSV));

    InitializeTodoList diffTitleOrder = new InitializeTodoList(readCSV.getCSV(),
        readDiffCSV.getHeaderTitleOrder());
    assertFalse(initializer.equals(diffTitleOrder));
  }

  @Test
  public void testHashCodeSelfConsistency() {
    assertTrue(initializer.hashCode() == initializer.hashCode());
  }

  @Test
  public void testHashCodeConsistency() {
    assertTrue(initializer.hashCode() == initializer2.hashCode());
  }

  @Test
  public void testHashCodeDifferent() {
    InitializeTodoList diffTitleOrder = new InitializeTodoList(readCSV.getCSV(),
        new ArrayList<>(Arrays.asList(0, 3, 5, 2, 1, 4)));
    assertFalse(initializer.hashCode() == diffTitleOrder.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("InitializeTodoList {todoMap = "
            + "{1=[1, Finish HW9, false, 3/22/2020, 1, school], "
            + "2=[2, Mail passport, true, 2/28/2020, ?, ?], "
            + "3=[3, Study for finals, false, ?, 2, school], "
            + "4=[4, Clean the house, false, 3/22/2020, 3, home], "
            + "5=[5, Buy yarn for blanket, stuffed toy, true, ?, ?, home], "
            + "header=[id, text, completed, due, priority, category]}, "
            + "headerColumnOrder = [0, 1, 2, 3, 4, 5]}",
        initializer.toString());
  }

  @Test
  public void getTodoMap() {
    Map<String, ArrayList<String>> expectedMap = new HashMap<>();
    expectedMap.put("1", new ArrayList<>(
        Arrays.asList("1", "Finish HW9", "false", "3/22/2020", "1", "school")));
    expectedMap.put("2", new ArrayList<>(
        Arrays.asList("2", "Mail passport", "true", "2/28/2020", "?", "?")));
    expectedMap.put("3", new ArrayList<>(
        Arrays.asList("3", "Study for finals", "false", "?", "2", "school")));
    expectedMap.put("4", new ArrayList<>(
        Arrays.asList("4", "Clean the house", "false", "3/22/2020", "3", "home")));
    expectedMap.put("5", new ArrayList<>(
        Arrays.asList("5", "Buy yarn for blanket, stuffed toy", "true", "?", "?", "home")));
    expectedMap.put("header", new ArrayList<>(
        Arrays.asList("id", "text", "completed", "due", "priority", "category")));

    assertEquals(expectedMap, initializer.getTodoMap());
  }

  @Test
  public void getHeaderColumnOrder() {
    List<Integer> expectedOrder = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
    assertEquals(expectedOrder, initializer.getHeaderColumnOrder());
  }
}