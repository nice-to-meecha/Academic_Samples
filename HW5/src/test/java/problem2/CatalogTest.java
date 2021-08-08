package problem2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CatalogTest {

  private Catalog catalog;
  private Catalog catalog2;
  private ArrayList<Item> library = new ArrayList<Item>();
  private static final Author DICTIONARY_AUTHOR = new Author("Merriam", "Webster");
  private static final Author JANE_EYRE_AUTHOR = new Author("Emily", "Bronte");
  private static final RecordingArtist REMI = new RecordingArtist("Remi", "Wolf");
  private static final RecordingArtist OMAR = new RecordingArtist("Omar", "Apollo");

  @Before
  public void setUp() throws ImproperCreatorException, UnacceptableLibraryException {
    Item dictionary = new Book(DICTIONARY_AUTHOR, "Dictionary", 2021);
    Item discoMan = new Music(REMI, "Disco Man", 2019);
    library.add(dictionary);
    library.add(discoMan);
    catalog = new Catalog(library);
    catalog2 = new Catalog(library);
  }

  @Test (expected = UnacceptableLibraryException.class)
  public void constructorNullError() throws UnacceptableLibraryException {
    Catalog nullCatalog = new Catalog(null);
  }

  @Test
  public void constructorNoCollection() {
    Catalog emptyCatalog = new Catalog();
    ArrayList<Item> emptyList = new ArrayList<Item>();
    assertEquals(emptyList, emptyCatalog.getLibrary());
  }

  @Test
  public void getLibrary() throws ImproperCreatorException {
    Item dictionary = new Book(DICTIONARY_AUTHOR, "Dictionary", 2021);
    Item discoMan = new Music(REMI, "Disco Man", 2019);
    ArrayList<Item> expectedLibrary = new ArrayList<Item>();
    expectedLibrary.add(dictionary);
    expectedLibrary.add(discoMan);
    assertEquals(expectedLibrary, catalog.getLibrary());
  }

  @Test
  public void setLibrary() throws ImproperCreatorException, UnacceptableLibraryException {
    Item janeEyre = new Book(JANE_EYRE_AUTHOR, "Jane Eyre", 1847);
    Item kamikaze = new Music(OMAR, "Kamikaze", 2020);
    ArrayList<Item> expectedLibrary = new ArrayList<Item>();
    expectedLibrary.add(janeEyre);
    expectedLibrary.add(kamikaze);
    catalog.setLibrary(expectedLibrary);
    assertEquals(expectedLibrary, catalog.getLibrary());
  }

  @Test (expected = UnacceptableLibraryException.class)
  public void setLibraryNull() throws UnacceptableLibraryException {
    catalog.setLibrary(null);
  }

  @Test
  public void addItem() throws ImproperCreatorException {
    Item dictionary = new Book(DICTIONARY_AUTHOR, "Dictionary", 2021);
    Item discoMan = new Music(REMI, "Disco Man", 2019);
    Item janeEyre = new Book(JANE_EYRE_AUTHOR, "Jane Eyre", 1847);
    ArrayList<Item> expectedLibrary = new ArrayList<Item>();
    expectedLibrary.add(dictionary);
    expectedLibrary.add(discoMan);
    expectedLibrary.add(janeEyre);
    catalog.addItem(janeEyre);
    assertEquals(expectedLibrary, catalog.getLibrary());
  }

  @Test
  public void addItemAlreadyInCatalog() throws ImproperCreatorException {
    Item dictionary = new Book(DICTIONARY_AUTHOR, "Dictionary", 2021);
    catalog.addItem(dictionary);
    assertEquals(library, catalog.getLibrary());
  }

  @Test
  public void removeItem() throws ImproperCreatorException {
    Item dictionary = new Book(DICTIONARY_AUTHOR, "Dictionary", 2021);
    Item discoMan = new Music(REMI, "Disco Man", 2019);
    ArrayList<Item> expectedLibrary = new ArrayList<Item>();
    expectedLibrary.add(discoMan);
    catalog.removeItem(dictionary);
    assertEquals(expectedLibrary, catalog.getLibrary());
  }

  @Test
  public void removeItemNotInCatalog() throws ImproperCreatorException {
    Item janeEyre = new Book(JANE_EYRE_AUTHOR, "Jane Eyre", 1847);
    catalog.removeItem(janeEyre);
    assertEquals(library, catalog.getLibrary());
  }

  @Test
  public void searchKeywordSingleLetter() {
    ArrayList<Item> singleLetterSearch = catalog.search("I");
    assertEquals(library, singleLetterSearch);
  }

  @Test
  public void searchKeywordSingleWord() throws ImproperCreatorException {
    Item discoMan = new Music(REMI, "Disco Man", 2019);
    ArrayList<Item> singleWordSearch = catalog.search("dIsCO");
    ArrayList<Item> oneSong = new ArrayList<Item>();
    oneSong.add(discoMan);
    assertEquals(oneSong, singleWordSearch);
  }

  @Test
  public void searchKeywordFullTitle() throws ImproperCreatorException {
    Item discoMan = new Music(REMI, "Disco Man", 2019);
    ArrayList<Item> singleFullTitle = catalog.search("dIsCO mAN");
    ArrayList<Item> oneSong = new ArrayList<Item>();
    oneSong.add(discoMan);
    assertEquals(oneSong, singleFullTitle);
  }

  @Test
  public void searchKeywordMisspelling() throws ImproperCreatorException {
    ArrayList<Item> misspelling = catalog.search("dictionery");
    ArrayList<Item> empty = new ArrayList<Item>();
    assertEquals(empty, misspelling);
  }

  @Test
  public void searchKeywordNonexistentLetter() throws ImproperCreatorException {
    ArrayList<Item> misspelling = catalog.search("Z");
    ArrayList<Item> empty = new ArrayList<Item>();
    assertEquals(empty, misspelling);
  }

  @Test
  public void searchAuthor() throws ImproperCreatorException {
    Item dictionary = new Book(DICTIONARY_AUTHOR, "Dictionary", 2021);
    ArrayList<Item> merriam = catalog.search(DICTIONARY_AUTHOR);
    ArrayList<Item> expectedDictionary = new ArrayList<Item>();
    expectedDictionary.add(dictionary);
    assertEquals(expectedDictionary, merriam);
  }

  @Test
  public void searchAuthorSameNameWrongClass() {
    Author writingRemi = new Author("Remi", "Wolf");
    ArrayList<Item> result = catalog.search(writingRemi);
    ArrayList<Item> empty = new ArrayList<Item>();
    assertEquals(empty, result);
  }

  @Test
  public void searchAuthorNotInList() {
    Author melville = new Author("Herman", "Melville");
    ArrayList<Item> result = catalog.search(melville);
    ArrayList<Item> empty = new ArrayList<Item>();
    assertEquals(empty, result);
  }

  @Test
  public void searchRecordingArtist() throws ImproperCreatorException {
    Item discoMan = new Music(REMI, "Disco Man", 2019);
    ArrayList<Item> findDisco = catalog.search(REMI);
    ArrayList<Item> expectedDictionary = new ArrayList<Item>();
    expectedDictionary.add(discoMan);
    assertEquals(expectedDictionary, findDisco);
  }

  @Test
  public void searchRecordingArtistInBand() throws ImproperCreatorException {
    RecordingArtist remi = new RecordingArtist("Remi", "Wolf");
    RecordingArtist omar = new RecordingArtist("Omar", "Apollo");
    RecordingArtist dprLive = new RecordingArtist("DPR", "Live");
    ArrayList<RecordingArtist> dreamTeam = new ArrayList<RecordingArtist>();
    dreamTeam.add(remi);
    dreamTeam.add(omar);
    dreamTeam.add(dprLive);
    Band fake = new Band("Fakin' It 'Til We Make It", dreamTeam);
    Item random = new Music(fake, "Random Name", 2023);
    catalog.addItem(random);
    ArrayList<Item> findLive = catalog.search(dprLive);
    ArrayList<Item> expectedDictionary = new ArrayList<Item>();
    expectedDictionary.add(random);
    assertEquals(expectedDictionary, findLive);
  }

  @Test
  public void searchRecordingArtistThatAbandonedBand() throws ImproperCreatorException {
    RecordingArtist remi = new RecordingArtist("Remi", "Wolf");
    RecordingArtist omar = new RecordingArtist("Omar", "Apollo");
    RecordingArtist silly = new RecordingArtist("Silly", "Fool");
    ArrayList<RecordingArtist> dreamTeam = new ArrayList<RecordingArtist>();
    dreamTeam.add(remi);
    dreamTeam.add(omar);
    Band fake = new Band("Fakin' It 'Til We Make It", dreamTeam);
    Item random = new Music(fake, "Random Name", 2023);
    catalog.addItem(random);
    ArrayList<Item> forgetSilly = catalog.search(silly);
    ArrayList<Item> expectedDictionary = new ArrayList<Item>();
    assertEquals(expectedDictionary, forgetSilly);
  }

  @Test
  public void searchRecordingArtistSameNameWrongClass() {
    RecordingArtist singingDictionary = new RecordingArtist("Merriam", "Webster");
    ArrayList<Item> result = catalog.search(singingDictionary);
    ArrayList<Item> empty = new ArrayList<Item>();
    assertEquals(empty, result);
  }

  @Test
  public void searchRecordingArtistNotInList() {
    ArrayList<Item> result = catalog.search(OMAR);
    ArrayList<Item> empty = new ArrayList<Item>();
    assertEquals(empty, result);
  }

  @Test
  public void testEqualsReflexive() {
    assertTrue(catalog.equals(catalog));
  }

  @Test
  public void testEqualsSymmetric() {
    assertTrue(catalog.equals(catalog2) &&
        catalog2.equals(catalog));
  }

  @Test
  public void testEqualsTransitive() throws UnacceptableLibraryException {
    Catalog catalog3 = new Catalog(library);
    assertTrue(catalog.equals(catalog2) &&
        catalog2.equals(catalog3) &&
        catalog.equals(catalog3));
  }

  @Test
  public void testEqualsConsistent() {
    boolean result = catalog.equals(catalog2);
    assertEquals(result, catalog.equals(catalog2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(catalog.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(catalog.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() {
    Catalog emptyCatalog = new Catalog();
    assertFalse(catalog.equals(emptyCatalog));
  }

  @Test
  public void testHashSelfConsistent() {
    assertTrue(catalog.hashCode() == catalog.hashCode());
  }

  @Test
  public void testHashConsistent() {
    assertTrue(catalog.hashCode() == catalog2.hashCode());
  }

  @Test
  public void testHashDifferent() {
    Catalog emptyCatalog = new Catalog();
    assertFalse(catalog.hashCode() == emptyCatalog.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Catalog: ['Dictionary' written by Merriam Webster -- "
        + "published in 2021, 'Disco Man' sung by Remi Wolf -- "
        + "released in 2019]",
        catalog.toString());
  }
}