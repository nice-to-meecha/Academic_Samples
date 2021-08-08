package problem2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class BandTest {

  private Band band;
  private Band band2;
  private String name = "Out of Our Minds";
  private String newName = "We'll Never Be the Same";
  private ArrayList<RecordingArtist> members = new ArrayList<RecordingArtist>();
  private static final RecordingArtist REMI = new RecordingArtist("Remi", "Wolf");
  private static final RecordingArtist OMAR = new RecordingArtist("Omar", "Apollo");
  private static final RecordingArtist DPR_LIVE = new RecordingArtist("DPR", "Live");
  private static final RecordingArtist SILLY = new RecordingArtist("Silly", "Fool");

  @Before
  public void setUp() throws ImproperCreatorException {
    members.add(REMI);
    members.add(OMAR);
    members.add(DPR_LIVE);
    band = new Band(name, members);
    band2 = new Band(name, members);
  }

  @Test (expected = ImproperCreatorException.class)
  public void constructorNullMemberError() throws ImproperCreatorException {
    Band nullBand = new Band(name, null);
  }

  @Test
  public void getName() {
    assertEquals(name, band.getName());
  }

  @Test
  public void getBandMembers() {
    assertEquals(members, band.getBandMembers());
  }

  @Test
  public void setName() {
    band.setName(newName);
    assertEquals(newName, band.getName());
  }

  @Test
  public void setBandMembers() throws ImproperCreatorException {
    ArrayList<RecordingArtist> newMembers = new ArrayList<RecordingArtist>();
    newMembers.add(SILLY);
    newMembers.add(DPR_LIVE);
    band.setBandMembers(newMembers);
    assertEquals(newMembers, band.getBandMembers());
  }

  @Test (expected = ImproperCreatorException.class)
  public void setBandMembersNullError() throws ImproperCreatorException {
    band.setBandMembers(null);
  }

  @Test
  public void isCorrectCreator() {
    assertTrue(band.isCorrectCreator(DPR_LIVE));
  }

  @Test
  public void isIncorrectCreator() {
    assertFalse(band.isCorrectCreator(SILLY));
  }

  @Test
  public void testEqualsReflexive() {
    assertTrue(band.equals(band));
  }

  @Test
  public void testEqualsSymmetric() {
    assertTrue(band.equals(band2) &&
        band2.equals(band));
  }

  @Test
  public void testEqualsTransitive() throws ImproperCreatorException {
    Band band3 = new Band(name, members);
    assertTrue(band.equals(band2) &&
        band2.equals(band3) &&
        band.equals(band3));
  }

  @Test
  public void testEqualsConsistent() {
    boolean result = band.equals(band2);
    assertEquals(result, band.equals(band2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(band.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(band.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() throws ImproperCreatorException {
    Band diffName = new Band(newName, members);
    assertFalse(band.equals(diffName));

    ArrayList<RecordingArtist> newMembers = new ArrayList<RecordingArtist>();
    newMembers.add(REMI);
    newMembers.add(OMAR);
    newMembers.add(DPR_LIVE);
    newMembers.add(SILLY);
    Band diffMembers = new Band(name, newMembers);
    assertFalse(band.equals(diffMembers));
  }

  @Test
  public void testHashSelfConsistent() {
    assertTrue(band.hashCode() == band.hashCode());
  }

  @Test
  public void testHashConsistent() {
    assertTrue(band.hashCode() == band2.hashCode());
  }

  @Test
  public void testHashDifferent() throws ImproperCreatorException {
    ArrayList<RecordingArtist> newMembers = new ArrayList<RecordingArtist>();
    newMembers.add(REMI);
    newMembers.add(OMAR);
    newMembers.add(DPR_LIVE);
    newMembers.add(SILLY);
    Band diffMembers = new Band(name, newMembers);
    assertFalse(band.hashCode() == diffMembers.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Out of Our Minds with: [Remi Wolf, Omar Apollo, DPR Live]",
        band.toString());
  }
}