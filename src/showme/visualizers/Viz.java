package showme.visualizers;

import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Visualizer;

public class Viz implements Visualizer {
	@Override
	public List<Figure> process(File f) throws IOException {
		List<Figure> figures = new ArrayList<Figure>();
		Scanner in = new Scanner(f);
		
		in.close();
		// in = new Scanner(new File(f.getPath() + ".a"));
		return figures;
	}
}
