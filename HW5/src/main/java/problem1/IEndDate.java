package problem1;

import java.time.LocalDateTime;

/**
 * Interface providing the ability to check the validity of submitted ending
 * dates (cancellation and processing dates) of donations, in relation to
 * their creation.
 *
 * @author bello
 */
public interface IEndDate {

  /**
   * Ensures the cancellation date of a monthly or processing date of a pledge
   * cannot be set prior to the creation date.
   *
   * @param endDate - the date on which the monthly donations will end
   *                         or a pledge will be processed
   * @param donation - the monthly donation or pledge made
   *
   * @throws InvalidDateException - thrown when one attempts to set the
   * cancellation or processing date prior to the creation date
   */
  static void checkDate(LocalDateTime endDate, Donation donation) throws InvalidDateException {
    if ((endDate != null) && (endDate.isBefore(donation.getCreationDate()))) {
      throw new InvalidDateException("This date -- " + endDate +
          " -- is set before the time when you decided to donate -- " +
          donation.getCreationDate() + ". \nIt's suggested you change that.");
    }
  }

}
