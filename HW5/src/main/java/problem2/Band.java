package problem2;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represent a band, which is comprised of a collection of RecordingArtists.
 *
 * Using array lists: https://www.educative.io/edpresso/what-is-an-arraylist-in-java
 *
 * @author bello
 */
public class Band implements Creator {

  private String name;
  private ArrayList<RecordingArtist> bandMembers;

  /**
   * Generates a Band object, utilizing the band's name as well as
   * its members.
   *
   * @param name - the name of the band
   * @param bandMembers - the recording artists constituting the band
   *
   * @throws ImproperCreatorException - thrown when one attempts to initialize a
   * Band object with a null reference in place of a collection of members.
   */
  public Band(String name, ArrayList<RecordingArtist> bandMembers)
      throws ImproperCreatorException {
    this.checkNull(bandMembers);
    this.name = name;
    this.bandMembers = bandMembers;
  }

  /**
   * Ensures the members of a Band object cannot be established as null.
   *
   * @param bandMembers - the recording artists constituting a band
   *
   * @throws ImproperCreatorException - thrown when one attempts to initialize a
   * Band object with a null reference in place of a collection of members.
   */
  private void checkNull(ArrayList<RecordingArtist> bandMembers)
      throws ImproperCreatorException {
    if (bandMembers == null) {
      throw new ImproperCreatorException();
    }
  }

  /**
   * Returns the name of the band
   *
   * @return the name of the band
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the collection of recording artists that comprise the band
   *
   * @return - the collection of recording artists that comprise the band
   */
  public ArrayList<RecordingArtist> getBandMembers() {
    return this.bandMembers;
  }

  /**
   * Sets the name of the band
   *
   * @param name - the name of the band
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the collection of recording artists that comprise the band
   *
   * @param bandMembers - the collection of recording artists that comprise the band
   *
   * @throws ImproperCreatorException - thrown when one attempts to initialize set the
   * members of a Band object to a null reference.
   */
  public void setBandMembers(ArrayList<RecordingArtist> bandMembers)
      throws ImproperCreatorException {
    this.checkNull(bandMembers);
    this.bandMembers = bandMembers;
  }

  /**
   * Determines whether the provided recording artist matches another within
   * the band
   *
   * @param creator - the recording artist who will be utilized to compare
   *                against another within the band
   *
   * @return boolean indicating whether the provided recording artists
   * matches another within the band
   */
  @Override
  public Boolean isCorrectCreator(Creator creator) {
    for (RecordingArtist member: this.bandMembers) {
      if (member.isCorrectCreator(creator)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines whether a Band object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a Band object
   *
   * @return boolean indicating whether a Band object is equivalent
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
    Band band = (Band) o;
    return Objects.equals(this.name, band.name) && Objects
        .equals(this.bandMembers, band.bandMembers);
  }

  /**
   * Determines the hashcode of a Band object
   *
   * @return the hashcode of a Band object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.bandMembers);
  }

  /**
   * Provides a string representation of a Band object
   *
   * @return a string representation of a Band object
   */
  @Override
  public String toString() {
    return name + " with: " + bandMembers;
  }
}
