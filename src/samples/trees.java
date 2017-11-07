package samples;
import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Tools;
import showme.framework.Visualizer;


public class trees implements Visualizer {
	@Override
	public List<Figure> process(File f) throws IOException {
		List<Figure> figures = new ArrayList<Figure>();
		File f2 = new File(f.getPath() + ".a");
		Scanner in = new Scanner(f2);
		Random r = new Random(f.hashCode());
		while (in.hasNext()) {
			figures.add(Polygon.read(in, 3).setFillColor(Tools.randomColor(r)).setFillOpacity(0.5));
		}
		in.close();
		return figures;
	}
}
