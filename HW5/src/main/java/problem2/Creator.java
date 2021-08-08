package problem2;

public interface Creator {

  /**
   * Determines whether the provided creator matches an author, an individual
   * recording artist or an artist within a group.
   *
   * @param creator - the creator who will be utilized to compare against an
   *                individual or a group of creators
   *
   * @return boolean indicating whether the provided creator matches an author,
   * an individual recording artist or an artist within a group.
   */
  Boolean isCorrectCreator(Creator creator);
}
