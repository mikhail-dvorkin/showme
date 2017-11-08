package showme.framework;

import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.ichthyop.util.MetaFilenameFilter;
import showme.visualizers.*;

public class ShowMe {
	public static int wid = 800;
	public static int hei = 600;
	public static double stroke = 0.2;
	public static double border = 5;
	public static String gallery = null;
	public static boolean reverse = false;
	private static Visualizer vis = new Viz(); // Put your visualizer here

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		List<String> masks = new ArrayList<String>();
		String dir = null;
		for (String arg : args) {
			if (arg.toLowerCase().startsWith("--size=")) {
				try {
					Scanner in = new Scanner(arg.substring("--size=".length()));
					in.useDelimiter("\\D+");
					wid = in.nextInt();
					hei = in.nextInt();
					in.close();
					continue;
				} catch (Exception e) {
					throw new IllegalArgumentException("Can't parse argument: " + arg, e);
				}
			}
			if (arg.toLowerCase().startsWith("--border=")) {
				try {
					border = Double.parseDouble(arg.substring("--border=".length()));
					continue;
				} catch (Exception e) {
					throw new IllegalArgumentException("Can't parse argument: " + arg, e);
				}
			}
			if (arg.toLowerCase().startsWith("--stroke=")) {
				try {
					stroke = Double.parseDouble(arg.substring("--stroke=".length()));
					continue;
				} catch (Exception e) {
					throw new IllegalArgumentException("Can't parse argument: " + arg, e);
				}
			}
			if (arg.toLowerCase().startsWith("--gallery=")) {
				gallery = arg.substring("--gallery=".length());
				continue;
			}
			if (arg.toLowerCase().startsWith("--reverse")) {
				reverse = true;
				continue;
			}
			
			if (dir == null) {
				dir = arg;
				continue;
			}
			masks.add(arg);
		}
		if (dir == null || masks.isEmpty()) {
			String run = "java -jar showme.jar";
			System.out.println("Visulizer \"" + vis.getClass().getSimpleName() + "\", ShowMe 1.0 by Mikhail Dvorkin, mikhail.dvorkin@gmail.com");
			System.out.println();
			System.out.println("Usage:");
			System.out.println(run + " [--border=BORDER] [--stroke=STROKEWIDTH]");
			System.out.println(run.replaceAll(".", " ") + " [--gallery=NAME.html] [--size=WIDTHxHEIGHT] [--reverse]");
			System.out.println(run.replaceAll(".", " ") + " <directory with visualized files>");
			System.out.println(run.replaceAll(".", " ") + " <names/masks of visualized files>");
			System.out.println();
			System.out.println("Defaults:");
			System.out.println("	border=5");
			System.out.println("	stroke=0.2");
			System.out.println("	size=800x600");
			System.out.println();
			System.out.println("Example:");
			System.out.println(run + " . ??");
			System.out.println("    - visualize all files with 2-character names in the current directory ");
			System.exit(1);
		}
		File curDir = new File(dir);
		if (!curDir.isDirectory()) {
			throw new IllegalArgumentException(dir + " is not an existing directory");
		}
		Collection<File> files = new TreeSet<File>();
		for (String mask : masks) {
			File[] satisfy = curDir.listFiles(new MetaFilenameFilter(mask));
			for (File f : satisfy) {
				files.add(f);
			}
		}
		if (reverse) {
			files = new ArrayList<File>(files);
			Collections.reverse((List<?>) files);
		}
		SVGEnvironment env = new SVGEnvironment(stroke, border);
		System.out.print("Files to visualize:");
		for (File f : files) {
			System.out.print(" " + f.getName());
		}
		System.out.println();
		
		for (File f : files) {
			System.out.print("Visualizing " + f + "...");
			try {
				List<Figure> figures = vis.process(f);
				makeSVG(figures, f, env);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println(" done.");
		}
		if (gallery != null) {
			System.out.print("Making gallery " + gallery + "...");
			try {
				makeGallery(files);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println(" done.");
		}
	}

	private static void makeSVG(List<Figure> figures, File f, SVGEnvironment env) throws IOException {
		PrintWriter out = new PrintWriter(new File(f.getPath() + ".svg"));
		if (figures.isEmpty()) {
			figures.add(new Text(0, 0, "No figures"));
		}
		Box box = new Box(figures);
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		out.println("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
		out.println("<svg width=\"100%\" height=\"100%\" viewBox=\"" + env.viewBox(box) + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");
		out.println("<g transform=\"scale(1 -1)\">");
		for (Figure fig : figures) {
			out.println(fig.toSVG(env));
		}
		out.println("</g>");
		out.println("</svg>");
		out.close();
	}

	private static void makeGallery(Collection<File> files) throws IOException {
		PrintWriter out = new PrintWriter(gallery);
		out.println("<html><body><center>");
		for (File f : files) {
			String svg = f.getPath() + ".svg";
			out.println("<iframe src=\"" + svg + "\" width=\"" + wid + "px\" height=\"" + hei + "px\" frameborder=\"0\" marginwidth=\"0\"  marginheight=\"0\">");
		    out.println("<embed src=\"" + svg + "\" width=\"" + wid + "px\" height=\"" + hei + "px\" type=\"gtaw/svg+xml\" />"); 
		    out.println("</iframe><a href=\"" + svg + "\">" + svg + "</a>");
		}
		out.println("</center></body></html>");
		out.close();
	}
}
