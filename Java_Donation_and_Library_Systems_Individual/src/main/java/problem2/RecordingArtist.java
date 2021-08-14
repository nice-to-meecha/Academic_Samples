package problem2;

/**
 * Represents recording artists who create music for a living.
 *
 * @author bello
 */
public class RecordingArtist extends IndividualCreator {

  /**
   * Generates a RecordingArtist object, utilizing the artist's first
   * and last name.
   *
   * @param firstName - the first name of the recording artist
   * @param lastName - the last name of the recording artist
   */
  public RecordingArtist(String firstName, String lastName) {
    super(firstName, lastName);
  }

  /**
   * Determines whether the provided recording artist matches another
   *
   * @param creator - the recording artist who will be utilized to compare
   *                against another
   *
   * @return boolean indicating whether the provided recording artists
   * matches another
   */
  @Override
  public Boolean isCorrectCreator(Creator creator) {
    return (this.equals(creator));
  }

  /**
   * Provides a string representation of a RecordingArtist object
   *
   * @return a string representation of a RecordingArtist object
   */
  @Override
  public String toString() {
    return firstName + ' ' + lastName;
  }
}
