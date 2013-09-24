import java.io.IOException;
import java.util.Observable;
import java.util.Vector;

import javax.swing.JOptionPane;

public class PresentationFacade extends Observable {
	private Presentation presentation;

	public Presentation getPresentation() {
		return presentation;
	}
	
	public int getCurrentSlideNumber() {
		return presentation.getSlideNumber();
	}
	
	public int getTotalSlides() {
		return presentation.getSize();
	}
	
	public Vector<SlideItem> getCurrentSlideItems() {
		Vector<SlideItem> items = new Vector<SlideItem>(presentation.getCurrentSlide().getSlideItems());
		items.insertElementAt(presentation.getCurrentSlide().getTitle(), 0);
		return items;
	}

	public PresentationFacade() {
		createPresentation();
	}

	public PresentationFacade(String XMLFilePath) {
		createPresentation(XMLFilePath);
	}
	
	public void createPresentation() {
		presentation = createPresentation(Accessor.getDemoAccessor(), "");
		this.setChanged();
	    this.notifyObservers();
	}
	
	public void createPresentation(String XMLFilePath) {
		presentation = createPresentation(new XMLAccessor(), XMLFilePath);
		this.setChanged();
	    this.notifyObservers();
	}
	
	public void nextSlide() {
		int currentSlideNumber = presentation.getSlideNumber();
		if (currentSlideNumber < (presentation.getSize()-1)) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}
	
	public void prevSlide() {
		int currentSlideNumber = presentation.getSlideNumber();
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
		}
	}
	
	public void setSlideNumber(int slide) {
		presentation.setSlideNumber(slide);
		this.setChanged();
	    this.notifyObservers();
	}
	
	public void addAnnotation(int x1, int y1, int x2, int y2) {	
		SlideItem i = SlideItemFactory.getFactory("annotation")
				.create(0, new Line(x1, y1, x2, y2, Style.getAnnotationColor()));
		presentation.getCurrentSlide().append(i);
		
		this.setChanged();
	    this.notifyObservers();
	}
	
	public void emptyPresentation() {
		presentation.clear();
	}
	
	public void exit(int n) {
		System.exit(n);
	}

	private Presentation createPresentation(Accessor a, String path) {
		Presentation presentation = new Presentation();
		try {
			a.loadFile(presentation, path);
			presentation.setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "IO Error: " + ex,
					"JabberPoint Error", JOptionPane.ERROR_MESSAGE);
		}
		return presentation;
	}

}
