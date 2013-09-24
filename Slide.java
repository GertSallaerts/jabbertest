import java.util.Vector;

/** Ean slide
 * <P>
 * This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Slide.java,v 1.1 2002/12/17 Gert Florijn
 * @version $Id: Slide.java,v 1.2 2003/11/19 Sylvia Stuurman
 * @version $Id: Slide.java,v 1.3 2004/08/17 Sylvia Stuurman
 * @version $Id: Slide.java,v 1.4 2007/07/16 Sylvia Stuurman
 */

public class Slide {
  public final static int referenceWidth = 800;
  public final static int referenceHeight = 600;
  // Verandering: de title wordt een TextItem in plaats van een String
  protected TextItem title; // de titel wordt apart bewaard
  protected Vector<SlideItem> items; // de slide-items wordne in een Vector bewaard

  public Slide() {
    items = new Vector<SlideItem>();
  }

// Voeg een SlideItem toe
  public void append(SlideItem anItem) {
    items.addElement(anItem);
  }

// geef de titel van de slide
  public TextItem getTitle() {
    return title;
  }
  
  public String getTitleText() {
	    return title.getText();
	  }

// verander de titel van de slide
  // Verandering: de title is een TextItem in plaats van een String
  public void setTitle(String newTitle) {
    title = new TextItem(0, newTitle);
  }

// Maak een TextItem van String, en voeg het TextItem toe
  public void append(int level, String message) {
    append(new TextItem(level, message));
  }

// geef het betreffende SlideItem
  public SlideItem getSlideItem(int number) {
    return (SlideItem)items.elementAt(number);
  }

// geef alle SlideItems in een Vector
  public Vector<SlideItem> getSlideItems() {
    return items;
  }

// geef de afmeting van de Slide
  public int getSize() {
    return items.size();
  }
}
