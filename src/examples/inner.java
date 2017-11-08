package samples;

import java.awt.Color;
import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Visualizer;

public class inner implements Visualizer {
	@Override
	public List<Figure> process(File f) throws IOException {
		List<Figure> figures = new ArrayList<Figure>();
		Scanner in = new Scanner(f);
		figures.add(Polygon.read(in));
		in.close();
		
		File f2 = new File(f.getPath() + ".a");
		in = new Scanner(f2);
		if (in.hasNext("NONE")) {
			figures.add(new Text(0, 0, "NONE").setFontSize(30).setColor(Color.RED));
			in.close();
			return figures;
		}
		while (in.hasNext()) {
			figures.add(Point.read(in).setColor(Color.RED).setThickness(2));
		}
		in.close();
		return figures;
	}
}
