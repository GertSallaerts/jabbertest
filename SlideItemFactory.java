public abstract class SlideItemFactory {
	public static SlideItemFactory getFactory(String type) {
		switch (type) {
		case "text":
			return new TextItemFactory();
		case "image":
			return new BitmapItemFactory();
		case "annotation":
			return new AnnotationItemFactory();
		default:
			return new TextItemFactory();
		}
	}

	public abstract SlideItem create(int level, Object content);
}
