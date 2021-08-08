package problem01.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import problem01.view.InvalidNullArgumentException;

/**
 * Interface describing the public functionality of a WriteCSV object,
 * comprising of the ability to write store To-do information to a csv file.
 */
public interface IWriteCSV {

  /**
   * Fills a csv with previously stored and/or altered information,
   * regarding Todos.
   *
   * @param csv - the file to which the Todos -- newly generated or altered --
   *                will be written
   *
   * @throws IOException - input/output exception, which is thrown if an error is
   * encountered while writing to newFile
   * @throws InvalidNullArgumentException - thrown when a null argument is utilized
   * in unsupported fashion.
   * @throws NoSuchMethodException - thrown if a method name utilized within getMethod()
   * is non-existent.
   * @throws InvocationTargetException - thrown if the method called by invoke() throws
   * an exception.
   * @throws IllegalAccessException - thrown if the method called by invoke() is
   * inaccessible.
   */
  void writeContentsToCSV(BufferedWriter csv) throws IOException,
      InvalidNullArgumentException, NoSuchMethodException, InvocationTargetException,
      IllegalAccessException;

}
