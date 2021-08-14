package problem2;

import java.util.Objects;

/**
 * Represents the two possible items that may be stored within
 * a catalog -- books and music.
 *
 * @author bello
 */
public abstract class Item {

  protected Creator creator;
  protected String title;
  protected Integer year;

  /**
   * Generates an object of an Item subclass, utilizing the creator
   * of the item, as well as its title and the year it was released
   * or published.
   *
   * @param creator - the creator of the item
   * @param title - the title name of the item
   * @param year - the year that the item was released or published
   */
  public Item(Creator creator, String title, Integer year) {
    this.creator = creator;
    this.title = title;
    this.year = year;
  }

  /**
   * Returns the creator of the item
   *
   * @return the creator of the item
   */
  public Creator getCreator() {
    return this.creator;
  }

  /**
   * Returns the title name of the item
   *
   * @return the title name of the item
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns the year that the item was released or published
   *
   * @return the year that the item was released or published
   */
  public Integer getYear() {
    return this.year;
  }

  /**
   * Sets the title name of the item
   *
   * @param title - the title name of the item
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Sets the year that the item was released or published
   *
   * @param year - the year that the item was released or published
   */
  public void setYear(Integer year) {
    this.year = year;
  }

  /**
   * Determines whether an Item object is equivalent to another object,
   * comparing memory location, class, and field values.
   *
   * @param o - the object being compared with an Item object
   *
   * @return boolean indicating whether an Item object is equivalent
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
    Item item = (Item) o;
    return this.creator.equals(item.creator) &&
        this.title.equals(item.title) &&
        this.year.equals(item.year);
  }

  /**
   * Determines the hashcode of an Item object
   *
   * @return the hashcode of an Item object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.creator, this.title, this.year);
  }
}
