package showme.visualizers;

import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Visualizer;

public class template implements Visualizer {
	@Override
	public List<Figure> process(File f) throws IOException {
		List<Figure> figures = new ArrayList<Figure>();
		Scanner in = new Scanner(f);
		
		in.close();
		return figures;
	}
}
