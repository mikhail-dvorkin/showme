package samples;

import java.awt.Color;
import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Visualizer;

public class queens implements Visualizer {
	@Override
	public List<Figure> process(File f) throws IOException {
		List<Figure> figures = new ArrayList<Figure>();
		Scanner in = new Scanner(f);
		int n = 0;
		while (in.hasNext()) {
			String s = in.next();
			char c = s.charAt(0);
			int y = Integer.parseInt(s.substring(1)) - 1;
			int x = (c >= 'a' && c <= 'z') ? (c - 'a') : (c - 'A' + 26);
			figures.add(new Circle(0.5 + x, 0.5 + y, 0.4).setFillColor(Color.MAGENTA).setFillOpacity(1));
			n = Math.max(n, x);
			n = Math.max(n, y);
		}
		n++;
		for (int i = 0; i <= n; i++) {
			figures.add(new Segment(0, i, n, i));
			figures.add(new Segment(i, 0, i, n));
		}
		in.close();
		return figures;
	}
}
