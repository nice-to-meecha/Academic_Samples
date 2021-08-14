package problem01.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import problem01.view.InvalidArgumentException;

public class ValidateInputTest {

  ValidateInput vi;
  ValidateInput same;
  ValidateInput diff;
  String[] cliArgs;

  @Before
  public void setUp() throws Exception {
    cliArgs = new String[]{"--csv-file", "<path/to/file>"};
    vi = new ValidateInput(cliArgs);
    same = new ValidateInput(cliArgs);
    diff = new ValidateInput(new String[]{"--todo-text", "<description of todo>}"});
  }

  @Test(expected = InvalidArgumentException.class)
  public void checkCSVFail() throws InvalidArgumentException {
    diff.checkCSV();
  }

  @Test
  public void checkCSVSuccess() {
    try {
      assertEquals("<path/to/file>", vi.checkCSV());
    } catch (InvalidArgumentException e) {
    }
  }

  @Test(expected = InvalidArgumentException.class)
  public void checkNoTaskFail() throws InvalidArgumentException {
    vi.checkNoTask(false, false, false);
  }

  @Test
  public void checkNoTaskSuccess() {
    try {
      vi.checkNoTask(true, false, false);
      vi.checkNoTask(false, true, false);
      vi.checkNoTask(false, false, true);
    } catch (InvalidArgumentException e) {
    }
  }

  @Test(expected = InvalidArgumentException.class)
  public void checkFollowingArg() throws InvalidArgumentException {
    String[] args = new String[]{"--csv-file", "--add-todo"};
    ValidateInput test1 = new ValidateInput(args);
    test1.checkFollowingArg(0, "--csv-file", "<path/to/file>");
  }

  @Test(expected = InvalidArgumentException.class)
  public void checkFollowingArgOutOfBounds() throws InvalidArgumentException {
    String[] args = new String[]{"--csv-file", "--todo-text"};
    ValidateInput test1 = new ValidateInput(args);
    test1.checkFollowingArg(1, "--todo-text", "<description of todo>");
  }

  @Test
  public void getCliArgs() {
    assertTrue(vi.getCliArgs().equals(cliArgs));
  }

  @Test
  public void testEquals() {
    // Null case
    assertFalse(vi.equals(null));
    // Different object type
    assertFalse(vi.equals(new NullPointerException()));
    // Equal to itself
    assertTrue(vi.equals(vi));
    // Equal to object same values
    assertTrue(vi.equals(same));
    // Not equals to different cliArgs
    assertFalse(vi.equals(diff));
  }

  @Test
  public void testHashCode() {
    // Same
    assertTrue(vi.hashCode() == vi.hashCode());
    // Consistent
    assertTrue(vi.hashCode() == same.hashCode());
    // Different
    assertFalse(vi.hashCode() == diff.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("ValidateInput{cliArgs=[--csv-file, <path/to/file>]}", vi.toString());
  }
}