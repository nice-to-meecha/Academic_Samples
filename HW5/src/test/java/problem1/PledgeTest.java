package problem1;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class PledgeTest {

  private Pledge donation;
  private Pledge donation2;
  private Pledge donationWithoutProcessing;
  private static final LocalDateTime CREATION_DATE = LocalDateTime.of(
      2010, 9, 18, 15, 52);
  private static final Float DONATION_AMOUNT = 13496.41f;
  private static final LocalDateTime PROCESSING_DATE = LocalDateTime.of(
      2017, 4, 16, 11, 23);
  private static final LocalDateTime NEW_DATE = LocalDateTime.of(
      2013, 7, 24, 7, 12);
  private static final Float NEW_AMOUNT = 375125.01f;
  private static final Float ROUNDING_PRECISION = 0.01f;

  @Before
  public void setUp() throws InvalidDateException {
    donation = new Pledge(CREATION_DATE, DONATION_AMOUNT, PROCESSING_DATE);
    donation2 = new Pledge(CREATION_DATE, DONATION_AMOUNT, PROCESSING_DATE);
    donationWithoutProcessing = new Pledge(CREATION_DATE, DONATION_AMOUNT);
  }

  @Test
  public void getProcessingDate() {
    assertEquals(PROCESSING_DATE, donation.getProcessingDate());
  }

  @Test
  public void setProcessingDate() throws InvalidDateException {
    donationWithoutProcessing.setProcessingDate(NEW_DATE);
    assertEquals(NEW_DATE, donationWithoutProcessing.getProcessingDate());
  }

  @Test (expected = InvalidDateException.class)
  public void setBadProcessingDate() throws InvalidDateException {
    LocalDateTime badDate = LocalDateTime.of(2010, 9, 18,
        15, 51);
    donationWithoutProcessing.setProcessingDate(badDate);
  }

  @Test
  public void calculateDonationRightYear() {
    assertEquals(DONATION_AMOUNT, donation.calculateDonation(2017));
  }

  @Test
  public void calculateDonationWrongYear() {
    assertEquals(0f, donation.calculateDonation(2018), ROUNDING_PRECISION);
  }

  @Test
  public void calculateDonationNoProcessingDate() {
    assertEquals(0f, donationWithoutProcessing.calculateDonation(2017),
        ROUNDING_PRECISION);
  }

  @Test
  public void testEqualsReflexive() {
    assertTrue(donation.equals(donation));
  }

  @Test
  public void testEqualsSymmetric() {
    assertTrue(donation.equals(donation2) &&
        donation2.equals(donation));
  }

  @Test
  public void testEqualsTransitive() throws InvalidDateException {
    Pledge donation3 = new Pledge(CREATION_DATE, DONATION_AMOUNT, PROCESSING_DATE);
    assertTrue(donation.equals(donation2) &&
        donation2.equals(donation3) &&
        donation.equals(donation3));
  }

  @Test
  public void testEqualsConsistent() {
    boolean result = donation.equals(donation2);
    assertEquals(result, donation.equals(donation2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(donation.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(donation.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() throws InvalidDateException {
    Pledge diffDate = new Pledge(NEW_DATE, DONATION_AMOUNT, PROCESSING_DATE);
    assertFalse(donation.equals(diffDate));

    Pledge diffAmount = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    assertFalse(donation.equals(diffAmount));

    LocalDateTime newProcessingDate = LocalDateTime.of(2014, 10, 31,
        11, 59);
    donation2.setProcessingDate(newProcessingDate);
    assertFalse(donation.equals(donation2));
  }

  @Test
  public void testHashSelfConsistent() {
    assertTrue(donation.hashCode() == donation.hashCode());
  }

  @Test
  public void testHashConsistent() {
    assertTrue(donation.hashCode() == donation2.hashCode());
  }

  @Test
  public void testHashDifferent() throws InvalidDateException {
    Pledge diffDate = new Pledge(NEW_DATE, DONATION_AMOUNT, PROCESSING_DATE);
    assertFalse(donation.hashCode() == diffDate.hashCode());
  }

  @Test
  public void testToStringWithoutProcessingDate() {
    assertEquals("This pledge was created on 2010-09-18T15:52 "
            + "for an amount of $13496.41. It will be processed on null.",
        donationWithoutProcessing.toString());
  }

  @Test
  public void testToStringWithProcessingDate() {
    assertEquals("This pledge was created on 2010-09-18T15:52 "
            + "for an amount of $13496.41. It will be processed on 2017-04-16T11:23.",
        donation.toString());
  }
}