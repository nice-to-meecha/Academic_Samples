package problem01.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import problem01.view.InvalidNullArgumentException;

public class ReadCSVTest {

  ReadCSV readCSV;
  ReadCSV readCSV2;

  ClassLoader classloader;
  InputStream csvStream;
  InputStreamReader csvStreamReader;
  BufferedReader csv;

  @Before
  public void setUp() throws IOException {
    readCSV = new ReadCSV();
    readCSV2 = new ReadCSV();
    classloader = Thread.currentThread().getContextClassLoader();
    csvStream = classloader.getResourceAsStream("todos.csv");
    csvStreamReader = new InputStreamReader(csvStream);
    csv = new BufferedReader(csvStreamReader);
    csv.mark(8000);
  }

  @Test
  public void splitCSV() throws IOException, InvalidNullArgumentException {
    readCSV.splitCSV(csv);

    Map<String, ArrayList<String>> csvMap = new HashMap<>();
    String[] header = {"id", "text", "completed", "due", "priority", "category"};
    csvMap.put("header", new ArrayList<>(Arrays.asList(header)));
    String[] id1 = {"1", "Finish HW9", "false", "3/22/2020", "1", "school"};
    csvMap.put("1", new ArrayList<>(Arrays.asList(id1)));
    String[] id2 = {"2", "Mail passport", "true", "2/28/2020", "?", "?"};
    csvMap.put("2", new ArrayList<>(Arrays.asList(id2)));
    String[] id3 = {"3", "Study for finals", "false", "?", "2", "school"};
    csvMap.put("3", new ArrayList<>(Arrays.asList(id3)));
    String[] id4 = {"4", "Clean the house", "false", "3/22/2020", "3", "home"};
    csvMap.put("4", new ArrayList<>(Arrays.asList(id4)));
    String[] id5 = {"5","Buy yarn for blanket, stuffed toy","true","?","?","home"};
    csvMap.put("5", new ArrayList<>(Arrays.asList(id5)));

    assertEquals(csvMap, readCSV.getCSV());
  }

  @Test
  public void splitCSVEmpty() throws IOException, InvalidNullArgumentException {
    InputStream emptyStream = classloader.getResourceAsStream("csv_to_be_written.csv");
    InputStreamReader emptyStreamReader = new InputStreamReader(emptyStream);
    BufferedReader emptyCSV = new BufferedReader(emptyStreamReader);
    readCSV.splitCSV(emptyCSV);

    Map<String, ArrayList<String>> headerMap = new HashMap<>();
    String[] header = {"id", "text", "completed", "due", "priority", "category"};
    headerMap.put("header", new ArrayList<>(Arrays.asList(header)));
    assertEquals(headerMap, readCSV.getCSV());
  }

  @Test (expected = IOException.class)
  public void splitCSVIOException() throws IOException, InvalidNullArgumentException {
    readCSV.splitCSV(new BufferedReader(new FileReader("oranges")));
  }

  @Test (expected = InvalidNullArgumentException.class)
  public void splitCSVNull() throws IOException, InvalidNullArgumentException {
    readCSV.splitCSV(null);
  }

  @Test
  public void testEqualsReflexivity() {
    assertTrue(readCSV.equals(readCSV));
  }

  @Test
  public void testEqualsSymmetry() {
    assertTrue(readCSV.equals(readCSV2) &&
        readCSV2.equals(readCSV));
  }

  @Test
  public void testEqualsTransitivity() {
    ReadCSV readCSV3 = new ReadCSV();
    assertTrue(readCSV.equals(readCSV2) &&
        readCSV2.equals(readCSV3) &&
        readCSV.equals(readCSV3));
  }

  @Test
  public void testEqualsConsistency() {
    boolean result = readCSV.equals(readCSV2);
    assertEquals(result, readCSV.equals(readCSV2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(readCSV.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(readCSV.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() throws IOException, InvalidNullArgumentException {
    // Same title order, diff csv
    ReadCSV diffCsvAndOrder = new ReadCSV();
    diffCsvAndOrder.splitCSV(csv);
    assertFalse(readCSV.equals(diffCsvAndOrder));

    // Diff order, diff csv
    diffCsvAndOrder.getHeaderTitleOrder();
    assertFalse(readCSV.equals(diffCsvAndOrder));

    // Diff order, same csv
    csv.reset();
    readCSV.splitCSV(csv);
    assertFalse(readCSV.equals(diffCsvAndOrder));
  }

  @Test
  public void testHashCodeSelfConsistency() {
    assertTrue(readCSV.hashCode() == readCSV.hashCode());
  }

  @Test
  public void testHashCodeConsistency() {
    assertTrue(readCSV.hashCode() == readCSV2.hashCode());
  }

  @Test
  public void testHashCodeSelfDifferent() throws IOException, InvalidNullArgumentException {
    ReadCSV diffCSV = new ReadCSV();
    diffCSV.splitCSV(csv);
    assertFalse(readCSV.hashCode() == diffCSV.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("ReadCSV {csv = {}, headerTitleOrder = []}",
        readCSV.toString());
  }

  @Test
  public void getCSVEmptyMap() {
    Map<String, ArrayList<String>> emptyMap = new HashMap<>();
    assertEquals(emptyMap, readCSV.getCSV());
  }

  @Test
  public void getHeaderOnlyCSV() throws IOException, InvalidNullArgumentException {
    InputStream csvStream = classloader.getResourceAsStream("empty.csv");
    InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
    BufferedReader emptyCSV = new BufferedReader(csvStreamReader);
    readCSV.splitCSV(emptyCSV);

    Map<String, ArrayList<String>> headerOnlyMap = new HashMap<>();
    String[] header = {"id", "text", "completed", "due", "priority", "category"};
    headerOnlyMap.put("header", new ArrayList<>(Arrays.asList(header)));

    assertEquals(headerOnlyMap, readCSV.getCSV());
  }

  @Test
  public void getEmptyHeaderTitleOrder() {
    List<Integer> emptyList = new ArrayList<>();
    assertEquals(emptyList, readCSV.getHeaderTitleOrder());
  }

  @Test
  public void getDifferentHeaderTitleOrder() throws IOException, InvalidNullArgumentException {
    InputStream diffOrderStream = classloader.getResourceAsStream("diff_column_order.csv");
    InputStreamReader diffOrderStreamReader = new InputStreamReader(diffOrderStream);
    BufferedReader diffOrder = new BufferedReader(diffOrderStreamReader);
    readCSV.splitCSV(diffOrder);

    List<Integer> expectedOrder = new ArrayList<>(Arrays.asList(1, 0, 5, 2, 4, 3));
    assertEquals(expectedOrder, readCSV.getHeaderTitleOrder());
  }
}