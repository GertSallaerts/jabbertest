import java.awt.Rectangle;


public class AnnotationItem extends SlideItem {
	
	Line line;

	public AnnotationItem(Line l) {
		this.line = l;
	}
	
	public AnnotationItem() {
		this.line = new Line();
	}

	@Override
	public Rectangle draw(int offsetX, int offsetY, Drawing drawer) {
		drawer.draw(0, 0, line);
		return new Rectangle(0,0);
	}
}
