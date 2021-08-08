package problem1;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents non-profit organizations, recording all donations made to
 * them and reporting their yearly gains.
 *
 * !!! Used emptySet, add, contains, and remove methods from HW 4 !!!
 * (with slight modifications)
 *
 * @author bello
 */
public class NonProfit implements INonProfit {

  private String organizationName;
  private Donation[] donations;
  private static final int NO_DONATIONS = 0;

  /**
   * Generates a NonProfit object, utilizing a non-profit organization's
   * name. For accuracy, non-profits will start with zero donations;
   * all donations will be added individually.
   *
   * @param organizationName - the name of the non-profit organization
   */
  public NonProfit(String organizationName) {
    this.organizationName = organizationName;
    this.donations = new Donation[NO_DONATIONS];
  }

  /**
   * Returns the name of the non-profit organization
   *
   * @return the name of the non-profit organization
   */
  public String getOrganizationName() {
    return this.organizationName;
  }

  /**
   * Returns the collection of all donations made to the non-profit
   *
   * @return the collection of all donations made to the non-profit
   */
  public Donation[] getDonations() {
    return this.donations;
  }

  /**
   * Sets the name of the non-profit organization
   *
   * @param organizationName - the name of the non-profit organization
   */
  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  // No setter for donations, so that they can be added individually

  //!!! Used emptySet, add, contains, and remove methods from HW 4 !!!
  //    (with slight modifications)

  /**
   * Generates an empty collection of donations, which is an array of size 0.
   */
  public void noDonations() {
    this.donations = new Donation[NO_DONATIONS];
  }

  /**
   * Adds a donation to a collection of donations, so long as the same donation
   * had not already been added earlier.
   *
   * @param donation - donation to be added to the collection of donations
   */
  @Override
  public void add(Donation donation) {
    int addMember = 1, loopStart = 0, index;
    if ((donation == null) || (this.contains(donation))) {
      return;
    }

    Donation[] newDonations = new Donation[this.getDonations().length + addMember];
    for (index = loopStart; index < this.getDonations().length; index++) {
      newDonations[index] = this.getDonations()[index];
    }
    newDonations[this.getDonations().length] = donation;

    this.donations = newDonations;
  }

  /**
   * Determines whether the provided donation can be found within the
   * collection of donations.
   *
   * @param donation - donation whose membership within the set will be determined
   *
   * @return boolean indicating whether the provided donation can be found within
   * the collection of donations
   */
  @Override
  public Boolean contains(Donation donation) {
    int index, loopStart = 0;

    for (index = loopStart; index < this.getDonations().length; index++) {
      if (this.getDonations()[index].equals(donation)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns a collection of donations with the provided donation removed
   *
   * @param donation - the donation to be removed from the collection of donations
   */
  @Override
  public void remove(Donation donation) {
    int index, loopStart = 0, removeMember = 1, accountForIndex = 1;
    boolean skipped = false;

    if ((donation == null) || (!contains(donation))) {
      return;
    }

    Donation[] newDonations = new Donation[this.getDonations().length - removeMember];
    for (index = loopStart; index < newDonations.length; index++) {
      if (this.getDonations()[index].equals(donation)) {
        skipped = true;
      }
      else {
        if (skipped) {
          newDonations[index - removeMember] = this.getDonations()[index];
        }
        else {
          newDonations[index] = this.getDonations()[index];
        }
      }
    }

    // If final member not removed, will be missing an element in newDonations
    if (!this.getDonations()[index].equals(donation)) {
      newDonations[newDonations.length - accountForIndex] =
          this.getDonations()[this.getDonations().length - accountForIndex];
    }

    this.donations = newDonations;
  }

  /**
   * Determines the total amount of donations acquired (or to be acquired) by a
   * non-profit over the course of the specified year.
   *
   * @param year - the year for which the total amount of donations will be calculated
   *
   * @return - the total amount of donations acquired (or to be acquired) by a
   * non-profit over the course of the specified year
   *
   * @throws InvalidDateException - thrown when one attempts to set the
   * cancellation or processing date prior to the creation date
   */
  @Override
  public Float getTotalDonationsForYear(Integer year) throws InvalidDateException {
    Float total = 0f;
    for (Donation donation: this.getDonations()) {
      total += donation.calculateDonation(year);
    }
    return total;
  }

  /**
   * Determines whether a NonProfit object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a NonProfit object
   *
   * @return boolean indicating whether a NonProfit object is equivalent
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
    NonProfit nonProfit = (NonProfit) o;
    return Objects.equals(this.organizationName, nonProfit.organizationName) && Arrays
        .equals(this.donations, nonProfit.donations);
  }

  /**
   * Determines the hashcode of a NonProfit object
   *
   * @return the hashcode of a NonProfit object
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(this.organizationName);
    result = 31 * result + Arrays.hashCode(this.donations);
    return result;
  }

  /**
   * Provides a string representation of a NonProfit object
   *
   * @return a string representation of a NonProfit object
   */
  @Override
  public String toString() {
    return this.organizationName + "'s donations: " + Arrays.toString(this.donations);
  }
}
