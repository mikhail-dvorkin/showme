package showme.figure;

import java.util.Collection;

import showme.framework.Tools;

public class Box {
	public final double x1, y1, x2, y2;

	public Box(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Box(double x, double y) {
		this(x, y, x, y);
	}
	
	public Box(double[] x, double[] y) {
		this(Tools.min(x), Tools.min(y), Tools.max(x), Tools.max(y));
	}
	
	public Box(Collection<Figure> figures) {
		Box box = null;
		for (Figure f : figures) {
			box = f.boundingBox().unite(box);
		}
		if (box == null) {
			throw new IllegalArgumentException("No figures specified");
		}
		this.x1 = box.x1;
		this.y1 = box.y1;
		this.x2 = box.x2;
		this.y2 = box.y2;
	}
	
	public Box unite(Box that) {
		if (that == null)
			return this;
		return new Box(Math.min(x1, that.x1), Math.min(y1, that.y1), Math.max(x2, that.x2), Math.max(y2, that.y2));
	}
}
