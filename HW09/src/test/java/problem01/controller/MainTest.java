package problem01.controller;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

  ClassLoader classLoader;
  String path;

  @Before
  public void setUp() throws Exception {
    classLoader = Thread.currentThread().getContextClassLoader();
    URL csvURL = classLoader.getResource("todos.csv");
    path = csvURL.getPath();

    // For the sake of test coverage
    Main main = new Main();
  }

  @Test
  public void main() throws IOException {
    String[] cliArgs = new String[]{"--csv-file", path, "--display"};
    Main.main(cliArgs);

    BufferedReader csv = new BufferedReader(new FileReader(path));
    csv.readLine();
    String actualLine = csv.readLine();
    String expectedLine = "\"1\",\"Finish HW9\",\"false\",\"3/22/2020\",\"1\",\"school\"";
    assertEquals(expectedLine, actualLine);
  }

  @Test
  public void mainIOException() {
    String[] cliArgs = new String[]{"--csv-file", "orange", "--complete-todo", "1"};
    Main.main(cliArgs);
  }

  @Test
  public void mainOuterInvalidArgumentException() {
    String[] cliArgs = new String[]{"--csv-file", "--add-todo"};
    Main.main(cliArgs);
  }

  @Test
  public void mainInnerInvalidArgumentException() {
    String[] cliArgs = new String[]{"--csv-file", path, "--add-todo"};
    Main.main(cliArgs);
  }
}