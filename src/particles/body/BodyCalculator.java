/******************************************************************************
*
* COPYRIGHT Vin�cius G. Mendon�a ALL RIGHTS RESERVED.
*
* This software cannot be copied, stored, distributed without  
* Vin�cius G.Mendon�a prior authorization.
*
* This file was made available on http://www.pontov.com.br and it is free 
* to be restributed or used under Creative Commons license 2.5 br: 
* http://creativecommons.org/licenses/by-sa/2.5/br/
*
*******************************************************************************
* Este software nao pode ser copiado, armazenado, distribuido sem autoriza��o 
* a priori de Vin�cius G. Mendon�a
*
* Este arquivo foi disponibilizado no site http://www.pontov.com.br e esta 
* livre para distrui��o seguindo a licen�a Creative Commons 2.5 br: 
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
