package net.etfbl.pj2.model;

import java.util.Random;

public class VagonTeretni extends Vagon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5679040741192756611L;
	private double maksimalnaNosivost;

	public VagonTeretni() {
		super();
		Random random = new Random();
		maksimalnaNosivost = random.nextDouble() * 3000 + 2000;
	}

	public double getMaksimalnaNosivost() {
		return maksimalnaNosivost;
	}

	public void setMaksimalnaNosivost(double maksimalnaNosivost) {
		this.maksimalnaNosivost = maksimalnaNosivost;
	}

	@Override
	public String toString() {
		return "VagonTeretni[ maksimalna nosivost=" + maksimalnaNosivost + super.toString() + "]";
	}
}
