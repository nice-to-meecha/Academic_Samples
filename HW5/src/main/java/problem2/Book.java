package problem2;

/**
 * Represents a book item, which retains an author as its creator,
 * as well as its title and the year it was published.
 *
 * @author bello
 */
public class Book extends Item {

  /**
   * Generates a Book object, utilizing its creator, title, and the year
   * it was published.
   *
   * @param creator - the creator of the book
   * @param title - the title of the book
   * @param year - the year the book was published
   *
   * @throws ImproperCreatorException - thrown when one attempts to establish
   * a Book object with a RecordingArtist or Band as its creator
   */
  public Book(Creator creator, String title, Integer year) throws ImproperCreatorException {
    super(creator, title, year);
    this.checkCreator(creator);
  }

  /**
   * Ensures the creator of a book is not instantiated as a RecordingArtist or Band
   *
   * @param creator - the creator of the book
   *
   * @throws ImproperCreatorException - thrown when one attempts to establish
   * a Book object with a RecordingArtist or Band as its creator
   */
  private void checkCreator(Creator creator) throws ImproperCreatorException {
    if ((creator instanceof RecordingArtist) || (creator instanceof Band)) {
      throw new ImproperCreatorException();
    }
  }

  /**
   * Sets the creator of the book
   *
   * @param creator - the creator of the book
   *
   * @throws ImproperCreatorException - thrown when one attempts to establish
   * a Book object with a RecordingArtist or Band as its creator
   */
  public void setCreator(Creator creator) throws ImproperCreatorException {
    this.checkCreator(creator);
    this.creator = creator;
  }

  /**
   * Provides a string representation of a Book object
   *
   * @return a string representation of a Book object
   */
  @Override
  public String toString() {
    return '\'' + title + "' written by " + creator +
        " -- published in " + year;
  }
}
