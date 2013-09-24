public class TextItemFactory extends SlideItemFactory {

	@Override
	public SlideItem create(int level, Object content) {
		String text;
		if (content instanceof String) {
			text = (String) content;
		} else {
			text = "[Invalid Item]";
		}

		return new TextItem(level, text);
	}

}
