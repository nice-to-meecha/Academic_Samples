package problem1;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class MonthlyDonationTest {

  private MonthlyDonation donation;
  private MonthlyDonation donation2;
  private static final LocalDateTime CREATION_DATE = LocalDateTime.of(
      2010, 9, 18, 15, 52);
  private static final Float DONATION_AMOUNT = 37.48f;
  private static final LocalDateTime CANCELLATION_DATE = LocalDateTime.of(
      2017, 4, 16, 11, 23);
  private static final LocalDateTime NEW_DATE = LocalDateTime.of(
      2013, 7, 24, 7, 12);
  private static final Float NEW_AMOUNT = 351.27f;
  private static final Float ROUNDING_PRECISION = 0.01f;

  @Before
  public void setUp() throws Exception {
    donation = new MonthlyDonation(CREATION_DATE, DONATION_AMOUNT);
    donation2 = new MonthlyDonation(CREATION_DATE, DONATION_AMOUNT);
  }

  @Test
  public void getCancellationDate() {
    assertNull(donation.getCancellationDate());
  }

  @Test
  public void setCancellationDate() throws InvalidDateException {
    donation.setCancellationDate(CANCELLATION_DATE);
    assertEquals(CANCELLATION_DATE, donation.getCancellationDate());
  }

  @Test (expected = InvalidDateException.class)
  public void setCancellationDateError() throws InvalidDateException {
    LocalDateTime badDate = LocalDateTime.of(2010, 9, 18,
        15, 51);
    donation.setCancellationDate(badDate);
  }

  @Test
  public void calculateDonationNull() throws InvalidDateException {
    float expectedAmount = 149.92f;
    float actualAmount = donation.calculateDonation(2010);
    assertEquals(expectedAmount, actualAmount, ROUNDING_PRECISION);
    assertNull(donation.getCancellationDate());
  }

  @Test
  public void calculateDonationEndBefore() throws InvalidDateException {
    LocalDateTime endBefore = LocalDateTime.of(2011, 12, 31,
        23, 59);
    donation.setCancellationDate(endBefore);
    float expectedAmount = 0f;
    float actualAmount = donation.calculateDonation(2012);
    assertEquals(expectedAmount, actualAmount, ROUNDING_PRECISION);
  }

  @Test
  public void calculateDonationStartAfter() throws InvalidDateException {
    float expectedAmount = 0f;
    float actualAmount = donation.calculateDonation(2009);
    assertEquals(expectedAmount, actualAmount, ROUNDING_PRECISION);
    assertNull(donation.getCancellationDate());
  }

  @Test
  public void calculateDonationStartBeforeEndDuring() throws InvalidDateException {
    LocalDateTime endDuring = LocalDateTime.of(2012, 3, 31,
        10, 28);
    donation.setCancellationDate(endDuring);
    float expectedAmount = 112.44f;
    float actualAmount = donation.calculateDonation(2012);
    assertEquals(expectedAmount, actualAmount, ROUNDING_PRECISION);
  }

  @Test
  public void calculateDonationStartDuringEndAfter() throws InvalidDateException {
    donation.setCreationDate(NEW_DATE);
    LocalDateTime endAfter = LocalDateTime.of(2014, 1, 1,
        0, 0);
    donation.setCancellationDate(endAfter);
    float expectedAmount = 224.88f;
    float actualAmount = donation.calculateDonation(2013);
    assertEquals(expectedAmount, actualAmount, ROUNDING_PRECISION);
  }

  @Test
  public void calculateDonationStartBeforeEndAfter() throws InvalidDateException {
    donation.setCancellationDate(NEW_DATE);
    float expectedAmount = 449.76f;
    float actualAmount = donation.calculateDonation(2011);
    assertEquals(expectedAmount, actualAmount, ROUNDING_PRECISION);
  }

  @Test
  public void calculateDonationStartBeforeEndAfterNoDonation() throws InvalidDateException {
    LocalDateTime noDonation = LocalDateTime.of(2012, 1, 12,
        16, 30);
    donation.setCancellationDate(noDonation);
    float expectedAmount = 0f;
    float actualAmount = donation.calculateDonation(2012);
    assertEquals(expectedAmount, actualAmount, ROUNDING_PRECISION);
  }

  @Test
  public void calculateDonationStartEndDuring() throws InvalidDateException {
    LocalDateTime sameYear = LocalDateTime.of(2010, 11, 11,
        4, 22);
    donation.setCancellationDate(sameYear);
    float expectedAmount = 74.96f;
    float actualAmount = donation.calculateDonation(2010);
    assertEquals(expectedAmount, actualAmount, ROUNDING_PRECISION);
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
  public void testEqualsTransitive() {
    MonthlyDonation donation3 = new MonthlyDonation(CREATION_DATE, DONATION_AMOUNT);
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
    MonthlyDonation diffDate = new MonthlyDonation(NEW_DATE, DONATION_AMOUNT);
    assertFalse(donation.equals(diffDate));

    MonthlyDonation diffAmount = new MonthlyDonation(CREATION_DATE, NEW_AMOUNT);
    assertFalse(donation.equals(diffAmount));

    donation2.setCancellationDate(NEW_DATE);
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
    donation2.setCancellationDate(NEW_DATE);
    assertFalse(donation.hashCode() == donation2.hashCode());
  }

  @Test
  public void testToStringNoCancellationDate() {
    assertEquals("This monthly donation was created on 2010-09-18T15:52."
        + "\nAn amount of $37.48 will be billed each month until null.",
        donation.toString());
  }

  @Test
  public void testToStringWithCancellationDate() throws InvalidDateException {
    donation.setCancellationDate(NEW_DATE);
    assertEquals("This monthly donation was created on 2010-09-18T15:52."
            + "\nAn amount of $37.48 will be billed each month until 2013-07-24T07:12.",
        donation.toString());
  }
}