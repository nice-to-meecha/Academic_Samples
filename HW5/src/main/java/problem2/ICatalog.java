package problem2;

import java.util.ArrayList;

/**
 * Provides the functionality of a catalog retaining a library of
 * music and books generated by a variety of authors, individual
 * recording artists and bands.
 *
 * @author bello
 */
public interface ICatalog {

  /**
   * Appends an item to the end of the library.
   *
   * @param item - a book or music item being added to the library
   */
  void addItem(Item item);

  /**
   * Removes an item from the library.
   *
   * @param item - a book or music item being removed from the library
   */
  void removeItem(Item item);

  /**
   * Provides a list containing all items from the catalog whose titles match
   * the provided keyword.
   *
   * @param keyword - the letter, phrase, name, etc which will be utilized
   *                to search for items with matching titles.
   *
   * @return a list containing all items from the catalog whose titles match
   * the provided keyword.
   */
  ArrayList<Item> search(String keyword);

  /**
   * Provides a list containing all items from the catalog whose writer matches
   * the provided author.
   *
   * @param author - the writer who will be utilized to search for items with a
   *               matching author.
   *
   * @return a list containing all items from the catalog whose writer matches
   * the provided author.
   */
  ArrayList<Item> search(Author author);

  /**
   * Provides a list containing all items from the catalog whose singer or
   * band member matches the provided recording artist.
   *
   * @param artist - the artist who will be utilized to search for items with a
   *               matching recording artist.
   *
   * @return a list containing all items from the catalog whose singer or
   * band member matches the provided recording artist.
   */
  ArrayList<Item> search(RecordingArtist artist);
}