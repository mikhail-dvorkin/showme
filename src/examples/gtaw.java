package examples;

import java.awt.Color;
import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Visualizer;

public class gtaw implements Visualizer {
	private final int cols = 4;
	private final double gap = 0.3;

	@Override
	public List<Figure> process(File f) throws IOException {
		File f2 = new File(f.getPath() + ".a");
		Set<Integer> good = new HashSet<Integer>();
		if (f2.exists()) {
			Scanner in = new Scanner(f2);
			int n = in.nextInt();
			for (int i = 0; i < n; i++) {
				good.add(in.nextInt());
			}
			in.close();
		}
		
		List<Figure> figures = new ArrayList<Figure>();
		Scanner in = new Scanner(f);
		int n = in.nextInt();
		int r = in.nextInt();
		figures.add(new Circle(0, 0, r).setFillColor(Color.GRAY).setFillOpacity(1));
		Polygon bolt = readPolygon(in, 0, 0);
		figures.add(bolt.setFillColor(Color.white).setFillOpacity(1));
		for (int i = 1; i <= n; i++) {
			double xc = (i % cols) * r * (2 + gap);
			double yc = - (i / cols) * r * (2 + gap);
			figures.add(new Circle(xc, yc, r));
			Polygon wrench = readPolygon(in, xc, yc);
			boolean ok = good.contains(i);
			figures.add(wrench.setFillColor(ok ? Color.GREEN : Color.BLACK).setFillOpacity(1));
		}
		in.close();
		return figures;
	}

	private static Polygon readPolygon(Scanner in, double xc, double yc) {
		int n = in.nextInt();
		double[] x = new double[n];
		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			int r = in.nextInt();
			double phi = in.nextInt() * Math.PI / 180;
			x[i] = xc + r * Math.cos(phi);
			y[i] = yc + r * Math.sin(phi);
		}
		return new Polygon(x, y);
	}
}
