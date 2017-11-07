package showme.framework;

import java.awt.Color;

import showme.figure.Box;

@SuppressWarnings("static-method")
public class SVGEnvironment {
	private static final double eps = 1e-3;
	private double strokeWidth;
	private double border;
	
	public SVGEnvironment(double strokeWidth, double border) {
		this.strokeWidth = strokeWidth;
		this.border = border;
	}

	public String thickness(double thickness) {
		return strokeWidth * thickness + "%";
	}
	
	public String color(Color color) {
		if (color == null)
			return "none";
		return "#" + Integer.toHexString(color.getRGB()).substring(2);
	}
	
	public String viewBox(Box box) {
		double wid = box.x2 - box.x1;
		double dx = wid * border / (100 - 2 * border);
		if (wid + 2 * dx == 0) {
			dx += eps;
		}
		double hei = box.y2 - box.y1;
		double dy = hei * border / (100 - 2 * border);
		if (hei + 2 * dy == 0) {
			dy += eps;
		}
		return (box.x1 - dx) + " " + (- box.y2 - dy) + " " + (wid + 2 * dx) + " " + (hei + 2 * dy);
	}

	public String openElement(String name, String... parameters) {
		String s = element(name, parameters);
		return s.substring(0, s.length() - 2) + ">";
	}
	
	public String closeElement(String name) {
		return "</" + name + ">";
	}
	
	public String element(String name, String... parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(name);
		for (int i = 0; i < parameters.length; i += 2) {
			if (parameters[i + 1] == null)
				continue;
			sb.append(" ").append(parameters[i]).append("=\"").append(parameters[i + 1]).append("\"");
		}
		sb.append("/>");
		return sb.toString();
	}
}
