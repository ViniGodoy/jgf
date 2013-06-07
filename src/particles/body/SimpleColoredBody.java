package jgf.particles.body;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import jgf.math.IntRange;
import jgf.particles.Particle.ParticleBody;
import jgf.particles.Particle.ParticleInfo;
import jgf.particles.Particle.ParticleLife;

public class SimpleColoredBody implements BodyCalculator {
	public enum CachingMode {
		NONE, SINGLE_COLOR, AGGRESSIVE;
	}
	
	private int size;
	private int r;
	private int g;
	private int b;
	private IntRange a;
	private CachingMode cachingMode = CachingMode.SINGLE_COLOR;
	
	private Map<Color, BufferedImage> cache = new HashMap<Color, BufferedImage>();

	public SimpleColoredBody(int size, int r, int g, int b, IntRange a) {
		this.size = size;
		setColor(r, g, b);
		setAlpha(a);		
	}

	public boolean isCaching() {
		return cache != null;
	}
	
	public void setCaching(CachingMode newMode) {
		if (newMode == null)
			throw new IllegalArgumentException("The caching mode cannot be null!");
		
		if (newMode == CachingMode.NONE)
			cache.clear();
		
		cachingMode = newMode;
	}


	@Override
	public ParticleBody init() {
		return new ParticleBody(r, g, b, a.getValue(0), size);
	}

	@Override
	public void calculate(double time, ParticleLife life, ParticleBody body) {
		body.setAlpha(a.getValue(life.getPercent()));
	}

	public int getRed() {
		return r;
	}

	public int getGreen() {
		return g;
	}

	public int getBlue() {
		return b;
	}

	public IntRange getAlpha() {
		return a;
	}

	public void setAlpha(IntRange a) {
		this.a = a;
	}

	public void setColor(int r, int g, int b) {
		this.r = r < 0 ? 0 : (r > 255 ? 255 : r);
		this.g = g < 0 ? 0 : (g > 255 ? 255 : g);
		this.b = b < 0 ? 0 : (b > 255 ? 255 : b);

	}

	public void setColor(int r, int g, int b, IntRange a) {
		setColor(r, g, b);
		setAlpha(a);
	}

	public void setColor(Color color) {
		setColor(color.getRed(), color.getGreen(), color.getBlue());
		a = new IntRange(color.getAlpha());

		if (cachingMode == CachingMode.SINGLE_COLOR)
			cache.clear();
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public void draw(Graphics2D g, ParticleInfo particle) {
		g.drawImage(createImage(particle), 
				(int) (particle.getX() - particle.getSize() / 2),
				(int) (particle.getY() - particle.getSize() / 2), null);
	}

	
	private BufferedImage createImage(ParticleInfo particle) {
		Color c = particle.getColor();
		if (cache != null && cache.containsKey(c))
			return cache.get(c);

		BufferedImage particleImage = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration()
				.createCompatibleImage(particle.getSize(), particle.getSize(),
						Transparency.TRANSLUCENT);

		Graphics2D g2d = particleImage.createGraphics();
		g2d.setColor(c);
		g2d.fill(new Ellipse2D.Double(0, 0, particle.getSize(), particle.getSize()));
		g2d.dispose();

		if (cachingMode != CachingMode.NONE)
			cache.put(c, particleImage);

		return particleImage;
	}
};