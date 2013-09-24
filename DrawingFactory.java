import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public abstract class DrawingFactory {

	public static Drawing createDrawing(Class c, Style style, Rectangle area, Graphics g, ImageObserver view) {
		if(c == TextItem.class) {
			return new TextDrawing(style, area, g, view);
		} else if (c == BitmapItem.class) {
			return new ImageDrawing(style, area, g, view);
		} else if (c == AnnotationItem.class) {
			return new LineDrawing(style, area, g, view);
		} else {
			return new TextDrawing(style, area, g, view);
		}
	}
}
