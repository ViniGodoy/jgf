package jgf.particles.shape;

import jgf.math.Vector2D;

public interface EmitterShape {
	Vector2D nextPosition(Vector2D center);
	void process(double secs);
}
