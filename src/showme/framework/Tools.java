package showme.framework;
import java.awt.Color;
import java.util.*;

public class Tools {
	public static Color randomColor(Random r) {
		return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}
	
	public static double min(double... x) {
		double min = Double.POSITIVE_INFINITY;
		for (double xx : x) {
			min = Math.min(min, xx);
		}
		return min;
	}

	public static double max(double... x) {
		double max = Double.NEGATIVE_INFINITY;
		for (double xx : x) {
			max = Math.max(max, xx);
		}
		return max;
	}
}
