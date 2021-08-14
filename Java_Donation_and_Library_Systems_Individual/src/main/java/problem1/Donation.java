package problem1;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents donations made to non-profit organizations, according to
 * their date of donation/pledge as well as the amount given/promised.
 *
 * LocalDateTime uses: https://docs.oracle.com/en/java/javase/11/docs/
 * api/java.base/java/time/LocalDateTime.html
 *
 * Rounding to hundredths place: https://stackoverflow.com/questions/
 * 11701399/round-up-to-2-decimal-places-in-java
 *
 * @author bello
 */
public abstract class Donation implements IDonation {

  protected LocalDateTime creationDate;
  protected Float donationAmount;
  protected static final Float ROUNDING_PRECISION = 100.0f;

  /**
   * Used by subclasses to generate objects that can be categorized as
   * Donations.
   *
   * @param creationDate - the date when the donation was made or pledged
   * @param donationAmount - the amount of money donated or pledged
   */
  public Donation(LocalDateTime creationDate, Float donationAmount) {
    this.creationDate = creationDate;
    this.donationAmount = donationAmount;
  }

  /**
   * Returns the date when the donation was made or pledged
   *
   * @return the date when the donation was made or pledged
   */
  public LocalDateTime getCreationDate() {
    return this.creationDate;
  }

  /**
   * Returns the amount of money donated or pledged
   *
   * @return the amount of money donated or pledged
   */
  public Float getDonationAmount() {
    return this.donationAmount;
  }

  /**
   * Sets the date when the donation was made or pledged
   *
   * @param creationDate - the date when the donation was made or pledged
   */
  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * Sets the amount of money donated or pledged
   *
   * @param donationAmount - the amount of money donated or pledged
   */
  public void setDonationAmount(Float donationAmount) {
    this.donationAmount = donationAmount;
  }

  /**
   * Determines whether a Donation object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a Donation object
   *
   * @return boolean indicating whether a Donation object is equivalent
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
    Donation donation = (Donation) o;
    return Objects.equals(this.creationDate, donation.creationDate) && Objects
        .equals(this.donationAmount, donation.donationAmount);
  }

  /**
   * Determines the hashcode of a Donation object
   *
   * @return the hashcode of a Donation object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.creationDate, this.donationAmount);
  }
}
