/******************************************************************************
 *
 * COPYRIGHT Vinícius G. Mendonça ALL RIGHTS RESERVED.
 *
 * This software cannot be copied, stored, distributed without  
 * Vinícius G.Mendonça prior authorization.
 *
 * This file was made available on http://www.pontov.com.br and it is free 
 * to be restributed or used under Creative Commons license 2.5 br: 
 * http://creativecommons.org/licenses/by-sa/2.5/br/
 *
 *******************************************************************************
 * Este software nao pode ser copiado, armazenado, distribuido sem autorização 
 * a priori de Vinícius G. Mendonça
 *
 * Este arquivo foi disponibilizado no site http://www.pontov.com.br e esta 
 * livre para distribuição seguindo a licença Creative Commons 2.5 br:
 * http://creativecommons.org/licenses/by-sa/2.5/br/
 *
 ******************************************************************************/
package jgf.particles;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jgf.math.DoubleRange;
import jgf.math.Vector2D;
import jgf.particles.Particle.ParticleInfo;
import jgf.particles.body.BodyCalculator;
import jgf.particles.physics.PhysicsCalculator;
import jgf.particles.shape.EmitterShape;
import jgf.particles.shape.PointShape;

public class Emitter {
	private List<Particle> particles = new ArrayList<Particle>();
	private List<ParticleListener> listeners = new ArrayList<ParticleListener>();

	private Vector2D position;
	private boolean active = true;

	public int particlesPerCreation = 1;
	private double creationsPerSec;
	private double lastCreation;

	private DoubleRange lifeTime;

	private EmitterShape shape;
	private PhysicsCalculator physics;
	private BodyCalculator body;

	public Emitter(Vector2D position, double creationsPerSec,
			DoubleRange lifeTime, PhysicsCalculator physics, BodyCalculator body) {		
		this.position = position.clone();
		this.creationsPerSec = creationsPerSec;
		this.lifeTime = new DoubleRange(lifeTime.getFrom(), lifeTime.getTo());
		this.shape = new PointShape();
		this.physics = physics;
		this.body = body;

	}

	public Emitter setParticlesPerCreation(int amount) {
		particlesPerCreation = amount;
		return this;
	}

	public int getParticlesPerCreation() {
		return particlesPerCreation;
	}

	public void createParticles() {
		for (int i = 0; i < particlesPerCreation; i++)
		{
			Particle particle = new Particle(
					lifeTime.random(), 
					physics.init(shape.nextPosition(position)), 
					body.init()); 
			particles.add(particle);
			fireParticleCreated(particle);			
		}
		lastCreation = 0;		
	}

	public void process(double secs) {
		Iterator<Particle> it = particles.iterator();
		while (it.hasNext()) {
			Particle particle = it.next();

			particle.getLife().age(secs);
			if (particle.isDead()) {				
				fireParticleDestroyed(particle);
				it.remove();
				continue;
			}
			physics.calculate(secs, particle.getLife(), particle.getPhysics());
			body.calculate(secs, particle.getLife(), particle.getBody());			
		}

		if (!active || creationsPerSec <= 0 && particlesPerCreation <= 0)
			return;

		double timeBetweenCreations = 1.0 / creationsPerSec;
		lastCreation += secs;		
		if (lastCreation > timeBetweenCreations)
			createParticles();

		shape.process(secs);
	}

	public void draw(Graphics2D g2d) {		
		for (Particle particle : particles)
			body.draw(g2d, particle.toInfo());
	}

	public Vector2D getPosition() {
		return position;
	}

	public DoubleRange getLifeTime() {
		return lifeTime;
	}

	public Emitter setLifeTime(DoubleRange newLifeTime) {
		this.lifeTime = newLifeTime;
		return this;
	}

	public Emitter setShape(EmitterShape shape) {
		this.shape = shape;
		return this;
	}

	public EmitterShape getShape() {
		return shape;
	}

	public Emitter setActive(boolean active) {
		this.active = active;
		return this;
	}

	public boolean isActive() {
		return active;
	}

	private void fireParticleCreated(Particle particle) {
		if (listeners.size() == 0)
			return;

		ParticleInfo info = particle.toInfo();
		for (ParticleListener listener : listeners)
			listener.particleCreated(info);
	}

	private void fireParticleDestroyed(Particle particle) {
		if (listeners.size() == 0)
			return;

		ParticleInfo info = particle.toInfo();
		for (ParticleListener listener : listeners)
			listener.particleDestroyed(info);
	}
	
	public void addParticleListener(ParticleListener listener) {
		listeners.add(listener);
	}
	
	public void removeParticleListener(ParticleListener listener) {
		listeners.remove(listener);
	}

	public int getCount() {
		return particles.size();
	}
}