package showme.figure;

import showme.framework.SVGEnvironment;

public class Text extends Figure {
	private double x, y;
	private double fontSize;
	private String text;
	private String fontFamily = "Courier New";

	public Text(double x, double y, String text) {
		super();
		this.x = x;
		this.y = y;
		this.text = text;
	}

	public double fontSize() {
		return fontSize;
	}

	public Text setFontSize(double fontSize) {
		this.fontSize = fontSize;
		return this;
	}

	@Override
	public Box boundingBox() {
		return new Box(x, y, x, y);
//		return new Box(x, y, x + fontSize * text.length(), y + fontSize);
	}

	@Override
	public String toSVG(SVGEnvironment env) {
		return env.openElement("text",
				"x", "" + x,
				"y", "" + y,
				"font-size", "" + env.thickness(fontSize),
				"font-family", fontFamily,
				"fill", env.color(color),
				"fill-opacity", "" + opacity,
				"transform", "scale(1 -1)")
				+ text +
				env.closeElement("text");
	}
}
