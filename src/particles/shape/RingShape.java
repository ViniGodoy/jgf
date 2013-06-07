package jgf.particles.shape;

import java.util.Random;

import jgf.math.Vector2D;

public class RingShape implements EmitterShape {
	private static final Random RND = new Random();

	private boolean filled;
	private double semiMajorAxis;
	private double semiMinorAxis;
	private double angle;

	public RingShape(boolean filled, double majorAxis, double minorAxis,
			double angle) {
		this.filled = filled;
		this.semiMajorAxis = majorAxis / 2;
		this.semiMinorAxis = minorAxis / 2;
		this.angle = angle;
	}

	public RingShape(boolean filled, double majorAxis, double minorAxis) {
		this(filled, majorAxis, minorAxis, 0);
	}

	public RingShape(boolean filled, double radius) {
		this(filled, radius, radius, 0);
	}
	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public double getMajorAxis() {
		return semiMajorAxis * 2;
	}

	public void setMajorAxis(double majorAxis) {
		this.semiMajorAxis = majorAxis / 2;
	}

	public double getMinorAxis() {
		return semiMinorAxis * 2;
	}

	public void setMinorAxis(double minorAxis) {
		this.semiMinorAxis = minorAxis / 2;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	@Override
	public Vector2D nextPosition(Vector2D center) {
		double distFromCenter = filled ? RND.nextDouble() : 1.0;

		double alpha = RND.nextDouble() * 2 * Math.PI;
		double sinalpha = Math.sin(alpha);
		double cosalpha = Math.cos(alpha);

		double sinbeta = Math.sin(angle);
		double cosbeta = Math.cos(angle);

		return new Vector2D(
				center.getX() + (semiMajorAxis * cosalpha * cosbeta - semiMinorAxis * sinalpha * sinbeta) * distFromCenter, 
				center.getY() + (semiMajorAxis * cosalpha * sinbeta + semiMinorAxis * sinalpha * cosbeta) * distFromCenter);
	}

	@Override
	public void process(double secs) {
	}
}
