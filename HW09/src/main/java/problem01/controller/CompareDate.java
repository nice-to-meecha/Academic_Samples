package problem01.controller;

import java.util.Comparator;

/**
 * Comparator class which can be utilized to sort all Todos,
 * according to their date.
 */
public class CompareDate implements Comparator<Todo> {

  private static final int EQUIVALENT_DATES = 0;

  /**
   * Generates an empty CompareDate object
   */
  public CompareDate() {}

  /**
   * Compares two to-do objects, according to their due dates.
   *
   * @param o1 - the to-do that is being compared with o2 to-do
   * @param o2 - the to-do that is being compared with o1 to-do
   *
   * @return an integer indicating the equivalency of the two to-dos.
   * Returns a negative value if o1's due date is earlier than that of o2.
   * Returns a positive value if o1's due date is later than that of o2.
   * Returns 0 if both due dates are the same.
   */
  @Override
  public int compare(Todo o1, Todo o2) {
    int comparison, nullAtEndOfList = 1;
    if (o1.getDueDate() == null && o2.getDueDate() == null) {
      comparison = EQUIVALENT_DATES;
    }
    else if (o1.getDueDate() == null) {
      comparison = nullAtEndOfList;
    }
    else if (o2.getDueDate() == null) {
      comparison = -(nullAtEndOfList);
    }
    else {
      comparison = o1.getDueDate().compareTo(o2.getDueDate());
    }

    if (comparison == EQUIVALENT_DATES) {
      comparison = o1.getId().compareTo(o2.getId());
    }
    return comparison;
  }
}
