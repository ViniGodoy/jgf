package jgf.particles;

import jgf.particles.Particle.ParticleInfo;

public interface ParticleListener {
	void particleCreated(ParticleInfo particle);	
	void particleDestroyed(ParticleInfo particle);
}

