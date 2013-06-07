package jgf.particles.shape;

import jgf.math.Vector2D;

public class PointShape implements EmitterShape {
	@Override
	public Vector2D nextPosition(Vector2D center) {
		return center;
	}

	@Override
	public void process(double secs) {
	}
}
