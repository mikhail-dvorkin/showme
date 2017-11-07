package showme.figure;

import java.util.Scanner;

import showme.framework.SVGEnvironment;

public class Point extends Figure {
	private double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Point read(Scanner in) {
		return new Point(in.nextDouble(), in.nextDouble());
	}

	@Override
	public Box boundingBox() {
		return new Box(x, y);
	}

	@Override
	public String toSVG(SVGEnvironment env) {
		return env.element("circle",
				"cx", "" + x,
				"cy", "" + y,
				"r", env.thickness(0.5 * thickness),
				"stroke-width", env.thickness(0.5 * thickness),
				"stroke", env.color(color),
				"stroke-opacity", "" + opacity,
				"fill", env.color(color),
				"fill-opacity", "" + opacity);
	}
}
