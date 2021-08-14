package problem2;

/**
 * Represents authors who write books for a living.
 *
 * @author bello
 */
public class Author extends IndividualCreator {

  /**
   * Generates an Author object, utilizing the author's first and last name.
   *
   * @param firstName - the first name of the author
   * @param lastName - the last name of the author
   */
  public Author(String firstName, String lastName) {
    super(firstName, lastName);
  }

  /**
   * Determines whether the provided author matches another
   *
   * @param creator - the author who will be utilized to compare against another
   *
   * @return boolean indicating whether the provided author matches another
   */
  @Override
  public Boolean isCorrectCreator(Creator creator) {
    return (this.equals(creator));
  }

  /**
   * Provides a string representation of an Author object
   *
   * @return a string representation of an Author object
   */
  @Override
  public String toString() {
    return firstName + ' ' + lastName;
  }
}
