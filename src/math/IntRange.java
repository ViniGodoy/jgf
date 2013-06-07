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

package jgf.math;

import java.util.Random;

public final class IntRange {
	private static final Random RND = new Random();
	private int from;
	private int to;

	public IntRange(int fromTo) {
		set(fromTo, fromTo);
	}

	public IntRange(int from, int to) {
		set(from, to);
	}

	private void set(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getMin() {
		return to < from ? to : from;
	}

	public int getMax() {
		return to > from ? to : from;
	}

	public int random() {
		return getValue(RND.nextDouble());
	}

	public boolean isInRange(int value) {
		return value >= getMin() && value <= getMax();
	}

	public int getValue(double factor) {
		if (to == from)
			return to;

		factor = factor < 0 ? 0 : (factor > 1 ? 1 : factor);
		return (int) (intervalSign() * factor + from);
	}

	public double factor(int number) {
		if (to == from)
			return 1;
		return !isInRange(number) ? 0 : (number - from) / intervalSign();
	}

	private double intervalSign() {
		return to * 1.0 - from;
	}

	public int convert(double value, DoubleRange range) {
		return getValue(range.factor(value));
	}

	public int convert(int value, IntRange range) {
		return getValue(range.factor(value));
	}
}