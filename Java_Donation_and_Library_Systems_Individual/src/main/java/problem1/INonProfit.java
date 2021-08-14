package problem1;

/**
 * Provides the functionality afforded to NonProfits, including the ability
 * to calculate all donations received (or to be received) within a certain year.
 *
 * !!! Used emptySet, add, contains, and remove methods from HW 4 !!!
 * (with slight modifications)
 *
 * @author bello
 */
public interface INonProfit {

  /**
   * Determines the total amount of donations acquired (or to be acquired)
   * by a non-profit over the course of the specified year.
   *
   * @param year - the year for which the total amount of donations will
   *             be calculated
   *
   * @return - the total amount of donations acquired (or to be acquired)
   * by a non-profit over the course of the specified year
   *
   * @throws InvalidDateException - thrown when one attempts to set the
   * cancellation or processing date prior to the creation date
   */
  Float getTotalDonationsForYear(Integer year) throws InvalidDateException;

  //!!! Used emptySet, add, contains, and remove methods from HW 4 !!!
  //    (with slight modifications)

  /**
   * Generates an empty collection of donations, which is an array of size 0.
   */
  void noDonations();

  /**
   * Adds a donation to a collection of donations, so long as the same donation
   * had not already been added earlier.
   *
   * @param donation - donation to be added to the collection of donations
   */
  void add(Donation donation);

  /**
   * Determines whether the provided donation can be found within the
   * collection of donations.
   *
   * @param donation - donation whose membership within the set will be determined
   *
   * @return boolean indicating whether the provided donation can be found within
   * the collection of donations
   */
  Boolean contains(Donation donation);

  /**
   * Returns a collection of donations with the provided donation removed
   *
   * @param donation - the donation to be removed from the collection of donations
   */
  void remove(Donation donation);

}
