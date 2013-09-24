import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * Het applicatiewindow voor een slideviewcomponent
 * 
 * @version $Id: SlideViewerFrame.java,v 1.1 2002/12/17 Gert Florijn
 * @version $Id: SlideViewerFrame.java,v 1.2 2003/11/19 Sylvia Stuurman
 * @version $Id: SlideViewerFrame.java,v 1.3 2004/08/17 Sylvia Stuurman
 * @version $Id: SlideViewerFrame.java,v 1.4 2007/07/16 Sylvia Stuurman
 */

public class SlideViewerFrame extends JFrame {
	private static final long serialVersionUID = 3227L;

	public SlideViewerFrame(String title, PresentationFacade presentation) {
		super(title);
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent();
		// VERANDERD:
		// we laten de verantwoordelijkheid nu aan SlideViewComponent zelf over om
		// zich bij Presentation aan te melden. presentation.setShowView(slideViewerComponent);
		setupWindow(slideViewerComponent, presentation);
	}

	// De GUI opzetten
	public void setupWindow(SlideViewerComponent slideViewerComponent,
			PresentationFacade presentation) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getContentPane().add(slideViewerComponent);
		presentation.addObserver(slideViewerComponent);
		addKeyListener(new KeyController(presentation)); // een controller toevoegen
		setJMenuBar(new MenuController(this, presentation, slideViewerComponent)); // nog een controller toevoegen
		setSize(new Dimension(800, 600)); // Ooit kan Presentatie dat aan gaan geven
		setVisible(true);
	}
}
