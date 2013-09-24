import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * De controller voor het menu
 * <P>
 * This program is distributed under the terms of the accompanying COPYRIGHT.txt
 * file (which is NOT the GNU General Public License). Please read it. Your use
 * of the software constitutes acceptance of the terms in the COPYRIGHT.txt
 * file.
 * 
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: MenuController.java,v 1.1 2002/12/17 Gert Florijn
 * @version $Id: MenuController.java,v 1.2 2003/11/19 Sylvia Stuurman
 * @version $Id: MenuController.java,v 1.3 2004/08/17 Sylvia Stuurman
 * @version $Id: MenuController.java,v 1.4 2007/07/16 Sylvia Stuurman
 */
public class MenuController extends JMenuBar {
	private static final long serialVersionUID = 227L;
	private Frame parent; // het frame, alleen gebruikt als ouder voor de
							// Dialogs
	private PresentationFacade presentation; // wat gecontrolled wordt is de
												// presentatie
	private SlideViewerComponent slideComp;
	private AnnotationInputAdapter m;

	public MenuController(Frame frame, PresentationFacade pres, SlideViewerComponent s) {
		parent = frame;
		presentation = pres;
		slideComp = s;

		add(getFileMenu());
		add(getViewMenu());
		add(getAnnotationsMenu());

		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(mkMenuItem("About", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutBox.show(parent);
			}
		}));
		add(helpMenu); // setHelpMenu is not yet implemented in swing?
	}

	private JMenu getFileMenu() {
		JMenu menu = mkMenu("File");

		menu.add(mkMenuItem("Open", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				presentation.createPresentation("test.xml");
				parent.repaint();
			}
		}));

		menu.add(mkMenuItem("New", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				presentation.emptyPresentation();
				parent.repaint();
			}
		}));

		menu.add(mkMenuItem("Save", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Accessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.saveFile(presentation.getPresentation(),
							"dump.xml");
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent,
							"IOException: " + exc, "Save Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}));

		menu.addSeparator();

		menu.add(mkMenuItem("Exit", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				presentation.exit(0);
			}
		}));

		return menu;
	}

	private JMenu getViewMenu() {
		JMenu menu = mkMenu("View");

		menu.add(mkMenuItem("Next", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				presentation.nextSlide();
			}
		}));

		menu.add(mkMenuItem("Previous", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				presentation.prevSlide();
			}
		}));

		menu.add(mkMenuItem("Goto", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pageNumberStr = JOptionPane
						.showInputDialog((Object) "Page number?");
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setSlideNumber(pageNumber - 1);
			}
		}));

		return menu;
	}

	private JMenu getAnnotationsMenu() {
		JMenu menu = mkMenu("Annotations");
		
		m = new AnnotationInputAdapter(presentation);

		JCheckBoxMenuItem enabled = new JCheckBoxMenuItem("Enabled");
		enabled.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					slideComp.addMouseListener(m);
					slideComp.addMouseMotionListener(m);
				} else {
					slideComp.removeMouseListener(m);
					slideComp.removeMouseMotionListener(m);
				}

			}
		});
		
		menu.add(enabled);

		menu.addSeparator();

		ButtonGroup colorGroup = new ButtonGroup();
		
		Color[] colors = { Color.black, Color.red, Color.green, Color.blue };
		String[] colorNames = { "Black", "Red", "Green", "Blue" };
		for (int i = 0; i < colors.length; i++) {
			final Color c = colors[i];
			JRadioButton b = new JRadioButton(colorNames[i]);

			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Style.setAnnotationColor(c);
				}
			});

			colorGroup.add(b);
			menu.add(b);
		}

		return menu;
	}

	private JMenu mkMenu(String name) {
		return new JMenu(name);
	}

	// een menu-item aanmaken
	private JMenuItem mkMenuItem(String name, ActionListener a) {
		JMenuItem i = new JMenuItem(name);
		i.addActionListener(a);
		return i;
	}
}
