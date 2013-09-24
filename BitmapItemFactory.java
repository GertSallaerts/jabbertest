public class BitmapItemFactory extends SlideItemFactory {

	@Override
	public SlideItem create(int level, Object content) {
		if (content instanceof String) {
			return new BitmapItem(level, (String) content);
		} else {
			return new TextItem(level, "[Invalid Image Path]");
		}
	}
}
