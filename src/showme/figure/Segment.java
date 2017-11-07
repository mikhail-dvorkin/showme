package showme.figure;

import java.util.Scanner;

import showme.framework.SVGEnvironment;

public class Segment extends Figure {
	private double x1, y1, x2, y2;

	public Segment(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public static Segment read(Scanner in) {
		return new Segment(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());
	}

	@Override
	public Box boundingBox() {
		return new Box(x1, y1).unite(new Box(x2, y2));
	}

	@Override
	public String toSVG(SVGEnvironment env) {
		return env.element("line",
				"x1", "" + x1,
				"y1", "" + y1,
				"x2", "" + x2,
				"y2", "" + y2,
				"stroke-width", env.thickness(thickness),
				"stroke", env.color(color),
				"stroke-opacity", "" + opacity);
	}
}
