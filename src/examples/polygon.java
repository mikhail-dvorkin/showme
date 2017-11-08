package examples;

import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Visualizer;

public class polygon implements Visualizer {
	@Override
	public List<Figure> process(File f) throws IOException {
		List<Figure> figures = new ArrayList<Figure>();
		Scanner in = new Scanner(f);
		Circle c = new Circle(0, 0, 1);
		c.setThickness(0);
//		c.setFillColor(Color.RED);
		c.setFillOpacity(0.5);
		figures.add(c);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			Point p = new Point(in.nextDouble(), in.nextDouble());
			figures.add(p);
		}
		double area = in.nextDouble();
		Text t = new Text(0.8, 0.8, "" + area).setFontSize(0.3);
		figures.add(t);
		in.close();
		return figures;
	}
}
