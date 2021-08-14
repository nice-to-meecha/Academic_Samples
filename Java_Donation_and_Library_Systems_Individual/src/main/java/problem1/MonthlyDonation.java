package problem1;

import static java.time.temporal.ChronoUnit.MONTHS;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a monthly donation subscription, which bills the donor
 * an established amount at the same time of day each month, until
 * the end of the subscription is reached.
 *
 * Using MONTHS enum: https://docs.oracle.com/en/java/javase/11/docs/
 * api/java.base/java/time/temporal/ChronoUnit.html
 *
 * Empty statement: https://stackoverflow.com/questions/22493765/
 * java-do-nothing
 *
 * @author bello
 */
public class MonthlyDonation extends Donation implements IEndDate {

  private LocalDateTime cancellationDate;

  /**
   * Generates a MonthlyDonation object, utilizing the date on which
   * the donation was made as well as its amount. Its cancellation date
   * cannot be set during instantiation (it's initially null),
   * however it can be set afterward.
   *
   * @param creationDate - the date when the donation was made
   * @param donationAmount - the amount of money donated
   */
  public MonthlyDonation(LocalDateTime creationDate, Float donationAmount) {
    super(creationDate, donationAmount);
    this.cancellationDate = null;
  }

  /**
   * Returns the date on which the monthly donations will end
   *
   * @return the date on which the monthly donations will end
   */
  public LocalDateTime getCancellationDate() {
    return this.cancellationDate;
  }

  /**
   * Sets the date on which the monthly donations will end
   *
   * @param cancellationDate - the date on which the monthly donations will end
   *
   * @throws InvalidDateException - thrown when one attempts to set the
   * cancellation date prior to the creation date
   */
  public void setCancellationDate(LocalDateTime cancellationDate)
      throws InvalidDateException {
    IEndDate.checkDate(cancellationDate, this);
    this.cancellationDate = cancellationDate;
  }

  /**
   * Determines the total donation amount made during which a monthly
   * donation subscription is active.
   *
   * @param year - the year for which the donation is calculated
   *
   * @return the total donation amount made during which a monthly
   * donation subscription is active
   *
   * @throws InvalidDateException - thrown when one attempts to set the
   * cancellation date prior to the creation date
   */
  public Float calculateDonation(Integer year) throws InvalidDateException {
    float wrongDonationYear = 0f, total;
    int january = 1, december = 12, wholeYear = 12, accountForFirstPayment = 1;
    boolean wasNull = false;

    if (this.getCancellationDate() == null) {
      this.calculateWithNullCancellationDate(year);
      wasNull = true;
    }

    // Case when ended before or starts after specified year
    if ((this.getCancellationDate().getYear() < year) ||
        (this.getCreationDate().getYear() > year)) {
      total = wrongDonationYear;
    }

    // Case when starts before and ends during year
    else if ((this.getCreationDate().getYear() < year) &&
        (this.getCancellationDate().getYear() == year)) {
      total = this.calculateSpecialStartOrEndDates(year, january);
    }

    // Case when starts during and ends after year
    else if ((this.getCreationDate().getYear() == year) &&
        ((this.getCancellationDate().getYear() > year))) {
      total = this.calculateSpecialStartOrEndDates(year, december);
    }

    // Case when starts before and ends after year
    else if (this.getCreationDate().getYear() < year) {
      total = (wholeYear * this.getDonationAmount());
    }

    // Case when starts and ends in year
    else {
      long numberOfMonths = this.getCreationDate().until(this.getCancellationDate(),
          MONTHS);
      total = (this.getDonationAmount() * (numberOfMonths + accountForFirstPayment));
    }

    if (wasNull) {
      this.calculateWithNullCancellationDate(year);
    }
    return (float) (Math.round(total * ROUNDING_PRECISION) / ROUNDING_PRECISION);
  }

  /**
   * Aids in calculating the total amount of monthly donations made when
   * the cancellation date has not yet been set.
   *
   * @param year - the year for which the donation is calculated
   */
  private void calculateWithNullCancellationDate(Integer year) throws InvalidDateException {
    LocalDateTime setDate;
    int december = 12;
    // Will temporarily set null date to viable date for calculation only
    if ((this.getCancellationDate() == null) &&
        (year >= this.getCreationDate().getYear())){
      setDate = this.getCreationDate().withMonth(december).withYear(year);
    }
    // Occurs when attempting to calculate price for years before
    // monthly donations established
    else if (this.getCancellationDate() == null) {
      setDate = this.getCreationDate();
    }
    else {
      setDate = null;
    }

    this.setCancellationDate(setDate);
  }

  /**
   * Determines the total donation amount made during the specified year,
   * in 2 cases: 1) when donations began before and end during the year, or
   * 2) when donations started during and end after the year.
   *
   * @param year - the year for which the donation is calculated
   * @param replacementMonth - the month to replace the original month of the
   *                         start or end date of the donation
   *
   * @return the total donation amount made during which a monthly
   * donation subscription is active
   */
  private Float calculateSpecialStartOrEndDates(Integer year, Integer replacementMonth) {
    float endBeforeStart = 0f;
    int january = 1, december = 12, accountForFirstPayment = 1;
    long numberOfMonths;

    if (replacementMonth == january) {
      numberOfMonths = this.getCreationDate().withMonth(
          january).withYear(year).until(this.getCancellationDate(), MONTHS);
    }
    else {
      numberOfMonths = this.getCreationDate().until(
          this.getCreationDate().withMonth(december).withYear(year), MONTHS);
    }

    // Cases for year of 2021: 1) start on 5/19/2018 and end 1/12/2021
    if (numberOfMonths == endBeforeStart) {
      return endBeforeStart;
    }
    else {
      return (this.getDonationAmount() * (numberOfMonths + accountForFirstPayment));
    }
  }

  /**
   * Determines whether a MonthlyDonation object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a MonthlyDonation object
   *
   * @return boolean indicating whether a MonthlyDonation object is equivalent
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
    MonthlyDonation that = (MonthlyDonation) o;
    return Objects.equals(this.cancellationDate, that.cancellationDate);
  }

  /**
   * Determines the hashcode of a MonthlyDonation object
   *
   * @return the hashcode of a MonthlyDonation object
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.cancellationDate);
  }

  /**
   * Provides a string representation of a MonthlyDonation object
   *
   * @return a string representation of a MonthlyDonation object
   */
  @Override
  public String toString() {
    return "This monthly donation was created on " + this.creationDate +
        ".\nAn amount of $" + this.donationAmount + " will be billed each month " +
        "until " + this.cancellationDate + ".";
  }
}
