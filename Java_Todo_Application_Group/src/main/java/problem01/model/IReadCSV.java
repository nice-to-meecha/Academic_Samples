package problem01.model;

import java.io.BufferedReader;
import java.io.IOException;
import problem01.view.InvalidNullArgumentException;

/**
 * Interface describing the public functionality of a ReadCSV object,
 * comprising of the ability to store contents of a csv file, row by row.
 */
public interface IReadCSV {

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
  void splitCSV(BufferedReader csv) throws IOException, InvalidNullArgumentException;

}
