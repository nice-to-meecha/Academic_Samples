package problem2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

  private Book book;
  private Book book2;
  private static final Author DICTIONARY_AUTHOR = new Author("Merriam", "Webster");
  private static final Author JANE_EYRE_AUTHOR = new Author("Emily", "Bronte");
  private static final String DICTIONARY = "Dictionary";
  private static final String JANE_EYRE = "Jane Eyre";
  private static final Integer DICT_YEAR = 2021;
  private static final Integer JANE_YEAR = 1847;

  @Before
  public void setUp() throws ImproperCreatorException {
    book = new Book(DICTIONARY_AUTHOR, DICTIONARY, DICT_YEAR);
    book2 = new Book(DICTIONARY_AUTHOR, DICTIONARY, DICT_YEAR);
  }

  @Test (expected = ImproperCreatorException.class)
  public void constructorCreatorError() throws ImproperCreatorException {
    RecordingArtist wrongCreator = new RecordingArtist("Remi", "Wolf");
    Book errorBook = new Book(wrongCreator, DICTIONARY, DICT_YEAR);
  }

  @Test
  public void getCreator() {
    assertEquals(DICTIONARY_AUTHOR, book.getCreator());
  }

  @Test
  public void getTitle() {
    assertEquals(DICTIONARY, book.getTitle());
  }

  @Test
  public void getYear() {
    assertEquals(DICT_YEAR, book.getYear());
  }

  @Test
  public void setCreator() throws ImproperCreatorException {
    book.setCreator(JANE_EYRE_AUTHOR);
    assertEquals(JANE_EYRE_AUTHOR, book.getCreator());
  }

  @Test (expected = ImproperCreatorException.class)
  public void setCreatorError() throws ImproperCreatorException {
    RecordingArtist remi = new RecordingArtist("Remi", "Wolf");
    RecordingArtist omar = new RecordingArtist("Omar", "Apollo");
    RecordingArtist dpr = new RecordingArtist("DPR", "Live");
    ArrayList<RecordingArtist> dreamTeam = new ArrayList<RecordingArtist>();
    dreamTeam.add(remi);
    dreamTeam.add(omar);
    dreamTeam.add(dpr);
    Band wrongCreator = new Band("Fakers", dreamTeam);
    book.setCreator(wrongCreator);
  }

  @Test
  public void setTitle() {
    book.setTitle(JANE_EYRE);
    assertEquals(JANE_EYRE, book.getTitle());
  }

  @Test
  public void setYear() {
    book.setYear(JANE_YEAR);
    assertEquals(JANE_YEAR, book.getYear());
  }

  @Test
  public void testEqualsReflexive() {
    assertTrue(book.equals(book));
  }

  @Test
  public void testEqualsSymmetric() {
    assertTrue(book.equals(book2) &&
        book2.equals(book));
  }

  @Test
  public void testEqualsTransitive() throws ImproperCreatorException {
    Book book3 = new Book(DICTIONARY_AUTHOR, DICTIONARY, DICT_YEAR);
    assertTrue(book.equals(book2) &&
        book2.equals(book3) &&
        book.equals(book3));
  }

  @Test
  public void testEqualsConsistent() {
    boolean result = book.equals(book2);
    assertEquals(result, book.equals(book2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(book.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(book.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() throws ImproperCreatorException {
    Book diffAuthor = new Book(JANE_EYRE_AUTHOR, DICTIONARY, DICT_YEAR);
    assertFalse(book.equals(diffAuthor));

    Book diffTitle = new Book(DICTIONARY_AUTHOR, JANE_EYRE, DICT_YEAR);
    assertFalse(book.equals(diffTitle));

    Book diffYear = new Book(DICTIONARY_AUTHOR, DICTIONARY, JANE_YEAR);
    assertFalse(book.equals(diffYear));
  }

  @Test
  public void testHashSelfConsistent() {
    assertTrue(book.hashCode() == book.hashCode());
  }

  @Test
  public void testHashConsistent() {
    assertTrue(book.hashCode() == book2.hashCode());
  }

  @Test
  public void testHashDifferent() throws ImproperCreatorException {
    Book diffTitle = new Book(DICTIONARY_AUTHOR, JANE_EYRE, DICT_YEAR);
    assertFalse(book.hashCode() == diffTitle.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("'Dictionary' written by Merriam Webster -- published in 2021",
        book.toString());
  }
}