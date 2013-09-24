import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;


// Om het Observer patroon te implementeren
import java.util.Observer;
import java.util.Observable;
import java.util.Vector;


/** SlideViewerComponent is een grafische component die Slides kan laten zien.
 * <P>
 * This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: SlideViewerComponent.java,v 1.1 2002/12/17 Gert Florijn
 * @version $Id: SlideViewerComponent.java,v 1.2 2003/11/19 Sylvia Stuurman
 * @version $Id: SlideViewerComponent.java,v 1.3 2004/08/17 Sylvia Stuurman
 * @version $Id: SlideViewerComponent.java,v 1.4 2007/07/16 Sylvia Stuurman
 */

// Veranderd: de klasse wordt een Observer van Presentation
public class SlideViewerComponent extends JComponent implements Observer {
	private static final long serialVersionUID = 227L;
	// VERANDERD: ook het slidenumber en de afmetingen onthouden
	private int slidenumber;
	private int aantalSlides;
	private Vector<SlideItem> slideItems; // de huidige slide
	private Font labelFont = null; // het font voor labels
	
//	VERANDERD: de presentatie hoeft niet meer onthouden te worden
	//private Presentation presentation = null; // de presentatie

	public SlideViewerComponent() {
		setBackground(Color.white); // dit zou ooit van Style afkomstig kunnen zijn
		//presentation = pres;
		labelFont = new Font("Dialog", Font.BOLD, 10);
	}

	public Dimension getPreferredSize() {
		return new Dimension(Slide.referenceWidth, Slide.referenceHeight);
	}

//	 VERANDERD: update veranderen we zo dat het aan de signatuur van Observer voldoet
	public void update(Observable observable, Object object) {
//		 VERANDERD: het object dat wordt meegestuurd is een Slide
		
		PresentationFacade pres = (PresentationFacade) observable;
		if (pres == null) {
			repaint();
			return;
		}
		
		this.slideItems = pres.getCurrentSlideItems();
		this.slidenumber = pres.getCurrentSlideNumber();
		this.aantalSlides = pres.getTotalSlides();
			
		repaint();
  }
	  
// teken de slide
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getSize().width, getSize().height);
//		 VERANDERD: we kennen slidenummer zelf
	    if ((slidenumber < 0) || (slideItems == null)) {
	      return;
	    }
		g.setFont(labelFont);
		g.setColor(Color.black);
//		 Veranderd: we kennen het slidenummer en het aantal slides nu zelf
	    // g.drawString("Slide " + (1+presentation.getSlideNumber()) + " of " +
	    //             presentation.getSize(), 600, 30);
	    g.drawString("Slide " + (1+slidenumber) + " of " + aantalSlides, 600, 30);
		Rectangle area = new Rectangle(0, 20, getWidth(), (getHeight()-20));
		
		int y = area.y;
		for (SlideItem item : slideItems) {
			// drawing code.
			Style style = Style.getStyle(item.getLevel());
			Drawing drawer = DrawingFactory.createDrawing(item.getClass(), style, area, g, this);
			y += item.draw(area.x, y, drawer).height;
		}
	}
}
