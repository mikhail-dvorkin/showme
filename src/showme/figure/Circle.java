package showme.figure;

import java.util.Scanner;

import showme.framework.SVGEnvironment;

public class Circle extends Figure {
	private double x, y, r;
	
	public Circle(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public static Circle read(Scanner in) {
		return new Circle(in.nextDouble(), in.nextDouble(), in.nextDouble());
	}

	@Override
	public Box boundingBox() {
		return new Box(x - r, y - r, x + r, y + r);
	}

	@Override
	public String toSVG(SVGEnvironment env) {
		return env.element("circle",
				"cx", "" + x,
				"cy", "" + y,
				"r", "" + r,
				"stroke-width", env.thickness(thickness),
				"stroke", env.color(color),
				"stroke-opacity", "" + opacity,
				"fill", env.color(fillColor),
				"fill-opacity", "" + fillOpacity);
	}
}
