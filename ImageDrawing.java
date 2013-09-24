import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class ImageDrawing extends Drawing {

	public ImageDrawing(Style style, Rectangle area, Graphics g,
			ImageObserver view) {
		super(style, area, g, view);
	}

	@Override
	public Rectangle getBoundingBox(Object content) {
		BufferedImage image = (BufferedImage) content;
		if(image == null) { return new Rectangle(0, 0); }
		
		return new Rectangle((int) (style.indent * scale), 0,
				(int) (image.getWidth(view) * scale),
				((int) (style.leading * scale)) + (int) (image.getHeight(view) * scale));
	}

	@Override
	public void draw(int offsetX, int offsetY, Object content) {
		BufferedImage image = (BufferedImage) content;
		if(image == null) { return; }
		
		int width = offsetX + (int) (style.indent * scale);
	    int height = offsetY + (int) (style.leading * scale);
	    g.drawImage(image, width, height,(int) (image.getWidth(view)*scale),
	                (int) (image.getHeight(view)*scale), view);
	}

}
