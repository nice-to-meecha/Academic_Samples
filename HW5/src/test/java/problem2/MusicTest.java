package problem2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class MusicTest {

  private Music music;
  private Music bandMusic;
  private Band band;
  private static final RecordingArtist REMI = new RecordingArtist("Remi", "Wolf");
  private static final RecordingArtist OMAR = new RecordingArtist("Omar", "Apollo");
  private static final RecordingArtist DPR = new RecordingArtist("DPR", "Live");
  private static final String DISCO_MAN = "Disco Man";
  private static final Integer DISCO_YEAR = 2019;
  private static final Author JANE_EYRE_AUTHOR = new Author("Emily", "Bronte");
  private ArrayList<RecordingArtist> members = new ArrayList<RecordingArtist>();

  @Before
  public void setUp() throws ImproperCreatorException {
    members.add(REMI);
    members.add(OMAR);
    members.add(DPR);
    band = new Band("What Are We Doing", members);
    music = new Music(REMI, DISCO_MAN, DISCO_YEAR);
    bandMusic = new Music(band, "No Idea", 2024);
  }

  @Test (expected = ImproperCreatorException.class)
  public void constructorCreatorError() throws ImproperCreatorException {
    Music wrongCreator = new Music(JANE_EYRE_AUTHOR, DISCO_MAN, DISCO_YEAR);
  }

  @Test
  public void setCreator() throws ImproperCreatorException {
    music.setCreator(OMAR);
    assertEquals(OMAR, music.getCreator());
  }

  @Test (expected = ImproperCreatorException.class)
  public void setCreatorCreatorError() throws ImproperCreatorException {
    music.setCreator(JANE_EYRE_AUTHOR);
  }

  @Test (expected = ImproperCreatorException.class)
  public void setBandCreatorCreatorError() throws ImproperCreatorException {
    bandMusic.setCreator(JANE_EYRE_AUTHOR);
  }

  @Test
  public void testToString() {
    assertEquals("'Disco Man' sung by Remi Wolf -- released in 2019",
        music.toString());
  }
}