package problem01.controller;

import java.util.Comparator;

/**
 * Comparator class which can be utilized to sort all Todos,
 * according to their priority.
 */
public class ComparePriority implements Comparator<Todo> {

  private static final int EQUIVALENT_PRIORITIES = 0;

  /**
   * Generates an empty ComparePriority object
   */
  public ComparePriority() {}

  /**
   * Compares two to-do objects, according to their priorities.
   *
   * @param o1 - the to-do that is being compared with o2 to-do
   * @param o2 - the to-do that is being compared with o1 to-do
   *
   * @return an integer indicating the equivalency of the two to-dos.
   * Returns a negative value if o1's priority is lower than that of o2.
   * Returns a positive value if o1's priority is higher than that of o2.
   * Returns 0 if both priorities are the same.
   */
  @Override
  public int compare(Todo o1, Todo o2) {
    int comparison, nullAtEndOfList = 1;
    if (o1.getPriority() == null && o2.getPriority() == null) {
      comparison = EQUIVALENT_PRIORITIES;
    }
    else if (o1.getPriority() == null) {
      comparison = nullAtEndOfList;
    }
    else if (o2.getPriority() == null) {
      comparison = -(nullAtEndOfList);
    }
    else {
      comparison = o1.getPriority().compareTo(o2.getPriority());
    }

    if (comparison == EQUIVALENT_PRIORITIES) {
      comparison = o1.getId().compareTo(o2.getId());
    }
    return comparison;
  }
}
