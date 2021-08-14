package problem2;

public class Music extends Item {

  /**
   * Generates a Music object, utilizing its creator, title, and the year
   * it was published.
   *
   * @param creator - the creator of the music
   * @param title - the title of the music
   * @param year - the year the music was released
   *
   * @throws ImproperCreatorException - thrown when one attempts to establish
   * a Music object with an Author its creator
   */
  public Music(Creator creator, String title, Integer year) throws ImproperCreatorException {
    super(creator, title, year);
    this.checkCreator(creator);
  }

  /**
   * Ensures the creator of music is not instantiated as an Author
   *
   * @param creator - the creator of the music
   *
   * @throws ImproperCreatorException - thrown when one attempts to establish
   * a Music object with an Author its creator
   */
  private void checkCreator(Creator creator) throws ImproperCreatorException {
    if (creator instanceof Author){
      throw new ImproperCreatorException();
    }
  }

  /**
   * Sets the creator of the music
   *
   * @param creator - the creator of the music
   *
   * @throws ImproperCreatorException - thrown when one attempts to establish
   * a Music object with an Author as its creator
   */
  public void setCreator(Creator creator) throws ImproperCreatorException {
    this.checkCreator(creator);
    this.creator = creator;
  }

  /**
   * Provides a string representation of a Music object
   *
   * @return a string representation of a Music object
   */
  @Override
  public String toString() {
    return '\'' + title + "' sung by " + creator +
        " -- released in " + year;
  }
}
