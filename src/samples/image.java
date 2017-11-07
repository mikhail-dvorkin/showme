package samples;

import java.awt.Color;
import java.io.*;
import java.util.*;

import showme.figure.*;
import showme.framework.Visualizer;

public class image implements Visualizer {
	private final double gap = 2;
	private final int cols = 4;
	private final String moves = "UDLR";
	
	@Override
	public List<Figure> process(File f) throws IOException {
		List<Figure> figures = new ArrayList<Figure>();
		File f2 = new File(f.getPath() + ".a");
		Scanner in = new Scanner(f);
		int n, hei, wid;
		// Simplified version has only two parameters 
		int a = in.nextInt();
		int b = in.nextInt();
		if (in.hasNextInt()) {
			n = a;
			hei = b;
			wid = in.nextInt();
		} else {
			n = 2;
			hei = a;
			wid = b;
		}
		boolean[][] black = new boolean[hei][wid];
		for (int k = 0; k < n; k++) {
			double p = (k % cols) * (wid + gap);
			double q = (k / cols) * (hei + gap);
			for (int i = 0; i < hei; i++) {
				String s = in.next();
				for (int j = 0; j < wid; j++) {
					black[i][j] = s.charAt(j) == 'B';
					Rectangle r = new Rectangle(p + j, - q - i, p + j + 1, - q - i - 1);
					r.setFillColor(black[i][j] ? Color.BLACK : Color.WHITE).setFillOpacity(1);
					r.setColor(Color.GRAY).setOpacity(0.5);
					figures.add(r);
				}
			}
			if (!f2.exists()) {
				continue;
			}
			Scanner in2 = new Scanner(f2);
			StringBuilder sb = new StringBuilder();
			while (in2.hasNext()) {
				sb.append(in2.next());
			}
			in2.close();
			String s = sb.toString();
			int i = 0;
			int x = 0;
			int y = 0;
			while (true) {
				char c = s.charAt(i);
				if (c >= '0' && c <= '9') {
					break;
				}
				if (moves.contains("" + c)) {
					int xx = x;
					int yy = y;
					switch (c) {
					case 'U':
						xx--;
						break;
					case 'D':
						xx++;
						break;
					case 'L':
						yy--;
						break;
					case 'R':
						yy++;
					}
					figures.add(new Segment(p + y + 0.5, - (q + x + 0.5), p + yy + 0.5, - (q + xx + 0.5)).setColor(Color.RED));
					x = xx;
					y = yy;
					i++;
					continue;
				}
				if (c == ')') {
					i++;
					continue;
				}
				i++;
				if (black[x][y]) {
					int level = 0;
					while (true) {
						char d = s.charAt(i);
						i++;
						if (d == '(') {
							level++;
							continue;
						}
						if (d == ')') {
							level--;
							continue;
						}
						if (d == ':' && level == 0) {
							break;
						}
					}
					continue;
				}
				continue;
			}
		}
		in.close();
		return figures;
	}
}
