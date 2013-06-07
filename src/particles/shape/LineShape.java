package jgf.particles.shape;

import java.util.Random;

import jgf.math.Vector2D;

public class LineShape implements EmitterShape {
	private Vector2D direction;
	private int size;

	private double startTime;
	private double lifeTime;

	public LineShape(int size, float angle, double startTime) {
		direction = Vector2D.createBySizeAngle(1, angle);
		this.size = size;
		lifeTime = 0;
		this.startTime = startTime;

	}

	public LineShape(int size, float angle) {
		this(size, angle, 0);
	}

	public LineShape(int size) {
		this(size, 0, 0);
	}

	@Override
	public Vector2D nextPosition(Vector2D center) {
		int maxSize = (int) (lifeTime >= startTime ? size : (size * lifeTime / startTime));
		int actualSize = (maxSize == 0 ? 0 : new Random().nextInt(maxSize) - size / 2);
		return direction.multiply(actualSize).addMe(center);
	}

	@Override
	public void process(double secs) {
		if (lifeTime >= startTime)
			return;

		lifeTime += secs;
	}
}
