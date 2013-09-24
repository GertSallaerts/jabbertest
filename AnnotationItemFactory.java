public class AnnotationItemFactory extends SlideItemFactory {
	@Override
	public SlideItem create(int level, Object content) {
		if (content instanceof Line) {
			return new AnnotationItem((Line) content);
		} else {
			return new AnnotationItem();
		}
	}
}