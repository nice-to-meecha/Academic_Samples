package problem1;

/**
 * Provides the functionality utilized by Donations,
 * including their calculation.
 *
 * @author bello
 */
public interface IDonation {

  /**
   * Determines the donation amount, according to the donation type.
   *
   * @param year - the year for which the donation is calculated
   *
   * @return the donation amount, according to the donation type.
   *
   * @throws InvalidDateException - thrown specifically when one attempts
   * to set the cancellation date prior to the creation date (only applies
   * to MonthlyDonation class)
   */
  Float calculateDonation(Integer year) throws InvalidDateException;

}
