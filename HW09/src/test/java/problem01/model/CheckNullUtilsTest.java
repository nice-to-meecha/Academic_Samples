package problem01.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import problem01.view.InvalidNullArgumentException;

public class CheckNullUtilsTest {

  CheckNullUtils checkNullUtils;

  @Test
  public void nonexistentConstructor() {
    // Testing for the sake of code coverage
    checkNullUtils = new CheckNullUtils();
  }

  @Test (expected = InvalidNullArgumentException.class)
  public void evaluateNullReference() throws InvalidNullArgumentException {
    CheckNullUtils.evaluate(null);
  }

  @Test (expected = InvalidNullArgumentException.class)
  public void evaluateNullString() throws InvalidNullArgumentException {
    CheckNullUtils.evaluate("null");
  }

  @Test
  public void evaluateNonNullObject() throws InvalidNullArgumentException {
    CheckNullUtils.evaluate(2);
  }
}