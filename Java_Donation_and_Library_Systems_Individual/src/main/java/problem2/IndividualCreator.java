package problem2;

import java.util.Objects;

/**
 * Represents individuals that generate some form of content -- in this
 * particular case, either books or music.
 *
 * @author bello
 */
public abstract class IndividualCreator implements Creator {

  protected String firstName;
  protected String lastName;

  /**
   * Generates an object of an IndividualCreator subclass, utilizing
   * the creator's first and last name.
   *
   * @param firstName - the first name of the individual creator
   * @param lastName - the last name of the individual creator
   */
  public IndividualCreator(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Returns the first name of the creator
   *
   * @return the first name of the creator
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Returns the last name of the creator
   *
   * @return the last name of the creator
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Sets the first name of the creator
   *
   * @param firstName - the first name of the creator
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Sets the last name of the creator
   *
   * @param lastName - the last name of the creator
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Determines whether an IndividualCreator object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with an IndividualCreator object
   *
   * @return boolean indicating whether an IndividualCreator object is equivalent
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
    IndividualCreator that = (IndividualCreator) o;
    return this.firstName.equals(that.firstName) && this.lastName.equals(that.lastName);
  }

  /**
   * Determines the hashcode of an IndividualCreator object
   *
   * @return the hashcode of an IndividualCreator object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.firstName, this.lastName);
  }
}
