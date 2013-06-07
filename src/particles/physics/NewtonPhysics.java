package jgf.particles.physics;

import jgf.math.DoubleRange;
import jgf.math.Vector2D;
import jgf.particles.Particle.ParticleDynamics;
import jgf.particles.Particle.ParticleLife;

public class NewtonPhysics implements PhysicsCalculator {
	private DoubleRange force;
	private DoubleRange angle;
	private DoubleRange mass;

	private Vector2D forces = new Vector2D();
	private Vector2D gravity = new Vector2D();

	public NewtonPhysics(DoubleRange force, DoubleRange angle, DoubleRange mass) {
		this.force = force;
		this.angle = angle;
		this.mass = mass;
	}

	public Vector2D getForces() {
		return forces;
	}

	public Vector2D getGravity() {
		return gravity;
	}

	@Override
	public ParticleDynamics init(Vector2D position) {
		double mass = this.mass.random();		
		return new ParticleDynamics(position, Vector2D.createBySizeAngle(
				force.random(), angle.random()).divideMe(mass), mass);
	}

	@Override
	public void calculate(double secs, ParticleLife life,
			ParticleDynamics physics) {
		Vector2D accelSecs = forces.divide(physics.getMass()).addMe(gravity).multiplyMe(secs);
		physics.getPosition().addMe(physics.getMomentum().add(accelSecs.divide(2)).multiply(secs));
		physics.getMomentum().addMe(accelSecs);
	}
};