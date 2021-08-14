package problem01.controller;

import static org.junit.Assert.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import problem01.view.InvalidArgumentException;

public class ParserTest {

  Parser test1;
  Parser same;
  Parser diffArgs;
  Parser diffTaskManager;
  Parser diffValidateInput;
  Parser diffPathToFile;
  Parser diffAddTodo;
  Parser diffCompleteTodo;
  Parser diffDisplayTodo;

  Todo todo;
  Todo todo2;
  List<Todo> todoList;

  String[] cliArgs;

  @Before
  public void setUp() throws Exception {
    todo = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 1);
    todo2 = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 2);
    todoList = new ArrayList<>();
    todoList.add(todo);
    cliArgs = new String[]{"--csv-file", "<path/to/file>"};
    test1 = new Parser(cliArgs);
    same = new Parser(cliArgs);
    diffAddTodo = new Parser(new String[]{"--csv-file", "<path/to/file>", "--add-todo", "--completed", "--due", "04/22/2021",
        "--priority", "2", "--category", "school", "--todo-text", "description of todo"});
    diffCompleteTodo = new Parser(new String[]{"--csv-file", "<path/to/file>", "--complete-todo", "1"});
    diffDisplayTodo = new Parser(new String[]{"--csv-file", "<path/to/file>", "--display", "--show-incomplete", "--show-category",
        "school", "--sort-by-date", "--sort-by-priority"});
  }

  @Test
  public void parseCommandLine() {
  }

  @Test
  public void checkAddArgs() {
    try {
      TaskManager tm = new TaskManager(new ArrayList<>());
      HashMap<String, String> addTest = new HashMap<>();
      addTest.put("--completed", "true");
      addTest.put("--due", "04/22/2021");
      addTest.put("--priority", "2");
      addTest.put("--category", "school");
      addTest.put( "--todo-text", "description of todo");
      tm.createAddTodo(addTest);
      assertTrue(tm.equals(diffAddTodo.parseCommandLine()));
    } catch (InvalidArgumentException e) {
    }

  }

  @Test
  public void checkCompleteArgs() {
    try {
      todo.setCompleted(true);
      List <Todo> todoList2 = new ArrayList<>();
      todoList2.add(todo);
      TaskManager tm = new TaskManager(new ArrayList<>());
      tm.createCompleteTodo("1");
      assertTrue(tm.equals(diffCompleteTodo.parseCommandLine()));
    } catch (InvalidArgumentException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void checkDisplayArgs() {
    try {
      TaskManager tm = new TaskManager(new ArrayList<>());
      HashMap<String, String> displayTest = new HashMap<>();
      displayTest.put("--show-incomplete", "true");
      displayTest.put("--show-category", "school");
      displayTest.put("--sort-by-date", "true");
      displayTest.put("--sort-by-priority", "true");
      tm.createDisplayTable(displayTest);
      assertTrue(tm.equals(diffDisplayTodo.parseCommandLine()));
    } catch (InvalidArgumentException e) {
    }
  }

  @Test
  public void getPathToFile() {
    try {
      diffDisplayTodo.parseCommandLine();
      assertEquals("<path/to/file>", diffDisplayTodo.getPathToFile());
    } catch (InvalidArgumentException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void setTaskManager (){
    TaskManager newTM = new TaskManager(todoList);
    test1.setTaskManager(todoList);
    assertTrue(test1.getTaskManager().equals(newTM));

  }

  @Test
  public void testEquals() throws InvalidArgumentException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    URL csvURL = classLoader.getResource("todos.csv");
    String path = csvURL.getPath();

    // Null case
    assertFalse(test1.equals(null));
    // Different object type
    assertFalse(test1.equals(new NullPointerException()));
    // Equal to itself
    assertTrue(test1.equals(test1));
    // Equal to object same values
    assertTrue(test1.equals(same));
    // Not equal different args

    // Not equal different taskManager
    test1.setTaskManager(todoList);
    assertFalse(test1.equals(same));

    // Diff addTodo
    test1 = new Parser(new String[]{"--csv-file", path, "--add-todo", "todo-text", "blah"});
    same = new Parser(new String[]{"--csv-file", path, "--add-todo", "todo-text", "blah"});
    test1.parseCommandLine();
    assertFalse(test1.equals(same));

    // Diff completeTodo
    test1 = new Parser(new String[]{"--csv-file", path, "--complete-todo", "1"});
    same = new Parser(new String[]{"--csv-file", path, "--complete-todo", "1"});
    test1.parseCommandLine();
    assertFalse(test1.equals(same));

    // Diff displayTodo
    test1 = new Parser(new String[]{"--csv-file", path, "--display"});
    same = new Parser(new String[]{"--csv-file", path, "--display"});
    test1.parseCommandLine();
    assertFalse(test1.equals(same));


    // Not equal different pathToFile
    String[] diffPath = new String[]{"--csv-file", "<other/path/to/file>", "--display"};
    Parser test2 = new Parser(diffPath);
    try {
      test2.parseCommandLine();
    } catch (InvalidArgumentException e) {
    }
    assertFalse(test1.equals(test2));
    // Not equal different addTodo
    assertFalse(test1.equals(diffAddTodo));
    // Not equal different completeTodo
    assertFalse(test1.equals(diffCompleteTodo));
    // Not equal different displayTodo
    assertFalse(test1.equals(diffCompleteTodo));

  }

  @Test
  public void testHashCode() {
    // Same
    assertTrue(test1.hashCode() == test1.hashCode());
    // Consistent
    assertTrue(test1.hashCode() == same.hashCode());
    // Different
    assertFalse(test1.hashCode() == diffAddTodo.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Parser{args=[--csv-file, <path/to/file>],"
        + " taskManager=TaskManager{taskList=[], todoList=[], ID=0}, "
        + "validate=ValidateInput{cliArgs=[--csv-file, <path/to/file>]}, pathToFile='', "
        + "addTodo=false, completeTodo=false, displayTodo=false}", test1.toString());
  }
}