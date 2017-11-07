package showme.figure;

import java.awt.Color;

import showme.framework.SVGEnvironment;

public abstract class Figure {
	protected Color color = Color.BLACK;
	protected double opacity = 1;
	protected double thickness = 1;
	protected Color fillColor = null;
	protected double fillOpacity = 0;
	
	public abstract Box boundingBox();
	
	public abstract String toSVG(SVGEnvironment env);

	public Color color() {
		return color;
	}

	public Figure setColor(Color color) {
		this.color = color;
		return this;
	}

	public double opacity() {
		return opacity;
	}

	public Figure setOpacity(double opacity) {
		this.opacity = opacity;
		return this;
	}

	public double thickness() {
		return thickness;
	}

	public Figure setThickness(double thickness) {
		this.thickness = thickness;
		return this;
	}

	public Color fillColor() {
		return fillColor;
	}

	public Figure setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		return this;
	}

	public double fillOpacity() {
		return fillOpacity;
	}

	public Figure setFillOpacity(double fillOpacity) {
		this.fillOpacity = fillOpacity;
		return this;
	}
}
