import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public abstract class Drawing {	
	Style style;
	float scale;
	Graphics g;
	ImageObserver view;
	
	public Drawing(Style style, Rectangle area, Graphics g, ImageObserver view) {
		this.style = style;
		this.scale = Math.min(((float)area.width) / ((float)Slide.referenceWidth), ((float)area.height) / ((float)Slide.referenceHeight));
		this.g = g;
		this.view = view;
	}
	public abstract Rectangle getBoundingBox(Object content);
	public abstract void draw(int offsetX, int offsetY, Object content);
}
