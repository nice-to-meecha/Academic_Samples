package problem2;

import static org.junit.Assert.*;

import com.sun.org.apache.bcel.internal.generic.NEW;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class RecordingArtistTest {

  private RecordingArtist artist;
  private RecordingArtist artist2;
  private static final String FIRST = "Remi";
  private static final String LAST = "Wolf";
  private static final String NEW_FIRST = "Omar";
  private static final String NEW_LAST = "Apollo";

  @Before
  public void setUp() {
    artist = new RecordingArtist(FIRST, LAST);
    artist2 = new RecordingArtist(FIRST, LAST);
  }

  @Test
  public void getFirstName() {
    assertEquals(FIRST, artist.getFirstName());
  }

  @Test
  public void getLastName() {
    assertEquals(LAST, artist.getLastName());
  }

  @Test
  public void setFirstName() {
    artist.setFirstName(NEW_FIRST);
    assertEquals(NEW_FIRST, artist.getFirstName());
  }

  @Test
  public void setLastName() {
    artist.setLastName(NEW_LAST);
    assertEquals(NEW_LAST, artist.getLastName());
  }

  @Test
  public void isCorrectCreator() {
    RecordingArtist remi = new RecordingArtist(FIRST, LAST);
    assertTrue(artist.isCorrectCreator(remi));
  }

  @Test
  public void isIncorrectCreator() {
    RecordingArtist omar = new RecordingArtist(NEW_FIRST, NEW_LAST);
    assertFalse(artist.isCorrectCreator(omar));
  }

  @Test
  public void testEqualsReflexive() {
    assertTrue(artist.equals(artist));
  }

  @Test
  public void testEqualsSymmetric() {
    assertTrue(artist.equals(artist2) &&
        artist2.equals(artist));
  }

  @Test
  public void testEqualsTransitive() throws UnacceptableLibraryException {
    RecordingArtist artist3 = new RecordingArtist(FIRST, LAST);
    assertTrue(artist.equals(artist2) &&
        artist2.equals(artist3) &&
        artist.equals(artist3));
  }

  @Test
  public void testEqualsConsistent() {
    boolean result = artist.equals(artist2);
    assertEquals(result, artist.equals(artist2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(artist.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(artist.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() {
    RecordingArtist diffFirst = new RecordingArtist(NEW_FIRST, LAST);
    assertFalse(artist.equals(diffFirst));

    RecordingArtist diffLast = new RecordingArtist(FIRST, NEW_LAST);
    assertFalse(artist.equals(diffLast));
  }

  @Test
  public void testHashSelfConsistent() {
    assertTrue(artist.hashCode() == artist.hashCode());
  }

  @Test
  public void testHashConsistent() {
    assertTrue(artist.hashCode() == artist2.hashCode());
  }

  @Test
  public void testHashDifferent() {
    RecordingArtist diffFirst = new RecordingArtist(NEW_FIRST, LAST);
    assertFalse(artist.hashCode() == diffFirst.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Remi Wolf", artist.toString());
  }
}