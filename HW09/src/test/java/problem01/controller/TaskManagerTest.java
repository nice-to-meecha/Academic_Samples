package problem01.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import problem01.Task;
import problem01.view.DisplayTable;
import problem01.view.InvalidArgumentException;

public class TaskManagerTest {

  Todo todo;
  Todo todo2;
  List<Todo> todoList;
  List<Todo> todoList2;
  List<Task> taskList;
  Integer id;
  TaskManager tm;
  TaskManager same;
  TaskManager diffTaskList;
  TaskManager diffTodoList;
  TaskManager diffID;

  @Before
  public void setUp() throws Exception {
    todo = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 1);
    todo2 = new Todo("Description", false, LocalDate.parse("2021-04-20"),
        1, "school", 2);
    todoList = new ArrayList<>();
    todoList2 = new ArrayList<>();
    todoList.add(todo);
    todoList2.add(todo2);
    taskList = new ArrayList<>();
    id = 1;
    tm = new TaskManager(todoList);
    same = new TaskManager(todoList);
    diffTaskList = new TaskManager(todoList);
    diffTodoList = new TaskManager(todoList2);
    diffID = new TaskManager(todoList2);

  }

  @Test
  public void testAddTask() {
    Task complete = new CompleteTodo(1);
    taskList.add(complete);
    tm.addTask(complete);
    assertTrue(taskList.equals(tm.getTaskList()));
  }

  @Test
  public void testValidateArgsAllTasks() {
    Task complete = new CompleteTodo(1);
    tm.addTask(complete);
    try {
      tm.validateArgsAllTasks();
    } catch (InvalidArgumentException e) {
    }
  }

  @Test(expected = InvalidArgumentException.class)
  public void testValidateTaskList() throws InvalidArgumentException {
    HashMap <String, String> args = new HashMap<>();
    tm.createAddTodo(args);
    tm.createDisplayTable(args);
    tm.createCompleteTodo("1");
    tm.createAddTodo(args);
    tm.validateArgsAllTasks();
  }

  @Test
  public void testPerformAllTasks() {
    Task complete = new CompleteTodo(1);
    tm.addTask(complete);
    tm.performAllTasks();
    List<Todo> testTodoList = complete.performTask(todoList);
    assertTrue(tm.getTodoList().equals(testTodoList));
  }

  @Test
  public void testCreateAddTodo() {
    try {
      HashMap<String, String> args = new HashMap<>();
      diffTaskList.createAddTodo(args);
      AddTodo newAddTodo = new AddTodo(2);
      List<Task> testList = new ArrayList<>();
      testList.add(newAddTodo);
      assertTrue(diffTaskList.getTaskList().equals(testList));

      args.put(ArgConstants.TODO_TEXT, "this is the description of the todo");
      args.put(ArgConstants.COMPLETED, "true");
      args.put(ArgConstants.PRIORITY, "2");
      args.put(ArgConstants.CATEGORY, "school");
      args.put(ArgConstants.DUE, "04/22/2021");
      tm.createAddTodo(args);

      AddTodo addTodo = new AddTodo(2);
      addTodo.setTodoText("this is the description of the todo");
      addTodo.setCompleted(true);
      addTodo.setPriority(2);
      addTodo.setCategory("school");
      addTodo.setDueDate("04/22/2021");
      List<Task> test = new ArrayList<>();
      test.add(addTodo);

      assertTrue(tm.getTaskList().equals(test));
      assertTrue(tm.getId().equals(2));
    } catch (InvalidArgumentException e) {
    }
  }

  @Test
  public void testGenerateID() {
    try {
      todoList.add(new Todo("Description", false, LocalDate.parse("2021-04-20"),
          1, "school", 2));
      TaskManager newTM = new TaskManager(todoList);
      HashMap<String, String> args = new HashMap<>();

      args.put(ArgConstants.TODO_TEXT, "this is the description of the todo");
      args.put(ArgConstants.COMPLETED, "true");
      args.put(ArgConstants.PRIORITY, "2");
      args.put(ArgConstants.CATEGORY, "school");
      args.put(ArgConstants.DUE, "04/22/2021");
      tm.createAddTodo(args);
      assertTrue(tm.getId().equals(3));
    } catch (InvalidArgumentException e) {
    }
  }

  @Test
  public void generateIDWithSingleTodoList() throws InvalidArgumentException {
    List<Todo> singleList = new ArrayList<>(Arrays.asList(new Todo("Fake errands", true,
        LocalDate.of(2021, 4, 22), null, "nothin'", 0)));
    TaskManager taskManager = new TaskManager(singleList);

    HashMap<String, String> args = new HashMap<>();

    args.put(ArgConstants.TODO_TEXT, "this is the description of the todo");
    args.put(ArgConstants.COMPLETED, "true");
    args.put(ArgConstants.PRIORITY, "2");
    args.put(ArgConstants.CATEGORY, "school");
    args.put(ArgConstants.DUE, "04/22/2021");
    taskManager.createAddTodo(args);
    assertEquals((Integer) 1, taskManager.getId());
  }

  @Test(expected = InvalidArgumentException.class)
  public void testCreatAddTodoFail() throws InvalidArgumentException {
    HashMap<String, String> args = new HashMap<>();
    diffTaskList.createAddTodo(args);
    AddTodo newAddTodo = new AddTodo(2);
    List<Task> testList = new ArrayList<>();
    testList.add(newAddTodo);
    assertTrue(diffTaskList.getTaskList().equals(testList));

    args.put(ArgConstants.TODO_TEXT, "this is the description of the todo");
    args.put(ArgConstants.COMPLETED, "true");
    args.put(ArgConstants.PRIORITY, "tree");
    args.put(ArgConstants.CATEGORY, "school");
    args.put(ArgConstants.DUE, "04/22/2021");
    tm.createAddTodo(args);
  }

  @Test
  public void testCreateCompleteTodo() {
    try {
      tm.createCompleteTodo("1");
    } catch (InvalidArgumentException e) {
      e.printStackTrace();
    }
    taskList.add(new CompleteTodo(1));
    assertTrue(tm.getTaskList().equals(taskList));
  }

  @Test(expected = InvalidArgumentException.class)
  public void testCreateCompleteTodoFail() throws InvalidArgumentException {
    tm.createCompleteTodo("yolo");
  }

  @Test
  public void testCreateDisplayTable() {
    HashMap<String, String> args = new HashMap<>();
    diffTaskList.createDisplayTable(args);
    DisplayTable dt1 = new DisplayTable();
    List<Task> testList = new ArrayList<>();
    testList.add(dt1);
    assertTrue(diffTaskList.getTaskList().equals(testList));

    args.put(ArgConstants.SHOW_INCOMPLETE, "true");
    args.put(ArgConstants.SHOW_CATEGORY, "school");
    args.put(ArgConstants.SORT_BY_DATE, "true");
    args.put(ArgConstants.SORT_BY_PRIORITY, "true");
    tm.createDisplayTable(args);

    DisplayTable dt2 = new DisplayTable();
    dt2.setShowIncomplete(true);
    dt2.setShowCategory("school");
    dt2.setSortByDate(true);
    dt2.setSortByPriority(true);
    List<Task> test = new ArrayList<>();
    test.add(dt2);

    assertTrue(tm.getTaskList().equals(test));

  }

  @Test
  public void testGetTaskList() {
    assertTrue(tm.getTaskList().equals(taskList));
  }

  @Test
  public void testGetID() {
    assertTrue(tm.getId().equals(0));
  }

  @Test
  public void testGetTodoList() {
    assertEquals(todoList, tm.getTodoList());
  }

  @Test
  public void testEquals1() {
    // Null case
    assertFalse(tm.equals(null));
    // Different object type
    assertFalse(tm.equals(new NullPointerException()));
    // Equal to itself
    assertTrue(tm.equals(tm));
    // Equal to object same values
    assertTrue(tm.equals(same));
    // Different todoList
    assertFalse(tm.equals(diffTodoList));
    // Different taskList
    try {
      diffTaskList.createCompleteTodo("1");
    } catch (InvalidArgumentException e) {
      e.printStackTrace();
    }
    assertFalse(tm.equals(diffTaskList));
    // Different ID
    diffID.setId(5);
    assertFalse(tm.equals(diffID));
  }

  @Test
  public void testHashCode1() {
    // Same
    assertTrue(tm.hashCode() == tm.hashCode());
    // Consistent
    assertTrue(tm.hashCode() == same.hashCode());
    // Different
    assertFalse(tm.hashCode() == diffTodoList.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("TaskManager{taskList=[], todoList=[Todo{text='Description',"
        + " completed=false, dueDate=2021-04-20, priority=1, category='school', ID=1}], ID=0}",
        tm.toString());
  }
}