import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;


public class LineDrawing extends Drawing {
	public LineDrawing(Style style, Rectangle area, Graphics g,
			ImageObserver view) {
		super(style, area, g, view);
	}

	@Override
	public Rectangle getBoundingBox(Object content) {
		Line line = (Line) content;
		if(line == null) { return new Rectangle(0, 0); }
		
		int left = line.getX1() < line.getX2() ? line.getX1() : line.getX2();
		int top = line.getY1() < line.getY2() ? line.getY1() : line.getY2();
		
		return new Rectangle(left, top, Math.abs(line.getX1() - line.getX2()), Math.abs(line.getY1() - line.getY2()));
	}

	@Override
	public void draw(int offsetX, int offsetY, Object content) {
		Line line = (Line) content;
		if(line == null) { return; }
		
		Color old = g.getColor();
		
		g.setColor(line.getColor());
		g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
		
		g.setColor(old);
	}

}
