import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextDrawing extends Drawing {
	
	public TextDrawing(Style style, Rectangle area, Graphics g,
			ImageObserver view) {
		super(style, area, g, view);
	}

	@Override
	public Rectangle getBoundingBox(Object content) {
		String text = (String) content;
		if(text == null) { text = content.toString(); }
				
		List<TextLayout> layouts = getLayouts(text);
	    int xsize = 0, ysize = (int) (style.leading * scale);
	    Iterator<TextLayout> iterator = layouts.iterator();
	    while (iterator.hasNext()) {
	      TextLayout layout = (TextLayout) iterator.next();
	      Rectangle2D bounds = layout.getBounds();
	      if (bounds.getWidth() > xsize) {
	        xsize = (int) bounds.getWidth();
	      }
	      if (bounds.getHeight() > 0) {
	        ysize += bounds.getHeight();
	      }
	      ysize += layout.getLeading() + layout.getDescent();
	    }
	    return new Rectangle((int) (style.indent*scale), 0, xsize, ysize );
	}

	@Override
	public void draw(int offsetX, int offsetY, Object content) {
		// TODO: check all casts! not safe like C#
		String text = (String) content;
		if(text == null) { text = content.toString(); }
		
		if (text == null || text.length() == 0) {
			return;
		}
		
		List<TextLayout> layouts = getLayouts(text);
		Point pen = new Point(offsetX + (int) (style.indent * scale), offsetY
				+ (int) (style.leading * scale));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(style.color);
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = (TextLayout) it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}

	}
	
	private AttributedString getAttributedString(String content) {
	    AttributedString attrStr = new AttributedString(content);
	    attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, content.length());
	    return attrStr;
	}
	
	private List<TextLayout> getLayouts(String content) {
	    List<TextLayout> layouts = new ArrayList<TextLayout>();
	    AttributedString attrStr = getAttributedString(content);
	    Graphics2D g2d = (Graphics2D) g;
	    FontRenderContext frc = g2d.getFontRenderContext();
	    LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
	    float wrappingWidth = (Slide.referenceWidth - style.indent) * scale;
	    while (measurer.getPosition() < content.length()) {
	      TextLayout layout = measurer.nextLayout(wrappingWidth);
	      layouts.add(layout);
	    }
	    return layouts;
	  }

}
