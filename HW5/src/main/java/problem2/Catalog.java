package problem2;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a catalog retaining written and audio works (books and music),
 * created by associated authors, individual recording artists and bands, alike.
 *
 * @author bello
 */
public class Catalog implements ICatalog {

  private ArrayList<Item> library;

  /**
   * Generates a Catalog object, with an established collection of books
   * and music.
   *
   * @param library - a collection of books and music created by associated authors,
   *                individual recording artists and bands.
   *
   * @throws UnacceptableLibraryException - thrown when one attempts to initialize a
   * Catalog object with a null reference in place of a library.
   */
  public Catalog(ArrayList<Item> library) throws UnacceptableLibraryException {
    this.checkNull(library);
    this.library = library;
  }

  /**
   * Ensures the library of a Catalog object cannot be established as null.
   *
   * @param library - a collection of books and music created by associated authors,
   *                individual recording artists and bands.
   *
   * @throws UnacceptableLibraryException - thrown when one attempts to initialize a
   * Catalog object with a null reference in place of a library.
   */
  private void checkNull(ArrayList<Item> library) throws UnacceptableLibraryException {
    if (library == null) {
      throw new UnacceptableLibraryException();
    }
  }

  /**
   * Generates a Catalog object without an established collection of items.
   */
  public Catalog() {
    this.library = new ArrayList<Item>();
  }

  /**
   * Returns the collection of books and music retained by the catalog
   *
   * @return - the collection of books and music retained by the catalog
   */
  public ArrayList<Item> getLibrary() {
    return this.library;
  }

  /**
   * Sets the collection of books and music retained by the catalog
   *
   * @param library  - a collection of books and music created by associated authors,
   *                 individual recording artists and bands.
   *
   * @throws UnacceptableLibraryException - thrown when one attempts to set the library
   * of a Catalog object with a null reference.
   */
  public void setLibrary(ArrayList<Item> library) throws UnacceptableLibraryException {
    this.checkNull(library);
    this.library = library;
  }

  /**
   * Appends an item to the end of the library.
   *
   * @param item - a book or music item being added to the library
   */
  @Override
  public void addItem(Item item) {
    if (!this.library.contains(item)) {
      this.library.add(item);
    }
  }

  /**
   * Removes an item from the library.
   *
   * @param item - a book or music item being removed from the library
   */
  @Override
  public void removeItem(Item item) {
    // Will not cause an error if the item
    // is not found within the ArrayList
    this.library.remove(item);
  }

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
  @Override
  public ArrayList<Item> search(String keyword){
    ArrayList<Item> matchingKeywords = new ArrayList<Item>();
    for (Item item: this.library) {
      if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
        matchingKeywords.add(item);
      }
    }
    return matchingKeywords;
  }

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
  @Override
  public ArrayList<Item> search(Author author){
    ArrayList<Item> matchingAuthors = new ArrayList<Item>();
    for (Item book: this.library) {
      if (book.getCreator().isCorrectCreator(author)) {
        matchingAuthors.add(book);
      }
    }
    return matchingAuthors;
  }

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
  @Override
  public ArrayList<Item> search(RecordingArtist artist){
    ArrayList<Item> matchingArtists = new ArrayList<Item>();
    for (Item music: this.library) {
      if (music.getCreator().isCorrectCreator(artist)) {
        matchingArtists.add(music);
      }
    }
    return matchingArtists;
  }

  /**
   * Determines whether a Catalog object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with a Catalog object
   *
   * @return boolean indicating whether a Catalog object is equivalent
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
    Catalog catalog = (Catalog) o;
    return library.equals(catalog.library);
  }

  /**
   * Determines the hashcode of a Catalog object
   *
   * @return the hashcode of a Catalog object
   */
  @Override
  public int hashCode() {
    return Objects.hash(library);
  }

  /**
   * Provides a string representation of a Catalog object
   *
   * @return a string representation of a Catalog object
   */
  @Override
  public String toString() {
    return "Catalog: " + library;
  }
}
