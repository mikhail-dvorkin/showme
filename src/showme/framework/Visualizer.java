package showme.framework;

import java.io.*;
import java.util.*;

import showme.figure.Figure;

public interface Visualizer {
	public abstract List<Figure> process(File f) throws IOException;
}
