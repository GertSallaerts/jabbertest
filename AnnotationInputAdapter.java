import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;


public class AnnotationInputAdapter extends MouseInputAdapter {
	
	int x, y;
	PresentationFacade presentation;

	public AnnotationInputAdapter(PresentationFacade p) {
		this.presentation = p;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		presentation.addAnnotation(x, y, arg0.getX(), arg0.getY());
		
		x = arg0.getX();
		y = arg0.getY();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

}
