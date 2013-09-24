
/**
 * JabberPoint Main Programma
 * <P>
 * This program is distributed under the terms of the accompanying COPYRIGHT.txt
 * file (which is NOT the GNU General Public License). Please read it. Your use
 * of the software constitutes acceptance of the terms in the COPYRIGHT.txt
 * file.
 * 
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: JabberPoint.java,v 1.1 2002/12/17 Gert Florijn
 * @version $Id: JabberPoint.java,v 1.2 2003/11/19 Sylvia Stuurman
 * @version $Id: JabberPoint.java,v 1.3 2004/08/17 Sylvia Stuurman
 * @version $Id: JabberPoint.java,v 1.4 2007/07/16 Sylvia Stuurman
 */

public class JabberPoint {

	/** Het Main Programma */
	public static void main(String argv[]) {
		Style.createStyles();
		
		PresentationFacade presFacade;

		if (argv.length == 0) { // een demo presentatie
			presFacade = new PresentationFacade();
		} else {
			presFacade = new PresentationFacade(argv[0]);
		}

		new SlideViewerFrame("JabberPoint 1.4 - OU version", presFacade);
		
		// TODO: Make this happen without setting slide number.
		presFacade.setSlideNumber(0);
	}
}
