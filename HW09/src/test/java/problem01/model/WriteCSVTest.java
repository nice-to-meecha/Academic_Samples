package problem01.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import problem01.controller.Todo;
import problem01.view.InvalidNullArgumentException;

public class WriteCSVTest {

  WriteCSV writeCSV;
  WriteCSV writeCSV2;

  ClassLoader classloader;
  InputStream csvStream;
  InputStreamReader csvStreamReader;
  BufferedReader csv;

  URL writeCSVStream;
  BufferedWriter writeToCSV;

  private Todo schoolTodo1 = new Todo("Finish A6", true,
      LocalDate.of(2021, 4, 21), 2, "School", 1);
  private Todo hobbyTodo = new Todo("Make clothes", false, null, null,
      "Hobby", 2);
  private Todo schoolTodo2 = new Todo("Finish HW9", false,
      LocalDate.of(2021, 4, 22), 1, "school", 3);
  private Todo cookingTodo = new Todo("Make tamales", false,
      null, 3, "Cooking", 4);
  private List<Todo> todoList = new ArrayList<>();
  private List<Integer> titleOrder = new ArrayList<>(Arrays.asList(1, 0, 5, 2, 4, 3));

  @Before
  public void setUp() throws IOException, InvalidNullArgumentException {
    todoList.add(schoolTodo1);
    todoList.add(hobbyTodo);
    todoList.add(schoolTodo2);
    todoList.add(cookingTodo);
    writeCSV = new WriteCSV(todoList, titleOrder);
    writeCSV2 = new WriteCSV(todoList, titleOrder);

    classloader = Thread.currentThread().getContextClassLoader();
    csvStream = classloader.getResourceAsStream("todos.csv");
    csvStreamReader = new InputStreamReader(csvStream);
    csv = new BufferedReader(csvStreamReader);

    writeCSVStream = classloader.getResource("csv_to_be_written.csv");
    writeToCSV = new BufferedWriter(new FileWriter(writeCSVStream.getFile()));
  }

  @Test (expected = InvalidNullArgumentException.class)
  public void writeCSVConstructorNullTodoList() throws InvalidNullArgumentException {
    WriteCSV nullWriteCsv = new WriteCSV(null, titleOrder);
  }

  @Test (expected = InvalidNullArgumentException.class)
  public void writeCSVConstructorNullHeaderTitleOrderList() throws InvalidNullArgumentException {
    WriteCSV nullWriteCsv = new WriteCSV(todoList ,null);
  }

  @Test
  public void writeContentsToCSV() throws IOException, InvalidNullArgumentException,
      NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    writeCSV.writeContentsToCSV(writeToCSV);
    writeToCSV.close();

    InputStream writtenStream = classloader.getResourceAsStream("csv_to_be_written.csv");
    InputStreamReader writtenStreamReader = new InputStreamReader(writtenStream);
    BufferedReader writtenCSV = new BufferedReader(writtenStreamReader);

    ReadCSV result = new ReadCSV();
    result.splitCSV(writtenCSV);

    Map<String, ArrayList<String>> csvMap = new HashMap<>();
    String[] header = {"text", "id", "category", "completed", "priority", "due"};
    csvMap.put("header", new ArrayList<>(Arrays.asList(header)));
    String[] id1 = {"Finish A6", "1", "School", "true", "2", "4/21/2021"};
    csvMap.put("1", new ArrayList<>(Arrays.asList(id1)));
    String[] id2 = {"Make clothes", "2", "Hobby", "false", "?", "?"};
    csvMap.put("2", new ArrayList<>(Arrays.asList(id2)));
    String[] id3 = {"Finish HW9", "3", "school", "false", "1", "4/22/2021"};
    csvMap.put("3", new ArrayList<>(Arrays.asList(id3)));
    String[] id4 = {"Make tamales", "4", "Cooking", "false", "3", "?"};
    csvMap.put("4", new ArrayList<>(Arrays.asList(id4)));

    assertEquals(csvMap, result.getCSV());
  }

  @Test
  public void testEqualsReflexivity() {
    assertTrue(writeCSV.equals(writeCSV));
  }

  @Test
  public void testEqualsSymmetry() {
    assertTrue(writeCSV.equals(writeCSV2) &&
        writeCSV2.equals(writeCSV));
  }

  @Test
  public void testEqualsTransitivity() throws InvalidNullArgumentException {
    WriteCSV writeCSV3 = new WriteCSV(todoList, titleOrder);
    assertTrue(writeCSV.equals(writeCSV2) &&
        writeCSV2.equals(writeCSV3) &&
        writeCSV.equals(writeCSV3));
  }

  @Test
  public void testEqualsConsistency() {
    boolean result = writeCSV.equals(writeCSV2);
    assertEquals(result, writeCSV.equals(writeCSV2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(writeCSV.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(writeCSV.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() throws InvalidNullArgumentException {
    List<Todo> smallerList = new ArrayList<>(Arrays.asList(schoolTodo2, cookingTodo));
    WriteCSV diffList = new WriteCSV(smallerList, titleOrder);
    assertFalse(writeCSV.equals(diffList));

    List<Integer> standardTitleOrder = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
    WriteCSV diffTitleOrder = new WriteCSV(todoList, standardTitleOrder);
    assertFalse(writeCSV.equals(diffTitleOrder));
  }

  @Test
  public void testHashCodeSelfConsistency() {
    assertTrue(writeCSV.hashCode() == writeCSV.hashCode());
  }

  @Test
  public void testHashCodeConsistency() {
    assertTrue(writeCSV.hashCode() == writeCSV2.hashCode());
  }

  @Test
  public void testHashCodeDifference() throws InvalidNullArgumentException {
    List<Todo> smallerList = new ArrayList<>(Arrays.asList(schoolTodo2, cookingTodo));
    WriteCSV diffList = new WriteCSV(smallerList, titleOrder);
    assertFalse(writeCSV.hashCode() == diffList.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("WriteCSV {todoList = [Todo{text='Finish A6', "
        + "completed=true, dueDate=2021-04-21, priority=2, category='School'"
        + ", ID=1}, Todo{text='Make clothes', completed=false, dueDate=null, "
        + "priority=null, category='Hobby', ID=2}, Todo{text='Finish HW9', "
        + "completed=false, dueDate=2021-04-22, priority=1, category='school'"
        + ", ID=3}, Todo{text='Make tamales', completed=false, dueDate=null, "
        + "priority=3, category='Cooking', ID=4}], headerColumnOrder = [1, 0, 5, 2, 4, 3]}",
        writeCSV.toString());
  }
}