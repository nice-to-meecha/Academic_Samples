package problem1;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class OneTimeDonationTest {

  private OneTimeDonation donation;
  private OneTimeDonation donation2;
  private static final LocalDateTime CREATION_DATE = LocalDateTime.of(
      2010, 9, 18, 15, 52);
  private static final Float DONATION_AMOUNT = 58.62f;
  private static final LocalDateTime NEW_DATE = LocalDateTime.of(
      2013, 7, 24, 7, 12);
  private static final Float NEW_AMOUNT = 1268.07f;

  @Before
  public void setUp() {
    donation = new OneTimeDonation(CREATION_DATE, DONATION_AMOUNT);
    donation2 = new OneTimeDonation(CREATION_DATE, DONATION_AMOUNT);
  }

  @Test
  public void calculateDonationRightYear() {
    Float expectedAmount = DONATION_AMOUNT;
    Float actualAmount = donation.calculateDonation(2010);
    assertEquals(expectedAmount, actualAmount);
  }

  @Test
  public void calculateDonationWrongYear() {
    Float expectedAmount = 0f;
    Float actualAmount = donation.calculateDonation(2011);
    assertEquals(expectedAmount, actualAmount);

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
    Donation donation3 = new OneTimeDonation(CREATION_DATE, DONATION_AMOUNT);
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
  public void testEqualsFieldCoverage() {
    Donation diffDate = new MonthlyDonation(NEW_DATE, DONATION_AMOUNT);
    assertFalse(donation.equals(diffDate));

    Donation diffAmount = new MonthlyDonation(CREATION_DATE, NEW_AMOUNT);
    assertFalse(donation.equals(diffAmount));
  }

  @Test
  public void testHashCodeSelfConsistent() {
    assertTrue(donation.hashCode() == donation.hashCode());
  }

  @Test
  public void testHashCodeConsistent() {
    assertTrue(donation.hashCode() == donation2.hashCode());
  }

  @Test
  public void testHashCodeSelfDifferent() {
    donation2.setDonationAmount(NEW_AMOUNT);
    assertFalse(donation.hashCode() == donation2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("This one-time donation was created on 2010-09-18T15:52" +
        " for the amount of $58.62.", donation.toString());
  }
}