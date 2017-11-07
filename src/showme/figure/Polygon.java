package showme.figure;

import java.util.*;

import showme.framework.SVGEnvironment;

public class Polygon extends Figure {
	private int n;
	private double[] x;
	private double[] y;
	
	public Polygon() {
	}
	
	public Polygon(double[] x, double[] y) {
		this.n = x.length;
		this.x = Arrays.copyOf(x, n);
		this.y = Arrays.copyOf(y, n);
	}
	
	static public Polygon read(Scanner in) {
		int n = in.nextInt();
		return Polygon.read(in, n);
	}
	
	static public Polygon read(Scanner in, int n) {
		Polygon poly = new Polygon();
		poly.n = n;
		poly.x = new double[n];
		poly.y = new double[n];
		for (int i = 0; i < n; i++) {
			poly.x[i] = in.nextDouble();
			poly.y[i] = in.nextDouble();
		}
		return poly;
	}

	@Override
	public Box boundingBox() {
		return new Box(x, y);
	}

	@Override
	public String toSVG(SVGEnvironment env) {
		StringBuilder points = new StringBuilder();
		for (int i = 0; i < n; i++) {
			points.append(x[i]).append(",").append(y[i]).append(" ");
		}
		return env.element("polygon",
				"points", points.toString().trim(),
				"stroke-width", env.thickness(thickness),
				"stroke", env.color(color),
				"stroke-opacity", "" + opacity,
				"fill", env.color(fillColor),
				"fill-opacity", "" + fillOpacity);
	}
}
