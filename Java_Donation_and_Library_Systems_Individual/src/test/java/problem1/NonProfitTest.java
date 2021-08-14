package problem1;

import static org.junit.Assert.*;

import com.sun.org.apache.bcel.internal.generic.NEW;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class NonProfitTest {

  private NonProfit nonProfit;
  private NonProfit nonProfit2;
  private static final String NAME = "Toys 'R No More";
  private static final String NEW_NAME = "Radio Shackles";
  private static final Donation[] EMPTY_ARRAY = new Donation[0];
  private static final LocalDateTime CREATION_DATE = LocalDateTime.of(
      2010, 9, 18, 15, 52);
  private static final LocalDateTime PROCESSING_DATE = LocalDateTime.of(
      2012, 5, 6, 9, 14);
  private static final Float DONATION_AMOUNT = 58.62f;
  private static final Float NEW_AMOUNT = 3458.62f;
  private static final Donation oneTime = new OneTimeDonation(CREATION_DATE, DONATION_AMOUNT);
  private static final Donation monthly = new MonthlyDonation(CREATION_DATE, DONATION_AMOUNT);
  private static final Float ROUNDING_PRECISION = 0.01f;


  @Before
  public void setUp() throws InvalidDateException {
    nonProfit = new NonProfit(NAME);
    nonProfit2 = new NonProfit(NAME);
  }

  @Test
  public void getOrganizationName() {
    assertEquals(NAME, nonProfit.getOrganizationName());
  }

  @Test
  public void getDonations() {
    assertArrayEquals(EMPTY_ARRAY, nonProfit.getDonations());
  }

  @Test
  public void setOrganizationName() {
    nonProfit.setOrganizationName(NEW_NAME);
    assertEquals(NEW_NAME, nonProfit.getOrganizationName());
  }

  @Test
  public void noDonations() {
    nonProfit.noDonations();
    assertArrayEquals(EMPTY_ARRAY, nonProfit.getDonations());
  }

  @Test
  public void add() throws InvalidDateException {
    Donation pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit.add(oneTime);
    nonProfit.add(monthly);
    nonProfit.add(pledge);
    Donation[] expectedDonations = {oneTime, monthly, pledge};
    assertArrayEquals(expectedDonations, nonProfit.getDonations());
  }

  @Test
  public void addDuplicate() {
    nonProfit.add(oneTime);
    nonProfit.add(monthly);
    nonProfit.add(oneTime);
    Donation[] expectedDonations = {oneTime, monthly};
    assertArrayEquals(expectedDonations, nonProfit.getDonations());
  }

  @Test
  public void addNull() {
    nonProfit.add(null);
    nonProfit.add(monthly);
    nonProfit.add(oneTime);
    Donation[] expectedDonations = {monthly, oneTime};
    assertArrayEquals(expectedDonations, nonProfit.getDonations());
  }

  @Test
  public void contains() {
    nonProfit.add(monthly);
    nonProfit.add(oneTime);
    assertTrue(nonProfit.contains(oneTime));
    assertTrue(nonProfit.contains(monthly));
  }

  @Test
  public void doesNotContain() {
    nonProfit.add(oneTime);
    assertFalse(nonProfit.contains(monthly));
  }

  @Test
  public void removeFirst() throws InvalidDateException {
    Donation pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit.add(oneTime);
    nonProfit.add(monthly);
    nonProfit.add(pledge);
    nonProfit.remove(oneTime);
    Donation[] expectedDonations = {monthly, pledge};
    assertArrayEquals(expectedDonations, nonProfit.getDonations());
  }

  @Test
  public void removeMiddle() throws InvalidDateException {
    Donation pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit.add(oneTime);
    nonProfit.add(monthly);
    nonProfit.add(pledge);
    nonProfit.remove(monthly);
    Donation[] expectedDonations = {oneTime, pledge};
    assertArrayEquals(expectedDonations, nonProfit.getDonations());
  }

  @Test
  public void removeLast() throws InvalidDateException {
    Donation pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit.add(oneTime);
    nonProfit.add(monthly);
    nonProfit.add(pledge);
    nonProfit.remove(pledge);
    Donation[] expectedDonations = {oneTime, monthly};
    assertArrayEquals(expectedDonations, nonProfit.getDonations());
  }

  @Test
  public void removeDoesNotContain() throws InvalidDateException {
    Donation pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit.add(oneTime);
    nonProfit.add(pledge);
    nonProfit.remove(monthly);
    Donation[] expectedDonations = {oneTime, pledge};
    assertArrayEquals(expectedDonations, nonProfit.getDonations());
  }

  @Test
  public void removeNull() throws InvalidDateException {
    Donation pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit.add(monthly);
    nonProfit.add(oneTime);
    nonProfit.add(pledge);
    nonProfit.remove(null);
    Donation[] expectedDonations = {monthly, oneTime, pledge};
    assertArrayEquals(expectedDonations, nonProfit.getDonations());
  }

  @Test
  public void getTotalDonationsForYearNoProcessing() throws InvalidDateException {
    float expectedTotal = 293.10f;
    Donation pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit.add(monthly);
    nonProfit.add(oneTime);
    nonProfit.add(pledge);
    float actualTotal = nonProfit.getTotalDonationsForYear(2010);
    assertEquals(expectedTotal, actualTotal, ROUNDING_PRECISION);
  }

  @Test
  public void getTotalDonationsForYearNoOneTime() throws InvalidDateException {
    float expectedTotal = 4162.06f;
    Donation pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit.add(monthly);
    nonProfit.add(oneTime);
    nonProfit.add(pledge);
    float actualTotal = nonProfit.getTotalDonationsForYear(2012);
    assertEquals(expectedTotal, actualTotal, ROUNDING_PRECISION);
  }

  @Test
  public void testEqualsReflexive() {
    assertTrue(nonProfit.equals(nonProfit));
  }

  @Test
  public void testEqualsSymmetric() {
    assertTrue(nonProfit.equals(nonProfit2) &&
        nonProfit2.equals(nonProfit));
  }

  @Test
  public void testEqualsTransitive() throws InvalidDateException {
    NonProfit nonProfit3 = new NonProfit(NAME);
    assertTrue(nonProfit.equals(nonProfit2) &&
        nonProfit2.equals(nonProfit3) &&
        nonProfit.equals(nonProfit3));
  }

  @Test
  public void testEqualsConsistent() {
    boolean result = nonProfit.equals(nonProfit2);
    assertEquals(result, nonProfit.equals(nonProfit2));
  }

  @Test
  public void testEqualsNull() {
    assertFalse(nonProfit.equals(null));
  }

  @Test
  public void testEqualsDifferentClasses() {
    NullPointerException exception = new NullPointerException();
    assertFalse(nonProfit.equals(exception));
  }

  @Test
  public void testEqualsFieldCoverage() throws InvalidDateException {
    NonProfit diffName = new NonProfit(NEW_NAME);
    assertFalse(nonProfit.equals(diffName));

    Pledge pledge = new Pledge(CREATION_DATE, NEW_AMOUNT, PROCESSING_DATE);
    nonProfit2.add(pledge);
    nonProfit2.add(monthly);
    assertFalse(nonProfit.equals(nonProfit2));
  }

  @Test
  public void testHashSelfConsistent() {
    assertTrue(nonProfit.hashCode() == nonProfit.hashCode());
  }

  @Test
  public void testHashConsistent() {
    assertTrue(nonProfit.hashCode() == nonProfit2.hashCode());
  }

  @Test
  public void testHashDifferent() throws InvalidDateException {
    nonProfit2.setOrganizationName(NEW_NAME);
    assertFalse(nonProfit.hashCode() == nonProfit2.hashCode());
  }

  @Test
  public void testToStringWithoutDonations() {
    assertEquals("Toys 'R No More's donations: []",
        nonProfit.toString());
  }

  @Test
  public void testToStringWithDonations() {
    nonProfit.add(oneTime);
    nonProfit.add(monthly);
    assertEquals("Toys 'R No More's donations: [This one-time donation was created "
            + "on 2010-09-18T15:52 for the amount of $58.62., This monthly donation was "
            + "created on 2010-09-18T15:52.\nAn amount of $58.62 will be billed each "
            + "month until null.]",
        nonProfit.toString());
  }
}