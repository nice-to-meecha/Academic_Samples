package problem01.view;

/**
 * Interface describing the public functionality of a GenerateDisplay object,
 * comprising of the ability to write print a user-friendly table of To-dos
 * to the console.
 */
public interface IGenerateDisplay {

  /**
   * Displays the provided collection of Todos within in a table,
   * which is able to conform to the size of input.
   */
  void printTable();

}
