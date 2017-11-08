package examples;

import java.awt.Color;
import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Visualizer;

public class four implements Visualizer {
	@Override
	public List<Figure> process(File f) throws IOException {
		List<Figure> figures = new ArrayList<Figure>();
		Scanner in = new Scanner(f);
		for (int i = 0; i < 4; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			figures.add(new Point(x, y).setColor(Color.RED).setThickness(4));
		}
		in.close();
		File f2 = new File(f.getPath() + ".a");
		if (f2.exists()) {
			in = new Scanner(f2);
			figures.add(Polygon.read(in, 4));
			in.close();
		}
		return figures;
	}
}
