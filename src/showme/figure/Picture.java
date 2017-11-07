package showme.figure;

import java.util.*;

import showme.framework.SVGEnvironment;

/**
 * @deprecated Not decided how to implement boundingBox()
 */
@Deprecated
public class Picture extends Figure {
	private Collection<Figure> content;
	private String transform;

	public Picture(Collection<Figure> content) {
		this.content = content;
		this.transform = "";
	}
	
	private Picture transform(String t, double... parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append(t).append("(");
		boolean first = true;
		for (double p : parameters) {
			if (!first) {
				sb.append(", ");
			}
			first = false;
			sb.append(p);
		}
		sb.append(")");
		if (transform.isEmpty()) {
			transform += " ";
		}
		transform += sb.toString();
		return this;
	}
	
	public Picture translate(double x, double y) {
		return this.transform("translate", x, y);
	}

	public Picture scale(double sx, double sy) {
		return this.transform("scale", sx, sy);
	}

	public Picture rotate(double angle, double cx, double cy) {
		return this.transform("rotate", angle, cx, cy);
	}

	public Picture skewX(double angle) {
		return this.transform("skewX", angle);
	}

	public Picture skewY(double angle) {
		return this.transform("skewY", angle);
	}

	public Picture matrix(double a, double b, double c, double d, double e, double f) {
		return this.transform("matrix", a, b, c, d, e, f);
	}

	@Override
	public Box boundingBox() {
		return new Box(content);
	}

	@Override
	public String toSVG(SVGEnvironment env) {
		StringBuilder sb = new StringBuilder();
		sb.append("<g" + (transform.isEmpty() ? "" : ("transform=\"" + transform + "\"")) + ">");
		for (Figure f : content) {
			sb.append(f.toSVG(env));
		}
		sb.append("</g>");
		return sb.toString();
	}
}
