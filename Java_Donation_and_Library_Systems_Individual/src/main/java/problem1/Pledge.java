package problem1;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a pledge made to donate a sum of money to a
 * non-profit organization at a specified point in time.
 *
 * @author bello
 */
public class Pledge extends Donation implements IEndDate {

  private LocalDateTime processingDate;

  /**
   * Generates a Pledge object, utilizing the date on which
   * the donation, its amount and the date on which it will
   * be processed.
   *
   * @param creationDate - the date when the donation was made
   * @param donationAmount - the amount of money donated
   * @param processingDate - the date on which the pledge will be processed
   *
   * @throws InvalidDateException - thrown when one attempts to set the
   * processing date prior to the creation date
   */
  public Pledge(LocalDateTime creationDate, Float donationAmount,
      LocalDateTime processingDate) throws InvalidDateException {
    super(creationDate, donationAmount);
    IEndDate.checkDate(processingDate, this);
    this.processingDate = processingDate;
  }

  /**
   * Generates a Pledge object, utilizing the date on which
   * the donation was made as well as its amount. This is instantiated
   * in the absence of an processing date.
   *
   * @param creationDate - the date when the donation was made
   * @param donationAmount - the amount of money donated
   */
  public Pledge(LocalDateTime creationDate, Float donationAmount) {
    super(creationDate, donationAmount);
    this.processingDate = null;
  }

  /**
   * Returns the date on which the pledge will be processed
   *
   * @return the date on which the pledge will be processed
   */
  public LocalDateTime getProcessingDate() {
    return this.processingDate;
  }

  /**
   * Sets the date on which the pledge will be processed
   *
   * @param processingDate - the date on which the pledge will be processed
   *
   * @throws InvalidDateException - thrown when one attempts to set the
   * processing date prior to the creation date
   */
  public void setProcessingDate(LocalDateTime processingDate)
      throws InvalidDateException {
    IEndDate.checkDate(processingDate, this);
    this.processingDate = processingDate;
  }


  /**
   * Determines the donation amount, according to the pledge made.
   *
   * @param year - the year for which the donation is calculated
   *
   * @return the donation amount, according to the pledge made.
   */
  @Override
  public Float calculateDonation(Integer year) {
    Float wrongDonationYear = 0f;
    if ((this.getProcessingDate() == null) ||
        !(this.getProcessingDate().getYear() == year)) {
      return wrongDonationYear;
    }
    else {
      return (float) (Math.round(this.getDonationAmount() * ROUNDING_PRECISION) /
          ROUNDING_PRECISION);
    }
  }

  /**
   * Determines whether a Pledge object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a Pledge object
   *
   * @return boolean indicating whether a Pledge object is equivalent
   * to another object
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Pledge pledge = (Pledge) o;
    return Objects.equals(processingDate, pledge.processingDate);
  }

  /**
   * Determines the hashcode of a Pledge object
   *
   * @return the hashcode of a Pledge object
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), processingDate);
  }

  /**
   * Provides a string representation of a Pledge object
   *
   * @return a string representation of a Pledge object
   */
  @Override
  public String toString() {
    return "This pledge was created on " + this.creationDate +
        " for an amount of $" + this.donationAmount + ". It will be " +
        "processed on " + this.processingDate + ".";
  }
}
