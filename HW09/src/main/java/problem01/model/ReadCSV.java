package problem01.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import problem01.view.InvalidNullArgumentException;

/**
 * Class which reads and stored To-do information from a provided .csv file.
 */
public class ReadCSV implements IReadCSV {

  private static final ArrayList<String> HEADER_TITLES = new ArrayList<>(
      Arrays.asList("id", "text", "completed", "due", "priority", "category"));

  private Map<String, ArrayList<String>> csv;
  private List<Integer> headerTitleOrder;

  /**
   * Generates a ReadCSV object, initializing its csv and headerTitleOrder fields
   * with an empty HashMap and ArrayList, respectively.
   */
  public ReadCSV() {
    this.csv = new HashMap<>();
    this.headerTitleOrder = new ArrayList<>();
  }

  /**
   * Splits all contents of a .csv file, storing them within a HashMap -- attributing
   * rows to particular todos or headers -- for future use.
   *
   * @param csv - the .csv file retaining the content which will be split and stored,
   *            according to row
   *
   * @throws IOException - input/output exception, which is thrown if an error is
   * encountered while reading the provided .csv file.
   * @throws InvalidNullArgumentException - thrown when a null argument is utilized
   * in unsupported fashion.
   */
  @Override
  public void splitCSV(BufferedReader csv) throws IOException,
      InvalidNullArgumentException {
    CheckNullUtils.evaluate(csv);

    this.splitHeader(csv);

    String line;
    while ((line = csv.readLine()) != null) {
      ArrayList<String> todoInfo = this.splitRow(line);
      String id = todoInfo.get(this.csv.get("header").indexOf("id"));
      this.csv.put(id, todoInfo);
    }
  }

  /**
   * Splits the first row of the csv file (retaining the titles of each column),
   * storing its contents within a HashMap. If the file is empty, a default
   * ArrayList of header titles is stored instead.
   *
   * @param csv - the .csv file retaining the content which will be split and stored,
   *            according to row
   *
   * @throws IOException - input/output exception, which is thrown if an error is
   * encountered while reading the provided .csv file.
   */
  private void splitHeader(BufferedReader csv) throws IOException {
    String line;
    if ((line = csv.readLine()) != null) {
      ArrayList<String> todoInfo = this.splitRow(line);
      this.csv.put("header", todoInfo);
    }
    else {
      this.csv.put("header", HEADER_TITLES);
    }
  }

  /**
   * Splits a row of a .csv file, according to quotation marks, so as to
   * generate an array of Strings, with each element bearing information
   * stored within the provided file line.
   *
   * @param lineToSplit - a line of the .csv file to be split according to
   *                    quotation marks
   *
   * @return a List of Strings, with each element bearing information
   * stored within the provided file line.
   */
  private ArrayList<String> splitRow(String lineToSplit) {
    Pattern categoryPattern = Pattern.compile("^\"|\",\"|\"$");
    String[] information = categoryPattern.split(lineToSplit);
    ArrayList<String> newList = new ArrayList<>();
    for (String element: information) {
      if (!element.equals("")) {
        newList.add(element);
      }
    }
    return newList;
  }

  /**
   * Places the index values of column titles (stored within the HashMap,
   * associated with the header key) within the ArrayList of the
   * headerTitleOrder field, according to the HEADER_TITLES List.
   * Utilized for dynamic ordering of columns.
   *
   * Example: header appears as text, due, priority, category, completed, id
   * headerTitleOrder == [1, 3, 4, 5, 2, 0]
   */
  private void determineHeaderTitleOrder() {
    ArrayList<String> header = this.csv.get("header");
    this.headerTitleOrder = new ArrayList<>();
    if (header != null) {
      for (String title : header) {
        this.headerTitleOrder.add(HEADER_TITLES.indexOf(title));
      }
    }
  }

  /**
   * Determines whether a ReadCSV object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a ReadCSV object
   *
   * @return boolean indicating whether a ReadCSV object is equivalent
   * to another object
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    ReadCSV readCSV = (ReadCSV) o;
    return Objects.equals(this.headerTitleOrder, readCSV.headerTitleOrder) &&
        Objects.equals(this.csv, readCSV.csv);
  }

  /**
   * Determines the hashcode of a ReadCSV object
   *
   * @return the hashcode of a ReadCSV object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.csv, this.headerTitleOrder);
  }

  /**
   * Provides a string representation of a ReadCSV object
   *
   * @return a string representation of a ReadCSV object
   */
  @Override
  public String toString() {
    return "ReadCSV {" +
        "csv = " + this.csv +
        ", headerTitleOrder = " + this.headerTitleOrder +
        '}';
  }

  /**
   * Returns the information of a provided .csv file, stored within
   * a Map
   *
   * @return the information of a provided .csv file, stored within
   * a Map
   */
  public Map<String, ArrayList<String>> getCSV() {
    return this.csv;
  }

  /**
   * Returns the order of column titles (collectively referred to as the header),
   * in relation to the order of titles stored in the HEADER_TITLES ArrayList.
   *
   * @return List retaining the order of column titles, in relation to the order
   * of titles stored in the HEADER_TITLES ArrayList.
   */
  public List<Integer> getHeaderTitleOrder() {
    this.determineHeaderTitleOrder();
    return this.headerTitleOrder;
  }
}
