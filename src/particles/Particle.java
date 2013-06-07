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

import java.awt.Color;

import jgf.math.Vector2D;

public class Particle {
	private ParticleLife life;
	private ParticleDynamics physics;
	private ParticleBody body;

	public Particle(double lifeTime, ParticleDynamics physics, ParticleBody body) {
		life = new ParticleLife(lifeTime);
		this.physics = physics;
		this.body = body;
	}

	public ParticleLife getLife() {
		return life;
	}

	public ParticleDynamics getPhysics() {
		return physics;
	}

	public ParticleBody getBody() {
		return body;
	}

	public boolean isDead() {
		return life.age > life.time;
	}

	public ParticleInfo toInfo() {
		return new ParticleInfo(this);
	}

	public static class ParticleLife {
		private double age = 0;
		private double time;

		public ParticleLife(double time) {
			if (time <= 0)
				throw new IllegalArgumentException("Time must be >= 0!");
			this.time = time;
		}

		public double getAge() {
			return age;
		}

		public void age(double age) {
			this.age += age;
		}

		public double getTime() {
			return time;
		}

		public double getPercent() {
			return time == 0 ? 1 : age / time;
		}
	};

	public static class ParticleBody {
		private int red;
		private int green;
		private int blue;
		private int alpha;

		private int size;

		public ParticleBody(int r, int g, int b, int a, int size) {
			setColor(r, g, b);
			setAlpha(a);
			this.size = size;
		}

		public void setColor(int r, int g, int b) {
			this.red = r < 0 ? 0 : (r > 255 ? 255 : r);
			this.green = g < 0 ? 0 : (g > 255 ? 255 : g);
			this.blue = b < 0 ? 0 : (b > 255 ? 255 : b);
		}

		public int getRed() {
			return red;
		}

		public int getGreen() {
			return green;
		}

		public int getBlue() {
			return blue;
		}

		public int getAlpha() {
			return alpha;
		}

		public Color getColor() {
			return new Color(red, green, blue, alpha);
		}

		public void setAlpha(int a) {
			this.alpha = a < 0 ? 0 : (a > 255 ? 255 : a);
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

	};

	public static class ParticleDynamics {
		private Vector2D position;
		private Vector2D momentum;
		private double mass;

		public ParticleDynamics(Vector2D position, Vector2D momentum,
				double mass) {
			this.position = position.clone();
			this.momentum = momentum;
			this.mass = mass;
		}

		public Vector2D getPosition() {
			return position;
		}

		public Vector2D getMomentum() {
			return momentum;
		}

		public double getMass() {
			return mass;
		}

		public void setMass(double mass) {
			this.mass = mass;
		}
	}

	public static class ParticleInfo {
		private Particle particle;

		public ParticleInfo(Particle particle) {
			this.particle = particle;
		}

		public double getX()
		{
			return particle.getPhysics().getPosition().getX();
		}
		public double getY()
		{
			return particle.getPhysics().getPosition().getY();
		}
		
		public Vector2D getPosition() {
			return particle.getPhysics().getPosition().clone();
		}

		public Vector2D getMomentum() {
			return particle.getPhysics().getMomentum().clone();
		}

		public double getMass() {
			return particle.getPhysics().getMass();
		}

		public Color getColor() {
			return particle.getBody().getColor();
		}

		public int getSize() {
			return particle.getBody().getSize();
		}

		public double getAge() {
			return particle.getLife().getAge();
		}

		public double getTime() {
			return particle.getLife().getTime();
		}

		public double getLifePercent() {
			return particle.getLife().getPercent();
		}

		public boolean isDead() {
			return particle.isDead();
		}
	}
}