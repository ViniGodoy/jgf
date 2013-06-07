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
* livre para distruição seguindo a licença Creative Commons 2.5 br: 
* http://creativecommons.org/licenses/by-sa/2.5/br/
*
******************************************************************************/

package jgf.particles.body;

import java.awt.Graphics2D;

import jgf.particles.Particle.ParticleBody;
import jgf.particles.Particle.ParticleInfo;
import jgf.particles.Particle.ParticleLife;

public interface BodyCalculator
{
	ParticleBody init();
	void calculate(double time, ParticleLife life, ParticleBody body);
	void draw(Graphics2D g, ParticleInfo particle);
};
