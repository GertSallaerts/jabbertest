import java.util.ArrayList;

/**
 * Presentation houdt de slides in de presentatie bij.
 * <p>
 * In the Presentation's world, page numbers go from 0 to n-1 * <p>
 * This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Presentation.java,v 1.1 2002/12/17 Gert Florijn
 * @version $Id: Presentation.java,v 1.2 2003/11/19 Sylvia Stuurman
 * @version $Id: Presentation.java,v 1.3 2004/08/17 Sylvia Stuurman
 * @version $Id: Presentation.java,v 1.4 2007/07/16 Sylvia Stuurman
 */

// Verandering: Presentation wordt een Observable
public class Presentation {
	private String showTitle; // de titel van de presentatie
	private ArrayList<Slide> showList = null; // een ArrayList met de Slides
	private int currentSlideNumber = 0; // het slidenummer van de huidige Slide
	// Verandering: we kennen slideViewComponent niet meer direct
	// private SlideViewerComponent slideViewComponent = null; // de viewcomponent voor de Slides

	public Presentation() {
		// Verandering: Presentation heeft slideViewComponent nu niet meer als attribuut
		//slideViewComponent = null;
		clear();
	}

// Methode die wordt gebruikt door de Controller
// om te bepalen wat er getoond wordt.
	public int getSize() {
		return showList.size();
	}

	public String getTitle() {
		return showTitle;
	}

	public void setTitle(String nt) {
		showTitle = nt;
	}

	// Verandering: deze methode hebben we niet meer nodig
	// public void setShowView(SlideViewerComponent slideViewerComponent) {
	//  	this.slideViewComponent = slideViewerComponent;
	// }

// geef het nummer van de huidige slide
	public int getSlideNumber() {
		return currentSlideNumber;
	}

// verander het huidige-slide-nummer en laat het aan het window weten.
	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		// Verandering: het updaten van de SlideViewerComponent gebeurt nu via het Observer patroon
		//if (slideViewComponent != null) {
		//	slideViewComponent.update(this, getCurrentSlide());
		//}
	}

// Verwijder de presentatie, om klaar te zijn voor de volgende
	void clear() {
		showList = new ArrayList<Slide>();
		setTitle("New presentation");
		setSlideNumber(-1);
	}

// Voeg een slide toe aan de presentatie
	public void append(Slide slide) {
		showList.add(slide);
	}

// Geef een slide met een bepaald slidenummer
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()){
			return null;
		}
		return (Slide)showList.get(number);
	}

// Geef de huidige Slide
	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}
}
