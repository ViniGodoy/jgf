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
 * livre para distribui��o seguindo a licen�a Creative Commons 2.5 br:
 * http://creativecommons.org/licenses/by-sa/2.5/br/
 *
 ******************************************************************************/

package jgf.math;

import java.util.Random;

public final class DoubleRange {
	private static final Random RND = new Random();
	private double from;
	private double to;

	public DoubleRange(double fromTo) {
		set(fromTo, fromTo);
	}

	public DoubleRange(double from, double to) {
		set(from, to);
	}

	private void set(double from, double to) {
		this.from = from;
		this.to = to;
	}

	public double getFrom() {
		return from;
	}

	public double getTo() {
		return to;
	}

	public double getMin() {
		return to < from ? to : from;
	}

	public double getMax() {
		return to > from ? to : from;
	}

	public double random() {
		return getValue(RND.nextDouble());
	}

	public boolean isInRange(double value) {
		return value >= getMin() && value <= getMax();
	}

	public double getValue(double factor) {
		if (to == from)
			return to;

		factor = factor < 0 ? 0 : (factor > 1 ? 1 : factor);
		return intervalSign() * factor + from;
	}

	public double factor(double number) {
		if (to == from)
			return 1;
		return !isInRange(number) ? 0 : (number - from) / intervalSign();
	}

	private double intervalSign() {
		return to * 1.0 - from;
	}

	public double convert(double value, DoubleRange range) {
		return getValue(range.factor(value));
	}

	public double convert(int value, IntRange range) {
		return getValue(range.factor(value));
	}
}
