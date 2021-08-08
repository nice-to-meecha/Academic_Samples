package problem1;

import java.time.LocalDateTime;

/**
 * Represents donations to non-profit organizations that are only made once
 * (without subscription).
 *
 * @author bello
 */
public class OneTimeDonation extends Donation {

  /**
   * Generates a OneTimeDonation object, utilizing the date on which
   * the donation was made as well as its amount.
   *
   * @param creationDate - the date when the donation was made
   * @param donationAmount - the amount of money donated
   */
  public OneTimeDonation(LocalDateTime creationDate, Float donationAmount) {
    super(creationDate, donationAmount);
  }

  /**
   * Determines the amount of a one-time donation.
   *
   * @param year - the year for which the donation is calculated
   *
   * @return the amount of a one-time donation.
   */
  @Override
  public Float calculateDonation(Integer year) {
    Float wrongDonationYear = 0f;
    if (this.getCreationDate().getYear() == year) {
      return (float) (Math.round(this.getDonationAmount() * ROUNDING_PRECISION) /
                                 ROUNDING_PRECISION);
    }
    else {
      return wrongDonationYear;
    }
  }

  /**
   * Provides a string representation of a OneTimeDonation object
   *
   * @return a string representation of a OneTimeDonation object
   */
  @Override
  public String toString() {
    return "This one-time donation was created on " + this.creationDate +
        " for the amount of $" + this.donationAmount + ".";
  }
}
