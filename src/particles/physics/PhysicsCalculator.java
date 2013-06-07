package jgf.particles.physics;

import jgf.math.Vector2D;
import jgf.particles.Particle.ParticleDynamics;
import jgf.particles.Particle.ParticleLife;

public interface PhysicsCalculator {
	ParticleDynamics init(Vector2D position);
	void calculate(double secs, ParticleLife life, ParticleDynamics physics);	
}
